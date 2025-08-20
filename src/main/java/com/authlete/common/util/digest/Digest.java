package com.authlete.common.util.digest;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.EncoderException;

/**
 * A wrapper class over MessageDigest with many {@code update}
 * methods in a fluent style, meaning {@code update} methods
 * can be chained.
 *
 * <p>
 * {@code update} methods are provided for all the primitive types
 * and {@code String}, and their array types. In addition,
 * {@link #updateJson(String)} has been available since the version
 * 1.2 which updates the digest with the content of the given JSON.
 * Note that {@link #update(String)} and {@link #updateJson(String)}
 * are different.
 * </p>
 *
 * <p>
 * <code>getInstance<i>XXX</i></code> methods (where <code><i>XXX</i></code>
 * is a pre-defined algorithm name with hyphens removed) such as
 * {@link #getInstanceSHA1()} are provided. They won't throw
 * {@code NoSuchAlgorithmException}.
 * </p>
 *
 * <pre style="background-color: #EEEEEE; margin-left: 2em; margin-right: 2em; border: 1px solid black;">
 * <span style="color: darkgreen;">
 * // Compute SHA-1 of "Hello, world.".
 * // 'digest' will have "2ae01472317d1935a84797ec1983ae243fc6aa28".</span>
 * String digest = Digest.{@link #getInstanceSHA1()}
 *                 .{@link #update(String) update}(<span style="color: #990000">"Hello, world."</span>)
 *                 .{@link #digestAsString()};
 *
 * <span style="color: darkgreen;">// Compute SHA-1 of "Hello, world." and get the result as Base64.
 * // 'digest' will have "KuAUcjF9GTWoR5fsGYOuJD/Gqig=".</span>
 * String digest = Digest.{@link #getInstanceSHA1()}
 *                 .{@link #update(String) update}(<span style="color: #990000">"Hello, world."</span>)
 *                 .{@link #digestAsString(BinaryEncoder) digestAsString}(new {@link
 *                 org.apache.commons.codec.binary.Base64#Base64() Base64()});
 *
 * <span style="color: darkgreen;">// Compute SHA-1 of two JSONs.
 * // 'result1' and 'result2' will have the same value.</span>
 * String json1 = <span style="color: #990000">"{ \"key1\":\"value1\", \"key2\":\"value2\" }"</span>;
 * String json2 = <span style="color: #990000">"{ \"key2\":\"value2\", \"key1\":\"value1\" }"</span>;
 * String result1 = Digest.{@link #getInstanceSHA1()}.{@link #updateJson(String)
 * updateJson}(json1).{@link #digestAsString()};
 * String result2 = Digest.{@link #getInstanceSHA1()}.{@link #updateJson(String)
 * updateJson}(json2).{@link #digestAsString()};
 * </pre>
 *
 * @author Takahiko Kawasaki
 */
public class Digest implements Cloneable
{
    /**
     * Features to control behaviors.
     *
     * @since 1.2
     */
    public static enum Feature
    {
        /**
         * Ignore JSON key-value entries whose value is {@code null}.
         * In other words, JSON key-value entries whose value is
         * {@code null} are treated as if they did not exist.
         *
         * <p>
         * If this feature is enabled, two JSONs below generate
         * the same digest value.
         * </p>
         *
         * <pre>
         * { "key1":"value1", "key2":null }
         * { "key1":"value1" }
         * </pre>
         *
         * <p>
         * The default value is 'disabled'.
         * </p>
         */
        IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_NULL,


        /**
         * Ignore JSON key-value entries whose value is {@code false}.
         * In other words, JSON key-value entries whose value is
         * {@code false} are treated as if they did not exist.
         *
         * <p>
         * If this feature is enabled, two JSONs below generate
         * the same digest value.
         * </p>
         *
         * <pre>
         * { "key1":"value1", "key2":false }
         * { "key1":"value1" }
         * </pre>
         *
         * <p>
         * The default value is 'disabled'.
         * </p>
         *
         * @since 1.5
         */
        IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_FALSE,


        /**
         * Ignore JSON key-value entries whose value is zero.
         * In other words, JSON key-value entries whose value is
         * zero are treated as if they did not exist.
         *
         * <p>
         * If this feature is enabled, two JSONs below generate
         * the same digest value.
         * </p>
         *
         * <pre>
         * { "key1":"value1", "key2":0, "key3":0.0 }
         * { "key1":"value1" }
         * </pre>
         *
         * <p>
         * The default value is 'disabled'.
         * </p>
         *
         * @since 1.5
         */
        IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_ZERO,


        /**
         * Ignore JSON key-value entries whose value is an empty string.
         * In other words, JSON key-value entries whose value is an empty
         * string are treated as if they did not exist.
         *
         * <p>
         * If this feature is enabled, two JSONs below generate
         * the same digest value.
         * </p>
         *
         * <pre>
         * { "key1":"value1", "key2":"" }
         * { "key1":"value1" }
         * </pre>
         *
         * <p>
         * The default value is 'disabled'.
         * </p>
         *
         * @since 1.5
         */
        IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_EMPTY_STRING,


