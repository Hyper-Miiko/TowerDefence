package fr.tm_nlm.tower_defence.control.entity.monster;

import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.entity.PathNode;

public final class Walking implements Deplacement {
	@Override
	public Vector move(double speed, Shape shape, PathNode nextNode) {
		double angle = shape.getPosition().angle(nextNode.getPosition());
		Vector nextPosition = shape.getPosition().byAngle(angle, speed);
		return nextPosition;
	}
}
