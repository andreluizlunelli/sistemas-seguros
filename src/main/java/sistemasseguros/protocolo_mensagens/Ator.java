package sistemasseguros.protocolo_mensagens;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class Ator {

	private String nome;
	private String senha;
	private KeyPair parChaves;
	private PrivateKey chavePrivada;
	private PublicKey chavePublica;
	private String AESInitVector; // adicionar um hash map ccom o vetor de inicialização e a key para cada contato, 2) E persistir essas chaves em alguma midia ex: meuarquivo.pk 
	private SecretKey AESKey;
	private HashMap<String, Ator> contatos = new HashMap<String, Ator>();
	private String ultimaMensagemRecebidaDescriptografada = "";
	private String ultimaMensagemRecebidaCriptografada = "";
	
	public Ator(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
		// tenho que fazer um if, se já existe, ai eu carrego, se não gero
		// ai quando eu gerar um chave nova, ai eu adiciono no repositório
		gerarChavesCriptografiaAssimetrica();
	}

	public void gerarChavesCriptografiaAssimetrica() {
		try {
			
			parChaves = KeyPairGenerator.getInstance("RSA").generateKeyPair();			
			chavePrivada = parChaves.getPrivate();
			chavePublica = parChaves.getPublic();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean temChaveSimetricaCom(Ator para) {
		return getContatos().get(para.getNome()) != null;		
	}

	public PublicKey getChavePublica() {
		return chavePublica;
	}

	public void receberAES(Ator de, SealedObject encriptMessage) {		
		getContatos().put(de.getNome(), de);
		Cipher decCipher;
		try {
			decCipher = Cipher.getInstance("RSA");
			decCipher.init(Cipher.DECRYPT_MODE, this.chavePrivada);
			String mensagemFinal = (String) encriptMessage.getObject(decCipher);
			String[] pair = mensagemFinal.split(":");
			SecretKey key = AES.decodeAesKey(pair[0]);
			String initVector = pair[1];
			setAESInitVector(initVector);
			setAESKey(key);						
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getAESInitVector() {
		return AESInitVector;
	}

	public void setAESInitVector(String aESInitVector) {
		AESInitVector = aESInitVector;
	}

	public SecretKey getAESKey() {
		return AESKey;
	}

	public void setAESKey(SecretKey key) {
		AESKey = key;
	}

	public void recerMensagem(String encrypt) {
		ultimaMensagemRecebidaCriptografada = encrypt;
		ultimaMensagemRecebidaDescriptografada = AES.decrypt(getAESKey(), getAESInitVector(), encrypt);
		System.out.println(String.format("[MENSAGEM RECEBIDA] %s", this.getNome()));
	}			

	public HashMap<String, Ator> getContatos() {
		return contatos;
	}

	public void setContatos(HashMap<String, Ator> contatos) {
		this.contatos = contatos;
	}

}
