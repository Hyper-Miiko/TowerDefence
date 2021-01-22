package fr.tm_nlm.tower_defence.control2;

import fr.tm_nlm.tower_defence.Couple;

public interface Movable extends Localisable {
	/**
	 * 
	 * @param time
	 * @param collide
	 * @return
	 */
	public boolean move(double time, boolean collide);
	public void resetMove();
	public double getSpeed();
	public double getBaseSpeed();
	public void slow(Couple<Double, Double> slow);
	public void confuse(double confuse);
}
