package automatosFinitos.util;

import java.util.ArrayList;
import java.util.LinkedList;

import automatosFinitos.EntradaIndefinidaException;
import automatosFinitos.deterministico.*;

/**
 * Converte um AFD dado para sua forma minima (equivalente com menor numero de estados possíveis par).
 */
public class MinimizadorAFD {
	
	@SuppressWarnings("unchecked")
	public static AFD minimizacao(AFD autom) throws EntradaIndefinidaException{
		LinkedList<Particao> atuaisParticoes = new LinkedList<>();
		atuaisParticoes.add(particaoInicial(autom));
		LinkedList<Particao> novasParticoes = (LinkedList<Particao>) atuaisParticoes.clone();
		
		while(true){
			for(Particao part : novasParticoes){
				if (part.isParticionavel())
					novasParticoes = reParticiona(novasParticoes, part);
			}
			if(particoesImutaveis(atuaisParticoes, novasParticoes))
				break;
			else{
				atuaisParticoes = (LinkedList<Particao>) novasParticoes.clone();
			}
		}
		atuaisParticoes.add(particaoFinal(autom));
		AFD minim = gerarAFD(atuaisParticoes, autom.getAlfabeto());
		return minim;
	}
	
	public static ArrayList<EstadoAFD> estadosInalcancaveis(AFD autom){
		int qtdeEstados = autom.getEstados().size();
		ArrayList<EstadoAFD> alcancaveis = new ArrayList<EstadoAFD>(qtdeEstados);
		alcancaveis.add(autom.getEstadoInicial());
		ArrayList<EstadoAFD> novosEstados = new ArrayList<EstadoAFD>(qtdeEstados);
		novosEstados.add(autom.getEstadoInicial());
		
		ArrayList<EstadoAFD> temp;
		while(!novosEstados.isEmpty()){
			temp = new ArrayList<EstadoAFD>(qtdeEstados);
			EstadoAFD estadoAtual;
			for(int i = 0; i < novosEstados.size(); i++){
				estadoAtual = novosEstados.get(i);
				temp.addAll(estadoAtual.getFuncoesTransicao().values());
			}
			novosEstados = temp;
			novosEstados.removeAll(alcancaveis);
			alcancaveis.addAll(novosEstados);
		}
		ArrayList<EstadoAFD> inalcancaveis = (ArrayList<EstadoAFD>) autom.getEstados().clone();
		inalcancaveis.removeAll(alcancaveis);
		return inalcancaveis;
	}
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<EstadoAFD> estadosAlcancaveis(AFD autom){
		ArrayList<EstadoAFD> estados = autom.getEstados();
		ArrayList<EstadoAFD> alcancaveis = (ArrayList<EstadoAFD>) estados.clone();
		alcancaveis.removeAll(estadosInalcancaveis(autom));
	
		return alcancaveis;
	}
	
	private static Particao particaoInicial(AFD autom){
		Particao naoFinais = new Particao();
		for(EstadoAFD estado : estadosAlcancaveis(autom)){
			if(!estado.isFinal())
				naoFinais.addEstado(estado);
		}
		return naoFinais;
	}
	
	private static Particao particaoFinal(AFD autom){
		Particao finais = new Particao();
		for(EstadoAFD estado : autom.getEstados()){
			if(estado.isFinal())
				finais.addEstado(estado);
		}
		return finais;
	}
	
	@SuppressWarnings("unchecked")
	private static LinkedList<Particao> reParticiona(LinkedList<Particao> atuais, Particao p) throws EntradaIndefinidaException{
		LinkedList<Particao> novas = (LinkedList<Particao>) atuais.clone();
		for(int i=1; i < p.getEstados().size(); i++){
			EstadoAFD estado1 = p.getEstados().get(i -1);
			EstadoAFD estado2 = p.getEstados().get(i);
			for(String chave: estado1.getFuncoesTransicao().keySet()){
				if(!mesmaParticao(atuais, estado1.getResultadoFuncaoTransicao(chave), estado2.getResultadoFuncaoTransicao(chave))){
					novas = realocaEstado(novas, estado2);
					break;
				}
			}
		}
		return novas;
	}
	
