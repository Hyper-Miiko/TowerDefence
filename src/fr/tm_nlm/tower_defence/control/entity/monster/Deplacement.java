package fr.tm_nlm.tower_defence.control.entity.monster;

import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathBridge;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathNode;

public interface Deplacement {
	abstract Vector move(double speed, Shape shape, PathNode nextNode, PathBridge bridge);
}