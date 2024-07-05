package Tarefa2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Tarefa2 {

	private final static String CONTAUSUARIO = "XXXX";
	private final static String PASSWORD = "XXXX";

	final static String ASUNTO = "Este es el asunto. TAREA UD 5_2.";
	final static String CUERPO = "Este es el cuerpo del mensaje que envío desde mi programa Java, para la tarea 2 de la Unidad 5 de PSPS";

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
					InternetAddress.parse("XXX@yopmail.com, XXXX@yopmail.com", false));

			// Asunto del correo
			message.setSubject(ASUNTO);

			// Creamos el cuerpo del mensaje
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(CUERPO);

			// Creamos el anexo
			MimeBodyPart adjuntoPart = new MimeBodyPart();
			String archivo = "config.txt";
			DataSource source = new FileDataSource(archivo);
			adjuntoPart.setDataHandler(new DataHandler(source));
			adjuntoPart.setFileName("config.txt");

			// Combinamos cuerpo con anexo
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(adjuntoPart);

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
