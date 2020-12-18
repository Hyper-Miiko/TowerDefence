package fr.tm_nlm.tower_defence.control;

import java.util.ArrayList;

import fr.tm_nlm.tower_defence.control.data.Image;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Rect;

import static fr.tm_nlm.tower_defence.Constant.RIGHT;
import static fr.tm_nlm.tower_defence.Constant.BOTTOM;

public class Entity {
	public boolean collide(Entity entityA, Entity entityB) {
		boolean collide;
		if(entityA.shape instanceof Circle && entityB.shape instanceof Circle) {
			double cumulRadius = ((Circle) entityA.shape).getRadius();
			cumulRadius += ((Circle) entityB.shape).getRadius();
			collide = cumulRadius <= entityA.position.dist(entityB.position);
		} else if(entityA.shape instanceof Rect && entityB.shape instanceof Rect) {
			double aPosX = ((Rect) entityA.shape).getPosition().x;
			double aPosY = ((Rect) entityA.shape).getPosition().y;
			double bPosX = ((Rect) entityB.shape).getPosition().x;
			double bPosY = ((Rect) entityB.shape).getPosition().y;
			int compare = (aPosX <= bPosX) ? 0 : 1;
			compare += (aPosY <= bPosY) ? 0 : 2;
			if(compare == 0) {
				double aPosCornerX = aPosX + ((Rect) entityA.shape).getCorner(RIGHT, BOTTOM).x/2;
				double aPosCornerY = aPosY + ((Rect) entityA.shape).getCorner(RIGHT, BOTTOM).y/2;
				//if()
			}
		}
		return /*collide*/false;
	}
	
	private Vector position;
	private Shape shape;
	private ArrayList<Image> img;

	public Entity(Vector position, Shape shape) {
		this.position = position;
		this.shape = shape;
		img = new ArrayList<>();
	}

	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public void setPosition(double x, double y) {
		
	}
}