	private static boolean mesmaParticao(LinkedList<Particao> particoes, EstadoAFD estado1, EstadoAFD estado2){
		for(Particao part : particoes){
			if(part.getEstados().contains(estado1) && !part.getEstados().contains(estado2))
				return false;
			else if(part.getEstados().contains(estado1))
				return true;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private static LinkedList<Particao> realocaEstado(LinkedList<Particao> atuais, EstadoAFD estado) throws EntradaIndefinidaException{
		LinkedList<Particao> novasParticoes = (LinkedList<Particao>) atuais.clone();
		boolean encontrada = false;
		
		for(Particao part : novasParticoes){
			LinkedList<EstadoAFD> estadosPart = part.getEstados();
			if(estadosPart.contains(estado) && part.isParticionavel())
				estadosPart.remove(estado);
			for(String chave: estado.getFuncoesTransicao().keySet()){
				EstadoAFD resultanteBase = estadosPart.getFirst().getResultadoFuncaoTransicao(chave);
				EstadoAFD resultanteTeste = estado.getResultadoFuncaoTransicao(chave);
				if(mesmaParticao(novasParticoes, resultanteBase, resultanteTeste))
					encontrada = true;
				else
					encontrada = false;
			}
			if(encontrada){
				part.addEstado(estado);
				return novasParticoes;
			}
		}
		Particao nova = new Particao(estado);
		novasParticoes.add(nova);
		return novasParticoes;
	}
	
	private static boolean particoesImutaveis(LinkedList<Particao> anteriores, LinkedList<Particao> atuais){
		for(Particao particao: anteriores){
			if(!atuais.contains(particao))
				return false;
		}
		for(Particao particao: atuais){
			if(!anteriores.contains(particao))
				return false;
		}
		return true;
	}
	
	private static AFD gerarAFD(LinkedList<Particao> particoes, String[] alfabeto) throws EntradaIndefinidaException{
		ArrayList<EstadoAFD> estados = new ArrayList<EstadoAFD>();
		for(int i=0; i < particoes.size(); i++)
			estados.add(new EstadoAFD(alfabeto.length)); //cria um estado (atualmente autorreferenciado apenas) para cada particao
		estados.get(estados.size()-1).setFinal(true); //sendo a particao que contém os estados finais a ultima adionada, o estado equivalente é final
		
		for(int i=0; i < particoes.size(); i++){
			for(int j=0; j < alfabeto.length; j++){
				int indResultante = geIndiceParticaoEquivalente(particoes, particoes.get(i).getEstados().getFirst().getResultadoFuncaoTransicao(alfabeto[j]));
				estados.get(i).setFuncaoTransicao(alfabeto[j], estados.get(indResultante));
				//O estado que está na particao resultante é resultante para a mesma entrada no estado equivalente (mesmo índice)
			}
		}
		
		AFD automato = new AFD(alfabeto);
		EstadoAFD inicial = estados.get(geIndiceInicial(particoes));
		inicial.setInicial(true);
		automato.setEstadoInicial(inicial);
		for(int i=1; i < estados.size(); i++){
			automato.addEstado(estados.get(i));
		}
		
		return automato;
	}
	
	private static int geIndiceParticaoEquivalente(LinkedList<Particao> particoes, EstadoAFD estado){
		for(int i=0; i < particoes.size(); i++){
			if(particoes.get(i).getEstados().contains(estado))
				return i;
		}
		return 0;
	}
	
	private static int geIndiceInicial(LinkedList<Particao> particoes){
		for(int i=0; i < particoes.size(); i++){
			for(EstadoAFD estado : particoes.get(i).getEstados())
				if(estado.isInicial()) return i;
		}
		return 0;
	}

}
