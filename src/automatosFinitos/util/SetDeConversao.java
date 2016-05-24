package automatosFinitos.util;

import java.util.ArrayList;
import java.util.HashMap;

import automatosFinitos.deterministico.EstadoAFD;
import automatosFinitos.nao_deterministico.EstadoAFND;;

public class SetDeConversao {

	private ArrayList<EstadoAFND> estadosInternos;
	private HashMap<String, SetDeConversao> setsTraniscoes;
	private String[] alfabeto;
	
	public SetDeConversao(ArrayList<EstadoAFND> alcancaveis, String[] alfabeto){
		this.estadosInternos = alcancaveis;
		this.setsTraniscoes = new HashMap<String, SetDeConversao>(alfabeto.length);
		this.alfabeto = alfabeto;
	}
	
	public EstadoAFD toEstadoAFD(){
		return null;
	}
	
	public boolean isFinal(){
		for(EstadoAFND estado : estadosInternos){
			if(estado.isFinal())
				return true;
		}
		return false;
	}
	
	public ArrayList<EstadoAFND> getEstadosInternos() {
		return estadosInternos;
	}

	public HashMap<String, SetDeConversao> getSetsTraniscoes() {
		return setsTraniscoes;
	}
	
	public SetDeConversao getSetTransicao(String entrada){
		return setsTraniscoes.get(entrada);
	}
	
	public void setTransicao(String entrada, SetDeConversao resultante){
		setsTraniscoes.put(entrada, resultante);
	}
	
	public String[] getAlfabeto() {
		return alfabeto;
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof SetDeConversao))
			return false;
		SetDeConversao other = (SetDeConversao) obj;
		for(EstadoAFND estado: estadosInternos){
			if(!other.getEstadosInternos().contains(estado))
				return false;
		}
		for(EstadoAFND estado: other.getEstadosInternos()){
			if(!this.getEstadosInternos().contains(estado))
				return false;
		}
		return true;
	}

}
