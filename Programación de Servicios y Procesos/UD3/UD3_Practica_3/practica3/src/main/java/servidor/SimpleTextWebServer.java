package servidor;

import java.io.*;
import java.net.*;

import cliente.ClientHandler;

public class SimpleTextWebServer {

	public static void main(String[] args) {
		try {
			// servidor web simulado na porta 8080
			ServerSocket serverSocket = new ServerSocket(8080);
		
			while (true) {
				// creamos un fio cada vez que o servidor recibe unha conexion
				Socket clienteSocket = serverSocket.accept();
				Thread hilo = new Thread(new ClientHandler(clienteSocket));
				hilo.start();
				serverSocket.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
