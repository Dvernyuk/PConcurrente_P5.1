package mensajes.cliente;

import mensajes.Mensaje;

public class CerrarConexion extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7925354000518823113L;

	public CerrarConexion(String origen) {
		super(Mensaje.CerrarConexion, origen, null);
	}

}
