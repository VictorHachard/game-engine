package gameengine.particule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gameengine.world.Level;

/**
 * Class that handle all the particle and the emitter.
 * @author Main
 *
 */
public class ParticuleEngine {
	private List<Emitter> lstEmitter = new ArrayList<>();
	private Level level ;
	public ParticuleEngine(Level level) {
		super();
		this.level = level;
	}
	public void addEmitter(Emitter... e) {
		for (Emitter es : e) {
			level.getLstGameObject().add(es);
			lstEmitter.add(es);
		}
	}
	public void removeEmitter(Emitter... e) {
		for (Emitter es : e) {
			level.getLstGameObject().remove(es);
			lstEmitter.remove(es);
		}
	}
	/**
	 * Update the life, alpha color, position of all particles.
	 */
	public void update() {
		for (Emitter emitter : lstEmitter) {
			emitter.getLstParticule().addAll(emitter.emit(emitter.getPosition()));
			for (Iterator<Particle> iterator = emitter.getLstParticule().iterator(); iterator.hasNext();) {
				Particle p = (Particle) iterator.next();
				p.update();
				if(!p.isAlive()) {
					iterator.remove();
				}
			}
		}
	}
}
