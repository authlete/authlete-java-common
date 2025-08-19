package com.authlete.common.util.security;


import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.spec.AlgorithmParameterSpec;


/**
 * Cipher with encoder and decoder.
 *
 * <pre style="background-color: #EEEEEE; margin-left: 2em; margin-right: 2em; border: 1px solid black; padding: 0.5em;">
 * <span style="color: darkgreen;">// This example shows encryption/decryption using "AES/CBC/PKCS5Padding",
 * // but {@link AESCipher <span style="color: darkgreen;">AESCipher</span>} is much easier to use for "AES/CBC/PKCS5Padding".
 *
 * // Prepare cipher initialization parameters.</span>
 * Key key
 *     = new SecretKeySpec(<span style="color: darkred;">"1234567890123456"</span>.getBytes(<span style="color: darkred;">"UTF-8"</span>), <span style="color: darkred;">"AES"</span>);
 * AlgorithmParameterSpec spec
 *     = new IvParameterSpec(<span style="color: darkred;">"abcdefghijklmnop"</span>.getBytes(<span style="color: darkred;">"UTF-8"</span>));
 *
 * <span style="color: darkgreen;">// Create a cipher using "AES/CBC/PKCS5Padding".</span>
 * String transformation = {@link StandardCipherTransformations}.{@link StandardCipherTransformations#AES_CBC_PKCS5PADDING AES_CBC_PKCS5PADDING};
 * CodecCipher cipher = new {@link #CodecCipher(String) CodecCipher(transformation)};
 *
 * <span style="color: darkgreen;">// Set initialization parameters.</span>
 * cipher.{@link #setInit(Key, AlgorithmParameterSpec) setInit(key, spec)};
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
 * <span style="color: darkgreen;">// Coder can be specified as a parameter of some constructors.</span>
 * cipher = new CodecCipher(<span style="color: darkred;">"AES/CBC/PKCS5Padding"</span>, hex);
 *
 * <span style="color: darkgreen;">// If you want, an encoder and a decoder can be set separately.</span>
 * cipher.{@link #setEncoder(BinaryEncoder) setEncoder(hex)};
 * cipher.{@link #setDecoder(BinaryDecoder) setDecoder(hex)};
 * </pre>
 *
 * @see <a href="http://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html"
 *      >javax.crypto.Cipher</a>
 *
 * @author Takahiko Kawasaki
 */
public class CodecCipher
{
    private static final Base64 DEFAULT_CODER = new Base64();

    private Cipher cipher;
    private BinaryEncoder encoder;
    private BinaryDecoder decoder;
    private Key key;
    private AlgorithmParameters params;
    private AlgorithmParameterSpec spec;
    private Certificate certificate;
    private SecureRandom random;


    /**
     * The default constructor.
     */
    public CodecCipher()
    {
    }


    /**
     * Constructor with a cipher, an encoder and a decoder.
     *
     * @param cipher
     *         A cipher. If {@code null} is given, {@link #setCipher(Cipher)}
     *         must be called later with a valid cipher.
     *
     * @param encoder
     *         An encoder used in {@link #encrypt(String)} and
     *         {@link #encrypt(byte[])} to encode an encrypted byte array.
     *         If {@code null} is given, {@link Base64} is used as the default
     *         encoder.
     *
     * @param decoder
     *         A decoder used in {@link #decrypt(String)} and
     *         {@link #decrypt(byte[])} to decode an encoded input byte array.
     *         If {@code null} is given, {@link Base64} is used as the default
     *         decoder.
     */
    public CodecCipher(Cipher cipher, BinaryEncoder encoder, BinaryDecoder decoder)
    {
        this.cipher  = cipher;
        this.encoder = encoder;
        this.decoder = decoder;
    }


    /**
     * Constructor with a cipher and a coder.
     *
     * <p>
     * This constructor is an alias of {@link #CodecCipher(Cipher, BinaryEncoder, BinaryDecoder)
     * CodecCipher(cipher, coder, coder)}.
     * </p>
     *
     * @param cipher
     *         A cipher. If {@code null} is given, {@link #setCipher(Cipher)}
     *         must be called later with a valid cipher.
     *
     * @param coder
     *         A coder which works as both an encoder and a decoder.
     *         If {@code null} is given, {@link Base64} is used as the
     *         default coder.
     */
    public <TCoder extends BinaryEncoder & BinaryDecoder> CodecCipher(Cipher cipher, TCoder coder)
    {
        this(cipher, coder, coder);
    }


