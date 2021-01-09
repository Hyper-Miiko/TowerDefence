package fr.tm_nlm.tower_defence.control.entity;

import java.awt.Color;
import java.util.ArrayList;
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
	private int nbrOfColor;
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
	private ArrayList<String> quotes;
	private Entity owner;
	private Field field;
	private LinkedList<Bullet> bulletLeft;
	
	{
		aiming = false;
		minRed = maxRed = 200;
		minGreen = maxGreen = 200;
		minBlue = maxBlue = 0;
		minBullet = maxBullet = 1;
		minBulletByShot = maxBulletByShot = 1;
		nbrOfColor = 255;
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
		quotes = new ArrayList<>();
		owner = null;
	}
	public Attack(Field field) {
		this.field = field;
	}
	
	public void checkForShootAt(Entity target) {
		double time = (double) System.nanoTime()/1000000000;
		if(time > nextCooldom) {
			launchQuote();
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
				calcColor(bullet);
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
	
	private void launchQuote() {
		if(quotes.size() > 0) {
			String quoteTeller = (owner == null) ? "Anonymous" : owner.getName();
			int randomIndex = random.nextInt(quotes.size());
			String quote = quotes.get(randomIndex);
			System.out.println(quoteTeller + ": " + quote);
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
	
	private void calcColor(Bullet bullet) {
		int tempRed = (minRed == maxRed) ? minRed : random.nextInt(maxRed - minRed) + minRed;
		int tempGreen = (minGreen == maxGreen) ? minGreen : random.nextInt(maxGreen - minGreen) + minGreen;
		int tempBlue = (minBlue == maxBlue) ? minBlue : random.nextInt(maxBlue - minBlue) + minBlue;
		int step = 255/(nbrOfColor + 1);
		int red, green, blue;
		red = green = blue = 0;
		for(int i = 0; i <= nbrOfColor; i++) {
			if(tempRed > step) {
				tempRed -= 255/nbrOfColor;
				red += 255/nbrOfColor;
			}
			if(tempGreen > step) {
				tempGreen -= 255/nbrOfColor;
				green += 255/nbrOfColor;
			}
			if(tempBlue > step) {
				tempBlue -= 255/nbrOfColor;
				blue += 255/nbrOfColor;
			}
		}
		if(red   < 000) red   = 000;
		if(red   > 255) red   = 255;
		if(green < 000) green = 000;
		if(green > 255) green = 255;
		if(blue  < 000) blue  = 000;
		if(blue  > 255) blue  = 255;
		if(red == 0 && green == 0 && blue == 0) {
			calcColor(bullet);
		} else {
			bullet.setColor(red, green, blue);
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
	
	public void setSize(double size) {
		this.size = size;
	}
	
	public double getRange() {
		return range;
	}
	public void setRange(double range) {
		this.range = range;
	}
	public void addQuote(String quote) {
		quotes.add(quote);
	}
	public void setOwner(Entity owner) {
		this.owner = owner;
	}
	public void setNbrOfColor(int nbrOfColor) {
		this.nbrOfColor = nbrOfColor;
	}
}
