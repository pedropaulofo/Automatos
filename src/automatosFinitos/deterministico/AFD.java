package automatosFinitos.deterministico;

import java.util.ArrayList;
import java.util.List;

import automatosFinitos.AbstractEstado;
import automatosFinitos.AutomatoFinito;
import automatosFinitos.EntradaIndefinidaException;


public class AFD extends AutomatoFinito{
	
	private ArrayList<EstadoAFD> estados;
	private ArrayList<EstadoAFD> estadosFinais;
	protected AbstractEstado estadoInicial = null;
	
	public AFD(String[] alfabeto){
		super(alfabeto);
		estados = new ArrayList<EstadoAFD>();
		estadosFinais = new ArrayList<EstadoAFD>();
		EstadoAFD inicial = this.addNovoEstado();
		this.estadoInicial = inicial;
	}
	
	public EstadoAFD addNovoEstado(){ //cria um proximo estado no automato, que por padrao suas funcoes de transição levam a si proprio
		EstadoAFD novo = new EstadoAFD(getAlfabeto().length);
		novo.setIndice(estados.size());
		for (int i = 0; i < getAlfabeto().length; i++){
			novo.getFuncoesTransicao().put(getAlfabeto()[i], novo);
		}
		estados.add(novo);
		return novo;
	}
	
	@Override
	public EstadoAFD getEstadoInicial() {
		return (EstadoAFD) estadoInicial;
	}
	
	@Override
	public ArrayList<EstadoAFD> getEstados() {
		return this.estados;
	}
	
	public ArrayList<EstadoAFD> getEstadosFinais() {
		return estadosFinais;
	}
	
	@Override
	public boolean aceitaPalavra(String palavra) throws EntradaIndefinidaException {
		EstadoAFD estadoAtual = this.getEstadoInicial();
		for(int i = 0; i< palavra.length(); i++){
			String entrada = "" + palavra.charAt(i);
			estadoAtual = estadoAtual.getResultadoFuncaoTransicao(entrada);
		}
		return estadoAtual.isFinal();
	}
	
}