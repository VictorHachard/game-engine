package gameengine.core;

import gameengine.constante.Constante;
import gameengine.render.sound.SoundManager;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LoaderResources {
	private static LoaderResources INSTANCE;
	private Boolean enable;
	private SoundManager soundManager;
	private Map<String,ImagePattern> mImage = new HashMap<>(); 
	
	public static LoaderResources getLoaderResources() {
		if(INSTANCE ==null) {
			INSTANCE = new LoaderResources();
		}
		return INSTANCE;
	}
	public LoaderResources() {
		initResources();
	}
	public void initResources() {
		initImage();
		initSound();
		System.out.println("END RESSOURCE");
	}
	
	
	
	private void initImage() {
		//TODO faire evoluer pour les sous dossiers.
		File folder;
		try {
			folder = new File(getClass().getResource(Constante.TEXTURE_PATH).toURI().getPath());
			String[] filesName = folder.list();
			if(filesName != null) {
				for (int i = 0; i < filesName.length; i++) {
					String nameFile = filesName[i].split("\\.")[0];
					Image image = new Image(Constante.TEXTURE_PATH+filesName[i]);
					mImage.put(nameFile,new ImagePattern(image));
				}			
			}

			for (Entry<String, ImagePattern> string : mImage.entrySet()) {
				System.out.println(string.getKey());
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public void initSound() {
		soundManager = SoundManager.createSoundManager();
		
		File folder;
		try {
			folder = new File(this.getClass().getResource(Constante.SOUND_PATH).toURI().getPath());
			String[] filesName = folder.list();
			
			if(filesName != null) {
				for (int i = 0; i < filesName.length; i++) {
					String nameFile = filesName[i].split("\\.")[0];
					soundManager.add(nameFile, filesName[i]);
				}			
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ImagePattern getImage(String image) {
		return mImage.get(image);
	}
}
