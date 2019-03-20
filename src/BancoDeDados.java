import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BancoDeDados {
	
	//Classe que estabelece a conexão com o banco de dados do dicionário
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultset = null;
	
	public void conectar() {
		
		//conexão com o banco de dados
		
		String servidor = "jdbc:mysql://db4free.net:3306/dicionario";
		String usuario = "jgalmeidast";
		String senha = "1974532918643";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		/*
		String servidor = "jdbc:mysql://localhost:3306/dicionario?useTimezone=true&serverTimezone=UTC";
		String usuario = "root";
		String senha = "joao2293";
		String driver = "com.mysql.cj.jdbc.Driver";
		*/
		
		try {
			
			Class.forName(driver);
			this.connection = DriverManager.getConnection(servidor, usuario, senha);
			this.statement = this.connection.createStatement();
		
		}catch(Exception e) {
			
			System.out.println("Erro: " + e.getMessage());
			
		}
		
	}
	
	public boolean estaConectado() {
		
		if(this.connection != null) {
			
			return true;
			
		}else {
			
			return false;
			
		}
		
	}
	
	
	//Seleciona as palavras que irão ser usadas no jogo
	public String[][] selecionaPalavras() {
		
		String[][] palavras= null;
		
		try {
			
			//Busca no banco de dados a palavra central do jogo de palavras cruzadas, que deve ter o tamanho maior ou igual a 10 e menor ou igual 15
			String query = "SELECT * FROM word WHERE character_length(word) >= 10 && character_length(word) <= 15 ORDER BY RAND() LIMIT 1;";
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			
			while(this.resultset.next()) {

				palavras = new String[this.resultset.getString("word").length() + 1][2];
				palavras[0][0] = this.resultset.getString("word_id");
				palavras[0][1] = this.resultset.getString("word");

			}
			
			
			//Busca o restante das palavras que irão compor o jogo, de acordo com os caracteres da palavra principal anteriormente selecionada.
			for(int i = 1; i < palavras.length; i++) {
				
				query = "SELECT * FROM word WHERE character_length(word) <= 10 && WORD LIKE '%" + palavras[0][1].charAt(i - 1) +"%' ORDER BY RAND() LIMIT 1;";
				this.resultset = this.statement.executeQuery(query);
				this.statement = this.connection.createStatement();
				
				while(this.resultset.next()) {
					
					palavras[i][0] = this.resultset.getString("word_id");
					palavras[i][1] = this.resultset.getString("word");
					
				}
				
			}
			
		}catch(Exception e) {
			
			System.out.println("Erro: " + e.getMessage());
			
		}
		
		return palavras;
		
	}
	
	//Busca no banco de dados do dicionário o significado de cada palavra selecionada para o jogo, para que esses significados sejam usados como dicas.
	public String[][] selecionaDicas(String[][] palavras){
		
		String[][] dicas = new String[palavras.length][palavras[0].length];
		
		try {
			
			for(int i = 0; i < palavras.length; i++) {
				
				String query = "SELECT * FROM revision WHERE word_id = " + palavras[i][0];
				this.resultset = this.statement.executeQuery(query);
				this.statement = this.connection.createStatement();
				
				while(this.resultset.next()) {
					
					dicas[i][0] = palavras[i][0];
					dicas[i][1] = this.resultset.getString("xml");
					dicas[i][1] = dicas[i][1].substring(dicas[i][1].indexOf("<def>"));
					
					do {
						
						dicas[i][1] = dicas[i][1].replace(dicas[i][1].substring(dicas[i][1].indexOf("<"), dicas[i][1].indexOf(">") + 1), "");
						
					}while(dicas[i][1].contains("<") && dicas[i][1].contains(">"));
					
					dicas[i][1] = dicas[i][1].replace("\n", " ");
					dicas[i][1] = dicas[i][1].replace("_", "\"");
					
				}
				
			}
			
			
		}catch(Exception e) {
			
			System.out.println("Erro: " + e.getMessage());
			
		}
		
		return dicas;
		
	}
	
	//Metodo de teste usado para listar todas as palavras do banco de dados
	public void listarPalavras() {
		
		try {
			
			String query = "SELECT * FROM word";
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while(this.resultset.next()) {
				System.out.println("id: " + this.resultset.getString("word_id") + " Palavra: " + this.resultset.getString("word"));
			}
			
		}catch(Exception e) {
			
			System.out.println("Erro: " + e.getMessage());
			
		}
		
	}
	
	//Método de teste usado para listar o significado de todas as palavras do dicionário
	public void listarColunaXml() {
		
		try {
			
			String query = "SELECT xml FROM revision";
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while(this.resultset.next()) {
				System.out.println(this.resultset.getString("xml"));
			}
			
		}catch(Exception e) {
			
			System.out.println("Erro: " + e.getMessage());
			
		}
		
		
	}
		
	public void desconectar() {
		
		try {
			this.connection.close();
		}catch(Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
	}

}
