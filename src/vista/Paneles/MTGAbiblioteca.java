package vista.Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bdd.Conexion;
import vista.MTGAanadirbiblioteca;
import vista.MTGAanadircartas;
import vista.MTGAverbiblioteca;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MTGAbiblioteca extends JPanel {
	private JTextField txtNombre;
	public JTable table;
	public int id;
	public 	JButton btnBuscarCarta = new JButton("Buscar");

	/**
	 * Create the panel.
	 */
	public MTGAbiblioteca(int idd) {
		setLayout(null);
		id = idd;
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 260, 1018, 373);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Due\u00F1o", "Tipo de Biblioteca", "Cantidad de Cartas"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panelBuscador = new JPanel();
		panelBuscador.setLayout(null);
		panelBuscador.setBorder(new TitledBorder(null, "Buscador", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelBuscador.setBounds(25, 73, 1018, 129);
		add(panelBuscador);
		
		JButton btnBuscarCarta = new JButton("Buscar");

		btnBuscarCarta.setBounds(844, 69, 89, 23);
		panelBuscador.add(btnBuscarCarta);
		
		JLabel lblNewLabel_1 = new JLabel("Due\u00F1o");
		lblNewLabel_1.setBounds(337, 44, 73, 14);
		panelBuscador.add(lblNewLabel_1);
		
		JComboBox<String> cmbDueno = new JComboBox<String>();
		cmbDueno.setBounds(337, 69, 145, 22);
		panelBuscador.add(cmbDueno);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo De Biblioteca");
		lblNewLabel_2.setBounds(573, 44, 145, 14);
		panelBuscador.add(lblNewLabel_2);
		
		JComboBox<String> cmbTipoDeBiblioteca = new JComboBox<String>();
		cmbTipoDeBiblioteca.setBounds(573, 69, 145, 22);
		panelBuscador.add(cmbTipoDeBiblioteca);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre ");
		lblNewLabel_4.setBounds(39, 44, 113, 14);
		panelBuscador.add(lblNewLabel_4);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(39, 70, 212, 20);
		panelBuscador.add(txtNombre);
		txtNombre.setColumns(10);
		
		btnBuscarCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbDueno.getSelectedItem().toString().equals("Todos") && cmbTipoDeBiblioteca.getSelectedItem().toString().equals("Todos") && txtNombre.getText().equals("")) {
					llenarTabla("SELECT biblioteca.idBiblioteca, biblioteca.nombre, usuarios.usuario as usuario, tipodebiblioteca.nombre as tipodebiblioteca from biblioteca JOIN tipodebiblioteca USING(idTipoDeBiblioteca) JOIN usuarios USING(idUser)", table);
				}else {
					String consulta = "SELECT biblioteca.idBiblioteca, biblioteca.nombre, usuarios.usuario as usuario, tipodebiblioteca.nombre as tipodebiblioteca from biblioteca JOIN tipodebiblioteca USING(idTipoDeBiblioteca) JOIN usuarios USING(idUser) WHERE";
					boolean nomaswhere = false;
					
					if (!txtNombre.getText().equals("")) {
						if (nomaswhere == false) {
							nomaswhere = true;
						}
						consulta += " biblioteca.nombre like '%" + txtNombre.getText() + "%'";

					}
					
					if (!cmbDueno.getSelectedItem().toString().equals("Todos")) {
						if (nomaswhere == false) {
							nomaswhere = true;
						}else {
							consulta += " and";
						}
						consulta += " usuarios.usuario = '" + cmbDueno.getSelectedItem().toString() + "'";

					}

					if (!cmbTipoDeBiblioteca.getSelectedItem().toString().equals("Todos")) {
						if (nomaswhere == false) {
							nomaswhere = true;
						} else {
							consulta += " and";
						}
						consulta += " tipodebiblioteca.nombre = '" + cmbTipoDeBiblioteca.getSelectedItem().toString() + "'";
					}

					System.out.println(consulta);
					llenarTabla(consulta, table);
				}
			}
		});

		llenarTabla("SELECT biblioteca.idBiblioteca, biblioteca.idBiblioteca, biblioteca.nombre, usuarios.usuario as usuario, tipodebiblioteca.nombre as tipodebiblioteca from biblioteca JOIN tipodebiblioteca USING(idTipoDeBiblioteca) JOIN usuarios USING(idUser)", table);
		llenarTodosLosCombobox(cmbDueno, cmbTipoDeBiblioteca);
		poputTable();
	}

	
	public void llenarTabla(String consulta, JTable tabla) {
		DefaultTableModel modelo;
		String[] columnas = { "Nombre", "Due?o", "Tipo de Biblioteca", "Cantidad de Cartas" };
		modelo = new DefaultTableModel(null, columnas);
		String[] fila = new String[5];
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				fila[0] = rs.getString("nombre");
				fila[1] = rs.getString("usuario");
				fila[2] = rs.getString("tipodebiblioteca");
				int contador = contarCartas(rs.getInt("idBiblioteca"));
				fila[3] = contador+"";
				modelo.addRow(fila);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		tabla.setModel(modelo);
	}
	
	public static ArrayList<String> llenarCombo(String tabla,String tipo) {

		ArrayList<String> lista = new ArrayList<String>();
		String q = "SELECT "+tipo+" from " + tabla;
		Connection conexion = Conexion.open();
		try {
			PreparedStatement sentencia = conexion.prepareStatement(q);
			ResultSet resultado = sentencia.executeQuery();
			lista.add("Todos");
			while (resultado.next()) {
				lista.add(resultado.getString(tipo));
			}
			System.out.println("Se han rellenado el combobox");
		} catch (Exception e) {
			System.out.println(e);
		}

		return lista;
	}
	
	public void llenarTodosLosCombobox(JComboBox<String> dueno, JComboBox<String> tipodebiblioteca) {
		ArrayList<String> lista = llenarCombo("usuarios","usuario");
		for (int i = 0; i < lista.size(); i++) {
			dueno.addItem(lista.get(i));
		}

		lista = llenarCombo("tipodebiblioteca","nombre");
		for (int i = 0; i < lista.size(); i++) {
			tipodebiblioteca.addItem(lista.get(i));
		}


	}

	public void poputTable() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem menuItem1 = new JMenuItem("Ver Biblioteca");
		JMenuItem menuItem2 = new JMenuItem("Crear Biblioteca");
		JMenuItem menuItem3 = new JMenuItem("Eliminar Biblioteca");

		menuItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null,"Tienes que seleccionar la carta primero","Error",JOptionPane.ERROR_MESSAGE);
				}else {
					MTGAverbiblioteca a = new MTGAverbiblioteca((String) table.getModel().getValueAt(table.getSelectedRow(), 0),id);
					a.setVisible(true);
					}

				}

			
		});

		menuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				MTGAanadirbiblioteca a = new MTGAanadirbiblioteca(id,btnBuscarCarta);
				a.setVisible(true);
			}
		});

		menuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (saberSiEsMia()) {
					int input = JOptionPane.showConfirmDialog(null, "Estas seguro que quieres eliminar esta biblioteca?");
					if (input == 0) {
						Connection conexion = Conexion.open();
						String consulta = "DELETE FROM biblioteca WHERE nombre = '"+(String) table.getModel().getValueAt(table.getSelectedRow(), 0)+"'";
						try {
							PreparedStatement pst = conexion.prepareStatement(consulta);
							if (pst.executeUpdate() == 1) {
								JOptionPane.showMessageDialog(null, "La biblioteca se ha eliminado correctamente");
							}
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Estas intentando una biblioteca que no es tuya", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
			
		popMenu.add(menuItem1);
		popMenu.add(menuItem2);
		popMenu.add(menuItem3);
		table.setComponentPopupMenu(popMenu);
	}

	public boolean saberSiEsMia() {
		boolean resultado = false;
		String consulta = "SELECT * FROM biblioteca where idUser = "+id+" and nombre = '"+(String) table.getModel().getValueAt(table.getSelectedRow(), 0)+"'";
		System.out.println(consulta);
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				System.out.println("ESTA EN VERDADERO");
				resultado = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}System.out.println(resultado);
		return resultado;
	}
	
	public int contarCartas(int idddd) {
		int contador = 0;
		String consulta = "SELECT count(*) as contar FROM conjuntodecartas where idBiblioteca = "+idddd;
		Connection conexion = Conexion.open();
		try {
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
				contador = resultado.getInt("contar");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return contador;
	}
	public int d() {
		
		return id;
	}
}
