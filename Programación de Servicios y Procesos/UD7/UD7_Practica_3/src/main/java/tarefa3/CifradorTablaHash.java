package tarefa3;

/**
 * @author Gonzalo
 */
import java.util.HashMap;

public class CifradorTablaHash {

	//número de posicións que utilizaremos para o cifrado
	private final Integer cifrado = 1;
	private HashMap<Character, Character> taboaHash;

	public CifradorTablaHash(HashMap<Character, Character> taboaPersonalizada) {

		//Se a nosa táboa está vacía
		if (taboaPersonalizada.isEmpty()) {
			
			//creamos un novo HashMap<>()
			this.taboaHash = new HashMap<>();
			//mediante un bucle for pasamos os caracteres ASCII á táboa
			for (int i = 0; i < 256; i++) {
				//obtemos o caracter correspondente a cada iteración do bucle
				char c = (char) i;
				//obtemos o caracter codificado
				char cc = (char) ((i + cifrado) % 256);
				//pasamos ambos caracteres á nosa táboa
				taboaHash.put(c, cc);
			}

			//se a nosa táboa non está vacía
		} else {
			this.taboaHash = taboaPersonalizada;
		}
	}

	public String cifrar(String mensaxe) {
			
		//xeramos un StringBuilder para traballar con el
		StringBuilder mensaxeCifrado = new StringBuilder();
		//recorremos os caracteres da mensaxe pasada por parámetro
		for (char c : mensaxe.toCharArray()) {
			//se a táboa contén o valor do carácter da iteracción
			if (taboaHash.containsValue(c)) {
				//obtén o carácter da nosa táboa para pasarllo ó StringBuilder
				mensaxeCifrado.append(taboaHash.get(c));
				//se a táboa NON contén o valor
			} else {
				//pasaselle ó StringBuilder o carácter sen máis
				mensaxeCifrado.append(c);
			}
		}
		//devolvemos a mensaxe cifrada 
		return mensaxeCifrado.toString();
	}

	public String descifrar(String mensaxeCifrado) {

		//xeramos un StringBuilder para traballar con el
		StringBuilder mensaxeDescifrado = new StringBuilder();
		//recorremos os caracteres da mensaxe pasada por parámetro
		for (char c : mensaxeCifrado.toCharArray()) {
			//recorremos agora as chaves da nosa táboa, non os valores como no caso do cifrado
			for (char chave : taboaHash.keySet()) {
				//se a chave da táboa é igual ao carácter do bucle anterior
				if (taboaHash.get(chave) == c) {
					//pasalle esta chave ó StringBuilder
					mensaxeDescifrado.append(chave);
					//break;
				}
			}
		}
		//devolvemos a mensaxe descifrada
		return mensaxeDescifrado.toString();
	}

	public static void main(String[] args) {

		//creamos unha táboa vacía
		HashMap<Character, Character> taboaHash = new HashMap<>();
		//chamamos ao constructor coa nosa táboa vacía
		CifradorTablaHash cifrador = new CifradorTablaHash(taboaHash);

		//escribimos dúas frases de exemplo
		String mensaxe = "Esta é a tarefa3 de PSP 7";
		String mensaxe2 = "Hola mundo";
		//mediante cifrador, ciframos ambas mensaxes
		String mensaxeCifrada = cifrador.cifrar(mensaxe);
		String mensaxeCifrada2 = cifrador.cifrar(mensaxe2);

		//imprimimolas por pantalla
		System.out.println("Mensaxe cifrada: " + mensaxeCifrada);
		System.out.println("----------------------------------------------");
		System.out.println("Mensaxe cifrada2: " + mensaxeCifrada2);
		System.out.println("----------------------------------------------");
		
		//Descriframos ambas mensaxes
		String mensaxeDescifrada = cifrador.descifrar(mensaxeCifrada);
		String mensaxeDescifrada2 = cifrador.descifrar(mensaxeCifrada2);

		//imprimimolas por pantalla
		System.out.println("Mensaxe descifrada: " + mensaxeDescifrada);
		System.out.println("----------------------------------------------");
		System.out.println("Mensaxe descifrada2: " + mensaxeDescifrada2);
		System.out.println("----------------------------------------------");

	}
}
