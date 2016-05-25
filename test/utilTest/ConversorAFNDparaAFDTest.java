package utilTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import automatosFinitos.EntradaIndefinidaException;
import automatosFinitos.deterministico.AFD;
import automatosFinitos.deterministico.EstadoAFD;
import automatosFinitos.nao_deterministico.AFND;
import automatosFinitos.nao_deterministico.EstadoAFND;
import automatosFinitos.util.ConversorAFNDparaAFD;

public class ConversorAFNDparaAFDTest {
	
	public AFND automato1;
	public AFND automato2;
	public AFND automato3;
	public AFND automato4;
	
	@Before
	public void setUp() throws Exception{
		String[] alfabeto1 = {"0", "1"};
		
		//(alfabeto 1,0) Aceita apenas palavras de tamanho ímpar:
		automato1 = new AFND(alfabeto1);
		EstadoAFND q0 = automato1.getEstadoInicial();
		EstadoAFND q1 = automato1.novoEstado();
		q1.setFuncaoTransicao("0", q0);
		q1.setFuncaoTransicao("1", q0);
		q0.setFuncaoTransicao("0", q1);
		q0.setFuncaoTransicao("1", q1);
		q1.setFinal(true); //q1 = entrada de tamanho ímpar
		
		//(alfabeto 1,0) Aceita apenas palavras de tamanho par terminadas em 1:
		automato2 = new AFND(alfabeto1);
		EstadoAFND r0 = automato2.getEstadoInicial();
		EstadoAFND r1 = automato2.novoEstado();
		EstadoAFND r2 = automato2.novoEstado();
		r0.setFuncaoTransicao("0", r1);
		r0.setFuncaoTransicao("1", r1);
		r1.setFuncaoTransicao("0", r0);
		r1.setFuncaoTransicao("1", r2);
		r2.setFuncaoTransicao("0", r1);
		r2.setFuncaoTransicao("1", r1);
		r2.setFinal(true); //r2 = entrada de tamanho par e último lido foi 1
		
		String[] alfabeto2 = {"a", "b", "c"};
		
		//(alfabeto a,b, c) Aceita apenas palavras da linguagem definida por: a*(bc)*
		automato3 = new AFND(alfabeto2);
		EstadoAFND s0 = automato3.getEstadoInicial();
		EstadoAFND s1 = automato3.novoEstado();
		EstadoAFND s2 = automato3.novoEstado();
		EstadoAFND s3 = automato3.novoEstado();
		s0.setFuncaoTransicao("b", s1);
		s0.setFuncaoTransicao("c", s3);
		s1.setFuncaoTransicao("a", s3);
		s1.setFuncaoTransicao("b", s3);
		s1.setFuncaoTransicao("c", s2);
		s2.setFuncaoTransicao("a", s3);
		s2.setFuncaoTransicao("b", s1);
		s2.setFuncaoTransicao("c", s3);
		s0.setFinal(true); //s0 = entrada vazia
		s2.setFinal(true); //s2 = a*(bc)*
		
		//(alfabeto a,b, c) Aceita cadeias contendo "abc"
		automato4 = new AFND(alfabeto2);
		EstadoAFND t0 = automato4.getEstadoInicial();
		EstadoAFND t1 = automato4.novoEstado();
		EstadoAFND t2 = automato4.novoEstado();
		EstadoAFND t3 = automato4.novoEstado();
		t0.setFuncaoTransicao("a", t1);
		t1.setFuncaoTransicao("c", t0);
		t1.setFuncaoTransicao("b", t2);
		t1.setFuncaoTransicao("c", t0);
		t2.setFuncaoTransicao("a", t0);
		t2.setFuncaoTransicao("b", t0);
		t2.setFuncaoTransicao("c", t3);
		t3.setFinal(true); //t3 = E*abcE*
	}
	
	@Test
	public void testaConversao() throws EntradaIndefinidaException{
		AFD conversao1 = ConversorAFNDparaAFD.converter(automato1);
		Assert.assertEquals(2, conversao1.getEstados().size());
	}

}
