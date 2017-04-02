package sistemasseguros.protocolo_mensagens;

import org.junit.Test;

public class RepositorioTest {
	
	@Test
	public void teste01() {
		Repositorio repo = Repositorio.getInstance();
		
		Ator pessoa1 = new Ator("andre", "123");
		Ator pessoa2 = new Ator("prof", "123");
		Ator wiu = new Ator("wiu", "123");
		
		repo.addListaContatos(pessoa1);
		repo.addListaContatos(pessoa2);
		
		// alguem tem que ser o agente ativo para iniciar a conversa
		Conversa conversa = repo.conversar(pessoa1, pessoa2);		
		conversa.mensagem(pessoa2, "Opa e ai blz?");
		conversa.mensagem(pessoa1, "tudo certinho, e o trabalho já terminou?");
		conversa.mensagem(pessoa2, "rs.. ta quase");
				
	}
}
