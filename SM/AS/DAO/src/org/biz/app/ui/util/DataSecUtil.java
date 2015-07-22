package org.biz.app.ui.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;




 public class DataSecUtil {

    private static final char[] PASSWORD = "enfldsgbnlsngdlksdsgm".toCharArray();
    private static final byte[] SALT = {-34, 51, 16, 18, -34, 51, 16, 18};

    public static void main(String[] args)
            throws Exception {
        String originalPassword = "secret";
        System.out.println("Original password: " + originalPassword);
        String encryptedPassword = encrypt(originalPassword);
        System.out.println("Encrypted password: " + encryptedPassword);
        String decryptedPassword = decrypt(encryptedPassword);
        System.out.println("Decrypted password: " + decryptedPassword);
    }

    public static String encrypt(String property) throws GeneralSecurityException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(1, key, new PBEParameterSpec(SALT, 20));
        return base64Encode(pbeCipher.doFinal(property.getBytes()));
    }

    public static String base64Encode(byte[] bytes) {
        Base64.Encoder doc=Base64.getEncoder();
        return doc.encodeToString(bytes);
    }

    public static String decrypt(String property) throws GeneralSecurityException, IOException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");

        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(2, key, new PBEParameterSpec(SALT, 20));
        return new String(pbeCipher.doFinal(base64Decode(property)));
    }

    public static byte[] base64Decode(String property) throws IOException {
        Base64.Decoder doc=Base64.getDecoder();
        return doc.decode(property);
    }
}
/*// Location: D:\intel Biz\jxpower\JxPower.jar
//* Qualified Name: util.ProtectedConfigFile
 * JD-Core Version:0.5.4
 */