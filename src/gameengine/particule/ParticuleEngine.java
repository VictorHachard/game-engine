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
	/**
	 * Add an emitter to the active list of emitter, if an emitter is already in the list do nothing.
	 * @param e The emitter to add to the list.
	 */
	public void addEmitter(Emitter... e) {
		for (Emitter es : e) {
			if (!lstEmitter.contains(es)) {
				level.getLstGameObject().add(es);
				lstEmitter.add(es);
			}
		}
	}
	/**
	 * Remove an emitter to the active list of emitter, if an emitter is not in the list do nothing.
	 * @param e The emitter to add to the list.
	 */
	public void removeEmitter(Emitter... e) {
		for (Emitter es : e) {
			if (lstEmitter.contains(es)) {
				level.getLstGameObject().remove(es);
				lstEmitter.remove(es);
			}
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
