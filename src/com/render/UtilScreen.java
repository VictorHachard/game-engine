package gameengine.render;

import gameengine.app.GameSetting;
import gameengine.constante.Constante;
import gameengine.physic.Point2D;

public class UtilScreen {
	public Point2D convertScreen2Coord(Point2D cursor) {
		Camera camera = SceneManager.getSceneManager().getCamera();
		double zoom = camera.getZoom();
		double offsetX = (-camera.getPosition().getX()*Constante.GRID_SIZE-camera.getGameObjectBinded().getDimension().getWidth()/2)  *zoom +  GameSetting.getGameSetting().getWidth()/2;
		double offsetY = (-camera.getPosition().getY()*Constante.GRID_SIZE-camera.getGameObjectBinded().getDimension().getHeight()/2) *zoom + GameSetting.getGameSetting().getHeight()/2;
		double posX= -zoom-offsetX;
		double posY= -zoom-offsetY;
		double x = cursor.getX()/Constante.GRID_SIZE;
		double y = cursor.getY()/Constante.GRID_SIZE;
		x += posX;
		y += posY;
		return new Point2D(x,y);
	}
}
