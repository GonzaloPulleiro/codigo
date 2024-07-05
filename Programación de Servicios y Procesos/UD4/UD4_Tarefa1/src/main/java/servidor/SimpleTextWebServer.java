package servidor;

import java.io.*;
import java.net.*;

import cliente.ClientHandler;

public class SimpleTextWebServer {

	public static void main(String[] args) {
		ServerSocket servidor = null;
		Socket socket;

		try {
			// creamos servidor que escoita no porto 8080
			servidor = new ServerSocket(8080);
			System.out.println("Servidor esperando cliente...");
			// conectase o cliente e crea fíos
			while (true) {
				socket = servidor.accept();
				System.out.println("Cliente conectado...");

				Thread hiloCliente = new Thread(new ClientHandler(socket));
				hiloCliente.start();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
