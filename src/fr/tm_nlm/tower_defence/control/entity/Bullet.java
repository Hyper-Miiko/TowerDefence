package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;

/**
 * Un entité qui a pour but de chasser un type d'entité
 * auto-guidé ou non
 * @author Hyper Mïko
 *
 */
public class Bullet<E extends Entity> extends Entity implements Movable {
	private boolean aiming;
	private double aimingFactor;
	private double angle;
	private double speed;
	private double damage;
	private E target;

	public Bullet() {
		super(null, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	void setAiming(boolean aiming) {
		this.aiming = aiming;
	}

	void setAimingFactor(double aimingFactor) {
		this.aimingFactor = aimingFactor;
	}
	
	void setAngle(double angle) {
		this.angle = angle;
	}

	void setSpeed(double speed) {
		this.speed = speed;
	}

	void setDamage(double damage) {
		this.damage = damage;
	}
	
	E getTarget() {
		return target;
	}
	void setTarget(E target) {
		this.target = target;
	}
	
}
