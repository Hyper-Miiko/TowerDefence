package fr.tm_nlm.tower_defence;

/**
 * Associe deux éléments ensemble, rien de plus.
 * @author Hyper Mïko
 *
 * @param <V1> Premier élément du couple
 * @param <V2> Second élément du couple
 */
public class Couple<V1, V2> {
	public final V1 _1;
	public final V2 _2;
	
	public Couple(V1 _1, V2 _2) {
		this._1 = _1;
		this._2 = _2;
	}
}
