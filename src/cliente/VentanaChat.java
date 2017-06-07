package cliente;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class VentanaChat extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnEnviar;
	private JTextArea textArea;
	private String usuarioDestinoChat;
	private Cliente cliente;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaChat frame = new VentanaChat("pepe", "chat");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 * 
	 * Modo:
	 * 		"Sala":	Sala de Chat
	 * 		"Chat": Sesión privada
	 *  
	 * Usuario Destino: Usuario en sesión privada
	 */
	public VentanaChat(final Cliente cliente, String usuarioDestinoChat, String modoChat) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.usuarioDestinoChat = usuarioDestinoChat;
		
		if(modoChat.equals("Sala"))
			setTitle("Sala de Chat");
		else
			setTitle(usuarioDestinoChat);
				
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				mostrarVentanaConfirmacion();
			}
		});
		
		setBounds(100, 100, 450, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 444, 300);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if(key.getKeyCode() == KeyEvent.VK_ENTER){
					btnEnviar.doClick();
				}
			}
		});
		textField.setBounds(0, 300, 336, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cliente.enviarMensaje(textField.getText());
				textArea.append(cliente.getNombre() + ": " + textField.getText() + "\n");
				textField.setText(null);
//				escribeEnTextArea(textField.getText());
			}
		});
		btnEnviar.setBounds(346, 302, 91, 23);
		contentPane.add(btnEnviar);

		setVisible(true);
		textField.requestFocus();

	}
	
	public void escribeEnTextArea(String text) {
		textArea.setCaretPosition(textArea.getText().length());
		textArea.append(text + "\n");
	}
	
	private void mostrarVentanaConfirmacion() {
		int res = JOptionPane.showConfirmDialog(this, "Desea salir de la sesión de chat", "Confirmar cerrar", JOptionPane.YES_NO_OPTION);
		if(res == JOptionPane.YES_OPTION)
			dispose();
	}
	
	private void hacerFocoTextField(JTextField textField) {
		textField.requestFocus();
		textField.selectAll();
	}
}
