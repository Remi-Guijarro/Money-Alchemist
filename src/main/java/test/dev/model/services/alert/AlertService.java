package test.dev.model.services.alert;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class AlertService {
	public enum AlertType {		
		DANGER("danger"),
		WARNING("warning");
		
		public String css;
		
		private AlertType(String url) {
			this.css = url;
		}
	}
	
	public static void apply(Label label, AlertType type, String message) {
		Platform.runLater(() -> {
			label.getStyleClass().add(type.css);			
			label.setText(message); 
	        FadeTransition fade = new FadeTransition();  	          	             
	        fade.setDuration(Duration.millis(3500));  	          
	        fade.setFromValue(10);  
	        fade.setToValue(0);  	          
	        fade.setCycleCount(1);  	            
	        fade.setAutoReverse(false);  	          
	        fade.setNode(label);  	          	          
	        fade.play();  
		});		
        label.getStyleClass().remove(type.css);
        label.setText("");
	}
}
