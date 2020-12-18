package fr.tm_nlm.tower_defence.control;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.data.geometric.Position;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public abstract class Monster extends Entity {
	private double speed;
	private LinkedList<Position> objectives;
	
	public Monster(Position position, Shape shape) {
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
	
	public void setPosition(Position position) {
		super.setPosition(position);
	}
	
	public void setposition(double x, double y) {
		super.setPosition(x, y);
	}
}
