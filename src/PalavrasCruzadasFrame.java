import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PalavrasCruzadasFrame extends JFrame {

	private PalavrasCruzadasPanel palavrasPanel;
	private JPanel dicasPanel;
	
	public PalavrasCruzadasFrame() {
		
		palavrasPanel = new PalavrasCruzadasPanel();
		dicasPanel = new JPanel();
		dicasPanel.setLayout(new GridLayout(palavrasPanel.getPalavraCruzada().getDicas().length, 1));
		
		add(palavrasPanel);
		add(dicasPanel, BorderLayout.WEST);
	}
	
}
