package fr.tm_nlm.tower_defence.control.entity.monster;

import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.entity.Monster;

public class FlyingMonster extends Monster {

	public FlyingMonster(Field field, Shape shape, double speed) {
		super(field, shape, speed);
	}

}