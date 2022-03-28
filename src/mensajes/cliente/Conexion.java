package mensajes.cliente;

import mensajes.Mensaje;

public class Conexion extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6538779788009767009L;
	private String _hostname;
	
	public Conexion(String id, String hostname) {
		super(Mensaje.Conexion, id, null);
		
	}

	public String getHostname() {
		return _hostname;
	}
	
}
