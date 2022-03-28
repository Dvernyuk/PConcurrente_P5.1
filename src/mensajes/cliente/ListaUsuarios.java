package mensajes.cliente;

import mensajes.Mensaje;

public class ListaUsuarios extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4245057039769649052L;

	public ListaUsuarios(String origen) {
		super(Mensaje.ListaUsuarios, origen, null);
	}

}
