import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PalavrasCruzadasFrame extends JFrame {

	private PalavrasCruzadasPanel palavrasPanel;
	private JPanel controlePanel;
	private JPanel botoesPanel;
	private JPanel tituloPanel;
	private JButton botaoIniciar;
	private JButton botaoReiniciar;
	private JLabel labelTitulo;
	
	//Classe JFrame que exibe o jogo na sua totalidade
	public PalavrasCruzadasFrame() {
		
		palavrasPanel = new PalavrasCruzadasPanel();
		
		controlePanel = new JPanel();
		controlePanel.setLayout(new GridLayout(2, 1));
		
		//JPanel dos Botoes
		botoesPanel = new JPanel();
		botoesPanel.setLayout(new FlowLayout());
		
		//JPanel do título
		tituloPanel = new JPanel();
		tituloPanel.setLayout(new FlowLayout());
		
		//Botões de controle do jogo
		botaoIniciar = new JButton("Iniciar");
		botaoReiniciar = new JButton("Reiniciar");
		
		labelTitulo = new JLabel("Palavras Cruzadas");
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 45));
		
		tituloPanel.add(labelTitulo);
		//Adição do botaoIniciar
		botoesPanel.add(botaoIniciar);
		
		controlePanel.add(tituloPanel);
		controlePanel.add(botoesPanel);
		
		add(controlePanel, BorderLayout.NORTH);
		
		//ActionListener adicionado ao botaoIniciar, para que quando o botaoIniciar for acionado, sejam feitas as mudanças necessárias no layout.
		botaoIniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				botoesPanel.remove(botaoIniciar);
				add(palavrasPanel);
				
				validate();
				palavrasPanel.repaint();
				repaint();
				
			}
				
		});
		
		//ActionListener adicionado ao botaoReiniciar, para que quando for acionado, sejam feitas as mudanças necessárias no layout
		botaoReiniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				remove(palavrasPanel);
				
				palavrasPanel = new PalavrasCruzadasPanel();
				
				for(int i = 0; i < palavrasPanel.getQuadriculado().length; i++) {
					
					for(int j = 0; j < palavrasPanel.getQuadriculado()[i].length; j++) {
						
						if(palavrasPanel.getQuadriculado()[i][j] != null) {
							
							//Adição de um FocusListener, que verifica se o usuário completou o jogo toda vex que um jtextfield perde o foco
							palavrasPanel.getQuadriculado()[i][j].addFocusListener(new FocusAdapter() {

								@Override
								public void focusLost(FocusEvent arg0) {
									
									if(palavrasPanel.getPalavraCruzada().ganhouJogo(palavrasPanel.getRespostaQuadriculado())) {

										botoesPanel.add(botaoReiniciar);
										
										validate();
										palavrasPanel.repaint();
										repaint();
										
									}
									
								}
														
							});				
							
						}
						
					}
					
				}
				
				add(palavrasPanel);
				botoesPanel.remove(botaoReiniciar);
				
				validate();
				palavrasPanel.repaint();
				repaint();
				
			}
			
			
		});
		
		//Adição de um FocusListener, que verifica se o usuário completou o jogo toda vex que um jtextfield perde o foco
		for(int i = 0; i < palavrasPanel.getQuadriculado().length; i++) {
			
			for(int j = 0; j < palavrasPanel.getQuadriculado()[i].length; j++) {
				
				if(palavrasPanel.getQuadriculado()[i][j] != null) {
					
					palavrasPanel.getQuadriculado()[i][j].addFocusListener(new FocusAdapter() {

						@Override
						public void focusLost(FocusEvent arg0) {
							
							if(palavrasPanel.getPalavraCruzada().ganhouJogo(palavrasPanel.getRespostaQuadriculado())) {
								
								botoesPanel.add(botaoReiniciar);
								
								validate();
								palavrasPanel.repaint();
								repaint();
								
							}
							
						}
												
					});				
					
				}
				
			}
			
		}
		
		
	}
	

}
