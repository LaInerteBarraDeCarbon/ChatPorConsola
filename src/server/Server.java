package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import cliente.Cliente;


// Nombre e IP del Servidor
/* getByName(host): regresa la direccion ip de la maquina que se especifica como parametro.
 * este parametro es un string, el cual puede ser el nombre de la maquinacomo el nombre de dominio 
 * "javacs.mty.itesm.mx" o el string que regresa la direccion de i en su notacion decimal "131.178.14.228".
 * 
 * getLocalHost(): regresa la direccion ip de la maquina donde se esta ejecutando el programa.
 * 
 * getAllByName(host):Regresa un arreglo de objetos de tipo InetAddress. Este m�todo es �til en caso de 
 * que quieras averiguar todas las direcciones IP que tenga asignada una m�quina en particular.
 *
 * Todos arrojan la excepcion UnknowHostException en caso de no encontrar la direccion.
 */

/**
 * Clase que representa al servidor de la aplicación al cual se conectarán los clientes.
 */
public class Server {

	/**
	 * Permite establecer el enlace desde el servidor hacia el cliente.
	 */
    private ServerSocket servidor;
    
    /**
     * Permite establecer el enlace desde el cliente hacia el servidor.
     */
    private Socket cliente;


	/**
     * Determina la cantidad de clientes conectados.
     */
    public static int cantActualClientes;
    private Collection<Socket> coleccion;
    
    /**
     * Determina la cantidad máxima de clientes conectados al mismo tiempo que soportará el servidor.
     */
    private int max_clientes;
    
    /**
     * Número de puerto por elque se realiza la conexión.
     */
    private int puerto;
    
    /**
     * Nombre del servidor.
     */
    private String nombreHost;
    
    /**
     * IP del servidor.
     */
    private String IPHost;
    /**
     * Campo usado para tener acceso a los datos del usuario.
     */
    private Cliente datosCliente;

    public String getNombreHost() {
        return nombreHost;
    }

    public String getIPHost() {
        return IPHost;
    }


    public int getPuerto() {
        return puerto;
    }
    
    public Socket getCliente() {
		return cliente;
	}
    
    public Server(String ip,int port) {
/*
        try {
            nombreHost = InetAddress.getLocalHost().getHostName().toString();
            IPHost = InetAddress.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
*/
    	IPHost = ip;
        puerto = port;
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

    /**
     * Acepta la conección solicitada por el cliente.
     * @return
     */
    public Socket aceptarConexion() {

        cantActualClientes++;
        
        try {
        	cliente = servidor.accept();
        } catch (Exception e) {
            System.out.println("Error al aceptar conexiones, Cerrando el Servidor...");
            System.exit(1);
        }
        System.out.println("La Conexion NRO " + cantActualClientes
                + " fue aceptada correctamente.");
        coleccion.add(cliente);
        
        return cliente;
    }


	/**
     * Detiene la ejecución del servidor.
     */
    public void pararServidor() {
        try {
            servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}