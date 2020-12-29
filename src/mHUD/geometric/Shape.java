package mHUD.geometric;

public abstract class Shape {
	private Vector position;
	
	public Shape(Vector position) {
		setPosition(position);
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	public void setPosition(double x, double y) {
		position = new Vector(x, y);
	}
	public Vector getPosition() {
		return position;
	}
}
