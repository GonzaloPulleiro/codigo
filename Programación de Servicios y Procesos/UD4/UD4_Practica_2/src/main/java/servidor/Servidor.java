package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

	private static ServerSocket servidor;
	private static Socket cliente;
	private static int PORTO = 1234;

	public static void main(String[] args) {

		try {
			servidor = new ServerSocket(PORTO);
			System.out.println("Esperando cliente...");

			cliente = servidor.accept();
			System.out.println("Cliente conectado");
			System.out.println("Dirección IP cliente: " + cliente.getInetAddress());

			// Entrada do cliente
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

			// Saida ao cliente
			PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);

			// teclado
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			//mensaxe do cliente e do servidor
			String cadea, msxSaidaServidor;
			while ((cadea = entrada.readLine()) != null) {
				//sacamos por pantalla a mensaxe do cliente
				System.out.println("Cliente: " + cadea);
				//escribimos e enviamos a mensaxe do servidor
				msxSaidaServidor = br.readLine();
				saida.println(msxSaidaServidor);
				//cando se introduza sair() finalizamos
				if (msxSaidaServidor.equals("sair()")) {
					break;
				}
			}
					//pechamos os portos
					servidor.close();
					cliente.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
