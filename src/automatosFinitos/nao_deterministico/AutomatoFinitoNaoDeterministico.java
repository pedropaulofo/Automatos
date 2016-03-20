package automatosFinitos.nao_deterministico;

import java.util.ArrayList;
import java.util.List;

import automatosFinitos.AbstractEstado;
import automatosFinitos.AutomatoFinito;

public class AutomatoFinitoNaoDeterministico extends AutomatoFinito{
	
	private String[] alfabeto;
	private List<EstadoAFND> estados = new ArrayList<EstadoAFND>();

	public AutomatoFinitoNaoDeterministico(String[] alfabeto) {
		super(alfabeto);
	}

	@Override
	public AbstractEstado addNovoEstado() {
		EstadoAFND novoEstado = new EstadoAFND(alfabeto.length);
		novoEstado.setIndice(estados.size());
		estados.add(novoEstado);
		return novoEstado;
	}

	@Override
	public boolean aceitaPalavra(String palavra) {
		EstadoAFND estadoAtual = (EstadoAFND) this.getEstadoInicial();
		for(int i = 0; i< palavra.length(); i++){
			String entrada = "" + palavra.charAt(i);
			List<String> entradasTransicoes = estadoAtual.getEntradasTransicoes();
			for(int j=0; j< entradasTransicoes.size(); j++){
				if(entradasTransicoes.get(i).equals(entrada))
					estadoAtual = estadoAtual.getResultadosTransicoes().get(i);
			}
		}
		return estadoAtual.isFinal();
	}

}
