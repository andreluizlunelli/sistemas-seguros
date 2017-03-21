package sistemasseguros;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;

import org.junit.Test;

public final class RSATest {

	@Test
	public void test01() {
		try {
			KeyPairGenerator keyPair = KeyPairGenerator.getInstance("RSA");
			KeyPair pair = keyPair.generateKeyPair();
			
			Cipher encCipher = Cipher.getInstance("RSA");
			encCipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
			
			String msg = "minha mensagem secreta";			
			SealedObject encriptMessage = new SealedObject(msg, encCipher);
			
			Cipher decCipher = Cipher.getInstance("RSA");
			decCipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
			
			String mensagemFinal = (String) encriptMessage.getObject(decCipher);
			
			System.out.println("Mensagem enviada: '"+mensagemFinal+"'");
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
