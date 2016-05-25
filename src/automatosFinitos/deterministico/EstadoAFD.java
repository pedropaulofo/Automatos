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
		//if(!funcoesTransicao.containsKey(entrada)) throw new EntradaIndefinidaException(entrada);
		funcoesTransicao.put(entrada, estadoResultante);
	}
	
	public EstadoAFD getResultadoFuncaoTransicao(String entrada) throws EntradaIndefinidaException{
		if(!funcoesTransicao.containsKey(entrada)) throw new EntradaIndefinidaException(entrada);
		return this.getFuncoesTransicao().get(entrada);
	}
	
	public void inicializar(String[] alfabeto){
		for (int i = 0; i < alfabeto.length; i++){
			funcoesTransicao.put(alfabeto[i], this);
		}
	}
	
	public boolean equivalente(EstadoAFD other){		
		if(other.isFinal() && !this.isFinal()) return false;
		if(!other.isFinal() && this.isFinal()) return false;
		
		Object[] alfabeto = funcoesTransicao.keySet().toArray();
		for(int i = 0; i < alfabeto.length; i++){
			String entrada = (String) alfabeto[i];
			try {
				if(getResultadoFuncaoTransicao(entrada).getIndice() != other.getResultadoFuncaoTransicao(entrada).getIndice())
					return false;
			} catch (EntradaIndefinidaException e) {
				return false;
			}
		}
		return true;
	}

}
