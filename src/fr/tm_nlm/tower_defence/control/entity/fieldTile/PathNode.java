package fr.tm_nlm.tower_defence.control.entity.fieldTile;

import java.util.HashMap;
import java.util.HashSet;

import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;

/**
 * Un point de passage est un point de la carte par le quel peut passer les monstres.
 * Ils suivent les points connecté entre eux par des ponts en choisisant le pont le plus court.
 * @see fr.tm_nlm.tower_defence.control.entity.Monster
 * @author Hyper Mïko
 *
 */
public class PathNode extends Entity {
	private static final double radius = 20;
	private static final HashMap<Field, HashSet<PathNode>> allNodes = new HashMap<>();
	public static boolean canCreateNodeHere(Field field, Circle circle) {
		HashSet<PathNode> allNodesOfField = allNodes.get(field);
		if(allNodesOfField == null) {
			return true;
		} else {
			for(PathNode node : allNodesOfField) {
				if(circle.getPosition().dist(node.getPosition()) < node.getAppareances().getCircle().getRadius() ||
				   circle.getPosition().dist(node.getPosition()) < circle.getRadius()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean canCreateNodeHere(Field field, double posX, double posY) {
		return canCreateNodeHere(field, new Vector(posX, posY));
	}
	
	public static boolean canCreateNodeHere(Field field, Vector positions) {
		return canCreateNodeHere(field, new Circle(positions, radius));
	}
	
	private final HashSet<PathBridge> bridges;

	private boolean castle;
	private PathBridge passByToCastle;
	{
		bridges = new HashSet<>();
		passByToCastle = null;
	}
	public PathNode(Field field, Vector positions, boolean castle) {
		super(field, new Circle(positions, radius));
		Circle circle = new Circle(positions, radius);
		init(field, circle, castle);
	}
	
	public PathNode(Field field, double posX, double posY, boolean castle) {
		super(field, new Circle(new Vector(posX, posY), radius));
		Circle circle = new Circle(new Vector(posX, posY), radius);
		init(field, circle, castle);
	}
	
	public PathNode(Field field, Circle circle, boolean castle) {
		super(field, circle);
		init(field, circle, castle);
	}
	
	private void init(Field field, Circle circle, boolean castle) {
		if(!canCreateNodeHere(field, circle)) {
			throw new IllegalArgumentException("Impossible de créé deux noeuds aussi proche, merci d'utiliser canCreateNodeHere à l'avenir.");
		}
		this.castle = castle;
		if(castle) {
			passByToCastle = null;
		}
		
		HashSet<PathNode> allNodesOfField = allNodes.get(field);
		if(allNodesOfField == null) {
			allNodesOfField = new HashSet<>();
			allNodes.put(field, allNodesOfField);
		}
		allNodesOfField.add(this);
	}
	
	/**
	 * Ajoute une liaison entre se point de passage et un autre
	 * Si le nouveau chemin est plus interessant
	 * @param pathNode point de passage à lier au point courant
	 */
	public void bridgeTo(PathNode pathNode) {
		PathBridge bridge = new PathBridge(this, pathNode);
		bridges.add(bridge);
		if(castle)
			return;
		boolean haveNoWay = passByToCastle == null;
		boolean firstValidWay = !haveNoWay
								&& bridge.getDistToCastle(this) != null
								&& passByToCastle.getDistToCastle(this) == null;
		boolean shorterWay = !haveNoWay
							 && !firstValidWay
							 && bridge.getDistToCastle(this) != null
							 && passByToCastle.getDistToCastle(this) != null
							 && bridge.getDistToCastle(this) < passByToCastle.getDistToCastle(this);
		if(haveNoWay || firstValidWay || shorterWay) {
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
		passByToCastle = bridge;
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
		if(passByToCastle != null && passByToCastle.equals(bridge)) {
			passByToCastle = bridge;
			for(PathBridge currentBridge : bridges) {
				if(currentBridge.getDistToCastle(this) < passByToCastle.getDistToCastle(this)) {
					passBy(currentBridge);
				}
			}
		}
	}
	
	public boolean isCastle() {
		return castle;
	}
	
	public Double getDistToCastle() {
		if(castle) {
			return 0d;
		}
		if(passByToCastle == null) {
			return null;
		}
		return passByToCastle.getDistToCastle(this);
	}
	
	public PathBridge getBridgeToCastle() {
		return passByToCastle;
	}
	
	public String getWayToCastle() {
		String str = "";
		str += "<" + getPosition() + ", " + getDistToCastle() + ">\n";
		if(castle) {
			str += "Castle";
		} else if(passByToCastle == null) {
			str += "Aucun chemin trouvé.";
		} else {
			str += passByToCastle.otherNode(this).getWayToCastle();
		}
		return str;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof PathNode) {
			return getPosition().equals(((PathNode) object).getPosition());
		}
		return false;
	}
}