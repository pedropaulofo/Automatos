package automatosFinitos.deterministico;

import java.util.HashMap;
import java.util.Map;

import automatosFinitos.AbstractEstado;


public class EstadoAFD extends AbstractEstado{
	
	private Map<String, EstadoAFD> funcoesTransicao;
	
	public EstadoAFD(int tamanhoAlfabeto){
		super(tamanhoAlfabeto);
		this.funcoesTransicao = new HashMap<String, EstadoAFD>(tamanhoAlfabeto);
	}

	public Map<String, EstadoAFD> getFuncoesTransicao() {
		return funcoesTransicao;
	}
	
	public void setFuncaoTransicao(String entrada, EstadoAFD estadoResultante) {
		funcoesTransicao.put(entrada, estadoResultante);
	}
	
	public EstadoAFD getResultadoFuncaoTransicao(String entrada) {
		return this.getFuncoesTransicao().get(entrada);
	}

}
