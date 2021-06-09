package vista.Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bdd.Conexion;
import vista.MTGAanadircartas;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MTGAcartas extends JPanel {
	private JTable tblPrincipal;
	private JTable tblSecundario;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public MTGAcartas() {
		setLayout(null);
		
		JScrollPane scrollPrincipal = new JScrollPane();
		scrollPrincipal.setBounds(10, 220, 755, 438);
		add(scrollPrincipal);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(scrollPrincipal, popupMenu);
		
		JMenuItem mntmAnadirCarta = new JMenuItem("Crear nueva carta");
		mntmAnadirCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MTGAanadircartas a = new MTGAanadircartas();
				a.setVisible(true);
			}
		});
		popupMenu.add(mntmAnadirCarta);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("A\u00F1adir carta a biblioteca\r\n");
		popupMenu.add(mntmNewMenuItem);
		
		tblPrincipal = new JTable();
		tblPrincipal.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Nombre", "Tipo De Carta", "Subtipo", "Expansion", "Color"
			}
		));
		scrollPrincipal.setViewportView(tblPrincipal);
		
		JLabel lblFoto = new JLabel("Aqui iria una foto\r\n");
		lblFoto.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblFoto.setBounds(806, 42, 255, 361);
		add(lblFoto);
		
		JScrollPane scrollSecundario = new JScrollPane();
		scrollSecundario.setBounds(809, 432, 252, 211);
		add(scrollSecundario);
		
		tblSecundario = new JTable();
		tblSecundario.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Due\u00F1o", "Biblioteca"
			}
		));
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
		
		textField = new JTextField();
		textField.setBounds(10, 50, 212, 20);
		panelBuscador.add(textField);
		textField.setColumns(10);
		
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

		llenarTabla("Select cartas.nombre, tipodecarta.nombre as tipodecarta, subtipo.nombre as subtipo, expansion.nombre as expansion, color.nombre as color from cartas JOIN tipodecarta USING(idTipoDeCarta) JOIN subtipo USING(idSubtipo) JOIN expansion USING(idExpansion) JOIN color USING(idColor)",tblPrincipal);
		llenarTodosLosCombobox(cmbexpansion,cmbsubtipo,cmbtipodecarta,cmbcolor,cmbdueno);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public void llenarTabla(String consulta, JTable tabla) {
		DefaultTableModel modelo;
		String[] columnas = {"Nombre","Tipo de carta","Subtipo","Expansion","Color"};
		modelo = new DefaultTableModel(null,columnas);
		String[] fila = new String[5];
		Connection conexion = Conexion.open();
		try {
			PreparedStatement pst =conexion.prepareStatement(consulta);
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

    public static ArrayList<String> llenarCombo(String tabla){
    	
        ArrayList<String> lista = new ArrayList<String>();
        String q = "SELECT nombre from "+tabla;
        Connection conexion = Conexion.open();
        try {
        	PreparedStatement sentencia = conexion.prepareStatement(q);
            ResultSet resultado = sentencia.executeQuery();
            lista.add("Todos");
            while(resultado.next()) {
            	lista.add(resultado.getString("nombre"));
            }
            System.out.println("Se han rellenado el combobox");
        } catch (Exception e) {
            System.out.println(e);
        }
       
        return lista;
    }
    
    public static ArrayList<String> llenarDueno(){
        ArrayList<String> lista = new ArrayList<String>();
        String q = "SELECT * from usuarios";
        Connection conexion = Conexion.open();
        try {
        	PreparedStatement sentencia = conexion.prepareStatement(q);
            ResultSet resultado = sentencia.executeQuery();
            lista.add("Cualquiera");
            while(resultado.next()) {
            	lista.add(resultado.getString("usuario"));
            }
            System.out.println("Se han rellenado el combobox");
        } catch (Exception e) {
            System.out.println(e);
        }
       
        return lista;
    }
    public void llenarTodosLosCombobox(JComboBox <String> expansion, JComboBox <String> subtipo, JComboBox <String> tipodecarta, JComboBox <String> color,JComboBox <String> dueno) {
    	ArrayList<String> lista = llenarCombo("expansion");
    	for (int i = 0;i<lista.size();i++) {
    		expansion.addItem(lista.get(i));
    	}
    	
    	lista = llenarCombo("subtipo");
    	for (int i = 0;i<lista.size();i++) {
    		subtipo.addItem(lista.get(i));
    	}
    	
    	lista = llenarCombo("tipodecarta");
    	for (int i = 0;i<lista.size();i++) {
    		tipodecarta.addItem(lista.get(i));
    	}
    	lista = llenarCombo("color");
    	for (int i = 0;i<lista.size();i++) {
    		color.addItem(lista.get(i));
    	}
    	lista = llenarDueno();
    	for (int i = 0;i<lista.size();i++) {
    		dueno.addItem(lista.get(i));
    	}
    }
}
