package automatosFinitos.nao_deterministico;

import java.util.ArrayList;
import automatosFinitos.AbstractEstado;


public class EstadoAFND extends AbstractEstado{
	
	private ArrayList<String> chaves;
	private ArrayList<EstadoAFND> resultadosTransicoes;
	public static final String EPSILON = "EPSILON";
	
	public EstadoAFND(){
		chaves = new ArrayList<String>();
		resultadosTransicoes = new ArrayList<EstadoAFND>();
	}

	public void setFuncaoTransicao(String entrada, EstadoAFND estadoResutante) {			
		chaves.add(entrada);
		resultadosTransicoes.add(estadoResutante);
	}
	
	public void setTransicaoEpsilon(EstadoAFND estadoResultante) {
		chaves.add(EPSILON);
		resultadosTransicoes.add(estadoResultante);
	}
	
	public void removeFuncaoTransicao(String entrada, AbstractEstado estadoResutante){
		for(int i = 0; i < chaves.size(); i++){
			if(chaves.get(i).equals(entrada) && resultadosTransicoes.get(i).equals(estadoResutante)){
				chaves.remove(i);
				resultadosTransicoes.remove(i);
				return;
			}
		}
	}
	
	public ArrayList<String> getChaves() {
		return chaves;
	}
	
	public ArrayList<EstadoAFND> getResultadosTransicoes() {
		return resultadosTransicoes;
	}

}