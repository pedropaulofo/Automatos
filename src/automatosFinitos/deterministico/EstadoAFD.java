package automatosFinitos.deterministico;

import java.util.HashMap;
import java.util.Map;

import automatosFinitos.AbstractEstado;
import automatosFinitos.EntradaIndefinidaException;


public class EstadoAFD extends AbstractEstado{
	
	private Map<String, EstadoAFD> funcoesTransicao;
	
	public EstadoAFD(int tamanhoAlfabeto){
		this.funcoesTransicao = new HashMap<String, EstadoAFD>(tamanhoAlfabeto);
	}

	public Map<String, EstadoAFD> getFuncoesTransicao() {
		return funcoesTransicao;
	}
	
	public void setFuncaoTransicao(String entrada, EstadoAFD estadoResultante) throws EntradaIndefinidaException{
		if(!funcoesTransicao.containsKey(entrada)) throw new EntradaIndefinidaException();
		funcoesTransicao.put(entrada, estadoResultante);
	}
	
	public EstadoAFD getResultadoFuncaoTransicao(String entrada) throws EntradaIndefinidaException{
		if(!funcoesTransicao.containsKey(entrada)) throw new EntradaIndefinidaException();
		return this.getFuncoesTransicao().get(entrada);
	}

}
