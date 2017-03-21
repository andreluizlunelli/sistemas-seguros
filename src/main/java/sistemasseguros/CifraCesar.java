package sistemasseguros;

public class CifraCesar {

	private static int posicao = 0;
	
	private static char[] alfabeto = {
		'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','s','r','t','u','v','w','x','y','z'
	};
	
	public static void Posicao(int i) {
		posicao = i;
	}

	public static String Criptografia(String mensagem) {
		String resposta = "";
		char p;
		int a = 0;
		for (char c : mensagem.toCharArray()) {
			a = GetPosicaoAlfabeto(c);
			if ((a==alfabeto.length-1)) {
				p = alfabeto[0];
			} else {
				p = alfabeto[GetPosicaoAlfabeto(c)+posicao];
			}
			resposta += p;
		}
		return resposta;
	}
	
	private static int GetPosicaoAlfabeto(char letra) {		
		int i = 0;
		for (char c : alfabeto) {
			if (c == letra) {
				return i;
			}
			i++;
		}
		return '0';
	}
}
