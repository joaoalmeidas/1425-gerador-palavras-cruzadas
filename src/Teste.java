
public class Teste {

	public static void main(String[] args) {
		
		BancoDeDados banco = new BancoDeDados();
		String[][] palavras;
		String[][] dicas;
		
		banco.conectar();
		
		if(banco.estaConectado()) {
			
			palavras = banco.selecionaPalavras();
			dicas = banco.selecionaDicas(palavras);
			
			for(int i = 0; i < palavras.length; i++) {
				
				for(int j = 0; j < palavras[0].length; j++) {
					
					System.out.printf("%s ", palavras[i][j]);
					System.out.printf("%s ", dicas[i][j]);
					
				}
				
				System.out.println();
				
			}
			
			banco.desconectar();
			
		}else {
			
			System.out.println("Não foi possível conectar com o banco.");
			
		}
		
	}

}
