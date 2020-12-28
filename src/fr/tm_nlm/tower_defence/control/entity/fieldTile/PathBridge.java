package fr.tm_nlm.tower_defence.control.entity.fieldTile;

import fr.tm_nlm.tower_defence.control.Field;

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
	
	public PathBridge(PathNode pathNodeA, PathNode pathNodeB) {
		if(pathNodeA.equals(pathNodeB)) {
			throw new IllegalArgumentException("Il s'agit de deux fois le même noeud.");
		}
		if(pathNodeA.getField() != pathNodeB.getField()) {
			throw new IllegalArgumentException("Les deux noeuds de passage doivent appartenir à la même carte.");
		}
		length = pathNodeA.getPosition().dist(pathNodeB.getPosition());
		field = pathNodeA.getField();
		this.pathNodeA = pathNodeA;
		this.pathNodeB = pathNodeB;
	}
	
	public PathNode otherNode(PathNode origin) {
		if(origin != pathNodeA && origin != pathNodeB) {
			throw new IllegalArgumentException("Le noeux fourni n'est pas une extrémité du pont.");
		}
		return (origin == pathNodeA) ? pathNodeA : pathNodeB;
	}
	
	public double getDistToCastle(PathNode origin) {
		return otherNode(origin).getDistToCastle() + length;
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
