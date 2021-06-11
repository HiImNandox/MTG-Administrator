package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bdd.Conexion;
import controlador.Cartas;
import vista.Paneles.MTGAcartas;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MTGAanadircartas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCoste;
	public JSpinner spinFuerza = new JSpinner();
	public JSpinner spinResistencia = new JSpinner();
	static int buscar;
	public JComboBox<String> cmbExpansion = new JComboBox<String>();
	public JComboBox<String> cmbTipoDeCarta = new JComboBox<String>();
	public JComboBox<String> cmbRareza = new JComboBox<String>();
	public JComboBox<String> cmbSubtipo = new JComboBox<String>();
	public JComboBox<String> cmbColor = new JComboBox<String>();
	public JTextPane txtpaneTextoDeCarta = new JTextPane();
	public JButton btnCrear = new JButton("Crear Carta");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTGAanadircartas frame = new MTGAanadircartas(buscar);
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
	@SuppressWarnings("deprecation")
	public MTGAanadircartas(int buscarr) {
		setResizable(false);
		buscar=buscarr;
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
		
		
		cmbExpansion.setBounds(10, 92, 220, 22);
		contentPane.add(cmbExpansion);
		
		JLabel lblNewLabel_3 = new JLabel("Rareza");
		lblNewLabel_3.setBounds(280, 67, 46, 14);
		contentPane.add(lblNewLabel_3);
		

		cmbRareza.setBounds(280, 92, 122, 22);
		contentPane.add(cmbRareza);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo De Carta");
		lblNewLabel_4.setBounds(10, 125, 92, 14);
		contentPane.add(lblNewLabel_4);
		

		cmbTipoDeCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbTipoDeCarta.getSelectedItem().toString().equals("Encantamiento")) {
					spinFuerza.setEnabled(false);
					spinResistencia.setEnabled(false);
				}
				if (cmbTipoDeCarta.getSelectedItem().toString().equals("Criatura")) {
					spinFuerza.setEnabled(true);
					spinResistencia.setEnabled(true);
				}
				if (cmbTipoDeCarta.getSelectedItem().toString().equals("Planeswalker")) {
					spinFuerza.setEnabled(false);
					spinResistencia.setEnabled(true);
				}
				if (cmbTipoDeCarta.getSelectedItem().toString().equals("Artefacto")) {
					spinFuerza.setEnabled(false);
					spinResistencia.setEnabled(false);
				}
				if (cmbTipoDeCarta.getSelectedItem().toString().equals("Tierra")) {
					spinFuerza.setEnabled(false);
					spinResistencia.setEnabled(false);
				}
			}
		});

		cmbTipoDeCarta.setBounds(10, 150, 220, 22);
		contentPane.add(cmbTipoDeCarta);
		
		JLabel lblNewLabel_5 = new JLabel("Subtipo");
		lblNewLabel_5.setBounds(280, 125, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		
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
		
		
		cmbColor.setBounds(114, 223, 153, 22);
		contentPane.add(cmbColor);
		
		
		btnCrear.setBounds(151, 543, 116, 23);
		contentPane.add(btnCrear);
		
		JLabel lblNewLabel_9 = new JLabel("Texto de la carta");
		lblNewLabel_9.setBounds(10, 282, 135, 14);
		contentPane.add(lblNewLabel_9);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 307, 392, 225);
		contentPane.add(scrollPane);
		

		scrollPane.setViewportView(txtpaneTextoDeCarta);
		

		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cartas carta = new Cartas(txtNombre.getText(),txtCoste.getText(),cmbColor.getSelectedItem().toString(),(Integer) spinFuerza.getValue(),(Integer) spinResistencia.getValue(),cmbTipoDeCarta.getSelectedItem().toString(),cmbSubtipo.getSelectedItem().toString(),cmbRareza.getSelectedItem().toString(),cmbExpansion.getSelectedItem().toString(),txtpaneTextoDeCarta.getText());
				if (buscar == 0) {
					if (!carta.anadirCartas()) {
						JOptionPane.showMessageDialog (null, "Esta carta ya existe", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						dispose();
					}
				}else {
					if (carta.editarCarta(cmbColor.getSelectedIndex()+1, cmbTipoDeCarta.getSelectedIndex()+1, cmbRareza.getSelectedIndex()+1, cmbSubtipo.getSelectedIndex()+1, cmbExpansion.getSelectedIndex()+1, buscarr)) {
						dispose();
					}
				}
			}
		});

		
		llenarTodosLosCombobox(cmbExpansion,cmbRareza,cmbSubtipo,cmbTipoDeCarta,cmbColor);
		if (buscar != 0) {
			editarCartas();
		}
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

    public void editarCartas() {
    	String consulta = "SELECT * FROM cartas WHERE idCarta = "+buscar;
    	Connection conexion = Conexion.open();
    	try {
        	PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
            	txtNombre.setText(resultado.getString("nombre"));
            	txtCoste.setText(resultado.getString("coste"));
            	cmbExpansion.setSelectedIndex(resultado.getInt("idExpansion")-1);;
            	cmbRareza.setSelectedIndex(resultado.getInt("idRareza")-1);
            	cmbSubtipo.setSelectedIndex(resultado.getInt("idSubTipo")-1);
            	cmbColor.setSelectedIndex(resultado.getInt("idColor")-1);
            	cmbTipoDeCarta.setSelectedIndex(resultado.getInt("idTipoDeCarta")-1);
            	txtpaneTextoDeCarta.setText(resultado.getString("textodelacarta"));
            	ponerfuerzayresistencia(resultado.getString("fuerzairesistencia"));
            	btnCrear.setText("Editar Carta");
            }
            System.out.println("Se han rellenado el combobox");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @SuppressWarnings("deprecation")
	public void ponerfuerzayresistencia(String texto) {
    	if (cmbTipoDeCarta.getSelectedItem().toString().equals("Criatura")) {
			String[] fuerzaresistencia = texto.split("/");
			spinFuerza.setValue(Integer.valueOf(fuerzaresistencia[0]));
			spinResistencia.setValue(Integer.valueOf(fuerzaresistencia[1]));
		}else if (cmbTipoDeCarta.getSelectedItem().toString().equals("Planeswalker")) {
			spinResistencia.setValue(Integer.valueOf(texto));
		}else {
			spinFuerza.disable();
			spinResistencia.disable();
		}
    	
    }
}
