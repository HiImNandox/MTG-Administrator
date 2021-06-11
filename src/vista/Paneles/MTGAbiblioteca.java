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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MTGAbiblioteca extends JPanel {
	private JTextField textField;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public MTGAbiblioteca() {
		setLayout(null);
		
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

		btnBuscarCarta.setBounds(845, 69, 89, 23);
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
		
		textField = new JTextField();
		textField.setBounds(39, 70, 212, 20);
		panelBuscador.add(textField);
		textField.setColumns(10);
		
		btnBuscarCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbDueno.getSelectedItem().toString().equals("Todos") && cmbTipoDeBiblioteca.getSelectedItem().toString().equals("Todos")) {
					llenarTabla("SELECT biblioteca.nombre, usuarios.usuario as usuario, tipodebiblioteca.nombre as tipodebiblioteca from biblioteca JOIN tipodebiblioteca USING(idTipoDeBiblioteca) JOIN usuarios USING(idUser)", table);
				}else {
					String consulta = "SELECT biblioteca.nombre, usuarios.usuario as usuario, tipodebiblioteca.nombre as tipodebiblioteca from biblioteca JOIN tipodebiblioteca USING(idTipoDeBiblioteca) JOIN usuarios USING(idUser) WHERE";
					boolean nomaswhere = false;

					if (!cmbDueno.getSelectedItem().toString().equals("Todos")) {
						if (nomaswhere == false) {
							nomaswhere = true;
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

		llenarTabla("SELECT biblioteca.nombre, usuarios.usuario as usuario, tipodebiblioteca.nombre as tipodebiblioteca from biblioteca JOIN tipodebiblioteca USING(idTipoDeBiblioteca) JOIN usuarios USING(idUser)", table);
		llenarTodosLosCombobox(cmbDueno, cmbTipoDeBiblioteca);
		poputTable();
	}

	
	public void llenarTabla(String consulta, JTable tabla) {
		DefaultTableModel modelo;
		String[] columnas = { "Nombre", "Dueño", "Tipo de Biblioteca", "Cantidad de Cartas" };
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
				MTGAverbiblioteca a = new MTGAverbiblioteca();
				a.setVisible(true);
			}
		});

		menuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("lo has pulsado1");
			}
		});

		menuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("lo has pulsado2");
			}
		});
		
			
		popMenu.add(menuItem1);
		popMenu.add(menuItem2);
		popMenu.add(menuItem3);
		table.setComponentPopupMenu(popMenu);
	}
}
