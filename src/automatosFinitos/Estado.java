package automatosFinitos;

public interface Estado {
	
	/**
	 * O estado deve ser capaz de informar seu �ndice no aut�mato.
	 * @return �ndice do estado;
	 */
	int getIndice();
	
	/**
	 * O estado de ser capaz de atualizar seu �ndice no aut�mato;
	 * @param indice Novo �ndice a ser definido.
	 */
	void setIndice(int indice);
	
	/**
	 * O estado deve ser capaz de ser definido como o Estado Inicial do aut�mato ou redefinido
	 * como N�o-incial.
	 * @param valor Booleano que indica se o estado � definido ou n�o como inicial.
	 */
	void setInicial(boolean valor);
	
	/**
	 * O estado deve ser capaz de ser definido como Estado Final (estado de aceita��o) e
	 * redefinido como n�o-final.
	 * @param valor Booleano que indica se o estado ser� deinido ou n�o como final.
	 */
	void setFinal(boolean valor);
	
	/**
	 * O estado deve poder definir fun��es de tr�nsi��o que ligam a entrada dada ao estado
	 * especificado.
	 * @param entrada Entrada da fun��o.
	 * @param estadoResutante Estado resultante ao ler a entrada.
	 */
	void setFuncaoTransicao(String entrada, AbstractEstado estadoResutante);
	
	/**
	 * O estado deve ser capaz de informar se � o Estado Inicial.
	 * @return True se for, False se n�o.
	 */
	boolean isInicial();
	
	/**
	 * O estado deve ser capaz de informar se � um Estado Final(estado de aceita��o).
	 * @return True se for, False se n�o.
	 */
	boolean isFinal();


}