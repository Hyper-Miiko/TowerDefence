package mHUD.geometric;

public class Circle extends Shape {
	private double radius;

	public Circle(Vector position, double radius) {
		super(position);
		setRadius(radius);
	}
	public Circle(double posX, double posY, double radius) {
		super(new Vector(posX, posY));
		setRadius(radius);
	}

	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		if(radius <= 0) {
			throw new IllegalArgumentException("The radius must be > 0.");
		}
		this.radius = radius;
	}
}