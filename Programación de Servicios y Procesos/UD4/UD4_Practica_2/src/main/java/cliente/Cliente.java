package cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	private static Socket cliente;
	private static String HOST = "localhost";
	private static int PORTO = 1234;

	public static void main(String[] args) {

		try {
			cliente = new Socket(HOST, PORTO);
			System.out.println("Conexión exitosa");

			// Entrada ao servidor
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

			// Saida ao servidor
			PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);

			// teclado
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			// mensaxe do cliente e do servidor
			String msxSaidaCliente, cadea;
			while (true) {
				// enviamos mensaxe do cliente
				msxSaidaCliente = br.readLine();
				saida.println(msxSaidaCliente);
				// se a mensaxe é sair() finalizamos
				if (msxSaidaCliente.equals("sair()")) {
					break;
				}
				// lemos e imprimos por pantalla a mensaxe do servidor
				cadea = entrada.readLine();
				System.out.println("Servidor: " + cadea);
				// se a mensaxe é sair() finalizamos
				if (cadea.equals("sair()")) {
					System.out.println("Fin do intercambio");
					break;
				}
			}

					// pechamos o porto
					cliente.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
