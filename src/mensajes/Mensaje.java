package mensajes;

import java.io.Serializable;

public abstract class Mensaje implements Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7377893795932427613L;
	private int _tipo;
	private String _origen;
	private String _destino;
	
	public static final int Conexion = 0;
	public static final int ConfirmacionConexion = 1;
	
	public static final int ListaUsuarios = 2;
	public static final int ConfirmacionListaUsuarios = 3;
	
	public static final int CerrarConexion = 4;
	public static final int ConfirmacionCerrarConexion = 5;
	
	public static final int PedirFichero = 6;
	public static final int EmitirFichero = 7;
	
	public static final int PreparadoCliente_Servidor = 8;
	public static final int PreparadoServidor_Cliente = 9;
	
	public static final int AddFichero = 10;
	public static final int ConfirmacionAddFichero = 11;
	
	public static final int EliminarFichero = 12;
	public static final int ConfirmacionEliminarFichero = 13;
	
	public static final int Fichero = 14;
	
	public Mensaje(int tipo, String origen, String destino) {
		_tipo = tipo;
		_origen = origen;
		_destino = destino;
	}
	
	public int getTipo() {
		return _tipo;
	}
	
	public String getOrigen() {
		return _origen;
	}
	
	public String getDestino() {
		return _destino;
	}

}
