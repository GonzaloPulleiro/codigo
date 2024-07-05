package tarefa1;

/**
 * @author Gonzalo
 * 
 */
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


public class GestorCifradorRSA {
	static KeyPair claves;
	static KeyPairGenerator generadorClaves;
	static Cipher cifrador;

	public void GestorCifrado() throws NoSuchAlgorithmException, NoSuchPaddingException {

	}

	public PublicKey getPublica() {

		PublicKey publica = claves.getPublic();
		return publica;
	}

	public PrivateKey getPrivada() {

		PrivateKey privada = claves.getPrivate();
		return privada;
	}

	// o método cifrar tomará unha mensaxe dada e a chave pública como entrada, 
	// devolvendo a mensaxe cifrada.
	
	public static byte[] cifrar(byte[] paraCifrar, Key claveCifrado)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
		cifrador.init(Cipher.ENCRYPT_MODE, claveCifrado);

		byte[] encriptado = cifrador.doFinal(paraCifrar);
		
		return encriptado;
	}

	// o método descifrar tomará a mensaxe cifrada e a chave privada como entrada, 
	// devolvendo a mensaxe orixinal.
	
	public static byte[] descifrar(byte[] paraDescifrar, Key claveDescifrado)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{

		cifrador.init(Cipher.DECRYPT_MODE, claveDescifrado);
		byte[] desencriptado = cifrador.doFinal(paraDescifrar);

		return desencriptado;

	}

	public static void main(String[] args) throws Exception {
		
		cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		generadorClaves = KeyPairGenerator.getInstance("RSA");
		generadorClaves.initialize(1024);
		claves = generadorClaves.generateKeyPair();
		
		GestorCifradorRSA gestor = new GestorCifradorRSA();
		
		String texto = "Este é o Exercicio2 da Tarefa1 de PSP 7";
				
		byte[] mensaxe = texto.getBytes();
		
		byte[] mensaxeCifrada = gestor.cifrar(mensaxe, gestor.getPublica());
		
		System.out.println("Cifrado: " + mensaxeCifrada );
		
		byte[] mensaxeDescifrada = gestor.descifrar(mensaxeCifrada, gestor.getPrivada());
		
		String descifrada = new String(mensaxeDescifrada);
		
		System.out.println("Descrifrado: " + descifrada);
	
	}
}
