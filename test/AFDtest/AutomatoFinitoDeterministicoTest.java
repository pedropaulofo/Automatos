package AFDtest;

import automatosFinitos.deterministico.AutomatoFinitoDeterministico;
import automatosFinitos.deterministico.EstadoAFD;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AutomatoFinitoDeterministicoTest {
	
	public AutomatoFinitoDeterministico automato1;
	public AutomatoFinitoDeterministico automato2;
	
	@Before
	public void setUp(){
		String[] alfabeto1 = {"0", "1"};
		//(alfabeto 1,0) Aceita apenas palavras de tamanho ímpar:
		automato1 = new AutomatoFinitoDeterministico(alfabeto1);
		EstadoAFD q0 = automato1.getEstadoInicial();
		EstadoAFD q1 = automato1.addNovoEstado();
		q1.setFuncaoTransicao("0", q0);
		q1.setFuncaoTransicao("1", q0);
		q0.setFuncaoTransicao("0", q1);
		q0.setFuncaoTransicao("1", q1);
		q1.setFinal(true); //q1 = entrada de tamanho ímpar
		
		//(alfabeto 1,0) Aceita apenas palavras de tamanho par terminadas em 1:
		automato2 = new AutomatoFinitoDeterministico(alfabeto1);
		EstadoAFD r0 = automato2.getEstadoInicial();
		EstadoAFD r1 = automato2.addNovoEstado();
		EstadoAFD r2 = automato2.addNovoEstado();
		r0.setFuncaoTransicao("0", r1);
		r0.setFuncaoTransicao("1", r1);
		r1.setFuncaoTransicao("0", r0);
		r1.setFuncaoTransicao("1", r2);
		r2.setFuncaoTransicao("0", r1);
		r2.setFuncaoTransicao("1", r1);
		r2.setFinal(true); //q1 = entrada de tamanho par e último lido foi 1
	}
	
	@Test
	public void testAceitaPalavra(){
		Assert.assertTrue(automato1.aceitaPalavra("010"));
		Assert.assertTrue(automato1.aceitaPalavra("1"));
		Assert.assertTrue(automato1.aceitaPalavra("00000"));
		Assert.assertFalse(automato1.aceitaPalavra("1111"));
		Assert.assertFalse(automato1.aceitaPalavra(""));
		Assert.assertFalse(automato1.aceitaPalavra("011001"));
		
		Assert.assertTrue(automato2.aceitaPalavra("0001"));
		Assert.assertTrue(automato2.aceitaPalavra("010011"));
		Assert.assertTrue(automato2.aceitaPalavra("11"));
		Assert.assertFalse(automato2.aceitaPalavra(""));
		Assert.assertFalse(automato2.aceitaPalavra("111"));
		Assert.assertFalse(automato2.aceitaPalavra("0110"));
	}
	
}
