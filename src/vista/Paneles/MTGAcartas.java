package vista.Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vista.MTGAanadircartas;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		lblNewLabel_1.setBounds(254, 32, 73, 14);
		panelBuscador.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(254, 49, 145, 22);
		panelBuscador.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Subtipo");
		lblNewLabel_2.setBounds(431, 32, 64, 14);
		panelBuscador.add(lblNewLabel_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(431, 49, 145, 22);
		panelBuscador.add(comboBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("Expansion");
		lblNewLabel_3.setBounds(10, 81, 73, 14);
		panelBuscador.add(lblNewLabel_3);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(10, 101, 212, 22);
		panelBuscador.add(comboBox_2);
		
		JLabel lblNewLabel_4 = new JLabel("Color");
		lblNewLabel_4.setBounds(254, 81, 46, 14);
		panelBuscador.add(lblNewLabel_4);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(254, 101, 145, 22);
		panelBuscador.add(comboBox_3);
		
		JLabel lblNewLabel_5 = new JLabel("Due\u00F1o");
		lblNewLabel_5.setBounds(431, 82, 46, 14);
		panelBuscador.add(lblNewLabel_5);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(431, 101, 145, 22);
		panelBuscador.add(comboBox_1_1);

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
}
