package gameengine.particule;

import java.util.ArrayList;
import java.util.List;

import gameengine.physic.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;

public class FireEmitter extends Emitter{

	@Override
	public List<Particle> emit(Point2D position) {
		List<Particle> lstParticles = new ArrayList<>();
		int numParticules = 1;
		for(int i = 0; i <numParticules ; i++) {
			Particle p = new Particle(new Point2D(position.getX(),position.getY()),new Point2D(Math.random()*-0.001,Math.random()*-0.010),2.0, BlendMode.SRC_OVER, Color.rgb(255, 0, 0), 10.0);
			lstParticles.add(p);
		}
		return lstParticles;
	}

}