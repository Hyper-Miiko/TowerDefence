package fr.tm_nlm.tower_defence.control.entity.fieldTile;

import java.util.HashSet;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.entity.FieldTile;

/**
 * Un point de passage est un point de la carte par le quel peut passer les monstres.
 * Ils suivent les points connecté entre eux par des ponts en choisisant le pont le plus court.
 * @see fr.tm_nlm.tower_defence.control.entity.Monster
 * @author Hyper Mïko
 *
 */
public class PathNode extends FieldTile {
	private final HashSet<PathBridge> bridges;
	/**
	 * Le pont a prendre ainsi que la distance total jusqu'à la fin du chemin
	 * _1 le pont
	 * _2 la distance
	 */
	private Couple<PathBridge, Double> passByToCastle;
	
	public PathNode(Field field, Shape shape, boolean castle) {
		super(field, shape);
		bridges = new HashSet<>();
		passByToCastle = null;
		if(castle) {
			passByToCastle = new Couple<>(null, -1d);
		}
	}
	
	/**
	 * Ajoute une liaison entre se point de passage et un autre
	 * Si le nouveau chemin est plus interessant
	 * @param pathNode point de passage à lier au point courant
	 */
	public void bridgeTo(PathNode pathNode) {
		PathBridge bridge = new PathBridge(this, pathNode);
		bridges.add(bridge);
		if(passByToCastle._2 == -1 || bridge.length + pathNode.passByToCastle._2 < passByToCastle._2) {
			passBy(bridge);
		}
	}
	
	/**
	 * Choisis le pont de passage comme étant le pont par le quel les monstre doivent passer aveuglément.
	 * Normalement le passage le plus court.
	 * @param bridge
	 */
	private void passBy(PathBridge bridge) {
		if(bridge == null) {
			throw new IllegalArgumentException("On ne peut pas passer par un pont inexistant.");
		}
		double distToCastle = bridge.length + bridge.getDistToCastle(this);
		passByToCastle = new Couple<>(bridge, distToCastle);
		for(PathBridge currentBridge : bridges) {
			currentBridge.otherNode(this).refreshPath(currentBridge);
		}
	}
	
	/**
	 * 
	 * @param pathLand
	 */
	private void refreshPath(PathBridge bridge) {
		if(bridge == null) {
			throw new IllegalArgumentException("On ne peut pas rafraichir un chemin inexistant.");
		}
		if(passByToCastle._1.equals(bridge)) {
			Double newDist = bridge.getDistToCastle(this);
			passByToCastle = new Couple<>(bridge, newDist);
			for(PathBridge currentBridge : bridges) {
				if(currentBridge.getDistToCastle(this) < passByToCastle._2) {
					passBy(currentBridge);
				}
			}
		}
	}
	
	public Field getField() {
		return field;
	}
	
	public double getDistToCastle() {
		return passByToCastle._2;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof PathNode) {
			return getPosition().equals(((PathNode) object).getPosition());
		}
		return false;
	}
}