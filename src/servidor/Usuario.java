package servidor;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String _id;
	private String _hostname;
	private List<String> _ficheros;
	
	public Usuario(String id, String hostname) {
		_id = id;
		_hostname = hostname;
		_ficheros = new ArrayList<>();
	}
	
	public boolean buscarFichero(String fichero) {
		return _ficheros.contains(fichero);
	}
	
	public boolean addFichero(String fichero) {
		return _ficheros.add(fichero);
	}
	
	public boolean eliminarFichero(String fichero) {
		return _ficheros.remove(fichero);
	}
	
	public String getId() {
		return _id;
	}
	
	public String getHostname() {
		return _hostname;
	}
	
	public List<String> getFicheros() {
		return _ficheros;
	}
}
