import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BancoDeDados {
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultset = null;
	
	public void conectar() {
		
		String servidor = "jdbc:mysql://localhost:3306/dicionario?useTimezone=true&serverTimezone=UTC";
		String usuario = "root";
		String senha = "joao2293";
		String driver = "com.mysql.cj.jdbc.Driver";
		
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
	
	public String[][] selecionaPalavras() {
		
		String[][] palavras= null;
		
		try {
			
			String query = "SELECT * FROM word WHERE character_length(word) >= 10 && character_length(word) <= 15 ORDER BY RAND() LIMIT 1;";
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			
			while(this.resultset.next()) {
				System.out.println("id: " + this.resultset.getString("word_id") + " Palavra: " + this.resultset.getString("word"));
				palavras = new String[this.resultset.getString("word").length() + 1][2];
				palavras[0][0] = this.resultset.getString("word_id");
				palavras[0][1] = this.resultset.getString("word");
			}
			
			for(int i = 1; i < palavras[0][1].length(); i++) {
				
				query = "SELECT * FROM word WHERE character_length(word) <= 10 && WORD LIKE '%" + palavras[0][1].charAt(i) +"%' ORDER BY RAND() LIMIT 1;";
				this.resultset = this.statement.executeQuery(query);
				this.statement = this.connection.createStatement();
				
				while(this.resultset.next()) {
					System.out.println("id: " + this.resultset.getString("word_id") + " Palavra: " + this.resultset.getString("word"));
					palavras = new String[this.resultset.getString("word").length() + 1][2];
					palavras[i][0] = this.resultset.getString("word_id");
					palavras[i][1] = this.resultset.getString("word");
				}
				
			}
			
		}catch(Exception e) {
			
			System.out.println("Erro: " + e.getMessage());
			
		}
		
		return palavras;
		
	}
	
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
	
	public void corrigeColunaXml() {
		
		String significado = "";
		String entrada = "";
		String id = "";
		try {
			
			String query = "SELECT * FROM revision";
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while(this.resultset.next()) {
				
				entrada = this.resultset.getString("xml");
				id = this.resultset.getString("word_id");
				
				significado = entrada.substring(entrada.indexOf("<def>") + 5, entrada.indexOf("</def>"));
				
				significado = significado.replace("\n", " ");
				significado = significado.replace("'", " ");
				
				if(significado.contains("<")) {
					
					significado = significado.substring(0, significado.indexOf("<"));
					
				}
				
				
				query = "UPDATE revision SET xml = '" + significado + "' WHERE word_id = " +id+ ";";
				
				//System.out.println(significado);
				this.statement.executeUpdate(query);
			}
			
			System.out.println("fim");
			
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
