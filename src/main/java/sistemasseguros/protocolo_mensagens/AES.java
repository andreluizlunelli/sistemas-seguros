package sistemasseguros.protocolo_mensagens;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import javax.xml.bind.DatatypeConverter; 

public class AES {
    public static String encrypt(SecretKey secretKey, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return DatatypeConverter.printBase64Binary(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(SecretKey key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] original = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public static String encodeAesKey(SecretKey key) {    	
    	return Base64.getEncoder().encodeToString(key.getEncoded());
    }
    
    public static SecretKey decodeAesKey(String key) {
    	byte[] decodedKey = Base64.getDecoder().decode(key);
    	return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public static void main(String[] args) {
        String initVector = "RandomInitVector"; // 16 bytes IV
		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			
			SecretKey key = keyGenerator.generateKey();
			System.out.println("output final: "+
					decrypt(key, initVector,
							encrypt(key, initVector, "Hello World")));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

    }
}
