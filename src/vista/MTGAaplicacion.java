package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vista.Paneles.MTGAbiblioteca;
import vista.Paneles.MTGAcartas;
import vista.Paneles.MTGAperfil;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MTGAaplicacion extends JFrame {
	public JFrame frame = this;
	private JPanel contentPane;
	final static String VENTANA1 = "Card with MTGAperfil";
	final static String VENTANA2 = "Card with MTGAcartas";
	final static String VENTANA3 = "Card with MTGAbiblioteca";
	
	MTGAperfil perfil = new MTGAperfil();
	MTGAcartas cartas = new MTGAcartas();
	MTGAbiblioteca biblioteca = new MTGAbiblioteca();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTGAaplicacion frame = new MTGAaplicacion();
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
	public MTGAaplicacion() {

		setTitle("MTG Administrator - Perfil");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MTGAaplicacion.class.getResource("/img/logo.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1135, 780);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPerfil = new JMenu("");
		mnPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiarPaneles(VENTANA1);
				setTitle("MTG Administrator - Perfil");
			}
		});
		mnPerfil.setIcon(new ImageIcon(MTGAaplicacion.class.getResource("/img/miusuario.png")));
		menuBar.add(mnPerfil);
		
		JMenu mnCartas = new JMenu("");
		mnCartas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiarPaneles(VENTANA2);
				setTitle("MTG Administrator - Cartas");
			}
		});
		mnCartas.setIcon(new ImageIcon(MTGAaplicacion.class.getResource("/img/cartasmtg.png")));
		menuBar.add(mnCartas);
		
		JMenu mnBiblioteca = new JMenu("");
		mnBiblioteca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiarPaneles(VENTANA3);
				setTitle("MTG Administrator - Biblioteca");
			}
		});
		mnBiblioteca.setIcon(new ImageIcon(MTGAaplicacion.class.getResource("/img/albumm.png")));
		menuBar.add(mnBiblioteca);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		añadirPaneles();		

		
	}
	
	public void cambiarPaneles(String panel) {
		CardLayout c1 = (CardLayout)(contentPane.getLayout());
		c1.show(contentPane, panel);
	}
	
	public void añadirPaneles() {
		contentPane.add(perfil,VENTANA1);
		contentPane.add(cartas,VENTANA2);
		contentPane.add(biblioteca,VENTANA3);
	}
	
}
