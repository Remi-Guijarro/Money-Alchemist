package test.dev.model.services.validators;

import java.net.URL;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidatorService {
	
	public static enum VerificationType {	
		LOGIN_TEXTFIELD(ValidatorService.class.getResource("login.json"), " Login"),
		PASSWORD_TEXTFIELD(ValidatorService.class.getResource("password.json")," Mot de passe");
		
		public URL adress;
		public String type;
		
		VerificationType(URL url, String type){
			this.adress = url;
			this.type= type; 
		}
	}
	
	public static boolean checkTextField(TextField textField,VerificationType verificationType, Label alertZone) {
		TextFieldValidator textFieldValidator = new TextFieldValidator();
		try {
			return textFieldValidator.validate(textField,verificationType, alertZone);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}
}
