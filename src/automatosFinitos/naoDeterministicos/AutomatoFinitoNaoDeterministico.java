package automatosFinitos.naoDeterministicos;

import java.util.ArrayList;
import java.util.List;

import automatosFinitos.AutomatoFinito;

public class AutomatoFinitoNaoDeterministico extends AutomatoFinito{
	
	private String[] alfabeto;
	private List<EstadoAFND> estados = new ArrayList<EstadoAFND>();

	public AutomatoFinitoNaoDeterministico(String[] alfabeto) {
		super(alfabeto);
	}

	@Override
	public void addNovoEstado() {
		EstadoAFND novoEstado = new EstadoAFND(alfabeto.length);
		novoEstado.setIndice(estados.size());
		estados.add(novoEstado);
	}

}
