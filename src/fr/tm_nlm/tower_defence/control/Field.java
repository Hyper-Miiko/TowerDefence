package fr.tm_nlm.tower_defence.control;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.entity.Monster;
import fr.tm_nlm.tower_defence.control.entity.Tower;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathNode;
import fr.tm_nlm.tower_defence.view.mHUDCompat.FieldToGraphic;

public class Field extends Thread {
	private boolean activ;
	private boolean running;
	private boolean someNews;
	private int width;
	private int height;
	private int lives;
	private int temmies;
	private final HashSet<Entity> entities;
	private final LinkedList<PathNode> pathNodes;
	private final LinkedList<Tower> towers;
	private final LinkedList<Monster> monsters;
	private final LinkedList<Couple<Action, Object>> job;

	public Field(int width, int height) {
		super("Champ");
		activ = true;
		this.width = width;
		this.height = height;
		lives = 10;
		temmies = 100;
		entities = new HashSet<>();
		pathNodes = new LinkedList<>();
		towers = new LinkedList<>();
		monsters = new LinkedList<>();
		job = new LinkedList<>();
		someNews = true;
	}

	public void add(Entity entity) {
		int before = entities.size();
		if(!entities.add(entity)) {
			
			System.err.println(entity + " était déjà enregistré. (" + before + "/" + entities.size() + ")");
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
		someNews = true;
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
		someNews = true;
	}
	
	public void removeLive(int nbrOfLive) {
		lives = (nbrOfLive >= lives) ? 0 : lives - nbrOfLive;
	}
	
	public void workOn(Couple<Action, Object> action) {
		job.add(action);
	}

	@Override
	public void run() {
		while(activ) {
			running = true;
			processEntities();
			actionFromUser();
		}
		running = false;
	}
	
	private void processEntities() {
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
			System.out.println(monster);
			monster.process();
		}
	}
	
	private void actionFromUser() {
		Couple<Action, Object> action = job.pollFirst();
		if(action != null) {
			if(action._1 == null) {
				switch(action._1) {
				case connect:
					connect(action._2);
					break;
				case evolve:
					evolve(action._2);
					break;
				case none:
					System.err.println("Get a none statement at wrong place, no reason to get scared... Right?");
					break;
				case place:
					place(action._2);
					break;
				case remove:
					break;
				default:
					throw new InternalError("J'ai oublié " + action._1);
				}
			}
		}
	}
	
	private void connect(Object object) {
		if(object instanceof Couple<?, ?>
		   && ((Couple<?, ?>) object)._1 instanceof PathNode
		   && ((Couple<?, ?>) object)._2 instanceof PathNode) {
				@SuppressWarnings("unchecked")
				Couple<PathNode, PathNode> pathNodes = (Couple<PathNode, PathNode>) object;
				pathNodes._1.link(pathNodes._2);
		} else {
				throw new IllegalStateException("connect n'agit que sur des Couple<PathNode, PathNode> pas sur " + object.getClass());
		}
	}
	
	private void evolve(Object object) {
		if(object instanceof Tower) {
			Tower tower = (Tower) object;
			if(tower.canEvolve()) {
				tower.evolve();
			} else {
				System.err.println(tower + " essaie d'évoluer alors qu'il ne peut pas, blamez celui qui a envoyé la demande.");
			}
		} else {
			throw new IllegalStateException("evolve n'agit que sur des Tower pas sur " + object.getClass());
		}
	}
	
	private void place(Object object) {
		if(object instanceof Couple<?, ?>
		   && ((Couple<?, ?>) object)._1 instanceof Monster
		   && ((Couple<?, ?>) object)._1 instanceof PathNode) {
			@SuppressWarnings("unchecked")
			Couple<Monster, PathNode> coupleMonster = (Couple<Monster, PathNode>) object;
			
		} else if(object instanceof PathNode) {
			PathNode pathNode = (PathNode) object;
		} else if(object instanceof Tower) {
			Tower tower = (Tower) object;
		} else {
			throw new IllegalStateException("place only work on Monster, PathNode or Tower " + object.getClass());
		}
	}
	
	public HashSet<Entity> getEntities() {
		return entities;
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
	
	public boolean getSomeNews() {
		boolean dummy = someNews;
		someNews = false;
		return dummy;
	}
	
	public void setActiv(boolean activ) {
		this.activ = activ;
		if(activ) {
			run();
		}
	}
	
	public int getLives() {
		return lives;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public enum Action {
		connect,
		evolve,
		none,
		place,
		remove;
	}
}
