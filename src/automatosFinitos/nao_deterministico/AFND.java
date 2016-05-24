package automatosFinitos.nao_deterministico;

import java.util.ArrayList;
import automatosFinitos.AutomatoFinito;
import automatosFinitos.EntradaIndefinidaException;

public class AFND extends AutomatoFinito{
	
	private ArrayList<EstadoAFND> estados;

	public AFND(String[] alfabeto) {
		super(alfabeto);
		estados = new ArrayList<EstadoAFND>();
		EstadoAFND inicial = this.novoEstado();
		this.estadoInicial = inicial;
	}

	public EstadoAFND novoEstado() {
		EstadoAFND novo = new EstadoAFND();
		novo.setIndice(estados.size());
		this.estados.add(novo);
		return novo;
	}
	
	@Override
	public EstadoAFND getEstadoInicial() {
		return (EstadoAFND) estadoInicial;
	}
	
	public ArrayList<EstadoAFND> getEstados() {
		return this.estados;
	}

	@Override
	public boolean aceitaPalavra(String palavra) throws EntradaIndefinidaException {
		// TODO Auto-generated method stub
		return false;
	}
	


}
