package fr.tm_nlm.tower_defence.control.data.geometric;

public class Vector {
	public final double x, y;
	
	/**
	 * Classe qui permet d'avoir la position sur l'axe des x et des y des monstres et des tours
	 * @param x
	 * @param y
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(Vector vector) {
		x = vector.x;
		y = vector.y;
	}
	
	public double angle(Vector vector) {
		Vector vectorA = new Vector(vector.x - x, vector.y - y);
		Vector vectorB = new Vector(1, 0);
		double normeA = vectorA.dist(new Vector(0, 0));
		double normeB = vectorB.dist(new Vector(0, 0));
		double scalaire = vectorA.x*vectorB.x + vectorA.y*vectorB.y;
		double cos = scalaire/normeA*normeB;
		return Math.acos(cos);
	}
	
	public Vector byAngle(double angle, double dist) {
		double x = this.x + Math.sin(angle)*dist;
		double y = this.y + Math.cos(angle)*dist;
		return new Vector(x, y);
	}
	
	/**
	 * Mesure la distance euclidienne entre 2 positions.
	 * @param vector
	 * @return
	 */
	public double dist(Vector vector) {
		return Math.sqrt(Math.pow(x - vector.x, 2) + Math.pow(y - vector.y, 2));
	}
	
	public boolean equals(Vector vector) {
		return x == vector.x && y == vector.y;
	}

	/**
	 * Retourne la position du point sur l'axe des x et des y
	 */
	@Override
	public String toString() {
		return "(" + Double.toString(x) + "," + Double.toString(y) + ")";
	}
}
