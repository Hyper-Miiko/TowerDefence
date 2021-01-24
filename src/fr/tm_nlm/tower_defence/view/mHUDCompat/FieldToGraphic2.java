package fr.tm_nlm.tower_defence.view.mHUDCompat;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

import fr.tm_nlm.tower_defence.control2.DisplayEntity;
import fr.tm_nlm.tower_defence.control2.Game;
import mHUD.mGraphicEntity.CooldownBar;
import mHUD.mGraphicEntity.GCircleEntity;
import mHUD.mGraphicEntity.GMonsterEntity;
import mHUD.mGraphicEntity.GPictureEntity;
import mHUD.mGraphicEntity.IGraphicView;
import mHUD.mGraphicEntity.LifeBar;
import mHUD.mGraphicEntity.MGraphicEntity;

/*
 * 2e version de FtG qui conserva son nom alors que le "field" avais disparu
 * son objectif est de connecter le modèle à la vue
 */

public class FieldToGraphic2 extends Thread {
	private IGraphicView view;
	private HashMap<DisplayEntity, MGraphicEntity> entityToGraphic = new HashMap<>();
	private HashMap<DisplayEntity, LifeBar> lifeBar = new HashMap<>();
	private HashMap<DisplayEntity, ArrayList<CooldownBar>> cooldownBar = new HashMap<>();
	private HashMap<DisplayEntity, GCircleEntity> range = new HashMap<>();
	
	private GPictureEntity background = null;
	private String actualBackground = null;
	private boolean displaySlot = false;
	
	public FieldToGraphic2(IGraphicView view) {
		this.view = view;
	}
	
