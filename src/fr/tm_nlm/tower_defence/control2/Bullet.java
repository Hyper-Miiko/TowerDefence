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
	private double aimingFactor;
	private double baseSpeed;
	private double fadeAt;
	private double fadingTime;
	private double lastMove;
	private double minDamage, maxDamage;
	private Attack onDeathAttack;
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
		onDeathAttack = null;
		collideWith = EntityType.MONSTER;
		shape = PresetShape.circle(5);
		track = false;
	}

	@Override
	public Vector getPosition() {
		return shape.getPosition();
	}

	@Override
	public boolean haveImage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOnScreen() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void process() {
		double currentTime = Game.time();
		if(fadeAt == Double.POSITIVE_INFINITY) {
			move(currentTime - lastMove, !ghost);
			lastMove = currentTime;
			HashSet<Localisable> potentialTargets = new HashSet<>();
			if(tracked instanceof Monster) {
				potentialTargets.addAll(game.readMonsters());
			}
			for(Localisable potentialTarget : potentialTargets) {
				if(collide(potentialTarget)) {
					
				}
			}
		} else if(!dead && currentTime > fadeAt) {
			dead = true;
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

	@Override
	public Couple<Area, Color> getShape() {
		if(fadeAt == Double.POSITIVE_INFINITY) {
			Area area = shape.getShape()._1;
			Color color = shape.getShape()._2;
			int alpha = color.getAlpha();
			alpha *= (fadeAt - Game.time())/fadingTime;
			color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
			return new Couple<>(area, color);
		} else {
			return shape.getShape();
		}
	}

	@Override
	public boolean move(double time, boolean b) {
		shape.setPosition(getPosition().byAngle(shape.getAngle(), time*getSpeed()));
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
				dead = true;
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
		return bullet;
	}

	public boolean isDead() {
		return dead;
	}
}
