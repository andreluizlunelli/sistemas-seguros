package sistemasseguros.protocolo_mensagens;

import org.junit.Test;

public class RepositorioTest {
	
	@Test
	public void teste01() {
		Repositorio repo = Repositorio.getInstance();
		
		Ator pessoa1 = new Ator("andre", "123");
		Ator pessoa2 = new Ator("prof", "123");
		
		repo.addListaContatos(pessoa1);
		repo.addListaContatos(pessoa2);
		
		Conversa conversa = repo.conversar(pessoa1, pessoa2);		
		conversa.mensagem(pessoa2, "Opa e ai blz?");
		
	}
}
