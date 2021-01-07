package fr.tm_nlm.tower_defence.control.entity;

import java.util.LinkedList;
import java.util.Random;

import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.Appareances;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;

public class Attack {
	private static Random random = new Random();
	
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
	private double size;
	private Appareances appareance;
	private LinkedList<Bullet> bulletLeft;
	private Field field;
	
	public Attack(Field field) {
		this.field = field;
		bulletLeft = new LinkedList<>();
	}
	
	public void checkForShootAt(Entity target) {
		long time = System.nanoTime();
		if(time > nextRafaleInterval) {
			nextRafaleInterval = time + ((minRafaleInterval == maxRafaleInterval) ? minRafaleInterval : random.nextInt(maxRafaleInterval - minRafaleInterval) + minRafaleInterval);
			bulletLeft = new LinkedList<>();
			int bulletToMake = (minBullet == maxBullet) ? minBullet : random.nextInt(maxBullet - minBullet) + minBullet;
			for(int n = 0; n < bulletToMake; n++) {
				Bullet bullet = new Bullet(field);
				bullet.setAiming(aiming);
				bullet.setAimingFactor(aimingFactor);
				double damage = random.nextDouble()*maxDamage - minDamage;
				bullet.setDamage(damage);
				double bulletSpeed = random.nextDouble()*maxBulletSpeed - minBulletSpeed;
				bullet.setSpeed(bulletSpeed);
				bullet.setTarget(target);
				bullet.setSize(size);
				bulletLeft.add(bullet);
			}
		}
	}
	
	public void checkForBullet(Vector from) {
		long time = System.nanoTime();
		if(time > nextInterval) {
			nextInterval = time + ((minInterval == maxInterval) ? minInterval : random.nextInt(maxInterval - minInterval) + minInterval);
			Bullet bullet = bulletLeft.poll();
			if(bullet != null) {
				double loss = random.nextDouble()*precisionLoss*2 - precisionLoss;
				double angle = from.angle(bullet.getTarget().getPosition()) + loss;
				bullet.setAngle(angle);
				int lifeTime = (minLifeTime == maxLifeTime) ? minLifeTime : random.nextInt(maxLifeTime - minLifeTime) + minLifeTime;
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
	void setSize(double size) {
		this.size = size;
	}
	
	public void setLifeTime(double minLifeTime, double maxLifeTime) {
		this.minLifeTime = (int) (minLifeTime*1000000000);
		this.maxLifeTime = (int) (maxLifeTime*1000000000);
	}
}
