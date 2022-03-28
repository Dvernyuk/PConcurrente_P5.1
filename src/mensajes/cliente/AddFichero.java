package mensajes.cliente;

import mensajes.Mensaje;

public class AddFichero extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4416939741903633817L;
	private String _fichero;

	public AddFichero(String fichero, String origen) {
		super(Mensaje.AddFichero, origen, null);
		_fichero = fichero;
	}

	public String getFichero() {
		return _fichero;
	}
	
}
