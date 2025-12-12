import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static void main(String[] args) {

        // El servidor
        ServerSocket servidor = null;
        Socket sc = null;

        // Para leer y enviar datos
        DataInputStream in = null;
        DataOutputStream out = null;

        final int PUERTO = 5000;

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando...");
            boolean salir = true;

            while(!salir){

                // Esperar a que un cliente llame al servidor/realice una petición
                sc = servidor.accept();

                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                // Leo el mensaje del cliente
                String mensaje = in.readUTF();
                System.out.println(mensaje);

                if (mensaje.equals("FIN")){

                    // Envía un mensaje
                    out.writeUTF("conexión terminada");
                    salir = false;

                } else {

                    out.writeUTF(mensaje);
                }

                // Cerrar socket
                sc.close();
                System.out.println("Conexión finalizada");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
