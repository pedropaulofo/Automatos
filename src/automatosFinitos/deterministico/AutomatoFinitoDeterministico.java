package automatosFinitos.deterministico;

import java.util.ArrayList;
import java.util.List;

import automatosFinitos.AbstractEstado;
import automatosFinitos.AutomatoFinito;
import automatosFinitos.EntradaIndefinidaException;


public class AutomatoFinitoDeterministico extends AutomatoFinito{
	
	private List<EstadoAFD> estados;
	protected AbstractEstado estadoInicial = null;
	
	public AutomatoFinitoDeterministico(String[] alfabeto){
		super(alfabeto);
		estados = new ArrayList<EstadoAFD>();
		EstadoAFD inicial = this.addNovoEstado();
		this.estadoInicial = inicial;
	}
	
	public EstadoAFD addNovoEstado(){ //cria um proximo estado no automato, que por padrao suas funcoes de transi��o levam a si proprio
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
	public List<EstadoAFD> getEstados() {
		return this.estados;
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