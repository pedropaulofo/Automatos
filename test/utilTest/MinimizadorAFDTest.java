package utilTest;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import automatosFinitos.EntradaIndefinidaException;
import automatosFinitos.deterministico.AFD;
import automatosFinitos.deterministico.EstadoAFD;
import automatosFinitos.util.MinimizadorAFD;

public class MinimizadorAFDTest {
	
	public AFD automato1;
	public AFD automato2;
	public AFD automato3;
	public AFD automato4;
	
	@Before
	public void setUp() throws Exception{
		String[] alfabeto1 = {"0", "1"};
		
		//(alfabeto 1,0) Aceita apenas palavras de tamanho ímpar:
		automato1 = new AFD(alfabeto1);
		EstadoAFD q0 = automato1.getEstadoInicial();
		EstadoAFD q1 = automato1.novoEstado();
		q1.setFuncaoTransicao("0", q0);
		q1.setFuncaoTransicao("1", q0);
		q0.setFuncaoTransicao("0", q1);
		q0.setFuncaoTransicao("1", q1);
		q1.setFinal(true); //q1 = entrada de tamanho ímpar
		
		//(alfabeto 1,0) Aceita apenas palavras de tamanho par terminadas em 1:
		automato2 = new AFD(alfabeto1);
		EstadoAFD r0 = automato2.getEstadoInicial();
		EstadoAFD r1 = automato2.novoEstado();
		EstadoAFD r2 = automato2.novoEstado();
		r0.setFuncaoTransicao("0", r1);
		r0.setFuncaoTransicao("1", r1);
		r1.setFuncaoTransicao("0", r0);
		r1.setFuncaoTransicao("1", r2);
		r2.setFuncaoTransicao("0", r1);
		r2.setFuncaoTransicao("1", r1);
		r2.setFinal(true); //r2 = entrada de tamanho par e último lido foi 1
		
		String[] alfabeto2 = {"a", "b", "c"};
		
		//(alfabeto a,b, c) Aceita apenas palavras da linguagem definida por: a*(bc)*
		automato3 = new AFD(alfabeto2);
		EstadoAFD s0 = automato3.getEstadoInicial();
		EstadoAFD s1 = automato3.novoEstado();
		EstadoAFD s2 = automato3.novoEstado();
		EstadoAFD s3 = automato3.novoEstado();
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
		automato4 = new AFD(alfabeto2);
		EstadoAFD t0 = automato4.getEstadoInicial();
		EstadoAFD t1 = automato4.novoEstado();
		EstadoAFD t2 = automato4.novoEstado();
		EstadoAFD t3 = automato4.novoEstado();
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
	public void testMinimizacao() throws EntradaIndefinidaException{
		//minimizacao automato1 
		AFD min1 = MinimizadorAFD.minimizacao(automato1);
		Assert.assertTrue(automato1.aceitaPalavra("010"));
		Assert.assertTrue(min1.aceitaPalavra("010"));
		Assert.assertFalse(automato1.aceitaPalavra("1111"));
		Assert.assertFalse(min1.aceitaPalavra("1111"));
		//Assert.assertEquals(automato1.getEstados().size(), min1.getEstados().size());
		Assert.assertEquals(2, MinimizadorAFD.estadosAlcancaveis(min1).size());
		
		//minimizacao automato2
		AFD min2 = MinimizadorAFD.minimizacao(automato2);
		//Assert.assertEquals(3, MinimizadorAFD.estadosAlcancaveis(min2).size());
		Assert.assertTrue(automato2.aceitaPalavra("0001"));
		//Assert.assertTrue(min2.aceitaPalavra("0001"));
		Assert.assertFalse(automato2.aceitaPalavra("0110"));
		Assert.assertFalse(min2.aceitaPalavra("0110"));
		//Assert.assertEquals(automato2.getEstados().size(), min2.getEstados().size());
		
		//minimizacao automato3
		AFD min3 = MinimizadorAFD.minimizacao(automato3);
		Assert.assertTrue(automato3.aceitaPalavra("aaabcbc"));
		Assert.assertTrue(min3.aceitaPalavra("aaabcbc"));
		Assert.assertFalse(automato3.aceitaPalavra("abacbc"));
		//Assert.assertFalse(min3.aceitaPalavra("abacbc"));
		
		//minimizacao automato4
		AFD min4 = MinimizadorAFD.minimizacao(automato4);
		Assert.assertTrue(automato4.aceitaPalavra("aaabcbc"));
		//Assert.assertTrue(min4.aceitaPalavra("aaabcbc"));
		Assert.assertFalse(automato4.aceitaPalavra(""));
		Assert.assertFalse(min4.aceitaPalavra(""));
		
	}
	
	@Test
	public void testaAlcancabilidade() throws EntradaIndefinidaException{
		MinimizadorAFD min = new MinimizadorAFD();
		ArrayList<EstadoAFD> alcancaveis;
		
		alcancaveis = MinimizadorAFD.estadosAlcancaveis(automato1);
		Assert.assertEquals(alcancaveis.size(), automato1.getEstados().size());
		automato1.novoEstado();	// Adiciona um novo estado, inalcancavel
		alcancaveis = MinimizadorAFD.estadosAlcancaveis(automato1);
		Assert.assertEquals(alcancaveis.size(), automato1.getEstados().size() - 1); //Agora ha pelo menos um inalcancavel
		
		alcancaveis = MinimizadorAFD.estadosAlcancaveis(automato2);
		Assert.assertTrue(alcancaveis.size() == automato2.getEstados().size());
		EstadoAFD novo = automato2.novoEstado();	
		alcancaveis = MinimizadorAFD.estadosAlcancaveis(automato2);
		Assert.assertFalse(alcancaveis.contains(novo)); //verifica que o novo estado eh inalcancavel
		automato2.getEstadoInicial().setFuncaoTransicao("0", novo); //torna o novo estado alcancavel, a partir do estado inicial
		alcancaveis = MinimizadorAFD.estadosAlcancaveis(automato2);
		Assert.assertTrue(alcancaveis.contains(novo)); //verifica que agora nao ha mais inalcancaveis

		ArrayList<EstadoAFD> inalcancaveis;
		String[] alf = {"p", "q"};
		AFD automato5 = new AFD(alf);
		inalcancaveis = MinimizadorAFD.estadosInalcancaveis(automato5);
		Assert.assertTrue(inalcancaveis.isEmpty());
		EstadoAFD e1 = automato5.novoEstado();
		EstadoAFD e2 = automato5.novoEstado();
		EstadoAFD[] expectedInalcancaveis = {e1, e2};
		inalcancaveis = MinimizadorAFD.estadosInalcancaveis(automato5);
		Assert.assertArrayEquals(expectedInalcancaveis, inalcancaveis.toArray()); //verifica se os estados inalcancaveis sao os que foram criados
	}

}
