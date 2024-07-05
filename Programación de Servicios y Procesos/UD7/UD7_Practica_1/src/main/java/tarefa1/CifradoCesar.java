package tarefa1;

/**
 * @author Gonzalo
 * 
 */
import java.util.Scanner;

public class CifradoCesar {

	private static String alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789";
	private String alfabetoCifrado;

	
	public static String cifrar(String mensaje, String clave) {

		String textoCifrado = "";

		mensaje = mensaje.toUpperCase();

		int chave = Integer.parseInt(clave);

		char caracter;
		for (int i = 0; i < mensaje.length(); i++) {
			caracter = mensaje.charAt(i);

			int pos = alfabeto.indexOf(caracter);

			if (pos == -1) {
				textoCifrado += caracter;
			} else {
				textoCifrado += alfabeto.charAt((pos + chave) % alfabeto.length());
			}
		}
		return textoCifrado;
	}

	public static String descifrar(String mensajeCifrado, String clave) {

		String textoDescifrado = "";

		mensajeCifrado = mensajeCifrado.toUpperCase();

		int chave = Integer.parseInt(clave);

		char caracter;
		for (int i = 0; i < mensajeCifrado.length(); i++) {
			caracter = mensajeCifrado.charAt(i);

			int pos = alfabeto.indexOf(caracter);

			if (pos == -1) {
				textoDescifrado += caracter;
			} else {
				if (pos - chave < 0) {
					textoDescifrado += alfabeto.charAt(alfabeto.length() + (pos - chave));

				} else {
					textoDescifrado += alfabeto.charAt((pos - chave) % alfabeto.length());
				}

			}
		}

		return textoDescifrado;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");

		System.out.println("Introduce frase a codificar: ");
		String frase = sc.next();

		String mensaje = cifrar(frase, "1");
		System.out.println("Mensaje cifrado: " + mensaje);

		mensaje = descifrar(mensaje, "1");
		System.out.println("Mensaje descifrado: " + mensaje);
		
	}
}