package fr.tm_nlm.tower_defence.control2;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Class centrale qui centralise en sont centre sans trop qu'on s'entre tue sur les données du jeu
 * @author Hyper Mïko
 *
 */
public final class Game extends Thread {
	/**
	 * nombre de boucle par seconde de ce thread et non pas du graphique
	 */
	private static final int MAX_FPS = 60;
	@SuppressWarnings("unused")
	private static double fpsTime;
	private static final Random random = new Random();
	/**
	 * instance en singleton pattern de la classe
	 */
	private static Game instance;
	/**
	 * Argent du jeu commun à toute les cartes
	 */
	private static double temmies = 100;
	/**
	 * God-mode
	 */
	private static boolean determination = false;
	
	/**
	 * Récupère toutes les entités qui peuvent être affichées à l'écran.
	 * @return 
	 * @throws InterruptedException pour éviter de toucher aux listes depuis deux endroits différents.
	 */
	public static HashSet<DisplayEntity> getAll() {
		HashSet<Displayable> origin = instance.readAll();
		HashSet<DisplayEntity> clone = new HashSet<>();
		for(Displayable elem : origin) {
			clone.add(new DisplayEntity(elem));
		}
		return clone;
	}
	/**
	 * Récupère tous les projectiles qui peuvent être affichés à l'écran.
	 * @return 
	 * @throws InterruptedException pour éviter de toucher aux listes depuis deux endroits différents.
	 */
	public static HashSet<DisplayEntity> getBullets() {
		HashSet<Bullet> origin = instance.readBullets();
		HashSet<DisplayEntity> clone = new HashSet<>();
		for(Displayable elem : origin) {
			clone.add(new DisplayEntity(elem));
		}
		return clone;
	}
	/**
	 * Récupère tous les monstres qui peuvent être affichés à l'écran.
	 * @return 
	 * @throws InterruptedException pour éviter de toucher aux listes depuis deux endroits différents.
	 */
	public static HashSet<DisplayEntity> getMonsters() {
		HashSet<Monster> origin = instance.readMonsters();
		HashSet<DisplayEntity> clone = new HashSet<>();
		for(Displayable elem : origin) {
			clone.add(new DisplayEntity(elem));
		}
		return clone;
	}
	/**
	 * Récupère tous les emplacement de tour qui peuvent être affichés à l'écran.
	 * @return 
	 * @throws InterruptedException pour éviter de toucher aux listes depuis deux endroits différents.
	 */
	public static HashSet<DisplayEntity> getSlots() {
		HashSet<Slot> origin = instance.readSlots();
		HashSet<DisplayEntity> clone = new HashSet<>();
		for(Displayable elem : origin) {
			clone.add(new DisplayEntity(elem));
		}
		return clone;
	}
	/**
	 * Récupère toutes les tours qui peuvent être affichées à l'écran.
	 * @return 
	 * @throws InterruptedException pour éviter de toucher aux listes depuis deux endroits différents.
	 */
	public static HashSet<DisplayEntity> getTowers() {
		HashSet<Tower> origin = instance.readTowers();
		HashSet<DisplayEntity> clone = new HashSet<>();
		for(Displayable elem : origin) {
			clone.add(new DisplayEntity(elem));
		}
		return clone;
	}
	
	/**
	 * Récupère les phrases que les tours (ou tout autre identifiable) disent à ce moment
	 * @return tous les id avec leur phrase s'il en ont
	 */
	public static HashMap<Long, String> getQuotes() {
		HashMap<Long, String> quotes = new HashMap<>();;
		for(Tower tower : instance.readTowers()) {
			if(tower.getQuote() != null) {
				quotes.put(tower.getId(), tower.getQuote());
			}
		}
		return quotes;
	}
	
	public static HashSet<String> getSound() {
		HashSet<String> sounds = new HashSet<>();;
		for(Tower tower : instance.readTowers()) {
			sounds.add(tower.pollSound());
		}
		sounds.add(instance.map.pollSound());
		sounds.add(instance.sound);
		instance.sound = null;
		sounds.remove("data/music/null.wav");
		sounds.remove(null);
		return sounds;
	}
	
