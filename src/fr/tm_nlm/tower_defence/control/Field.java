package fr.tm_nlm.tower_defence.control;

import java.util.HashSet;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.entity.Bullet;
import fr.tm_nlm.tower_defence.control.entity.Entity;
import fr.tm_nlm.tower_defence.control.entity.Monster;
import fr.tm_nlm.tower_defence.control.entity.PathNode;
import fr.tm_nlm.tower_defence.control.entity.Tower;

public class Field extends Thread {
	private boolean running;
	private boolean someNews;
	private int lives;
	private int temmies;
	private final HashSet<Entity> entities;
	private final LinkedList<PathNode> pathNodes;
	private final LinkedList<Tower> towers;
	private final LinkedList<Monster> monsters;
	private final LinkedList<Bullet> bullets;
	private final LinkedList<Entity> trash;

	public Field() {
		super("Champ");
		lives = 10;
		temmies = 100;
		entities = new HashSet<>();
		pathNodes = new LinkedList<>();
		towers = new LinkedList<>();
		monsters = new LinkedList<>();
		bullets = new LinkedList<>();
		trash = new LinkedList<>();
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
		} else if(entity instanceof Bullet) {
			bullets.add((Bullet) entity);
		} else {
			throw new InternalError("L'entité " + entity + " n'est pas reconnue");
		}
		someNews = true;
	}
	
	public boolean buy(int price) {
		if(price <= temmies) {
			temmies -= price;
			return true;
		}
		return false;
	}
	
	public boolean canBuy(int price) {
		return price <= temmies;
	}

	public void remove(Entity entity) {
		trash.add(entity);
	}
	
	public void removeLive(int nbrOfLive) {
		lives = (nbrOfLive >= lives) ? 0 : lives - nbrOfLive;
	}

	@Override
	public void run() {
		processEntities();
		emptyTrash();
	}
	
	private void emptyTrash() {
		while(!trash.isEmpty()) {
			Entity junk = trash.poll();
			entities.remove(junk);
			if(junk instanceof PathNode) {
				pathNodes.remove(junk);
			} else if(junk instanceof Tower) {
				towers.remove(junk);
			} else if(junk instanceof Monster) {
				System.out.println(monsters.size());
				monsters.remove(junk);
				System.out.println(monsters.size());
			} else if(junk instanceof Bullet) {
				bullets.remove(junk);
			} else {
				throw new InternalError("L'entité " + junk + " n'est pas reconnue");
			}
			someNews = true;
		}
	}
	
	public PathNode createPathNode(Vector position, boolean castle) {
		PathNode pathNode = new PathNode(this, position, castle);
		add(pathNode);
		return pathNode;
	}
	
	public PathNode createPathNode(double x, double y, boolean castle) {
		return createPathNode(new Vector(x, y), castle);
	}
	
	public void placeTower(Tower tower, Vector position) {
		tower.place(position);
	}
	
	public void placeTower(Tower tower, double x, double y) {
		placeTower(tower, new Vector(x, y));
	}
	
	public void placeMonster(Monster monster, PathNode pathNode) {
		monster.place(pathNode);
	}
	
	public void evolveTower(Tower tower) {
		if(tower.canEvolve()) {
			tower.evolve();
		} else {
			System.err.println(tower + " essaie d'évoluer alors qu'il ne peut pas, blamez celui qui a envoyé la demande.");
		}
	}
	
	public void connect(PathNode pathNodeA, PathNode pathNodeB) {
		pathNodeA.link(pathNodeB);
	}
	
//	public void workOn(Action action, Vector position, Entity... entities) {
//		Couple<Entity[], Vector> targets = new Couple<>(entities, position);
//		Couple<Action, Couple<Entity[], Vector>> task = new Couple<>(action, targets);
//		job.add(task);
//	}
//	
//	public void workOn(Action action, Entity... entities) {
//		Couple<Entity[], Vector> targets = new Couple<>(entities, null);
//		Couple<Action, Couple<Entity[], Vector>> task = new Couple<>(action, targets);
//		job.add(task);
//	}
	
	private void processEntities() {
		for(Entity entity : entities) {
			if(entity.isDead()) {
				trash.add(entity);
			}
		}
		for(Tower tower : towers) {
			if(tower.isOnField()) {
				tower.process();
			}
		}
		for(Monster monster : monsters) {
			monster.process();
		}
		for(Bullet bullet : bullets) {
			bullet.process();
		}
	}
	
	public HashSet<Entity> getEntities() {
		return entities;
	}
	
	public LinkedList<Monster> getMonsters() {
		return monsters;
	}
	
	public LinkedList<PathNode> getPathNodes() {
		return pathNodes;
	}
	
	public LinkedList<Tower> getTowers() {
		return towers;
	}
	
	public LinkedList<Bullet> getBullets() {
		return bullets;
	}
	
	public boolean getSomeNews() {
		boolean dummy = someNews;
		someNews = false;
		return dummy;
	}
	
	public int getLives() {
		return lives;
	}
	public int getTemmies() {
		return temmies;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	@Override
	public String toString() {
		String str = "Field : " + getName();
		
		int nbrMonsters = monsters.size();
		str += "\n\nIl y a actuellement " + nbrMonsters + " monstres sur le terrain.";
		if(nbrMonsters <= 15) {
			for(Monster monster : monsters) {
				str += "\n\t" + monster;
			}
		}
		
		int nbrPathNodes = pathNodes.size();
		str += "\n\nIl y a actuellement " + nbrPathNodes + " points de passage sur le terrain.";
		if(nbrPathNodes <= 15) {
			for(PathNode pathNode: pathNodes) {
				str += "\n\t" + pathNode;
			}
		}
		
		int nbrTowers = towers.size();
		str += "\n\nIl y a actuellement " + nbrTowers + " tours.";
		for(Tower tower : towers) {
			str += "\n\t" + tower;
		}
		
		str += "\n\nPour un total de " + entities.size() + " entitée.";
		return str;
	}
	
	public enum Action {
		connect,
		evolve,
		place,
		remove;
	}
}
