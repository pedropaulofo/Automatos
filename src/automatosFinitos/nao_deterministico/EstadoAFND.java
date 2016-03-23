package automatosFinitos.nao_deterministico;

import java.util.ArrayList;
import java.util.List;

import automatosFinitos.AbstractEstado;


public class EstadoAFND extends AbstractEstado{
	
	private List<EstadoAFND> transicoesVazias = new ArrayList<EstadoAFND>();
	private ArrayList<EstadoAFND> transicoesNaEntrada[] = new ArrayList[255];
	
	public EstadoAFND(){
	}

	public void setFuncaoTransicao(char entrada, EstadoAFND estadoResutante) {			
		transicoesNaEntrada[(int)entrada].add(estadoResutante) ;
	}
	
	public void setTransicaoEpsilon(EstadoAFND estadoResultante) {
		transicoesVazias.add(estadoResultante) ;
	    }
	
	public void removeFuncaoTransicao(String entrada, AbstractEstado estadoResutante){
		
	}
	
	public boolean aceita(String s) {
		return aceita(s,new ArrayList<EstadoAFND>()) ;
	}
	
	private boolean aceita(String palavra, ArrayList<EstadoAFND> visitados){

		if (visitados.contains(this)) 
		    /* Neste caso chegou-se ao proprio estado através de transições epsilon,
		     * portanto devemos retornar para não cairmos  num loop infinito. */
		    return false ;
		
		/* Se foi feita uma transição epsilon, devemos adicionar este estado à 
		 * lista de visitados. */
		visitados.add(this);
		
		if (palavra.length() == 0) {
		    /* A string está vazia, então só conclui-se a aceitação desta string se
		     * este estado for final, ou se podemos chegar a um estado final sem ler
		     * nenhuma entrada. */
		    if (isFinal())
			return true ;

		    /* Verificado que este não é um estado final, devemos checar os estados
		     * vizinhos são finais. */
		    for (EstadoAFND resultado : transicoesVazias) {
			if (resultado.aceita("",visitados))
			    return true ;
		    }
		    return false ;
		} else {
		    /* Neste caso, a string não está vazia, então verificaremos o primeiro
		     * caracter e checaremos se os estados vizinhos acessados através deste
		     * caracter aceitam o resto da string. */

		    int c = (int)palavra.charAt(0) ;

		    for (EstadoAFND resultado : transicoesNaEntrada[c]) {
		    	if (resultado.aceita(palavra.substring(1)))
		    		return true ;
		    }

		    /* Como nenhum vizinho acessado através deste caractere de entrada aceita
		     * o restante da palavra, verificaremos se através das transições epsilon 
		     * disponiveis deste estado os estados resultantes aceitam a atual parte
		     * ainda não lida da palavra. */
		    for (EstadoAFND resultado : transicoesVazias) {
			if (resultado.aceita(palavra,visitados))
			    return true ;
		    }
		    /* Chegando aqui, conclui-se que a linguagem deste automato não aceita
		     * esta palavra.
		     */
		    return false ;
		}
		
	}
}
