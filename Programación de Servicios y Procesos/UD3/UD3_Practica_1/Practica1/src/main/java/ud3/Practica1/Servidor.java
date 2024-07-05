package ud3.Practica1;

import java.net.*;

public class Servidor {

	public static void main(String[] args) {

		try {
			// Asociar socket Servidor a porto 12345
			int portoServidor = 12345;
			DatagramSocket socketServ = new DatagramSocket(portoServidor);

			// Espera ata que recibe un datagrama enviado polo cliente
			byte[] buffer = new byte[1024];
			DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
			socketServ.receive(paquete);

			// Imprime info relevante sobre datagrama recibido
			System.out.println("Número de bytes recibidos: " + paquete.getLength());
			System.out.println("Contido do paquete: " + new String(paquete.getData(), 0, paquete.getLength()));
			System.out.println("Porto orixe mensaxe: " + paquete.getPort());
			System.out.println("IP de orixe: " + paquete.getAddress().getHostAddress());
			System.out.println("Porto de destino da mensaxe: " + portoServidor);

			// Cerramos o socket
			socketServ.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
