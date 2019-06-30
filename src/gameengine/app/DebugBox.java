package gameengine.app;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DebugBox {
	
	private VBox layout;
	private Scene scene;
	private Label fps;
	private double height;
	private double width;
	private String title;
	
	private static DebugBox INSTANCE = null;
	public static DebugBox getGameSetting() {
		if (INSTANCE != null) {
			return INSTANCE;
		} return null;
	}
	
	public static DebugBox createDebugBox() {
		INSTANCE = new DebugBox();
		//Default setting
		INSTANCE.title = "Debug Windows";
		INSTANCE.height = 480;
		INSTANCE.width = 720;
		return getGameSetting();
	}
	
	public void defaultSetting() {
		Stage debug = new Stage();
		debug.initModality(Modality.APPLICATION_MODAL);
		debug.setTitle(title);
		debug.setHeight(height);
		debug.setWidth(width);
		
		fps = new Label();
		
		layout = new VBox(10);
		layout.getChildren().add(fps);
		
		scene = new Scene(layout);
		debug.setScene(scene);
		debug.show();
	}
	
	public void setFPS(String e) {
		fps.setText("FPS: " + e);
	}
	
	public void addElement(Label e) {
		layout.getChildren().add(e);
	}
	
	public void removeElement() {
		
	}
}
