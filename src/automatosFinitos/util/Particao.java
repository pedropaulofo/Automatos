package automatosFinitos.util;

import java.util.LinkedList;
import automatosFinitos.deterministico.EstadoAFD;

public class Particao {
	private LinkedList<EstadoAFD> estados;
	
	public Particao(LinkedList<EstadoAFD> estados){
		this.estados = estados;
	}
	
	public Particao(EstadoAFD estado){
		this.estados = new LinkedList<EstadoAFD>();
		estados.add(estado);
	}
	
	public Particao(){
		this.estados = new LinkedList<EstadoAFD>();
	}
	
	public void addEstado(EstadoAFD novo){
		estados.add(novo);
	}

	public LinkedList<EstadoAFD> getEstados() {
		return estados;
	}
	
	public boolean isParticionavel(){
		return estados.size() > 1;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Particao))
			return false;
		Particao other = (Particao) obj;
		for(EstadoAFD estado: estados){
			if(!other.getEstados().contains(estado))
				return false;
		}
		for(EstadoAFD estado: other.getEstados()){
			if(!estados.contains(estado))
				return false;
		}
		return true;
	}

}
