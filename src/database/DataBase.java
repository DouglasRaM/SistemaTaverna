package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import model.entities.Quartos;

public class DataBase {

	private static Connection conexao = null;
	
	//pegar os dados de login ao banco, o arquivo db.properties
	public static Properties carregarPropriedades() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs); 
			return props;
		}
		catch (IOException err) {
			throw new DbException(err.getMessage());
		}
	}
	
	//Conectar ao banco de dados
	public static Connection getConexao() {
		if(conexao == null) {
			try {
				Properties props = carregarPropriedades();
				String url = props.getProperty("dburl");
				conexao = DriverManager.getConnection(url,props);
			}
			catch(SQLException err) {
				throw new DbException(err.getMessage());
			}
		}
		return conexao;
	}
	
	//tres funcoes para fechar conexao/Statement/ResultSet
	public static void fecharConexao() {
		 if(conexao != null) {
			 try {
				 conexao.close();
			 }
			 catch(SQLException err) {
				 throw new DbException(err.getMessage());
			 }
		 }
	}
	public static void fecharStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException err) {
				throw new DbException(err.getMessage());
			}
		}
	}
	public static void fecharResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException err) {
				throw new DbException(err.getMessage());
			}
		}
	}
	
	
	
	//exibir tabela quartos
	public static void exibir(Connection conn) {
		Statement st = null;
		ResultSet rs = null;
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from quartos");
				System.out.println("ID , NOME         , TIPO, CAMAS, DIARIA, OCUPADO");
				while(rs.next()) {
					System.out.println(rs.getInt("ID") + ", " +
					rs.getString("NOME") + ", " +
					rs.getString("TIPO") + ", " +
					rs.getInt("CAMAS") + ", " +
					rs.getDouble("DIARIA") + ", " +
					rs.getBoolean("OCUPADO"));
				}
			}
			catch(SQLException err) {
				throw new DbException(err.getMessage());
			}
			finally {
				DataBase.fecharStatement(st);
				DataBase.fecharResultSet(rs);
			}
	}
	
	
	//cadastrar quartos
	public static void cadastrarQuarto(Connection conn) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Quartos quarto = new Quartos();
		try {
			pst = conn.prepareStatement(
					"insert into quartos (NOME, TIPO, CAMAS, DIARIA)" +
					"values (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, quarto.getNomeQuarto());
			pst.setString(2,quarto.getTipo());
			pst.setInt(3, quarto.getCamas());
			pst.setDouble(4, quarto.getDiaria());
			
			int linhasAlteradas = pst.executeUpdate();
			if(linhasAlteradas > 0) {
				System.out.println("O quarto foi inserido no banco");
			}
			rs = pst.getGeneratedKeys();
		}
		catch(SQLException err) {
			throw new DbException(err.getMessage());
		}
		finally {
			DataBase.fecharResultSet(rs);
		}
	}
	
	//atualizar situação do quarto
	public static void atualizarOcupado(Connection conn, int id, boolean bool) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(
					"update quartos SET OCUPADO = ? WHERE (ID = ?)");
			pst.setBoolean(1,bool);
			pst.setInt(2, id);
			
			int linhasAlteradas = pst.executeUpdate();
			System.out.println(linhasAlteradas + " mudancas(s) realizada(s)");
		}
		catch(SQLException err) {
			throw new DbException(err.getMessage());
		}
	}
	// atualizar quarto
	public static void atualizarQuarto(Connection conn, Quartos quarto) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(
					"UPDATE quartos "
					+ "SET NOME = ?, TIPO = ?, CAMAS = ?, DIARIA = ?, OCUPADO = ?"
					+ "WHERE ID = ?");
			pst.setString(1, quarto.getNomeQuarto());
			pst.setString(2, quarto.getTipo());
			pst.setInt(3, quarto.getCamas());
			pst.setDouble(4, quarto.getDiaria());
			pst.setBoolean(5, quarto.isOcupado());
			pst.setInt(6, quarto.getId());
			
			pst.executeUpdate();
		}
		catch(SQLException err) {
			throw new DbException(err.getMessage());
		}
	}
	
	// deletar quarto
	public static void deletarQuarto(Connection conn, int id) {
		
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(
					"DELETE FROM quartos  WHERE ID = ?");
			pst.setInt(1, id);
			
			pst.executeUpdate();
		}
			catch(SQLException err) {
				throw new DbException(err.getMessage());
		}
	}
}
