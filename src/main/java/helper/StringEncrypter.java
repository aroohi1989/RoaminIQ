package helper;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.util.Base64;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Arrays;


public class StringEncrypter
{
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    public static final String DES_ENCRYPTION_SCHEME = "DES";
    private KeySpec keySpec;
    private SecretKeyFactory keyFactory;
    private Cipher cipher;
    private static final String UNICODE_FORMAT = "UTF8";
    private final IvParameterSpec ivParameters = new IvParameterSpec(new byte[] { 12, 34, 56, 78, 90, 24, 68, 35 });
    private String encryptionScheme;
    private static final String VALIDATE_STRING = "NitinAsrani";
    private String toVerify = null;
    private File f = null;
    private File f1 = null;
    private byte[] digestOfPassword = null;
    private byte[] ciphertext = null;



    public StringEncrypter(String encryptionScheme, String encryptionKey, String env1, String app) throws Exception
    {
        if (encryptionKey == null)
            throw new IllegalArgumentException("encryption key was null");
        if (encryptionKey.trim().length() < 24)
            throw new IllegalArgumentException("encryption key was less than 24 characters");
        boolean err = false;
        try
        {
            if (f==null || !f.exists())
            {
                //encryptionKey = toHex((env1 + new SimpleDateFormat("mmssddMM").format(new Date()) + app + encryptionKey).getBytes());
                encryptionKey = toHex((env1 + app + encryptionKey).getBytes());
                final MessageDigest md = MessageDigest.getInstance("SHA-512");
                digestOfPassword = md.digest(encryptionKey.getBytes(UNICODE_FORMAT));
            }
            final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            if (encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME))
            {
                //3DES encryption
                for (int j = 0, k = 16; j < 8;)
                    keyBytes[k++] = keyBytes[j++];
                keySpec = new DESedeKeySpec(keyBytes);
            }
            else if (encryptionScheme.equals(DES_ENCRYPTION_SCHEME))
                keySpec = new DESKeySpec(keyBytes);
            else
                throw new IllegalArgumentException("Encryption scheme not supported: " + encryptionScheme);
            this.encryptionScheme = encryptionScheme;
            keyFactory = SecretKeyFactory.getInstance(encryptionScheme);
            if (encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME))
                cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            else
                cipher = Cipher.getInstance(encryptionScheme);

        }
        catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException e)//| EncryptionException e)
        {
            err = true;
            throw new EncryptionException(e);
            //throw new Exception("Error in reading encrypted values");
        }
        finally
        {
            if (err)
            {
                if (f != null)
                    f.delete();
                if (f1 != null)
                    f1.delete();
            }
        }
    }

    private void writeOut() throws EncryptionException
    {
        try
        {
            try (FileOutputStream out = new FileOutputStream(f))
            {
                byte[] rawkey = digestOfPassword;
                //Write the raw key to the file
                out.write(rawkey);
                out.close();
            }
            catch (Exception j)
            {
                throw new Exception("Error writing byte encryption");
            }
            try (BufferedWriter bos = new BufferedWriter(new FileWriter(f1)))
            {
                bos.write(Base64.getEncoder().encodeToString(ciphertext));
                bos.close();
            }
            catch (Exception e)
            {
                f.delete();
                throw new Exception("Error writing string encryption");
            }
        }
        catch (Exception g)
        {
            throw new EncryptionException(g);
        }
    }



    public String encrypt(String unencryptedString) throws EncryptionException
    {
        if (unencryptedString == null || unencryptedString.trim().length() == 0)
            throw new IllegalArgumentException("unencrypted string was null or empty");
        try
        {
            SecretKey key = keyFactory.generateSecret(keySpec);
            if (encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME))
                cipher.init(Cipher.ENCRYPT_MODE, key, ivParameters);
            else
                cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cleartext = unencryptedString.getBytes(UNICODE_FORMAT);
            ciphertext = cipher.doFinal(cleartext); //declare private so it can be shared with writeOut()
            return Base64.getEncoder().encodeToString(ciphertext);
        }
        catch (Exception e)
        {
            throw new EncryptionException(e);
        }
    }

    public String decrypt(String encryptedString) throws EncryptionException
    {
        if (encryptedString == null || encryptedString.trim().length() <= 0)
            throw new IllegalArgumentException("encrypted string was null or empty");
        if (f!=null && !f.exists())
            throw new EncryptionException(new Exception("Error decrypting, invalid encryption key"));
        try
        {
            SecretKey key = keyFactory.generateSecret(keySpec);
            if (encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME))
                cipher.init(Cipher.DECRYPT_MODE, key, ivParameters);
            else
                cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] cleartext =Base64.getDecoder().decode(encryptedString);
                    //Base64.parseBase64Binary(encryptedString);
            byte[] ciphertext = cipher.doFinal(cleartext);
            return bytes2String(ciphertext);
        }
        catch (Exception e)
        {
            throw new EncryptionException(e);
        }
    }

    private String bytes2String(byte[] bytes)
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++)
        {
            stringBuffer.append((char) bytes[i]);
        }
        return stringBuffer.toString();
    }



    private String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
        {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        }
        else
        {
            return hex;
        }
    }

    public static class EncryptionException extends Exception
    {
        private static final long serialVersionUID = 1L;
        public EncryptionException(Throwable t)
        {
            super(t);
        }
    }

}
