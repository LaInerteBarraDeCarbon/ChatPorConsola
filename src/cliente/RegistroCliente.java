package cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegistroCliente extends JFrame {

	private JPanel contentPane;
	private JTextField ip;
	private JTextField puer;
	private JTextField user;
	private VentanaCliente vc;
	private JButton reg;
	Cliente cliente;
	int puerto;
	String host;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroCliente frame = new RegistroCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegistroCliente() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistroDeCliente = new JLabel("REGISTRO DE CLIENTE");
		lblRegistroDeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeCliente.setBounds(10, 11, 414, 14);
		contentPane.add(lblRegistroDeCliente);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(68, 85, 46, 14);
		contentPane.add(lblIp);
		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setBounds(68, 110, 46, 14);
		contentPane.add(lblPuerto);
		
		JLabel lblNombreDeUsuerio = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuerio.setBounds(68, 135, 129, 14);
		contentPane.add(lblNombreDeUsuerio);
		
		ip = new JTextField();
		ip.setBounds(218, 82, 86, 20);
		contentPane.add(ip);
		ip.setColumns(10);
		
		puer = new JTextField();
		puer.setBounds(218, 107, 86, 20);
		contentPane.add(puer);
		puer.setColumns(10);
		
		user = new JTextField();
		user.setBounds(218, 132, 86, 20);
		contentPane.add(user);
		user.setColumns(10);
		
		reg = new JButton("REGISTRAR");
		reg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if(key.getKeyCode()== KeyEvent.VK_ENTER){
					reg.doClick();
				}
			}
		});
		reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				host = ip.getText();
				puerto = Integer.parseInt(puer.getText());
		        cliente = new Cliente(host, puerto);
		        cliente.setNombre(user.getText());
		        new ThreadCliente(cliente.getSocket(),cliente).start();
//				vc = new VentanaCliente(cliente);
//				vc.setVisible(true);
				dispose();
			}
		});
		reg.setBounds(159, 199, 129, 23);
		contentPane.add(reg);

       



       }
}
