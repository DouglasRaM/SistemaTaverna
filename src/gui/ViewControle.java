package gui;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import database.DataBase;
import gui.util.Alertas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ViewControle implements Initializable{
	@FXML
	private TextField txtCaixa1;
	@FXML
	private TextArea entradaUsuario; 
	@FXML
	private Button reservaQuarto;

	public void botaoReservaDeQuartos() {
		try {
			String entradaUsuario = txtCaixa1.getText();
		if(entradaUsuario.equals(null)) {
			Alertas.mostrarAlerta("forma de texto",null,"NÃO ACEITAMOS textos vazios BRO",AlertType.INFORMATION);
		}
		}
		catch(NumberFormatException err) {
			Alertas.mostrarAlerta("ERROs",null,"Texto nao reconhecido",AlertType.ERROR);
		}	
	}
	public void listagem() {
		Connection conn = DataBase.getConexao();
		DataBase.exibir(conn);
	}
	//INICIAR TELA
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	

}

