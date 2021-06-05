package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class MTGAanadircartas extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 220, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Coste");
		lblNewLabel_1.setBounds(280, 11, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(280, 36, 122, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Expansion");
		lblNewLabel_2.setBounds(10, 67, 66, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 92, 220, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Rareza");
		lblNewLabel_3.setBounds(280, 67, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(280, 92, 122, 22);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo De Carta");
		lblNewLabel_4.setBounds(10, 125, 92, 14);
		contentPane.add(lblNewLabel_4);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(10, 150, 220, 22);
		contentPane.add(comboBox_2);
		
		JLabel lblNewLabel_5 = new JLabel("Subtipo");
		lblNewLabel_5.setBounds(280, 125, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(280, 150, 122, 22);
		contentPane.add(comboBox_3);
		
		JLabel lblNewLabel_6 = new JLabel("Fuerza");
		lblNewLabel_6.setBounds(30, 199, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(30, 224, 46, 20);
		contentPane.add(spinner);
		
		JLabel lblNewLabel_7 = new JLabel("Resistencia");
		lblNewLabel_7.setBounds(301, 199, 101, 14);
		contentPane.add(lblNewLabel_7);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(311, 224, 46, 20);
		contentPane.add(spinner_1);
		
		JLabel lblNewLabel_8 = new JLabel("Color");
		lblNewLabel_8.setBounds(187, 199, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(166, 223, 78, 22);
		contentPane.add(comboBox_4);
		
		JButton btnNewButton = new JButton("Crear Carta");
		btnNewButton.setBounds(151, 543, 116, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_9 = new JLabel("Texto de la carta");
		lblNewLabel_9.setBounds(10, 282, 135, 14);
		contentPane.add(lblNewLabel_9);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 307, 392, 225);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
	}
}
