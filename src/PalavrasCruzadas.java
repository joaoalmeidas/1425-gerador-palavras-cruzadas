
public class PalavrasCruzadas {
	
	private BancoDeDados banco = new BancoDeDados();
	private char[][] quadriculado;
	private String[][] dicas;
	
	
	public PalavrasCruzadas() {
		
		banco.conectar();
		
		String[][] palavras = banco.selecionaPalavras();
		String palavraCentral = palavras[0][1];
		
		int esquerda = 0, direita = 0;
		
		dicas = banco.selecionaDicas(palavras);
		
		
		for(int i = 0; i < palavraCentral.length(); i++) {
			
			
			if(palavras[i+1][1].substring(0, palavras[i+1][1].lastIndexOf(palavraCentral.charAt(i))).length() > esquerda) {
				
				esquerda = palavras[i+1][1].substring(0, palavras[i+1][1].lastIndexOf(palavraCentral.charAt(i))).length();
				
			}
			
			if(palavras[i+1][1].substring(palavras[i+1][1].lastIndexOf(palavraCentral.charAt(i)), palavras[i+1][1].length()).length() > direita) {
				
				direita = palavras[i+1][1].substring(palavras[i+1][1].lastIndexOf(palavraCentral.charAt(i)), palavras[i+1][1].length()).length();
				
			}
				
		}
		
		quadriculado = new char[palavraCentral.length()][esquerda + direita];
		
		
		for(int i = 0; i < palavraCentral.length(); i++) {
			
			quadriculado[i][esquerda] = palavraCentral.charAt(i);
			
		}
		
		for(int i = 0; i < palavraCentral.length(); i++) {
			
			String stringEsquerda = palavras[i+1][1].substring(0, palavras[i+1][1].lastIndexOf(palavraCentral.charAt(i)));
			String stringDireita = palavras[i+1][1].substring(palavras[i+1][1].lastIndexOf(palavraCentral.charAt(i)), palavras[i+1][1].length());
			
			
			for(int j = esquerda, k = palavras[i+1][1].lastIndexOf(palavraCentral.charAt(i)); j >= esquerda - stringEsquerda.length(); j--, k--) {
				
				quadriculado[i][j] = palavras[i+1][1].charAt(k);
				
			}
			
			for(int j = esquerda, k = palavras[i+1][1].lastIndexOf(palavraCentral.charAt(i)); j < esquerda + stringDireita.length(); j++, k++) {
				
				quadriculado[i][j] = palavras[i+1][1].charAt(k);
				
			}
			
		}
		
		for(int i = 0; i < quadriculado.length; i++) {
			
			for(int j = 0; j < quadriculado[0].length; j++) {
				
				System.out.printf("%c", quadriculado[i][j]);
				
			}
			
			System.out.println();
			
		}
		
		banco.desconectar();

	}
	
	
	
}
