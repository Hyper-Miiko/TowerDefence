package fr.tm_nlm.tower_defence.control.data.geometric;
/**
 * Classe qui permet d'avoir des positions
 * @param x
 * @param y
 */
public class Vector {
	public final double x;
	public final double y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(Vector vector) {
		x = vector.x;
		y = vector.y;
	}
	
	/**
	 * Renvoie l'angle entre ce vector et l'autre entre -pi et pi
	 * @param vector
	 * @return
	 */
	public double angle(Vector vector) {
		Vector vectorA = new Vector(vector.x - x, vector.y - y);
		Vector vectorB = new Vector(1, 0);
		double normeA = vectorA.dist(new Vector(0, 0));
		double normeB = vectorB.dist(new Vector(0, 0));
		double scalaire = vectorA.x*vectorB.x + vectorA.y*vectorB.y;
		double cos = scalaire/normeA*normeB;
		double angle = Math.acos(cos);
		while(angle > Math.PI) {
			angle -= 2*Math.PI;
		}
		while(angle < -Math.PI) {
			angle += 2*Math.PI;
		}
		return Math.acos(cos);
	}
	
	/**
	 * renvoie un point en fonction de la position de ce point d'un angle et d'une longueur
	 * @param angle
	 * @param dist
	 * @return
	 */
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

	/**
	 * Retourne la position du point sur l'axe des x et des y
	 */
	@Override
	public String toString() {
		return "(" + Double.toString(x) + "," + Double.toString(y) + ")";
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Vector) {
			return x == ((Vector) object).x &&
				   y == ((Vector) object).y;
		}
		return false;
	}
}