    /**
     * Constructor with a cipher.
     *
     * <p>
     * This constructor is an alias of {@link #CodecCipher(Cipher, BinaryEncoder, BinaryDecoder)
     * CodecCipher(cipher, (BinaryEncoder)null, (BinaryDecoder)null)}.
     * </p>
     *
     * @param cipher
     *         A cipher. If {@code null} is given, {@link #setCipher(Cipher)}
     *         must be called later with a valid cipher.
     */
    public CodecCipher(Cipher cipher)
    {
        this(cipher, (BinaryEncoder)null, (BinaryDecoder)null);
    }


    /**
     * Constructor with a transformation, an encoder and a decoder.
     *
     * @param transformation
     *         A transformation in the form of either <code>"<i>algorithm/mode/padding</i>"</code>
     *         or <code>"<i>algorithm</i>"</code>. For example, <code>"AES/CBC/PKCS5Padding"</code>.
     *
     * @param encoder
     *         An encoder used in {@link #encrypt(String)} and
     *         {@link #encrypt(byte[])} to encode an encrypted byte array.
     *         If {@code null} is given, {@link Base64} is used as the default
     *         encoder.
     *
     * @param decoder
     *         A decoder used in {@link #decrypt(String)} and
     *         {@link #decrypt(byte[])} to decode an encoded input byte array.
     *         If {@code null} is given, {@link Base64} is used as the default
     *         decoder.
     *
     * @throws IllegalArgumentException
     *         The given transformation is not supported. This exception wraps
     *         the original exception (such as {@link java.security.NoSuchAlgorithmException
     *         NoSuchAlgorithmException} and {@link javax.crypto.NoSuchPaddingException
     *         NoSuchPaddingException}) as the cause.
     *
     * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher"
     *      >Cipher (Encryption) Algorithms</a>
     *
     * @see StandardCipherTransformations
     */
    public CodecCipher(String transformation, BinaryEncoder encoder, BinaryDecoder decoder) throws IllegalArgumentException
    {
        this(getCipherInstance(transformation), encoder, decoder);
    }


    /**
     * Constructor with a transformation and a coder.
     *
     * <p>
     * This constructor is an alias of {@link #CodecCipher(String, BinaryEncoder, BinaryDecoder)
     * CodecCipher(transformation, coder, coder)}.
     * </p>
     *
     * @param transformation
     *         A transformation in the form of either <code>"<i>algorithm/mode/padding</i>"</code>
     *         or <code>"<i>algorithm</i>"</code>. For example, <code>"AES/CBC/PKCS5Padding"</code>.
     *
     * @param coder
     *         A coder which works as both an encoder and a decoder.
     *         If {@code null} is given, {@link Base64} is used as the
     *         default coder.
     *
     * @throws IllegalArgumentException
     *         The given transformation is not supported. This exception wraps
     *         the original exception (such as {@link java.security.NoSuchAlgorithmException
     *         NoSuchAlgorithmException} and {@link javax.crypto.NoSuchPaddingException
     *         NoSuchPaddingException}) as the cause.
     *
     * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher"
     *      >Cipher (Encryption) Algorithms</a>
     *
     * @see StandardCipherTransformations
     */
    public <TCoder extends BinaryEncoder & BinaryDecoder> CodecCipher(String transformation, TCoder coder) throws IllegalArgumentException
    {
        this(transformation, coder, coder);
    }


