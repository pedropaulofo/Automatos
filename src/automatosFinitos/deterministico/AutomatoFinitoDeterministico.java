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
	
	@Override
	public EstadoAFD getEstadoInicial() {
		return (EstadoAFD) estadoInicial;
	}
	
	public List<EstadoAFD> getEstados() {
		return this.estados;
	}
	
	public EstadoAFD addNovoEstado(){ //cria um proximo estado no automato, que por padrao suas funcoes de transição levam a si proprio
		EstadoAFD novoEstado = new EstadoAFD(getAlfabeto().length);
		novoEstado.setIndice(estados.size());
		for (int i = 0; i < getAlfabeto().length; i++){
			novoEstado.getFuncoesTransicao().put(getAlfabeto()[i], novoEstado);
		}
		estados.add(novoEstado);
		return novoEstado;
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