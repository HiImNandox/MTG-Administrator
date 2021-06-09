package vista.Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class MTGAbiblioteca extends JPanel {
	private JTextField textField;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public MTGAbiblioteca() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 260, 1018, 373);
		add(scrollPane);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(scrollPane, popupMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ver contenido de biblioteca");
		popupMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Eliminar biblioteca");
		popupMenu.add(mntmNewMenuItem_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Due\u00F1o", "Tipo de Biblioteca", "Cantidad de Cartas"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panelBuscador = new JPanel();
		panelBuscador.setLayout(null);
		panelBuscador.setBorder(new TitledBorder(null, "Buscador", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelBuscador.setBounds(25, 73, 1018, 129);
		add(panelBuscador);
		
		JButton btnBuscarCarta = new JButton("Buscar");
		btnBuscarCarta.setBounds(877, 69, 89, 23);
		panelBuscador.add(btnBuscarCarta);
		
		JLabel lblNewLabel_1 = new JLabel("Due\u00F1o");
		lblNewLabel_1.setBounds(256, 44, 73, 14);
		panelBuscador.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(256, 69, 145, 22);
		panelBuscador.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo De Biblioteca");
		lblNewLabel_2.setBounds(448, 44, 145, 14);
		panelBuscador.add(lblNewLabel_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(448, 69, 145, 22);
		panelBuscador.add(comboBox_1);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre ");
		lblNewLabel_4.setBounds(10, 44, 113, 14);
		panelBuscador.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(10, 70, 212, 20);
		panelBuscador.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(649, 69, 145, 22);
		panelBuscador.add(comboBox_1_1);
		
		JLabel lblNewLabel_5 = new JLabel("Biblioteca");
		lblNewLabel_5.setBounds(649, 49, 145, 14);
		panelBuscador.add(lblNewLabel_5);

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
