package mensajes.servidor;

import mensajes.Mensaje;

public class ConfirmacionCerrarConexion extends Mensaje {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6941996549368605915L;

	public ConfirmacionCerrarConexion(String destino) {
		super(Mensaje.ConfirmacionCerrarConexion, null, destino);
	}
	
}
