package fonts;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class Fuente {

	
	/*public Font devolverFuente() {
		try {
			normalFont = Font.createFont(Font.TRUETYPE_FONT, new File("mtgfont.ttf")).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(normalFont);
		} catch (Exception e) {
			System.err.println(e);
		}
		return normalFont;
	}*/
	
	public Fuente() {
		try {
			Font normalFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("mtgfont.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(normalFont);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
