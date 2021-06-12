package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bdd.Conexion;
import java.awt.Toolkit;

public class MTGAverbiblioteca extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTable table;
	public static String nombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTGAverbiblioteca frame = new MTGAverbiblioteca(nombre);
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
	public MTGAverbiblioteca(String nombres) {
		nombre = nombres;
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
		btnBuscarCarta.setBounds(656, 101, 89, 23);
		panelBuscador.add(btnBuscarCarta);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 32, 46, 14);
		panelBuscador.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(10, 50, 212, 20);
		panelBuscador.add(txtNombre);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de Carta");
		lblNewLabel_1.setBounds(254, 32, 73, 14);
		panelBuscador.add(lblNewLabel_1);
		
		JComboBox cmbTipoDeCarta = new JComboBox();
		cmbTipoDeCarta.setBounds(254, 49, 145, 22);
		panelBuscador.add(cmbTipoDeCarta);
		
		JLabel lblNewLabel_2 = new JLabel("Subtipo");
		lblNewLabel_2.setBounds(431, 32, 64, 14);
		panelBuscador.add(lblNewLabel_2);
		
		JComboBox cmbSubtipo = new JComboBox();
		cmbSubtipo.setBounds(431, 49, 145, 22);
		panelBuscador.add(cmbSubtipo);
		
		JLabel lblNewLabel_3 = new JLabel("Expansion");
		lblNewLabel_3.setBounds(10, 81, 73, 14);
		panelBuscador.add(lblNewLabel_3);
		
		JComboBox cmbExpansion = new JComboBox();
		cmbExpansion.setBounds(10, 101, 212, 22);
		panelBuscador.add(cmbExpansion);
		
		JLabel lblNewLabel_4 = new JLabel("Color");
		lblNewLabel_4.setBounds(254, 81, 46, 14);
		panelBuscador.add(lblNewLabel_4);
		
		JComboBox cmbColor = new JComboBox();
		cmbColor.setBounds(254, 101, 145, 22);
		panelBuscador.add(cmbColor);
		
		JButton btnNewButton = new JButton("A\u00F1adir carta");
		btnNewButton.setBounds(849, 77, 135, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar Carta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(849, 135, 135, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 1032, 376);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre"
			}
		));
		scrollPane.setViewportView(table);
		String consulta = "SELECT cartas.nombre, expansion.nombre as expansion, tipodecarta.nombre as tipodecarta , subtipo.nombre as subtipo, color.nombre as color FROM cartas JOIN conjuntodecartas USING (idCarta) JOIN expansion USING (idExpansion) JOIN tipodecarta USING (idTipoDeCarta) JOIN subtipo USING (idSubtipo) JOIN color USING (idColor) JOIN biblioteca USING(idBiblioteca) WHERE biblioteca.nombre = '"+nombre+"'";
		llenarTablar(consulta, table);
	}
	
	public void llenarTablar(String consulta, JTable tabla) {
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
}
