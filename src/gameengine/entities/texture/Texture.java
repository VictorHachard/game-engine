package gameengine.entities.texture;

import gameengine.constante.Constante;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Texture {
	private ImagePattern imagePattern;
	
	public Texture(String fileName) {
		Image image = new Image(Constante.TEXTURE_PATH+fileName+".png");
		imagePattern = new ImagePattern(image);
	}

	public ImagePattern getImagePattern() {
		return imagePattern;
	}

	public void setImagePattern(ImagePattern imagePattern) {
		this.imagePattern = imagePattern;
	}
	
}
