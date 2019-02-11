package gameengine.render.iu;

import gameengine.render.SceneManager;
import javafx.scene.text.Text;

public class UIUtils {
	public static void addText(double x,double y,String text) {
		SceneManager.getSceneManager().addUI(new Text(x, y, text));
	}
}