    /**
     * Constructor with a transformation.
     *
     * <p>
     * This constructor is an alias of {@link #CodecCipher(String, BinaryEncoder, BinaryDecoder)
     * CodecCipher(transformation, (BinaryEncoder)null, (BinaryDecoder)null)}.
     * </p>
     *
     * @param transformation
     *         A transformation in the form of either <code>"<i>algorithm/mode/padding</i>"</code>
     *         or <code>"<i>algorithm</i>"</code>. For example, <code>"AES/CBC/PKCS5Padding"</code>.
     *
     * @throws IllegalArgumentException
     *         The given transformation is not supported. This exception wraps
     *         the original exception (such as {@link java.security.NoSuchAlgorithmException
     *         NoSuchAlgorithmException} and {@link javax.crypto.NoSuchPaddingException
     *         NoSuchPaddingException}) as the cause.
     *
     * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher"
     *      >Cipher (Encryption) Algorithms</a>
     *
     * @see StandardCipherTransformations
     */
    public <TCoder extends BinaryEncoder & BinaryDecoder> CodecCipher(String transformation) throws IllegalArgumentException
    {
        this(transformation, (BinaryEncoder)null, (BinaryDecoder)null);
    }


    /**
     * Constructor with a transformation, a provider, an encoder and a decoder.
     *
     * @param transformation
     *         A transformation in the form of either <code>"<i>algorithm/mode/padding</i>"</code>
     *         or <code>"<i>algorithm</i>"</code>. For example, <code>"AES/CBC/PKCS5Padding"</code>.
     *
     * @param provider
     *         A name of provider which provides the implementation of the
     *         transformation.
     *
     * @param encoder
     *         An encoder used in {@link #encrypt(String)} and
     *         {@link #encrypt(byte[])} to encode an encrypted byte array.
     *         If {@code null} is given, {@link Base64} is used as the default
     *         encoder.
     *
     * @param decoder
     *         A decoder used in {@link #decrypt(String)} and
     *         {@link #decrypt(byte[])} to decode an encoded input byte array.
     *         If {@code null} is given, {@link Base64} is used as the default
     *         decoder.
     *
     * @throws IllegalArgumentException
     *         The given transformation is not supported. This exception wraps
     *         the original exception (such as {@link java.security.NoSuchAlgorithmException
     *         NoSuchAlgorithmException}, {@link javax.crypto.NoSuchPaddingException
     *         NoSuchPaddingException} and {@link java.security.NoSuchProviderException
     *         NoSuchProviderException}) as the cause.
     *
     * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher"
     *      >Cipher (Encryption) Algorithms</a>
     *
     * @see StandardCipherTransformations
     */
    public CodecCipher(String transformation, String provider, BinaryEncoder encoder, BinaryDecoder decoder) throws IllegalArgumentException
    {
        this(getCipherInstance(transformation, provider), encoder, decoder);
    }


    /**
     * Constructor with a transformation, a provider and a coder.
     *
     * <p>
     * This constructor is an alias of {@link #CodecCipher(String, String, BinaryEncoder, BinaryDecoder)
     * CodecCipher(transformation, provider, coder, coder)}.
     * </p>
     *
     * @param transformation
     *         A transformation in the form of either <code>"<i>algorithm/mode/padding</i>"</code>
     *         or <code>"<i>algorithm</i>"</code>. For example, <code>"AES/CBC/PKCS5Padding"</code>.
     *
     * @param provider
     *         A name of provider which provides the implementation of the
     *         transformation.
     *
     * @param coder
     *         A coder which works as both an encoder and a decoder.
     *         If {@code null} is given, {@link Base64} is used as the
     *         default coder.
     *
     * @throws IllegalArgumentException
     *         The given transformation is not supported. This exception wraps
     *         the original exception (such as {@link java.security.NoSuchAlgorithmException
     *         NoSuchAlgorithmException}, {@link javax.crypto.NoSuchPaddingException
     *         NoSuchPaddingException} and {@link java.security.NoSuchProviderException
     *         NoSuchProviderException}) as the cause.
     *
     * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher"
     *      >Cipher (Encryption) Algorithms</a>
     *
     * @see StandardCipherTransformations
     */
    public <TCoder extends BinaryEncoder & BinaryDecoder> CodecCipher(String transformation, String provider, TCoder coder) throws IllegalArgumentException
    {
        this(transformation, provider, coder, coder);
    }


