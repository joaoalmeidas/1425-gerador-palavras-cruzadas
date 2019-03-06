import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PalavrasCruzadasPanel extends JPanel {

	private PalavrasCruzadas palavraCruzada;
	private JTextField[][] quadriculado;
	private char[][] respostaQuadriculado;
	private JButton[] botoesDicas;
	
	//Classe JPanel que exibe o quadriculado que usuário irá preencher
	public PalavrasCruzadasPanel() {
		
		palavraCruzada = new PalavrasCruzadas();
		
		//Array de JTextFields com as mesmas dimensões do array Quadriculado da classe PalavraCruzadas
		quadriculado = new JTextField[palavraCruzada.getQuadriculado().length][palavraCruzada.getQuadriculado()[0].length];
		respostaQuadriculado = new char[palavraCruzada.getQuadriculado().length][palavraCruzada.getQuadriculado()[0].length];
		
		//Array de JButtons que ao serem acionados exibirão
		botoesDicas = new JButton[palavraCruzada.getDicas().length];
		
		//GridLayout do JPanel definido com as mesmas dimensões do atributo quadriculado da classe PalavraCruzada
		GridLayout layout = new GridLayout(palavraCruzada.getQuadriculado().length + 1, palavraCruzada.getQuadriculado()[0].length + 1);
		setLayout(layout);
		
		
		//Primeira linha do quadriculado
		for(int i = 0; i < quadriculado[0].length + 1; i++) {
			
			if(i == palavraCruzada.getIndicePalavraCentral()) {
				
				//instanciação do primeiro JButton das dicas
				botoesDicas[0] = new JButton("1");
				add(botoesDicas[0]);
				
			}else {
				
				add(new JLabel());
				
			}
			
		}
		
		//demais linhas do quadriculado
		for(int i = 0; i < quadriculado.length; i++) {
			
			//instanciação dos demais JButton das dicas
			botoesDicas[i+1] = new JButton(String.format("%d", i+2));
			add(botoesDicas[i+1]);
			
			
			//Laço de repetição que instancia e adiciona os JTextfields ao JPanel
			for(int j = 0; j < quadriculado[0].length; j++) {
				
				
				//se o char no array quadriulado for um char válido, é instanciado um JTextField
				if(Character.isLetterOrDigit(palavraCruzada.getQuadriculado()[i][j]) || palavraCruzada.getQuadriculado()[i][j] == '.' 
						|| palavraCruzada.getQuadriculado()[i][j] == '-') {
					
					quadriculado[i][j] = new JTextField(1);
					quadriculado[i][j].setFont(new Font("Arial", Font.BOLD, 45));
					
					quadriculado[i][j].addKeyListener(new QuadriculadoHandler(i, j));
					
					add(quadriculado[i][j]);
					
				}else {
					
					add(new JLabel());
					
				}
				
			}
			
		}
		
		//Laço de repetição que adiciona um ActionListerner para cada JButton, que exibirá uma janela com a dica para palavra.
		for(int i = 0; i < botoesDicas.length; i++) {
			
			botoesDicas[i].addActionListener(new DicasHandler(i, palavraCruzada.getDicas()[i][1]));
			
		}
		
		
		
	}
	
	//Classe ActionListener que é usada para exibir as dicas
	private class DicasHandler implements ActionListener{
		
		private int i;
		private String dica;
		
		public DicasHandler(int i, String dica) {
			
			this.i = i;
			this.dica = dica;
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JOptionPane.showMessageDialog(null, dica, String.format("Dica %d", i+1), JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}
	
	//Classe KeyListener que é usada para modificar visualmente os JTextFields
	private class QuadriculadoHandler implements KeyListener{
		
		private int i;
		private int j;
		
		public QuadriculadoHandler(int i, int j) {
			
			this.i = i;
			this.j = j;
			
		}
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			
			//limita os caracteres
			getQuadriculado()[i][j].setText("");
			if(!getQuadriculado()[i][j].getText().equals("")) {
				
				getQuadriculado()[i][j].setText(Character.toString(getQuadriculado()[i][j].getText().charAt(0)));
				
			}
			

			
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			
			if(!quadriculado[i][j].getText().equals("")) {
				
				getRespostaQuadriculado()[i][j] = getQuadriculado()[i][j].getText().charAt(0);
				
			}
			
			if(getRespostaQuadriculado()[i][j] == palavraCruzada.getQuadriculado()[i][j] || 
					getRespostaQuadriculado()[i][j] == Character.toUpperCase(palavraCruzada.getQuadriculado()[i][j])) {
				
				getQuadriculado()[i][j].setEditable(false);
				getQuadriculado()[i][j].setFocusable(false);
				getQuadriculado()[i][j].setBackground(Color.GREEN);
				getQuadriculado()[i][j].setForeground(Color.WHITE);
				
			}
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
					
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

	public char[][] getRespostaQuadriculado() {
		return respostaQuadriculado;
	}

	public void setRespostaQuadriculado(char[][] respostaQuadriculado) {
		this.respostaQuadriculado = respostaQuadriculado;
	}
	
}

