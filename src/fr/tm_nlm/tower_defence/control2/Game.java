package fr.tm_nlm.tower_defence.control2;

import java.util.HashSet;
import java.util.Random;

/**
 * Class centrale qui centralise en sont centre sans trop qu'on s'entre tue les données du jeu
 * @author Hyper Mïko
 *
 */
public final class Game extends Thread {
	private static final Random random = new Random();
	private static Game instance;
	
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
	
	public static double time() {
		return ((double) System.nanoTime()/1000000000d) - instance.startTime;
	}
	public static void set(Map map) {
		instance = new Game(map);
		map.setGame(instance);
	}
	
	public static void placeTower(String towerName, Vector position) {
		Tower tower = ExistingTower.get(towerName);
		tower.setPosition(position);
		instance.add(tower);
	}
	
	private double startTime;
	private HashSet<Bullet> bullets;
	private WaitingBool bulletsBussy;
	private HashSet<Monster> monsters;
	private WaitingBool monstersBussy;
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
	
	{
		bullets = new HashSet<>();
		bulletsBussy = new WaitingBool("Bullets");
		monsters = new HashSet<>();
		monstersBussy = new WaitingBool("Monsters");
		towers = new HashSet<>();
		towersBussy = new WaitingBool("Towers");
		input = new HashSet<>();
		inputBussy = new WaitingBool("Input");
		trashput = new HashSet<>();
		trashputBussy = new WaitingBool("Trashput");
	}
	/**
	 * Est appelé lors de l'initialisation du programme sans avoir à le mettre dans le main.
	 * les booléen servent pour savoir si les collections sont en cour d'utilisations ou non.
	 */
	private Game(Map map) {
		startTime = (double) (System.nanoTime())/1000000000d;
		this.map = map;
		start();
	}
	
	@Override
	public void run() {
		while(true) {
			map.run();
			refreshAllList();
			for(Monster monster : readMonsters()) {
				monster.process();
				if(monster.isDead()) {
					remove(monster);
				}
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
			boolean notIn = true;
			if(input instanceof Bullet) {
				waitFor(bulletsBussy);
				bulletsBussy.set(true);
				notIn &= bullets.add((Bullet) input);
				bulletsBussy.set(false);
			} else if(input instanceof Monster) {
				waitFor(monstersBussy);
				monstersBussy.set(true);
				notIn &= monsters.add((Monster) input);
				monstersBussy.set(false);
			} else if(input instanceof Tower) {
				waitFor(towersBussy);
				towersBussy.set(true);
				notIn &= towers.add((Tower) input);
				towersBussy.set(false);
			} else {
				throw new IllegalStateException(input + " is not recognized.");
			}
			if(!notIn) {
				System.err.println("L'élément " + input + "a essayé d'être ajouté alors qu'il était déjà enregistré.");
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
		all.addAll(readTowers());
		return all;
	}
	
	@SuppressWarnings("unchecked")
	private HashSet<Bullet> readBullets() {
		waitFor(bulletsBussy);
		bulletsBussy.set(true);
		HashSet<Bullet> cloneBullets = (HashSet<Bullet>) bullets.clone();
		bulletsBussy.set(false);
		return cloneBullets;
	}
	
	@SuppressWarnings("unchecked")
	private HashSet<Monster> readMonsters() {
		waitFor(monstersBussy);
		monstersBussy.set(true);
		HashSet<Monster> cloneMonsters = (HashSet<Monster>) monsters.clone();
		monstersBussy.set(false);
		return cloneMonsters;
	}
	
	@SuppressWarnings("unchecked")
	HashSet<Tower> readTowers() {
		waitFor(towersBussy);
		towersBussy.set(true);
		HashSet<Tower> cloneTowers = (HashSet<Tower>) towers.clone();
		towersBussy.set(false);
		return cloneTowers;
	}
	
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
}
