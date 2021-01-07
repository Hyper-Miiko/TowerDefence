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
	private int minInterval;
	private int maxInterval;
	private long nextInterval;
	private int minRafaleInterval;
	private int maxRafaleInterval;
	private long nextRafaleInterval;
	private int minLifeTime;
	private int maxLifeTime;
	private double aimingFactor;
	private double minBulletSpeed;
	private double maxBulletSpeed;
	private double minDamage;
	private double maxDamage;
	private double precisionLoss;
	private Appareances appareance;
	private LinkedList<Bullet<E>> bulletLeft;
	private Field field;
	
	public Attack(E canTarget) {
		if(!(canTarget instanceof Monster) && !(canTarget instanceof Tower)) {
			throw new IllegalArgumentException("Target must be Monster or Tower. get: " + canTarget.getClass());
		}
		bulletLeft = new LinkedList<>();
	}
	
	public void checkForShootAt(E target) {
		long time = System.nanoTime();
		if(time > nextRafaleInterval) {
			nextRafaleInterval += random.nextInt(maxRafaleInterval - minRafaleInterval) + minRafaleInterval;
			bulletLeft = new LinkedList<>();
			int bulletToMake = random.nextInt(maxBullet - minBullet) + minBullet;
			for(int n = 0; n < bulletToMake; n++) {
				Bullet<E> bullet = new Bullet<>(field);
				bullet.setAiming(aiming);
				bullet.setAimingFactor(aimingFactor);
				double damage = random.nextDouble()*maxDamage - minDamage;
				bullet.setDamage(damage);
				double bulletSpeed = random.nextDouble()*maxBulletSpeed - minBulletSpeed;
				bullet.setSpeed(bulletSpeed);
				bullet.setTarget(target);
			}
		}
	}
	
	public void checkForBullet(Vector from) {
		long time = System.nanoTime();
		if(time > nextInterval) {
			nextInterval += random.nextInt(maxInterval - minInterval) + minInterval;
			Bullet<E> bullet = bulletLeft.pop();
			if(bullet != null) {
				double loss = random.nextDouble()*precisionLoss*2 - precisionLoss;
				double angle = from.angle(bullet.getTarget().getPosition()) + loss;
				bullet.setAngle(angle);
				int lifeTime = random.nextInt(maxLifeTime - minLifeTime) + minLifeTime;
				bullet.setLifeTime(lifeTime);
				bullet.place(from);
			}
		}
	}
	
	public void setAimingFactor(double aimingFactor) {
		this.aimingFactor = aimingFactor;
		this.aiming = aimingFactor != 0d;
	}
	
	public void setNbrOfBullet(int nbrOfBullet) {
		this.minBullet = nbrOfBullet;
		this.maxBullet = nbrOfBullet;
	}
	
	public void setNbrOfBullet(int minBullet, int maxBullet) {
		this.minBullet = minBullet;
		this.maxBullet = maxBullet;
	}
	
	public void setInterval(double interval) {
		setInterval(interval, interval);
	}
	
	public void setInterval(double minInterval, double maxInterval) {
		this.minInterval = (int) (minInterval*1000000000);
		this.maxInterval = (int) (maxInterval*1000000000);
	}
	
	public void setRafaleInterval(double rafaleInterval) {
		setRafaleInterval(rafaleInterval, rafaleInterval);
	}
	
	public void setRafaleInterval(double minRafaleInterval, double maxRafaleInterval) {
		this.minRafaleInterval = (int) (minRafaleInterval*1000000000);
		this.maxRafaleInterval = (int) (maxRafaleInterval*1000000000);
	}
	
	public void setBulletSpeed(double bulletSpeed) {
		setBulletSpeed(bulletSpeed, bulletSpeed);
	}
	
	public void setBulletSpeed(double minBulletSpeed, double maxBulletSpeed) {
		this.minBulletSpeed = minBulletSpeed;
		this.maxBulletSpeed = maxBulletSpeed;
	}
	
	public void setDamage(double damage) {
		setDamage(damage, damage);
	}
	
	public void setDamage(double minDamage, double maxDamage) {
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}
	
	public void setPrecisionLoss(double precisionLoss) {
		if(precisionLoss > 100) {
			throw new IllegalArgumentException("Percent are under 100.");
		}
		this.precisionLoss = Math.PI/100*(100-precisionLoss);
	}
	
	public void setLifeTime(double lifeTime) {
		setLifeTime(lifeTime, lifeTime);
	}
	
	public void setLifeTime(double minLifeTime, double maxLifeTime) {
		this.minLifeTime = (int) (minLifeTime*1000000000);
		this.maxLifeTime = (int) (maxLifeTime*1000000000);
	}
}
