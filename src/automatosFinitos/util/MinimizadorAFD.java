package automatosFinitos.util;

import java.util.ArrayList;
import java.util.LinkedList;

import automatosFinitos.deterministico.*;
import automatosFinitos.nao_deterministico.EstadoAFND;

/**
 * Converte um AFD dado para sua forma minima (equivalente com menor numero de estados possíveis par).
 */
public class MinimizadorAFD {
	
	public AFD minimizacao(AFD autom){
		Particao part = particaoInicial(autom);
		LinkedList<Particao> atualPart = new LinkedList<>();
		atualPart.add(part);
		LinkedList<Particao> novaPart;
		
		AFD minim = null;
		return minim;
	}
	
	public ArrayList<EstadoAFD> estadosInalcacaveis(AFD autom){
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
		ArrayList<EstadoAFD> inalcancaveis = autom.getEstados();
		inalcancaveis.removeAll(alcancaveis);
		return inalcancaveis;
	}
	
	private Particao particaoInicial(AFD autom){
		Particao naoFinais = new Particao();
		for(EstadoAFD estado : autom.getEstados()){
			if(!estado.isFinal())
				naoFinais.addEstado(estado);
		}
		return naoFinais;
	}
	
	private Particao particaoFinal(AFD autom){
		Particao finais = new Particao();
		for(EstadoAFD estado : autom.getEstados()){
			if(estado.isFinal())
				finais.addEstado(estado);
		}
		return finais;
	}
	
	private LinkedList<Particao> reParticionar(LinkedList<Particao> atuais, Particao p){
		int indPart = atuais.indexOf(p);
		for(EstadoAFD estado : p.getEstados()){
			
		}
	}
	
	private boolean particoesImutaveis(LinkedList<Particao> anteriores, LinkedList<Particao> atuais){
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

}
