package automatosFinitos.naoDeterministicos;

import java.util.ArrayList;
import java.util.List;

import automatosFinitos.AbstractEstado;


public class EstadoAFND extends AbstractEstado{
	
	private List<String> entradas;
	private List<EstadoAFND> estadosResultantes;
	
	public EstadoAFND(int tamanhoAlfabeto){
		super(tamanhoAlfabeto);
		this.entradas= new ArrayList<String>(tamanhoAlfabeto);
		this.estadosResultantes= new ArrayList<EstadoAFND>(tamanhoAlfabeto);
	}

	@Override
	public void setFuncaoTransicao(String entrada, AbstractEstado estadoResutante) {
		entradas.add(entrada);
		estadosResultantes.add((EstadoAFND) estadoResutante);
	}
	
	public void removeFuncaoTransicao(String entrada, AbstractEstado estadoResutante){
		for(int i = 0; i < estadosResultantes.size(); i++){
			if(estadosResultantes.get(i).equals(estadoResutante)){
				if(entradas.get(i).equals(entrada)){
					estadosResultantes.remove(i);
					entradas.remove(i);
				}
			}
		}
	}

}
