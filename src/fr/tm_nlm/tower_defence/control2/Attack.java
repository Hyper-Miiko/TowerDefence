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
	private boolean targetFlying;
	private boolean targetWalking;
	private int minBulletsByShot, maxBulletsByShot;
	private int minNbrOfShot, maxNbrOfShot;
	private double cooldown;
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
	private Identifiable owner;
	private LinkedList<LinkedList<Bullet>> bulletsReady;
	private Localisable target;
	private Map map;
	private final String name;
	private String sound;
	private Vector targetPosition;
	
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
		targetFlying = false;
		targetWalking = true;
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
					cooldown = valueBetween(minCooldown, maxCooldown);
					nextAttackTimer = currentTime + cooldown;
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
	
	public Localisable seek(Vector origin, HashSet<Localisable> targets) {
		Localisable currentTarget = null;
		double minDist = Double.POSITIVE_INFINITY;
		for(Localisable elem : targets)	{
			if((targetWalking && !elem.isFlying() || targetFlying && elem.isFlying())
					&& elem.getPosition().dist(origin) < range) {
				if(elem instanceof Monster) {
					double dist = ((Monster) elem).timeToEnd();
					if(dist < minDist || dist == Double.POSITIVE_INFINITY) {
						minDist = dist;
						currentTarget = elem;
					}
				}
			}
		}
		return currentTarget;
	}
	
	public void shot(Vector origin, Vector targetPosition) {
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
			
			owner.setSound(sound);
			quote();
			map.place(bullet, start);
		}
	}
	
	private void quote() {
		owner.setQuote(quotes.get(valueBetween(0, quotes.size())));
	}
	
	public void attack(Localisable target) {
		int nbrOfShots = valueBetween(minNbrOfShot, maxNbrOfShot);
		bulletsReady = new LinkedList<>();
		for(int i = 0; i < nbrOfShots; i++) {
			LinkedList<Bullet> shot = new LinkedList<>();
			int bulletsByShot = (int) valueBetween(minBulletsByShot, maxBulletsByShot);
			for(int j = 0; j < bulletsByShot; j++) {
				Bullet bullet = (Bullet) this.bullet.clone();
				bullet.setTracked(target);
				shot.add((Bullet) bullet);
			}
			bulletsReady.add(shot);
		}
	}
	
	public void resetCooldown() {
		cooldown = valueBetween(minCooldown, maxCooldown);
		nextAttackTimer = Game.time() + cooldown;
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
	
	public double getCooldown() {
		return 1 - (nextAttackTimer - Game.time())/cooldown;
	}
	
	public void addQuote(String quote) {
		quotes.add(quote);
	}
	
	public String peekQuote() {
		return quotes.get(valueBetween(0, quotes.size()));
	}
	
	public void setTargetFlying(boolean targetFlying) {
		this.targetFlying = targetFlying;
	}
	
	public void setTargetWalking(boolean targetWalking) {
		this.targetWalking = targetWalking;
	}
	
	public void setOwner(Identifiable owner) {
		this.owner = owner;
		bullet.setOwner(owner);
	}
	
	public void setSound(String sound) {
		this.sound = sound;
	}
}
