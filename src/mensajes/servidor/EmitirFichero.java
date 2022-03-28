package mensajes.servidor;

import mensajes.Mensaje;

public class EmitirFichero extends Mensaje {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5636300838132752981L;
	private String _fichero;
	
	public EmitirFichero(String fichero, String origen, String destino) {
		super(Mensaje.EmitirFichero, origen, destino);
		_fichero = fichero;
	}
	
	public String getFichero() {
		return _fichero;
	}

}
