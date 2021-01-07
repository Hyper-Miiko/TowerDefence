package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;

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

	{
		aiming = false;
		aimingFactor = 0d;
		angle = 0d;
		speed = 50d;
		damage = 2d;
	}
	public Bullet(Field field) {
		super(field, new Circle(null, 2), false);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	void place(Vector position) {
		
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
