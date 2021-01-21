package fr.tm_nlm.tower_defence.control2;

public interface Displayable extends Localisable {
	public boolean isOnScreen();
	public default boolean haveImage() {
		return !getImage().equals("data/img/null.png");
	}
	public String getImage();
}
