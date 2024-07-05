package cliente;

import java.io.IOException;
import java.net.*;

public class MainProgram {

	public static void main(String[] args) {

		// crear proceso servidor
		try {
			ServerSocket servidor = new ServerSocket(8080);
			// ejecutar
			while (true) {
				Socket socket = servidor.accept();
				// bucle de 10 clientes
				for (int i = 0; i < 10; i++) {
					SimpleTextWebClientTask clientTask = new SimpleTextWebClientTask(i);
					Thread cliente = new Thread(clientTask);
					cliente.start();
				}
				socket.close();
				servidor.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
