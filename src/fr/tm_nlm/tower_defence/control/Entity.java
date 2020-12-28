package fr.tm_nlm.tower_defence.control;

import fr.tm_nlm.tower_defence.control.data.Appareances;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public class Entity {
	private static long nextId = 1;
	
	protected Field field;
	private Appareances appareances;
	private final long id;

	public Entity(Field field, Shape shape) {
		this.field = field;
		appareances = new Appareances(shape);
		id = nextId++;
	}
	
	public Vector getPosition() {
		return appareances.getShape().getPosition();
	}

	public Appareances getAppareances() {
		return appareances;
	}
	
	@Override
	public boolean equals(Object entity) {
		if(!(entity instanceof Entity)) {
			return false;
		} else {
			return this.id == ((Entity) entity).id;
		}
	}
	
	@Override
	public String toString() {
		return "Entité n°" + id +
			   " aux positions " + appareances.getShape().getPosition() +
			   " dans la carte " + field + 
			   ".";
	}
}