	public static String getWaveName() {
		return instance.map.getWaveName();
	}
	
	/**
	 * Temps de jeu en seconde de la partie
	 * @return 
	 */
	public static double time() {
		return ((double) System.nanoTime()/1000000000d) - instance.startTime;
	}
	public static void set(Map map) {
		instance = new Game(map);
		map.setGame(instance);
		map.setUndying(determination);
		if(map.getWaveName().equals("Test")) {
			temmies = Double.POSITIVE_INFINITY;
			determination ^= true; //Passer sur la map test active/désactive le godmode
			map.setUndying(determination);
		}
	}
	
	public static String getBackground() {
		return instance.map.getBackground();
	}
	
	public static double getTemmies() {
		return temmies;
	}
	
	public static double getLives() {
		return instance.map.getLives();
	}
	
	public static String getWave() {
		return instance.map.getWaveName();
	}
	
	public static double evolvePrice(long towerId) {
		return ExistingTower.get(towerId).getEvolutionPrice();
	}
	
	/**
	 * Evolue la tour de cet id et renvoie l'id de l'évolution
	 * @param towerId id de la tour à évoluer
	 * @return id de la tour évoluée
	 */
	public static Long evolveTower(long towerId) {
		HashSet<Tower> towers = instance.readTowers();
		for(Tower tower : towers) {
			if(tower.getId() == towerId) {
				if(tower.getEvolution() == null) {
					System.err.println("No evolution available.");
					return null;
				}
				if(instance.buy(tower.getEvolutionPrice())) { //Si on peut payer le prix de la tour
					instance.remove(tower);
					instance.add(tower.getEvolution());
					tower.getEvolution().setGame(instance);
					tower.removeSlot(); //on retire la tour de sa position sur la carte
					ExistingTower.add(tower.getEvolution());
					ExistingTower.remove(tower.getId());
					Game.placeTower(tower.getEvolution().getId(), tower.getPosition()); //On place l'évolution aux position de l'ancienne tour
					return tower.getEvolution().getId();
				} else {
					System.err.println("Not enough temmies.");
				}
			}
		}
		return null;
	}
	
	public static boolean isOver() {
		return instance.over;
	}
	
	public static boolean canEvolve(long towerId) {
		Tower tower = ExistingTower.get(towerId);
		return (tower.getEvolution() != null && tower.getEvolutionPrice() <= getTemmies());
	}
	
	/**
	 * Place une tour au position donné et renvoie un accusé de tache
	 * @param towerId
	 * @param position
	 * @return
	 */
	public static boolean placeTower(long towerId, Vector position) {
		Tower tower = ExistingTower.get(towerId);
		HashSet<Slot> slots = instance.readSlots();
		Slot slot = null;
		Area area = new Area(new Ellipse2D.Double(position.x, position.y, 1, 1)); //On parcour les slot pour savoir si on a cliqué dessus
		for(Slot elem : slots) {
			if(elem.collide(area)) {
				slot = elem;
				break;
			}
		}
		if(slot == null) {
			System.err.println("No slot here: " + position + ".");
		} else if(!slot.canPlaceTower()) {
			System.err.println(slot.getTower().getName() + " is already here: " + position + ".");
		} else if(!instance.buy(tower.getPrice())) {
			System.err.println("Not enough temmies.");
		} else {
			tower.setSlot(slot); //on donne à la tour le slot
			tower.setGame(instance);
			tower.resetCooldown();
			instance.add(tower);
			return true;
		}
		return false;
	}
	
	private double startTime;
	private HashSet<Bullet> bullets;
	private WaitingBool bulletsBussy;
	private HashSet<Monster> monsters;
	private WaitingBool monstersBussy;
	private HashSet<Slot> slots;
	private WaitingBool slotsBussy;
	private HashSet<Tower> towers;
	private WaitingBool towersBussy;
	/**
	 * File d'attente pour être ajouté dans les collections
	 */
	private HashSet<Localisable> input;
	private WaitingBool inputBussy;
	/**
	 * File d'attente pour être retiré des collections
	 */
	private HashSet<Localisable> trashput;
	private WaitingBool trashputBussy;
	private Map map;
	private boolean over;
	private String sound;
	
