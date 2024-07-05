package Tarefa1;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Tarefa1 {

	private final static String HOST = "localhost";
	private final static int PORTO = 25;

	private final static String CONTAUSUARIO = "XXX@outlook.com";
	private final static String CONTADESTINO = "XXX@yopmail.com";

	//Cambiar XXX por cuenta de usuario

	public static void main(String[] args) {

		// Establecemos propiedades para construir sesión con servidor
		Properties propiedades = new Properties();
		propiedades.put("mail.smtp.auth", true);
		propiedades.put("mail.smtp.host", HOST);
		propiedades.put("mail.smtp.port", PORTO);

		// Abrimos sesión
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(CONTAUSUARIO, "");
			}
		};

		Session session = Session.getInstance(propiedades, auth);

		try {
			// Mensaje
			Message message = new MimeMessage(session);
			message.addHeader("Content-typ", "text/HTML; charset=UTF-8");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setSentDate(new Date());

			// Quién lo envía
			message.setFrom(new InternetAddress(CONTAUSUARIO));

			// A quién se envía
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(CONTADESTINO, false));

			// Asunto del correo
			message.setSubject("Este es el asunto");

			// Cuerpo mensaje del correo
			message.setText(
					"Este es el cuerpo del mensaje que envío desde mi programa Java, para la tarea 1 de la Unidad 5 de PSPS");

			// Envía mensaje
			Transport.send(message);

			// Imprime mensaje de confirmación por consola
			System.out.println("Correo enviado exitosamente");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
