package fr.tm_nlm.tower_defence.control2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

/**
 * Les attack que peuvent lancer les tours (les monstres théoriquement mais ça n'a pas été encore implémenté) et même les projectiles
 * @author Hyper Mïko
 *
 */
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
	private double rotation;
	private double rotationBetweenShot;
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
		rotation = 0;
		rotationBetweenShot = 0;
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
	
	/**
	 * Appel pricipal
	 * @param origin d'où par l'attaque
	 * @param targets les cibles potentielles
	 */
	public void process(Vector origin, HashSet<Localisable> targets) {
		double currentTime = Game.time();
		if(!bulletsReady.isEmpty()) {
			tryShot(origin, currentTime);
		} else {
			if(nextAttackTimer < currentTime) { //Delai entre deux attaques
				target = seek(origin, targets);
				if(target != null) {
					targetPosition = target.getPosition();
					attack(target);
					cooldown = valueBetween(minCooldown, maxCooldown); //Génère un nouveau cooldown
					nextAttackTimer = currentTime + cooldown;
				}
			}
		}
	}
	public void tryShot(Vector origin, double currentTime) {
		if(nextShotTimer < currentTime) {
			if(keepTracking) {
				targetPosition = target.getPosition();
			}
			shot(origin, targetPosition);
			nextShotTimer = currentTime + valueBetween(minInterval, maxInterval);
		}
	}
	
	private double valueBetween(double min, double max) {
		return (max > min) ? (max - min)*random.nextDouble() + min : min;
	}
	
	private int valueBetween(int min, int max) {
		return (max > min) ? random.nextInt(max - min + 1) + min : min;
	}
	
	/**
	 * Cherche une cible parmis une liste de cibles potentielles
	 * privilégie la cible qui arrivera le plus vite, si c'est un monstre
	 * @param origin point d'origine de la recherche
	 * @param targets les cibles potentienls
	 * @return la cible choisi
	 */
	public Localisable seek(Vector origin, HashSet<Localisable> targets) {
		Localisable currentTarget = null;
		double minDist = Double.POSITIVE_INFINITY;
		for(Localisable elem : targets)	{
			if((targetWalking && !elem.isFlying() || targetFlying && elem.isFlying()) //ajoute les monstre marchant si l'attaque cible les monstre marchant, ajouter les monstre volant si l'attaque cible les monstre volant (elle peut en cibler aucun)
					&& elem.getPosition().dist(origin) < range) { //Et la cible doit être à porté
				if(elem instanceof Monster) {
					double dist = ((Monster) elem).timeToEnd();
					if(dist < minDist || dist == Double.POSITIVE_INFINITY) { //Si on a mieux ou si on a que des immobile
						minDist = dist;
						currentTarget = elem;
					}
				}
			}
		}
		return currentTarget;
	}
	
	/**
	 * lance une partie d'une attaque, à partir d'un point vers un autre
	 * @param origin
	 * @param targetPosition
	 */
	public void shot(Vector origin, Vector targetPosition) {
		rotation += rotationBetweenShot;
		LinkedList<Bullet> shotBullet = bulletsReady.poll(); //Une des attaque restant;
		int size = shotBullet.size(); 
		for(int i = 0; i < size; i++) {
			Vector start = origin;
			Angle angle = null;
			Angle angleModif = null;
			angle = start.angle(targetPosition);
			if(!converge) { //Le tir chosi son angle avant d'étre déplacé donc il ne visera peut-^tre pas la cible après coup
				if(randomSpread || size == 1) {
					angleModif = new Angle(valueBetween(-spreadRange, spreadRange) + rotation);
				} else {
					angleModif = new Angle(-spreadRange + 2*spreadRange*i/(size-1) + rotation);
				}
			}
			
			//Déplacement du point de départ de l'attaque
			Angle startAngleModif;
			if(startRandomSpread || size == 1) {
				startAngleModif = new Angle(valueBetween(-startSpreadRange, startSpreadRange));
			} else {
				startAngleModif = new Angle(-startSpreadRange + 2*startSpreadRange*i/(size-1));
			}
			Angle finalStartAngle = new Angle(angle.value() + startAngleModif.value());
			start = origin.byAngle(finalStartAngle, valueBetween(minStartShotRange, maxStartShotRange));
			
			if(converge) { //Le tir choisi sont angle après sont déplacement donc il pourra suivre une autre trajectoire que directement depuis l'attaquant à la cible
				angle = start.angle(targetPosition);
				if(randomSpread || size == 1) {
					angleModif = new Angle(valueBetween(-spreadRange, spreadRange) + rotation);
				} else {
					angleModif = new Angle(-spreadRange + 2*spreadRange*i/(size-1) + rotation);
				}
			}
			
			Angle finalAngle = new Angle(angle.value() + angleModif.value());
			Bullet bullet = shotBullet.poll();
			bullet.setAngle(finalAngle);
			
			quote();
			map.place(bullet, start);
		}
	}
	
	/**
	 * Donne au graphique de quoi afficher une bulle au dessus de l'attaquant pour écrire un texte
	 */
	private void quote() {
		if(quotes.size() > 0) {
			owner.setQuote(quotes.get(valueBetween(0, quotes.size() - 1)));
		}
	}
	
	/**
	 * Prépare une attaque;
	 * @param target cible de l'attaque
	 */
	public void attack(Localisable target) {
		int nbrOfShots = valueBetween(minNbrOfShot, maxNbrOfShot);
		bulletsReady = new LinkedList<>(); //Vide les projectiles restant (théoriquement il n'y en a plus)
		for(int i = 0; i < nbrOfShots; i++) {
			LinkedList<Bullet> shot = new LinkedList<>();
			int bulletsByShot = (int) valueBetween(minBulletsByShot, maxBulletsByShot); //Nombre de projectile à génèré pour ce tir
			for(int j = 0; j < bulletsByShot; j++) {
				Bullet bullet = (Bullet) this.bullet.clone(); //On clone le projectile qui sert d'exemple pour cette attaque
				bullet.setTracked(target);
				shot.add((Bullet) bullet);
			}
			bulletsReady.add(shot);
		}
		owner.setSound(sound); //Certaine attaque peuvent jouer un son
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
	
	public void setRotationBetweenShot(double rotation) {
		this.rotationBetweenShot = rotation;
	}

	public boolean shotLeft() {
		return !bulletsReady.isEmpty();
	}

	public void setTargetPosition(Vector position) {
		this.targetPosition = position;
	}
}
