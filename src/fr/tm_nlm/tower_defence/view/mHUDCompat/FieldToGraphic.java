package fr.tm_nlm.tower_defence.view.mHUDCompat;

import java.math.*;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.tm_nlm.tower_defence.Couple;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.Field.Action;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;
import fr.tm_nlm.tower_defence.control.entity.Bullet;
import fr.tm_nlm.tower_defence.control.entity.Entity;
import fr.tm_nlm.tower_defence.control.entity.Monster;
import fr.tm_nlm.tower_defence.control.entity.PathNode;
import mHUD.mGraphicEntity.GCircleEntity;
import mHUD.mGraphicEntity.GPictureEntity;
import mHUD.mGraphicEntity.GRectEntity;
import mHUD.mGraphicEntity.IGraphicView;
import mHUD.mGraphicEntity.MGraphicEntity;
import mHUD.mObject.MWindow;

import static fr.tm_nlm.tower_defence.control.Field.Action.*;

/**
 * GRectEntity
 * GCircleEntity
 * GImageEntity
 * @author Hyper Mïko
 *
 */
public class FieldToGraphic extends Thread {
	private Field field;
	private IGraphicView view;
	private MWindow win;
	private HashMap<Entity, MGraphicEntity> entityToGraphic;
	private HashMap<PathNode, MGraphicEntity> nodePath;
	//private HashMap<MGraphicEntity, Entity> graphicToEntity;

	public FieldToGraphic(Field field, IGraphicView view,MWindow win) {
		this.field = field;
		this.view = view;
		this.win = win;
		entityToGraphic = new HashMap<>();
		nodePath = new HashMap<>();
	}
	
	private void add(Entity entity) {

		if(!field.equals(entity.getField())) {
			throw new IllegalArgumentException("L'entité n'appartient pas au bon champ.");
		}
		
		MGraphicEntity graphic;
		/*if(entity.getAppareances().getCurrentImage() != null) {
			graphic = new GPictureEntity(entity.getPosition().x, entity.getPosition().y,entity.getAppareances().getRect().getSize().x,entity.getAppareances().getRect().getSize().y, entity.getAppareances().getCurrentImage());
		} else */if(entity.getAppareances().isCircle()) {
			graphic = new GCircleEntity(entity.getPosition().x, entity.getPosition().y, entity.getAppareances().getCircle().getRadius());
			((GCircleEntity)graphic).setBackgroundColor(entity.getAppareances().getColor());
			((GCircleEntity)graphic).setLineColor(entity.getAppareances().getColor());
		} else {
			graphic = new GRectEntity(entity.getPosition().x, entity.getPosition().y, entity.getAppareances().getRect().getSize().x, entity.getAppareances().getRect().getSize().y);
			((GRectEntity)graphic).setBackgroundColor(entity.getAppareances().getColor());
			((GRectEntity)graphic).setLineColor(entity.getAppareances().getColor());
		}
		
		view.addGraphicEntityAt(graphic);
		entityToGraphic.put(entity, graphic);
		
		//if(entity instanceof PathNode) addPath((PathNode)entity);
	}
	
	private void addPath(PathNode p) {
		if(p.getNextToCastle() != null) {
			int sizeY = (int) p.getAppareances().getCircle().getRadius();
			int sizeX = (int) p.getPosition().dist(p.getNextToCastle().getPosition());
			int posX = (int) (p.getPosition().x);
			int posY = (int) (p.getPosition().y);
			double roation = p.getPosition().angle(p.getNextToCastle().getPosition());
			
			GRectEntity graphic = new GRectEntity(posX,posY,sizeX,sizeY);
			graphic.rotate(roation);
			graphic.setLineColor(255,255,255);
			
			view.addGraphicEntityAt(graphic);
			nodePath.put(p, graphic);
		}
	}
	
	private void remove(Entity entity) {
		if(!field.equals(entity.getField())) {
			throw new IllegalArgumentException("L'entité n'appartient pas au bon champ.");
		}
		MGraphicEntity graphic = entityToGraphic.get(entity);
		view.removeGraphicEntity(graphic);
		entityToGraphic.remove(entity);
		
		/*if(entity instanceof PathNode) {
			graphic = nodePath.get(entity);
			view.removeGraphicEntity(graphic);
			nodePath.remove(entity);
		}
		return graphic != null;*/
	}
	private void edit(Entity entity) {
		if(!field.equals(entity.getField())) {
			throw new IllegalArgumentException("L'entité n'appartient pas au bon champ.");
		}
		
		MGraphicEntity graphic = entityToGraphic.get(entity);
		graphic.setPosition(entity.getPosition().x, entity.getPosition().y);
	}
	
	private MGraphicEntity get(Entity entity) {
		if(!field.equals(entity.getField())) {
			throw new IllegalArgumentException("L'entité n'appartient pas au bon champ.");
		}
		return entityToGraphic.get(entity);
	}

	@Override
	public void run() {
		output();
		/*while(true) {
			working();
			output();
			waiting();
		}*/
	}
	
	private void output() {
		for(Entity entity : field.getEntities()) {
			MGraphicEntity graphic = this.get(entity);
			
			if(entityToGraphic.containsKey(entity))	edit(entity);
			else add(entity);
		}
		
		HashMap<Entity, MGraphicEntity> list = new HashMap<Entity, MGraphicEntity>(entityToGraphic);
		for(Entity e1 : list.keySet()) {
			boolean isPresent = false;

			System.out.println(e1);
			
			if(field.getEntities().contains(e1)) {
				isPresent = true;
			}
			if(!isPresent)remove(e1);
		}
		System.out.println();
	}
	
	/*private void working() {
		win.setActive(false);
		field.setActive(false);
		
		while(field.isRunning() || win.isRunning());
		
		field.suspend();
		win.suspend();
	}
	
	private void waiting() {
		win.setActive(true);
		field.setActive(true);
		
		field.resume();
		win.resume();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("FTG.waiting.catchError");
			e.printStackTrace();
		}
	}*/

}
