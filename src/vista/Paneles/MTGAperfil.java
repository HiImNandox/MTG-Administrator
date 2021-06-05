package vista.Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class MTGAperfil extends JPanel {

	/**
	 * Create the panel.
	 */
	public MTGAperfil() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Panel de perfil");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblNewLabel.setBounds(10, 11, 1046, 617);
		add(lblNewLabel);

	}
}
