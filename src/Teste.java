
public class Teste {

	public static void main(String[] args) {
		
		BancoDeDados banco = new BancoDeDados();
		String[][] palavras = banco.selecionaPalavras();
		
		for(int i = 0 ; i < palavras.length; i++) {
			
			for(int j = 0; j <  palavras[i].length; j++) {
				
				System.out.printf("%s", palavras[i][j]);
				
			}
			
			System.out.println();
			
		}
	}

}
