package mensajes.servidor;

import mensajes.Mensaje;

public class PreparadoServidor_Cliente extends Mensaje {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4622300546070596102L;
	private int _port;
	private String _hostname;
	
	public PreparadoServidor_Cliente(int puerto, String hostname, String origen, String destino) {
		super(Mensaje.PreparadoServidor_Cliente, origen, destino);
		_port = puerto;
		_hostname = hostname;
	}

	public int getPort() {
		return _port;
	}
	
	public String getHostname() {
		return _hostname;
	}
	
}
