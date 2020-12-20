package mHUD.variable;

public class Color {
	public final int Red;
	public final int Green;
	public final int Blue;
	
	public Color(int r, int g, int b) {
		if(r >= 0 && r <= 255)Red = r;
		else throw new IllegalArgumentException("Red isn't between 0 and 255");
		if(r >= 0 && r <= 255)Green = g;
		else throw new IllegalArgumentException("Green isn't between 0 and 255");
		if(r >= 0 && r <= 255)Blue = b;
		else throw new IllegalArgumentException("Blue isn't between 0 and 255");
	}
}
