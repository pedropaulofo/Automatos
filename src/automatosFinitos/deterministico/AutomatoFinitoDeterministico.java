package automatosFinitos.deterministico;

import java.util.ArrayList;
import java.util.List;

import automatosFinitos.Estado;

public class AutomatoFinitoDeterministico{
	
	private String[] alfabeto;
	private Estado estadoInicial = null;
	private List<Estado> estados = new ArrayList<Estado>();
	
	public AutomatoFinitoDeterministico(ArrayList<Integer> alfabeto){
		Estado inicial = new Estado(alfabeto.size());
		this.estadoInicial = inicial;
		estados.add(inicial);
	}
	
	
	public void addNovoEstado(){ //cria um proximo estado no automato, que por padrao suas funcoes de transição levam a si proprio
		Estado novoEstado = new Estado(alfabeto.length);
		novoEstado.setIndice(estados.size());
		for (int i = 0; i < this.alfabeto.length; i++){
			novoEstado.getFuncoesTransicao().put(alfabeto[i], novoEstado);
		}
		estados.add(novoEstado);
	}
	
	public Estado getEstadoInicial() {
		return estadoInicial;
	}

	public String[] getAlfabeto() {
		return alfabeto;
	}

	public List<Estado> getEstados() {
		return estados;
	}
	
	public void setEstadoInicial(int ind) {
		for (int i = 0; i < this.estados.size(); i++){
			if(i == ind)
				estados.get(i).setInicial(true);
			else
				estados.get(i).setInicial(false);
		}
	}
	
	public void setEstadoFinal(int ind, boolean valor) {
		for (int i = 0; i < this.estados.size(); i++){
			if(i == ind)
				estados.get(i).setFinal(valor);
		}
	}
	
	public boolean isCompleto() {
		for (int i = 0; i < this.estados.size(); i++){
			if(estados.get(i).getFuncoesTransicao().size() != alfabeto.length)
				return false;
		}
		return true;
	}

	
}