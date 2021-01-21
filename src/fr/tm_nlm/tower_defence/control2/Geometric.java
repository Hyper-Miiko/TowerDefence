package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;

import fr.tm_nlm.tower_defence.Couple;

public class Geometric implements Displayable, Cloneable {
	private Angle angle;
	private String imageName;
	private Area area;
	private Color color;
	
	{
		angle = new Angle(0);
		color = new Color(255, 255, 255, 255);
	}
	public Geometric(Area area) {
		this.area = area;
	}
	
	@Override
	public Object clone() {
		Geometric clone = new Geometric((Area) area.clone());
		clone.angle = angle;
		clone.imageName = imageName;
		clone.color = color;
		return clone;
	}
	
	public void translateByAngle(double dist) {
		Vector diff = new Vector(0, 0).byAngle(angle, dist);
		area.transform(AffineTransform.getTranslateInstance(diff.x,  diff.y));
	}
	
	public void setPosition(Vector position) {
		Rectangle2D bounds = area.getBounds2D();
		area.transform(AffineTransform.getTranslateInstance(-bounds.getCenterX(),  -bounds.getCenterY()));
		area.transform(AffineTransform.getTranslateInstance(position.x,  position.y));
	}
	
	public boolean collide(Displayable entity) {
		Area collideArea = (Area) entity.getShape()._1.clone();
		collideArea.intersect(this.area);
		return collideArea.isEmpty();
	}

	public Angle getAngle() {
		return angle;
	}
	
	@Override
	public Vector getPosition() {
		Rectangle2D bound = area.getBounds2D();
		return new Vector(bound.getCenterX(), bound.getCenterY());
	}

	@Override
	public boolean isOnScreen() {
		return false;
	}

	@Override
	public boolean haveImage() {
		return imageName != null;
	}

	@Override
	public Couple<Area, Color> getShape() {
		return new Couple<>((Area) area.clone(), color);
	}

	@Override
	public String getImage() {
		return "data/img/" + imageName + ".png";
	}
	public void setAngle(Angle angle) {
		this.angle = angle;
		double diff = Angle.diff(this.angle, angle).value();
		area.transform(AffineTransform.getRotateInstance(diff));
		this.angle = angle;
	}

	public void setImage(String image) {
		this.imageName = image;
	}
}
