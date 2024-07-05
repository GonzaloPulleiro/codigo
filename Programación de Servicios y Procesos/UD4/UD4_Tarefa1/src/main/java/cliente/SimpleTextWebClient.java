package cliente;

import java.io.*;
import java.net.*;

public class SimpleTextWebClient {
	static String HOST = "localhost";
	static int PUERTO = 8080;

	public static void main(String[] args) {
		Socket socket;

		try {
			socket = new Socket(HOST, PUERTO);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

			// Enviar solicitude HTTP GET ao servidor
			writer.println("GET / HTTP/1.1");
			writer.println("Host: localhost:8080");
			writer.println();

			// Leer e mostrar a resposta do servidor
			StringBuilder resposta = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				resposta.append(line).append("\n");
			}

			resposta.toString();
			System.out.println("Resposta do servidor:\n" + resposta);

			socket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
