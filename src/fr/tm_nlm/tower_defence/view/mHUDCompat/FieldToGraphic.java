package fr.tm_nlm.tower_defence.view.mHUDCompat;

import java.util.HashMap;
import java.util.Map;

import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;
import mHUD.mGraphicEntity.GCircleEntity;
import mHUD.mGraphicEntity.GPictureEntity;
import mHUD.mGraphicEntity.GRectEntity;
import mHUD.mGraphicEntity.MGraphicEntity;

/**
 * GRectEntity
 * GCircleEntity
 * GImageEntity
 * @author Hyper Mïko
 *
 */
public class FieldToGraphic extends Thread {
	private Field field;
	private HashMap<Entity, MGraphicEntity> entityToGraphic;
	private HashMap<MGraphicEntity, Entity> graphicToEntity;
	
	{
		entityToGraphic = new HashMap<>();
		graphicToEntity = new HashMap<>();
	}
	
	public FieldToGraphic(Field field) {
		this.field = field;
	}
	
	public void add(Entity entity) {
		if(!field.equals(entity.getField())) {
			throw new IllegalArgumentException("L'entité n'appartient pas au bon champ.");
		}
		MGraphicEntity graphic;
		if(entity.getAppareances().getCurrentImage() != null) {
			//XXX Wait for GImageEntity setup
			graphic = new GPictureEntity(entity.getPosition().x, entity.getPosition().y,entity.getAppareances().getRect().getSize().x,entity.getAppareances().getRect().getSize().y, entity.getAppareances().getCurrentImage());
		} else if(entity.getAppareances().isCircle()) {
			graphic = new GCircleEntity(entity.getPosition().x, entity.getPosition().y, entity.getAppareances().getCircle().getRadius());
		} else {
			graphic = new GRectEntity(entity.getPosition().x, entity.getPosition().y, entity.getAppareances().getRect().getSize().x, entity.getAppareances().getRect().getSize().y);
		}
		entityToGraphic.put(entity, graphic);
		graphicToEntity.put(graphic, entity);
	}
	
	public boolean remove(Entity entity) {
		if(!field.equals(entity.getField())) {
			throw new IllegalArgumentException("L'entité n'appartient pas au bon champ.");
		}
		MGraphicEntity graphic = entityToGraphic.get(entity);
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
		for(Map.Entry<Entity, MGraphicEntity> entry : entityToGraphic.entrySet()) {
			if(!entry.getKey().isCheck()) {
				remove(entry.getKey());
				add(entry.getKey());
				entry.getKey().check();
			}
		}
	}

}
