package automatosFinitos;

@SuppressWarnings("serial")
public class EntradaIndefinidaException extends Exception{
	public EntradaIndefinidaException(){
		super("Esta entrada nao esta presente no alfabeto definido.");
	}
}
