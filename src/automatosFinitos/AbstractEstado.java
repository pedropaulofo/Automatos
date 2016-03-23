package automatosFinitos;


public class AbstractEstado implements Estado{

	private boolean isInicial = false;
	private boolean isFinal = false;
	protected int indice = 0;

	public AbstractEstado() {
	}
	
	@Override
	public int getIndice() {
		return indice;
	}
	
	@Override
	public void setIndice(int indice) {
		this.indice = indice;
	}

	@Override
	public void setInicial(boolean valor) {
		this.isInicial = valor;
	}

	@Override
	public void setFinal(boolean valor) {
		this.isFinal = valor;
	}

	@Override
	public boolean isInicial() {
		return isInicial;
	}

	@Override
	public boolean isFinal() {
		return isFinal;
	}

	@Override
	public void setFuncaoTransicao(String entrada, AbstractEstado estadoResutante) {
	}

	


}