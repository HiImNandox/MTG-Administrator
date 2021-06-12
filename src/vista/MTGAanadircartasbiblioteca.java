package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bdd.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class MTGAanadircartasbiblioteca extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -422329475049576296L;
	private final JPanel contentPanel = new JPanel();
	public static String nombre;
	public static int id;
	public JComboBox<String> cmbBiblioteca = new JComboBox<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MTGAanadircartasbiblioteca dialog = new MTGAanadircartasbiblioteca(nombre, id);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("deprecation")
	public MTGAanadircartasbiblioteca(String nombree, int idd) {
		nombre=nombree;
		id=idd;
		setTitle("A\u00F1adir carta a biblioteca");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MTGAanadircartasbiblioteca.class.getResource("/img/logo.jpg")));
		setResizable(false);
		setBounds(100, 100, 323, 227);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cuantas copias de "+nombre+" quieres a\u00F1adir");
		lblNewLabel.setBounds(10, 11, 414, 14);
		contentPanel.add(lblNewLabel);
		
		JSpinner spinCantidad = new JSpinner();
		spinCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinCantidad.setBounds(10, 72, 48, 20);
		contentPanel.add(spinCantidad);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad");
		lblNewLabel_1.setBounds(10, 47, 82, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Biblioteca");
		lblNewLabel_2.setBounds(138, 47, 82, 14);
		contentPanel.add(lblNewLabel_2);
		
		
		cmbBiblioteca.setBounds(138, 71, 135, 22);
		contentPanel.add(cmbBiblioteca);
		llenarComboBox();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirmar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						agregarCartaABiblioteca((Integer) spinCantidad.getValue());
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}
	
	public void llenarComboBox() {
		
			ArrayList<String> lista = new ArrayList<String>();
			String q = "SELECT nombre from biblioteca where idUser ="+id;
			System.out.println(q);
			Connection conexion = Conexion.open();
			try {
				PreparedStatement sentencia = conexion.prepareStatement(q);
				ResultSet resultado = sentencia.executeQuery();
				while (resultado.next()) {
					lista.add(resultado.getString("nombre"));
				}
				System.out.println("Se han rellenado eertwtdfgcb");
			} catch (Exception e) {
				System.out.println(e);
			}
		for (int i = 0; i < lista.size(); i++) {
			cmbBiblioteca.addItem(lista.get(i));
		}
	}
	
	public void agregarCartaABiblioteca(int cantidad) {
		int contador = 0;
		for (int i = 0; i < cantidad; i++) {
			contador++;
			String consulta = "INSERT INTO conjuntodecartas (idBiblioteca,idCarta) VALUES ((SELECT idBiblioteca FROM biblioteca where nombre = '"+cmbBiblioteca.getSelectedItem().toString()+"'),(SELECT idCarta FROM cartas where nombre = '"+nombre+"'))";
			Connection conexion = Conexion.open();
			try {
				PreparedStatement sentencia = conexion.prepareStatement(consulta);
				if (sentencia.executeUpdate() == 1) {
					System.out.println("Se ha agregado "+i);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		JOptionPane.showMessageDialog(null, "Se ha añadido un total de "+contador+" copias");
		dispose();
	}
}
