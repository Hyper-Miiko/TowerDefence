package fr.tm_nlm.tower_defence.control2;

public interface Movable extends Localisable {
	/**
	 * 
	 * @param time
	 * @param collide
	 * @return
	 */
	public boolean move(double time, boolean collide);
	public double getSpeed();
	public double getBaseSpeed();
}
