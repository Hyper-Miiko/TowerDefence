package fr.tm_nlm.tower_defence.control2;

public class Identifiable {
	private static long nextId = 1;
	
	private final long id;
	
	{
		id = nextId++;
	}
	
	public long getId() {
		return id;
	}
}
