package fr.tm_nlm.tower_defence.control;

public interface Damageable {
	public abstract void damage(int value);
	public abstract void heal(int value);
	public abstract int getHealth();
}
