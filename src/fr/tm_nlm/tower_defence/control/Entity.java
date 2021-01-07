package fr.tm_nlm.tower_defence.control;

import fr.tm_nlm.tower_defence.control.data.Appareances;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public abstract class Entity {
	private static long nextId = 1;
	
	protected Field field;
	private Appareances appareances;
	private boolean dead;
	protected boolean check;
	private long lastNano;
	private final long id;

	public Entity(Field field, Shape shape) {
		this.field = field;
		appareances = new Appareances(shape);
		id = nextId++;
		check = false;
		lastNano = System.nanoTime();
		
		field.add(this);
	}
	
	protected void refreshNano() {
		lastNano = System.nanoTime();
	}
	
	protected long getLastNano() {
		return lastNano;
	}
	
	protected double getLastSecond() {
		return (double) lastNano/1000000000d;
	}
	
	public boolean collide(Entity entity) {
		return collide(entity.getAppareances().getShape());
	}
	
	public boolean collide(Shape shape) {
		return getAppareances().getShape().collide(shape);
	}
	
	public void kill() {
		dead = true;
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
	
	public boolean isCheck() {
		return check;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void check() {
		check = true;
	}
	
	@Override
	public String toString() {
		return "Entité n°" + id +
			   " aux positions " + appareances.getShape().getPosition() +
			   " dans la carte " + field + 
			   ", check: " + check +
			   ".";
	}
	
	@Override
	public boolean equals(Object object) {
		return (object instanceof Entity) && (((Entity) object).id == id);
	}
}
