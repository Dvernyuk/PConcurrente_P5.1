package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UsuarioCliente {
	private String _id;
	private ObjectInputStream _input;
	private ObjectOutputStream _output;
	
	public UsuarioCliente(String id, ObjectInputStream input,
			ObjectOutputStream output) {
		_id = id;
		_input = input;
		_output = output;
	}
	
	public String getId() {
		return _id;
	}
	
	public ObjectInputStream getInput() {
		return _input;
	}
	
	public ObjectOutputStream getOutput() {
		return _output;
	}
	
}
