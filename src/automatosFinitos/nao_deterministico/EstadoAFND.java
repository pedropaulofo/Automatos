package automatosFinitos.nao_deterministico;

import java.util.ArrayList;
import java.util.List;

import automatosFinitos.AbstractEstado;


public class EstadoAFND extends AbstractEstado{
	
	private List<String> entradas;
	private List<EstadoAFND> estadosResultantes;
	
	private List<EstadoAFND> vazios = new ArrayList<EstadoAFND>();
	
	public EstadoAFND(int tamanhoAlfabeto){
		super(tamanhoAlfabeto);
		this.entradas= new ArrayList<String>(tamanhoAlfabeto);
		this.estadosResultantes= new ArrayList<EstadoAFND>(tamanhoAlfabeto);
	}

	@Override
	public void setFuncaoTransicao(String entrada, AbstractEstado estadoResutante) {
		entradas.add(entrada);
		estadosResultantes.add((EstadoAFND) estadoResutante);
	}
	
	public void removeFuncaoTransicao(String entrada, AbstractEstado estadoResutante){
		for(int i = 0; i < estadosResultantes.size(); i++){
			if(estadosResultantes.get(i).equals(estadoResutante)){
				if(entradas.get(i).equals(entrada)){
					estadosResultantes.remove(i);
					entradas.remove(i);
				}
			}
		}
	}
	
	public List<String> getEntradasTransicoes(){
		return this.entradas;
	}
	
	public List<EstadoAFND> getResultadosTransicoes(){
		return this.estadosResultantes;
	}
	
	public boolean aceita(String s) {
		return aceita(s,new ArrayList<EstadoAFND>()) ;
	}
	
	private boolean aceita(String palavra, ArrayList<EstadoAFND> visitados){

		if (visitados.contains(this)) 
		    /* We've found a path back to ourself through empty edges;
		     * stop or we'll go into an infinite loop. */
		    return false ;
		
		/* In case we make an empty transition, we need to add this
		 * state to the visited list. */
		visitados.add(this);
		
		if (palavra.length() == 0) {
		    /* The string is empty, so we match this string only if
		     * this state is a final state, or we can reach a final
		     * state without consuming any input. */
		    if (isFinal())
			return true ;

		    /* Since this state is not final, we'll ask if any
		     * neighboring states that we can reach on empty edges can
		     * match the empty string. */
		    for (EstadoAFND next : vazios) {
			if (next.aceita("",visitados))
			    return true ;
		    }
		    return false ;
		} else {
		    /* In this case, the string is not empty, so we'll pull
		     * the first character off and check to see if our
		     * neighbors for that character can match the remainder of
		     * the string. */

		    int c = (int)palavra.charAt(0) ;

		    for (EstadoAFND next : onChar[c]) {
		    	if (next.aceita(palavra.substring(1)))
		    		return true ;
		    }

		    /* It looks like we weren't able to match the string by
		     * consuming a character, so we'll ask our
		     * empty-transition neighbors if they can match the entire
		     * string. */
		    for (EstadoAFND next : vazios) {
			if (next.aceita(palavra,visitados))
			    return true ;
		    }
		    return false ;
		}
		
	}
}
