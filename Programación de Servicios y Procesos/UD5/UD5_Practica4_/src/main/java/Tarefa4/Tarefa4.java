package Tarefa4;

import javax.mail.*;

import java.io.IOException;
import java.util.*;

public class Tarefa4 {


	private final static String HOST = "outlook.office365.com";
	private final static String USUARIO = "XXX@outlook.com";
	private final static String PASSWORD = "****";

	public static void main(String[] args) {

		// Establecemos propiedades para construir sesión
		Properties propiedades = new Properties();
		propiedades.setProperty("mail.store.protocol", "imaps");
		propiedades.setProperty("mail.imaps.host", HOST);
		propiedades.setProperty("mail.imaps.port", "993");

		// Crear sesion
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USUARIO, PASSWORD);
			}
		};

		Session session = Session.getDefaultInstance(propiedades, auth);
		try {
			// Conectar al servidor IMAP
			Store store = session.getStore();
			store.connect();

			// Accedemos a carpeta de entrada
			Folder entrada = store.getFolder("INBOX");
			entrada.open(Folder.READ_ONLY);

			// Sacamos el total de mensajes de la bandeja de entrada
			Message[] mensajes = entrada.getMessages();
			System.out.println("Total mensajes: " + mensajes.length);
			System.out.println("-------------------------------------------------");

			// Recorremos los mensajes y listamos los asuntos de los correos recibidos
			for (int i = 0; i < mensajes.length; i++) {
				Message msj = mensajes[i];
				System.out.println("Asunto mensaje " + i + ": " + msj.getSubject());

				// Creamos un objeto para obtener en él, el cuerpo del mensaje
				Object contenido = msj.getContent();
				// Si el contenido es un String, imprime directamente lo obtenido
				if (contenido instanceof String) {
					System.out.println("-------------------------------------------------");
					System.out.println((String) contenido);
					// Si el contenido no es un String, es un correo con formato multiparte
				} else if (contenido instanceof Multipart) {
					// Creamos un objeto Multipart del contenido
					Multipart multiparte = (Multipart) contenido;
					// Lo recorremos
					for (int j = 0; j < multiparte.getCount(); j++) {
						// Lo almacenamos en un BodyPart
						BodyPart cuerpo = multiparte.getBodyPart(j);
						// Si lo almacenado en el BodyPart es texto plano, lo imprimimos por pantalla
						if (cuerpo.isMimeType("text/plain")) {
							System.out.println("-------------------------------------------------");
							System.out.println(cuerpo.getContent()+"\n");
							System.out.println("-------------------------------------------------");

						}
					}
				}
			}
			// Cerramos la entrada y el almacenamiento de mensajes
			entrada.close(false);
			store.close();

		} catch (MessagingException | IOException e) {
			e.getMessage();
		}

	}

}
