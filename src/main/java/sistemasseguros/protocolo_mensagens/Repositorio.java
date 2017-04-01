package sistemasseguros.protocolo_mensagens;

import java.util.HashMap;

public class Repositorio {

	private static Repositorio repo = null;
	private HashMap<String, Ator> contatos = new HashMap<String, Ator>();
	
	private Repositorio() {
		
	}
	
	public static Repositorio getInstance() {
		if (repo == null)
			repo = new Repositorio();
		return repo;
	}
	
	public Conversa conversar(Ator pessoa1, Ator pessoa2) {		
		return new Conversa(pessoa1, pessoa2);
	}

	public void addListaContatos(Ator ator) {
		contatos.put(ator.getNome(), ator);
	}

	public Ator getPessoa(String nome) {
		return contatos.get(nome);
	}

	

}
