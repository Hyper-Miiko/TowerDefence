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
import fr.tm_nlm.tower_defence.control.entity.Entity;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathNode;
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
		if(entity.getAppareances().getCurrentImage() != null) {
			graphic = new GPictureEntity(entity.getPosition().x, entity.getPosition().y,entity.getAppareances().getRect().getSize().x,entity.getAppareances().getRect().getSize().y, entity.getAppareances().getCurrentImage());
		} else if(entity.getAppareances().isCircle()) {
			graphic = new GCircleEntity(entity.getPosition().x, entity.getPosition().y, entity.getAppareances().getCircle().getRadius());
		} else {
			graphic = new GRectEntity(entity.getPosition().x, entity.getPosition().y, entity.getAppareances().getRect().getSize().x, entity.getAppareances().getRect().getSize().y);
		}
		
		view.addGraphicEntity(graphic);
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
			
			view.addGraphicEntity(graphic);
			nodePath.put(p, graphic);
		}
	}
	
	private boolean remove(Entity entity) {
		if(!field.equals(entity.getField())) {
			throw new IllegalArgumentException("L'entité n'appartient pas au bon champ.");
		}
		MGraphicEntity graphic = entityToGraphic.get(entity);
		view.removeGraphicEntity(graphic);
		entityToGraphic.remove(entity);
		
		if(entity instanceof PathNode) {
			graphic = nodePath.get(entity);
			view.removeGraphicEntity(graphic);
			nodePath.remove(entity);
		}
		return graphic != null;
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
		//while(true) {
			//working();
			
			output();
			
			input();
			
			//waiting();
		//}
	}
	
	private void output() {
		for(Entity e : field.getEntities()) {
			MGraphicEntity g = this.get(e);
			
			if(entityToGraphic.containsKey(e)) {
				
				if(g == null) remove(e);
				else edit(e);
			}
			else {
				add(e);
			}
		}
	}
	
	private void input() {
		boolean empty;
		//TODO attente de l'action coté vue
		
		/*working();
		Couple<MGraphicEntity, Couple<Double, Double>> graphicAction = view.getActiveEntity();
		Action action;
		Object target;
		if(graphicAction._1 == null) {
			
		}
		Couple<Action, Object> fieldAction = new Couple<>(action, target);
		field.workOn(fieldAction);
		waiting();*/
	}
	
	private void working() {
		win.setActive(false);
		field.setActiv(false);
		
		System.out.println("	IN");
		while(field.isRunning());
		System.out.println("	OUT");
		
		System.out.println("	IN");
		while(win.isRunning());
		System.out.println("	OUT");
		
		field.suspend();
		win.suspend();
	}
	
	private void waiting() {
		win.setActive(true);
		field.setActiv(true);
		
		field.resume();
		win.resume();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("FTG.waiting.catchError");
			e.printStackTrace();
		}
	}

}
