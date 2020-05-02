package test.dev.model.services.validators;

import java.util.List;

import com.google.gson.JsonObject;

public class Specification {
	public int lengthMax;
	public int lengthMin;
	public JsonObject rule;
	public List<String> excluded;
}
