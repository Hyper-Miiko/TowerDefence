//package fr.tm_nlm.tower_defence.control.entity;
//
//import fr.tm_nlm.tower_defence.control.Field;
//import fr.tm_nlm.tower_defence.control.data.Appareances;
//import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
//import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
//
//public abstract class DisplayEntity {
//	private static long nextId = 1;
//	
//	public static DisplayEntity dummy(Vector vector) {
//		return null;
//	}
//	
//	protected Field field;
//	private Appareances appareances;
//	private boolean dead;
//	protected boolean check;
//	private long lastNano;
//	private final long id;
//	private String name;
//	
//	{
//		check = false;
//		lastNano = System.nanoTime();
//		name = "undefined";
//	}
//	public DisplayEntity(Field field, Shape shape) {
//		id = nextId++;
//		init(field, shape);
//	}
//	
//	private void init(Field field, Shape shape) {
//		this.field = field;
//		appareances = new Appareances(shape);
//	}
//	
//	protected void refreshNano() {
//		lastNano = System.nanoTime();
//	}
//	
//	protected long getLastNano() {
//		return lastNano;
//	}
//	
//	protected double getLastSecond() {
//		return (double) lastNano/1000000000d;
//	}
//	
//	public boolean collide(DisplayEntity entity) {
//		return collide(entity.getAppareances().getShape());
//	}
//	
//	public boolean collide(Shape shape) {
//		return getAppareances().getShape().collide(shape);
//	}
//	
//	public void kill() {
//		dead = true;
//		check = false;
//	}
//	
//	public Field getField() {
//		return field;
//	}
//	
//	public Vector getPosition() {
//		return appareances.getShape().getPosition();
//	}
//	public void setPosition(Vector vector) {
//		appareances.getShape().setPosition(vector);
//		check = false;
//	}
//
//	public Appareances getAppareances() {
//		return appareances;
//	}
//	
//	public boolean isCheck() {
//		return check;
//	}
//	
//	public boolean isDead() {
//		return dead;
//	}
//	
//	public boolean isOnField() {
//		return appareances.getShape() != null;
//	}
//	
//	public void check() {
//		check = true;
//	}
//	
//	public long id() {
//		return id;
//	}
//	
//	@Override
//	public String toString() {
//		return "Entité n°" + id;
//	}
//	
//	@Override
//	public boolean equals(Object object) {
//		return (object instanceof DisplayEntity) && (((DisplayEntity) object).id == id);
//	}
//	
//	public String getName() {
//		return name;
//	}
//	
//	protected void setName(String name) {
//		this.name = name;
//	}
//}
