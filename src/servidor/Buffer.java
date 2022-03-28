package servidor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mensajes.servidor.EmitirFichero;
import mensajes.servidor.PreparadoServidor_Cliente;

public class Buffer {
	private List<UsuarioCliente> _usuariosCliente = new ArrayList<UsuarioCliente>();
	private List<Usuario> _usuarios = new ArrayList<Usuario>();
	
	synchronized public void addUsuario(Usuario nu, UsuarioCliente nuc) {
		_usuarios.add(nu);
		_usuariosCliente.add(nuc);
	}
	
	synchronized public String getlista() {
		String lista = "";
		
		for(Usuario u: _usuarios) {
			lista = lista + u.getId() + ": ";
			for(String s: u.getFicheros()) {
				lista = lista + s + " | "; 
			}
			lista += "\n";
		}
		
		return lista;
	}
	
	synchronized public boolean cerrarConexion(String origen) {
		boolean end = false; int i = 0;
		
		while(i < _usuarios.size() && !end) {
			if(_usuarios.get(i).getId() == origen) {
				end = true;
				_usuarios.remove(i);
				_usuariosCliente.remove(i);
			}	
			else i++;
		}
		
		return end;
	}
	
	synchronized public boolean addFichero(String origen, String fichero) {
		int i = 0;
		boolean end = false, ret = false;
			
		while(i < _usuarios.size() &&  !end) {
			if(_usuarios.get(i).getId().equals(origen)) {
				end = true;
				if(_usuarios.get(i).addFichero(fichero)) {
					ret = true;
				}
			}
			else i++;
		}
		
		return ret;
	}
	
	synchronized public boolean eliminarFichero(String origen, String fichero) {
		int i = 0;
		boolean end = false, ret = false;
			
		while(i < _usuarios.size() &&  !end) {
			if(_usuarios.get(i).getId().equals(origen)) {
				end = true;
				if(_usuarios.get(i).eliminarFichero(fichero)) {
					ret = true;
				}
			}
			else i++;
		}
		
		return ret;
	}
	
	synchronized public void pedirFichero(String origen, String fichero) throws IOException {
		boolean end = false; int i = 0;
			
		while(i < _usuarios.size() && !end) {
			if(_usuarios.get(i).buscarFichero(fichero) && !(_usuarios.get(i).getId() == origen)) {
				end = true;
				_usuariosCliente.get(i).getOutput().writeObject(new EmitirFichero(fichero, origen, _usuarios.get(i).getId()));
			}	
			else i++;
		}
	}
	
	synchronized public void preparadoClienteServidor(int port, String host, String origen, String destino) throws IOException {
		boolean end = false; int i = 0;

		while(i < _usuarios.size() && !end) {
			if(_usuarios.get(i).getId().equals(destino)) {
				end = true;
				_usuariosCliente.get(i).getOutput().writeObject(new PreparadoServidor_Cliente(port, host, origen, destino));
			}	
			else i++;
		}
	}

}
