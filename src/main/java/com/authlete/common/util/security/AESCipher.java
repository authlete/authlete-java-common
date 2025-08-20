package com.authlete.common.util.security;


import static com.authlete.common.util.security.StandardCipherTransformations.AES_CBC_PKCS5PADDING;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.binary.Base64;


/**
 * Cipher using {@code "AES/CBC/PKCS5Padding"}.
 *
 * <pre style="background-color: #EEEEEE; margin: 2em; border: 1px solid black; padding: 0.5em;">
 * <span style="color: darkgreen;">// Create a cipher with a secret key.</span>
 * AESCipher cipher = new {@link #AESCipher()}.{@link #setKey(String, String) setKey}(<span style="color: darkred;">"secret key"</span>, <span style="color: darkred;">"initial vector"</span>);
 *
 * <span style="color: darkgreen;">// Encryption &amp; decryption.
 * // 'plaintext' and 'decrypted' have the same value.</span>
 * String plaintext = <span style="color: darkred;">"plain text"</span>;
 * String encrypted = cipher.{@link #encrypt(String) encrypt(plaintext)};
 * String decrypted = cipher.{@link #decrypt(String) decrypt(encrypted)};
 *
 * <span style="color: darkgreen;">// In the above example, 'encrypted' is encoded by Base64 (default).
 * // If you want to change the format, use {@code setCoder} method.
 * // For example, to change the format to hexadecimal:</span>
 * Hex hex = new org.apache.commons.codec.binary.Hex();
 * cipher.setCoder(hex);
 *
 * <span style="color: darkgreen;">// Binary representation (only "0"s and "1"s) also can be used.</span>
 * BinaryCodec binary = new org.apache.commons.codec.BinaryCodec();
 * cipher.setCoder(binary);
 *
 * <span style="color: darkgreen;">// Coder can be specified as a constructor parameter.</span>
 * cipher = new AESCipher(hex);
 *
 * <span style="color: darkgreen;">// If you want, an encoder and a decoder can be set separately.</span>
 * cipher.{@link #setEncoder(BinaryEncoder) setEncoder(hex)};
 * cipher.{@link #setDecoder(BinaryDecoder) setDecoder(hex)};
 * </pre>
 *
 * <pre style="background-color: #EEEEEE; margin: 2em; border: 1px solid black; padding: 0.5em;">
 * <span style="color: darkgreen;">// Another example which performs encryption without initial vector.</span>
 * String secretkey = <span style="color: darkred;">"secret key"</span>;
 * String plaintext = <span style="color: darkred;">"plain text"</span>;
 *
 * <span style="color: darkgreen;">// Create and set up without initial vector.</span>
 * AESCipher cipher = new AESCipher().setKey(secretkey);
 *
 * <span style="color: darkgreen;">// Encrypt.</span>
 * String encrypted = cipher.encrypt(plaintext);
 *
 * <span style="color: darkgreen;">// Get the auto-generated initial vector.</span>
 * byte[] iv = cipher.getCipher().getIV();
 *
 * <span style="color: darkgreen;">// Decryption requires initial vector.</span>
 * cipher.setKey(secretkey, iv);
 *
 * <span style="color: darkgreen;">// Decrypt.</span>
 * String decrypted = cipher.decrypt(encrypted);
 * </pre>
 *
 * @author Takahiko Kawasaki
 * @since 4.23
 */
public class AESCipher extends CodecCipher
{
    /**
     * The default key size. The value is 16, meaning 128 bits.
     */
    public static final int DEFAULT_KEY_SIZE = 16;


    /**
     * The default transformation. The value is "AES/CBC/PKCS5Padding".
     */
    public static final String DEFAULT_TRANSFORMATION = AES_CBC_PKCS5PADDING;


    /**
     * The initial vector. The size of initial vectors for AES is always 16.
     */
    private static final int INITIAL_VECTOR_SIZE = 16;


