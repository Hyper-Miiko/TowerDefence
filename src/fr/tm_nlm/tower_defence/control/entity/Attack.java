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
	private int minInterval, maxInterval;
	private long nextInterval;
	private int minCooldown, maxCooldown;
	private long nextCooldom;
	private int minLifeTime, maxLifeTime;
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
		minInterval = maxInterval = 500000000;
		minCooldown = maxCooldown = 1000000000;
		minLifeTime = maxLifeTime = 2000000000;
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
		long time = System.nanoTime();
		if(time > nextCooldom) {
			nextCooldom = time + ((minCooldown == maxCooldown) ? minCooldown : random.nextInt(maxCooldown - minCooldown) + minCooldown);
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
		long time = System.nanoTime();
		if(time > nextInterval) {
			nextInterval = time + ((minInterval == maxInterval) ? minInterval : random.nextInt(maxInterval - minInterval) + minInterval);
			int bulletByShot = (minBulletByShot == maxBulletByShot) ? minBulletByShot : random.nextInt(maxBulletByShot - minBulletByShot) + minLifeTime;
			for(int i = 0; i < bulletByShot; i++) {
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
		this.minInterval = (int) (minInterval*1000000000);
		this.maxInterval = (int) (maxInterval*1000000000);
	}
	
	public void setCooldown(double cooldown) {
		setCooldown(cooldown, cooldown);
	}
	
	public void setCooldown(double minCooldown, double maxCooldown) {
		this.minCooldown = (int) (minCooldown*1000000000);
		this.maxCooldown = (int) (maxCooldown*1000000000);
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
		this.precisionLoss = Math.PI/100*precisionLoss;
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
	
	double getRange() {
		return range;
	}
	void setRange(double range) {
		this.range = range;
	}
}
