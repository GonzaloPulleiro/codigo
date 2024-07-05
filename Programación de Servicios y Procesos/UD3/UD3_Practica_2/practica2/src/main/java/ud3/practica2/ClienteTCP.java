package ud3.practica2;

import java.io.*;
import java.net.*;

public class ClienteTCP {

	public static void main(String[] args) {

		try {
			// Creamos un obxecto paquete coa mensaxe a enviar
			PaqueteTCP paquete = new PaqueteTCP(34567, 12345, "Enviando saúdos ao servidor");

			// Establecemos socket orixe para conectarse ao servidor
			Socket socketCliente = new Socket("localhost", 12345);

			// Enviamos o paquete ao servidor con ObjectOutputStream
			ObjectOutputStream outStream = new ObjectOutputStream(socketCliente.getOutputStream());
			outStream.writeObject(paquete);

			// Cerramos o outputstream e o socketcliente
			outStream.close();
			socketCliente.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
