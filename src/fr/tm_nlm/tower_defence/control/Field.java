package fr.tm_nlm.tower_defence.control;

import java.util.LinkedList;

public class Field {
	LinkedList<Entity> entities;

	public Field() {
		// TODO Auto-generated constructor stub
	}

	public void remove(Entity entity) {
		entities.remove(entity);
	}
}
