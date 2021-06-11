package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bdd.Conexion;
import controlador.Usuarios;
import fonts.Fuente;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.Color;

public class MTGAlogin extends JFrame {
	
	private JFrame frame = this;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTGAlogin frame = new MTGAlogin();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MTGAlogin() {
		
		Fuente f = new Fuente();
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MTGAlogin.class.getResource("/img/logo.jpg")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Iniciar Sesion");
		btnNewButton.setFont(new Font("Magic:the Gathering", Font.PLAIN, 20));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuarios cargarUsuario = new Usuarios(txtUsuario.getText(), String.valueOf(txtContrasena.getPassword()), "");
				int siguientePestana = cargarUsuario.iniciarSesion();
				if (siguientePestana == 0) {

				}else {
					MTGAaplicacion a = new MTGAaplicacion(siguientePestana);
					a.setVisible(true);

					dispose();
				}
			}
		});

		btnNewButton.setBounds(587, 323, 146, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrarse");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MTGAregister register = new MTGAregister();
				register.setVisible(true);
				register.setParent(frame);
				frame.setEnabled(false);
			}
		});

		btnNewButton_1.setFont(new Font("Magic:the Gathering", Font.PLAIN, 20));
		btnNewButton_1.setBounds(411, 323, 137, 36);
		contentPane.add(btnNewButton_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Magic:the Gathering", Font.PLAIN, 11));
		txtUsuario.setBounds(560, 171, 152, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MTGAlogin.class.getResource("/img/logo2.jpg")));
		lblNewLabel.setBounds(20, 44, 290, 290);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrasena");
		lblNewLabel_1.setFont(new Font("Magic:the Gathering", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(411, 241, 125, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Usuario");
		lblNewLabel_1_1.setFont(new Font("Magic:the Gathering", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(411, 163, 125, 36);
		contentPane.add(lblNewLabel_1_1);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setForeground(Color.RED);
		txtContrasena.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtContrasena.setBounds(560, 249, 152, 20);
		contentPane.add(txtContrasena);
		
		JLabel lblNewLabel_2 = new JLabel("MTG Administrator");
		lblNewLabel_2.setFont(new Font("Magic:the Gathering", Font.BOLD, 30));
		lblNewLabel_2.setBounds(411, 44, 301, 78);
		contentPane.add(lblNewLabel_2);
	}
}
