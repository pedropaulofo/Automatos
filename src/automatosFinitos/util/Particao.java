package automatosFinitos.util;

import java.util.LinkedList;

import automatosFinitos.deterministico.EstadoAFD;

public class Particao {
	LinkedList<EstadoAFD> estados;
	boolean particionavel = true;
	
	public Particao(LinkedList<EstadoAFD> estados){
		this.estados = estados;
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

}
