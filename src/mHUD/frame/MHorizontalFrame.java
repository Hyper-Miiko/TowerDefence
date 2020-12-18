package mHUD.frame;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import mHUD.MObject;

public class MHorizontalFrame extends MFrame 
{
	public void recalculateUp() {
		Vector v;
		double vx = 0;
		double vy = 0;
		
		for(MObject c : child) {
			vx += c.getSx();
			vy = Math.max(c.getSy(), vy);
			
		}
		
		v = new Vector(vx, vy);
		
		this.setSize(v);
		
		super.recalculateUp();
	}
	
	@Override
	public void recalculateDown() {
		if(mother == null)this.setPos(0.5,0.5);
		float sumX = 0;
		
		for(MObject c : child) {
			c.setPos(this.getPx()-this.getSx()+c.getSx()+sumX*2,this.getPy());
			sumX+=c.getSx();
		}
		
		super.recalculateDown();
	}

}
