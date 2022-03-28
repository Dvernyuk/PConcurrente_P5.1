package mensajes.servidor;

import mensajes.Mensaje;

public class ConfirmacionListaUsuarios extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5053163823773028066L;
	private String _lista;
	
	public ConfirmacionListaUsuarios(String lista, String destino) {
		super(Mensaje.ConfirmacionListaUsuarios, null, destino);
		_lista = lista;
	}

	public String toString() {
		return _lista;
	}
}
