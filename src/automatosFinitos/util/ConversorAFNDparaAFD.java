package automatosFinitos.util;

import java.util.ArrayList;

import automatosFinitos.EntradaIndefinidaException;
import automatosFinitos.deterministico.AFD;
import automatosFinitos.deterministico.EstadoAFD;
import automatosFinitos.nao_deterministico.*;

public class ConversorAFNDparaAFD {
	
	
	public static AFD converter(AFND automato) throws EntradaIndefinidaException{
		ArrayList<SetDeConversao> sets = new ArrayList<SetDeConversao>();
		SetDeConversao setInicial = new SetDeConversao(estadosAlcancaveis(automato.getEstadoInicial(), EstadoAFND.EPSILON), automato.getAlfabeto());
		// O set de estados que é definido como inciial é aquele obtido do estado inciial do AFND com a entrada nula (EPSILON)
		sets.add(setInicial);
		addSetsResultantes(sets, setInicial);
		return gerarAFD(sets);
	}
	
	/**
	 * Retorna um ArrayList contendo todos os estados alcançáveis a partir do estado dado e lida a entrada dada,
	 * que pode também ser simplemente EPSILON.
	 * 
	 * @param inicial Estado AFND do qual se parte para  a análise.
	 * @param entrada Valor de entrada que determina o alcance. Pode ser EPSILON.
	 * @return
	 */
	public static ArrayList<EstadoAFND> estadosAlcancaveis(EstadoAFND inicial, String entrada){ 
		ArrayList<EstadoAFND> alcancaveis = new ArrayList<EstadoAFND>();		
		addAlcancaveis(alcancaveis, inicial, entrada);
		return alcancaveis;
	}
	
	private static void addAlcancaveis(ArrayList<EstadoAFND> registrados, EstadoAFND atual, String entrada){
		if(registrados.contains(atual))
			return;						//evita ciclos
		else
			registrados.add(atual);		//registra estado visitado
		
		ArrayList<String> chaves = atual.getChaves();
		ArrayList<EstadoAFND> estados = atual.getResultadosTransicoes();
		
		for(int i=0; i < atual.getChaves().size(); i++){ //transicoes EPSILON que podem ser feitas sem ler entrada
			if(chaves.get(i).equals(EstadoAFND.EPSILON))
				addAlcancaveis(registrados, estados.get(i), entrada); //chamada recursiva para cada estado alcançado por EPSILON
		}
		
		if (entrada != EstadoAFND.EPSILON){ //checa se há uma entrada para ser lida	
			for(int i=0; i < atual.getChaves().size(); i++){ //transicoes compativeis com a entrada
				if(chaves.get(i).equals(entrada))
					addAlcancaveis(registrados, estados.get(i), EstadoAFND.EPSILON); //chamada recursiva para cada estado alcançado ao ler a entrada, agora sem poder lê-la novamente
			}
		}
	}

	private static void addSetsResultantes(ArrayList<SetDeConversao> conhecidos, SetDeConversao atual){
		if(conhecidos.contains(atual))
			return;						//evita ciclos
		else
			conhecidos.add(atual);		//registra set como conhecido
		
		String[] alfabeto = atual.getAlfabeto();
		for(int i=0; i < alfabeto.length; i++){ //para cada chave do alfabeto de entradas gera um set resultante
			SetDeConversao resultante = setResultante(atual, alfabeto[i]);
			atual.setTransicao(alfabeto[i], resultante); //define a transição do atual para o resultante de acordo com a entrada que o gerou.
			addSetsResultantes(conhecidos, resultante); //chamada recursiva para set obtido do atual
		}
	}

	private static SetDeConversao setResultante(SetDeConversao atual, String entrada){
		ArrayList<EstadoAFND> alcancaveis = new ArrayList<EstadoAFND>();		
		for(EstadoAFND estado : atual.getEstadosInternos())
			addAlcancaveis(alcancaveis, estado, entrada);
		return new SetDeConversao(alcancaveis, atual.getAlfabeto());
	}
	
	private static AFD gerarAFD(ArrayList<SetDeConversao> sets) throws EntradaIndefinidaException{
		String[] alfabeto = sets.get(0).getAlfabeto();
		ArrayList<EstadoAFD> estados = new ArrayList<EstadoAFD>();
		for(int i=0; i < sets.size(); i++)
			estados.add(new EstadoAFD(alfabeto.length)); //cria um estado (atualmente autorreferenciado apenas) para cada set
		
		for(int i=0; i < sets.size(); i++){
			if(sets.get(i).isFinal()) estados.get(i).setFinal(true); //para cada estado equivalente a um set que contém um final, o estado é final
			for(int j=0; j < alfabeto.length; j++){
				int indResultante = getIndiceSet(sets, sets.get(i).getSetTransicao(alfabeto[j])); //O indice equivalente do set resultante da transição 
				estados.get(i).setFuncaoTransicao(alfabeto[j], estados.get(indResultante)); //O estado de mesmo indice é resultante dessa mesma transição no estado equivalente iterado
			}
		}
		
		AFD automato = new AFD(alfabeto);
		EstadoAFD inicial = estados.get(0); //o primeiro set é o set que contém o estado inicial sem ler entrada, logo o primeiro estado é o inicial.
		inicial.setInicial(true);
		automato.setEstadoInicial(inicial);
		for(int i=1; i < estados.size(); i++){
			automato.addEstado(estados.get(i));
		}
		
		return automato;
	}
	
	private static int getIndiceSet(ArrayList<SetDeConversao> sets, SetDeConversao set){
		for(int i=0; i<sets.size(); i++){
			if(sets.get(i).equals(set)) return i;
		}
		return 0;
	}
}