    /**
     * Constructor.
     *
     * <p>
     * This constructor just performs {@link CodecCipher#CodecCipher(String)
     * super("AES/CBC/PKCS5Padding")}.
     * </p>
     */
    public AESCipher()
    {
        super(DEFAULT_TRANSFORMATION);
    }


    /**
     * Constructor.
     *
     * <p>
     * This constructor just performs {@link CodecCipher#CodecCipher(String)
     * super(transformation)}.
     * </p>
     */
    public AESCipher(String transformation)
    {
        super(transformation);
    }


    /**
     * Constructor with an encoder and a decoder.
     *
     * <p>
     * This constructor just performs {@link CodecCipher#CodecCipher(String,
     * BinaryEncoder, BinaryDecoder) super("AES/CBC/PKCS5Padding", encoder, decoder)}.
     * </p>
     *
     * @param encoder
     *         An encoder used in {@link #encrypt(String) encrypt(String)} and
     *         {@link #encrypt(byte[]) encrypt(byte[])} to encode an encrypted byte array.
     *         If {@code null} is given, {@link Base64} is used as the default
     *         encoder.
     *
     * @param decoder
     *         A decoder used in {@link #decrypt(String) decrypt(String)} and
     *         {@link #decrypt(byte[]) decrypt(byte[])} to decode an encoded input byte array.
     *         If {@code null} is given, {@link Base64} is used as the default
     *         decoder.
     */
    public AESCipher(BinaryEncoder encoder, BinaryDecoder decoder)
    {
        super(DEFAULT_TRANSFORMATION, encoder, decoder);
    }


    /**
     * Constructor with a transformation, an encoder and a decoder.
     *
     * <p>
     * This constructor just performs {@link CodecCipher#CodecCipher(String,
     * BinaryEncoder, BinaryDecoder) super(transformation, encoder, decoder)}.
     * </p>
     *
     * @param encoder
     *         An encoder used in {@link #encrypt(String) encrypt(String)} and
     *         {@link #encrypt(byte[]) encrypt(byte[])} to encode an encrypted byte array.
     *         If {@code null} is given, {@link Base64} is used as the default
     *         encoder.
     *
     * @param decoder
     *         A decoder used in {@link #decrypt(String) decrypt(String)} and
     *         {@link #decrypt(byte[]) decrypt(byte[])} to decode an encoded input byte array.
     *         If {@code null} is given, {@link Base64} is used as the default
     *         decoder.
     */
    public AESCipher(String transformation, BinaryEncoder encoder, BinaryDecoder decoder)
    {
        super(transformation, encoder, decoder);
    }


    /**
     * Constructor with a coder.
     *
     * <p>
     * This constructor just performs {@code super("AES/CBC/PKCS5Padding", coder)}.
     * </p>
     *
     * @param coder
     *         A coder which works as both an encoder and a decoder.
     *         If {@code null} is given, {@link Base64} is used as the
     *         default coder.
     */
    public <TCoder extends BinaryEncoder & BinaryDecoder> AESCipher(TCoder coder)
    {
        super(DEFAULT_TRANSFORMATION, coder);
    }


