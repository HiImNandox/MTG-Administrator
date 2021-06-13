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

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MTGAanadirbiblioteca extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	public static int id;
	public static JButton buton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MTGAanadirbiblioteca dialog = new MTGAanadirbiblioteca(id,buton);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MTGAanadirbiblioteca(int idd, JButton botonn) {
		id = idd;
		setTitle("A\u00F1adir biblioteca");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MTGAanadirbiblioteca.class.getResource("/img/logo.jpg")));
		setResizable(false);
		setBounds(100, 100, 314, 228);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de la biblioteca");
		lblNewLabel.setBounds(28, 35, 227, 13);
		contentPanel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(28, 58, 227, 19);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de Biblioteca");
		lblNewLabel_1.setBounds(28, 105, 227, 13);
		contentPanel.add(lblNewLabel_1);
		
		JComboBox<String> cmbTioBiblioteca = new JComboBox<String>();
		cmbTioBiblioteca.setBounds(28, 128, 227, 21);
		contentPanel.add(cmbTioBiblioteca);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear Biblioteca");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String consulta = "INSERT INTO biblioteca (nombre, idUser, IdTipoDeBiblioteca) VALUES (?,?,(SELECT idTipoDeBiblioteca FROM tipodebiblioteca where nombre = '"+cmbTioBiblioteca.getSelectedItem().toString()+"'))";
						Connection conexion = Conexion.open();
						try {
							PreparedStatement pst = conexion.prepareStatement(consulta);
							pst.setString(1, txtNombre.getText());
							pst.setInt(2, id);
							
							if (pst.executeUpdate() == 1) {
								JOptionPane.showMessageDialog(null, "Tu biblioteca ha sido creado correctamente");
								botonn.doClick();
								dispose();
							}else {
								System.out.println("Ha habido un error");
							}
							
						} catch (Exception f) {
							System.out.println(f);
						}
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
		llenarCombo(cmbTioBiblioteca);
	}
	
	public static void llenarCombo(JComboBox<String> cmbTioBiblioteca) {

		ArrayList<String> lista = new ArrayList<String>();
		String q = "SELECT nombre FROM tipodebiblioteca";
		Connection conexion = Conexion.open();
		try {
			PreparedStatement sentencia = conexion.prepareStatement(q);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				lista.add(resultado.getString("nombre"));
			}
			System.out.println("Se han rellenado el combobox");
		} catch (Exception e) {
			System.out.println(e);
		}
		for (int i = 0; i < lista.size(); i++) {
			cmbTioBiblioteca.addItem(lista.get(i));
		}
	}
}