    /**
     * Constructor with a transformation and a provider.
     *
     * <p>
     * This constructor is an alias of {@link #CodecCipher(String, String, BinaryEncoder, BinaryDecoder)
     * CodecCipher(transformation, provider, (BinaryEncoder)null, (BinaryDecoder)null)}.
     * </p>
     *
     * @param transformation
     *         A transformation in the form of either <code>"<i>algorithm/mode/padding</i>"</code>
     *         or <code>"<i>algorithm</i>"</code>. For example, <code>"AES/CBC/PKCS5Padding"</code>.
     *
     * @param provider
     *         A name of provider which provides the implementation of the
     *         transformation.
     *
     * @throws IllegalArgumentException
     *         The given transformation is not supported. This exception wraps
     *         the original exception (such as {@link java.security.NoSuchAlgorithmException
     *         NoSuchAlgorithmException}, {@link javax.crypto.NoSuchPaddingException
     *         NoSuchPaddingException} and {@link java.security.NoSuchProviderException
     *         NoSuchProviderException}) as the cause.
     *
     * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher"
     *      >Cipher (Encryption) Algorithms</a>
     *
     * @see StandardCipherTransformations
     */
    public CodecCipher(String transformation, String provider) throws IllegalArgumentException
    {
        this(transformation, provider, (BinaryEncoder)null, (BinaryDecoder)null);
    }


    /**
     * Constructor with a transformation, a provider, an encoder and a decoder.
     *
     * @param transformation
     *         A transformation in the form of either <code>"<i>algorithm/mode/padding</i>"</code>
     *         or <code>"<i>algorithm</i>"</code>. For example, <code>"AES/CBC/PKCS5Padding"</code>.
     *
     * @param provider
     *         A provider which provides the implementation of the transformation.
     *
     * @param encoder
     *         An encoder used in {@link #encrypt(String)} and
     *         {@link #encrypt(byte[])} to encode an encrypted byte array.
     *         If {@code null} is given, {@link Base64} is used as the default
     *         encoder.
     *
     * @param decoder
     *         A decoder used in {@link #decrypt(String)} and
     *         {@link #decrypt(byte[])} to decode an encoded input byte array.
     *         If {@code null} is given, {@link Base64} is used as the default
     *         decoder.
     *
     * @throws IllegalArgumentException
     *         The given transformation is not supported. This exception wraps
     *         the original exception (such as {@link java.security.NoSuchAlgorithmException
     *         NoSuchAlgorithmException} and {@link javax.crypto.NoSuchPaddingException
     *         NoSuchPaddingException}) as the cause.
     *
     * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher"
     *      >Cipher (Encryption) Algorithms</a>
     *
     * @see StandardCipherTransformations
     */
    public CodecCipher(String transformation, Provider provider, BinaryEncoder encoder, BinaryDecoder decoder) throws IllegalArgumentException
    {
        this(getCipherInstance(transformation, provider), encoder, decoder);
    }


    /**
     * Constructor with a transformation, a provider and a coder.
     *
     * <p>
     * This constructor is an alias of {@link #CodecCipher(String, Provider, BinaryEncoder, BinaryDecoder)
     * CodecCipher(transformation, provider, coder, coder)}.
     * </p>
     *
     * @param transformation
     *         A transformation in the form of either <code>"<i>algorithm/mode/padding</i>"</code>
     *         or <code>"<i>algorithm</i>"</code>. For example, <code>"AES/CBC/PKCS5Padding"</code>.
     *
     * @param provider
     *         A provider which provides the implementation of the transformation.
     *
     * @param coder
     *         A coder which works as both an encoder and a decoder.
     *         If {@code null} is given, {@link Base64} is used as the
     *         default coder.
     *
     * @throws IllegalArgumentException
     *         The given transformation is not supported. This exception wraps
     *         the original exception (such as {@link java.security.NoSuchAlgorithmException
     *         NoSuchAlgorithmException} and {@link javax.crypto.NoSuchPaddingException
     *         NoSuchPaddingException}) as the cause.
     *
     * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher"
     *      >Cipher (Encryption) Algorithms</a>
     *
     * @see StandardCipherTransformations
     */
    public <TCoder extends BinaryEncoder & BinaryDecoder> CodecCipher(String transformation, Provider provider, TCoder coder) throws IllegalArgumentException
    {
        this(transformation, provider, coder, coder);
    }


