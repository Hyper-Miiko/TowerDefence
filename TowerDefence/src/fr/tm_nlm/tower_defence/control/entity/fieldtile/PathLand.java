package fr.tm_nlm.tower_defence.control.entity.fieldtile;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.entity.FieldTile;

public class PathLand extends FieldTile {
	private LinkedList<PathLand> connecteds;
	private PathLand passByToCastle;
	private double distToCastle;
	
	public PathLand(Field field, Vector position, Shape shape) {
		super(field, position, shape);
		connecteds = new LinkedList<>();
		passByToCastle = null;
		distToCastle = -1;
	}
	
	public void connectTo(PathLand pathLand) {
		double distToPathLand = getPosition().dist(pathLand.getPosition());
		if(distToCastle == -1 || distToPathLand + pathLand.distToCastle < distToCastle) {
			passBy(pathLand);
			connecteds.add(pathLand);
		}
	}
	
	public void passBy(PathLand pathLand) {
		double distToPathLand = getPosition().dist(pathLand.getPosition());
		distToCastle = distToPathLand + pathLand.distToCastle;
		passByToCastle = pathLand;
		for(PathLand connected : connecteds) {
			connected.refreshPath(this);
		}
	}
	
	private void refreshPath(PathLand pathLand) {
		if(passByToCastle == pathLand) {
			double distToPathLand = getPosition().dist(pathLand.getPosition());
			distToCastle = distToPathLand + pathLand.distToCastle;
			for(PathLand connected : connecteds) {
				distToPathLand = getPosition().dist(connected.getPosition());
				if(distToCastle == -1 || distToPathLand + pathLand.distToCastle < distToCastle) {
					passBy(connected);
				}
			}
		}
	}
}
