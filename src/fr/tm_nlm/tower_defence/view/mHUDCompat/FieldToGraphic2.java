package fr.tm_nlm.tower_defence.view.mHUDCompat;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.HashMap;

import fr.tm_nlm.tower_defence.control2.Bullet;
import fr.tm_nlm.tower_defence.control2.DisplayEntity;
import fr.tm_nlm.tower_defence.control2.Game;
import fr.tm_nlm.tower_defence.control2.Monster;
import fr.tm_nlm.tower_defence.control2.PathNode;
import fr.tm_nlm.tower_defence.control2.Tower;
import mHUD.mGraphicEntity.GCircleEntity;
import mHUD.mGraphicEntity.GPictureEntity;
import mHUD.mGraphicEntity.GRectEntity;
import mHUD.mGraphicEntity.IGraphicView;
import mHUD.mGraphicEntity.LifeBar;
import mHUD.mGraphicEntity.MGraphicEntity;

public class FieldToGraphic2 extends Thread {
	private IGraphicView view;
	private HashMap<DisplayEntity, MGraphicEntity> entityToGraphic = new HashMap<>();
	private HashMap<DisplayEntity, LifeBar> lifeBar = new HashMap<>();
	//private HashMap<PathNode, MGraphicEntity> nodePath;
	//private HashMap<MGraphicEntity, Entity> graphicToEntity;
	private GPictureEntity background = null;
	private String actualBackground = null;

	private boolean displaySlot = false;
	
	public FieldToGraphic2(IGraphicView view) {
		this.view = view;
	}
	
	private void add(DisplayEntity entity) {
		MGraphicEntity graphic;
		
		Rectangle r = entity.getShape()._1.getBounds();
		if(entity.haveImage()) {
			graphic = new GPictureEntity(r.getCenterX(), r.getCenterY(), entity.getImage());
		}
		else {
			graphic = new GCircleEntity(r.getCenterX(), r.getCenterY(),r.getSize().height/2);
			((GCircleEntity)graphic).setBackgroundColor(entity.getShape()._2);
			((GCircleEntity)graphic).setLineColor(new Color(0,0,0));
		}
		
		view.addGraphicEntityAt(entity.getPriority(),graphic);
		graphic.setDisplay(entity.isOnScreen());
		entityToGraphic.put(entity, graphic);
		
		if(entity.isDamageable()) {
			LifeBar l = new LifeBar(r.getCenterX(), r.getCenterY(),r.getSize().height*2,r.getSize().height/4,entity.getHealth());
			lifeBar.put(entity, l);
			l.setDisplay(entity.isOnScreen());
			view.addGraphicEntityAt(entity.getPriority(),l);
		}
	}
	
	private void remove(DisplayEntity entity) {
		MGraphicEntity graphic = getEntity(entity);
		view.removeGraphicEntity(graphic);
		entityToGraphic.remove(entity);
		
		LifeBar l = lifeBar.get(entity);
		if(l != null) {
			view.removeGraphicEntity(l);
			lifeBar.remove(entity);
		}
	}
	private void edit(DisplayEntity entity) {
		MGraphicEntity graphic = getEntity(entity);
		Rectangle r = entity.getShape()._1.getBounds();	
		graphic.setPosition(r.getCenterX(), r.getCenterY());
		
		if(entity.isSlot()) graphic.setDisplay(displaySlot);
		else graphic.setDisplay(entity.isOnScreen());
		
		if(graphic instanceof GCircleEntity) {
			((GCircleEntity)graphic).setBackgroundColor(entity.getShape()._2);
			((GCircleEntity)graphic).setLineColor(new Color(255,255,255));
		}
		
		LifeBar l = getLifeBar(entity);
		if(l != null) {
			l.setPosition(r.getCenterX(), r.getCenterY());
			l.setLife(entity.getHealth());
			l.setDisplay(entity.isOnScreen());
		}
	}
	
	private MGraphicEntity getEntity(DisplayEntity entity) {
		for(DisplayEntity e1 : entityToGraphic.keySet()) {
			if(entity.equals(e1))return entityToGraphic.get(e1);
		}
		return null;
	}
	private LifeBar getLifeBar(DisplayEntity entity) {
		for(DisplayEntity e1 : lifeBar.keySet()) {
			if(entity.equals(e1))return lifeBar.get(e1);
		}
		return null;
	}

	@Override
	public void run() {
		output();
	}
	
	private void output() {
		if((actualBackground == null && Game.getBackground() != null) || !actualBackground.equals(Game.getBackground())) {		
			background = new GPictureEntity(view.getSize().x/2,view.getSize().y/2,Game.getBackground());
			view.addGraphicEntityAt(0, background);
			actualBackground = Game.getBackground();
		}
		
		for(DisplayEntity entity : Game.getAll()) {
			boolean isPresent = false;	
			for(DisplayEntity e1 : entityToGraphic.keySet()) {
				if(entity.equals(e1)) {
					isPresent = true;
					break;
				}
			}
			if(isPresent)edit(entity);
			else add(entity);
		}
		
		HashMap<DisplayEntity, MGraphicEntity> list = new HashMap<DisplayEntity, MGraphicEntity>(entityToGraphic);
		for(DisplayEntity e1 : list.keySet()) {
			boolean isPresent = false;	
			for(DisplayEntity entity : Game.getAll()) {
				if(entity.equals(e1)) {
					isPresent = true;
					break;
				}
			}
			if(!isPresent)remove(e1);
		}
	}

	public void setDisplaySlot(boolean d) {
		displaySlot = d;
	}
}