	{
		bullets = new HashSet<>();
		bulletsBussy = new WaitingBool("Bullets");
		monsters = new HashSet<>();
		monstersBussy = new WaitingBool("Monsters");
		slots = new HashSet<>();
		slotsBussy = new WaitingBool("Slots");
		towers = new HashSet<>();
		towersBussy = new WaitingBool("Towers");
		input = new HashSet<>();
		inputBussy = new WaitingBool("Input");
		trashput = new HashSet<>();
		trashputBussy = new WaitingBool("Trashput");
		over = false;
		sound = null;
	}
	/**
	 * Est appelé lors de l'initialisation du programme sans avoir à le mettre dans le main.
	 * les booléen servent pour savoir si les collections sont en cour d'utilisations ou non.
	 */
	private Game(Map map) {
		startTime = System.nanoTime()/1000000000d;
		this.map = map;
		this.slots = map.getSlots();
		fpsTime = startTime;
		start();
	}
	
	private boolean buy(double cost) {
		if(temmies >= cost) {
			temmies -= cost;
			return true;
		}
		return false;
	}
	
	public void increaseTemmies(double temmies) {
		Game.temmies += temmies;
	}
	
	/**
	 * boucle principal
	 */
	@Override
	public void run() {
		while(!over) {
			try {
				Thread.sleep((long) (1/MAX_FPS*1000)); 
			} catch (InterruptedException e) {}
			fpsTime = Game.time();
			map.run();
			refreshAllList();
			for(Monster monster : readMonsters()) {
				monster.process();
				if(monster.isDead()) {
					remove(monster);
				}
			}
			for(Tower tower : readTowers()) {
				tower.process();
				if(tower.isDead()) {
					remove(tower);
				}
			}
			for(Bullet bullet : readBullets()) {
				bullet.process();
				if(bullet.isDead()) {
					remove(bullet);
				}
			}
			if(map.isOver() && readMonsters().isEmpty()) { //Fin de partie gagné
				over = true;
				temmies = (temmies < 75) ? 75 : temmies;
			} else if(map.getLives() == 0) {               //fin de partie perdue
				sound = "data/music/game_over.wav";
				over = true;
				temmies /= 2;
				temmies = (temmies < 50) ? 50 : temmies;
			}
		}
	}
	
	/**
	 * Fait rentrer ceux qui toc à la porte et vire ceux qui roupille sur le paillasson
	 */
	private void refreshAllList() {
		refreshInputAllList();
		refreshTrashputAllList();
	}
	
	/**
	 * On ajoute des Localisable qui peuvent être (pour l'instant) de 3 type différent donc 3 collection différente
	 * On soulève une erreur si on envoie un mauvais élément au cas où
	 * @throws InterruptedException
	 */
	private void refreshInputAllList() {
		HashSet<Localisable> cloneInput = readInput();
		for(Localisable input : cloneInput) {
			if(input instanceof Bullet) {
				waitFor(bulletsBussy);
				bulletsBussy.set(true);
				bullets.add((Bullet) input);
				bulletsBussy.set(false);
			} else if(input instanceof Monster) {
				waitFor(monstersBussy);
				monstersBussy.set(true);
				monsters.add((Monster) input);
				monstersBussy.set(false);
			} else if(input instanceof Tower) {
				waitFor(towersBussy);
				towersBussy.set(true);
				towers.add((Tower) input);
				towersBussy.set(false);
			} else {
				throw new IllegalStateException(input + " is not recognized.");
			}
		}
	}
	