    /**
     * Constructor with a transformation and a coder.
     *
     * <p>
     * This constructor just performs {@code super(transformation, coder)}.
     * </p>
     *
     * @param coder
     *         A coder which works as both an encoder and a decoder.
     *         If {@code null} is given, {@link Base64} is used as the
     *         default coder.
     */
    public <TCoder extends BinaryEncoder & BinaryDecoder> AESCipher(String transformation, TCoder coder)
    {
        super(transformation, coder);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * This method is an alias of {@link #setInit(java.security.Key,
     * java.security.spec.AlgorithmParameterSpec) setInit(key, iv)}.
     * </p>
     *
     * @param key
     *         Secret key.
     *
     * @param iv
     *         Initial vector.
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         {@code key} is {@code null}.
     */
    public AESCipher setKey(SecretKey key, IvParameterSpec iv)
    {
        return (AESCipher)setInit(key, iv);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * This method is an alias of {@link #setKey(byte[], byte[], int)
     * setKey}<code>(key, iv, {@link #DEFAULT_KEY_SIZE})</code>.
     * </p>
     *
     * @param key
     *         Secret key.
     *
     * @param iv
     *         Initial vector.
     *
     * @return
     *         {@code this} object.
     */
    public AESCipher setKey(byte[] key, byte[] iv)
    {
        return setKey(key, iv, DEFAULT_KEY_SIZE);
    }


    /**
     * Set cipher initialization parameters. Other {@code setKey}
     * method variants except {@link #setKey(SecretKey, IvParameterSpec)}
     * eventually call this method.
     *
     * <p>
     * This method constructs a {@link SecretKey} instance and an
     * {@link IvParameterSpec} instance from the arguments,
     * and then calls {@link #setKey(SecretKey, IvParameterSpec)}.
     * </p>
     *
     * @param key
     *         Secret key. If {@code null} is given, {@code new byte[16]}
     *         is used. If not {@code null} and the length is less than 16,
     *         a byte array of size 16 is allocated and the content of
     *         {@code key} is copied to the newly allocated byte array,
     *         and the resultant byte array is used.
     *
     * @param iv
     *         Initial vector. If {@code null} is given, {@code null}
     *         is used, meaning that {@code IvParameterSepc} argument
     *         passed to {@link #setKey(SecretKey, IvParameterSpec)} is
     *         {@code null}. In that case, you will want to obtain the
     *         auto-generated initial vector by calling {@link #getCipher()
     *         getCipher()}{@code .}{@link javax.crypto.Cipher#getIV()
     *         getIV()} in order to decrypt the encrypted data.
     *
     *         <p>
     *         If {@code iv} is not {@code null} and the length is less
     *         than 16, a byte array of size 16 is allocated and the content
     *         of {@code iv} is copied to the newly allocated byte array,
     *         and the resultant byte array is used. Even if the length is
     *         greater than 16, only the first 16 bytes are used to construct
     *         an {@code IvParameterSpec} instance.
     *         </p>
     *
     * @param keySize
     *         The size of the secret key in bytes.
     *
     * @return
     *         {@code this} object.
     */
    public AESCipher setKey(byte[] key, byte[] iv, int keySize)
    {
        SecretKey secretKey  = Utils.createSecretKeySpec(key, getAlgorithm(), keySize);
        IvParameterSpec spec = null;

        if (iv != null)
        {
            spec = Utils.createIvParameterSpec(iv, INITIAL_VECTOR_SIZE);
        }

        return setKey(secretKey, spec);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * This method is an alias of {@link #setKey(String, byte[], int)
     * setKey}<code>(key, iv, {@link #DEFAULT_KEY_SIZE})</code>.
     * </p>
     *
     * @param key
     *         Secret key.
     *
     * @param iv
     *         Initial vector.
     *
     * @return
     *         {@code this} object.
     */
    public AESCipher setKey(String key, byte[] iv)
    {
        return setKey(key, iv, DEFAULT_KEY_SIZE);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * This method is an alias of {@link #setKey(byte[], byte[], int)}.
     * </p>
     *
     * @param key
     *         Secret key. The value is converted to a byte array
     *         by {@code key.getBytes("UTF-8")} and used as the
     *         first argument of {@link #setKey(byte[], byte[], int)}.
     *
     * @param iv
     *         Initial vector.
     *
     * @param keySize
     *         The size of the secret key in bytes.
     *
     * @return
     *         {@code this} object.
     */
    public AESCipher setKey(String key, byte[] iv, int keySize)
    {
        byte[] key2 = Utils.getBytesUTF8(key);

        return setKey(key2, iv, keySize);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * This method is an alias of {@link #setKey(String, String, int)
     * setKey}<code>(key, iv, {@link #DEFAULT_KEY_SIZE})</code>.
     * </p>
     *
     * @param key
     *         Secret key.
     *
     * @param iv
     *         Initial vector.
     *
     * @return
     *         {@code this} object.
     */
    public AESCipher setKey(String key, String iv)
    {
        return setKey(key, iv, DEFAULT_KEY_SIZE);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * This method is an alias of {@link #setKey(byte[], byte[], int)}.
     * </p>
     *
     * @param key
     *         Secret key. The value is converted to a byte array
     *         by {@code key.getBytes("UTF-8")} and used as the
     *         first argument of {@link #setKey(byte[], byte[], int)}.
     *
     * @param iv
     *         Initial vector. The value is converted to a byte array
     *         by {@code iv.getBytes("UTF-8")} and used as the
     *         second argument of {@link #setKey(byte[], byte[], int)}.
     *
     * @param keySize
     *         The size of the secret key in bytes.
     *
     * @return
     *         {@code this} object.
     */
    public AESCipher setKey(String key, String iv, int keySize)
    {
        byte[] key2 = Utils.getBytesUTF8(key);
        byte[] iv2  = Utils.getBytesUTF8(iv);

        return setKey(key2, iv2, keySize);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * This method is an alias of {@link #setKey(String, int)
     * setKey}<code>(key, {@link #DEFAULT_KEY_SIZE})</code>
     * </p>
     *
     * @param key
     *         Secret key.
     *
     * @return
     *         {@code this} object.
     */
    public AESCipher setKey(String key)
    {
        return setKey(key, DEFAULT_KEY_SIZE);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * This method is an alias of {@link #setKey(byte[], byte[], int)}.
     * </p>
     *
     * @param key
     *         Secret key. The value is converted to a byte array
     *         by {@code key.getBytes("UTF-8")} and used as the
     *         first argument of {@link #setKey(byte[], byte[], int)}.
     *
     * @param keySize
     *         The size of the secret key in bytes.
     *
     * @return
     *         {@code this} object.
     */
    public AESCipher setKey(String key, int keySize)
    {
        byte[] key2 = Utils.getBytesUTF8(key);

        return setKey(key2, (byte[])null, keySize);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * This method is an alias of {@link #setKey(byte[], String, int)
     * setKey}<code>(key, iv, {@link #DEFAULT_KEY_SIZE})</code>.
     * </p>
     *
     * @param key
     *         Secret key.
     *
     * @param iv
     *         Initial vector.
     *
     * @return
     *         {@code this} object.
     */
    public AESCipher setKey(byte[] key, String iv)
    {
        return setKey(key, iv, DEFAULT_KEY_SIZE);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * This method is an alias of {@link #setKey(byte[], byte[], int)}.
     * </p>
     *
     * @param key
     *         Secret key.
     *
     * @param iv
     *         Initial vector. The value is converted to a byte array
     *         by {@code iv.getBytes("UTF-8")} and used as the
     *         second argument of {@link #setKey(byte[], byte[], int)}.
     *
     * @param keySize
     *         The size of the secret key in bytes.
     *
     * @return
     *         {@code this} object.
     */
    public AESCipher setKey(byte[] key, String iv, int keySize)
    {
        byte[] iv2 = Utils.getBytesUTF8(iv);

        return setKey(key, iv2, keySize);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * This method is an alias of {@link #setKey(byte[], int)
     * setKey}<code>(key, {@link #DEFAULT_KEY_SIZE})</code>.
     * </p>
     *
     * @param key
     *         Secret key.
     *
     * @return
     *         {@code this} object.
     */
    public AESCipher setKey(byte[] key)
    {
        return setKey(key, DEFAULT_KEY_SIZE);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * This method is an alias of {@link #setKey(byte[], byte[], int)
     * setKey}<code>(key, (byte[])null, keySize)</code>.
     * </p>
     *
     * @param key
     *         Secret key.
     *
     * @param keySize
     *         The size of the secret key in bytes.
     *
     * @return
     *         {@code this} object.
     */
    public AESCipher setKey(byte[] key, int keySize)
    {
        return setKey(key, (byte[])null, keySize);
    }
}