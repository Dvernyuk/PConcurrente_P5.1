package mensajes.cliente;

import mensajes.Mensaje;

public class EliminarFichero extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3100841649630030860L;
	private String _fichero;

	public EliminarFichero(String fichero, String origen) {
		super(Mensaje.EliminarFichero, origen, null);
		_fichero = fichero;
	}
	
	public String getFichero() {
		return _fichero;
	}

}
