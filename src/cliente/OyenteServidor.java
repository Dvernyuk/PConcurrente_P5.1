package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import mensajes.Fichero;
import mensajes.Mensaje;
import mensajes.cliente.AddFichero;
import mensajes.cliente.PreparadoCliente_Servidor;
import mensajes.servidor.ConfirmacionListaUsuarios;
import mensajes.servidor.EmitirFichero;
import mensajes.servidor.PreparadoServidor_Cliente;

public class OyenteServidor extends Thread {
	private Socket _cliente;
	private String _hostname;
	private ObjectInputStream _input;
	private ObjectOutputStream _output;
	
	public OyenteServidor(Socket cliente, String hostname, ObjectOutputStream output){
		_cliente = cliente;
		_hostname = hostname;
		_output = output;
	}
	
	
	public void run() {
		
		try {
			_input = new ObjectInputStream(_cliente.getInputStream());
			
			while(true) {
				Mensaje m = (Mensaje) _input.readObject();
				
				switch(m.getTipo()) {
					case Mensaje.ConfirmacionConexion:
						//* imprimir conexion establecida por standard output
						System.out.println("Conexion establecida");
						
						break;
						
					case Mensaje.ConfirmacionListaUsuarios:
						//* imprimir lista usuarios por standard output
						ConfirmacionListaUsuarios mclu = (ConfirmacionListaUsuarios) m;

						System.out.println(mclu);
						
						break;
						
					case Mensaje.ConfirmacionAddFichero:
						System.out.println("Se ha añadido el fichero");
						
						break;
						
					case Mensaje.ConfirmacionEliminarFichero:
						System.out.println("Se ha eliminado el fichero");
						
						break;
						
					case Mensaje.EmitirFichero:
						//* (nos llega nombre de cliente C1 e informacion pedida 3)
						//* enviar MENSAJE_PREPARADO_CLIENTESERVIDOR a mi oyente
						//* crear proceso EMISOR y esperar en accept la conexion
						EmitirFichero mef = (EmitirFichero) m;
						
						int port = (int) Math.random()*(6000-1000+1)+1000;
						(new Emisor(port, mef)).start();
						_output.writeObject(new PreparadoCliente_Servidor(port
								, _hostname, mef.getDestino(), mef.getOrigen()));
						
						break;
						
					case Mensaje.PreparadoServidor_Cliente:
						//* (llega direccion Ip y puerto del propietario de fichero)
						//* crear proceso RECEPTOR
						PreparadoServidor_Cliente mpsc = (PreparadoServidor_Cliente) m;
						
						(new Receptor(mpsc.getPort(), mpsc.getHostname())).start();
						
						break;
						
					case Mensaje.ConfirmacionCerrarConexion:
						//* imprimir adios por standard output					
						System.out.println("Adios");
						_cliente.close();
						
						return;
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
		
	}
	
	class Emisor extends Thread {
		private int _port;
		private EmitirFichero _mef;
		
		public Emisor(int port, EmitirFichero mef) {
			_port = port;
			_mef = mef;
		}
		
		public void run() {
			try (ServerSocket server = new ServerSocket(_port)) {
				
				Socket cliente = server.accept();
				
				ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
				output.writeObject(new Fichero(_mef.getFichero(), _mef.getDestino(), _mef.getOrigen()));
				
				/*while(server.isBound()) {}
				server.close();*/
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	class Receptor extends Thread {
		private int _port;
		private String _serverHostname;
		
		public Receptor(int port, String hostname) {
			_port = port;
			_serverHostname = hostname;
		}
		
		public void run() {
			try (Socket cliente = new Socket(_serverHostname,_port)) {

				ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());
				Fichero fichero = (Fichero) input.readObject();
				_output.writeObject(new AddFichero(fichero.getFichero(), fichero.getDestino()));
				
				//cliente.close();
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
