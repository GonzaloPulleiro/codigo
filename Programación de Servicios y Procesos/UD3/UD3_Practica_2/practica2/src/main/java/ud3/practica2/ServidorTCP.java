package ud3.practica2;

import java.io.*;
import java.net.*;

public class ServidorTCP {

	public static void main(String[] args) {

		try {
			// O servidor estará escoitando no porto 12345 no cal recibirá o paquete
			ServerSocket serverSocket = new ServerSocket(12345);

			// Aceptamos conexión de cliente
			Socket socket = serverSocket.accept();

			// Obtemos o ObjectInputStream
			ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

			// Leemos o paquete recibido do cliente
			PaqueteTCP paquete = (PaqueteTCP) inStream.readObject();

			// Mostramos a información recibida no paquete
			System.out.println("Mensaxe: " + paquete.getMensaxe());
			System.out.println("Porto de Orixe: " + paquete.getPortoOrixe());
			System.out.println("Porto de Destino: " + paquete.getPortoDestino());

			// Cerramos InputStream, socket e serverSocket
			inStream.close();
			socket.close();
			serverSocket.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
