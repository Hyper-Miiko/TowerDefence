package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import fr.tm_nlm.tower_defence.Couple;

/**
 * Classe qui sert pour les collisions et la représentation des entity
 * @author Hyper Mïko
 * FIXME Problème de rotation (peut être de translation aussi) 
 */
public class Geometric implements Displayable, Cloneable {
	private Angle angle;
	private String imageName;
	private Area area;
	private Color color;
	private Vector position;
	
	{
		angle = new Angle(0);
		color = new Color(255, 255, 255, 255);
	}
	public Geometric(Area area) {
		this.area = area;
		Rectangle2D bounds = area.getBounds2D();
		position = new Vector(bounds.getCenterX(), bounds.getCenterY());
//		setPosition(new Vector(0, 0));
//		this.position = new Vector(0, 0);
	}
	
	@Override
	public Object clone() {
		Geometric clone = new Geometric((Area) area.clone());
		clone.angle = angle;
		clone.imageName = imageName;
		clone.color = color;
		clone.position = position;
		return clone;
	}
	
	public void translateByAngle(double dist) {
//		Vector diff = new Vector(0, 0).byAngle(angle, dist);
		setPosition(position.byAngle(angle, dist));
//		area.transform(AffineTransform.getTranslateInstance(diff.x,  diff.y));
	}
	
	public void setPosition(Vector position) {
//		Rectangle2D bounds = area.getBounds2D();
//		area.transform(AffineTransform.getTranslateInstance(-bounds.getCenterX(),  -bounds.getCenterY()));
		area.transform(AffineTransform.getTranslateInstance(-this.position.x,  -this.position.y));
		area.transform(AffineTransform.getTranslateInstance(position.x,  position.y));
		this.position = position;
	}
	
	public boolean collide(Displayable entity) {
		Area collideArea = (Area) entity.getShape()._1.clone();
		collideArea.intersect(this.area);
		return !collideArea.isEmpty();
	}

	public Angle getAngle() {
		return angle;
	}
	
	@Override
	public Vector getPosition() {
//		Rectangle2D bound = area.getBounds2D();
//		return new Vector(bound.getCenterX(), bound.getCenterY());
		return position;
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
//		area.transform(AffineTransform.getRotateInstance(-this.angle.value()));
//		area.transform(AffineTransform.getRotateInstance(angle.value()));
//		this.angle = angle;
	}

	public void setImage(String image) {
		this.imageName = image;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean isFlying() {
		return false;
	}
}
