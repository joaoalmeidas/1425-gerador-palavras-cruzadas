
public class PalavrasCruzadas {
	
	
	
	private BancoDeDados banco = new BancoDeDados();
	private char[][] quadriculado;
	private String[][] dicas;
	private int indicePalavraCentral;
	
	//Construtor da classe PalavrasCruzadas
	public PalavrasCruzadas() {
		
		banco.conectar();
		
		//Seleção das palavras
		String[][] palavras = banco.selecionaPalavras();
		String palavraCentral = palavras[0][1];
		
		
		//Seleção dos significados das palavras
		dicas = banco.selecionaDicas(palavras);
		
		int esquerda = 0, direita = 0;
		
		//Laço de repetição usado para saber qual deverá ser a dimensão do array de chars
		for(int i = 0; i < palavraCentral.length(); i++) {
			
			if(palavras[i+1][1].substring(0, palavras[i+1][1].lastIndexOf(palavraCentral.charAt(i))).length() > esquerda) {
				
				esquerda = palavras[i+1][1].substring(0, palavras[i+1][1].lastIndexOf(palavraCentral.charAt(i))).length();
				
			}
			
			if(palavras[i+1][1].substring(palavras[i+1][1].lastIndexOf(palavraCentral.charAt(i)), palavras[i+1][1].length()).length() > direita) {
				
				direita = palavras[i+1][1].substring(palavras[i+1][1].lastIndexOf(palavraCentral.charAt(i)), palavras[i+1][1].length()).length();
				
			}
				
		}
		
		//Localização do caractere da palavra principal na primeira palavra na posição horizontal
		indicePalavraCentral = esquerda + 1;

		quadriculado = new char[palavraCentral.length()][esquerda + direita];
		
		//Preenchimento do array de chars no sentido vertical com a palavra principal
		for(int i = 0; i < palavraCentral.length(); i++) {
			
			quadriculado[i][esquerda] = palavraCentral.charAt(i);
			
		}
		
		//Preenchimento do array de chars no sentido horizontal com o restante das palavras.
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
		
		banco.desconectar();

	}


	public BancoDeDados getBanco() {
		return banco;
	}


	public void setBanco(BancoDeDados banco) {
		this.banco = banco;
	}


	public char[][] getQuadriculado() {
		return quadriculado;
	}


	public void setQuadriculado(char[][] quadriculado) {
		this.quadriculado = quadriculado;
	}


	public String[][] getDicas() {
		return dicas;
	}


	public void setDicas(String[][] dicas) {
		this.dicas = dicas;
	}


	public int getIndicePalavraCentral() {
		return indicePalavraCentral;
	}


	public void setIndicePalavraCentral(int indicePalavraCentral) {
		this.indicePalavraCentral = indicePalavraCentral;
	}
	
	//Método boolean que retorna se o jogador completou corretamente as palavras.
	public boolean ganhouJogo(char[][] charJogo) {
		
		for(int i = 0; i < getQuadriculado().length; i++) {
			
			for(int j = 0; j < getQuadriculado()[i].length; j++) {
				
				if(getQuadriculado()[i][j] != Character.toLowerCase(charJogo[i][j])) {
					
					return false;
					
				}
				
			}
			
		}
		
		return true;
		
	}
	
	
}
