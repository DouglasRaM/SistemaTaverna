package gui.util;

import javafx.scene.control.TextField;

public class Limites {
	//verificar se é numero
	public static void setTextFieldInteger(TextField txt) {
		txt.textProperty().addListener((obs,placeholder, valNovo) -> {
			if(valNovo != null && valNovo.matches("\\d*")) {
				txt.setText(placeholder);
			}
		});
	}
	//incluir limite de caracteres
	public static void setTextFieldMaxLength(TextField txt, int max) {
		txt.textProperty().addListener((obs,placeholder, valNovo) ->{
			if(valNovo.length() > max) {
				txt.setText(placeholder);
			}
		});
	}
}
