package mensajes.servidor;

import mensajes.Mensaje;

public class ConfirmacionConexion extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5181834810092900270L;

	public ConfirmacionConexion(String destino) {
		super(Mensaje.ConfirmacionConexion, null, destino);
	}

}
