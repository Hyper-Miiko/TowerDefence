package mHUD.mGraphicEntity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import mHUD.StdDraw;
import mHUD.mObject.MItem;

//import fr.tm_nlm.tower_defence.Couple;

public class IGraphicView extends MItem {
//	private LinkedList<Couple<MGraphicEntity, Couple<Double,Double>>> savedEntity = new LinkedList<>();
	private ArrayList<LinkedList<MGraphicEntity>> entityList = new ArrayList<LinkedList<MGraphicEntity>>();
	private Image background = null;
	
	public int arraySize;
	
	private Graphics2D imageEdit;
	private BufferedImage imageBuffer;
	private java.awt.Color color = new Color(255,255,255);
	
	private boolean clicked = false;
	private boolean loseScreen = false;
	private int fading = 255;
	
	//l'ordre d'affichage est gérée par le nombre de buffer créer à l'initialisation (arraySize)
	public IGraphicView(double x, double y, int arraySize) {
		setSize(x,y);
		this.setNeedRedraw(true);
		imageBuffer = new BufferedImage((int)getSize().x-1, (int)getSize().y-1, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
		
		this.arraySize = arraySize;
		for(int i = 0; i < this.arraySize; i++) {
			entityList.add(new LinkedList<MGraphicEntity>());
		}
	}
	
	//En commentaire ici ce trouve le buffer à entité clické
	//Une fonctionnalité qui m'a été demandée mais qui n'a jamais
	//été utiliséee
	protected void refreshObject() {
			if(mousePressed() && !clicked) {
				clicked = true;
//				for(int i = 0; i < entityList.size(); i++) {
//					for(MGraphicEntity e : entityList.get(i)) {
//						if(e != null && e.isIn(mouseX(),mouseY())) {
//							savedEntity.add(new Couple<>(e, new Couple<>(mouseX(),mouseY())));
//						}
//					}
//				}
			}
			if(!mousePressed() && clicked) clicked = false;
	}
	
	//Le stdDraw fut modifié pour permetre d'envoyer un java.awt.BufferedImage
	protected void draw() {	
		
		if(background != null)imageEdit.drawImage(background,0,0, null);
		else imageEdit.fill(new Rectangle2D.Double(0, 0, getSize().x, getSize().y));

		
		for(int i = 0; i < entityList.size(); i++) {
			for(MGraphicEntity e : entityList.get(i)) {
				if(e != null && e.isDisplay())imageEdit.drawImage(e.getImage(),(int)e.getPosition().x,(int)e.getPosition().y, null);
			}
		}
		
		if(loseScreen == true) {
			imageEdit.setColor(new Color(20,0,0,255-fading));
			imageEdit.fill(new Rectangle2D.Double(0, 0, getSize().x, getSize().y));
			if(fading > 0)fading-=2;
		}

		
		StdDraw.picture(getPos().x/getWindowSize().x, getPos().y/getWindowSize().y, imageBuffer);
	}
	
	public void addGraphicEntityAt(int n,MGraphicEntity e) {
		removeGraphicEntity(e);
		entityList.get(n).add(e);
	}
	public void removeGraphicEntity(MGraphicEntity e) {
		for(int i = 0; i < entityList.size(); i++) {
			if(entityList.get(i).contains(e)) {
				entityList.get(i).remove(e);
				break;
			}
		}
		
	}
	
	public void setBackgroundColor(int r, int g, int b) {
		this.setBackgroundColor(new Color(r,g,b));
	}
	public void setBackgroundColor(Color c) {
		if(c != color) {
			setNeedRedraw(true);
			color = c;
		}
	}
	
	//position de la souris calculée par rapport à la position actuelle de IGraphicView
	public double mouseX() {
		return (StdDraw.mouseX()*getWindowSize().x/2)-getPos().x/2+getSize().x/2;
	}
	public double mouseY() {
		return Math.abs(StdDraw.mouseY()*getWindowSize().y/2 - getWindowSize().y/2)-Math.abs(getPos().y-getWindowSize().y/2);
	}
	public boolean mousePressed() {
		return  StdDraw.isMousePressed() && mouseIn();
	}
	
	//Il m'à été demandé à un moment de lister toutes les entitées qui se sont fait cliqué dessus
	//Voila donc une fonction qui indique si le buffer à entité est vide et 
	//une autre qui récupère la plus ancienne en la virant de la liste
	//Cette fonctionalité est inutilisée
//	public boolean haveActiveEntity() {
//		return !savedEntity.isEmpty();
//	}
//	public Couple<MGraphicEntity, Couple<Double,Double>> getActiveEntity() {
//		return savedEntity.pollFirst();
//	}

	
	public void setBackground(Image background) {
		this.background = background;
	}
	public void setLoseScreen() {
		loseScreen = true;
	}
	public boolean hasStoped() {
		return fading <= 0;
	}
}
