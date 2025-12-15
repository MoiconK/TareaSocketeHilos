package tareasockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GestorCliente implements Runnable{
    Socket socket;

    public GestorCliente(Socket socket){ //Recibe el socket desde el servidor
        this.socket=socket;
    }

    @Override
    public void run() {
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            //Flujos
            in = new DataInputStream(this.socket.getInputStream());
            out = new DataOutputStream(this.socket.getOutputStream());

            boolean salir = true;

            // Envía un mensaje desde el servidor
            out.writeUTF("Le saludo desde el servidor");

            // Leo mensajes del cliente/s
            while(salir){
                String mensaje = in.readUTF();
                if (mensaje.equalsIgnoreCase("fin")) {
                    salir = false;
                } else {
                    System.out.println("Cliente "+this.socket.getRemoteSocketAddress()+" dice: "+mensaje);
                    out.writeUTF(mensaje);
                }
            }
            this.socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            //El servidor tiene un mensaje si el cliente se desconecta de forma inesperada
            System.out.println("El cliente "+this.socket.getRemoteSocketAddress()+" se ha desconectado inesperadamente");
        } finally{
            try {
                this.socket.close();
            } catch (IOException e) {
                System.out.println("Error con el servidor");
            }
        }
    }
}
