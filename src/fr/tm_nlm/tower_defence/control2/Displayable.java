package fr.tm_nlm.tower_defence.control2;

import java.awt.geom.Area;
import fr.tm_nlm.tower_defence.Couple;

public interface Displayable extends Localisable {
	public boolean isOnScreen();
	public boolean haveImage();
	public Couple<String, Area> getImage();
}
