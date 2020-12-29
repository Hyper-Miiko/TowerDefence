package fr.tm_nlm.tower_defence.control.entity.monster;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;
import fr.tm_nlm.tower_defence.control.entity.MeleeTower;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathBridge;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathNode;

public class Walking implements Deplacement {
	@Override
	public Vector move(double speed, Shape shape, PathNode nextNode, PathBridge bridge) {
		double angle = shape.getPosition().angle(nextNode.getPosition());
		Vector nextPosition = shape.getPosition().byAngle(angle, speed);
		LinkedList<MeleeTower> obstacles = bridge.obstacles;
		Shape newShape = (shape instanceof Circle) ? shape.getCircle() : shape.getRect();
		for(Entity obstacle : obstacles) {
			if(obstacle.getAppareances().getShape().collide(newShape)) {
				nextPosition = shape.getPosition();
			}
		}
		return nextPosition;
	}
}
