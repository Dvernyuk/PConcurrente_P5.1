package mensajes.servidor;

import mensajes.Mensaje;

public class ConfirmacionEliminarFichero extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8828863260091447372L;

	public ConfirmacionEliminarFichero(String origen) {
		super(Mensaje.ConfirmacionEliminarFichero, origen, null);
	}

}
