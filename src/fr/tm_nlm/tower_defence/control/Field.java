package fr.tm_nlm.tower_defence.control;

import java.util.HashMap;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.entity.FieldTile;
import fr.tm_nlm.tower_defence.control.entity.Monster;
import fr.tm_nlm.tower_defence.control.entity.Tower;

public class Field {
	private int lives;
	private int temmies;
	private LinkedList<Entity> entities;
	private LinkedList<FieldTile> fieldTiles;
	private LinkedList<Tower> towers;
	private LinkedList<Monster> monsters;

	public Field(int lives, int temmies) {
		this.lives = lives;
		this.temmies = temmies;
		entities = new LinkedList<>();
	}

	public void add(Entity entity) {
		entities.add(entity);
		if(entity instanceof FieldTile) {
			fieldTiles.add((FieldTile) entity);
		} else if(entity instanceof Tower) {
			towers.add((Tower) entity);
		} else if(entity instanceof Monster) {
			monsters.add((Monster) entity);
		} else {
			throw new InternalError("L'entité " + entity + " n'est pas reconnue");
		}
	}

	public void remove(Entity entity) {
		entities.remove(entity);
		if(entity instanceof FieldTile) {
			fieldTiles.remove(entity);
		} else if(entity instanceof Tower) {
			towers.remove(entity);
		} else if(entity instanceof Monster) {
			monsters.remove(entity);
		} else {
			throw new InternalError("L'entité " + entity + " n'est pas reconnue");
		}
	}
	
	public HashMap<Tower, Boolean> getAllTower() {
		return Tower.getAddables(this);
	}
}
