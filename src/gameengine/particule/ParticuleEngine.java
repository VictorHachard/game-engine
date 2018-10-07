package gameengine.particule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gameengine.world.Level;

public class ParticuleEngine {
	private List<Emitter> lstEmitter = new ArrayList<>();
	private Level level ;
	public ParticuleEngine(Level level) {
		super();
		this.level = level;
	}
	public void addEmitter(Emitter e) {
		level.getLstGameObject().add(e);
		lstEmitter.add(e);
	}
	public void update() {
		for (Emitter emitter : lstEmitter) {
			emitter.getLstParticule().addAll(emitter.emit(emitter.getPosition()));
			System.out.println(emitter.getLstParticule().size());
			System.out.println(emitter.getPosition());
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
