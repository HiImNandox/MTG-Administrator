package vista.Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bdd.Conexion;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;

public class MTGAperfil extends JPanel {
	private JTable tblCartas;
	public static int id;
	public JLabel lblUsuario = new JLabel("lblUsuario");
	public JLabel lblCorreo = new JLabel("lblCorreo");
	public JLabel lblBiblioteca = new JLabel("lblBiblioteca");
	public ArrayList<Integer> lista = new ArrayList<Integer>();
	public JLabel lblCartas = new JLabel("lblCartas");
	public int s;
	private JTable tblBiblioteca;

	/**
	 * Create the panel.
	 */
	public MTGAperfil(int idd) {
		id = idd;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(34, 36, 1009, 90);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(35, 35, 164, 13);
		panel.add(lblNewLabel);
		

		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsuario.setBounds(35, 58, 109, 13);
		panel.add(lblUsuario);
		
		JLabel lblNewLabel_2 = new JLabel("Correo Electronico");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(242, 35, 164, 13);
		panel.add(lblNewLabel_2);
		

		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorreo.setBounds(242, 58, 164, 13);
		panel.add(lblCorreo);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cantidad de Cartas");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(461, 35, 164, 13);
		panel.add(lblNewLabel_2_1);
		

		lblCartas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCartas.setBounds(461, 58, 109, 13);
		panel.add(lblCartas);
		
		JLabel lblNewLabel_2_2 = new JLabel("Cantidad de Bibliotecas");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_2.setBounds(650, 35, 164, 13);
		panel.add(lblNewLabel_2_2);
		

		lblBiblioteca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBiblioteca.setBounds(650, 59, 109, 13);
		panel.add(lblBiblioteca);
		
		JButton btnNewButton = new JButton("Cambiar contrase\u00F1a");
		btnNewButton.setBounds(824, 54, 164, 21);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Mis cartas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(34, 151, 487, 492);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 467, 459);
		panel_1.add(scrollPane);
		
		tblCartas = new JTable();
		scrollPane.setViewportView(tblCartas);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mis bibliotecas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.setBounds(556, 151, 487, 492);
		add(panel_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 23, 467, 459);
		panel_1_1.add(scrollPane_1);
		
		tblBiblioteca = new JTable();
		scrollPane_1.setViewportView(tblBiblioteca);

		empezar();
		llenartablabiblioteca(tblBiblioteca);
		llenartablacartas(tblCartas);
		
	}
	
	public void empezar() {
		String consulta = "SELECT * FROM usuarios where idUser = "+id;
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				lblUsuario.setText(rs.getString("usuario"));
				lblCorreo.setText(rs.getString("correo"));
				empezar2();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void empezar2() {
		String consulta = "SELECT * FROM biblioteca where idUser = "+id;
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				System.out.println("ACA ESTOY");
				lista.add(rs.getInt("idBiblioteca"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		lblBiblioteca.setText(lista.size()+"");
	}
	
	
	public void llenartablacartas(JTable tabla) {
		DefaultTableModel modelo;
		String[] columnas = { "Nombre", "Tipo de carta", "Expansion", "Color" };
		modelo = new DefaultTableModel(null, columnas);
		String[] fila = new String[4];
		for (int i = 0; i < lista.size(); i++) {
			String consulta = "SELECT cartas.nombre as nombre, tipodecarta.nombre as tipodecarta, expansion.nombre as expansion, color.nombre as color FROM cartas JOIN tipodecarta USING(idTipoDeCarta) JOIN expansion USING(idExpansion) JOIN color USING(idColor) JOIN conjuntodecartas USING(idCarta) WHERE conjuntodecartas.idBiblioteca = "+lista.get(i);
			System.out.println("Consulta : "+consulta);
			Connection conexion = Conexion.open();
			try {
				PreparedStatement pst = conexion.prepareStatement(consulta);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					fila[0] = rs.getString("nombre");
					fila[1] = rs.getString("tipodecarta");
					fila[2] = rs.getString("expansion");
					fila[3] = rs.getString("color");
					modelo.addRow(fila);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		tabla.setModel(modelo);
	}
	
	public void llenartablabiblioteca(JTable tabla) {
		DefaultTableModel modelo;
		String consulta = "SELECT biblioteca.idBiblioteca, biblioteca.nombre as nombre, tipodebiblioteca.nombre as tipodebiblioteca FROM biblioteca JOIN tipodebiblioteca USING(idTipoDeBiblioteca) WHERE idUser = "+id;
		String[] columnas = { "Nombre", "Tipo De Biblioteca", "Cantidad de cartas" };
		modelo = new DefaultTableModel(null, columnas);
		String[] fila = new String[3];
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				fila[0] = rs.getString("nombre");
				fila[1] = rs.getString("tipodebiblioteca");
				fila[2] = obtenerCuantasCartas(rs.getInt("idBiblioteca"))+"";
				modelo.addRow(fila);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		tabla.setModel(modelo);
		lblCartas.setText(s+"");
	}
	
	public int obtenerCuantasCartas(int idd) {
		int devolver = 0;
		String consulta = "SELECT count(*) as s FROM conjuntodecartas where idBiblioteca = "+idd;
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				devolver = rs.getInt("s");
				s+= rs.getInt("s");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return devolver;
	}
}
