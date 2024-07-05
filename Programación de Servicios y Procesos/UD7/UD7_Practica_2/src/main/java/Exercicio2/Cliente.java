package Exercicio2;

/**
 * @author Gonzalo
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Cliente {

	final static String IP = "localhost";
	final static int PORTO = 8080;

	public static void main(String[] args) {

		GestorCifradorRSA gestor = new GestorCifradorRSA();

		try {
			// xeramos o par de chaves ao iniciar
			KeyPairGenerator xeradorChave = KeyPairGenerator.getInstance("RSA");
			xeradorChave.initialize(1024);
			gestor.claves = xeradorChave.generateKeyPair();
			gestor.cifrador = Cipher.getInstance("RSA");

			// Conectamos co servidor
			Socket cliente = new Socket(IP, PORTO);
			System.out.println("Conectado co servidor no porto " + cliente.getPort());
			System.out.println("--------------------------------------");

			// Flujos entrada e saida
			ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());

			// Enviamos chave pública ao servidor
			out.writeObject(gestor.getPublica());

			// Recibimos chave pública do servidor
			PublicKey publicaServidor = (PublicKey) in.readObject();

			// Recibimos e desciframos mensaxe servidor
			byte[] mensaxeCifrada = (byte[]) in.readObject();
			byte[] mensaxeDescifrada = gestor.descifrar(mensaxeCifrada, gestor.getPrivada());
			String mensaxe = new String(mensaxeDescifrada);
			System.out.println("Mensaxe do servidor: " + mensaxe);
			System.out.println("--------------------------------------");

			// Enviamos resposta cifrada ao servidor
			byte[] respostaCifrada = gestor.cifrar("Mellon".getBytes(), publicaServidor);
			out.writeObject(respostaCifrada);

			// Recibimos e mostramos a mensaxe final do servidor
			String respostaFinal = (String) in.readObject();
			System.out.println(respostaFinal);

			// Pechamos conexions
			out.close();
			in.close();
			cliente.close();

		} catch (IOException | NoSuchAlgorithmException | ClassNotFoundException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException e) {
			System.err.println(e);
		}

	}

}
