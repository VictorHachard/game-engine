package gameengine.world.builder;

import gameengine.world.Level;

public class BuilderLevel {
	public static Level buildLevel() {
		Level l = new Level();
		return l;
	}
	public static Level buildLevel(String nomLevel) {
		Level l = new Level();
		l.setNomLevel(nomLevel);
		return l;
	}
}
