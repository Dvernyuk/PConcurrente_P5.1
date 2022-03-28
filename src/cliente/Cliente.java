package cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import mensajes.cliente.AddFichero;
import mensajes.cliente.CerrarConexion;
import mensajes.cliente.Conexion;
import mensajes.cliente.EliminarFichero;
import mensajes.cliente.ListaUsuarios;
import mensajes.cliente.PedirFichero;

public class Cliente {
	private static String _id;
	private final static String _hostname = "192.168.0.11";
	private static Scanner _scanner = new Scanner(System.in);
	private static int _port;
	private static ObjectOutputStream _output;

	public static void main(String[] args) {
		if (args.length < 1) return;
		_port = Integer.parseInt(args[0]);
		
		System.out.println("Introduzca su nombre de usuario");
		_id = _scanner.nextLine();

		try (Socket cliente = new Socket(_hostname, _port)) {
			
			_output = new ObjectOutputStream(cliente.getOutputStream());
			
			(new OyenteServidor(cliente,_hostname, _output)).start();
			
			_output.writeObject(new Conexion(_id,_hostname));
			
			System.out.println("1 -> Lista usuario/fichero");
			System.out.println("2 -> Pedir fichero");
			System.out.println("3 -> Añadir fichero");
			System.out.println("4 -> Eliminar fichero");
			System.out.println("0 -> Salir");
			
			int n;
			String fichero;
			while(true) {
				n = _scanner.nextInt();
				
				switch(n) {
					case 1:
						_output.writeObject(new ListaUsuarios(_id));
						
						break;
						
					case 2:
						System.out.println("Introduzca el nombre de fichero a pedir");
						_scanner.nextLine();
						fichero = _scanner.nextLine();
						_output.writeObject(new PedirFichero(fichero, _id));
						
						break;
						
					case 3:
						System.out.println("Introduzca el nombre de fichero a añadir");
						_scanner.nextLine();
						fichero = _scanner.nextLine();
						_output.writeObject(new AddFichero(fichero, _id));
						
						break;
						
					case 4:
						System.out.println("Introduzca el nombre de fichero a eliminar");
						_scanner.nextLine();
						fichero = _scanner.nextLine();
						_output.writeObject(new EliminarFichero(fichero, _id));
						
						break;
						
					case 0:
						_output.writeObject(new CerrarConexion(_id));
						while(!cliente.isClosed()) {}
						return;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
