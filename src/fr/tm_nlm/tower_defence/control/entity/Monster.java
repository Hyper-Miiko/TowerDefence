package fr.tm_nlm.tower_defence.control.entity;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public abstract class Monster extends Entity {
	private final double speed;
	private LinkedList<Vector> objectives;
	
	public Monster(Field field, Shape shape, double speed) {
		super(field, shape);
		this.speed = speed;
		objectives = new LinkedList<>();
	}
}
