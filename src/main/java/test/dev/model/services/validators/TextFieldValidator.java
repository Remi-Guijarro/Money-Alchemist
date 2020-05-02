package test.dev.model.services.validators;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import test.dev.model.services.alert.AlertService;
import test.dev.model.services.validators.ValidatorService.VerificationType;

public class TextFieldValidator extends Validator {
	
	public boolean validate(TextInputControl node, VerificationType type) {
		return validate(node, type);
	}

	@Override
	public Specification readSpecification(VerificationType type) {
		Gson gson = new Gson();
		Specification specification = null;
		try {
			specification = gson.fromJson(new FileReader(type.adress.getFile()), Specification.class);			
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return specification;
	}

	@Override
	public boolean validate(Control node, VerificationType type, Label zoneLabel) throws Exception {		
		Specification specification = readSpecification(type);	
		if(node instanceof TextField) {
			String value =  ((TextField) node).getText();
			if(value.length() < specification.lengthMin) {
				AlertService.apply(zoneLabel, AlertService.AlertType.DANGER, "Le " + type.type + " doit avoir une taille minimal de " + specification.lengthMin);
				return false;
			} else if (value.length() > specification.lengthMax) {
				AlertService.apply(zoneLabel, AlertService.AlertType.DANGER, "Le " + type.type + " doit avoir une taille maximal de " + specification.lengthMax);
				return false;
			} else if (specification.rule.get("regex") != null && !specification.rule.get("regex").getAsString().equals("")) {
				Pattern pattern = Pattern.compile(specification.rule.get("regex").getAsString());
				if(!pattern.matcher(value).find()) {					
					AlertService.apply(zoneLabel, AlertService.AlertType.DANGER, specification.rule.get("message").getAsString());
					return false;
				}
			} else if (!specification.excluded.isEmpty()) {
				for(String excludedChar : specification.excluded) {
					if(value.contains(excludedChar))
						AlertService.apply(zoneLabel, AlertService.AlertType.DANGER, "Le " + type.type + " contient des carateres non autorise [" + specification.excluded.toString() + "]" );						
						return false;
				}
			}
			return true;
		} else {
			throw new Exception("non applicable");
		}		
	}
}
