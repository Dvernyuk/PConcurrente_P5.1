package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static int _port;
	
	public static void main (String[] args) {
		if (args.length < 1) return;	
		_port = Integer.parseInt(args[0]);
		
		try (ServerSocket server = new ServerSocket(_port)) {
			
			Monitor m = new Monitor();
			Buffer b = new Buffer();
			
			while (true) {
				
				Socket cliente = server.accept();
				(new OyenteCliente(cliente,m,b)).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
