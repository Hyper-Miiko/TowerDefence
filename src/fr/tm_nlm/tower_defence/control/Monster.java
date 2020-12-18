package fr.tm_nlm.tower_defence.control;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public abstract class Monster extends Entity {
	private double speed;
	private LinkedList<Vector> objectives;
	
	public Monster(Vector position, Shape shape) {
		super(position, shape);
		speed = 0;
		objectives = new LinkedList<>();
	}
	
	/**
	 * Déplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		//TODO
	}
	
	public void setPosition(Vector position) {
		super.setPosition(position);
	}
	
	public void setposition(double x, double y) {
		super.setPosition(x, y);
	}
}
