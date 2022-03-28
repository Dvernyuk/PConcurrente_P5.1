package mensajes.cliente;

import mensajes.Mensaje;

public class PreparadoCliente_Servidor extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = -806824575974183668L;
	private int _port;
	private String _hostname;
	
	public PreparadoCliente_Servidor(int port, String hostname, String origen, String destino) {
		super(Mensaje.PreparadoCliente_Servidor, origen, destino);
		_port = port;
		_hostname = hostname;
	}
	
	public int getPort() {
		return _port;
	}
	
	public String getHostname() {
		return _hostname;
	}
	
}
