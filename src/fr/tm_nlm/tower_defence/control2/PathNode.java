package fr.tm_nlm.tower_defence.control2;

import java.util.ArrayList;
import java.util.Random;

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
}
