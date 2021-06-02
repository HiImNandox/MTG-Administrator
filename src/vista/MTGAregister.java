package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Usuarios;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MTGAregister extends JFrame {

	private JFrame parentFrame;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	private JTextField txtCorreo;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public MTGAregister() {
		setResizable(false);
		setTitle("Registrar");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MTGAregister.class.getResource("/img/logo.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 243, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setForeground(Color.LIGHT_GRAY);
		txtUsuario.setBounds(10, 36, 207, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre de Usuario");
		lblNewLabel.setBounds(10, 11, 207, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 67, 207, 14);
		contentPane.add(lblContrasea);
		
		txtContrasena = new JTextField();
		txtContrasena.setForeground(Color.LIGHT_GRAY);
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(10, 92, 207, 20);
		contentPane.add(txtContrasena);
		
		JLabel lblCorreoElectronica = new JLabel("Correo Electronico");
		lblCorreoElectronica.setBounds(10, 123, 207, 14);
		contentPane.add(lblCorreoElectronica);
		
		txtCorreo = new JTextField();
		txtCorreo.setForeground(Color.LIGHT_GRAY);
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(10, 148, 207, 20);
		contentPane.add(txtCorreo);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuarios nuevoUsuario = new Usuarios(txtUsuario.getText(),txtContrasena.getText(),txtCorreo.getText());
				nuevoUsuario.registrarUsuarios();
			}
		});
		btnRegistrar.setBounds(70, 192, 89, 23);
		contentPane.add(btnRegistrar);
	

			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					if (parentFrame != null) {
						parentFrame.setEnabled(true);
					}
					
				}
			});
	}

	public void setParent(JFrame parent) {
		this.parentFrame = parent;
	}
	
}
