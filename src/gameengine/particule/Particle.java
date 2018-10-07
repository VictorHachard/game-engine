package gameengine.particule;

import gameengine.entities.GameObject;
import gameengine.physic.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Paint;

public class Particle extends GameObject{
	private Double life = 1.0;
	private Double decay;
	private BlendMode blendMode;
	private Paint color;
	private Double radius;

	public Particle(Point2D position,Point2D direction, Double lifeLeft, BlendMode blendMode, Paint color, Double radius) {
		this.at(position);
		this.with(direction);
		this.decay = 0.016/lifeLeft;
		this.blendMode = blendMode;
		this.color = color;
		this.radius = radius;
	}


	public Double getLife() {
		return life;
	}


	public void setLife(Double life) {
		this.life = life;
	}


	public Double getDecay() {
		return decay;
	}


	public void setDecay(Double decay) {
		this.decay = decay;
	}


	public BlendMode getBlendMode() {
		return blendMode;
	}


	public void setBlendMode(BlendMode blendMode) {
		this.blendMode = blendMode;
	}


	public Paint getColor() {
		return color;
	}


	public void setColor(Paint color) {
		this.color = color;
	}


	public Double getRadius() {
		return radius;
	}


	public void setRadius(Double radius) {
		this.radius = radius;
	}


	public boolean isAlive() {
		return life > 0;
	}

	public void update() {
		getPosition().add(this.getVelocity());
		life -= decay;
	}
	
}
