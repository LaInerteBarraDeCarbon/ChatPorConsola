package server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaServidor extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private static Server servidor;
	private Socket socket;
	int puerto;
	String IpHost;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaServidor frame = new VentanaServidor(servidor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaServidor(final Server servidor) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 201);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnDetener = new JButton("Detener");
		btnDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				servidor.pararServidor();
				dispose();
			}
		});
		btnDetener.setBounds(335, 223, 89, 23);
		contentPane.add(btnDetener);
		
		textArea.append("DATOS DEL SERVIDOR:\n-------------------\n");
		textArea.append("IP del Servidor: " + servidor.getIPHost()+"\n");
		textArea.append("Puerto de Escucha: " + servidor.getPuerto()+"\n");
		textArea.append("\nServidor en escucha..."+"\n");
		//textArea.disable();
        
	}

	public void escribirTextArea(String text){
		textArea.append(text);
	}



}
