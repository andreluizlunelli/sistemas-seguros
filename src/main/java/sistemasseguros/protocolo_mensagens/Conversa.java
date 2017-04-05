package sistemasseguros.protocolo_mensagens;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class Conversa {

	private Ator pessoa1;
	private Ator pessoa2;
	
	public Conversa(Ator pessoa1, Ator pessoa2) {
		this.pessoa1 = pessoa1;
		this.pessoa2 = pessoa2;
	}

	public void mensagem(Ator para, String mensagem) {
		Ator de = getDe(para);					
		if (! de.temChaveSimetricaCom(para)) {			
			enviarChaveEVetorInicializacaoAES(para);
		}
		System.out.println(String.format("[MENSAGEM] %s: %s", de.getNome(), mensagem));
		para.recerMensagem(AES.encrypt(de.getAESKey(), de.getAESInitVector(), mensagem));		
	}
	

	private void enviarChaveEVetorInicializacaoAES(Ator para) {
		Ator de = getDe(para);
		System.out.println(String.format("[CRIANDO CHAVE AES] %s enviando >>> %s", de.getNome(), para.getNome()));
		Cipher encCipher;
		try {			
			
			// metodo para iniciar a conversa o ator que tem que criar a chave RSA pra iniciar a conversa
			// e um metodo para receber a mensagem (passo de inicialização no ator)
			
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			
			SecretKey key = keyGenerator.generateKey();
			String randomNumInitVector = String.valueOf(ThreadLocalRandom.current().nextInt(0, 10));
			String initVector = sha1(randomNumInitVector).substring(0, 16);			
			String enviar = AES.encodeAesKey(key)+":"+initVector;
	        
			encCipher = Cipher.getInstance("RSA");
			encCipher.init(Cipher.ENCRYPT_MODE, para.getChavePublica());
						
			SealedObject encriptMessage = new SealedObject(enviar, encCipher);
						
			de.setAESInitVector(initVector);
			de.setAESKey(key);
			para.receberAES(de, encriptMessage);
			de.getContatos().put(para.getNome(), para);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private String sha1(String input) {
		StringBuffer sb = new StringBuffer();
		try {			
			byte[] keyBytes;
			keyBytes = MessageDigest.getInstance("SHA1").digest(input.getBytes());
			for (int i = 0; i < keyBytes.length; i++) {
				sb.append(Integer.toString((keyBytes[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return sb.toString();		
	}

	private Ator getDe(Ator para) { 
		return para.getNome() == pessoa1.getNome() ? pessoa2 : pessoa1;
	}
}
