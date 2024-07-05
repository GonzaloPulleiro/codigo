package Tarefa3;

import java.io.*;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Tarefa3 {

	private final static String CONTAUSUARIO = "XXX";
	private final static String PASSWORD = "XXX";

	final static String ASUNTO = "Este es el asunto. TAREA UD 5_3.";
	final static String CUERPO = "<h1>Este es el cuerpo del mensaje</h1>que envío desde mi programa <b>Java</b>, para la tarea 3 de la Unidad 5 de PSPS"
			+ "<p>Incluimos en esta prueba de correo una imagen incrustada en el cuerpo del mensaje</p>"
			+ "<img src=\"cid:imagen\" alt=\"imagen\">";

	public static void main(String[] args) {
		// Establecemos propiedades para construir sesión con servidor. Leemos del smpt.properties
		Properties propiedades = new Properties();

		try {
			propiedades.load(new FileInputStream("smtp.properties"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		// Abrimos sesión
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(CONTAUSUARIO, PASSWORD);
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
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("XXX@yopmail.com, XXX@yopmail.com", false));

			// A quién ponemos en copia del correo
			message.setRecipients(Message.RecipientType.CC,
					InternetAddress.parse("XXX@hotmail.com", false));

			// Asunto del correo
			message.setSubject(ASUNTO);

			// Creamos el cuerpo del mensaje
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(CUERPO, "text/html");

			// Creamos multipart y añadimos el cuerpo del mensaje
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Creamos un nuevo mimeBodyPart para añadir la imagen al correo y adjuntarla.
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource("imagen.jpg");
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setHeader("Content-ID", "<imagen>");
			messageBodyPart.setFileName("imagen.jpg");
			multipart.addBodyPart(messageBodyPart);

			// Enviar partes como mensaje completo
			message.setContent(multipart);

			// Envía mensaje
			Transport.send(message);

			// Imprime mensaje de confirmación por consola
			System.out.println("Correo enviado exitosamente");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
