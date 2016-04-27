package automatosFinitos.util;

import java.util.ArrayList;

import automatosFinitos.deterministico.*;

/**
 * Converte um AFD dado para sua forma minima (menor numero de estados possiveis.
 */
public class MinimizadorAFD {
	
	public AFD minimizacao(AFD autom){
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
	
	private void removeEstadosIndistinguiveis(AFD autom){
		ArrayList<EstadoAFD> finais = autom.getEstadosFinais();
		ArrayList<EstadoAFD> naoFinais = autom.getEstados();
		naoFinais.removeAll(finais);
		
		while(!finais.isEmpty()){
			EstadoAFD[] par = {finais.get(0), finais.get(finais.size()-1)};
			ArrayList<EstadoAFD> resultantesDoPar = (ArrayList<EstadoAFD>) par[0].getFuncoesTransicao().values();
			resultantesDoPar.addAll( par[1].getFuncoesTransicao().values());
		}
		
	}

}
