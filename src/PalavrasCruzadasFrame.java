import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PalavrasCruzadasFrame extends JFrame {

	private PalavrasCruzadasPanel palavrasPanel;
	private JPanel dicasPanel;
	
	public PalavrasCruzadasFrame() {
		
		palavrasPanel = new PalavrasCruzadasPanel();
		dicasPanel = new JPanel();
		dicasPanel.setLayout(new GridLayout(palavrasPanel.getPalavraCruzada().getDicas().length, 1));
		
		for(int i = 0; i < palavrasPanel.getPalavraCruzada().getDicas().length; i++) {
			
			dicasPanel.add(new JLabel(String.format("%d - %s  ", i+1, palavrasPanel.getPalavraCruzada().getDicas()[i][1])));
			
		}
		
		add(palavrasPanel);
		add(dicasPanel, BorderLayout.WEST);
	}
	
}
