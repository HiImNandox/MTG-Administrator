package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bdd.Conexion;
import controlador.Cartas;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MTGAanadircartas extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCoste;
	public JSpinner spinFuerza = new JSpinner();
	public JSpinner spinResistencia = new JSpinner();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTGAanadircartas frame = new MTGAanadircartas();
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
	public MTGAanadircartas() {
		setTitle("A\u00F1adir Carta");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MTGAanadircartas.class.getResource("/img/logo.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 36, 220, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Coste");
		lblNewLabel_1.setBounds(280, 11, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtCoste = new JTextField();
		txtCoste.setBounds(280, 36, 122, 20);
		contentPane.add(txtCoste);
		txtCoste.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Expansion");
		lblNewLabel_2.setBounds(10, 67, 66, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox<String> cmbExpansion = new JComboBox<String>();
		cmbExpansion.setBounds(10, 92, 220, 22);
		contentPane.add(cmbExpansion);
		
		JLabel lblNewLabel_3 = new JLabel("Rareza");
		lblNewLabel_3.setBounds(280, 67, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JComboBox<String> cmbRareza = new JComboBox<String>();
		cmbRareza.setBounds(280, 92, 122, 22);
		contentPane.add(cmbRareza);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo De Carta");
		lblNewLabel_4.setBounds(10, 125, 92, 14);
		contentPane.add(lblNewLabel_4);
		
		JComboBox<String> cmbTipoDeCarta = new JComboBox<String>();
		cmbTipoDeCarta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cmbTipoDeCarta.getSelectedItem().toString().equals("Encantamiento")) {
					spinFuerza.setEnabled(false);
					spinFuerza.setEnabled(false);
				}
			}
		});

		cmbTipoDeCarta.setBounds(10, 150, 220, 22);
		contentPane.add(cmbTipoDeCarta);
		
		JLabel lblNewLabel_5 = new JLabel("Subtipo");
		lblNewLabel_5.setBounds(280, 125, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JComboBox<String> cmbSubtipo = new JComboBox<String>();
		cmbSubtipo.setBounds(280, 150, 122, 22);
		contentPane.add(cmbSubtipo);
		
		JLabel lblNewLabel_6 = new JLabel("Fuerza");
		lblNewLabel_6.setBounds(30, 199, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		
		spinFuerza.setModel(new SpinnerNumberModel(new Integer(0), new Integer(-1), null, new Integer(1)));
		spinFuerza.setBounds(30, 224, 46, 20);
		contentPane.add(spinFuerza);
		
		JLabel lblNewLabel_7 = new JLabel("Resistencia");
		lblNewLabel_7.setBounds(301, 199, 101, 14);
		contentPane.add(lblNewLabel_7);
		
		
		spinResistencia.setModel(new SpinnerNumberModel(new Integer(0), new Integer(-1), null, new Integer(1)));
		spinResistencia.setBounds(311, 224, 46, 20);
		contentPane.add(spinResistencia);
		
		JLabel lblNewLabel_8 = new JLabel("Color");
		lblNewLabel_8.setBounds(163, 199, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		JComboBox<String> cmbColor = new JComboBox<String>();
		cmbColor.setBounds(114, 223, 153, 22);
		contentPane.add(cmbColor);
		
		JButton btnCrear = new JButton("Crear Carta");
		btnCrear.setBounds(151, 543, 116, 23);
		contentPane.add(btnCrear);
		
		JLabel lblNewLabel_9 = new JLabel("Texto de la carta");
		lblNewLabel_9.setBounds(10, 282, 135, 14);
		contentPane.add(lblNewLabel_9);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 307, 392, 225);
		contentPane.add(scrollPane);
		
		JTextPane txtpaneTextoDeCarta = new JTextPane();
		scrollPane.setViewportView(txtpaneTextoDeCarta);
		

		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cartas carta = new Cartas(txtNombre.getText(),txtCoste.getText(),cmbColor.getSelectedItem().toString(),(Integer) spinFuerza.getValue(),(Integer) spinResistencia.getValue(),cmbTipoDeCarta.getSelectedItem().toString(),cmbSubtipo.getSelectedItem().toString(),cmbRareza.getSelectedItem().toString(),cmbExpansion.getSelectedItem().toString(),txtpaneTextoDeCarta.getText());

				if (!carta.anadirCartas()) {
					JOptionPane.showMessageDialog (null, "Esta carta ya existe", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		
		llenarTodosLosCombobox(cmbExpansion,cmbRareza,cmbSubtipo,cmbTipoDeCarta,cmbColor);
		
	}
	
    public static ArrayList<String> llenarCombo(String tabla){
    	
        ArrayList<String> lista = new ArrayList<String>();
        String q = "SELECT nombre from "+tabla;
        Connection conexion = Conexion.open();
        try {
        	PreparedStatement sentencia = conexion.prepareStatement(q);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
            	lista.add(resultado.getString("nombre"));
            }
            System.out.println("Se han rellenado el combobox");
        } catch (Exception e) {
            System.out.println(e);
        }
       
        return lista;
    }

    public void llenarTodosLosCombobox(JComboBox <String> expansion, JComboBox <String> rareza, JComboBox <String> subtipo, JComboBox <String> tipodecarta, JComboBox <String> color) {
    	ArrayList<String> lista = llenarCombo("expansion");
    	for (int i = 0;i<lista.size();i++) {
    		expansion.addItem(lista.get(i));
    	}
    	
    	lista = llenarCombo("rareza");
    	for (int i = 0;i<lista.size();i++) {
    		rareza.addItem(lista.get(i));
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
    }
}
