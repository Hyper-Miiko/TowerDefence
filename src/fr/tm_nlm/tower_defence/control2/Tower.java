package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;


/**
 * Les tour qui vont chercher et massacrer des ennemies
 * @author Hyper Mïko
 *
 */
public class Tower extends Identifiable implements Damageable, Displayable {
	private boolean dead;
	private int maxHandle;
	private double evolutionPrice;
	private double health, maxHealth;
	private double quoteTime;
	private double towerPrice;
	private ArrayList<Monster> handle;
	private Game game;
	private Geometric shape;
	private LinkedList<Attack> attacks;
	private Slot slot;
	private String quote;
	private final String name;
	private String sound;
	private Tower evolution;
	
	{
		attacks = new LinkedList<>();
		evolution = null;
		evolutionPrice = 0;
		handle = new ArrayList<>();
		health = maxHealth = 50;
		dead = false;
		maxHandle = 3;
		shape = PresetShape.circle(32);
		towerPrice = 10;
	}
	public Tower(String name) {
		if(name == null) {
			throw new IllegalArgumentException("You monster! :(");
		}
		this.name = name;
	}
	
	/**
	 * Appel principal
	 */
	public void process() {
		if(!dead) { //Le mort ne parlent pas
			if(havePosition()) {
				for(Attack attack : attacks) {
					HashSet<Localisable> monsters = new HashSet<>();
					monsters.addAll(game.readMonsters());
					attack.process(getPosition(), monsters);
				}
			}
		}
		if(quoteTime + 3 < Game.time()) { //Mais il peuvent se taire (^.^)
			quote = null;
		}
		ArrayList<Monster> newHandle = new ArrayList<>(); //Retire les monstre mort de la liste des monstre gérés
		for(Monster monster : handle) {
			if(!monster.isDead()) {
				newHandle.add(monster);
			}
		}
		handle = newHandle;
	}
	
	/**
	 * Permet de bloquer un monstre
	 * @param monster le monstre à bloquer
	 * @return si le monstre à été bloqué
	 */
	public boolean handle(Monster monster) {
		for(Monster monsterHandled : handle) { //Test si le monstre et déjà bloqué
			if(monsterHandled.equals(monster)) {
				return true;
			}
		}
		if(handle.size() < maxHandle) { //Test si il reste de la place
			handle.add(monster);
			return true;
		}
		return false;
	}
	
	public void resetCooldown() {
		for(Attack attack : attacks) {
			attack.resetCooldown();
		}
	}

	@Override
	public boolean havePosition() {
		return shape != null && shape.havePosition();
	}

	@Override
	public boolean isOnScreen() {
		return slot != null;
	}

	@Override
	public double getMaxHealth() {
		return maxHealth;
	}

	@Override
	public double getHealth() {
		return health;
	}

	@Override
	public void hurt(double damage) {
		//TODO
		health = (damage > health) ? 0 : health - damage;
	}
	@Override
	public void hurt(double damage, boolean lethal) {
		//TODO
		health = (damage > health) ? 0 : health - damage;
	}

	@Override
	public void heal(double sustain) {
		health = (health + sustain > maxHealth) ? maxHealth : health + sustain;
	}

	@Override
	public Vector getPosition() {
		return shape.getPosition();
	}

	@Override
	public Couple<Area, Color> getShape() {
		return shape.getShape();
	}

	@Override
	public String getImage() {
		return shape.getImage();
	}
	public void setMaxHandle(int maxHandle) {
		this.maxHandle = maxHandle;
	}
	public void addAttack(Attack attack) {
		this.attacks.add(attack);
		attack.setOwner(this);
	}

	public String getName() {
		return name;
	}
	
	public void setSlot(Slot slot) {
		if(this.slot != null) {
			this.slot.removeTower();
		}
		for(Attack attack : attacks) {
			attack.resetCooldown();
		}
		this.slot = slot;
		this.slot.setTower(this);
		shape.setPosition(slot.getPosition());
	}
	
	public void removeSlot() {
		slot.removeTower();
		slot = null;
	}
	
	@Override
	public String toString() {
		String str = name;
		if(havePosition()) {
			str += ": " + getPosition();
		}
		return str;
	}
	
	public void setGame(Game game) {
		this.game = game;
		for(Attack attack : attacks) {
			attack.setMap(game.getMap());
		}
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public double getRange() {
		double range = 0;
		
		for(Attack attack : attacks) {
			if(attack.getRange() > range) {
				range = attack.getRange();
			}
		}
		
		return range;
	}
	
	public void setImage(String image) {
		shape.setImage(image);
	}

	public LinkedList<Double> getCooldowns() {
		LinkedList<Double> cooldowns = new LinkedList<>();
		for(Attack attack : attacks) {
			cooldowns.add(attack.getCooldown());
		}
		return cooldowns;
	}
	
	public void setEvolution(Tower evolution, double price) {
		this.evolution = evolution;
		this.evolutionPrice = price;
	}
	
	public Tower getEvolution() {
		return evolution;
	}
	
	public double getPrice() {
		return towerPrice;
	}
	
	public double getEvolutionPrice() {
		return evolutionPrice;
	}

	@Override
	public Angle getAngle() {
		return null;
	}

	@Override
	public boolean isFlying() {
		return false;
	}
	
	public String getQuote() {
		return quote;
	}
	
	public void setQuote(String quote) {
		this.quote = quote;
		quoteTime = Game.time();
	}
	
	public void setSound(String sound) {
		this.sound = sound;
	}
	
	public String pollSound() {
		String dummy = sound;
		sound = null;
		return "data/music/" + dummy + ".wav";
	}

	public void setPrice(int price) {
		this.towerPrice = price;
	}
}
