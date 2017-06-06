package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;


// Nombre e IP del Servidor
/* getByName(host): regresa la direccion ip de la maquina que se especifica como parametro.
 * este parametro es un string, el cual puede ser el nombre de la maquinacomo el nombre de dominio 
 * "javacs.mty.itesm.mx" o el string que regresa la direccion de i en su notacion decimal "131.178.14.228".
 * 
 * getLocalHost(): regresa la direccion ip de la maquina donde se esta ejecutando el programa.
 * 
 * getAllByName(host):Regresa un arreglo de objetos de tipo InetAddress. Este método es útil en caso de 
 * que quieras averiguar todas las direcciones IP que tenga asignada una máquina en particular.
 *
 * Todos arrojan la excepcion UnknowHostException en caso de no encontrar la direccion.
 */

public class Server {

    private ServerSocket servidor;
    private Socket cliente;
    public static int cantActualClientes;
    private Collection<Socket> coleccion;
    private int max_clientes;
    private int puerto;
    private String nombreHost;
    private String IPHost;

    public String getNombreHost() {
        return nombreHost;
    }

    public String getIPHost() {
        return IPHost;
    }

    public int getMax_clientes() {
        return max_clientes;
    }

    public int getPuerto() {
        return puerto;
    }

    public Server(int port, int max_conexiones) {

        try {
            nombreHost = InetAddress.getLocalHost().getHostName().toString();
            IPHost = InetAddress.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

        puerto = port;
        max_clientes = max_conexiones;

        cantActualClientes = 0;
        coleccion = new ArrayList<Socket>();

        try {
            servidor = new ServerSocket(puerto);
        } catch (IOException e) {
            System.out.println("No se puede escuchar desde el puerto elegido, cerrando Servidor...");
            System.exit(1);
        }
    }

    public Collection<Socket> getLista() {
        return coleccion;
    }

    public Socket aceptarConexion() {

        cantActualClientes++;

        try {
            cliente = servidor.accept();
            if (cantActualClientes > max_clientes) {
                PrintStream ps = new PrintStream(cliente.getOutputStream());
                ps.println("Servidor Lleno");
                cliente.close();
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al aceptar conexiones, Cerrando el Servidor...");
            System.exit(1);
        }
        System.out.println("La Conexion NRO " + cantActualClientes
                + " fue aceptada correctamente.");
        coleccion.add(cliente);
        return cliente;
    }

    public void pararServidor() {
        try {
            servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
