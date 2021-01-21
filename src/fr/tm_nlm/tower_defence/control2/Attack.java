package fr.tm_nlm.tower_defence.control2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class Attack {
	private static Random random = new Random();
	
	private boolean converge;
	private boolean keepTracking;
	private boolean randomSpread;
	private boolean startRandomSpread;
	private int minBulletsByShot, maxBulletsByShot;
	private int minNbrOfShot, maxNbrOfShot;
	private double minCooldown, maxCooldown;
	private double minInterval, maxInterval;
	private double nextAttackTimer;
	private double nextShotTimer;
	private double range;
	private double spreadRange;
	private double minStartShotRange, maxStartShotRange;
	private double startSpreadRange;
	private ArrayList<String> quotes;
	private Bullet bullet;
	private LinkedList<LinkedList<Bullet>> bulletsReady;
	private Localisable target;
	private Map map;
	private Vector targetPosition;
	private final String name;
	
	{
		bullet = new Bullet();
		converge = false;
		bulletsReady = new LinkedList<>();
		keepTracking = false;
		quotes = new ArrayList<>();
		minBulletsByShot = maxBulletsByShot = 1;
		minCooldown = maxCooldown = 2;
		minInterval = maxInterval = 0.5;
		minNbrOfShot = maxNbrOfShot = 1;
		minStartShotRange = maxStartShotRange = 0;
		randomSpread = false;
		range = 150;
		spreadRange = 0;
		startRandomSpread = false;
		startSpreadRange = 0;
	}
	public Attack(String name) {
		if(name == null) {
			throw new IllegalArgumentException("This is not the shinobyu mind!");
		}
		this.name = name;
	}
	
	public void process(Vector origin, HashSet<Localisable> targets) {
		double currentTime = Game.time();
		if(!bulletsReady.isEmpty()) {
			if(nextShotTimer < currentTime) {
				if(keepTracking) {
					targetPosition = target.getPosition();
				}
				shot(origin, targetPosition);
				nextShotTimer = currentTime + valueBetween(minInterval, maxInterval);
			}
		} else {
			if(nextAttackTimer < currentTime) {
				target = seek(origin, targets);
				if(target != null) {
					targetPosition = target.getPosition();
					attack(target);
					nextAttackTimer = currentTime + valueBetween(minCooldown, maxCooldown);
				}
			}
		}
	}
	
	private double valueBetween(double min, double max) {
		return (max > min) ? (max - min)*random.nextDouble() + min : min;
	}
	
	private int valueBetween(int min, int max) {
		return (max > min) ? random.nextInt(max - min + 1) + min : min;
	}
	
	private Localisable seek(Vector origin, HashSet<Localisable> targets) {
		Localisable currentTarget = null;
		double minDist = Double.POSITIVE_INFINITY;
		for(Localisable elem : targets)	{
			if(elem.getPosition().dist(origin) < range) {
				if(elem instanceof Monster) {
					double dist = ((Monster) elem).timeToEnd();
					if(dist < minDist) {
						minDist = dist;
						currentTarget = elem;
					}
				}
			}
		}
		return currentTarget;
	}
	
	private void shot(Vector origin, Vector targetPosition) {
		LinkedList<Bullet> shotBullet = bulletsReady.poll();
		int size = shotBullet.size();
		for(int i = 0; i < size; i++) {
			Vector start = origin;
			Angle angle = null;
			Angle angleModif = null;
			angle = start.angle(targetPosition);
			if(!converge) {
				if(randomSpread || size == 1) {
					angleModif = new Angle(valueBetween(-spreadRange, spreadRange));
				} else {
					angleModif = new Angle(-spreadRange + 2*spreadRange*i/(size-1));
				}
			}
			
			Angle startAngleModif;
			if(startRandomSpread || size == 1) {
				startAngleModif = new Angle(valueBetween(-startSpreadRange, startSpreadRange));
			} else {
				startAngleModif = new Angle(-startSpreadRange + 2*startSpreadRange*i/(size-1));
			}
			Angle finalStartAngle = new Angle(angle.value() + startAngleModif.value());
			start = origin.byAngle(finalStartAngle, valueBetween(minStartShotRange, maxStartShotRange));
			
			if(converge) {
				angle = start.angle(targetPosition);
				if(randomSpread || size == 1) {
					angleModif = new Angle(valueBetween(-spreadRange, spreadRange));
				} else {
					angleModif = new Angle(-spreadRange + 2*spreadRange*i/(size-1));
				}
			}
			
			Angle finalAngle = new Angle(angle.value() + angleModif.value());
			Bullet bullet = shotBullet.poll();
			bullet.setAngle(finalAngle);
			
			
			map.place(bullet, start);
		}
	}
	
	private void attack(Localisable target) {
		int nbrOfShots = valueBetween(minNbrOfShot, maxNbrOfShot);
		bulletsReady = new LinkedList<>();
		for(int i = 0; i < nbrOfShots; i++) {
			LinkedList<Bullet> shot = new LinkedList<>();
			int bulletsByShot = (int) valueBetween(minBulletsByShot, maxBulletsByShot);
			for(int j = 0; j < bulletsByShot; j++) {
				shot.add((Bullet) bullet.clone());
			}
			bulletsReady.add(shot);
		}
	}
	
	public void resetCooldown() {
		nextAttackTimer = Game.time() + valueBetween(minCooldown, maxCooldown);
		bulletsReady = new LinkedList<>();
	}

	public void setCooldown(double cooldown) {
		setCooldown(cooldown, cooldown);
	}
	public void setCooldown(double minCooldown, double maxCooldown) {
		this.minCooldown = minCooldown;
		this.maxCooldown = maxCooldown;
	}

	/**
	 * 
	 * @param spreadRange 0 < spreadRange < PI
	 */
	public void setSpreadRange(double spreadRange) {
		this.spreadRange = spreadRange;
	}

	public void setRandomSpread(boolean randomSpread) {
		this.randomSpread = randomSpread;
	}

	public void setBulletsByShot(int bulletByShot) {
		setBulletsByShot(bulletByShot, bulletByShot);
	}
	public void setBulletsByShot(int minBulletsByShot, int maxBulletsByShot) {
		this.minBulletsByShot = minBulletsByShot;
		this.maxBulletsByShot = maxBulletsByShot;
	}

	public void setNbrOfShot(int bulletNbrOfShot) {
		setNbrOfShot(bulletNbrOfShot, bulletNbrOfShot);
	}
	public void setNbrOfShot(int minNbrOfShot, int maxNbrOfShot) {
		this.minNbrOfShot = minNbrOfShot;
		this.maxNbrOfShot = maxNbrOfShot;
	}

	public void setInterval(double interval) {
		setInterval(interval, interval);
	}
	public void setInterval(double minInterval, double maxInterval) {
		this.minInterval = minInterval;
		this.maxInterval = maxInterval;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	
	public void setRange(double range) {
		this.range = range;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public void setKeepTracking(boolean keepTracking) {
		this.keepTracking = keepTracking;
	}
	
	public double getRange() {
		return range;
	}

	public String getName() {
		return name;
	}

	public void setStartShotRange(double startShotRange) {
		setStartShotRange(startShotRange, startShotRange);
	}
	public void setStartShotRange(double minStartShotRange, double maxStartShotRange) {
		this.minStartShotRange = minStartShotRange;
		this.maxStartShotRange = maxStartShotRange;
	}

	public void setConverge(boolean converge) {
		this.converge = converge;
	}

	public void setStartSpreadRange(double startSpreadRange) {
		this.startSpreadRange = startSpreadRange;
	}
}
