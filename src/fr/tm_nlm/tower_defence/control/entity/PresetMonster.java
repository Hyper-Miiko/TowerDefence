package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;

public final class PresetMonster {
	private PresetMonster() {}
	
	public static Monster buildKumo(Field field) {
		Monster kumo = new Monster(field, new Circle(null, 10));
		kumo.setBaseSpeed(60);
		kumo.setMonsterType("Kumo");
		kumo.getAppareances().setColor(200, 0, 200);
		return kumo;
	}
	
	public static Monster buildAirplanChan(Field field) {
		Monster airplan = new Monster(field, new Circle(null, 50));
		airplan.setBaseSpeed(40);
		airplan.setHealth(200);
		airplan.setMonsterType("Airplan-Chan");
		airplan.getAppareances().setColor(255, 0, 200);
		airplan.setFly(true);
		airplan.setBoss(true);
		airplan.setStrength(5);
		return airplan;
	}
}
