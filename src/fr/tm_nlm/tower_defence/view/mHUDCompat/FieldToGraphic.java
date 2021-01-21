package fr.tm_nlm.tower_defence.view.mHUDCompat;

import java.awt.Color;
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
import fr.tm_nlm.tower_defence.control.entity.DisplayEntity;
import fr.tm_nlm.tower_defence.control.entity.Monster;
import fr.tm_nlm.tower_defence.control.entity.PathNode;
import fr.tm_nlm.tower_defence.control.entity.Tower;
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
	private HashMap<DisplayEntity, MGraphicEntity> entityToGraphic;
	private HashMap<PathNode, MGraphicEntity> nodePath;
	//private HashMap<MGraphicEntity, Entity> graphicToEntity;

	public FieldToGraphic(Field field, IGraphicView view) {
		this.field = field;
		this.view = view;
		entityToGraphic = new HashMap<>();
		nodePath = new HashMap<>();
	}
	
	private void add(DisplayEntity entity) {

		
		
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
		
		if(entity instanceof Tower) view.addGraphicEntityAt(4,graphic);
		if(entity instanceof Monster) view.addGraphicEntityAt(3,graphic);
		if(entity instanceof Bullet) view.addGraphicEntityAt(2,graphic);
		else if(entity instanceof PathNode) {
			view.addGraphicEntityAt(1,graphic);
			addPath((PathNode)entity);
		}
		
		entityToGraphic.put(entity, graphic);
	}
	
	private void addPath(PathNode p) {
		if(p.getNextToCastle() != null) {
			int sizeY = (int) p.getAppareances().getCircle().getRadius();
			int sizeX = (int) p.getPosition().dist(p.getNextToCastle().getPosition());
			int posX = (int) Math.abs(p.getPosition().x-(p.getPosition().x-p.getNextToCastle().getPosition().x)/2);
			int posY = (int) Math.abs(p.getPosition().y-(p.getPosition().y-p.getNextToCastle().getPosition().y)/2);
			double roation = p.getPosition().angle(p.getNextToCastle().getPosition());
			
			GRectEntity graphic = new GRectEntity(posX,posY,sizeX,sizeY);
			graphic.rotate(roation);
			graphic.setLineColor(0,150,0);
			graphic.setBackgroundColor(new Color(0,150,0,100));
			
			view.addGraphicEntityAt(0,graphic);
			nodePath.put(p, graphic);
		}
	}
	
	private void remove(DisplayEntity entity) {
		if(!field.equals(entity.getField())) {
			throw new IllegalArgumentException("L'entité n'appartient pas au bon champ.");
		}
		MGraphicEntity graphic = get(entity);
		view.removeGraphicEntity(graphic);
		entityToGraphic.remove(entity);
		
		if(entity instanceof PathNode) {
			graphic = nodePath.get(entity);
			view.removeGraphicEntity(graphic);
			nodePath.remove(entity);
		}
		/*return graphic != null;*/
	}
	private void edit(DisplayEntity entity) {
		if(!field.equals(entity.getField())) {
			throw new IllegalArgumentException("L'entité n'appartient pas au bon champ.");
		}
		
		MGraphicEntity graphic = get(entity);
		graphic.setPosition(entity.getPosition().x, entity.getPosition().y);
	}
	
	private MGraphicEntity get(DisplayEntity entity) {
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
		for(DisplayEntity entity : field.getEntities()) {
			if(entityToGraphic.containsKey(entity))	edit(entity);
			else add(entity);
		}
		
		HashMap<DisplayEntity, MGraphicEntity> list = new HashMap<DisplayEntity, MGraphicEntity>(entityToGraphic);
		for(DisplayEntity e1 : list.keySet()) {
			boolean isPresent = false;		
			if(field.getEntities().contains(e1)) {
				isPresent = true;
			}
			if(!isPresent)remove(e1);
		}
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
