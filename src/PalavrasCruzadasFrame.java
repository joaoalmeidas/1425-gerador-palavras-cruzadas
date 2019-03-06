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
	
	public PalavrasCruzadasFrame() {
		
		palavrasPanel = new PalavrasCruzadasPanel();
		
		controlePanel = new JPanel();
		controlePanel.setLayout(new GridLayout(2, 1));
		
		botoesPanel = new JPanel();
		botoesPanel.setLayout(new FlowLayout());
		
		tituloPanel = new JPanel();
		tituloPanel.setLayout(new FlowLayout());
		
		botaoIniciar = new JButton("Iniciar");
		botaoReiniciar = new JButton("Reiniciar");
		
		labelTitulo = new JLabel("Palavras Cruzadas");
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 45));
		
		tituloPanel.add(labelTitulo);

		botoesPanel.add(botaoIniciar);
		//botoesPanel.add(botaoReiniciar);
		
		controlePanel.add(tituloPanel);
		controlePanel.add(botoesPanel);
		
		
		//add(palavrasPanel);
		add(controlePanel, BorderLayout.NORTH);
		
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
		
		botaoReiniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				remove(palavrasPanel);
				
				palavrasPanel = new PalavrasCruzadasPanel();
				
				for(int i = 0; i < palavrasPanel.getQuadriculado().length; i++) {
					
					for(int j = 0; j < palavrasPanel.getQuadriculado()[i].length; j++) {
						
						if(palavrasPanel.getQuadriculado()[i][j] != null) {
							
							palavrasPanel.getQuadriculado()[i][j].addFocusListener(new FocusAdapter() {

								@Override
								public void focusLost(FocusEvent arg0) {
									
									if(palavrasPanel.getPalavraCruzada().ganhouJogo(palavrasPanel.getRespostaQuadriculado())) {
										
										System.out.println("sim");
										
										botoesPanel.add(botaoReiniciar);
										
										validate();
										palavrasPanel.repaint();
										repaint();
										
									}else {
										
										System.out.println("não");
										
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
		
		for(int i = 0; i < palavrasPanel.getQuadriculado().length; i++) {
			
			for(int j = 0; j < palavrasPanel.getQuadriculado()[i].length; j++) {
				
				if(palavrasPanel.getQuadriculado()[i][j] != null) {
					
					palavrasPanel.getQuadriculado()[i][j].addFocusListener(new FocusAdapter() {

						@Override
						public void focusLost(FocusEvent arg0) {
							
							if(palavrasPanel.getPalavraCruzada().ganhouJogo(palavrasPanel.getRespostaQuadriculado())) {
								
								System.out.println("sim");
								
								botoesPanel.add(botaoReiniciar);
								
								validate();
								palavrasPanel.repaint();
								repaint();
								
							}else {
								
								System.out.println("não");
								
							}
							
						}
												
					});				
					
				}
				
			}
			
		}
		
		
	}
	

}
