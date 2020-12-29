package fr.tm_nlm.tower_defence.control.data;

import java.util.HashMap;

import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Rect;

public class Appareances {
	private HashMap<Posture, String> postures;
	private Shape shape;
	
	public Appareances(Shape shape) {
		this.shape = shape;
		postures = new HashMap<>();
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public Circle getCircle() {
		return shape.getCircle();
	}
	
	public Rect getRect() {
		return shape.getRect();
	}

	public enum Posture {
		WALK_TL1,
		WALK_TL2,
		WALK_TL3,
		WALK_T1,
		WALK_T2,
		WALK_T3,
		WALK_TR1,
		WALK_TR2,
		WALK_TR3,
		WALK_R1,
		WALK_R2,
		WALK_R3,
		WALK_BR1,
		WALK_BR2,
		WALK_BR3,
		WALK_B1,
		WALK_B2,
		WALK_B3,
		WALK_BL1,
		WALK_BL2,
		WALK_BL3,
		WALK_L1,
		WALK_L2,
		WALK_L3;
	}
}
