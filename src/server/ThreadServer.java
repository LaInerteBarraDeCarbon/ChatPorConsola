package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;

public class ThreadServer extends Thread {

    private Socket socket;
    private Collection<Socket> coleccion;
    
    public ThreadServer(Socket socket, Collection<Socket> coleccion) {

        super("ThreadServer");
        this.socket = socket;
        this.coleccion = coleccion;
    }

    @SuppressWarnings("deprecation")
    public void run() {

        DataInputStream data;
        Iterator<Socket> i;
        String aux = null;

        try {
            do {
                if (aux != null) {
                    System.out.println(aux);
                    i = coleccion.iterator();

                    while (i.hasNext()) {
                        Socket cliente = i.next();
                        try {

                            // si el socket extraido es distinto al socket del
                            // hilo
                            // se enviara el msg a todos los usuarios de la
                            // coleccion menos el que envio dicho msg.
                            if (!cliente.equals(socket)) {
                            	
                                PrintStream ps = new PrintStream(cliente.getOutputStream());
                                ps.println(aux);// envia el mensaje al
                                                // correspondiente socket.
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // indico que el flujo de informacion provenga del usuario de
                // este hilo.
                data = new DataInputStream(socket.getInputStream());

            } while ((aux = data.readLine()) != null);

            Server.cantActualClientes--;
            coleccion.remove(socket);
            System.out.println("Un cliente se ha desconectado.");
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("La conexion ha finalizado.");
        }
    }
}