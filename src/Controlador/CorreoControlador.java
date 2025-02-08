package Controlador;

import Modelo.Correo;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import java.io.File;

public class CorreoControlador {
    private final String usuario = "apuentesm2@ucentral.edu.co";
    private final String clave = "xdgu drqy bvam pilk";

    public void enviarCorreo(String destinatario, String asunto, String mensaje, String rutaArchivo) {
        if (!destinatario.endsWith("@ucentral.edu.co")) {
            System.out.println("Error: Solo se pueden enviar correos al dominio ucentral.edu.co.");
            return;
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, clave);
            }
        });
        session.setDebug(false); // Habilitar depuración

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuario));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);

            // Crear el cuerpo del mensaje
            MimeBodyPart cuerpoMensaje = new MimeBodyPart();
            cuerpoMensaje.setText(mensaje);

            // Crear el multipart para agregar el cuerpo y el archivo adjunto
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(cuerpoMensaje);

            // Adjuntar archivo si se proporciona una ruta
            if (rutaArchivo != null && !rutaArchivo.isEmpty()) {
                MimeBodyPart adjunto = new MimeBodyPart();
                DataSource source = new FileDataSource(rutaArchivo);
                adjunto.setDataHandler(new DataHandler(source));
                adjunto.setFileName(new File(rutaArchivo).getName());
                multipart.addBodyPart(adjunto);
            }

            // Establecer el contenido del mensaje
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Correo enviado con exito a " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}