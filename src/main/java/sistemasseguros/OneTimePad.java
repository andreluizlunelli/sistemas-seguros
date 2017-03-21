package sistemasseguros;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OneTimePad {
	
	private static String PadronizaEntrada(String entrada) {
		//Retirar tudo que não for Caracter Alfabético
		//Chamar maiúsculo
		Matcher m = Pattern.compile("[A-Za-z0-9]").matcher(entrada);
		StringBuilder sb = new StringBuilder();
		while (m.find()) {
			sb.append(m.group().toUpperCase());
		}
		return sb.toString();
		
		// to.uppercase.replaceall([^A-z]) ou \n ? testar..
	}
	
	private static String criptografaOTP(String textoPlano, String chaveUsoUnico) {
		
		if (textoPlano.length() != chaveUsoUnico.length()){
			throw new IllegalArgumentException("Texto e cifra devem ter o mesmo tamanho");
		}
		int letraOriginal, letraChave, letraCifrada;
		for (int i = 0; i < textoPlano.length(); i++) {
			//Calcular letra nova, somando a letra do Texto Plano com a letra da Chave
			letraOriginal = (int) mensagemAjustada.charAt(i) - 65;
			letraChave = 
		}
		return "texto cifrado";
	}
	
	public static String descriptografaOTP(String textoCifrado, String chaveUsoUnico) {
		
		if (textoCifrado.length() != chaveUsoUnico.length()){
			throw new IllegalArgumentException("Texto e cifra devem ter o mesmo tamanho");
		}
		
		StringBuilder textoPlano = new StringBuilder(textoCifrado);
		
		for (int i = 0; i < textoCifrado.length(); i++) {
			//Fazer o cálculo de volta
		}
		return "texto plano";
	}
	
	public static void main (String args[]) throws IOException{
		
		String entrada = "C112 asA";
		String plano = PadronizaEntrada(entrada);
		String chave = "AABA";
		String cifrado = criptografaOTP(plano, chave);
		
		System.out.println(cifrado);
		String voltaParaPlano = descriptografaOTP(cifrado, chave);
		System.out.println(voltaParaPlano);
		
		System.in.read();
	}

}
