package tareasockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
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
            // El cliente siempre está a la espera
            while(true){
                // Esperar a que un cliente llame al servidor/realice una petición
                sc = servidor.accept();
                System.out.println("Cliente conectado");

                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());
                boolean salir = true;

                // Leo el mensaje del clienteç
                while(salir){
                    String mensaje = in.readUTF();
                    if (mensaje.equalsIgnoreCase("fin")) {
                        salir = false;
                    } else {
                        System.out.println("Cliente dice: "+mensaje);
                        out.writeUTF(mensaje);
                    }
                }
                // Envía un mensaje
                out.writeUTF("Le saludo desde el servidor");
                sc.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
