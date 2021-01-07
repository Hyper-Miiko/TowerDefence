package fr.tm_nlm.tower_defence.view.mHUDCompat;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.tm_nlm.tower_defence.Couple;
import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.Field.Action;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;

import mHUD.mGraphicEntity.GCircleEntity;
import mHUD.mGraphicEntity.GPictureEntity;
import mHUD.mGraphicEntity.GRectEntity;
import mHUD.mGraphicEntity.IGraphicView;
import mHUD.mGraphicEntity.MGraphicEntity;

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
	private HashMap<Entity, MGraphicEntity> entityToGraphic;
	private HashMap<MGraphicEntity, Entity> graphicToEntity;
	
	{
		entityToGraphic = new HashMap<>();
		graphicToEntity = new HashMap<>();
	}
	
	public FieldToGraphic(Field field, IGraphicView view) {
		this.field = field;
		this.view = view;
	}
	
	public void add(Entity entity) {

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
		graphicToEntity.put(graphic, entity);
	}
	
	public boolean remove(Entity entity) {
		if(!field.equals(entity.getField())) {
			throw new IllegalArgumentException("L'entité n'appartient pas au bon champ.");
		}
		MGraphicEntity graphic = entityToGraphic.get(entity);
		view.removeGraphicEntity(graphic);
		entityToGraphic.remove(entity);
		graphicToEntity.remove(graphic);
		return graphic != null;
	}
	
	public Entity get(MGraphicEntity graphic) {
		return graphicToEntity.get(graphic);
	}
	
	public MGraphicEntity get(Entity entity) {
		if(!field.equals(entity.getField())) {
			throw new IllegalArgumentException("L'entité n'appartient pas au bon champ.");
		}
		return entityToGraphic.get(entity);
	}

	@Override
	public void run() {
		while(true) {
			working();
			output();
			input();
			waiting();
		}
	}
	
	private void output() {
		HashSet<Entity> update = new HashSet<>();
		for(Map.Entry<Entity, MGraphicEntity> entry : entityToGraphic.entrySet()) {
			if(!entry.getKey().isCheck()) {
				update.add(entry.getKey());
				entry.getKey().check();
			}
		}
		for(Entity entity : update) {
			add(entity);
			remove(entity);
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
		view.setActive(false);
		field.setActiv(false);
		while(view.isRunning() || field.isRunning());
	}
	
	private void waiting() {
		view.setActive(true);
		field.setActiv(true);
	}

}
