package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import cliente.Cliente;

public class MainServer {
	
	private static VentanaConfiguracion vc;
	private static VentanaServidor vs;
	private static Server servidor;
	static Socket socket = null;
	
/*    public MainServer() {
    	
        Socket socket = null;

        Server servidor = null;
        int puerto = 50;
        int maximoConexiones = 15;

        System.out.println("SERVIDOR DE CHAT POR CONSOLA:\n-----------------------------\n");
        System.out.println("Ingrese el puerto a utilizar: ");

        BufferedReader brServer = new BufferedReader(new InputStreamReader(System.in));

        try {
            puerto = Integer.parseInt(brServer.readLine());
        } catch (Exception e) {

            System.out.println("Error al ingresar el puerto, cerrando aplicacion...");
            System.exit(1);
        }

        System.out.println("Ingrese la cantidad maxima de usuarios a soportar: ");

        brServer = new BufferedReader(new InputStreamReader(System.in));
/*
        try {
            maximoConexiones = Integer.parseInt(brServer.readLine());
        } catch (Exception e) {
            System.out.println("Error al ingresar el puerto, cerrando aplicacion...");
            System.exit(1);
        }
*/
 //       servidor = new Server(puerto, maximoConexiones);
/*
        System.out.println("DATOS DEL SERVIDOR:\n-------------------\n");
        System.out.println("Nombre del Servidor:\t" + servidor.getNombreHost());
        System.out.println("IP del Servidor:\t" + servidor.getIPHost());
        System.out.println("Puerto de Escucha:\t" + servidor.getPuerto());
        System.out.println("Cantidad Maxima de Clientes:\t" + servidor.getMax_clientes());
        System.out.println("\nServidor en escucha...");
       */
/*
        while (true) {
            socket = servidor.aceptarConexion();
            if (socket != null)
                new ThreadServer(socket, servidor.getLista()).start();
            else{
            	servidor.pararServidor();
            }
        }
        
    }*/
	
	public static void main(String[] args) {
		vc = new VentanaConfiguracion();
		servidor = vc.getServidor();
		vs = new VentanaServidor(servidor);
		vs.setVisible(true);
		
		
		 while (true) {
	            socket = servidor.aceptarConexion();
	            if (socket != null){
	            	vs.escribirTextArea("Nuevo usuario conectado \n");
	                new ThreadServer(socket, servidor.getLista(),vs).start();
	                
	            }
	            else{
	            	servidor.pararServidor();
	            }
	}
}
}
