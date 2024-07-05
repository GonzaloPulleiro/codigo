package Exercicio1;

/**
 * @author Gonzalo
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.net.ssl.SSLServerSocketFactory;

public class Amigo1 {

	public static void main(String[] args) {

		String servidorIP = "localhost";
		int puerto = 12345;
		// Clave para o cifrado
		String key = "1";

		try {
			// Conexión co servidor
			Socket socket = new Socket(servidorIP, puerto);
			System.out.println("Conectado");

			
			System.out.println("-------------------------------------");
			System.out.println("-------------------------------------");
			
			

			// Crear entrada e saida
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			// Enviamos mensaxe
			String envio = "Ola Amigo 2";
			System.out.println("Mensaxe enviada DESCIFRADA: " + envio);
			String envioCifrado = CifradoCesar.cifrar(envio, key);
			out.println(envioCifrado);
			System.out.println("Mensaxe enviada CIFRADA: " + envioCifrado);

			
			System.out.println("-------------------------------------");
			System.out.println("-------------------------------------");
			
			
			// Recepción mensaxe
			String recibido = in.readLine();
			System.out.println("Mensaxe recibida CIFRADA: " + recibido);
			recibido = CifradoCesar.descifrar(recibido, key);
			System.out.println("Mensaxe recibida DESCIFRADA: " + recibido);

			// Pechamos conexion
			socket.close();

		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

}