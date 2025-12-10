import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    static void main(String[] args) {

        // Host del servidor
        final String HOST = "localhost";

        // Puerto del servidor
        final int PORT = 5000;

        // Para leer y enviar datos
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            Socket socket = new Socket(HOST, PORT);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            // Envíamos una petición al servidor
            out.writeUTF("Hola soy el cliente1");

            // Recibimos la respuesta del servidor
            String mensaje = in.readUTF();
            System.out.println(mensaje);

            // Cerramos socket
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