    /**
     * Constructor with a transformation and a provider.
     *
     * <p>
     * This constructor is an alias of {@link #CodecCipher(String, Provider, BinaryEncoder, BinaryDecoder)
     * CodecCipher(transformation, provider, (BinaryEncoder)null, (BinaryDecoder)null)}.
     * </p>
     *
     * @param transformation
     *         A transformation in the form of either <code>"<i>algorithm/mode/padding</i>"</code>
     *         or <code>"<i>algorithm</i>"</code>. For example, <code>"AES/CBC/PKCS5Padding"</code>.
     *
     * @param provider
     *         A provider which provides the implementation of the transformation.
     *
     * @throws IllegalArgumentException
     *         The given transformation is not supported. This exception wraps
     *         the original exception (such as {@link java.security.NoSuchAlgorithmException
     *         NoSuchAlgorithmException} and {@link javax.crypto.NoSuchPaddingException
     *         NoSuchPaddingException}) as the cause.
     *
     * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher"
     *      >Cipher (Encryption) Algorithms</a>
     *
     * @see StandardCipherTransformations
     */
    public CodecCipher(String transformation, Provider provider) throws IllegalArgumentException
    {
        this(transformation, provider, (BinaryEncoder)null, (BinaryDecoder)null);
    }


    /**
     * Create a {@link Cipher} instance by {@link Cipher#getInstance(String)
     * Cipher.getInstance(transformation)}.
     */
    private static Cipher getCipherInstance(String transformation) throws IllegalArgumentException
    {
        try
        {
            return Cipher.getInstance(transformation);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * Create a {@link Cipher} instance by {@link Cipher#getInstance(String, String)
     * Cipher.getInstance(transformation, provider)}.
     */
    private static Cipher getCipherInstance(String transformation, String provider)
    {
        try
        {
            return Cipher.getInstance(transformation, provider);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * Create a {@link Cipher} instance by {@link Cipher#getInstance(String, Provider)
     * Cipher.getInstance(transformation, provider)}.
     */
    private static Cipher getCipherInstance(String transformation, Provider provider)
    {
        try
        {
            return Cipher.getInstance(transformation, provider);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * Get the cipher which this instance internally holds.
     *
     * @return
     *         The {@link Cipher} instance.
     */
    public Cipher getCipher()
    {
        return cipher;
    }


    /**
     * Set a cipher instance.
     *
     * @param cipher
     *         A cipher instance.
     *
     * @return
     *         {@code this} object.
     */
    public CodecCipher setCipher(Cipher cipher)
    {
        if (cipher == null)
        {
            throw new IllegalArgumentException("cipher is null.");
        }

        this.cipher = cipher;

        return this;
    }


    /**
     * Get the cipher algorithm name.
     *
     * <p>
     * If the internal cipher is {@code null}, {@code null} is returned.
     * Otherwise, the algorithm part of the cipher's transformation is
     * returned.
     * </p>
     *
     * @return
     *         The cipher algorithm name.
     */
    public String getAlgorithm()
    {
        if (cipher == null)
        {
            return null;
        }

        String transformation = cipher.getAlgorithm();

        if (transformation == null)
        {
            return null;
        }

        // Separator position.
        int pos = transformation.indexOf('/');

        if (pos < 0)
        {
            return transformation;
        }

        return transformation.substring(0, pos);
    }


    /**
     * Get the encoder which this instance internally holds.
     *
     * @return
     *         The internal encoder. This may be {@code null}, and
     *         in such a case, {@link #encrypt(String)} and
     *         {@link #encrypt(byte[])} use {@link Base64}.
     */
    public BinaryEncoder getEncoder()
    {
        return encoder;
    }


    /**
     * Set an encoder.
     *
     * @param encoder
     *         An encoder used by {@link #encrypt(String)} and
     *         {@link #encrypt(byte[])}.
     *
     * @return
     *         {@code this} object.
     */
    public CodecCipher setEncoder(BinaryEncoder encoder)
    {
        this.encoder = encoder;

        return this;
    }


    /**
     * Get the decoder which this instance internally holds.
     *
     * @return
     *         The internal decoder. This may be {@code null}, and
     *         in such a case, {@link #decrypt(String)} and
     *         {@link #decrypt(byte[])} use {@link Base64}.
     */
    public BinaryDecoder getDecoder()
    {
        return decoder;
    }


    /**
     * Set a decoder.
     *
     * @param decoder
     *         A decoder used by {@link #decrypt(String)} and
     *         {@link #decrypt(byte[])}.
     *
     * @return
     *         {@code this} object.
     */
    public CodecCipher setDecoder(BinaryDecoder decoder)
    {
        this.decoder = decoder;

        return this;
    }


    /**
     * Set a coder.
     *
     * @param coder
     *         A coder which works as both an encoder and a decoder.
     *         If {@code null} is given, {@link Base64} is used as the
     *         default coder.
     *
     * @return
     *         {@code this} object.
     */
    public <TCoder extends BinaryEncoder & BinaryDecoder> CodecCipher setCoder(TCoder coder)
    {
        this.encoder = coder;
        this.decoder = coder;

        return this;
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * If this method is used to set initialization parameters,
     * {@link Cipher#init(int, Key) Cipher.init(mode, (Key)key)}
     * is called later from within {@code encrypt}/{@code decrypt} methods.
     * </p>
     *
     * @param key
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         {@code key} is {@code null}.
     */
    public CodecCipher setInit(Key key) throws IllegalArgumentException
    {
        return setInit(key, null, null, null);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * If this method is used to set initialization parameters,
     * {@link Cipher#init(int, Key, SecureRandom)
     * Cipher.init(mode, (Key)key, (SecureRandom)random)}
     * is called later from within {@code encrypt}/{@code decrypt} methods.
     * </p>
     *
     * @param key
     *
     * @param random
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         {@code key} is {@code null}.
     */
    public CodecCipher setInit(Key key, SecureRandom random) throws IllegalArgumentException
    {
        return setInit(key, null, null, random);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * If this method is used to set initialization parameters,
     * {@link Cipher#init(int, Key, AlgorithmParameters)
     * Cipher.init(mode, (Key)key, (AlgorithmParameters)params)}
     * is called later from within {@code encrypt}/{@code decrypt} methods.
     * </p>
     *
     * @param key
     *
     * @param params
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         {@code key} is {@code null}.
     */
    public CodecCipher setInit(Key key, AlgorithmParameters params) throws IllegalArgumentException
    {
        return setInit(key, params, null, null);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * If this method is used to set initialization parameters,
     * {@link Cipher#init(int, Key, AlgorithmParameters, SecureRandom)
     * Cipher.init(mode, (Key)key, (AlgorithmParameters)params, (SecureRandom)random)}
     * is called later from within {@code encrypt}/{@code decrypt} methods.
     * </p>
     *
     * @param key
     *
     * @param params
     *
     * @param random
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         {@code key} is {@code null}.
     */
    public CodecCipher setInit(Key key, AlgorithmParameters params, SecureRandom random) throws IllegalArgumentException
    {
        return setInit(key, params, null, random);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * If this method is used to set initialization parameters,
     * {@link Cipher#init(int, Key, AlgorithmParameterSpec)
     * Cipher.init(mode, (Key)key, (AlgorithmParameterSpec)spec)}
     * is called later from within {@code encrypt}/{@code decrypt} methods.
     * </p>
     *
     * @param key
     *
     * @param spec
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         {@code key} is {@code null}.
     */
    public CodecCipher setInit(Key key, AlgorithmParameterSpec spec) throws IllegalArgumentException
    {
        return setInit(key, null, spec, null);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * If this method is used to set initialization parameters,
     * {@link Cipher#init(int, Key, AlgorithmParameterSpec, SecureRandom)
     * Cipher.init(mode, (Key)key, (AlgorithmParameterSpec)spec, (SecureRandom)random)}
     * is called later from within {@code encrypt}/{@code decrypt} methods.
     * </p>
     *
     * @param key
     *
     * @param spec
     *
     * @param random
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         {@code key} is {@code null}.
     */
    public CodecCipher setInit(Key key, AlgorithmParameterSpec spec, SecureRandom random) throws IllegalArgumentException
    {
        return setInit(key, null, spec, random);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * If this method is used to set initialization parameters,
     * {@link Cipher#init(int, Certificate)
     * Cipher.init(mode, (Certificate)certificate)}
     * is called later from within {@code encrypt}/{@code decrypt} methods.
     * </p>
     *
     * @param certificate
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         {@code certificate} is {@code null}.
     */
    public CodecCipher setInit(Certificate certificate) throws IllegalArgumentException
    {
        return setInit(certificate, null);
    }


    /**
     * Set cipher initialization parameters.
     *
     * <p>
     * If this method is used to set initialization parameters,
     * {@link Cipher#init(int, Certificate, SecureRandom)
     * Cipher.init(mode, (Certificate)certificate, (SecureRandom)random)}
     * is called later from within {@code encrypt}/{@code decrypt} methods.
     * </p>
     *
     * @param certificate
     *
     * @param random
     *
     * @return
     *         {@code this} object.
     *
     * @throws IllegalArgumentException
     *         {@code certificate} is {@code null}.
     */
    public CodecCipher setInit(Certificate certificate, SecureRandom random) throws IllegalArgumentException
    {
        if (certificate == null)
        {
            throw new IllegalArgumentException("certificate is null.");
        }

        return setInit(null, null, null, certificate, random);
    }


    /**
     * Set cipher initialization parameters.
     */
    private CodecCipher setInit(Key key, AlgorithmParameters params, AlgorithmParameterSpec spec, SecureRandom random)
    {
        if (key == null)
        {
            throw new IllegalArgumentException("key is null.");
        }

        return setInit(key, params, spec, null, random);
    }


    /**
     * Set cipher initialization parameters.
     */
    private CodecCipher setInit(Key key, AlgorithmParameters params, AlgorithmParameterSpec spec, Certificate certificate, SecureRandom random)
    {
        this.key         = key;
        this.params      = params;
        this.spec        = spec;
        this.certificate = certificate;
        this.random      = random;

        return this;
    }


    /**
     * Encrypt the given string.
     *
     * @param input
     *         Plain text before encryption.
     *
     * @return
     *         Encrypted string encoded by the encoder.
     *         If {@code input} is {@code null}, {@code null} is returned.
     *
     * @throws IllegalStateException
     *         Failed to encrypt the input. The {@code IllegalStateException}
     *         wraps the original exception (such as {@link InvalidKeyException}
     *         and {@link EncoderException}) as the cause, if any.
     *         <ul>
     *         <li>A cipher instance has not been set.
     *         <li>{@code setInit} method has not been called.
     *         <li>The cipher failed to encrypt the input (probably initialization
     *             parameters set by {@code setInit} method are wrong).
     *         <li>The encoder failed to encode the encrypted byte array.
     *         </ul>
     */
    public String encrypt(String input) throws IllegalStateException
    {
        return cipher(input, Cipher.ENCRYPT_MODE);
    }


    /**
     * Decrypt the given string.
     *
     * @param input
     *         Encrypted string encoded by an encoder.
     *
     * @return
     *         Decrypted plain text.
     *         If {@code input} is {@code null}, {@code null} is returned.
     *
     * @throws IllegalStateException
     *         Failed to decrypt the input. The {@code IllegalStateException}
     *         wraps the original exception (such as {@link InvalidKeyException}
     *         and {@link DecoderException}) as the cause, if any.
     *         <ul>
     *         <li>A cipher instance has not been set.
     *         <li>{@code setInit} method has not been called.
     *         <li>The decoder failed to decode the input.
     *         <li>The cipher failed to decrypt the byte array (probably initialization
     *             parameters set by {@code setInit} method are wrong).
     *         </ul>
     */
    public String decrypt(String input) throws IllegalStateException
    {
        return cipher(input, Cipher.DECRYPT_MODE);
    }


    /**
     * Encrypt the given byte array.
     *
     * @param input
     *         Plain byte array before encryption.
     *
     * @return
     *         Encrypted byte array encoded by the encoder.
     *         If {@code input} is {@code null}, {@code null} is returned.
     *
     * @throws IllegalStateException
     *         Failed to encrypt the input. The {@code IllegalStateException}
     *         wraps the original exception (such as {@link InvalidKeyException}
     *         and {@link EncoderException}) as the cause, if any.
     *         <ul>
     *         <li>A cipher instance has not been set.
     *         <li>{@code setInit} method has not been called.
     *         <li>The cipher failed to encrypt the input (probably initialization
     *             parameters set by {@code setInit} method are wrong).
     *         <li>The encoder failed to encode the encrypted byte array.
     *         </ul>
     */
    public byte[] encrypt(byte[] input) throws IllegalStateException
    {
        return cipher(input, Cipher.ENCRYPT_MODE);
    }


    /**
     * Decrypt the given byte array.
     *
     * @param input
     *         Encrypted byte array encoded by an encoder.
     *
     * @return
     *         Decrypted plain byte array.
     *         If {@code input} is {@code null}, {@code null} is returned.
     *
     * @throws IllegalStateException
     *         Failed to decrypt the input. The {@code IllegalStateException}
     *         wraps the original exception (such as {@link InvalidKeyException}
     *         and {@link DecoderException}) as the cause, if any.
     *         <ul>
     *         <li>A cipher instance has not been set.
     *         <li>{@code setInit} method has not been called.
     *         <li>The decoder failed to decode the input.
     *         <li>The cipher failed to decrypt the byte array (probably initialization
     *             parameters set by {@code setInit} method are wrong).
     *         </ul>
     */
    public byte[] decrypt(byte[] input) throws IllegalStateException
    {
        return cipher(input, Cipher.DECRYPT_MODE);
    }


    /**
     * Encrypt or decrypt.
     */
    private String cipher(String input, int mode)
    {
        if (input == null)
        {
            return null;
        }

        // Convert the input string into a byte array.
        byte[] inputBytes = Utils.getBytesUTF8(input);

        // Encrypt or decrypt.
        byte[] outputBytes = cipher(inputBytes, mode);

        // Build a string from the byte array.
        return Utils.toStringUTF8(outputBytes);
    }


    /**
     * Encrypt or decrypt.
     */
    private byte[] cipher(byte[] input, int mode)
    {
        try
        {
            return doCipher(input, mode);
        }
        catch (Exception e)
        {
            throw new IllegalStateException(e);
        }
    }


    /**
     * Encrypt or decrypt.
     */
    private byte[] doCipher(byte[] input, int mode) throws
            DecoderException, InvalidKeyException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException, EncoderException
    {
        if (input == null)
        {
            return null;
        }

        if (cipher == null)
        {
            throw new IllegalStateException("setCipher() has not been called.");
        }

        if (key == null && certificate == null)
        {
            throw new IllegalStateException("setInit() has not been called.");
        }

        if (mode == Cipher.DECRYPT_MODE)
        {
            input = decode(input);
        }

        initCipher(mode);

        byte[] output = cipher.doFinal(input);

        if (mode == Cipher.ENCRYPT_MODE)
        {
            output = encode(output);
        }

        return output;
    }


    /**
     * Initialize {@link cipher} by calling one of {@code Cipher.init} methods
     * using {@code mode} and one or some of {@link #key}, {@link #params},
     * {@link #spec}, {@link #certificate} and {@link #random}.
     */
    private void initCipher(int mode) throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        if (key != null)
        {
            if (params != null)
            {
                if (random != null)
                {
                    cipher.init(mode, key, params, random);
                }
                else
                {
                    cipher.init(mode, key, params);
                }
            }
            else if (spec != null)
            {
                if (random != null)
                {
                    cipher.init(mode, key, spec, random);
                }
                else
                {
                    cipher.init(mode, key, spec);
                }
            }
            else
            {
                if (random != null)
                {
                    cipher.init(mode, key, random);
                }
                else
                {
                    cipher.init(mode, key);
                }
            }
        }
        else
        {
            if (random != null)
            {
                cipher.init(mode, certificate, random);
            }
            else
            {
                cipher.init(mode, certificate);
            }
        }
    }


    /**
     * Encode the byte array using {@link #encoder}, or using
     * {@link #DEFAULT_CODER} if {@code encoder} is {@code null}.
     */
    private byte[] encode(byte[] input) throws EncoderException
    {
        if (encoder != null)
        {
            return encoder.encode(input);
        }
        else
        {
            return DEFAULT_CODER.encode(input);
        }
    }


    /**
     * Decode the byte array using {@link #decoder}, or using
     * {@link #DEFAULT_CODER} if {@code decoder} is {@code null}.
     */
    private byte[] decode(byte[] input) throws DecoderException
    {
        if (decoder != null)
        {
            return decoder.decode(input);
        }
        else
        {
            return DEFAULT_CODER.decode(input);
        }
    }
}