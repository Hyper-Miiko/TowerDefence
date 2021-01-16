package fr.tm_nlm.tower_defence.control2;

public class WaitingBool {
	private boolean value;
	public final String name;
	{
		value = false;
	}
	public WaitingBool(String name) {
		this.name = name;
	}
	public boolean get() {
		return value;
	}
	public void set(boolean value) {
		this.value = value;
	}
}
