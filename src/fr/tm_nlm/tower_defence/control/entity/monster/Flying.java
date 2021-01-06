package fr.tm_nlm.tower_defence.control.entity.monster;

import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathNode;

public final class Flying implements Deplacement {
	@Override
	public Vector move(double speed, Shape shape, PathNode nextNode) {
		double angle = shape.getPosition().angle(nextNode.getPosition());
		return shape.getPosition().byAngle(angle, speed);
	}
}
