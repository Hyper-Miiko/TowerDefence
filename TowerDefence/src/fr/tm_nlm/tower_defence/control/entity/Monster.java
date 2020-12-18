package fr.tm_nlm.tower_defence.control.entity;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public abstract class Monster extends Entity {
	private double speed;
	private LinkedList<Vector> objectives;
	
	public Monster(Field field, Shape shape) {
		super(field, shape);
		speed = 0;
		objectives = new LinkedList<>();
	}
	
	/**
	 * Déplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		//TODO
	}
}
