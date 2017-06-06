package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Calendar;

import javax.management.OperationsException;
import javax.swing.JOptionPane;

public class Cliente {

    private Socket cliente;
    private String nombre = null;
    private int puerto;
    private String mensaje;

    public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getPuerto() {
        return puerto;
    }

    public Cliente(String direccion, int port) {
        try {
            puerto = port;
            cliente = new Socket(direccion, port);
        } catch (IOException e) {
            System.out
                    .println("No se pudo conectar con el servidor, cerrando el  programa...");
            System.exit(1);
        }
    }

    public Socket getSocket() {
        return cliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre(){
    	return this.nombre;
    }

    public void enviarMensaje(String mensaje) {
        try {
                       
          //Se lee desde el host del usuario y dirige el flujo o información al server
            PrintStream ps = new PrintStream(cliente.getOutputStream());

            String data = mensaje;
            

                if (data.contains(".exit")) {
                    cerrarCliente();
                    System.exit(1);
                } else

                // Si se toca enter sin haber ingresado enter, lo ignoro
                if (!data.equals("")) {
                    ps.println(nombre + " dijo a las " + horaDelMensaje()
                            + ":  " + data);
                }
            

        } catch (IOException e) {
            JOptionPane op = new JOptionPane("ERROR AL ENVIAR MENSAJE",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cerrarCliente() {
        try {
            cliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String horaDelMensaje() {
        Calendar cal = Calendar.getInstance();
        return +cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE)
                + ":" + cal.get(Calendar.SECOND);
    }

}
