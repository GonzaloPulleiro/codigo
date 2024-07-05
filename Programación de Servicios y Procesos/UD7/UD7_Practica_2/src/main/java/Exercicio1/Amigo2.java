package Exercicio1;

/**
 * @author Gonzalo
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLServerSocketFactory;

public class Amigo2 {

	public static void main(String[] args) {

		int puerto = 12345;
		// Clave para o cifrado
		String key = "1";

		try {
			// Creamos servidor
			ServerSocket serverSocket = new ServerSocket(puerto);
			System.out.println("Esperando conexion...");

			// Conectamos
			Socket cliente = serverSocket.accept();
			System.out.println("Conexión exitosa");

			
			System.out.println("-------------------------------------");
			System.out.println("-------------------------------------");
			
			

			// Crear entrada e saida
			BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);

			// Recibimos mensaxe, sacamolo por pantalla
			String recibido = in.readLine();
			System.out.println("Mensaxe recibida CIFRADA: " + recibido);
			recibido = CifradoCesar.descifrar(recibido, key);
			System.out.println("Mensaxe recibida DESCIFRADA: " + recibido);

			
			System.out.println("-------------------------------------");
			System.out.println("-------------------------------------");
			
			
			// Enviamos mensaxe
			String envio = "Ola Amigo 1";
			System.out.println("Mensaxe enviada DESCIFRADA: " + envio);
			String mensaxeCifrada = CifradoCesar.cifrar(envio, key);
			out.println(mensaxeCifrada);
			System.out.println("Mensaxe enviada CIFRADA: " + mensaxeCifrada);

			// Pechamos conexion
			cliente.close();
			serverSocket.close();

		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

}