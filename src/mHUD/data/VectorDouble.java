package mHUD.data;


public class VectorDouble {
	public final double x;
	public final double y;
	
	public VectorDouble(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public VectorDouble(VectorDouble vector) {
		x = vector.x;
		y = vector.y;
	}
	
	public boolean equals(VectorDouble vector) {
		return x == vector.x && y == vector.y;
	}

	public String toString() {
		return "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
	}
}
