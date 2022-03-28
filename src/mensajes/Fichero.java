package mensajes;

public class Fichero extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1397335128048567208L;
	private String _fichero;

	public Fichero(String fichero, String origen, String destino) {
		super(Mensaje.Fichero, origen, destino);
		_fichero = fichero;
	}
	
	public String getFichero() {
		return _fichero;
	}

}
