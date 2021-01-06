package fr.tm_nlm.tower_defence.view.mHUDCompat;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;
import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.entity.Tower;

public class ActionFromUser extends Thread {
	private static ActionFromUser instance = new ActionFromUser();
	public static ActionFromUser getInstance() {
		return instance;
	}
	
	private LinkedList<Couple<Tower, Action>> file;
	private ActionFromUser() {
		file = new LinkedList<>();
	}
	
	public void push(Couple<Tower, Action> action) {
		file.push(action);
	}

	@Override
	public void run() {
		if(file == null) {
			return;
		}
		Couple<Tower, Action> action = file.pop();
		switch(action._2) {
		case Evolve:
			((Tower) action._1).evolve();
			break;
		case Place:
			break;
		case Remove:
			break;
		default:
			throw new InternalError("J'ai oublié l'action " + action._2);
		}
	}
	
	private enum Action {
		Evolve,
		Place,
		Remove;
	}
}
