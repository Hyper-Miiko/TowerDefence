package fr.tm_nlm.tower_defence.control2;

import fr.tm_nlm.tower_defence.NotYetImplementedException;

public class Identifiable {
	private static long nextId = 1;
	
	private final long id;
	
	{
		id = nextId++;
	}
	
	public long getId() {
		return id;
	}

	public void setQuote(String string) {
		throw new NotYetImplementedException("No cool sentence here.");
	}

	public void setSound(String sound) {
		throw new NotYetImplementedException("No cool sound here.");
	}
}
