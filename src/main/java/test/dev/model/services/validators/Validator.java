package test.dev.model.services.validators;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import test.dev.model.services.validators.ValidatorService.VerificationType;

public abstract class Validator {
	public abstract boolean validate(Control node, VerificationType type, Label zoneLabel) throws Exception;
	public abstract Specification readSpecification(VerificationType type);
}
