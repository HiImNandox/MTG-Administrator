package vista.Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Result;

import bdd.Conexion;
import vista.MTGAanadircartas;
import vista.MTGAanadircartasbiblioteca;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MTGAcartas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTable tblPrincipal;
	private JTable tblSecundario;
	private JTextField txtNombre;
	public int id;
	public MTGAcartas panel = this;

	/**
	 * Create the panel.
	 */
	public MTGAcartas(int idd) {
		id=idd;
		System.out.println(id);
		setLayout(null);

		JScrollPane scrollPrincipal = new JScrollPane();
		scrollPrincipal.setBounds(10, 220, 755, 438);
		add(scrollPrincipal);

		tblPrincipal = new JTable();
		tblPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				llenarTablaDueno(tblSecundario);
				System.out.println("CLICK");
			}
		});
		tblPrincipal.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "Nombre", "Tipo De Carta", "Subtipo", "Expansion", "Color" }));
		scrollPrincipal.setViewportView(tblPrincipal);

		JLabel lblFoto = new JLabel("Aqui iria una foto\r\n");
		lblFoto.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblFoto.setBounds(806, 42, 255, 361);
		add(lblFoto);

		JScrollPane scrollSecundario = new JScrollPane();
		scrollSecundario.setBounds(809, 432, 252, 211);
		add(scrollSecundario);

		tblSecundario = new JTable();
		tblSecundario.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Due\u00F1o", "Biblioteca" }));
		scrollSecundario.setViewportView(tblSecundario);

		JPanel panelBuscador = new JPanel();
		panelBuscador.setBorder(new TitledBorder(null, "Buscador", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelBuscador.setBounds(10, 36, 755, 142);
		add(panelBuscador);
		panelBuscador.setLayout(null);

		JButton btnBuscarCarta = new JButton("Buscar");

		btnBuscarCarta.setBounds(656, 101, 89, 23);
		panelBuscador.add(btnBuscarCarta);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 32, 46, 14);
		panelBuscador.add(lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.setBounds(10, 50, 212, 20);
		panelBuscador.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tipo de Carta");
		lblNewLabel_1.setBounds(254, 32, 145, 14);
		panelBuscador.add(lblNewLabel_1);

		JComboBox<String> cmbtipodecarta = new JComboBox<String>();
		cmbtipodecarta.setBounds(254, 49, 145, 22);
		panelBuscador.add(cmbtipodecarta);

		JLabel lblNewLabel_2 = new JLabel("Subtipo");
		lblNewLabel_2.setBounds(431, 32, 64, 14);
		panelBuscador.add(lblNewLabel_2);

		JComboBox<String> cmbsubtipo = new JComboBox<String>();
		cmbsubtipo.setBounds(431, 49, 145, 22);
		panelBuscador.add(cmbsubtipo);

		JLabel lblNewLabel_3 = new JLabel("Expansion");
		lblNewLabel_3.setBounds(10, 81, 73, 14);
		panelBuscador.add(lblNewLabel_3);

		JComboBox<String> cmbexpansion = new JComboBox<String>();
		cmbexpansion.setBounds(10, 101, 212, 22);
		panelBuscador.add(cmbexpansion);

		JLabel lblNewLabel_4 = new JLabel("Color");
		lblNewLabel_4.setBounds(254, 81, 46, 14);
		panelBuscador.add(lblNewLabel_4);

		JComboBox<String> cmbcolor = new JComboBox<String>();
		cmbcolor.setBounds(254, 101, 145, 22);
		panelBuscador.add(cmbcolor);

		JLabel lblNewLabel_5 = new JLabel("Due\u00F1o");
		lblNewLabel_5.setBounds(431, 82, 46, 14);
		panelBuscador.add(lblNewLabel_5);

		JComboBox<String> cmbdueno = new JComboBox<String>();
		cmbdueno.setBounds(431, 101, 145, 22);
		panelBuscador.add(cmbdueno);

		poputTable();
		llenarTabla(
				"Select cartas.nombre, tipodecarta.nombre as tipodecarta, subtipo.nombre as subtipo, expansion.nombre as expansion, color.nombre as color from cartas JOIN tipodecarta USING(idTipoDeCarta) JOIN subtipo USING(idSubtipo) JOIN expansion USING(idExpansion) JOIN color USING(idColor)",
				tblPrincipal);
		llenarTodosLosCombobox(cmbexpansion, cmbsubtipo, cmbtipodecarta, cmbcolor, cmbdueno);
		btnBuscarCarta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cmbsubtipo.getSelectedItem().toString().equals("Todos")
						&& cmbtipodecarta.getSelectedItem().toString().equals("Todos")
						&& cmbcolor.getSelectedItem().toString().equals("Todos")
						&& cmbexpansion.getSelectedItem().toString().equals("Todos")) {
					llenarTabla(
							"Select cartas.nombre, tipodecarta.nombre as tipodecarta, subtipo.nombre as subtipo, expansion.nombre as expansion, color.nombre as color from cartas JOIN tipodecarta USING(idTipoDeCarta) JOIN subtipo USING(idSubtipo) JOIN expansion USING(idExpansion) JOIN color USING(idColor)",
							tblPrincipal);
				} else {
					String consulta = "Select cartas.nombre, tipodecarta.nombre as tipodecarta, subtipo.nombre as subtipo, expansion.nombre as expansion, color.nombre as color from cartas JOIN tipodecarta USING(idTipoDeCarta) JOIN subtipo USING(idSubtipo) JOIN expansion USING(idExpansion) JOIN color USING(idColor) WHERE";
					boolean nomaswhere = false;
					if (!txtNombre.getText().equals("")) {
						if (nomaswhere == false) {
							nomaswhere = true;
						}
						consulta += " cartas.nombre = '" + txtNombre.getText() + "'";
					}

					if (!cmbsubtipo.getSelectedItem().toString().equals("Todos")) {
						if (nomaswhere == false) {
							nomaswhere = true;
						} else {
							consulta += " and";
						}
						consulta += " subtipo.nombre = '" + cmbsubtipo.getSelectedItem().toString() + "'";

					}

					if (!cmbtipodecarta.getSelectedItem().toString().equals("Todos")) {
						if (nomaswhere == false) {
							nomaswhere = true;
						} else {
							consulta += " and";
						}
						consulta += " tipodecarta.nombre = '" + cmbtipodecarta.getSelectedItem().toString() + "'";

					}

					if (!cmbexpansion.getSelectedItem().toString().equals("Todos")) {
						if (nomaswhere == false) {
							nomaswhere = true;
						} else {
							consulta += " and";
						}
						consulta += " expansion.nombre = '" + cmbexpansion.getSelectedItem().toString() + "'";

					}

					if (!cmbcolor.getSelectedItem().toString().equals("Todos")) {
						if (nomaswhere == false) {
							nomaswhere = true;
						} else {
							consulta += " and";
						}
						consulta += " color.nombre = '" + cmbcolor.getSelectedItem().toString() + "'";
					}

					System.out.println(consulta);
					llenarTabla(consulta, tblPrincipal);
				}

			}

		});
	}

	

	public void llenarTabla(String consulta, JTable tabla) {
		DefaultTableModel modelo;
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
				modelo.addRow(fila);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		tabla.setModel(modelo);
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

	public static ArrayList<String> llenarDueno() {
		ArrayList<String> lista = new ArrayList<String>();
		String q = "SELECT * from usuarios";
		Connection conexion = Conexion.open();
		try {
			PreparedStatement sentencia = conexion.prepareStatement(q);
			ResultSet resultado = sentencia.executeQuery();
			lista.add("Cualquiera");
			while (resultado.next()) {
				lista.add(resultado.getString("usuario"));
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
		lista = llenarDueno();
		for (int i = 0; i < lista.size(); i++) {
			dueno.addItem(lista.get(i));
		}
	}

	public int conseguirID() {
		int id = 0;
		String buscar = (String) tblPrincipal.getModel().getValueAt(tblPrincipal.getSelectedRow(), 0);
		System.out.println(buscar);
		String q = "SELECT idCarta from cartas where nombre = ?";
		Connection conexion = Conexion.open();
		try {
			System.out.println(q);
			PreparedStatement sentencia = conexion.prepareStatement(q);
			sentencia.setString(1,buscar);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				id = resultado.getInt("idCarta");
			}
			System.out.println("Se han pasado la id");
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}
	public void poputTable() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem menuItem1 = new JMenuItem("Crear carta");
		JMenuItem menuItem2 = new JMenuItem("Añadir carta a biblioteca");
		JMenuItem menuItem3 = new JMenuItem("Editar carta");

		menuItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MTGAanadircartas a = new MTGAanadircartas(0);
				a.setVisible(true);
			}
		});

		menuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("CUIDAAAAOOO  " + id);
				MTGAanadircartasbiblioteca a = new MTGAanadircartasbiblioteca( (String) tblPrincipal.getModel().getValueAt(tblPrincipal.getSelectedRow(), 0), id);
				a.setVisible(true);
				
			}	
		});

		menuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tblPrincipal.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null,"Tienes que seleccionar la carta primero","Error",JOptionPane.ERROR_MESSAGE);
				}else {
					int id = conseguirID();
					if (id == 0) {
						JOptionPane.showMessageDialog(null,"Hay un error con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
					}else {
						MTGAanadircartas a = new MTGAanadircartas(id);
						a.setVisible(true);
					}

				}
			}
		});
			
		popMenu.add(menuItem1);
		popMenu.add(menuItem2);
		popMenu.add(menuItem3);
		tblPrincipal.setComponentPopupMenu(popMenu);
	}
	
	public void llenarTablaDueno(JTable tabla) {
		int id = conseguirID();
		DefaultTableModel modelo;
		String consulta = "Select biblioteca.nombre as dueño from conjuntodecartas JOIN biblioteca USING(idBiblioteca) where idCarta = "+id;
		String[] columnas = { "Dueño", "Biblioteca" };
		modelo = new DefaultTableModel(null, columnas);
		String[] fila = new String[5];
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				fila[0] = rs.getString("dueño");
				fila[1] = obtenerUsuario(rs.getString("dueño"));
				modelo.addRow(fila);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		tabla.setModel(modelo);
		
	}
	
	public String obtenerUsuario(String nombre) {
		String consulta = "Select usuarios.usuario as nombre FROM biblioteca JOIN usuarios USING(idUser) where biblioteca.nombre ='"+nombre+"'";
		String devolver = new String();
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				devolver = rs.getString("nombre");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return devolver;
	}
}
