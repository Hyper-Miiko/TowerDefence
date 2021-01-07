package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathNode;

public final class PresetMonster {
	private PresetMonster() {}
	
	public static Monster buildKumo(Field field, PathNode start) {
		Shape shape = new Circle(start.getPosition(), 5);
		Monster kumo = new Monster(field, shape, start);
		kumo.setBaseSpeed(30);
		kumo.setMonsterType("Kumo");
		return kumo;
	}
	
	public static Monster buildKumo(Field field) {
		Monster kumo = new Monster(field, new Circle(null, 5));
		kumo.setBaseSpeed(30);
		kumo.setMonsterType("Kumo");
		return kumo;
	}
}
