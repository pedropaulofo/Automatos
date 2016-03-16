package automatosFinitos.deterministico;

import java.util.ArrayList;
import java.util.List;

import automatosFinitos.AutomatoFinito;

public class AutomatoFinitoDeterministico extends AutomatoFinito{
	
	private String[] alfabeto;
	private List<EstadoAFD> estados = new ArrayList<EstadoAFD>();
	
	public AutomatoFinitoDeterministico(String[] alfabeto){
		super(alfabeto);
	}
	
	
	public void addNovoEstado(){ //cria um proximo estado no automato, que por padrao suas funcoes de transição levam a si proprio
		EstadoAFD novoEstado = new EstadoAFD(alfabeto.length);
		novoEstado.setIndice(estados.size());
		for (int i = 0; i < this.alfabeto.length; i++){
			novoEstado.getFuncoesTransicao().put(alfabeto[i], novoEstado);
		}
		estados.add(novoEstado);
	}
	
	
}