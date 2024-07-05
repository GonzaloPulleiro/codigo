package Exercicio2;

/**
 * @author Gonzalo
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
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

import Exercicio2.*;

public class Servidor {

	final static int PORTO = 8080;

	public static void main(String[] args) {

		GestorCifradorRSA gestor = new GestorCifradorRSA();

		try {
			// Xeramos o par de chaves ao iniciar
			KeyPairGenerator xeradorChave = KeyPairGenerator.getInstance("RSA");
			xeradorChave.initialize(1024);
			gestor.claves = xeradorChave.generateKeyPair();
			gestor.cifrador = Cipher.getInstance("RSA");

			// iniciamos servidor e conexión co cliente
			ServerSocket servidor = new ServerSocket(PORTO);
			System.out.println("Esperando conexión no porto " + servidor.getLocalPort());

			while (true) {
				Socket cliente = servidor.accept();
				System.out.println("Conexión exitosa");
				System.out.println("--------------------------------------");
				
				new Thread(new ManejoCliente(cliente, gestor)).start();
			}

		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
