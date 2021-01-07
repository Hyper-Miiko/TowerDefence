package fr.tm_nlm.tower_defence.control.entity;

import java.util.LinkedList;
import java.util.Random;

import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.Appareances;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;

public class Attack<E extends Entity> {
	private static Random random = new Random();
	
	private final E canTarget = null;
	private boolean aiming;
	private int minBullet;
	private int maxBullet;
	private long minInterval;
	private long maxInterval;
	private long nextInterval;
	private long minRafalInterval;
	private long maxRafaleInterval;
	private long nextRafaleInterval;
	private double aimingFactor;
	private double bulletSpeed;
	private double damage;
	private double precisionLoss;
	private Appareances appareance;
	private LinkedList<Bullet<E>> bulletLeft;
	private Field field;
	
	public Attack() {
		if(!(canTarget instanceof Monster) && !(canTarget instanceof Tower)) {
			throw new IllegalArgumentException("Target must be Monster or Tower.");
		}
		bulletLeft = new LinkedList<>();
	}
	
	public void shootAt(E target) {
		bulletLeft = new LinkedList<>();
		int bulletToMake = random.nextInt(maxBullet - minBullet) + minBullet;
		for(int n = 0; n < bulletToMake; n++) {
			Bullet<E> bullet = new Bullet<>(field);
			bullet.setAiming(aiming);
			bullet.setAimingFactor(aimingFactor);
			bullet.setDamage(damage);
			bullet.setSpeed(bulletSpeed);
			bullet.setTarget(target);
		}
	}
	
	public void checkForBullet(Vector from) {
		Bullet<E> bullet = bulletLeft.pop();
		if(bullet != null) {
			double loss = random.nextDouble()*precisionLoss*2 - precisionLoss;
			double angle = from.angle(bullet.getTarget().getPosition()) + loss;
			bullet.setAngle(angle);
		}
	}
}
