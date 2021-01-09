package fr.tm_nlm.tower_defence.control.entity;

import java.util.HashMap;
import java.util.HashSet;

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
	
	public static PathNode dummy(Vector vector) {
		return new PathNode(vector);
	}
	
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
	
	private static void link(PathNode from, PathNode to) {
		from.links.add(to);
	}
	
	private final HashSet<PathNode> links;

	private boolean castle;
	private PathNode passByToCastle;
	{
		links = new HashSet<>();
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
	
	private PathNode(Vector vector) {
		super(null, new Circle(vector, 1));
	}
	
	private void init(Field field, Circle circle, boolean castle) {
		if(!canCreateNodeHere(field, circle)) {
			throw new IllegalArgumentException("Impossible de créé deux noeuds aussi proche, merci d'utiliser canCreateNodeHere à l'avenir.");
		}
		this.castle = castle;
		if(castle) {
			passByToCastle = null;
		}
		getAppareances().setColor(100, 150, 0);
		
		HashSet<PathNode> allNodesOfField = allNodes.get(field);
		if(allNodesOfField == null) {
			allNodesOfField = new HashSet<>();
			allNodes.put(field, allNodesOfField);
		}
		allNodesOfField.add(this);
	}
	
	public void calcWay() {
		if(!castle) {
			throw new IllegalStateException("Can only be called on castle, and must be called on all castle.");
		}
		for(PathNode link : links) {
			link.calcWay(this);
		}
	}
	
	private void calcWay(PathNode messenger) {
		passByToCastle = messenger;
		double dist = getDistToCastle();
		for(PathNode link : links) {
			if(!link.equals(messenger)) {
				if(link.passByToCastle == null) {
					link.calcWay(this);
				} else if(link.getDistToCastle() > dist + getPosition().dist(link.getPosition())) {
					link.calcWay(this);
				}
			}
		}
	}
	
	/**
	 * Ajoute une liaison entre se point de passage et un autre
	 * Si le nouveau chemin est plus interessant
	 * @param pathNode point de passage à lier au point courant
	 */
	public void link(PathNode pathNode) {
		link(this, pathNode);
		link(pathNode, this);
	}
	
	public boolean isCastle() {
		return castle;
	}
	
	public Double getDistToCastle() {
		Double dist;
		if(castle) {
			dist = 0d;
		} else if(passByToCastle == null) {
			dist = null;
		} else {
			dist = passByToCastle.getDistToCastle() + getPosition().dist(passByToCastle.getPosition());
		}
		return dist;
	}
	
	public PathNode getNextToCastle() {
		return passByToCastle;
	}
	
	public String getWayToCastle() {
		String str = "";
		str += getPosition();
		if(castle) {
			str += ": Castle";
		} else if(passByToCastle == null) {
			str += ": Aucun chemin trouvé.";
		} else {
			str +=  " à " + passByToCastle.getDistToCastle() + "\n" + passByToCastle.getWayToCastle();
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
	
	@Override
	public String toString() {
		String str = super.toString();
		str += ": Point" + getPosition();
		if(castle) {
			str += " mène vers la victoire";
		} else if(passByToCastle != null) {
			str += " mène vers le Point n°" + passByToCastle.id() + " en " + passByToCastle.getPosition();
		}
		str += ".";
		return str;
	}
}