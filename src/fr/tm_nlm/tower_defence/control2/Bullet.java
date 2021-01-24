package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.util.HashSet;
import java.util.Random;

import fr.tm_nlm.tower_defence.Couple;

/**
 * Projectiles qui avance vers leur cible et les affecte ou non
 * @author Hyper Mïko
 *
 */
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
	private boolean lethal;
	private boolean track;
	private boolean visible;
	private int minAlpha, maxAlpha;
	private int minBlue, maxBlue;
	private int minGreen, maxGreen;
	private int minRed, maxRed;
	private double aimingFactor;
	private double baseSpeed;
	private double confuse;
	private double decayTimer;
	private double fadeAt;
	private double fadingTime;
	private double lastMove;
	private double minDamage, maxDamage;
	private double minFadingTime, maxFadingTime;
	private double minLifeTime, maxLifeTime;
	private double spinSpeed;
	private Attack onDeathAttack;
	private Couple<Double, Double> slow;
	private EntityType collideWith;
	private Game game;
	private Geometric shape;
	private String fadingImage;
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
		lethal = true;
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
		visible = true;
	}

	@Override
	public Vector getPosition() {
		return shape.getPosition();
	}

	@Override
	public boolean isOnScreen() {
		return havePosition() && visible;
	}

	/**
	 * Appel principal
	 */
	public void process() {
		double currentTime = Game.time();
		if(fadeAt == Double.POSITIVE_INFINITY) { //Si on n'a pas donné au projectile l'instruction de disparaitre
			calcAngle(currentTime - lastMove);
			move(currentTime - lastMove, !ghost);
			lastMove = currentTime;
			if(currentTime > decayTimer) { //Si le projectile a fait son temps et doit mourir (permet d'évite de perdre des projectil on ne ais ou et de supperbe effet aussi)
				if(attackOnNaturalDeath) {
					deathAttack();
				}
				die();
			}
		} else {
			if(onDeathAttack != null && onDeathAttack.shotLeft()) { //Si on a un effet de mort en cour
				onDeathAttack.setTargetPosition(tracked.getPosition());
				onDeathAttack.tryShot(getPosition(), currentTime);
			} else if(currentTime > fadeAt) { //Le projectile et mort depuis plus longtemps que son animation de mort et on a plus d'effet de mort
				dead = true; //On préviens qu'on peut le supprimer
			}
		}
		if (currentTime > fadeAt) { //Si on a un effet de mort encore en cour mais que le projectile et mort depuis plus longtemps que son animation de mort
			visible = false;
		}
	}

	/**
	 * Calcule un angle en fonction du temps
	 * La rotation en 1 secondes et de PI*aimingFactor
	 * @param time
	 */
	private void calcAngle(double time) {
		if (track) { //On peut soit tourner vers un ennemie ou un position immobile
			if (tracked instanceof Damageable && ((Damageable) tracked).isDead()) {
				HashSet<Localisable> targets = new HashSet<>();
				switch (collideWith) { //Dépend de qui à lancé le projectil pour prendre en compte les bonnes cible potentielles
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
			if (tracked != null) {
				aimingPosition = tracked.getPosition(); //Si on trouve quelqu'un alors on actuallise la position
			}
		}
		Angle currentAngle = getPosition().angle(aimingPosition);
		Angle angleDiff = Angle.diff(getAngle(false), currentAngle);
		Angle newAngle = Angle.diff(getAngle(false), new Angle(angleDiff.value() * aimingFactor * time)); //L'initialisation semble inutile mais dans le doute je vais la laisser
		if (angleDiff.value() > Math.PI) {
			newAngle = Angle.diff(getAngle(false), new Angle(-angleDiff.value() * aimingFactor * time));
		} else {
			newAngle = Angle.diff(getAngle(false), new Angle(angleDiff.value() * aimingFactor * time));
		}
		setAngle(newAngle);
	}

	/**
	 * Recherche une cible parmis les cibles potentielle
	 * @param targets cible potentielles
	 * @return cible choisi
	 */
	private Localisable seek(HashSet<Localisable> targets) {
		Localisable currentTarget = null;
		double minDist = Double.POSITIVE_INFINITY;
		for (Localisable elem : targets) {
			double dist = getPosition().dist(elem.getPosition());
			if (dist < minDist) {
				minDist = dist;
				currentTarget = elem;
			}
		}
		return currentTarget;
	}

	/**
	 * Ce déplace pour un temps donné
	 * @param time temps de parcour
	 * @param b inutile et ignoré
	 * @return
	 */
	@Override
	public boolean move(double time, boolean b) {
		shape.translateByAngle(time * getSpeed()); //Oui, le déplacement ce fait en une ligne, mais ça implique d'autre chose
		HashSet<Localisable> collideables = new HashSet<>(); //Liste des collision potentielles
		switch (collideWith) {
		case MONSTER:
			collideables.addAll(game.readMonsters());
			break;
		case TOWER:
			collideables.addAll(game.readTowers());
			break;
		default:
			break;
		}
		HashSet<Localisable> toucheds = new HashSet<>(); //Liste des entités touchées (surtout pour les grosse hitbox et les cible groupée)
		for (Localisable collideable : collideables) {
			if ((collideWalking && !collideable.isFlying() || collideFlying && collideable.isFlying())//Un projectil peut ne rentrer en collision avec personne ou tout le monde
					&& shape.collide(collideable)) {
				toucheds.add(collideable);
			}
		}
		if (!toucheds.isEmpty()) {
			if (!ghost) { //Même si il traverse les entités il peut leur infliger des dégâts (à chaque frame il faut donc des dégâts réduit) il n'y a pas de frame d'invulnérabilité
				if (attackOnCollide) {//Si l'effet de mort ce déclenche à la collision
					deathAttack();
				}
				die();
			}
			for (Localisable touched : toucheds) {
				if (touched instanceof Damageable) {
					double damage = valueBetween(minDamage, maxDamage);
					if (!heal) {
						((Damageable) touched).hurt(damage, lethal); //Inflige des dégat, les dégât non léthaux ne peuvent pas descendre la vie de la cible sous un certain niveau
					} else {
						((Damageable) touched).heal(damage); //On peut théoriquement soigner même si aucunne attaque ne le fait
					}
				}

				if (touched instanceof Movable) { //Si la cible ce déplace alors on peut la ralentir ou changer sa direction
					if (slow._1 != 0) {
						((Movable) touched).slow(slow);
					}
					if (confuse > 0) {
						((Movable) touched).confuse(confuse);
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
		bullet.confuse = confuse;
		bullet.game = game;
		bullet.lethal = lethal;
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
		bullet.spinSpeed = spinSpeed;
		bullet.tracked = tracked;
		bullet.track = track;

		return bullet;
	}

	@Override
	public void resetMove() {
		lastMove = Game.time();
		decayTimer = Game.time() + valueBetween(minLifeTime, maxLifeTime);
		Color color = new Color(valueBetween(minRed, maxRed),
								valueBetween(minGreen, maxGreen),
								valueBetween(minBlue, maxBlue),
								valueBetween(minAlpha, maxAlpha));
		shape.setColor(color);
	}

	private void die() {
		fadingTime = valueBetween(minFadingTime, maxFadingTime);
		fadeAt = Game.time() + fadingTime;
	}

	/**
	 * Effet à la mort
	 */
	private void deathAttack() {
		onDeathAttack.setMap(game.getMap()); //on place l'effet sur la carte
		if (attackKeepTarget) { //Les attaque peuvent ou non poursuivre la cible du projectile
			onDeathAttack.attack(tracked);
			onDeathAttack.shot(getPosition(), tracked.getPosition());
		} else { //Sinon il en cherche une nouvelle (pour les attaque à collision ça peut être souvent la même puisque c'est la plus proche)
			HashSet<Localisable> collideables = new HashSet<>();
			switch (collideWith) {
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
			if(target != null) {
				tracked = target;
			}
			onDeathAttack.attack(tracked); //On prépare l'attaque
		}
	}

	/**
	 * N'est appelé qu'une fois donc ne peut pas réellement changer l'image coté graphique, mais la fonctionnalité est là
	 */
	@Override
	public String getImage() {
		if (fadingImage != null && fadeAt != Double.POSITIVE_INFINITY) {
			Color color = shape.getShape()._2;
			int alpha = color.getAlpha();
			alpha *= (fadeAt - Game.time()) / fadingTime;
			alpha = (alpha < 0) ? 0 : alpha;
			alpha = (alpha > 255) ? 255 : alpha;
			color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
			return fadingImage;
		} else {
			return shape.getImage();
		}
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
		return (max > min) ? (max - min) * random.nextDouble() + min : min;
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
		if (fadeAt != Double.POSITIVE_INFINITY) {
			Area area = shape.getShape()._1;
			Color color = shape.getShape()._2;
			int alpha = color.getAlpha();
			alpha *= (fadeAt - Game.time()) / fadingTime;
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
	public void slow(Couple<Double, Double> slow) {
	}

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
		this.onDeathAttack = onDeathAttack;
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


	public Angle getAngle(boolean displayed) {
		if(displayed) {
			return this.getAngle();
		}
		else {
			return shape.getAngle();
		}
	}
	@Override
	public Angle getAngle() {
		double spinAngle = shape.getAngle().value() + 2*Math.PI*Game.time()*spinSpeed;
		return new Angle(spinAngle);
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

	public void setConfuse(double confuse) {
		this.confuse = confuse;
	}

	@Override
	public void confuse(double confuse) {
	}

	public void setOwner(Identifiable owner) {
		if (onDeathAttack != null) {
			onDeathAttack.setOwner(owner);
		}
	}

	public void setFadingImage(String image) {
		this.fadingImage = "data/img/" + image + ".png";
	}

	public void setLethal(boolean lethal) {
		this.lethal = lethal;
	}

	public void setSpinSpeed(double spinSpeed) {
		this.spinSpeed = spinSpeed;
	}
}
