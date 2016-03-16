package automatosFinitos;

import java.util.HashMap;
import java.util.Map;

public class Estado {
	private boolean isInicial = false;
	private boolean isFinal = false;
	private Map<String, Estado> funcoesTransicao;
	private int indice = 0;
	
	public Estado(int tamanhoAlfabeto){
		this.funcoesTransicao = new HashMap<String, Estado>(tamanhoAlfabeto);
	}
	
	public int getIndice() {
		return indice;
	}
	
	public Map<String, Estado> getFuncoesTransicao() {
		return funcoesTransicao;
	}
	
	public void setIndice(int indice) {
		this.indice = indice;
	}

	public void setInicial(boolean valor){
		this.isInicial = valor;
	}

	public void setFinal(boolean valor){
		this.isFinal = valor;
	}

	public void setFuncaoTransicao(String entrada, Estado estadoResutante){
		funcoesTransicao.put(entrada, estadoResutante);
	}

	public boolean isInicial() {
		return isInicial;
	}

	public boolean isFinal() {
		return isFinal;
	}
	
	

}