	/**
	 * Pareil que refreshInputAllList(), il est à noter que je clone pour éviter de supprimer un élément d'une collection que je parcours
	 * @throws InterruptedException
	 */
	private void refreshTrashputAllList() {
		HashSet<Localisable> cloneTrashput = readTrashput();
		for(Localisable trashput : cloneTrashput) {
			boolean in = true;
			if(trashput instanceof Bullet) {
				waitFor(bulletsBussy);
				bulletsBussy.set(true);
				in &= bullets.remove((Bullet) trashput);
				bulletsBussy.set(false);
			} else if(trashput instanceof Monster) {
				waitFor(monstersBussy);
				monstersBussy.set(true);
				in &= monsters.remove((Monster) trashput);
				monstersBussy.set(false);
			} else if(trashput instanceof Tower) {
				waitFor(towersBussy);
				towersBussy.set(true);
				in &= towers.remove((Tower) trashput);
				towersBussy.set(false);
			} else {
				throw new IllegalStateException(trashput + " is not recognized.");
			}
			if(!in) {
				System.err.println("L'élément " + trashput + "a essayé d'être retiré alors qu'il n'était pas enregistré.");
			}
		}
	}
	
	/**
	 * Copie input et le reset
	 * @return
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	private HashSet<Localisable> readInput() {
		waitFor(inputBussy);
		inputBussy.set(true);
		HashSet<Localisable> cloneInput = (HashSet<Localisable>) input.clone();
		input = new HashSet<>();
		inputBussy.set(false);
		return cloneInput;
	}
	public void add(Localisable elem) {
		waitFor(inputBussy);
		inputBussy.set(true);
		input.add(elem);
		inputBussy.set(false);
	}
	/**
	 * Copie trashInput et le reset
	 * @return
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	private HashSet<Localisable> readTrashput() {
		waitFor(trashputBussy);
		trashputBussy.set(true);
		HashSet<Localisable> cloneTrashput = (HashSet<Localisable>) trashput.clone();
		trashput = new HashSet<>();
		trashputBussy.set(false);
		return cloneTrashput;
	}
	/**
	 * Ajouter un elem à la liste des élément à supprimer (ne le supprime pas de suite car souvent on veut le supprimer alors qu'on est encore dans la liste)
	 * @param elem
	 * @throws InterruptedException
	 */
	public void remove(Localisable elem){
		waitFor(trashputBussy);
		trashputBussy.set(true);
		trashput.add(elem);
		trashputBussy.set(false);
	}
	
	private HashSet<Displayable> readAll() {
		HashSet<Displayable> all = new HashSet<>();
		all.addAll(readBullets());
		all.addAll(readMonsters());
		all.addAll(readSlots());
		all.addAll(readTowers());
		return all;
	}
	
	@SuppressWarnings("unchecked")
	private HashSet<Bullet> readBullets() {
		HashSet<Bullet> cloneBullets;
		try {
			waitFor(bulletsBussy);
			bulletsBussy.set(true);
			cloneBullets = (HashSet<Bullet>) bullets.clone();
			bulletsBussy.set(false);
		} catch(ConcurrentModificationException e) {
			cloneBullets = readBullets();
		}
		return cloneBullets;
	}
	
	@SuppressWarnings("unchecked")
	HashSet<Monster> readMonsters() {
		waitFor(monstersBussy);
		monstersBussy.set(true);
		HashSet<Monster> cloneMonsters = (HashSet<Monster>) monsters.clone();
		monstersBussy.set(false);
		return cloneMonsters;
	}

	@SuppressWarnings("unchecked")
	private HashSet<Slot> readSlots() {
		waitFor(slotsBussy);
		slotsBussy.set(true);
		HashSet<Slot> cloneSlots = (HashSet<Slot>) slots.clone();
		slotsBussy.set(false);
		return cloneSlots;
	}
	
	@SuppressWarnings("unchecked")
	HashSet<Tower> readTowers() {
		waitFor(towersBussy);
		towersBussy.set(true);
		HashSet<Tower> cloneTowers = (HashSet<Tower>) towers.clone();
		towersBussy.set(false);
		return cloneTowers;
	}
	
	/**
	 * 
	 * @param who
	 */
	private void waitFor(WaitingBool who) {
		int wait = 1;
		while(who.get()) {
			try {
				sleep(random.nextInt(wait));
			} catch(InterruptedException e) {}
			if(wait > 1000) {
				System.err.println(who.name + " is waiting for a while.");
			}
			wait *= 2;
		}
	}
	
	Map getMap() {
		return map;
	}
}
