package Modelo;

public class Correo {
    private String destinatario;
    private String remitente;
    private String asunto;
    private String mensaje;

    public Correo(String destinatario, String remitente, String asunto, String mensaje) {
        this.destinatario = destinatario;
        this.remitente = remitente;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getMensaje() {
        return mensaje;
    }
}
