package fr.tm_nlm.tower_defence.control2;

public interface Localisable {
	public Vector getPosition();
	public default boolean havePosition() {
		return getPosition() != null;
	}
}
