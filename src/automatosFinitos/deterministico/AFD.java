package automatosFinitos.deterministico;

import java.util.ArrayList;

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
		EstadoAFD inicial = this.novoEstado();
		this.estadoInicial = inicial;
	}
	
	public EstadoAFD novoEstado(){ //cria um proximo estado no automato, que por padrao suas funcoes de transição levam a si proprio
		EstadoAFD novo = new EstadoAFD(getAlfabeto().length);
		novo.setIndice(estados.size());
		novo.inicializar(alfabeto);
		estados.add(novo);
		return novo;
	}
	
	public void addEstado(EstadoAFD novo){
		estados.add(novo);
	}
	
	@Override
	public EstadoAFD getEstadoInicial() {
		return (EstadoAFD) estadoInicial;
	}
	
	public void setEstadoInicial(AbstractEstado estadoInicial) throws EntradaIndefinidaException {
		if (estados.size() > 1) {
			for (int i = 0; i < estados.size(); i++) {
				EstadoAFD estado = estados.get(i);
				for (String chave : estado.getFuncoesTransicao().keySet()) {
					if (estado.getResultadoFuncaoTransicao(chave).isInicial())
						estado.setFuncaoTransicao(chave, estado);
				}
				if (estado.isInicial())
					estados.remove(i);
			}
		}
		this.estadoInicial = estadoInicial;
		estadoInicial.setInicial(true);
		estados.add((EstadoAFD) estadoInicial);
		
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
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof AFD)) return false;
		AFD other = (AFD) obj;
		
		ArrayList<EstadoAFD> estados = this.getEstados();
		ArrayList<EstadoAFD> otherEstados = other.getEstados();
		int totalEstados = estados.size();
		
		if(otherEstados.size() != totalEstados) return false; //AFDs de tamanhos diferentes
		if(!other.getEstadoInicial().equals(this.getEstadoInicial())) return false; //Estados inciais nao-equivalentes
		
		for(int i = 0; i < totalEstados; i++){
			int j = 0;
			while(j < totalEstados - i - 1){
				if(otherEstados.isEmpty() || estados.isEmpty()) break;
				if(otherEstados.get(0).equals(estados.get(j))){
					otherEstados.remove(0);
					estados.remove(j);
					break; //encontrou um estado neste AFD igual ao atual o AFD other
				}
				j++;
			}
			if(j == totalEstados) return false; //não encontrou nenhum estado igual ao atual 
		}				

		return otherEstados.isEmpty() && estados.isEmpty();
		
	}
	
}