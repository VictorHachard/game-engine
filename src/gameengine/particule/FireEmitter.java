package gameengine.particule;

import java.util.ArrayList;
import java.util.List;

import gameengine.constante.Constante;
import gameengine.physic.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * 
 * @author Main
 *
 */
public class FireEmitter extends Emitter{

	@Override
	public List<Particle> emit(Point2D position) {
		List<Particle> lstParticles = new ArrayList<>();
		int numParticules = 1;
		double x = position.getX();
		double y = position.getY();
		Paint color = Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), 0);
		double radius = 6.0;
		for(int i = 0; i <numParticules ; i++) {
			Particle p = null;
			if (Math.random()<=0.5) {
				p = new Particle(new Point2D(x,y),new Point2D(Math.random()*-0.01,Math.random()*-0.025),1.5, BlendMode.SRC_OVER, color, radius);
			} else {
				p = new Particle(new Point2D(x,y),new Point2D(Math.random()*0.01,Math.random()*-0.025),1.5, BlendMode.SRC_OVER, color, radius);
			}
			lstParticles.add(p);
		}
		return lstParticles;
	}

}