	/*
	 * Le run fonctionne de la façon suivante :
	 * Si j'ai un DisplayEntity sans MGraphicEntity, je crée un ensemble de MGraphicEntity (add)
	 * Si j'ai des MGraphicEntity sans DisplayEntity, je supprime les MGraphicEntity (delete)
	 * Et lorsque j'ai un DisplayEntity qui corespond à un MGraphicEntity, je met à jour le MGraphicEntity (edit)
	 */
	public void run() {
		//ici je vérifie bien que le background n'a pas changer
		if((actualBackground == null && Game.getBackground() != null) || !actualBackground.equals(Game.getBackground())) {		
			background = new GPictureEntity(view.getSize().x/2,view.getSize().y/2,Game.getBackground());
			view.addGraphicEntityAt(0, background);
			actualBackground = Game.getBackground();
		}
		
		//partie sur le add / edit
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
		
		//partie sur le remove
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
	
	private void add(DisplayEntity entity) {
		MGraphicEntity graphic;
		
		Rectangle r = entity.getShape()._1.getBounds();
		if(entity.haveImage()) {
			if(entity.getClassName().equals("Monster"))graphic = new GMonsterEntity(r.getCenterX(), r.getCenterY(), entity.getImage());
			else graphic = new GPictureEntity(r.getCenterX(), r.getCenterY(), entity.getImage());
		}
		else {
			graphic = new GCircleEntity(r.getCenterX(), r.getCenterY(),r.getSize().height/2);
			((GCircleEntity)graphic).setBackgroundColor(entity.getShape()._2);
			if(entity.isSlot())((GCircleEntity)graphic).setLineColor(new Color(255,255,255));
		}
		if(entity.getAngle() != null)graphic.rotate(entity.getAngle().value());
		
		if(entity.haveRange()) {
			GCircleEntity i = new GCircleEntity(r.getCenterX(), r.getCenterY(),entity.getRange()+1);
			i.setBackgroundColor(new Color(255,255,255,100));
			i.setLineColor(255,255,255);
			range.put(entity, i);
			i.setDisplay(false);
			view.addGraphicEntityAt(entity.getPriority(),i);
		}
		
		view.addGraphicEntityAt(entity.getPriority(),graphic);
		graphic.setDisplay(entity.isOnScreen());
		entityToGraphic.put(entity, graphic);
		
		if(entity.isDamageable()) {
			LifeBar l;
			
			if(entity.haveRange()) {
				ArrayList<CooldownBar> list = new ArrayList<CooldownBar>();
				for(int i = 0; i < entity.getCooldowns().size(); i++ ) {
					CooldownBar b = new CooldownBar(r.getCenterX(), r.getCenterY(), r.getHeight()*1.5+i*5+15 ,50,5,entity.getCooldowns().get(i));
					b.setDisplay(entity.isOnScreen());
					view.addGraphicEntityAt(entity.getPriority(),b);
					list.add(b);
				}
				cooldownBar.put(entity, list);
				l = new LifeBar(r.getCenterX(), r.getCenterY(), r.getHeight()*1.5 ,50,10,entity.getHealth());
			}
			else l = new LifeBar(r.getCenterX(), r.getCenterY(), r.getHeight()*0.75 ,50,10,entity.getHealth());
			
			lifeBar.put(entity, l);
			l.setDisplay(entity.isOnScreen());
			view.addGraphicEntityAt(entity.getPriority(),l);
		}
		
	}
	private void remove(DisplayEntity entity) {
		MGraphicEntity graphic = getEntity(entity);
		view.removeGraphicEntity(graphic);
		entityToGraphic.remove(entity);
		
		LifeBar l = getLifeBar(entity);
		if(l != null) {
			view.removeGraphicEntity(l);
			lifeBar.remove(entity);
		}
		GCircleEntity r = range.get(entity);
		if(r != null) {
			view.removeGraphicEntity(r);
			range.remove(entity);
		}
		ArrayList<CooldownBar> c = getAllCooldownBar(entity);
		if(c != null) {
			for(int i = 0; i < c.size(); i++ ) {
				view.removeGraphicEntity(c.get(i));
			}
			cooldownBar.remove(entity);
		}
	}
	private void edit(DisplayEntity entity) {
		MGraphicEntity graphic = getEntity(entity);
		Rectangle r = entity.getShape()._1.getBounds();	
		graphic.setPosition(r.getCenterX(), r.getCenterY());
		if(entity.getAngle() != null) graphic.rotate(entity.getAngle().value());
		
		if(entity.isSlot()) graphic.setDisplay(displaySlot);
		else graphic.setDisplay(entity.isOnScreen());
		
		if(graphic instanceof GCircleEntity) {
			((GCircleEntity)graphic).setBackgroundColor(entity.getShape()._2);
		}
		
		LifeBar l = getLifeBar(entity);
		if(l != null) {
			l.setPosition(r.getCenterX(), r.getCenterY());
			l.setLife(entity.getHealth());
			l.setDisplay(entity.isOnScreen());
		}
		ArrayList<CooldownBar> c = getAllCooldownBar(entity);
		if(c != null) {
			for(int i = 0; i < c.size(); i++ ) {
				c.get(i).setCooldown(entity.getCooldowns().get(i));
				c.get(i).setPosition(r.getCenterX(), r.getCenterY());
				c.get(i).setDisplay(entity.isOnScreen());
			}
		}
	}
	
	//Car deux appels de Game.getAll() me donne deux collections différentes de mêmes items mais avec d'autres références
	//Aussi je ne peux pas faire directement de .get sur mes hashmap...
	//Par ailleurs je manque de temps mais après avoir trié tout ça je me
	//suis rendu compte que range ne supprime pas et ne rafraichis pas ses GCircleEntity
	//car la fonction qui le permetrais n'est pas présente si dessous
	
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
	private ArrayList<CooldownBar> getAllCooldownBar(DisplayEntity entity) {
		for(DisplayEntity e1 : cooldownBar.keySet()) {
			if(entity.equals(e1))return cooldownBar.get(e1);
		}
		return null;
	}

	//Fonction permettant de chacher ou non certains éléments
	public void setSlotDisplay(boolean d) {
		displaySlot = d;
	}
	public void setGraphicRangeDisplay(long id, boolean v) {
		for(DisplayEntity e1 : range.keySet()) {
			if(e1.getId() == id) {
				range.get(e1).setDisplay(v);
			}
			else range.get(e1).setDisplay(false);
		}
	}
}
