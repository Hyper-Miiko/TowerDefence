package fr.tm_nlm.tower_defence.control.entity;

import java.util.LinkedList;
import java.util.Random;

import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;

public class Attack {
	private static Random random = new Random();
	
	private boolean aiming;
	private int minRed, maxRed;
	private int minGreen, maxGreen;
	private int minBlue, maxBlue;
	private int minBullet, maxBullet;
	private int minBulletByShot, maxBulletByShot;
	private double minInterval, maxInterval;
	private double nextInterval;
	private double minCooldown, maxCooldown;
	private double nextCooldom;
	private double minLifeTime, maxLifeTime;
	private double aimingFactor;
	private double minBulletSpeed, maxBulletSpeed;
	private double minDamage, maxDamage;
	private double precisionLoss;
	private double range;
	private double size;
	private LinkedList<Bullet> bulletLeft;
	private Field field;
	
	{
		aiming = false;
		minRed = maxRed = 200;
		minGreen = maxGreen = 200;
		minBlue = maxBlue = 0;
		minBullet = maxBullet = 1;
		minBulletByShot = maxBulletByShot = 1;
		minInterval = maxInterval = 0.5;
		minCooldown = maxCooldown = 1;
		minLifeTime = maxLifeTime = 2;
		aimingFactor = 0;
		minBulletSpeed = maxBulletSpeed = 100;
		minDamage = maxDamage = 2;
		precisionLoss = 0;
		range = 200;
		size = 3;
		bulletLeft = new LinkedList<>();
	}
	public Attack(Field field) {
		this.field = field;
	}
	
	public void checkForShootAt(Entity target) {
		double time = (double) System.nanoTime()/1000000000;
		if(time > nextCooldom) {
			nextCooldom = time + random.nextDouble()*(maxCooldown - minCooldown) + minCooldown;
			bulletLeft = new LinkedList<>();
			int bulletToMake = (minBullet == maxBullet) ? minBullet : random.nextInt(maxBullet - minBullet) + minBullet;
			for(int n = 0; n < bulletToMake; n++) {
				Bullet bullet = new Bullet(field);
				bullet.setAiming(aiming);
				bullet.setAimingFactor(aimingFactor);
				double damage = random.nextDouble()*(maxDamage - minDamage) + minDamage;
				bullet.setDamage(damage);
				double bulletSpeed = random.nextDouble()*(maxBulletSpeed - minBulletSpeed) + minBulletSpeed;
				bullet.setSpeed(bulletSpeed);
				bullet.setTarget(target);
				bullet.setSize(size);
				int red = (minRed == maxRed) ? minRed : random.nextInt(maxRed - minRed) + minRed;
				int green = (minGreen == maxGreen) ? minGreen : random.nextInt(maxGreen - minGreen) + minGreen;
				int blue = (minBlue == maxBlue) ? minBlue : random.nextInt(maxBlue - minBlue) + minBlue;
				bullet.setColor(red, green, blue);
				bulletLeft.add(bullet);
			}
		}
	}
	
	public void checkForBullet(Vector from) {
		double time = (double) System.nanoTime()/1000000000;
		if(time > nextInterval) {
			nextInterval = time + random.nextDouble()*(maxInterval - minInterval) + minInterval;
			int bulletByShot = (minBulletByShot == maxBulletByShot) ? minBulletByShot : random.nextInt(maxBulletByShot - minBulletByShot) + minBulletByShot;
			for(int i = 0; i < bulletByShot; i++) {
				Bullet bullet = bulletLeft.poll();
				if(bullet != null) {
					double angle = calcAngle(bullet, from);
					bullet.setAngle(angle);
					double lifeTime = random.nextDouble()*(maxLifeTime - minLifeTime) + minLifeTime;
					bullet.setLifeTime((long) (lifeTime*1000000000));
					bullet.place(from);
				}
			}
		}
	}
	
	private double calcAngle(Bullet bullet, Vector from) {
		Movable target = (Movable) bullet.getTarget();
		double marge = bullet.getAppareances().getCircle().getRadius() + bullet.getTarget().getAppareances().getCircle().getRadius()/2;
		final Vector targetPosition = target.getPosition();
		final double targetDirection = target.getAngle();
		Vector targetNextPosition = targetPosition;
		if(minBulletSpeed > target.getSpeed()) {
			Vector bulletNextPosition;
			double travelTime;
			double targetTravel;
			do {
				travelTime = bullet.travelTime(from, targetNextPosition);
				bulletNextPosition = targetNextPosition;
				targetNextPosition = targetPosition.byAngle(targetDirection, target.getSpeed()*travelTime);
				targetTravel = bulletNextPosition.dist(targetNextPosition);
			} while(targetTravel > marge);
		}
		double angle = from.angle(targetNextPosition);
		double loss = 2*Math.PI*random.nextDouble()*precisionLoss - Math.PI*precisionLoss;
		return angle + loss;
	}
	
	public void setAimingFactor(double aimingFactor) {
		this.aimingFactor = aimingFactor;
		this.aiming = aimingFactor != 0d;
	}
	
	public void setBulletByShot(int bulletByShot) {
		setBulletByShot(bulletByShot, bulletByShot);
	}
	
	public void setBulletByShot(int minBulletByShot, int maxBulletByShot) {
		this.minBulletByShot = minBulletByShot;
		this.maxBulletByShot = maxBulletByShot;
	}
	
	public void setRed(int red) {
		setRed(red, red);
	}
	
	public void setRed(int minRed, int maxRed) {
		this.minRed = minRed;
		this.maxRed = maxRed;
	}
	
	public void setGreen(int green) {
		setGreen(green, green);
	}
	
	public void setGreen(int minGreen, int maxGreen) {
		this.minGreen = minGreen;
		this.maxGreen = maxGreen;
	}
	
	public void setBlue(int blue) {
		setBlue(blue, blue);
	}
	
	public void setBlue(int minBlue, int maxBlue) {
		this.minBlue = minBlue;
		this.maxBlue = maxBlue;
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
		this.minInterval = minInterval;
		this.maxInterval = maxInterval;
	}
	
	public void setCooldown(double cooldown) {
		setCooldown(cooldown, cooldown);
	}
	
	public void setCooldown(double minCooldown, double maxCooldown) {
		this.minCooldown = minCooldown;
		this.maxCooldown = maxCooldown;
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
		if(precisionLoss > 1 || precisionLoss < 0) {
			throw new IllegalArgumentException("between 0 and 1.");
		}
		this.precisionLoss = precisionLoss;
	}
	
	public void setLifeTime(double lifeTime) {
		setLifeTime(lifeTime, lifeTime);
	}
	
	public void setLifeTime(double minLifeTime, double maxLifeTime) {
		this.minLifeTime = minLifeTime;
		this.maxLifeTime = maxLifeTime;
	}
	
	void setSize(double size) {
		this.size = size;
	}
	
	double getRange() {
		return range;
	}
	void setRange(double range) {
		this.range = range;
	}
}
