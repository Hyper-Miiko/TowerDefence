package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.HashSet;

import fr.tm_nlm.tower_defence.Couple;

public class Geometric implements Displayable, Cloneable {
	private Angle angle;
	private String imageName;
	private Vector position;
	private Area area;
	private Color color;
	
	public Geometric(Area area) {
		this.area = area;
		position = new Vector(0, 0);
		angle = new Angle(0);
	}
	
	@Override
	public Object clone() {
		Geometric clone = new Geometric((Area) area.clone());
		clone.angle = angle;
		clone.imageName = imageName;
		clone.position = position;
		clone.color = color;
		return clone;
	}
	
	public void translateByAngle(double dist) {
		Vector newPosition = position.byAngle(angle, dist);
		setPosition(newPosition);
	}
	
	public void setPosition(Vector position) {
		double diffX = this.position.x - position.x;
		double diffY = this.position.y - position.y;
		area.transform(AffineTransform.getTranslateInstance(diffX,  diffY));
		this.position = position;
	}
	
	public boolean collide(Displayable entity) {
		Area collideArea = (Area) entity.getShape()._1.clone();
		collideArea.intersect(this.area);
		return collideArea.isEmpty();
	}

	public double getAngle() {
		return angle.value();
	}
	
	@Override
	public Vector getPosition() {
		return position;
	}

	@Override
	public boolean isOnScreen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean haveImage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Couple<Area, Color> getShape() {
		return new Couple<>((Area) area.clone(), color);
	}

	@Override
	public Couple<String, Area> getImage() {
		return new Couple<>(imageName, (Area) area.clone());
	}
	public void setAngle(Angle angle) {
		this.angle = angle;
		double diff = Angle.diff(this.angle, angle).value();
		area.transform(AffineTransform.getRotateInstance(diff));
		this.angle = angle;
	}
}
