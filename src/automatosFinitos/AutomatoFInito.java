package automatosFinitos;

import java.util.ArrayList;
import java.util.List;


public abstract class AutomatoFinito implements IAutomatoFinito{
	
	private String[] alfabeto;
	private Estado estadoInicial = null;
	private List<AbstractEstado> estados = new ArrayList<AbstractEstado>();
	
	public AutomatoFinito(String[] alfabeto) {
		this.alfabeto = alfabeto;
		AbstractEstado inicial = new AbstractEstado(alfabeto.length);
		this.estadoInicial = inicial;
		estados.add(inicial);
	}
	public Estado getEstadoInicial() {
		return estadoInicial;
	}

	public String[] getAlfabeto() {
		return alfabeto;
	}

	public List<AbstractEstado> getEstados() {
		return estados;
	}
	
	public AbstractEstado getEstado(int ind) {
		return estados.get(ind);
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
	

}
