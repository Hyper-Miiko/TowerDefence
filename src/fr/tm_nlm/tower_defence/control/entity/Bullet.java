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
public class Bullet extends Entity implements Movable {
	private static final Tower towerDummy = new Tower(null, null);
	private static final Monster monsterDummy = new Monster(null, null, 0d);
	
	private double speed; //Pixels par secondes
	private double dammage;
	private double currentAngle;
	private double aimingFactor;
	private Entity target;
	public Bullet(Field field, Shape shape) {
		super(field, shape);
		speed = 10d;
		dammage = 0d;
		aimingFactor = -1d;
		target = null;
	}
	
	public Bullet add(Vector positions, double angle) {
		Bullet clone = new Bullet(field, getAppareances().getShape());
		clone.getAppareances().getShape().setPosition(positions);
		clone.speed        = this.speed;
		clone.dammage      = this.dammage;
		clone.currentAngle = angle;
		clone.aimingFactor = this.aimingFactor;
		clone.target       = this.target;
		return clone;
	}
	
	public void target(Target target) {
		switch(target) {
		case MONSTER:
			this.target = monsterDummy;
			break;
		case TOWER:
			this.target = towerDummy;
			break;
		default:
			throw new InternalError("J'ai oublié une cible potentielle : " + target);
		}
	}
	
	public void move() {
		long diffNano = System.nanoTime() - getLastNano();
		refreshNano();
		if(aimingFactor != -1) {
			
		}
		double dist = ((double) speed*diffNano)/1000000000d;
		Vector nextPosition = getPosition().byAngle(currentAngle, dist);
	}
	
	public enum Target {
		TOWER,
		MONSTER;
	}
}
