package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.util.HashSet;
import java.util.Random;

import fr.tm_nlm.tower_defence.Couple;

public class Bullet implements Displayable, Movable, Cloneable {
	private static Random random = new Random();
	
	private boolean attackKeepTarget;
	private boolean attackOnCollide;
	private boolean attackOnNaturalDeath;
	private boolean dead;
	private boolean ghost;
	private boolean heal;
	private boolean track;
	private int minBlue, maxBlue;
	private int minGreen, maxGreen;
	private int minRed, maxRed;
	private double aimingFactor;
	private double baseSpeed;
	private double decayTimer;
	private double fadeAt;
	private double fadingTime;
	private double lastMove;
	private double minDamage, maxDamage;
	private double minFadingTime, maxFadingTime;
	private double minLifeTime, maxLifeTime;
	private Attack onDeathAttack;
	private Couple<Double, Double> slow;
	private EntityType collideWith;
	private Game game;
	private Geometric shape;
	private Vector aimingPosition;
	private Localisable tracked;
	
	{
		aimingFactor = 0;
		attackKeepTarget = false;
		attackOnCollide = true;
		attackOnNaturalDeath = true;
		baseSpeed = 100;
		collideWith = EntityType.MONSTER;
		dead = false;
		fadeAt = Double.POSITIVE_INFINITY;
		fadingTime = 0;
		ghost = false;
		heal = false;
		minDamage = maxDamage = 1;
		minBlue = maxBlue = 255;
		minGreen = maxGreen = 255;
		minRed = maxRed = 255;
		minLifeTime = maxLifeTime = 2;
		onDeathAttack = null;
		collideWith = EntityType.MONSTER;
		shape = PresetShape.circle(5);
		slow = new Couple<>(0d, 0d);
		track = false;
	}

	@Override
	public Vector getPosition() {
		return shape.getPosition();
	}

	@Override
	public boolean isOnScreen() {
		return havePosition();
	}
	
	public void process() {
		double currentTime = Game.time();
		if(fadeAt == Double.POSITIVE_INFINITY) {
			calcAngle(currentTime - lastMove);
			move(currentTime - lastMove, !ghost);
			lastMove = currentTime;
			if(currentTime > decayTimer) {
				die();
			}
		} else if(!dead && currentTime > fadeAt) {
			dead = true;
		}
	}
	
	private void calcAngle(double time) {
		if(track) {
			aimingPosition = tracked.getPosition();
		}
		Angle angle = getPosition().angle(aimingPosition);
		Angle diff = Angle.diff(shape.getAngle(), angle);
		double adjust = Math.PI*time*aimingFactor;
		adjust = (adjust > diff.value()) ? diff.value() : adjust;
		Angle newAngle;
		if(diff.value() > Math.PI) {
			newAngle = new Angle(angle.value() - adjust);
		} else {
			newAngle = new Angle(angle.value() + adjust);
		}
		setAngle(newAngle);
	}
	
	private void die() {
		fadeAt = Game.time() + fadingTime;
	}

	@Override
	public String getImage() {
		return shape.getImage();
	}

	@Override
	public double getSpeed() {
		double actualSpeed = getBaseSpeed();
		return actualSpeed;
	}

	@Override
	public double getBaseSpeed() {
		return baseSpeed;
	}

	@Override
	public Couple<Area, Color> getShape() {
		if(fadeAt != Double.POSITIVE_INFINITY) {
			Area area = shape.getShape()._1;
			Color color = shape.getShape()._2;
			int alpha = color.getAlpha();
			alpha *= (fadeAt - Game.time())/fadingTime;
			alpha = (alpha < 0) ? 0 : alpha;
			alpha = (alpha > 255) ? 255 : alpha;
			color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
			return new Couple<>(area, color);
		} else {
			return shape.getShape();
		}
	}

	@Override
	public boolean move(double time, boolean b) {
		shape.translateByAngle(time*getSpeed());
		HashSet<Localisable> collideables = new HashSet<>();
		switch(collideWith) {
		case MONSTER:
			collideables.addAll(game.readMonsters());
			break;
		case TOWER:
			collideables.addAll(game.readTowers());
			break;
		default:
			break;
		}
		HashSet<Localisable> toucheds = new HashSet<>();
		for(Localisable collideable : collideables) {
			if(shape.collide(collideable)) {
					toucheds.add(collideable);
			}
		}
		if(!toucheds.isEmpty()) {
			if(!ghost) {
				die();
			}
			for(Localisable touched : toucheds) {
				if(touched instanceof Damageable) {
					double damage = valueBetween(minDamage, maxDamage);
					if(!heal) {
						((Damageable) touched).hurt(damage);
					} else {
						((Damageable) touched).heal(damage);
					}
				}
				
				if(touched instanceof Movable) {
					((Movable) touched).slow(slow);
				}
			}
		}
		return true;
	}
	
	private double valueBetween(double min, double max) {
		return (max > min) ? (max - min)*random.nextDouble() + min : min;
	}
	
//	private int valueBetween(int min, int max) {
//		return (max > min) ? random.nextInt(max - min + 1) + min : min;
//	}

	public void setShape(Geometric shape) {
		this.shape = shape;
	}

	public void setSpeed(double speed) {
		this.baseSpeed = speed;
	}

	public void setDamage(double damage) {
		setDamage(damage, damage);
	}
	public void setDamage(double minDamage, double maxDamage) {
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}

	public void setAimingFactor(double aimingFactor) {
		this.aimingFactor = aimingFactor;
	}

	public void setPosition(Vector position) {
		shape.setPosition(position);
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void resetMove() {
		lastMove = Game.time();
		decayTimer = Game.time() + valueBetween(minLifeTime, maxLifeTime);
	}

	public void setAngle(Angle angle) {
		shape.setAngle(angle);
	}
	
	@Override
	public Object clone() {
		Bullet bullet = new Bullet();
		bullet.ghost = ghost;
		bullet.aimingFactor = aimingFactor;
		bullet.aimingPosition = aimingPosition;
		bullet.game = game;
		bullet.shape = (Geometric) shape.clone();
		bullet.baseSpeed = baseSpeed;
		bullet.tracked = tracked;
		bullet.track = track;
		return bullet;
	}

	public boolean isDead() {
		return dead;
	}
	
	public void setSlow(Couple<Double, Double> slow) {
		this.slow = slow;
	}

	@Override
	public void slow(Couple<Double, Double> slow) {}

	public void setImage(String image) {
		shape.setImage(image);
	}
	
	public void setLifeTime(double lifeTime) {
		setLifeTime(minLifeTime, maxLifeTime);
	}

	public void setLifeTime(double minLifeTime, double maxLifeTime) {
		this.minLifeTime = minLifeTime;
		this.maxLifeTime = maxLifeTime;
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

	public void setFadingTime(double fadingTime) {
		setFadingTime(fadingTime);
	}

	public void setFadingTime(double minFadingTime, double maxFadingTime) {
		this.minFadingTime = minFadingTime;
		this.maxFadingTime = maxFadingTime;
	}

	public void setOnDeathAttack(Attack onDeathAttack, boolean attackOnCollide, boolean attackOnNaturalDeath) {
		this.onDeathAttack  = onDeathAttack;
		this.attackOnCollide = attackOnCollide;
		this.attackOnNaturalDeath = attackOnNaturalDeath;
	}

	public void setTracked(Localisable target) {
		this.tracked = target;
		aimingPosition = target.getPosition();
	}

	public void setTrack(boolean track) {
		this.track = track;
	}
}
