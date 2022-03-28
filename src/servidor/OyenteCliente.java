package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import mensajes.Mensaje;
import mensajes.cliente.AddFichero;
import mensajes.cliente.CerrarConexion;
import mensajes.cliente.Conexion;
import mensajes.cliente.EliminarFichero;
import mensajes.cliente.PedirFichero;
import mensajes.cliente.PreparadoCliente_Servidor;
import mensajes.servidor.ConfirmacionAddFichero;
import mensajes.servidor.ConfirmacionCerrarConexion;
import mensajes.servidor.ConfirmacionConexion;
import mensajes.servidor.ConfirmacionEliminarFichero;
import mensajes.servidor.ConfirmacionListaUsuarios;

public class OyenteCliente extends Thread {
	private Socket _cliente;
	private Monitor _monitor;
	private Buffer _buffer;
	
	private ObjectInputStream _input;
	private ObjectOutputStream _output;
	
	public OyenteCliente(Socket cliente, Monitor monitor, Buffer buffer){
		_cliente = cliente;
		_monitor = monitor;
		_buffer = buffer;
		
		_input = null;
		_output = null;
	}
	
	public void run() {
		
		try {
			_input = new ObjectInputStream(_cliente.getInputStream());
			
			while(true) {
				Mensaje m = (Mensaje) _input.readObject();
				boolean done; // variable auxiliar
				
				switch(m.getTipo()) {
					case Mensaje.Conexion:
						//* guardar informacion de usuario en las tablas
						//* enviar mensaje confirmacion conexion
						Conexion mc = (Conexion) m;
						
						Usuario nu = new Usuario(mc.getOrigen(),mc.getHostname());
						
						_output = new ObjectOutputStream(_cliente.getOutputStream());
						UsuarioCliente nuc = new UsuarioCliente(mc.getOrigen(),_input,_output);
						
						_monitor.P();
						_buffer.addUsuario(nu, nuc);
						_monitor.V();
						
						_output.writeObject(new ConfirmacionConexion(mc.getOrigen()));
						
						break;
						
					case Mensaje.ListaUsuarios:
						//* crear un mensaje con toda la informacion de usuarios en sistema (tabla de informacion)
						//* enviar ese mismo mensaje
						String lista = "";
						
						_monitor.P();
						lista = _buffer.getlista();
						_monitor.V();
						
						_output.writeObject(new ConfirmacionListaUsuarios(lista, m.getOrigen()));
						
						break;
						
					case Mensaje.CerrarConexion:
						//* eliminar informacion del usuario en las tablas
						//* enviar mensaje confirmacion de cierre de conexion
						CerrarConexion mcc = (CerrarConexion) m;
						//boolean done;
						
						_monitor.P();
						done = _buffer.cerrarConexion(mcc.getOrigen());
						_monitor.V();
						
						if(done) {
							_output.writeObject(new ConfirmacionCerrarConexion(mcc.getOrigen()));
							return;
						}
						
						break;
						
					case Mensaje.AddFichero:
						AddFichero maf = (AddFichero) m;
						//boolean done;
						
						_monitor.P();
						done = _buffer.addFichero(maf.getOrigen(), maf.getFichero());
						_monitor.V();
						
						if(done)
							_output.writeObject(new ConfirmacionAddFichero(maf.getOrigen()));
						
						break;
						
					case Mensaje.EliminarFichero:
						EliminarFichero mef = (EliminarFichero) m;
						
						_monitor.P();
						done = _buffer.eliminarFichero(mef.getOrigen(), mef.getFichero());
						_monitor.V();
						
						if(done)
							_output.writeObject(new ConfirmacionEliminarFichero(mef.getOrigen()));
						
						break;
						
					case Mensaje.PedirFichero:
						//* buscar usuario que tiene ese fichero y obtener su output
						//* enviar mensaje EMITIR_FICHERO a ese usuario para empezar la comunicacion desde el servidor al cliente
						PedirFichero mpf = (PedirFichero) m;
						
						_monitor.P();
						_buffer.pedirFichero(mpf.getOrigen(), mpf.getFichero());
						_monitor.V();
						
						break;
					
					case Mensaje.PreparadoCliente_Servidor:
						//* buscar el output del cliente al que hay que enviar el fichero
						//* envio mensaje PREPARADO_SERVIDOR-CLIENTE por el mismo output
						PreparadoCliente_Servidor mpcs = (PreparadoCliente_Servidor) m;
						
						_monitor.P();
						_buffer.preparadoClienteServidor(mpcs.getPort(), mpcs.getHostname(), mpcs.getOrigen(), mpcs.getDestino());
						_monitor.V();
						
						break;
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
		
	}
	
}
