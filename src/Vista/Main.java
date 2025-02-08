package Vista;

import Controlador.CorreoControlador;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        CorreoControlador controlador = new CorreoControlador();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Enviar correo");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el destinatario (@ucentral.edu.co): ");
                    String destinatario = scanner.nextLine();

                    System.out.print("Ingrese el asunto: ");
                    String asunto = scanner.nextLine();
                    System.out.print("Ingrese el mensaje: ");
                    String mensaje = scanner.nextLine();

                    System.out.print("Ingrese la ruta del archivo adjunto (deje en blanco si no desea adjuntar): ");
                    String rutaArchivo = scanner.nextLine();

                    // Validar el archivo adjunto
                    if (rutaArchivo != null && !rutaArchivo.isEmpty()) {
                        File archivo = new File(rutaArchivo);

                        // Verificar el tamaño del archivo (no mayor a 5 MB)
                        long tamanoArchivo = archivo.length();
                        if (tamanoArchivo > 5 * 1024 * 1024) { // 5 MB en bytes
                            System.out.println("Error: El archivo no puede ser mayor a 5 MB.");
                            continue; // Volver al menú
                        }

                        // Verificar la extensión del archivo
                        String nombreArchivo = archivo.getName();
                        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();
                        if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("pdf")) {
                            System.out.println("Error: Solo se permiten archivos con extensiones PNG, JPG y PDF.");
                            continue; // Volver al menú
                        }
                    }

                    // Enviamos los datos al controlador
                    controlador.enviarCorreo(destinatario, asunto, mensaje, rutaArchivo);
                    break;
                case 2:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 2);

        scanner.close();
    }
}