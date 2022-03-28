package mensajes.servidor;

import mensajes.Mensaje;

public class ConfirmacionAddFichero extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6949393120778493701L;

	public ConfirmacionAddFichero(String origen) {
		super(Mensaje.ConfirmacionAddFichero, origen, null);
	}

}
