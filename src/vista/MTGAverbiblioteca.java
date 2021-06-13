package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bdd.Conexion;
import java.awt.Toolkit;

public class MTGAverbiblioteca extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	public JTable table;
	public static String nombre;
	public static int id;
	public ArrayList<Integer> lista = new ArrayList<Integer>();
	public JComboBox<String> cmbtipodecarta = new JComboBox<String>();
	public JComboBox<String> cmbsubtipo = new JComboBox<String>();
	public JComboBox<String> cmbexpansion = new JComboBox<String>();
	public JComboBox<String> cmbcolor = new JComboBox<String>();
	public static JButton btnNewButton = new JButton("A\u00F1adir cartas");
	public static JButton btnBuscarCarta = new JButton("Buscar");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTGAverbiblioteca frame = new MTGAverbiblioteca(nombre,id);
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
	public MTGAverbiblioteca(String nombres,int idd) {
		nombre = nombres;
		id =idd;
		setTitle(nombre);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MTGAverbiblioteca.class.getResource("/img/logo.jpg")));
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1068, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelBuscador = new JPanel();
		panelBuscador.setLayout(null);
		panelBuscador.setBorder(new TitledBorder(null, "Buscador", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelBuscador.setBounds(10, 40, 755, 142);
		contentPane.add(panelBuscador);
		
		JButton btnBuscarCarta = new JButton("Buscar");
		btnBuscarCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenar();
			}
		});
		btnBuscarCarta.setBounds(641, 101, 89, 23);
		panelBuscador.add(btnBuscarCarta);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 32, 46, 14);
		panelBuscador.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(10, 50, 212, 20);
		panelBuscador.add(txtNombre);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de Carta");
		lblNewLabel_1.setBounds(254, 32, 145, 14);
		panelBuscador.add(lblNewLabel_1);
		

		cmbtipodecarta.setBounds(254, 49, 145, 22);
		panelBuscador.add(cmbtipodecarta);
		
		JLabel lblNewLabel_2 = new JLabel("Subtipo");
		lblNewLabel_2.setBounds(431, 32, 64, 14);
		panelBuscador.add(lblNewLabel_2);
		

		cmbsubtipo.setBounds(431, 49, 145, 22);
		panelBuscador.add(cmbsubtipo);
		
		JLabel lblNewLabel_3 = new JLabel("Expansion");
		lblNewLabel_3.setBounds(10, 81, 73, 14);
		panelBuscador.add(lblNewLabel_3);
		

		cmbexpansion.setBounds(10, 101, 212, 22);
		panelBuscador.add(cmbexpansion);
		
		JLabel lblNewLabel_4 = new JLabel("Color");
		lblNewLabel_4.setBounds(254, 81, 46, 14);
		panelBuscador.add(lblNewLabel_4);
		

		cmbcolor.setBounds(254, 101, 145, 22);
		panelBuscador.add(cmbcolor);
		
		JButton btnNewButton = new JButton("A\u00F1adir cartas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MTGAanadircartabiblioteca a = new MTGAanadircartabiblioteca(nombres,btnBuscarCarta);
				a.setVisible(true);
			}
		});
		btnNewButton.setBounds(849, 77, 135, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar Carta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null,"Tienes que seleccionar la carta primero","Error",JOptionPane.ERROR_MESSAGE);
				}else {
					if (sabersiesmia()) {
						eliminarCarta(lista.get(table.getSelectedRow()));
						llenar();
					}else {
						JOptionPane.showMessageDialog(null,"Estas intentando eliminar una carta que no es tuya","Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		btnNewButton_1.setBounds(849, 135, 135, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 1032, 376);
		contentPane.add(scrollPane);
		
		table = new JTable();

		scrollPane.setViewportView(table);
		String consulta = "SELECT conjuntodecartas.idConjuntoDeCartas,cartas.nombre, expansion.nombre as expansion, tipodecarta.nombre as tipodecarta , subtipo.nombre as subtipo, color.nombre as color FROM cartas JOIN conjuntodecartas USING (idCarta) JOIN expansion USING (idExpansion) JOIN tipodecarta USING (idTipoDeCarta) JOIN subtipo USING (idSubtipo) JOIN color USING (idColor) JOIN biblioteca USING(idBiblioteca) WHERE biblioteca.nombre = '"+nombre+"'";
		llenarTabla(consulta, table);
		llenarTodosLosCombobox(cmbexpansion, cmbsubtipo, cmbtipodecarta, cmbcolor, cmbcolor);
	}
	
	public void llenarTabla(String consulta, JTable tabla) {
		DefaultTableModel modelo;
		lista.clear();
		System.out.println(consulta);
		String[] columnas = { "Nombre", "Tipo de carta", "Subtipo", "Expansion", "Color" };
		modelo = new DefaultTableModel(null, columnas);
		String[] fila = new String[5];
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				fila[0] = rs.getString("nombre");
				fila[1] = rs.getString("tipodecarta");
				fila[2] = rs.getString("subtipo");
				fila[3] = rs.getString("expansion");
				fila[4] = rs.getString("color");
				lista.add(rs.getInt("idConjuntoDeCartas"));
				System.out.println(rs.getInt("idConjuntoDeCartas"));
				modelo.addRow(fila);
			}
		} catch (Exception e) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAA");
			System.out.println(e);
		}
		tabla.setModel(modelo);
	}
	
	public void llenar() {
		if (cmbsubtipo.getSelectedItem().toString().equals("Todos")
				&& cmbtipodecarta.getSelectedItem().toString().equals("Todos")
				&& cmbcolor.getSelectedItem().toString().equals("Todos")
				&& cmbexpansion.getSelectedItem().toString().equals("Todos")) {
			llenarTabla(
					"SELECT conjuntodecartas.idConjuntoDeCartas,cartas.nombre, expansion.nombre as expansion, tipodecarta.nombre as tipodecarta , subtipo.nombre as subtipo, color.nombre as color FROM cartas JOIN conjuntodecartas USING (idCarta) JOIN expansion USING (idExpansion) JOIN tipodecarta USING (idTipoDeCarta) JOIN subtipo USING (idSubtipo) JOIN color USING (idColor) JOIN biblioteca USING(idBiblioteca) WHERE biblioteca.nombre = '"+nombre+"'",table);
		} else {
			String consulta = "SELECT conjuntodecartas.idConjuntoDeCartas,cartas.nombre, expansion.nombre as expansion, tipodecarta.nombre as tipodecarta , subtipo.nombre as subtipo, color.nombre as color FROM cartas JOIN conjuntodecartas USING (idCarta) JOIN expansion USING (idExpansion) JOIN tipodecarta USING (idTipoDeCarta) JOIN subtipo USING (idSubtipo) JOIN color USING (idColor) JOIN biblioteca USING(idBiblioteca) WHERE biblioteca.nombre = '"+nombre+"'";
			if (!txtNombre.getText().equals("")) {
				consulta += " and cartas.nombre = '" + txtNombre.getText() + "'";
			}

			if (!cmbsubtipo.getSelectedItem().toString().equals("Todos")) {

				consulta += " and subtipo.nombre = '" + cmbsubtipo.getSelectedItem().toString() + "'";

			}

			if (!cmbtipodecarta.getSelectedItem().toString().equals("Todos")) {

				consulta += " and tipodecarta.nombre = '" + cmbtipodecarta.getSelectedItem().toString() + "'";

			}

			if (!cmbexpansion.getSelectedItem().toString().equals("Todos")) {

				consulta += " and expansion.nombre = '" + cmbexpansion.getSelectedItem().toString() + "'";

			}

			if (!cmbcolor.getSelectedItem().toString().equals("Todos")) {

				consulta += "and color.nombre = '" + cmbcolor.getSelectedItem().toString() + "'";
			}

			System.out.println(consulta);
			llenarTabla(consulta,table);
		}
	}
	
	public void eliminarCarta(int fila) {
		String consulta = "DELETE FROM conjuntodecartas where idConjuntoDeCartas = "+fila;
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static ArrayList<String> llenarCombo(String tabla) {

		ArrayList<String> lista = new ArrayList<String>();
		String q = "SELECT nombre from " + tabla;
		Connection conexion = Conexion.open();
		try {
			PreparedStatement sentencia = conexion.prepareStatement(q);
			ResultSet resultado = sentencia.executeQuery();
			lista.add("Todos");
			while (resultado.next()) {
				lista.add(resultado.getString("nombre"));
			}
			System.out.println("Se han rellenado el combobox");
		} catch (Exception e) {
			System.out.println(e);
		}

		return lista;
	}
	
	public void llenarTodosLosCombobox(JComboBox<String> expansion, JComboBox<String> subtipo,
			JComboBox<String> tipodecarta, JComboBox<String> color, JComboBox<String> dueno) {
		ArrayList<String> lista = llenarCombo("expansion");
		for (int i = 0; i < lista.size(); i++) {
			expansion.addItem(lista.get(i));
		}

		lista = llenarCombo("subtipo");
		for (int i = 0; i < lista.size(); i++) {
			subtipo.addItem(lista.get(i));
		}

		lista = llenarCombo("tipodecarta");
		for (int i = 0; i < lista.size(); i++) {
			tipodecarta.addItem(lista.get(i));
		}
		lista = llenarCombo("color");
		for (int i = 0; i < lista.size(); i++) {
			color.addItem(lista.get(i));
		}
		
	}
	
	public boolean sabersiesmia() {
		boolean resultado = false;
		
		String q = "SELECT * from biblioteca where nombre ='"+nombre+"' and idUser = "+id;
		Connection conexion = Conexion.open();
		try {
			PreparedStatement sentencia = conexion.prepareStatement(q);
			ResultSet e = sentencia.executeQuery();
			while (e.next()) {
				resultado = true;
			}
			System.out.println("Se han rellenado el combobox");
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultado;
	}
}
