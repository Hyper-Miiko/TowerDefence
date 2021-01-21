package fr.tm_nlm.tower_defence.control2;

public interface Displayable extends Localisable {
	public boolean isOnScreen();
	public boolean haveImage();
	public String getImage();
}
