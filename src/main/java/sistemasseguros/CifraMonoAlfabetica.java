package sistemasseguros;

import java.util.HashMap;

public class CifraMonoAlfabetica {

	private static HashMap<String, String> alfabeto;
	
	public static void Alfabeto(HashMap<String, String> hashMap) {
		alfabeto = hashMap;
	}

	public static String Criptografar(String mensagem) {
		String resposta = "";
		for (char c : mensagem.toCharArray()) {
			resposta += alfabeto.get(String.valueOf(c));
		}
		return resposta;
	}

}
