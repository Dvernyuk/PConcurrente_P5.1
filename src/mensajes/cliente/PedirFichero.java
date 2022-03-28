package mensajes.cliente;

import mensajes.Mensaje;

public class PedirFichero extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6184176570198673292L;
	private String _fichero;
	
	public PedirFichero(String fichero, String origen) {
		super(Mensaje.PedirFichero, origen, null);
		_fichero = fichero;
	}
	
	public String getFichero() {
		return _fichero;
	}

}
