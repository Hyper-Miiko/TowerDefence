package fr.tm_nlm.tower_defence.control2;

import java.util.HashSet;

/**
 * Class centrale qui centralise en sont centre sans trop qu'on s'entre tue les données du jeu
 * @author Hyper Mïko
 *
 */
public final class Game extends Thread {
	private static Game instance;
	
	/**
	 * Récupère toutes les entités qui peuvent être affichées à l'écran.
	 * @return 
	 * @throws InterruptedException pour éviter de toucher aux listes depuis deux endroits différents.
	 */
	public static HashSet<DisplayEntity> getAll() throws InterruptedException {
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
	public static HashSet<DisplayEntity> getBullets() throws InterruptedException {
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
	public static HashSet<DisplayEntity> getMonsters() throws InterruptedException {
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
	public static HashSet<DisplayEntity> getTowers() throws InterruptedException {
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
	
	private double startTime;
	private HashSet<Bullet> bullets;
	private boolean bulletsBussy;
	private HashSet<Monster> monsters;
	private boolean monstersBussy;
	private HashSet<Tower> towers;
	private boolean towersBussy;
	/**
	 * File d'attente pour être ajouté dans les collections
	 */
	private HashSet<Localisable> input;
	private boolean inputBussy;
	/**
	 * File d'attente pour être retiré des collections
	 */
	private HashSet<Localisable> trashput;
	private boolean trashputBussy;
	private Map map;
	
	{
		bullets = new HashSet<>();
		monsters = new HashSet<>();
		towers = new HashSet<>();
		input = new HashSet<>();
		trashput = new HashSet<>();
	}
	/**
	 * Est appelé lors de l'initialisation du programme sans avoir à le mettre dans le main.
	 * les booléen servent pour savoir si les collections sont en cour d'utilisations ou non.
	 */
	private Game(Map map) {
		bulletsBussy = false;
		monstersBussy = false;
		towersBussy = false;
		inputBussy = false;
		trashputBussy = false;
		startTime = (double) (System.nanoTime())/1000000000d;
		this.map = map;
		start();
	}
	
	@Override
	public void run() {
		while(true) {
			map.run();
			refreshAllList();
			try {
				for(Monster monster : readMonsters()) {
					monster.process();
				}
			} catch (InterruptedException e) {}
		}
	}
	
	/**
	 * Fait rentrer ceux qui toc à la porte et vire ceux qui roupille sur le paillasson
	 */
	private void refreshAllList() {
		try {
			refreshInputAllList();
			refreshTrashputAllList();
		} catch (InterruptedException e) {}
	}
	
	/**
	 * On ajoute des Localisable qui peuvent être (pour l'instant) de 3 type différent donc 3 collection différente
	 * On soulève une erreur si on envoie un mauvais élément au cas où
	 * @throws InterruptedException
	 */
	private void refreshInputAllList() throws InterruptedException {
		HashSet<Localisable> cloneInput = readInput();
		for(Localisable input : cloneInput) {
			boolean notIn = true;
			if(input instanceof Bullet) {
				while(bulletsBussy) sleep(1);
				bulletsBussy = true;
				notIn &= bullets.add((Bullet) input);
				bulletsBussy = false;
			} else if(input instanceof Monster) {
				while(monstersBussy) sleep(1);
				monstersBussy = true;
				notIn &= monsters.add((Monster) input);
				monstersBussy = false;
			} else if(input instanceof Tower) {
				while(towersBussy) sleep(1);
				towersBussy = true;
				notIn &= towers.add((Tower) input);
				towersBussy = false;
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
	private void refreshTrashputAllList() throws InterruptedException {
		HashSet<Localisable> cloneTrashput = readTrashput();
		for(Localisable trashput : cloneTrashput) {
			boolean in = true;
			if(trashput instanceof Bullet) {
				while(bulletsBussy) sleep(1);
				bulletsBussy = true;
				in &= bullets.remove((Bullet) trashput);
				bulletsBussy = false;
			} else if(trashput instanceof Monster) {
				while(monstersBussy) sleep(1);
				monstersBussy = true;
				in &= monsters.remove((Monster) trashput);
				monstersBussy = false;
			} else if(trashput instanceof Tower) {
				while(towersBussy) sleep(1);
				towersBussy = true;
				in &= towers.remove((Tower) trashput);
				towersBussy = false;
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
	private HashSet<Localisable> readInput() throws InterruptedException {
		while(inputBussy) sleep(1);
		inputBussy = true;
		HashSet<Localisable> cloneInput = (HashSet<Localisable>) input.clone();
		input = new HashSet<>();
		inputBussy = false;
		return cloneInput;
	}
	public void add(Localisable elem) {
		while(inputBussy) {
			try {
				sleep(1);
			} catch (InterruptedException e) {}
		}
		inputBussy = true;
		input.add(elem);
		inputBussy = false;
	}
	/**
	 * Copie trashInput et le reset
	 * @return
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	private HashSet<Localisable> readTrashput() throws InterruptedException {
		while(trashputBussy) sleep(1);
		trashputBussy = true;
		HashSet<Localisable> cloneTrashput = (HashSet<Localisable>) trashput.clone();
		trashput = new HashSet<>();
		trashputBussy = false;
		return cloneTrashput;
	}
	/**
	 * Ajouter un elem à la liste des élément à supprimer (ne le supprime pas de suite car souvent on veut le supprimer alors qu'on est encore dans la liste)
	 * @param elem
	 * @throws InterruptedException
	 */
	public void remove(Localisable elem){
		while(trashputBussy) {
			try {
				sleep(1);
			} catch (InterruptedException e) {}
		}
		trashputBussy = false;
		trashput.add(elem);
		trashputBussy = true;
	}
	
	private HashSet<Displayable> readAll() throws InterruptedException {
		HashSet<Displayable> all = new HashSet<>();
		all.addAll(readBullets());
		all.addAll(readMonsters());
		all.addAll(readTowers());
		return all;
	}
	
	@SuppressWarnings("unchecked")
	private HashSet<Bullet> readBullets() throws InterruptedException {
		while(bulletsBussy) sleep(1);
		bulletsBussy = true;
		HashSet<Bullet> cloneBullets = (HashSet<Bullet>) bullets.clone();
		bulletsBussy = false;
		return cloneBullets;
	}
	
	@SuppressWarnings("unchecked")
	private HashSet<Monster> readMonsters() throws InterruptedException {
		while(monstersBussy) sleep(1);
		monstersBussy = true;
		HashSet<Monster> cloneMonsters = (HashSet<Monster>) monsters.clone();
		monstersBussy = false;
		return cloneMonsters;
	}
	
	@SuppressWarnings("unchecked")
	HashSet<Tower> readTowers() {
		while(towersBussy) {
			try {
				sleep(1);
			} catch (InterruptedException e) {}
		}
		towersBussy = true;
		HashSet<Tower> cloneTowers = (HashSet<Tower>) towers.clone();
		towersBussy = false;
		return cloneTowers;
	}
}
