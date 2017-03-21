package sistemasseguros;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;

import junit.framework.Assert;

public class CifraMonoAlfabeticaTest {
	
	@Test
	public void test01() {
		CifraMonoAlfabetica.Alfabeto(new HashMap<String, String>(){{
			put("a","c");
			put("b","a");
			put("c","z");
			put("d","f");
			put("e","h");
			put("f","w");
			put("g","b");
			put("h","i");
			put("i","q");
			put("j","r");
			put("k","v");
			put("l","s");
			put("m","u");
			put("n","x");
			put("o","p");
			put("p","y");
			put("q","d");
			put("r","e");
			put("s","g");
			put("t","j");
			put("u","k");
			put("v","l");
			put("w","m");
			put("x","n");
			put("y","o");
			put("z","t");
		}});
		String s = CifraMonoAlfabetica.Criptografar("abcdefghijklmnopqrstuvwxyz");
		Assert.assertEquals(true, s.equals("cazfhwbiqrvsuxpydegjklmnot"));
	}
}
