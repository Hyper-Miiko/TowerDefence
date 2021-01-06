package fr.tm_nlm.tower_defence.control.entity.fieldTile;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.entity.Tower;

/**
 * Les ponts de passage ne sont pas des entités ils ne sont que la représentation du chemin que les monstres peuvent prendre
 * @author Hyper Mïko
 *
 */
public class PathBridge {
	public final double length;
	public final Field field;
	public final PathNode pathNodeA;
	public final PathNode pathNodeB;
	public final LinkedList<Tower> obstacles;
	
	public PathBridge(PathNode pathNodeA, PathNode pathNodeB) {
		if(pathNodeA.equals(pathNodeB)) {
			throw new IllegalArgumentException("Il s'agit de deux fois le même noeud.");
		}
		if(pathNodeA.getField() != pathNodeB.getField()) {
			throw new IllegalArgumentException("Les deux noeuds de passage doivent appartenir à la même carte.");
		}
		if(pathNodeA == null || pathNodeB == null) {
			throw new IllegalArgumentException("PathNode" + ((pathNodeA == null) ? "A" : "B") + " est null.");
		}
		length = pathNodeA.getPosition().dist(pathNodeB.getPosition());
		field = pathNodeA.getField();
		this.pathNodeA = pathNodeA;
		this.pathNodeB = pathNodeB;
		obstacles = new LinkedList<>();
	}
	
	public PathNode otherNode(PathNode origin) {
		if(origin != pathNodeA && origin != pathNodeB) {
			throw new IllegalArgumentException("Le noeux fourni n'est pas une extrémité du pont.");
		}
		return (origin == pathNodeA) ? pathNodeB : pathNodeA;
	}
	
	public Double getDistToCastle(PathNode origin) {
		Double otherDist = otherNode(origin).getDistToCastle();
		return (otherDist == null) ? null : length + otherDist;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof PathBridge) {
			boolean a = ((PathBridge) object).pathNodeA.equals(pathNodeA) || ((PathBridge) object).pathNodeA.equals(pathNodeB);
			boolean b = ((PathBridge) object).pathNodeB.equals(pathNodeA) || ((PathBridge) object).pathNodeB.equals(pathNodeB);
			return a && b;
		}
		return false;
	}
}
