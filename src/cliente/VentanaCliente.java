package cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.DropMode;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VentanaCliente extends JFrame {

	private JPanel contentPane;
	private JTextField text;
	private JButton enviar;
	private Cliente cliente;
	private String mensaje;
	private JTextArea area;
		
	public void setArea(String mensaje){
		this.mensaje = mensaje;
		area.append(this.mensaje + "\n");
	}
	public VentanaCliente(Cliente cli) {
		
		this.cliente = cli;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel chat = new JLabel("CHAT");
		chat.setBounds(5, 5, 424, 14);
		chat.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(chat);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 30, 419, 170);
		contentPane.add(scrollPane);
		
		area = new JTextArea();
		area.setEditable(false);
		scrollPane.setViewportView(area);
		
		text = new JTextField();
		text.setBounds(5, 210, 297, 20);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if(key.getKeyCode() == KeyEvent.VK_ENTER){
					enviar.doClick();
				}
			}
		});
		contentPane.add(text);
		text.setColumns(10);
		enviar = new JButton("ENVIAR");
		enviar.setBounds(335, 211, 89, 23);																												/**vale**/
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cliente.enviarMensaje(text.getText());
				area.append(cliente.getNombre() + " :" + text.getText() + "\n");
				text.setText(null);
			}
		});
		enviar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if(key.getKeyCode() == KeyEvent.VK_ENTER){
					enviar.doClick();
				}
			}
		});
		contentPane.add(enviar);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event){
				cliente.cerrarCliente();
				dispose();	
			}
		});
		
	}
	

}
