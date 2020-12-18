package fr.tm_nlm.tower_defence.control;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.entity.FieldTile;
import fr.tm_nlm.tower_defence.control.entity.fieldtile.PathLand;

public class Field {
	private LinkedList<FieldTile> fieldTiles;
	private LinkedList<PathLand> pathLands;
	
	public Field() {
		fieldTiles = new LinkedList<>();
		pathLands = new LinkedList<>();
	}

	public void addPathLand(PathLand pathLand) {
		pathLands.add(pathLand);
		
	}
}