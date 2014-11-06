import java.util.ArrayList;
import java.util.HashMap;

public class Matriz{
	
	Nodo raiz;
	
	public Matriz(HashMap<Character, ArrayList<Integer>> mapa, int niveles) {
		
		Nodo.dimension = niveles;
		raiz = new Nodo(0, mapa.size()); 
	}
}
