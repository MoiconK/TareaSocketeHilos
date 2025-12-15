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

        // Flujos para leer y enviar datos
        DataInputStream in = null;
        DataOutputStream out = null;

        final int PUERTO = 5000;


        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando...");
            // El servidor siempre está a la espera
            while(true){
                // Esperar a que un cliente llame al servidor/realice una petición
                sc = servidor.accept();
                System.out.println("Cliente conectado desde: " + sc.getRemoteSocketAddress()); //Mensaje cuando un cliente se conecta
                GestorCliente gc = new GestorCliente(sc); //Se crea una instancia del socket de cliente
                new Thread(gc).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
