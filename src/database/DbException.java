package database;

public class DbException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DbException(String err) {
		super(err);
		System.out.println("Falha na conexao com o Banco de Dados");
	}
}
