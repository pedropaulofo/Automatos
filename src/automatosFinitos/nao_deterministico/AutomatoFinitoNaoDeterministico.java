package automatosFinitos.nao_deterministico;

import java.util.ArrayList;
import java.util.List;

import automatosFinitos.AutomatoFinito;

public class AutomatoFinitoNaoDeterministico extends AutomatoFinito{
	
	private List<EstadoAFND> estados;

	public AutomatoFinitoNaoDeterministico(String[] alfabeto) {
		super(alfabeto);
		estados = new ArrayList<EstadoAFND>();
		EstadoAFND inicial = this.addNovoEstado();
		this.estadoInicial = inicial;
	}

	public EstadoAFND addNovoEstado() {
		EstadoAFND novo = new EstadoAFND();
		novo.setIndice(estados.size());
		this.estados.add(novo);
		return novo;
	}
	
	@Override
	public EstadoAFND getEstadoInicial() {
		return (EstadoAFND) estadoInicial;
	}
	
	public List<EstadoAFND> getEstados() {
		return this.estados;
	}

	@Override
	public boolean aceitaPalavra(String palavra) {
		return getEstadoInicial().aceita(palavra);
	}
	


}
