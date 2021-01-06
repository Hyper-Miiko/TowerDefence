package fr.tm_nlm.tower_defence.control;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.entity.Monster;
import fr.tm_nlm.tower_defence.control.entity.Tower;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathNode;

public class Field extends Thread {
	private boolean activ;
	private boolean running;
	private int width;
	private int height;
	private int lives;
	private int temmies;
	private HashSet<Entity> entities;
	private LinkedList<PathNode> pathNodes;
	private LinkedList<Tower> towers;
	private LinkedList<Monster> monsters;

	public Field(int width, int height) {
		super("Champ");
		this.width = width;
		this.height = height;
		lives = 10;
		temmies = 100;
		entities = new HashSet<>();
		pathNodes = new LinkedList<>();
		towers = new LinkedList<>();
		monsters = new LinkedList<>();
	}

	public void add(Entity entity) {
		if(!entities.add(entity)) {
			System.err.println(entity + " était déjà enregistré.");
			return;
		}
		if(entity instanceof PathNode) {
			pathNodes.add((PathNode) entity);
		} else if(entity instanceof Tower) {
			towers.add((Tower) entity);
		} else if(entity instanceof Monster) {
			monsters.add((Monster) entity);
		} else {
			throw new InternalError("L'entité " + entity + " n'est pas reconnue");
		}
	}
	
	public void buy(int price) {
		if(price > temmies) {
			throw new IllegalArgumentException("Pas assez de temmies, merci d'utiliser canBuy(price) à l'avenir.");
		}
		temmies -= price;
	}
	
	public boolean canBuy(int price) {
		return price <= temmies;
	}

	public void remove(Entity entity) {
		entities.remove(entity);
		if(entity instanceof PathNode) {
			pathNodes.remove(entity);
		} else if(entity instanceof Tower) {
			towers.remove(entity);
		} else if(entity instanceof Monster) {
			monsters.remove(entity);
		} else {
			throw new InternalError("L'entité " + entity + " n'est pas reconnue");
		}
	}
	
	public void removeLive(int nbrOfLive) {
		lives = (nbrOfLive >= lives) ? 0 : lives - nbrOfLive;
	}
	
	public HashMap<Tower, Boolean> getAllTower() {
		return Tower.getAddables(this);
	}
	
	public LinkedList<Monster> getMonsters() {
		return monsters;
	}
	
	public LinkedList<PathNode> getPathNodes() {
		return pathNodes;
	}

	@Override
	public void run() {
		while(activ) {
			running = true;
			LinkedList<Entity> remove = new LinkedList<>();
			for(Entity entity : entities) {
				if(entity.isDead()) {
					remove.push(entity);
				}
			}
			while(!remove.isEmpty()) {
				entities.remove(remove.pop());
			}
			for(Tower tower : towers) {
				tower.process();
			}
			for(Monster monster : monsters) {
				monster.process();
			}
		}
		running = false;
	}
	
	public void setActiv(boolean activ) {
		this.activ = activ;
		if(activ) {
			run();
		}
	}
	
	public boolean isRunning() {
		return running;
	}
	
	
}
