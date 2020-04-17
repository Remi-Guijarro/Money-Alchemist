package test.dev;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	
	private static double yOffset = 0;
	private static double xOffset = 0;

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
			root.setOnMousePressed((MouseEvent event) -> {
				yOffset = event.getSceneY();
				xOffset = event.getSceneX();
			});
			
			root.setOnMouseDragged((MouseEvent event) -> {
				primaryStage.setX(event.getScreenX() - xOffset);
				primaryStage.setY(event.getScreenY() - yOffset);
			});
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static double getXOffset() {
		return xOffset;
	}
	
	public static double getYOffset() {
		return yOffset;
	}
}
