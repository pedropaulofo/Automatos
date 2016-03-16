package automatosFinitos;

import java.util.List;


/**
 * Interface que define os metodos que interpretam o funcionamento de um automato finito;
 * 
 * @author Pedro Paulo
 */

public interface IAutomatoFinito {
	
	/**
	 * Todo automato deve ser capaz de possuir novos estados, durante sua constru��o.
	 * 
	 */
	public void addNovoEstado();
	
	/**
	 * O automato dev ser capaz de informar qual o seu estado incial.
	 * @return Estado inicial.
	 */
	public Estado getEstadoInicial();
	
	/**
	 * O automato deve ser capaz de informar qual o seu alfabeto;
	 * @return Alfabeto.
	 */
	public String[] getAlfabeto();
	
	/**
	 * O automato deve ser capaz de informar sua colea��o de estados.
	 * @return Conjunto de estados do aut�mato.
	 */
	public List<AbstractEstado> getEstados();
	
	/**
	 * O automato deve ser capaz de informar, dado um �ndice, o estado respectivo.
	 * @return O automato de
	 */
	public AbstractEstado getEstado(int ind);
	
	/**
	 * O usuario deve ser capaz de redefinir qual o estado inicial do automato.
	 * @param ind �ndice do estado que agora ser� o estado incial. Ex.: Para o param 5,
	 * o estado q5 ser� definido como o estado inicial).
	 */
	public void setEstadoInicial(int ind);
	
	/**
	 * O usuario deve ser capaz de definir os estados de aceita��o (estados finais), e reverter estados
	 * de aceita��o a estados comuns.
	 * @param ind �ndice do estado a ser definido como final/comm.
	 * @param valor Booleano que indica se o estado dado como parametro deve ser definido como final.
	 * Em caso negativo, o estado em questao ser� definido como n�o-final.
	 */
	public void setEstadoFinal(int ind, boolean valor);
	
}
