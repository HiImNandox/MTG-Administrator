package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bdd.Conexion;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

public class MTGAvisualizarcartas extends JFrame {

	private JPanel contentPane;
	public static String nombrecarta;
	public JLabel txtNombre = new JLabel("New label");
	public JLabel txtCoste = new JLabel("New label");
	public JLabel txtRareza = new JLabel("New label");
	public JLabel txtColor = new JLabel("New label");
	public JLabel txtExpansion = new JLabel("New label");
	public JLabel txtResistencia = new JLabel("New label");
	public JLabel txtFuerza = new JLabel("New label");
	public JLabel txtSubtipo = new JLabel("Subtipo");
	public JLabel txtTipoDeCarta = new JLabel("New label");
	public JTextPane txtTexto = new JTextPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTGAvisualizarcartas frame = new MTGAvisualizarcartas(nombrecarta);
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
	public MTGAvisualizarcartas(String nombre) {
		setAlwaysOnTop(true);
		nombrecarta = nombre;
		setTitle(nombre);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MTGAvisualizarcartas.class.getResource("/img/logo.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 411, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 108, 14);
		contentPane.add(lblNewLabel);
		

		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNombre.setBounds(10, 36, 217, 14);
		contentPane.add(txtNombre);
		
		JLabel lblNewLabel_2 = new JLabel("Coste");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(270, 11, 108, 14);
		contentPane.add(lblNewLabel_2);
		

		txtCoste.setBounds(270, 37, 108, 14);
		contentPane.add(txtCoste);
		
		JLabel lblNewLabel_4 = new JLabel("Color");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 75, 172, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2_1 = new JLabel("Rareza");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(270, 75, 108, 14);
		contentPane.add(lblNewLabel_2_1);
		

		txtRareza.setBounds(270, 101, 108, 14);
		contentPane.add(txtRareza);
		

		txtColor.setBounds(10, 100, 217, 14);
		contentPane.add(txtColor);
		

		txtExpansion.setBounds(10, 157, 217, 14);
		contentPane.add(txtExpansion);
		
		JLabel lblNewLabel_4_1 = new JLabel("Expansion");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4_1.setBounds(10, 132, 172, 14);
		contentPane.add(lblNewLabel_4_1);
		

		txtTipoDeCarta.setBounds(270, 157, 125, 14);
		contentPane.add(txtTipoDeCarta);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Tipo De Carta");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4_1_1.setBounds(270, 132, 125, 14);
		contentPane.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_5 = new JLabel("Fuerza");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(38, 192, 53, 14);
		contentPane.add(lblNewLabel_5);
		

		txtFuerza.setBounds(38, 217, 46, 14);
		contentPane.add(txtFuerza);
		
		JLabel lblNewLabel_5_1 = new JLabel("Resistencia");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(300, 192, 98, 14);
		contentPane.add(lblNewLabel_5_1);
		

		txtResistencia.setBounds(300, 217, 46, 14);
		contentPane.add(txtResistencia);
		
		JLabel lblNewLabel_7 = new JLabel("Subtipo");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(156, 192, 64, 14);
		contentPane.add(lblNewLabel_7);
		

		txtSubtipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSubtipo.setBounds(156, 216, 98, 14);
		contentPane.add(txtSubtipo);
		
		JLabel lblNewLabel_8 = new JLabel("Texto de la carta");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8.setBounds(10, 260, 108, 14);
		contentPane.add(lblNewLabel_8);
		

		txtTexto.setEditable(false);
		txtTexto.setBounds(10, 285, 388, 181);
		contentPane.add(txtTexto);
		obtenerDatos();
	}
	
	public void obtenerDatos() {
		Connection conexion = Conexion.open();
		String consulta = "Select cartas.nombre,cartas.coste, tipodecarta.nombre as tipodecarta, subtipo.nombre as subtipo, expansion.nombre as expansion, color.nombre as color, rareza.nombre as rareza, cartas.textodelacarta, cartas.fuerzairesistencia from cartas JOIN tipodecarta USING(idTipoDeCarta) JOIN subtipo USING(idSubtipo) JOIN expansion USING(idExpansion) JOIN color USING(idColor) JOIN rareza USING(idRareza) where cartas.nombre = '"+nombrecarta+"'";
		
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				txtNombre.setText(rs.getString("nombre"));
				txtColor.setText(rs.getString("color"));
				txtExpansion.setText(rs.getString("expansion"));
				txtCoste.setText(rs.getString("coste"));
				txtRareza.setText(rs.getString("rareza"));
				txtSubtipo.setText(rs.getString("subtipo"));
				txtTipoDeCarta.setText(rs.getString("tipodecarta"));
				establecerResistencia(rs.getString("fuerzairesistencia"));
				txtTexto.setText(rs.getString("textodelacarta"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void establecerResistencia(String fuerzayresistencia) {
		if (fuerzayresistencia.contains("/")) {
			String[] f = fuerzayresistencia.split("/");
			txtFuerza.setText(f[0]);
			txtResistencia.setText(f[1]);
		}else if (!fuerzayresistencia.equals("")) {
			txtResistencia.setText(fuerzayresistencia);
			txtFuerza.setText(" ");
		} else {
			txtFuerza.setText(" ");
			txtResistencia.setText(" ");
		}
	}
}
