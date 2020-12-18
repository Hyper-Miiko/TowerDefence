package fr.tm_nlm.tower_defence.control.data.geometric;

public abstract class Shape {
	private Position position;
	
	public Shape(Position position) {
		setPosition(position);
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}
}
