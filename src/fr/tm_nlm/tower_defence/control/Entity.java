package fr.tm_nlm.tower_defence.control;

import fr.tm_nlm.tower_defence.control.data.Appareances;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public class Entity {
	private static long nextId = 1;
	
	protected Field field;
	private Appareances appareances;
	private boolean check;
	private final long id;

	public Entity(Field field, Shape shape) {
		this.field = field;
		appareances = new Appareances(shape);
		id = nextId++;
		check = false;
		
		field.add(this);
	}
	
	public void kill() {
		field.remove(this);
	}
	
	public Field getField() {
		return field;
	}
	
	public Vector getPosition() {
		return appareances.getShape().getPosition();
	}
	public void setPosition(Vector vector) {
		appareances.getShape().setPosition(vector);
		check = false;
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
	
	public boolean isCheck() {
		return check;
	}
	
	public void check() {
		check = true;
	}
	
	@Override
	public String toString() {
		return "Entité n°" + id +
			   " aux positions " + appareances.getShape().getPosition() +
			   " dans la carte " + field + 
			   ".";
	}
}
