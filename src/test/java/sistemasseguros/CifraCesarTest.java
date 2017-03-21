package sistemasseguros;

import org.junit.Test;

import junit.framework.Assert;

public class CifraCesarTest {
	
	@Test
	public void test01UmaPosicaoIncremento() {
		CifraCesar.Posicao(1);
		String s = CifraCesar.Criptografia("meuchapeu");
		Assert.assertEquals(true, s.equals("nfvdibqfv"));
	}
	
	@Test
	public void test02UltimaPosicaoRetornaAPrimeira() {
		CifraCesar.Posicao(1);
		String s = CifraCesar.Criptografia("z");
		Assert.assertEquals(true, s.equals("a"));
	}
	
	@Test
	public void test03Incrementa3Posicoes() {
		CifraCesar.Posicao(3);
		String s = CifraCesar.Criptografia("abc");
		Assert.assertEquals(true, s.equals("def"));
	}
	
	
}
