package Exercicio2;

/**
 * @author Gonzalo
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PublicKey;

import Exercicio2.*;

public class ManejoCliente implements Runnable {

	private Socket cliente;
	private GestorCifradorRSA gestor;

	public ManejoCliente(Socket socket, GestorCifradorRSA gestor) {
		this.cliente = socket;
		this.gestor = gestor;
	}

	@Override
	public void run() {

		try {
			// Flujos entrada e saida
			ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());

			// Enviamos chave publica do Servidor ao cliente
			out.writeObject(gestor.getPublica());

			// Enviamos mensaxe cifrada ao cliente
			byte[] mensaxeCifrada = gestor.cifrar("Fala amigo e entra".getBytes(), (PublicKey) in.readObject());
			out.writeObject(mensaxeCifrada);

			// Recibimos resposta cliente e desciframos
			byte[] respostaCifrada = (byte[]) in.readObject();
			byte[] respostaDescifrada = gestor.descifrar(respostaCifrada, gestor.getPrivada());
			String resposta = new String(respostaDescifrada);
			System.out.println("Resposta cliente: " + resposta);
			System.out.println("--------------------------------------");

			// Verificamos resposta
			if (resposta.equals("Mellon")) {
				out.writeObject("As portas de Moria están abertas para ti, amigo");
			}

			// Pechamos conexions
			out.close();
			in.close();
			cliente.close();

		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
