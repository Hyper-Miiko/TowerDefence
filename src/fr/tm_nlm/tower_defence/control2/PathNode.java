package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Random;

import fr.tm_nlm.tower_defence.Couple;

/**
 * Chemins que vont suivre les monstres de manière quasi aléatoire, passant d'un point à l'autre
 * Les monstres préfèreront les chemins sans obstacle aux chemin avec obstacles
 * Si un monstre est engagé dans un chemin bouché il ne fera pas demi-tour
 * Certain monstre peuvent ignorer les obstacles
 * @author Hyper Mïko
 *
 */
public final class PathNode implements Localisable {
	private static Random random = new Random();
	
	private boolean end;
	private Vector position;
	private ArrayList<PathNode> nexts;
	
	{
		end = false;
		nexts = new ArrayList<>();
	}
	public PathNode(Vector position) {
		this.position = position;
	}
	public PathNode(Vector position, boolean end) {
		this.position = position;
		this.end = end;
	}
	public PathNode(double x, double y) {
		this.position = new Vector(x, y);
	}
	public PathNode(double x, double y, boolean end) {
		this.position = new Vector(x, y);
		this.end = end;
	}
	
	/**
	 * Ajoute un chemin possible à partir de ce points
	 * @param nextNode
	 */
	public void addWay(PathNode nextNode) {
		nexts.add(nextNode);
	}
	
	/**
	 * retourne le prochain point de passage à partir de ce point
	 * @param checkObstacle si on doit éviter les obstacles ou non
	 * @return
	 */
	public PathNode next(boolean checkObstacle) {
		if(checkObstacle && isPossibleWay()) {
			ArrayList<PathNode> possibleWays = possibleWays();
			return possibleWays.get(random.nextInt(possibleWays.size()));
		} else if(nexts.size() > 0){
			return nexts.get(random.nextInt(nexts.size()));
		} else {
			return null;
		}
		//return nexts.get(random.nextInt(nexts.size()));
	}
	private PathNode nextShortest(boolean checkObstacle) {
		if(checkObstacle && isPossibleWay()) {
			ArrayList<PathNode> possibleWays = possibleWays();
			return possibleWays.get(random.nextInt(possibleWays.size()));
		} else if(nexts.size() > 0){
			PathNode next = nexts.get(0);
			double dist = next.distToEnd(checkObstacle);
			for(int index = 1; index < nexts.size(); index++) {
				double tempDist = nexts.get(index).distToEnd(checkObstacle);
				if(tempDist < dist) {
					dist = tempDist;
					next = nexts.get(index);
				}
			}
			return next;
		} else {
			return null;
		}
	}

	private ArrayList<PathNode> possibleWays() {
		ArrayList<PathNode> possibleWays = new ArrayList<>();
		for(PathNode next : nexts) {
			if(next.isPossibleWay()) {
				possibleWays.add(next);
			}
		}
		return possibleWays;
	}
	
	private boolean isPossibleWay() {
		return  end || possibleWays().size() > 0;
	}

	@Override
	public Vector getPosition() {
		return position;
	}
	public boolean isEnd() {
		return end;
	}
	public double distToEnd(boolean checkObstacle) {
		if(isEnd()) {
			return 0;
		} else {
			PathNode next = nextShortest(checkObstacle);
			return getPosition().dist(next.getPosition()) + next.distToEnd(checkObstacle);
		}
	}
	@Override
	public Couple<Area, Color> getShape() {
		return null;
	}
	@Override
	public Angle getAngle() {
		return null;
	}
	@Override
	public boolean isFlying() {
		return false;
	}
}