        /**
         * Ignore JSON key-value entries whose value is an empty array.
         * In other words, JSON key-value entries whose value is an empty
         * array are treated as if they did not exist.
         *
         * <p>
         * If this feature is enabled, two JSONs below generate
         * the same digest value.
         * </p>
         *
         * <pre>
         * { "key1":"value1", "key2":[] }
         * { "key1":"value1" }
         * </pre>
         *
         * <p>
         * The default value is 'disabled'.
         * </p>
         *
         * @since 1.5
         */
        IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_EMPTY_ARRAY,


        /**
         * Ignore JSON key-value entries whose value is an empty object.
         * In other words, JSON key-value entries whose value is an empty
         * object are treated as if they did not exist.
         *
         * <p>
         * If this feature is enabled, two JSONs below generate
         * the same digest value.
         * </p>
         *
         * <pre>
         * { "key1":"value1", "key2":{} }
         * { "key1":"value1" }
         * </pre>
         *
         * <p>
         * The default value is 'disabled'.
         * </p>
         *
         * @since 1.5
         */
        IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_EMPTY_OBJECT,


        /**
         * Sort keys of JSON key-value entries before updating.
         *
         * <p>
         * If this feature is enabled, two JSONs below generate
         * the same digest value.
         * </p>
         *
         * <pre>
         * { "key1":"value1", "key2":"value2" }
         * { "key2":"value2", "key1":"value1" }
         * </pre>
         *
         * <p>
         * The default value is 'enabled'.
         * </p>
         */
        SORT_JSON_OBJECT_ENTRY_KEYS
    }


