package fr.tm_nlm.tower_defence.control.entity;

import java.util.HashMap;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;
import fr.tm_nlm.tower_defence.control.entity.Attack.Option;
import fr.tm_nlm.tower_defence.control.entity.Monster.Effect;

import static fr.tm_nlm.tower_defence.control.entity.Attack.Option.*;

/**
 * Un entité qui a pour but de chasser un type d'entité
 * auto-guidé ou non
 * @author Hyper Mïko
 *
 */
public class Bullet extends Entity implements Movable {
	private boolean aiming;
	private long deathTime;
	private double aimingFactor;
	private double angle;
	private double damage;
	private double size;
	private double speed;
	private Attack attack;
	private Entity target;
	private HashMap<Option, double[]> options;
	
	public Bullet(Field field) {
		super(field, new Circle(null, 2));
	}
	
	public void process() {
		if(!isDead()) {
			if(aiming) {
				if(!target.isDead() || seekNewTarget()) {
					double currentAngle = getPosition().angle(target.getPosition());
					double angleDiff = angle - currentAngle;
					angle = angle - angleDiff*aimingFactor;
				}
			}
			move();
			if(System.nanoTime() > deathTime) {
				kill();
			}
		}
	}

	@Override
	public void move() {
		long diffNano = System.nanoTime() - getLastNano();
		refreshNano();
		double diffSecond = (double) diffNano/1000000000d;
		Vector nextPosition = getPosition().byAngle(angle, speed*diffSecond);
		LinkedList<Entity> targetables = new LinkedList<>();
		if(target instanceof Monster) {
			targetables.addAll(field.getMonsters());
		} else if(target instanceof Tower) {
			targetables.addAll(field.getTowers());
		} else {
			throw new IllegalStateException("Forgot : " + target.getClass());
		}
		for(Entity entity : targetables) {
			if(getAppareances().getShape().collide(entity.getAppareances().getShape()) && attack.isValidTarget(entity)) {
				double modifiedDamage = damage;
				if(!options.containsKey(GHOST)) {
					kill();
				}
				if(options.containsKey(BLEEDING)) {
					((Damageable) entity).affectWith(Effect.BLEED, options.get(BLEEDING));
				}
				if(options.containsKey(CONFUSING)) {
					((Damageable) entity).affectWith(Effect.CONFUSED, options.get(CONFUSING));
				}
				if(options.containsKey(PAPYRUS)) {
					double speed = ((Movable) entity).getSpeed();
					double damageMult = speed/50*options.get(PAPYRUS)[0];
					modifiedDamage *= damageMult;
				}
				if(options.containsKey(SLOWING)) {
					((Damageable) entity).affectWith(Effect.SLOWED, options.get(SLOWING));
				}
				((Damageable) entity).dealDamage(modifiedDamage);
				if(options.containsKey(LIFESTEAL)) {
					((Damageable) attack.getOwner()).heal(modifiedDamage*options.get(LIFESTEAL)[0]);
				}
				break;
			}
		}
		getAppareances().getCircle().setPosition(nextPosition);
	}
	
	private boolean seekNewTarget() {
		boolean found = false;
		LinkedList<Entity> targets = new LinkedList<>();
		if(target instanceof Monster) {
			targets.addAll(field.getMonsters());
		} else if(target instanceof Tower) {
			targets.addAll(field.getTowers());
		}
		for(Entity seek : targets) {
			if(target.isDead() || (getPosition().dist(seek.getPosition()) < getPosition().dist(target.getPosition()) && attack.isValidTarget(seek))) {
				target = seek;
				found = true;
			}
		}
		return found;
	}
	
	void place(Vector position) {
		getAppareances().setShape(new Circle(position, size));
		field.add(this);
		check = false;
		refreshNano();
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
	void setLifeTime(long lifeTime) {
		deathTime = System.nanoTime() + lifeTime;
	}
	void setSize(double size) {
		this.size = size;
	}
	
	Entity getTarget() {
		return target;
	}
	void setTarget(Entity target) {
		this.target = target;
	}
	
	void setColor(int r, int g, int b) {
		getAppareances().setColor(r, g, b);
	}

	@Override
	public double getAngle() {
		return angle;
	}

	@Override
	public double getSpeed() {
		return speed;
	}
	
	public double travelTime(Vector from, Vector to) {
		double dist = from.dist(to);
		return dist/getSpeed();
	}

	public void setOptions(HashMap<Option, double[]> options) {
		this.options = options;
	}
	
	public void setAttack(Attack attack) {
		this.attack = attack;
	}

	@Override
	public boolean isSlow() {
		return false;
	}

	@Override
	public boolean isFlying() {
		return false;
	}
}
