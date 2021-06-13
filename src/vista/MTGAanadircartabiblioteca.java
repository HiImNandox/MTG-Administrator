package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import bdd.Conexion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MTGAanadircartabiblioteca extends JFrame {

	private JPanel contentPane;
	public static String nombre;
	private JTable table;
	private JTable table_1;
	public ArrayList<String> lista = new ArrayList<String>();
	public static JButton boton;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTGAanadircartabiblioteca frame = new MTGAanadircartabiblioteca(nombre,boton);
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
	public MTGAanadircartabiblioteca(String nombree, JButton botonn) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MTGAanadircartabiblioteca.class.getResource("/img/logo.jpg")));
		setTitle("A\u00F1adir cartas a "+nombre);
		boton = botonn;
		setResizable(false);
		nombre = nombree;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 918, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 48, 349, 280);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(517, 48, 349, 280);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JButton btnAnadir = new JButton("+");

		btnAnadir.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnAnadir.setBounds(424, 104, 59, 35);
		contentPane.add(btnAnadir);
		
		JButton btnQuitar = new JButton("-");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicion = table_1.getSelectedRow();
				lista.remove(posicion);
				llenarTabla(table_1);
			}
		});
		btnQuitar.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnQuitar.setBounds(424, 225, 59, 35);
		contentPane.add(btnQuitar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_1.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null,"Para añadir cartas debes introducir almenos una","Error",JOptionPane.ERROR_MESSAGE);
				}else {
					for (int i = 0; i < lista.size(); i++) {
						Connection conexion = Conexion.open();
						String consulta = "INSERT INTO conjuntodecartas (idCarta, idBiblioteca) VALUES ((SELECT idCarta FROM cartas where nombre = '"+lista.get(i)+"'),(SELECT idBiblioteca FROM biblioteca where nombre = '"+nombre+"'))";
						try {
							PreparedStatement pst = conexion.prepareStatement(consulta);
							pst.executeUpdate();

							
						} catch (Exception f) {
							System.out.println(f);
						}
					}
					JOptionPane.showMessageDialog(null, "Se han añadido las cartas a "+nombre);
					botonn.doClick();
					dispose();
				}
				}
				
		});
		btnAceptar.setBounds(373, 365, 85, 21);
		contentPane.add(btnAceptar);
		
		JButton btnNewButton_3 = new JButton("Cancelar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setBounds(483, 365, 85, 21);
		contentPane.add(btnNewButton_3);
		rellenarTabla(table);
		
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
				lista.add(nombre);
				llenarTabla(table_1);
			}
		});
	}
	
	public void rellenarTabla(JTable tabla) {
		String consulta = "SELECT * from cartas ORDER BY nombre";
		DefaultTableModel modelo = new DefaultTableModel();
		String[] columnas = { "Nombre" };
		modelo = new DefaultTableModel(null, columnas);
		String[] fila = new String[1];
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst = conexion.prepareStatement(consulta);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				fila[0] = rs.getString("nombre");
				modelo.addRow(fila);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		tabla.setModel(modelo);
	}
	
	public void llenarTabla(JTable tabla) {
		DefaultTableModel modelo;
		String[] columnas = { "Nombre" };
		modelo = new DefaultTableModel(null, columnas);
		String[] fila = new String[1];
		for (int i = 0; i < lista.size(); i++) {
			fila[0] = lista.get(i);
			modelo.addRow(fila);
		}
		tabla.setModel(modelo);
	}
}
