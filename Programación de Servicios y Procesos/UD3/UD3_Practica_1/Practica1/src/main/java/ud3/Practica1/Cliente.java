package ud3.Practica1;

import java.net.*;

public class Cliente {

	public static void main(String[] args) {

		try {
			// Mensaxe de saúdo
			String mensaje = "Enviando saúdos ao servidor";

			// Porto local para socketCliente
			int puertoLocal = 34567;

			// Creamos DatagramSocket cliente
			DatagramSocket clienteSocket = new DatagramSocket(puertoLocal);

			// Porto destino ao que se enviará o datagrama
			int puertoDestino = 12345;

			// Dirección servidor
			InetAddress dirServ = InetAddress.getLocalHost();

			// Convertir mensaxe a bytes
			byte[] datos = mensaje.getBytes();

			// Creamos DatagramPacket para enviar
			DatagramPacket paquete = new DatagramPacket(datos, datos.length, dirServ, puertoDestino);

			System.out.println("Lonxitude mensaxe: " + mensaje.length());
			System.out.println("Host destino: " + dirServ.getHostName());
			System.out.println("IP destino: " + dirServ.getHostAddress());
			System.out.println("Porto Local Socket: " + puertoLocal);
			System.out.println("Porto Destino: " + puertoDestino);

			// Enviamos o datagrama
			clienteSocket.send(paquete);

			// Cerramos o socket
			clienteSocket.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
