package gameengine.world.builder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import gameengine.constante.Constante;
import gameengine.entities.GameObject;
import gameengine.entities.builder.WrapperGameObject;
import gameengine.physic.Point2D;
import gameengine.world.Level;

/**
 * class that build the level.
 * @author Main
 *
 */
public class BuilderLevel {
	public static List<WrapperGameObject> LST_WRAPPER_GAMEOBJECT = new ArrayList<>();
	
	public static void addWrapperGameObject(WrapperGameObject w) {
		LST_WRAPPER_GAMEOBJECT.add(w);
	}
	public static void removeWrapperGameObject(WrapperGameObject w) {
		LST_WRAPPER_GAMEOBJECT.remove(w);
	}
	
	public static Level buildLevel() {
		Level l = new Level();
		return l;
	}
	public static Level buildLevel(String nomLevel) {
		Level l = new Level();
		l.setNomLevel(nomLevel);
		return l;
	}
	/**
	 * 
	 * @param nomLevel String that is the name of the level.
	 * @param fileName String the name og the file.
	 * @param pas Integer. 
	 * @return Level.
	 */
	public static Level buildLevel(String nomLevel , String fileName, Integer pas) {
		Level l = buildLevel(nomLevel);
		URL url = BuilderLevel.class.getResource(Constante.LEVEL_TEXT_PATH+fileName);
		File file = new File(url.getPath());
		// Open the File
		try {
			FileReader fr = new FileReader(file);
			int character = fr.read();
			StringBuilder sb = new StringBuilder();
			int compteur = 1;
			while(character != -1) {			
				if(character != 13 && character != 10 && compteur == pas) {
					compteur = 1;
					sb.append((char)character+"|");
				} else {
					compteur++;
					if(character == 13 || character == 10) {
						compteur=1;
					}
					sb.append((char)character);					
				} 
				character = fr.read();
			}
			String[] line = sb.toString().split("\\n");

			l.setHeight(line.length);
			// Start parsing of the file to put at good position GameObject
			for (double y = 0; y < line.length ; y++ ) {
				String lineToWork = line[(int)y].replaceAll("\n", "").replaceAll("\r", "");
				lineToWork = lineToWork.substring(0,lineToWork.length()-1);
				String[] lstStringGameObject = lineToWork.split("\\|");
				l.setWidth(lstStringGameObject.length); //ici c degeux
				for (double x = 0 ; x < lstStringGameObject.length; x++) {
					String block = lstStringGameObject[(int)x]; // on recupere le bon block
					for (WrapperGameObject wrapperGameObject : LST_WRAPPER_GAMEOBJECT) {
						if(wrapperGameObject.getId().equals(block)) {
							GameObject g = wrapperGameObject.getBuilder().createGameObject();
							g.at(new Point2D(x,y));
							l.addGameObject(g);			
						}
					}
					
				}
			}
		} catch (IOException e) {
			//TODO logguer
			e.printStackTrace();
		}
		return l;
	}
}
