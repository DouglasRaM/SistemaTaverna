package application;
	
import database.DataBase;
import java.sql.Connection;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	 
	
	@Override
	public void start(Stage palco) {
		try {
			Parent tela = FXMLLoader.load(getClass().getResource("/gui/View.fxml"));
			Scene menu = new Scene(tela);
			palco.setScene(menu);
			palco.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection conexao = DataBase.getConexao();
		DataBase.exibir(conexao);
		DataBase.cadastrarQuarto(conexao);
		Scanner sc = new Scanner(System.in);
		System.out.println("qual o id do quarto a ser retirado?");
		int val = sc.nextInt();
		DataBase.deletarQuarto(conexao, val);
		System.out.println("qual o id do quarto a ter reserva atualizada?");
		val = sc.nextInt();
		System.out.println("O quarto será reservado?");
		boolean bool = sc.nextBoolean();
		DataBase.atualizarOcupado(conexao, val, bool);
		sc.close();
		launch(args);
		DataBase.fecharConexao();
		
		
	}
}
