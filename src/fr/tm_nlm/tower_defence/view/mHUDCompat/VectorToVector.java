package fr.tm_nlm.tower_defence.view.mHUDCompat;

public class VectorToVector {
	public static mHUD.geometric.Vector fromTDVector(fr.tm_nlm.tower_defence.control.data.geometric.Vector from) {
		mHUD.geometric.Vector to = new mHUD.geometric.Vector(from.x, from.y);
		return to;
	}
}
