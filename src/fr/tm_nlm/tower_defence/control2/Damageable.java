package fr.tm_nlm.tower_defence.control2;

public interface Damageable {
	public double getMaxHealth();
	public double getHealth();
	public void hurt(double damage);
	public void heal(double sustain);
}
