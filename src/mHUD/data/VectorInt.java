package mHUD.data;


public class VectorInt {
	public final int x;
	public final int y;
	
	public VectorInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public VectorInt(VectorInt vector) {
		x = vector.x;
		y = vector.y;
	}
	
	public boolean equals(VectorInt vector) {
		return x == vector.x && y == vector.y;
	}

	public String toString() {
		return "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
	}
}
