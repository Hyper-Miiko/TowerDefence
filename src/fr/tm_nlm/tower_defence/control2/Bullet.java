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
	private boolean collideFlying;
	private boolean collideWalking;
	private boolean dead;
	private boolean ghost;
	private boolean heal;
	private boolean track;
	private int minAlpha, maxAlpha;
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
		attackOnCollide = false;
		attackOnNaturalDeath = false;
		collideFlying = false;
		collideWalking = true;
		baseSpeed = 100;
		collideWith = EntityType.MONSTER;
		dead = false;
		fadeAt = Double.POSITIVE_INFINITY;
		ghost = false;
		heal = false;
		minAlpha = maxAlpha = 255;
		minBlue = maxBlue = 255;
		minDamage = maxDamage = 1;
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
				if(attackOnNaturalDeath) {
					deathAttack();
				}
				die();
			}
		} else if(!dead && currentTime > fadeAt) {
			dead = true;
		}
	}
	
	private void calcAngle(double time) {
		if(track) {
			if(tracked instanceof Damageable && ((Damageable) tracked).isDead()) {
				HashSet<Localisable> targets = new HashSet<>();
				switch(collideWith) {
				case MONSTER:
					targets.addAll(game.readMonsters());
					break;
				case TOWER:
					targets.addAll(game.readTowers());
					break;
				default:
					break;
				}
				tracked = seek(targets);
			}
			if(tracked != null) {
				aimingPosition = tracked.getPosition();
			}
		}
		Angle currentAngle = getPosition().angle(aimingPosition);
		Angle angleDiff = Angle.diff(getAngle(), currentAngle);
		Angle newAngle = Angle.diff(getAngle(), new Angle(angleDiff.value()*aimingFactor*time));
		if(angleDiff.value() > Math.PI) {
			newAngle = Angle.diff(getAngle(), new Angle(-angleDiff.value()*aimingFactor*time));
		} else {
			newAngle = Angle.diff(getAngle(), new Angle(angleDiff.value()*aimingFactor*time));
		}
		setAngle(newAngle);
	}
	
	private Localisable seek(HashSet<Localisable> targets) {
		Localisable currentTarget = null;
		double minDist = Double.POSITIVE_INFINITY;
		for(Localisable elem : targets)	{
			double dist = getPosition().dist(elem.getPosition());
			if(dist < minDist) {
				minDist = dist;
				currentTarget = elem;
			}
		}
		return currentTarget;
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
			if((collideWalking && !collideable.isFlying() || collideFlying && collideable.isFlying())
					&& shape.collide(collideable)) {
					toucheds.add(collideable);
			}
		}
		if(!toucheds.isEmpty()) {
			if(!ghost) {
				if(attackOnCollide) {
					deathAttack();
				}
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
					if(slow._1 > 0) {
						((Movable) touched).slow(slow);
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public Object clone() {
		Bullet bullet = new Bullet();
		
		bullet.ghost = ghost;
		bullet.aimingFactor = aimingFactor;
		bullet.aimingPosition = aimingPosition;
		bullet.attackOnCollide = attackOnCollide;
		bullet.attackOnNaturalDeath = attackOnNaturalDeath;
		bullet.attackKeepTarget = attackKeepTarget;
		bullet.baseSpeed = baseSpeed;
		bullet.collideFlying = collideFlying;
		bullet.collideWalking = collideWalking;
		bullet.game = game;
		bullet.minAlpha = minAlpha;
		bullet.maxAlpha = maxAlpha;
		bullet.minBlue = minBlue;
		bullet.maxBlue = maxBlue;
		bullet.minDamage = minDamage;
		bullet.maxDamage = maxDamage;
		bullet.minFadingTime = minFadingTime;
		bullet.maxFadingTime = maxFadingTime;
		bullet.minGreen = minGreen;
		bullet.maxGreen = maxGreen;
		bullet.minLifeTime = minLifeTime;
		bullet.maxLifeTime = maxLifeTime;
		bullet.minRed = minRed;
		bullet.maxRed = maxRed;
		bullet.onDeathAttack = onDeathAttack;
		bullet.shape = (Geometric) shape.clone();
		bullet.slow = slow;
		bullet.tracked = tracked;
		bullet.track = track;
		
		return bullet;
	}

	@Override
	public void resetMove() {
		lastMove = Game.time();
		decayTimer = Game.time() + valueBetween(minLifeTime, maxLifeTime);
		Color color = new Color(valueBetween(minRed, maxRed), valueBetween(minGreen, maxGreen), valueBetween(minBlue, maxBlue), valueBetween(minAlpha, maxAlpha));
		shape.setColor(color);
	}
	
	private void die() {
		fadingTime = valueBetween(minFadingTime, maxFadingTime);
		fadeAt = Game.time() + fadingTime;
	}
	
	private void deathAttack() {
		onDeathAttack.setMap(game.getMap());
		if(attackKeepTarget) {
			onDeathAttack.attack(tracked);
			onDeathAttack.shot(getPosition(), tracked.getPosition());
		} else {
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
			Localisable target = onDeathAttack.seek(getPosition(), collideables);
			onDeathAttack.attack(target);
			onDeathAttack.shot(getPosition(), target.getPosition());
		}
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
	
	private double valueBetween(double min, double max) {
		return (max > min) ? (max - min)*random.nextDouble() + min : min;
	}
	
	private int valueBetween(int min, int max) {
		return (max > min) ? random.nextInt(max - min + 1) + min : min;
	}

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

	public void setAngle(Angle angle) {
		shape.setAngle(angle);
	}

	public boolean isDead() {
		return dead;
	}
	
	public void setSlow(double slowFactor, double slowTime) {
		this.slow = new Couple<>(slowFactor, slowTime);
	}

	@Override
	public void slow(Couple<Double, Double> slow) {}

	public void setImage(String image) {
		shape.setImage(image);
	}
	
	public void setLifeTime(double lifeTime) {
		setLifeTime(lifeTime, lifeTime);
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

	public void setAlpha(int alpha) {
		setBlue(alpha, alpha);
	}

	public void setAlpha(int minAlpha, int maxAlpha) {
		this.minAlpha = minAlpha;
		this.maxAlpha = maxAlpha;
	}

	public void setFadingTime(double fadingTime) {
		setFadingTime(fadingTime, fadingTime);
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

	@Override
	public Angle getAngle() {
		return shape.getAngle();
	}

	public void setGhost(boolean ghost) {
		this.ghost = ghost;
	}

	public void setAttackKeepTarget(boolean attackKeepTarget) {
		this.attackKeepTarget = attackKeepTarget;
	}

	@Override
	public boolean isFlying() {
		return true;
	}

	public void setCollideFlying(boolean collideFlying) {
		this.collideFlying = collideFlying;
	}

	public void setCollideWalking(boolean collideWalking) {
		this.collideWalking = collideWalking;
	}
}
