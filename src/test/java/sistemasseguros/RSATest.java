package sistemasseguros;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;

import org.junit.Test;

import junit.framework.Assert;

public final class RSATest {

	@Test
	public void testConfidencialidadeRSA() {
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
	
	@Test
	public void testAutenticidade() throws NoSuchPaddingException {
		KeyPairGenerator keyPair;
		try {
			keyPair = KeyPairGenerator.getInstance("RSA");
			KeyPair pair = keyPair.generateKeyPair();

			String msg = "lalalalal";
			
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(pair.getPrivate());
			signature.update(msg.getBytes());
			byte[] signValue = signature.sign();
			
			signature = Signature.getInstance("SHA1withRSA");
			signature.initVerify(pair.getPublic());
			signature.update(msg.getBytes());
			boolean verify = signature.verify(signValue);
			
			Assert.assertEquals(true, verify);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
