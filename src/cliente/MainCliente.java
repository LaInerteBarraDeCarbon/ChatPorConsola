package cliente;


import server.Server;

public class MainCliente {
	
	private static Cliente cliente;
	private static RegistroCliente regCliente;
	private VentanaChat ventanaChat;
	private static VentanaCliente ventanaCliente;
	private int puerto;
	private String ip;
	private Server server;

	

	public static void main(String[] args) {
		
		regCliente = new RegistroCliente();
		regCliente.setVisible(true);
		
 //       ventanaCliente = new VentanaCliente(cliente);
 //       ventanaCliente.setVisible(true);
		
		
	}

}
