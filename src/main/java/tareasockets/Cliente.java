package tareasockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    static void main(String[] args) {

        // Host del servidor
        final String HOST = "localhost";

        // Puerto del servidor
        final int PORT = 5000;

        // Para leer y enviar datos
        DataInputStream in = null;
        DataOutputStream out = null;

        Scanner teclado = new Scanner(System.in);

        try {
            Socket socket = new Socket(HOST, PORT);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            boolean salir = false;
            String envio;

            while(!salir){
                System.out.println("Escribe un mensaje: ");
                envio = teclado.nextLine();

                if (envio.equalsIgnoreCase("fin")) {
                    salir = true;
                    socket.close();
                } else {
                    out.writeUTF(envio);
                }
            }

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