    /**
     * Characters used to generate a hex string.
     */
    private static final char[] mHexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };


    /**
     * The wrapped messaged digest object.
     */
    private MessageDigest mMessageDigest;


    /**
     * Features (configuration).
     */
    private HashMap<Feature, Boolean> mFeatures;


    /**
     * Constructor with a {@link MessageDigest} instance.
     *
     * @param messageDigest
     *         A {@link MessageDigest} instance wrapped in this
     *         {@code Digest} instance.
     *
     * @throws IllegalArgumentException
     *         The given argument is null.
     */
    public Digest(MessageDigest messageDigest)
    {
        if (messageDigest == null)
        {
            throw new IllegalArgumentException("messageDigest is null");
        }

        mMessageDigest = messageDigest;
        mFeatures = createFeatureMap();
    }


    /**
     * Create a feature map with default values.
     */
    private HashMap<Feature, Boolean> createFeatureMap()
    {
        HashMap<Feature, Boolean> map = new HashMap<Feature, Boolean>();

        map.put(Feature.IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_NULL,         Boolean.FALSE);
        map.put(Feature.IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_FALSE,        Boolean.FALSE);
        map.put(Feature.IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_ZERO,         Boolean.FALSE);
        map.put(Feature.IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_EMPTY_STRING, Boolean.FALSE);
        map.put(Feature.IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_EMPTY_ARRAY,  Boolean.FALSE);
        map.put(Feature.IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_EMPTY_OBJECT, Boolean.FALSE);
        map.put(Feature.SORT_JSON_OBJECT_ENTRY_KEYS, Boolean.TRUE);

        return map;
    }


    /**
     * Constructor with an algorithm name.
     *
     * <p>
     * This constructor is equivalent to {@link #Digest(MessageDigest) this}{@code
     * (}{@link MessageDigest#getInstance(String) MessageDigest.getInstance}{@code
     * (algorithm))}.
     * </p>
     *
     * @param algorithm
     *         Algorithm name such as "MD5" and "SHA-1".
     *
     * @throws NoSuchAlgorithmException
     *         No provider supports the specified algorithm.
     *
     * @since 1.2
     */
    public Digest(String algorithm) throws NoSuchAlgorithmException
    {
        this(MessageDigest.getInstance(algorithm));
    }


    /**
     * Constructor with an algorithm name and a provider.
     *
     * <p>
     * This constructor is equivalent to {@link #Digest(MessageDigest) this}{@code
     * (}{@link MessageDigest#getInstance(String,String) MessageDigest.getInstance}{@code
     * (algorithm, provider))}.
     * </p>
     *
     * @param algorithm
     *         Algorithm name such as "MD5" and "SHA-1".
     *
     * @param provider
     *         Provider name.
     *
     * @throws NoSuchAlgorithmException
     *         The provider does not support the specified algorithm.
     *
     * @throws NoSuchProviderException
     *         The specified provider is not registered in the security provider list.
     *
     * @since 1.2
     */
    public Digest(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException
    {
        this(MessageDigest.getInstance(algorithm, provider));
    }


    /**
     * Constructor with an algorithm name and a provider.
     *
     * <p>
     * This constructor is equivalent to {@link #Digest(MessageDigest) this}{@code
     * (}{@link MessageDigest#getInstance(String,Provider) MessageDigest.getInstance}{@code
     * (algorithm, provider))}.
     * </p>
     *
     * @param algorithm
     *         Algorithm name such as "MD5" and "SHA-1".
     *
     * @param provider
     *         Provider.
     *
     * @throws NoSuchAlgorithmException
     *         The provider does not support the specified algorithm.
     *
     * @since 1.2
     */
    public Digest(String algorithm, Provider provider) throws NoSuchAlgorithmException
    {
        this(MessageDigest.getInstance(algorithm, provider));
    }


    /**
     * Create a {@code Digest} instance with the specified algorithm.
     *
     * <p>
     * This method creates a {@link MessageDigest} instance by
     * {@link MessageDigest#getInstance(String)} and wraps it
     * in a {@code Digest} instance.
     * </p>
     *
     * @param algorithm
     *         Algorithm name such as "MD5" and "SHA-1".
     *
     * @return
     *         A {@code Digest} instance that implements the specified algorithm.
     *
     * @throws NoSuchAlgorithmException
     *         No provider supports the specified algorithm.
     */
    public static Digest getInstance(String algorithm) throws NoSuchAlgorithmException
    {
        return new Digest(algorithm);
    }


    /**
     * Create a {@code Digest} instance with the specified algorithm.
     *
     * <p>
     * This method creates a {@link MessageDigest} instance by
     * {@link MessageDigest#getInstance(String, String)} and wraps it
     * in a {@code Digest} instance.
     * </p>
     *
     * @param algorithm
     *         Algorithm name such as "MD5" and "SHA-1".
     *
     * @param provider
     *         Provider name.
     *
     * @return
     *         A {@code Digest} instance that implements the specified algorithm.
     *
     * @throws NoSuchAlgorithmException
     *         The provider does not support the specified algorithm.
     *
     * @throws NoSuchProviderException
     *         The specified provider is not registered in the security provider list.
     */
    public static Digest getInstance(String algorithm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException
    {
        return new Digest(algorithm, provider);
    }


    /**
     * Create a {@code Digest} instance with the specified algorithm.
     *
     * <p>
     * This method creates a {@link MessageDigest} instance by
     * {@link MessageDigest#getInstance(String, Provider)} and wraps it
     * in a {@code Digest} instance.
     * </p>
     *
     * @param algorithm
     *         Algorithm name such as "MD5" and "SHA-1".
     *
     * @param provider
     *         Provider.
     *
     * @return
     *         A {@code Digest} instance that implements the specified algorithm.
     *
     * @throws NoSuchAlgorithmException
     *         The provider does not support the specified algorithm.
     */
    public static Digest getInstance(String algorithm, Provider provider) throws NoSuchAlgorithmException
    {
        return new Digest(algorithm, provider);
    }


    /**
     * Create a {@code Digest} instance with the specified algorithm.
     *
     * <p>
     * This method exists just to ignore {@code NoSuchAlgorithmException}.
     * </p>
     *
     * @param algorithm
     *         Algorithm name such as "MD5" and "SHA-1".
     *
     * @return
     *         A {@code Digest} instance that implements the specified algorithm.
     */
    private static Digest getInstancePredefined(String algorithm)
    {
        try
        {
            return getInstance(algorithm);
        }
        catch (NoSuchAlgorithmException e)
        {
            // This won't happen.
            return null;
        }
    }


    /**
     * Create a {@code Digest} instance that implements MD2.
     *
     * @return
     *         A {@code Digest} instance that implements MD2.
     */
    public static Digest getInstanceMD2()
    {
        return getInstancePredefined("MD2");
    }


    /**
     * Create a {@code Digest} instance that implements MD5.
     *
     * @return
     *         A {@code Digest} instance that implements MD5.
     */
    public static Digest getInstanceMD5()
    {
        return getInstancePredefined("MD5");
    }


    /**
     * Create a {@code Digest} instance that implements SHA-1.
     *
     * @return
     *         A {@code Digest} instance that implements SHA-1.
     */
    public static Digest getInstanceSHA1()
    {
        return getInstancePredefined("SHA-1");
    }


    /**
     * Create a {@code Digest} instance that implements SHA-256.
     *
     * @return
     *         A {@code Digest} instance that implements SHA-256.
     */
    public static Digest getInstanceSHA256()
    {
        return getInstancePredefined("SHA-256");
    }


    /**
     * Create a {@code Digest} instance that implements SHA-384.
     *
     * @return
     *         A {@code Digest} instance that implements SHA-384.
     */
    public static Digest getInstanceSHA384()
    {
        return getInstancePredefined("SHA-384");
    }


    /**
     * Create a {@code Digest} instance that implements SHA-512.
     *
     * @return
     *         A {@code Digest} instance that implements SHA-512.
     */
    public static Digest getInstanceSHA512()
    {
        return getInstancePredefined("SHA-512");
    }


    /**
     * Get the algorithm name.
     *
     * <p>
     * This method just calls {@link MessageDigest#getAlgorithm()
     * getAlgorithm()} of the wrapped {@code MessageDigest} instance.
     * </p>
     *
     * @return
     *         Algorithm name.
     */
    public String getAlgorithm()
    {
        return mMessageDigest.getAlgorithm();
    }


    /**
     * Get the length of the digest in bytes.
     *
     * <p>
     * This method just calls {@link MessageDigest#getDigestLength()
     * getDigestLength()} of the wrapped {@code MessageDigest} instance.
     * </p>
     *
     * @return
     *         Length of the digest in bytes.
     */
    public int getDigestLength()
    {
        return mMessageDigest.getDigestLength();
    }


    /**
     * Get the provider.
     *
     * <p>
     * This method just calls {@link MessageDigest#getProvider()
     * getProvider()} of the wrapped {@code MessageDigest} instance.
     * </p>
     *
     * @return
     *         Provider.
     */
    public Provider getProvider()
    {
        return mMessageDigest.getProvider();
    }


    /**
     * Get the wrapped {@code MessageDigest} instance.
     *
     * @return
     *         The {@code MessageDigest} instance that has been
     *         given to the constructor.
     */
    public MessageDigest getWrappedMessageDigest()
    {
        return mMessageDigest;
    }


    /**
     * Get a clone of this {@code Digest} instance.
     *
     * @return
     *         A cloned object.
     *
     * @throws CloneNotSupportedException
     *         The implementation does not support {@code clone} operation.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Digest cloned = (Digest)super.clone();

        cloned.mMessageDigest = (MessageDigest)mMessageDigest.clone();
        cloned.mFeatures      = (HashMap<Feature, Boolean>)mFeatures.clone();

        return cloned;
    }


    /**
     * Complete the hash computation. The digest is reset after
     * this call is made.
     *
     * <p>
     * This method just calls {@link MessageDigest#digest() digest()}
     * method of the wrapped {@code MessageDigest} instance.
     * </p>
     *
     * @return
     *         The resulting hash value.
     */
    public byte[] digest()
    {
        return mMessageDigest.digest();
    }


    /**
     * Perform the final update with the given byte array, and then
     * complete the hash computation. The digest is reset after
     * this call is made.
     *
     * <p>
     * This method just calls {@link MessageDigest#digest(byte[])
     * digest(byte[])} method of the wrapped {@code MessageDigest}
     * instance.
     * </p>
     *
     * @param input
     *         Byte array used for the last update.
     *
     * @return
     *         The resulting hash value.
     */
    public byte[] digest(byte[] input)
    {
        return mMessageDigest.digest(input);
    }


    /**
     * Complete the hash computation. The digest is reset after
     * this call is made.
     *
     * <p>
     * This method just calls {@link MessageDigest#digest(byte[],int,int)
     * digest(byte[], int, int)} method of the wrapped {@code MessageDigest}
     * instance.
     * </p>
     *
     * @param output
     *         Output buffer for the computed digest.
     *
     * @param offset
     *         Offset into the output buffer to begin storing the digest.
     *
     * @param length
     *         Number of bytes within the output buffer allotted for the digest.
     *
     * @return
     *         The resulting hash value.
     *
     * @throws DigestException
     *         The {@code digest} method of the {@link MessageDigest} class
     *         failed.
     */
    public int digest(byte[] output, int offset, int length) throws DigestException
    {
        return mMessageDigest.digest(output, offset, length);
    }


    /**
     * Complete the hash computation and get the resulting hash value
     * as a hex string. The digest is reset after this call is made.
     *
     * <p>
     * This method calls {@link #digest()} method and converts the result
     * to a String object.
     * </p>
     *
     * @return
     *         The result hash value represented in a hex String.
     */
    public String digestAsString()
    {
        return bytesToHex(digest());
    }


    /**
     * Perform the final update with the given byte array, and then
     * complete the hash computation and get the resulting hash value
     * as a hex string. The digest is reset after this call is made.
     *
     * <p>
     * This method calls {@link #digest(byte[])} method and converts
     * the result to a String object.
     * </p>
     *
     * @param input
     *         Byte array used for the last update.
     *
     * @return
     *         The result hash value represented in a hex String.
     */
    public String digestAsString(byte[] input)
    {
        return bytesToHex(digest(input));
    }


    /**
     * Complete the hash computation and get the resulting hash value
     * as a string. The given encoder is used to convert the digest
     * value to a string.
     *
     * <p>
     * This method is an alias of {@link #digestAsString(byte[],
     * BinaryEncoder) digestAsString((byte[])null, encoder)}.
     * </p>
     *
     * @param encoder
     *         Encoder to convert a digest value to a byte array
     *         whose elements are printable characters. For example,
     *         {@link org.apache.commons.codec.binary.Base64}.
     *
     * @return
     *         The result hash value encoded by the encoder.
     *
     * @throws RuntimeException
     *         If the encoder throws {@link EncoderException},
     *         a {@code RuntimeException} wrapping the
     *         {@code EncoderException} is thrown.
     *
     * @since 1.3
     */
    public String digestAsString(BinaryEncoder encoder)
    {
        return digestAsString((byte[])null, encoder);
    }


    /**
     * Perform the final update with the given byte array, and then
     * complete the hash computation and get the resulting hash value
     * as a string. The given encoder is used to convert the digest
     * value to a string.
     *
     * @param input
     *         Byte array used for the last update. If {@code null}
     *         is given, it is just ignored.
     *
     * @param encoder
     *         Encoder to convert a digest value to a byte array
     *         whose elements are printable characters. For example,
     *         {@link org.apache.commons.codec.binary.Base64}.
     *
     * @return
     *         The result hash value encoded by the encoder.
     *
     * @throws IllegalArgumentException
     *         {@code encoder} is {@code null}.
     *
     * @throws RuntimeException
     *         If the encoder throws {@link EncoderException},
     *         a {@code RuntimeException} wrapping the
     *         {@code EncoderException} is thrown.
     *
     * @since 1.3
     */
    public String digestAsString(byte[] input, BinaryEncoder encoder)
    {
        if (encoder == null)
        {
            throw new IllegalArgumentException("encoder is null.");
        }

        // Compute the digest value.
        byte[] digest = (input != null) ? digest(input) : digest();

        // Encoded value.
        byte[] encoded = null;

        try
        {
            // Encode the digest value.
            encoded = encoder.encode(digest);
        }
        catch (EncoderException e)
        {
            // Failed to encode the digest value.
            throw new RuntimeException("Failed to encode the digest value.", e);
        }

        try
        {
            // Convert the byte array into a string.
            return new String(encoded, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // This won't happen.
            return null;
        }
    }


    /**
     * Reset the wrapped {@code MessageDigest} instance.
     *
     * @return
     *         {@code this} object.
     */
    public Digest reset()
    {
        mMessageDigest.reset();

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link MessageDigest#update(byte) update(byte)}
     * method of the wrapped {@code MessageDigest} instance.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(byte input)
    {
        mMessageDigest.update(input);

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link MessageDigest#update(byte[]) update(byte[])}
     * method of the wrapped {@code MessageDigest} instance.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(byte[] input)
    {
        mMessageDigest.update(input);

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link MessageDigest#update(byte[],int,int)
     * update(byte[], int, int)} method of the wrapped
     * {@code MessageDigest} instance.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @param offset
     *         The offset to start from in the array.
     *
     * @param length
     *         The number of elements to use, starting at offset.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(byte[] input, int offset, int length)
    {
        mMessageDigest.update(input, offset, length);

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link MessageDigest#update(ByteBuffer)
     * update(ByteBuffer)} method of the wrapped {@code MessageDigest}
     * instance.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(ByteBuffer input)
    {
        mMessageDigest.update(input);

        return this;
    }


    /**
     * Check the validity of the combination of the given parameters.
     *
     * @param size
     *         Size of an array.
     *
     * @param offset
     *         Offset in the array.
     *
     * @param length
     *         Length of data to use.
     *
     * @throws IllegalArgumentException
     * <ul>
     * <li>{@code offset < 0}
     * <li>{@code size <= offset}
     * <li>{@code length < 0}
     * <li>{@code size < (offset + length)}
     * </ul>
     */
    private void checkRange(int size, int offset, int length)
    {
        String message = null;

        if (offset < 0)
        {
            message = "The offset is less than 0.";
        }
        else if (size <= offset)
        {
            message = "The offset is equal to or greater than the size.";
        }
        else if (length < 0)
        {
            message = "The length is less than 0.";
        }
        else if (size < (offset + length))
        {
            message = "The sum of the offset and the length is greater than the size.";
        }

        if (message != null)
        {
            throw new IllegalArgumentException(message);
        }
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(boolean)
     * update}{@code (input.booleanValue())}.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.4
     */
    public Digest update(Boolean input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input.booleanValue());
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(Boolean[], int, int)
     * update}{@code (input, 0, input.length)}.
     * </p>
     *
     * @param input
     *         Input data. If null is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.4
     */
    public Digest update(Boolean[] input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input, 0, input.length);
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(Boolean)} for each
     * array element which is in the specified range.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @param offset
     *         The offset to start from in the array.
     *
     * @param length
     *         The number of elements to use, starting at offset.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The range specified by the parameters is invalid.
     *
     * @since 1.4
     */
    public Digest update(Boolean[] input, int offset, int length)
    {
        if (input == null)
        {
            return this;
        }

        checkRange(input.length, offset, length);

        for (int i = 0; i < length; ++i)
        {
            update(input[i + offset]);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * @param input
     *         Input data. {@code true} results in {@link #update(byte)
     *         update}{@code ((byte)1)} and {@code false} results in
     *         {@link #update(byte) update}{@code ((byte)0)}.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(boolean input)
    {
        return update((byte)(input ? 1 : 0));
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(boolean[], int, int)
     * update}{@code (input, 0, input.length)}.
     * </p>
     *
     * @param input
     *         Input data. If null is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(boolean[] input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input, 0, input.length);
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(boolean)} for each
     * array element which is in the specified range.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @param offset
     *         The offset to start from in the array.
     *
     * @param length
     *         The number of elements to use, starting at offset.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The range specified by the parameters is invalid.
     */
    public Digest update(boolean[] input, int offset, int length)
    {
        if (input == null)
        {
            return this;
        }

        checkRange(input.length, offset, length);

        for (int i = 0; i < length; ++i)
        {
            update(input[i + offset]);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(char)
     * update}{@code (input.charValue())}.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.4
     */
    public Digest update(Character input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input.charValue());
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(Character[], int, int)
     * update}{@code (input, 0, input.length)}.
     * </p>
     *
     * @param input
     *         Input data. If null is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.4
     */
    public Digest update(Character[] input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input, 0, input.length);
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(Character)} for each
     * array element which is in the specified range.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @param offset
     *         The offset to start from in the array.
     *
     * @param length
     *         The number of elements to use, starting at offset.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The range specified by the parameters is invalid.
     *
     * @since 1.4
     */
    public Digest update(Character[] input, int offset, int length)
    {
        if (input == null)
        {
            return this;
        }

        checkRange(input.length, offset, length);

        for (int i = 0; i < length; ++i)
        {
            update(input[i + offset]);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(byte) update}{@code
     * ((byte)((input >> 8) & 0xff))} and {@link #update(byte)
     * update}({@code ((byte)(input >> 0) & 0xff)}.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(char input)
    {
        update((byte)((input >> 8) & 0xff));
        update((byte)((input >> 0) & 0xff));

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(char[], int, int)
     * update}{@code (input, 0, input.length)}.
     * </p>
     *
     * @param input
     *         Input data. If null is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(char[] input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input, 0, input.length);
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(char)} for each
     * array element which is in the specified range.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @param offset
     *         The offset to start from in the array.
     *
     * @param length
     *         The number of elements to use, starting at offset.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The range specified by the parameters is invalid.
     */
    public Digest update(char[] input, int offset, int length)
    {
        if (input == null)
        {
            return this;
        }

        checkRange(input.length, offset, length);

        for (int i = 0; i < length; ++i)
        {
            update(input[i + offset]);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(byte) update}{@code
     * ((byte)((input >> 8) & 0xff))} and {@link #update(byte)
     * update}({@code ((byte)(input >> 0) & 0xff)}.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(short input)
    {
        update((byte)((input >> 8) & 0xff));
        update((byte)((input >> 0) & 0xff));

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(short[], int, int)
     * update}{@code (input, 0, input.length)}.
     * </p>
     *
     * @param input
     *         Input data. If null is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(short[] input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input, 0, input.length);
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(short)} for each
     * array element which is in the specified range.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @param offset
     *         The offset to start from in the array.
     *
     * @param length
     *         The number of elements to use, starting at offset.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The range specified by the parameters is invalid.
     */
    public Digest update(short[] input, int offset, int length)
    {
        if (input == null)
        {
            return this;
        }

        checkRange(input.length, offset, length);

        for (int i = 0; i < length; ++i)
        {
            update(input[i + offset]);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(byte)} for each byte of the
     * 4 bytes from MSB to LSB (from {@code ((input >> 24) & 0xff)}
     * to {@code ((input >> 0) & 0xff)}).
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(int input)
    {
        update((byte)((input >> 24) & 0xff));
        update((byte)((input >> 16) & 0xff));
        update((byte)((input >>  8) & 0xff));
        update((byte)((input >>  0) & 0xff));

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(int[], int, int)
     * update}{@code (input, 0, input.length)}.
     * </p>
     *
     * @param input
     *         Input data. If null is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(int[] input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input, 0, input.length);
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(int)} for each
     * array element which is in the specified range.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @param offset
     *         The offset to start from in the array.
     *
     * @param length
     *         The number of elements to use, starting at offset.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The range specified by the parameters is invalid.
     */
    public Digest update(int[] input, int offset, int length)
    {
        if (input == null)
        {
            return this;
        }

        checkRange(input.length, offset, length);

        for (int i = 0; i < length; ++i)
        {
            update(input[i + offset]);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(byte)} for each byte of the
     * 8 bytes from MSB to LSB (from {@code ((input >> 54) & 0xff)}
     * to {@code ((input >> 0) & 0xff)}).
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(long input)
    {
        update((byte)((input >> 54) & 0xff));
        update((byte)((input >> 48) & 0xff));
        update((byte)((input >> 40) & 0xff));
        update((byte)((input >> 32) & 0xff));
        update((byte)((input >> 24) & 0xff));
        update((byte)((input >> 16) & 0xff));
        update((byte)((input >>  8) & 0xff));
        update((byte)((input >>  0) & 0xff));

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(long[], int, int)
     * update}{@code (input, 0, input.length)}.
     * </p>
     *
     * @param input
     *         Input data. If null is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(long[] input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input, 0, input.length);
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(long)} for each
     * array element which is in the specified range.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @param offset
     *         The offset to start from in the array.
     *
     * @param length
     *         The number of elements to use, starting at offset.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The range specified by the parameters is invalid.
     */
    public Digest update(long[] input, int offset, int length)
    {
        if (input == null)
        {
            return this;
        }

        checkRange(input.length, offset, length);

        for (int i = 0; i < length; ++i)
        {
            update(input[i + offset]);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method converts the given {@code float} value to a
     * {@code int} by {@link Float#floatToRawIntBits(float)}
     * and then passes it to {@link #update(int)}.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(float input)
    {
        return update(Float.floatToRawIntBits(input));
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(float[], int, int)
     * update}{@code (input, 0, input.length)}.
     * </p>
     *
     * @param input
     *         Input data. If null is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(float[] input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input, 0, input.length);
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(float)} for each
     * array element which is in the specified range.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @param offset
     *         The offset to start from in the array.
     *
     * @param length
     *         The number of elements to use, starting at offset.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The range specified by the parameters is invalid.
     */
    public Digest update(float[] input, int offset, int length)
    {
        if (input == null)
        {
            return this;
        }

        checkRange(input.length, offset, length);

        for (int i = 0; i < length; ++i)
        {
            update(input[i + offset]);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method converts the given {@code double} value to a
     * {@code long} by {@link Double#doubleToRawLongBits(double)}
     * and then passes it to {@link #update(long)}.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(double input)
    {
        return update(Double.doubleToRawLongBits(input));
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(double[], int, int)
     * update}{@code (input, 0, input.length)}.
     * </p>
     *
     * @param input
     *         Input data. If null is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(double[] input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input, 0, input.length);
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(double)} for each
     * array element which is in the specified range.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @param offset
     *         The offset to start from in the array.
     *
     * @param length
     *         The number of elements to use, starting at offset.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The range specified by the parameters is invalid.
     */
    public Digest update(double[] input, int offset, int length)
    {
        if (input == null)
        {
            return this;
        }

        checkRange(input.length, offset, length);

        for (int i = 0; i < length; ++i)
        {
            update(input[i + offset]);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method checks the class of the given instance and
     * calls a corresponding {@code update} method.
     * </p>
     *
     * <table border="1" style="margin: 1em; border-collapse: collapse; padding: 5px;">
     *   <caption></caption>
     *   <tr style="background-color: orange;">
     *     <th>Class</th>
     *     <th>Executed code</th>
     *   </tr>
     *   <tr>
     *     <td>{@code Byte}</td>
     *     <td>{@link #update(byte) update}{@code (((Byte)number).byteValue())}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code Short}</td>
     *     <td>{@link #update(short) update}{@code (((Short)number).shortValue())}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code Integer}</td>
     *     <td>{@link #update(int) update}{@code (((Integer)number).intValue())}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code Long}</td>
     *     <td>{@link #update(long) update}{@code (((Long)number).longValue())}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code Float}</td>
     *     <td>{@link #update(float) update}{@code (((Float)number).floatValue())}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code Double}</td>
     *     <td>{@link #update(double) update}{@code (((Double)number).doubleValue())}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code BigInteger}</td>
     *     <td>{@link #update(byte[]) update}{@code (((BigInteger)number).toByteArray())}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code BigDecimal}</td>
     *     <td>{@link #update(String) update}{@code (((BigDecimal)number).toString())}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code AtomicInteger}</td>
     *     <td>{@link #update(int) update}{@code (((AtomicInteger)number).intValue())}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code AtomicLong}</td>
     *     <td>{@link #update(long) update}{@code (((AtomicLong)number).longValue())}</td>
     *   </tr>
     *   <tr>
     *     <td>Others</td>
     *     <td>Ignored.</td>
     *   </tr>
     * </table>
     *
     * @param number
     *         Input data. If null or none of the above, update is not performed.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.1
     */
    public Digest update(Number number)
    {
        if (number == null)
        {
            return this;
        }

        // Byte
        if (number instanceof Byte)
        {
            update(((Byte)number).byteValue());
        }
        // Short
        else if (number instanceof Short)
        {
            update(((Short)number).shortValue());
        }
        // Integer
        else if (number instanceof Integer)
        {
            update(((Integer)number).intValue());
        }
        // Long
        else if (number instanceof Long)
        {
            update(((Long)number).longValue());
        }
        // Float
        else if (number instanceof Float)
        {
            update(((Float)number).floatValue());
        }
        // Double
        else if (number instanceof Double)
        {
            update(((Double)number).doubleValue());
        }
        // BigInteger
        else if (number instanceof BigInteger)
        {
            update(((BigInteger)number).toByteArray());
        }
        // BigDecimal
        else if (number instanceof BigDecimal)
        {
            update(((BigDecimal)number).toString());
        }
        // AtomicInteger
        else if (number instanceof AtomicInteger)
        {
            update(((AtomicInteger)number).intValue());
        }
        // AtomicLong
        else if (number instanceof AtomicLong)
        {
            update(((AtomicLong)number).longValue());
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(Number[], int, int)
     * update}{@code (input, 0, input.length)}.
     * </p>
     *
     * @param <TNumber>
     *         The subclass of the {@link Number} class.
     *
     * @param input
     *         Input data. If null is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.1
     */
    public <TNumber extends Number> Digest update(TNumber[] input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input, 0, input.length);
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(Number)} for each
     * array element which is in the specified range.
     * </p>
     *
     * @param <TNumber>
     *         The subclass of the {@link Number} class.
     *
     * @param input
     *         Input data.
     *
     * @param offset
     *         The offset to start from in the array.
     *
     * @param length
     *         The number of elements to use, starting at offset.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The range specified by the parameters is invalid.
     *
     * @since 1.1
     */
    public <TNumber extends Number> Digest update(TNumber[] input, int offset, int length)
    {
        if (input == null)
        {
            return this;
        }

        checkRange(input.length, offset, length);

        for (int i = 0; i < length; ++i)
        {
            update(input[i + offset]);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method converts the given string into bytes with the
     * character set of UTF-8 and then passes the byte array to
     * {@link #update(byte[])}.
     * </p>
     *
     * @param input
     *         Input data. If null is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(String input)
    {
        if (input == null)
        {
            return this;
        }

        try
        {
            return update(input.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            // This won't happen.
            return this;
        }
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method is an alias of {@link #update(String[], int, int)
     * update}{@code (input, 0, input.length)}.
     * </p>
     *
     * @param input
     *         Input data. If null is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     */
    public Digest update(String[] input)
    {
        if (input == null)
        {
            return this;
        }

        return update(input, 0, input.length);
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(String)} for each
     * array element which is in the specified range.
     * </p>
     *
     * @param input
     *         Input data.
     *
     * @param offset
     *         The offset to start from in the array.
     *
     * @param length
     *         The number of elements to use, starting at offset.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         The range specified by the parameters is invalid.
     */
    public Digest update(String[] input, int offset, int length)
    {
        if (input == null)
        {
            return this;
        }

        checkRange(input.length, offset, length);

        for (int i = 0; i < length; ++i)
        {
            update(input[i + offset]);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(Object)} for each element.
     * </p>
     *
     * @param input
     *         Input data. If {@code null} is given, update is not performed.
     *         {@code null} elements are ignored. Elements of unsupported
     *         classes are ignored, too.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.1
     */
    public Digest update(Iterable<?> input)
    {
        if (input == null)
        {
            return this;
        }

        for (Object element : input)
        {
            if (element == null)
            {
                continue;
            }

            update(element);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method calls {@link #update(Object)} for each element.
     * </p>
     *
     * @param input
     *         Input data. If {@code null} is given, update is not performed.
     *         {@code null} elements are ignored. Elements of unsupported
     *         classes are ignored, too.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.4
     */
    public Digest update(Object... input)
    {
        if (input == null)
        {
            return this;
        }

        for (Object element : input)
        {
            if (element == null)
            {
                continue;
            }

            update(element);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given input data.
     *
     * <p>
     * This method checks the class of the given object and calls
     * a corresponding {@code update} method.
     * </p>
     *
     * @param input
     *         Input data. If {@code null} is given, update is not performed.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.4
     */
    public Digest update(Object input)
    {
        if (input == null)
        {
            return this;
        }

        if (input instanceof String)
        {
            update((String)input);
        }
        else if (input instanceof String[])
        {
            update((String[])input);
        }
        else if (input instanceof Character)
        {
            update((Character)input);
        }
        else if (input instanceof Character[])
        {
            update((Character[])input);
        }
        else if (input instanceof Boolean)
        {
            update((Boolean)input);
        }
        else if (input instanceof boolean[])
        {
            update((boolean[])input);
        }
        else if (input instanceof Boolean[])
        {
            update((Boolean[])input);
        }
        else if (input instanceof Number)
        {
            update((Number)input);
        }
        else if (input instanceof byte[])
        {
            update((byte[])input);
        }
        else if (input instanceof Byte[])
        {
            update((Byte[])input);
        }
        else if (input instanceof ByteBuffer)
        {
            update((ByteBuffer)input);
        }
        else if (input instanceof char[])
        {
            update((char[])input);
        }
        else if (input instanceof double[])
        {
            update((double[])input);
        }
        else if (input instanceof Double[])
        {
            update((Double[])input);
        }
        else if (input instanceof float[])
        {
            update((float[])input);
        }
        else if (input instanceof Float[])
        {
            update((Float[])input);
        }
        else if (input instanceof int[])
        {
            update((int[])input);
        }
        else if (input instanceof Integer[])
        {
            update((Integer[])input);
        }
        else if (input instanceof long[])
        {
            update((long[])input);
        }
        else if (input instanceof Long[])
        {
            update((Long[])input);
        }
        else if (input instanceof short[])
        {
            update((short[])input);
        }
        else if (input instanceof Short[])
        {
            update((Short[])input);
        }
        else if (input instanceof Iterable<?>)
        {
            update((Iterable<?>)input);
        }

        return this;
    }


    /**
     * Update the wrapped {@code MessageDigest} object with the
     * given JSON. This method updates the digest based on the
     * content of the given JSON, and in the respect, this method
     * is different from {@link #update(String)}.
     *
     * <p>
     * JSONs with the same content, for example, two JSONs below,
     * generate the same digest.
     * </p>
     *
     * <pre>
     * { "key1":"value1", "key2":"value2" }
     * { "key1" : "value1" , "key2" : "value2" }
     * </pre>
     *
     * <p>
     * If {@link Feature#IGNORE_JSON_OBJECT_ENTRY_WITH_VALUE_NULL}
     * is enabled (it is disabled by default), key-value entries
     * with value 'null' are treated as if they did not exist.
     * Therefore, two JSONs below generate the same digest value.
     * </p>
     *
     * <pre>
     * { "key1":"value1", "key2":null }
     * { "key1":"value1" }
     * </pre>
     *
     * <p>
     * If {@link Feature#SORT_JSON_OBJECT_ENTRY_KEYS} is enabled
     * (it is enabled by default), orders of JSON object keys do
     * not matter. Therefore, two JSONs below generate the same
     * digest value.
     * </p>
     *
     * <pre>
     * { "key1":"value1", "key2":"value2" }
     * { "key2":"value2", "key1":"value1" }
     * </pre>
     *
     * @param json
     *         JSON.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IOException
     *         Failed to parse the given JSON.
     *
     * @since 1.2
     */
    public Digest updateJson(String json) throws IOException
    {
        return new JsonDigestUpdater().update(this, json);
    }


    /**
     * Check if the specified feature is enabled.
     *
     * @param feature
     *         Feature to check.
     *
     * @return
     *         {@code true} if the feature is enabled. Otherwise, {@code false}.
     *
     * @since 1.2
     */
    public boolean isEnabled(Feature feature)
    {
        return mFeatures.get(feature).booleanValue();
    }


    /**
     * Enable or disable the specified feature.
     *
     * @param feature
     *         {@link Feature} to enable or disable.
     *
     * @param enabled
     *         {@code true} to enable the feature.
     *         {@code false} to disable the feature.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.2
     */
    public Digest setEnabled(Feature feature, boolean enabled)
    {
        mFeatures.put(feature, Boolean.valueOf(enabled));

        return this;
    }


    /**
     * Convert the given byte array to a hex string.
     *
     * @param bytes
     *         A byte array to convert.
     *
     * @return
     *         A hex string with 0-9 and a-f.
     */
    public static String bytesToHex(byte[] bytes)
    {
        // http://stackoverflow.com/a/9855338/1174054
        char[] hexChars = new char[bytes.length * 2];
        int v;

        for (int j = 0; j < bytes.length; ++j)
        {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = mHexArray[v >>> 4];
            hexChars[j * 2 + 1] = mHexArray[v & 0x0F];
        }

        return new String(hexChars);
    }
}