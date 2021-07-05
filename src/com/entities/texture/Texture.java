package gameengine.entities.texture;

import gameengine.core.LoaderResources;
import javafx.scene.paint.ImagePattern;

/**
 * Class representing a texture (image).
 * @author Main
 *
 */
public class Texture {
	private ImagePattern imagePattern;
	
	public Texture(String fileName) {
		imagePattern = LoaderResources.getLoaderResources().getImage(fileName);
	}

	public ImagePattern getImagePattern() {
		return imagePattern;
	}

	public void setImagePattern(ImagePattern imagePattern) {
		this.imagePattern = imagePattern;
	}
	
}
