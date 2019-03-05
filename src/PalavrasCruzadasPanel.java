import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PalavrasCruzadasPanel extends JPanel {

	private PalavrasCruzadas palavraCruzada;
	private JTextField[][] quadriculado;
	private JButton[] botoesDicas;
	
	public PalavrasCruzadasPanel() {
		
		
		
		palavraCruzada = new PalavrasCruzadas();
		quadriculado = new JTextField[palavraCruzada.getQuadriculado().length][palavraCruzada.getQuadriculado()[0].length];
		botoesDicas = new JButton[palavraCruzada.getDicas().length];
		
		GridLayout layout = new GridLayout(palavraCruzada.getQuadriculado().length + 1, palavraCruzada.getQuadriculado()[0].length + 1);
		setLayout(layout);
		
		for(int i = 0; i < quadriculado[0].length + 1; i++) {
			
			if(i == palavraCruzada.getIndicePalavraCentral()) {
				
				botoesDicas[0] = new JButton("1");
				add(botoesDicas[0]);
				
			}else {
				
				add(new JLabel());
				
			}
			
		}
		
		for(int i = 0; i < quadriculado.length; i++) {
			
			
			botoesDicas[i+1] = new JButton(String.format("%d", i+2));
			add(botoesDicas[i+1]);
			
			for(int j = 0; j < quadriculado[0].length; j++) {
				
				if(Character.isLetterOrDigit(palavraCruzada.getQuadriculado()[i][j]) || palavraCruzada.getQuadriculado()[i][j] == '.' 
						|| palavraCruzada.getQuadriculado()[i][j] == '-') {
					
					quadriculado[i][j] = new JTextField(1);
					add(quadriculado[i][j]);
					
				}else {
					
					add(new JLabel());
					
				}
				
			}
			
		}
		
	}

	public PalavrasCruzadas getPalavraCruzada() {
		return palavraCruzada;
	}

	public void setPalavraCruzada(PalavrasCruzadas palavraCruzada) {
		this.palavraCruzada = palavraCruzada;
	}

	public JTextField[][] getQuadriculado() {
		return quadriculado;
	}

	public void setQuadriculado(JTextField[][] quadriculado) {
		this.quadriculado = quadriculado;
	}
	
	
	
}