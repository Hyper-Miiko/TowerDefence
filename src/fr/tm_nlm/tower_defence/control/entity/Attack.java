package fr.tm_nlm.tower_defence.control.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;

import static fr.tm_nlm.tower_defence.control.entity.Attack.Option.*;

public class Attack {
	private static Random random = new Random();
	
	private boolean aiming;
	private int minRed, maxRed;
	private int minGreen, maxGreen;
	private int minBlue, maxBlue;
	private double minBullet, maxBullet;
	private double minBulletByShot, maxBulletByShot;
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
	private Attack chainAttack;
	private Entity lastTarget;
	private Entity owner;
	private Field field;
	private LinkedList<Bullet> bulletLeft;
	private HashMap<Option, double[]> options;
	
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
		quotes = new ArrayList<>();
		chainAttack = null;
		lastTarget = null;
		owner = null;
		bulletLeft = new LinkedList<>();
		options = new HashMap<>();
	}
	public Attack(Field field) {
		this.field = field;
	}
	
	public void checkForShootAt(Entity target) {
		double time = (double) System.nanoTime()/1000000000;
		if(time > nextCooldom) {
			calcIncreassOptions(target);
			launchQuote();
			nextCooldom = time + random.nextDouble()*(maxCooldown - minCooldown) + minCooldown;
			forceShoot(target);
		}
	}
	
	public void forceShoot(Entity target) {
		launchQuote();
		bulletLeft = new LinkedList<>();
		int bulletToMake = (int) ((minBullet == maxBullet) ? minBullet : random.nextInt((int) (maxBullet - minBullet)) + minBullet);
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
			sendOptions(bullet);
			bullet.setAttack(this);
			bullet.setChainAttack(chainAttack);
			bulletLeft.add(bullet);
		}
	}
	
	public void checkForBullet(Vector from) {
		double time = (double) System.nanoTime()/1000000000;
		if(time > nextInterval) {
			nextInterval = time + random.nextDouble()*(maxInterval - minInterval) + minInterval;
			forceBullet(from);
		}
	}
	
	public void forceBullet(Vector from) {
		int bulletByShot = (int) ((minBulletByShot == maxBulletByShot) ? minBulletByShot : random.nextInt((int) (maxBulletByShot - minBulletByShot)) + minBulletByShot);
		for(int i = 0; i < bulletByShot; i++) {
			Bullet bullet = bulletLeft.poll();
			if(bullet != null) {
				calcIncreassOptions(bullet.getTarget());
				double angle = calcAngle(bullet, from);
				bullet.setAngle(angle);
				double lifeTime = random.nextDouble()*(maxLifeTime - minLifeTime) + minLifeTime;
				bullet.setLifeTime((long) (lifeTime*1000000000));
				bullet.place(from);
			}
		}
	}
	
	public boolean isValidTarget(Entity entity) {
		boolean valid = true;
		if(((Movable) entity).isFlying()) {
			valid &= options.containsKey(TARGET_FLYING);
		} else {
			valid &= options.containsKey(TARGET_WALKING);
		}
		if(((Monster) entity).isBoss()) {
			valid &= options.containsKey(TARGET_BOSS);
		} else {
			valid &= options.containsKey(TARGET_MOB);
		}
		return valid;
	}
	
	private void calcIncreassOptions(Entity target) {
		if(lastTarget != null) {
			double[] datas;
			if(target.equals(lastTarget)) {
				datas = options.get(INCREASS_BULLET);
				if(datas != null) {
					minBullet += datas[0];
					maxBullet += datas[0];
				}
				datas = options.get(INCREASS_BULLET_BY_SHOT);
				if(datas != null) {
					minBulletByShot += datas[0];
					maxBulletByShot += datas[0];
				}
				datas = options.get(INCREASS_DAMAGE);
				if(datas != null) {
					minDamage += datas[0];
					maxDamage += datas[0];
				}
				datas = options.get(INCREASS_SIZE);
				if(datas != null) {
					size += datas[0];
				}
			} else {
				datas = options.get(INCREASS_BULLET);
				if(datas != null) {
					minBullet = datas[1];
					maxBullet = datas[2];
				}
				datas = options.get(INCREASS_BULLET_BY_SHOT);
				if(datas != null) {
					minBulletByShot = datas[1];
					maxBulletByShot = datas[2];
				}
				datas = options.get(INCREASS_DAMAGE);
				if(datas != null) {
					minDamage = datas[1];
					maxDamage = datas[2];
				}
				datas = options.get(INCREASS_SIZE);
				if(datas != null) {
					size = datas[1];
				}
			}
		}
		lastTarget = target;
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
	
	private void sendOptions(Bullet bullet) {
		double[] datas, dummy;
		datas = options.get(INCREASS_BULLET_BY_SHOT);
		if(datas != null && datas.length != 3) {
			dummy = datas;
			datas = new double[3];
			datas[0] = dummy[0];
			datas[1] = minBulletByShot;
			datas[2] = maxBulletByShot;
			options.put(INCREASS_BULLET_BY_SHOT, datas);
		}
		datas = options.get(INCREASS_BULLET);
		if(datas != null && datas.length != 3) {
			dummy = datas;
			datas = new double[3];
			datas[0] = dummy[0];
			datas[1] = minBullet;
			datas[2] = maxBullet;
			options.put(INCREASS_BULLET, datas);
		}
		datas = options.get(INCREASS_SIZE);
		if(datas != null && datas.length != 2) {
			dummy = datas;
			datas = new double[2];
			datas[0] = dummy[0];
			datas[1] = size;
			options.put(INCREASS_SIZE, datas);
		}
		datas = options.get(INCREASS_DAMAGE);
		if(datas != null && datas.length != 3) {
			dummy = datas;
			datas = new double[3];
			datas[0] = dummy[0];
			datas[1] = minDamage;
			datas[2] = maxDamage;
			options.put(INCREASS_DAMAGE, datas);
		}
		
		bullet.setOptions(options);
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
		this.size = (size < 1) ? 1 : size;
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
	public void addOption(Option option, double... data) {
		switch(option) {
		case GHOST:
		case TARGET_BOSS:
		case TARGET_MOB:
		case TARGET_FLYING:
		case TARGET_WALKING:
			if(data.length != 0) {
				throw new IllegalArgumentException(option + " don't allow any double");
			}
			data = null;
			break;
		case CONFUSING:
		case INCREASS_BULLET:
		case INCREASS_BULLET_BY_SHOT:
		case INCREASS_DAMAGE:
		case INCREASS_SIZE:
		case LIFESTEAL:
		case PAPYRUS:
			if(data.length != 1) {
				throw new IllegalArgumentException(option + " need 1 and only 1 double");
			}
			break;
		case BLEEDING:
		case SLOWING:
			if(data.length != 2) {
				throw new IllegalArgumentException(option + " need 2 and only 2 double");
			}
		}
		options.put(option, data);
	}
	
	public void removeOption(Option option) {
		options.put(option,  null);
	}
	
	public Entity getOwner() {
		return owner;
	}
	
	public void setChainAttack(Attack chainAttack) {
		this.chainAttack = chainAttack;
	}
	
	public enum Option {
		BLEEDING,
		CONFUSING,
		GHOST,
		INCREASS_BULLET,
		INCREASS_BULLET_BY_SHOT,
		INCREASS_DAMAGE,
		INCREASS_SIZE,
		LIFESTEAL,
		PAPYRUS,
		SLOWING,
		TARGET_BOSS,
		TARGET_FLYING,
		TARGET_MOB,
		TARGET_WALKING,
	}
}
