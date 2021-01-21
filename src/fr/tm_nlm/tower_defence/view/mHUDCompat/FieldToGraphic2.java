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
import mHUD.mGraphicEntity.MGraphicEntity;

public class FieldToGraphic2 extends Thread {
	private IGraphicView view;
	private HashMap<DisplayEntity, MGraphicEntity> entityToGraphic;
	private HashMap<PathNode, MGraphicEntity> nodePath;
	//private HashMap<MGraphicEntity, Entity> graphicToEntity;

	public FieldToGraphic2(IGraphicView view) {
		this.view = view;
		entityToGraphic = new HashMap<>();
		nodePath = new HashMap<>();
	}
	
	private void add(DisplayEntity entity) {
		MGraphicEntity graphic;
		
		System.out.println(entity);
		Rectangle r = entity
				.getShape()
				._1
				.getBounds();
		if(entity.haveImage()) {
			graphic = new GPictureEntity(r.getCenterX(), r.getCenterY(), entity.getImage());
		}
		else {
			graphic = new GRectEntity(r.getCenterX(),r.getCenterY(),r.getSize().height,r.getSize().width);
			((GRectEntity)graphic).setBackgroundColor(entity.getShape()._2);
			((GRectEntity)graphic).setLineColor(entity.getShape()._2);
		}
		
		view.addGraphicEntityAt(0,graphic);
		entityToGraphic.put(entity, graphic);
	}
	
	private void remove(DisplayEntity entity) {
		MGraphicEntity graphic = get(entity);
		view.removeGraphicEntity(graphic);
		entityToGraphic.remove(entity);
	}
	private void edit(DisplayEntity entity) {
		MGraphicEntity graphic = get(entity);
		graphic.setPosition(entity.getPosition().x, entity.getPosition().y);
	}
	
	private MGraphicEntity get(DisplayEntity entity) {
		return entityToGraphic.get(entity);
	}

	@Override
	public void run() {
		output();
	}
	
	private void output() {
		for(DisplayEntity entity : Game.getAll()) {
			if(entityToGraphic.containsKey(entity))	edit(entity);
			else add(entity);
		}
		
		HashMap<DisplayEntity, MGraphicEntity> list = new HashMap<DisplayEntity, MGraphicEntity>(entityToGraphic);
		for(DisplayEntity e1 : list.keySet()) {
			boolean isPresent = false;		
			if(Game.getAll().contains(e1)) {
				isPresent = true;
			}
			if(!isPresent)remove(e1);
		}
	}

}
