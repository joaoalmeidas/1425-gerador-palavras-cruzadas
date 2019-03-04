import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class PalavrasCruzadasPanel extends JPanel {

	private PalavrasCruzadas palavraCruzada;
	private JTextField[][] quadriculado;
	
	public PalavrasCruzadasPanel() {
		
		
		
		palavraCruzada = new PalavrasCruzadas();
		quadriculado = new JTextField[palavraCruzada.getQuadriculado().length][palavraCruzada.getQuadriculado()[0].length];
		
		GridLayout layout = new GridLayout(palavraCruzada.getQuadriculado().length, palavraCruzada.getQuadriculado()[0].length);
		setLayout(layout);
		
		for(int i = 0; i < palavraCruzada.getQuadriculado().length; i++) {
			
			for(int j = 0; j < palavraCruzada.getQuadriculado()[0].length; j++) {
				
				if(Character.isLetterOrDigit(palavraCruzada.getQuadriculado()[i][j])) {
					
					quadriculado[i][j] = new JTextField(1);
					add(quadriculado[i][j]);
					
				}else {
					
					quadriculado[i][j] = new JTextField(1);
					quadriculado[i][j].setEditable(false);
					quadriculado[i][j].setBackground(Color.BLACK);
					add(quadriculado[i][j]);
					
				}
				
			}
			
			
		}
		
	}
	
}
