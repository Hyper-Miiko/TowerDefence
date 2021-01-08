package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;

public interface Movable {
	public void move();
	public double getAngle();
	public Vector getPosition();
	public double getSpeed();
}
