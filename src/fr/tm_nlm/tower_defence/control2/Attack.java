package fr.tm_nlm.tower_defence.control2;

import java.util.HashSet;
import java.util.Random;

public class Attack {
	private static Random random = new Random();
	private boolean keepTracking;
	private int nbrOfShotLeft, nbrOfShotByAttack;
	private double minCooldown, maxCooldown;
	private double minInterval, maxInterval;
	private double nextAttackTimer;
	private double nextShotTimer;
	private Localisable target;
	private Vector targetPosition;
	private final String name;
	
	public Attack(String name) {
		this.name = name;
	}
	
	public void process(Vector origin, HashSet<Localisable> targets) {
		double currentTime = Game.time();
		if(nbrOfShotLeft > 0) {
			if(nextShotTimer < currentTime) {
				if(keepTracking) {
					targetPosition = target.getPosition();
				}
				shot(targetPosition);
				nextShotTimer = currentTime + valueBetween(minInterval, maxInterval);
			}
		} else {
			if(nextAttackTimer < currentTime) {
				target = seek(origin, targets);
				if(target != null) {
					attack(target);
					nextAttackTimer = currentTime + valueBetween(minCooldown, maxCooldown);
				}
			}
		}
	}
	
	private double valueBetween(double min, double max) {
		return (max > min) ? (max - min)*random.nextDouble() + min : min;
	}
	
	private Localisable seek(Vector origin, HashSet<Localisable> targets) {
		Localisable currentTarget = null;
		double minDist = Double.POSITIVE_INFINITY;
		for(Localisable elem : targets)	{
			if(elem instanceof Monster) {
				double dist = ((Monster) elem).timeToEnd();
				if(dist < minDist) {
					minDist = dist;
					currentTarget = elem;
				}
			}
		}
		return currentTarget;
	}
	
	private void shot(Vector targetPosition) {
	}
	
	private void attack(Localisable target) {
		nbrOfShotLeft = nbrOfShotByAttack;
		
	}
	
	public void resetCooldown() {
		nextAttackTimer = Game.time() + valueBetween(minCooldown, maxCooldown);
		nbrOfShotLeft = 0;
	}

	public void setCooldown(double cooldown) {
		setCooldown(cooldown, cooldown);
	}
	public void setCooldown(double minCooldown, double maxCooldown) {
		this.minCooldown = minCooldown;
		this.maxCooldown = maxCooldown;
	}

	public void setSpreadRange(double d) {
		// TODO Auto-generated method stub
		
	}

	public void setRandomSpread(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void setBulletByShot(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	public void setNbrOfShot(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	public void setInterval(double d, int i) {
		// TODO Auto-generated method stub
		
	}

	public void setBullet(Bullet magicBullet) {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		return name;
	}
}
