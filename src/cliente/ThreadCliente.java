package cliente;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadCliente extends Thread {
    private Socket socket;
    private VentanaCliente vc;
    private Cliente cliente;
    
    public ThreadCliente(Socket socket, Cliente cliente) {
        super("ThreadCliente");
        this.socket = socket;
        this.cliente = cliente;
    }
    
    @SuppressWarnings("deprecation")
    public void run() {
        DataInputStream datos;
        String temp = null;
        vc = new VentanaCliente(this.cliente);
        vc.setVisible(true);
        try {
            do {
                if (temp != null){
                	vc.setArea(temp);
               }
                datos = new DataInputStream(socket.getInputStream());
            } while ((temp = datos.readLine()) != null);
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                System.out.println("ERROR EN ENVIO DE MENSAJE");
            }
        }
    }
}
