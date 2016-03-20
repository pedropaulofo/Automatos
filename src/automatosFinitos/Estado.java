package automatosFinitos;

public interface Estado {
	
	/**
	 * O estado deve ser capaz de informar seu índice no autômato.
	 * @return Índice do estado;
	 */
	int getIndice();
	
	/**
	 * O estado de ser capaz de atualizar seu índice no autômato;
	 * @param indice Novo índice a ser definido.
	 */
	void setIndice(int indice);
	
	/**
	 * O estado deve ser capaz de ser definido como o Estado Inicial do autômato ou redefinido
	 * como Não-incial.
	 * @param valor Booleano que indica se o estado é definido ou não como inicial.
	 */
	void setInicial(boolean valor);
	
	/**
	 * O estado deve ser capaz de ser definido como Estado Final (estado de aceitação) e
	 * redefinido como não-final.
	 * @param valor Booleano que indica se o estado será deinido ou não como final.
	 */
	void setFinal(boolean valor);
	
	/**
	 * O estado deve poder definir funções de trânsição que ligam a entrada dada ao estado
	 * especificado.
	 * @param entrada Entrada da função.
	 * @param estadoResutante Estado resultante ao ler a entrada.
	 */
	void setFuncaoTransicao(String entrada, AbstractEstado estadoResutante);
	
	/**
	 * O estado deve ser capaz de informar se é o Estado Inicial.
	 * @return True se for, False se não.
	 */
	boolean isInicial();
	
	/**
	 * O estado deve ser capaz de informar se é um Estado Final(estado de aceitação).
	 * @return True se for, False se não.
	 */
	boolean isFinal();


}