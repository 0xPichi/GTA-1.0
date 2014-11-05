import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class Generador {

	/**
	 *  Lee un texto y lo almacena en un objeto de la clase StringBuilder
	 * @param nombre
	 * @return StringBuilder
	 */
	public static StringBuilder leerTexto(String nombre){
		
		StringBuilder txt = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(nombre)));
			
			String leo;
			
			while((leo = br.readLine()) != null){
				
				txt.append(leo).append("\n");		
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return txt;
	}

	/**
	 * Crea un HashMap contanto el numero de veces que se repite un caracter
	 * @param texto 
	 * @return HashMap<Character, Integer>
	 */
	private static HashMap<Character, Integer> crearHash(StringBuilder texto) {

		HashMap<Character, Integer> mapa = new HashMap<Character, Integer>();
		
		for(int i = 0; i<texto.length(); i++){
			
			try{
				//Suma 1 al total de veces que se repite ese caracter (mapa.put('caracter', veces + 1))
				mapa.put(texto.charAt(i), mapa.get(texto.charAt(i)) + 1);
				
			}catch(NullPointerException e){
				
				//El caracter aparece por primera vez, veces = 1
				
				mapa.put(texto.charAt(i), 1);
			}
		}
		return mapa;
	}
	
	/**
	 * Metodo que recibe un StringBuilder y un HashMap, en el cual estan las referencias de cada caracter y las veces que se repite,
	 * para despues buscar en el StringBuilder las posiciones en las que esta cada caracter
	 * @param texto
	 * @param map
	 * @return HashMap<Character, ArrayList<Integer>>, Por ejemplo: caracter 'a', posiciones {23, 45, 103}
	 */
	private static HashMap<Character, ArrayList<Integer>> charPositions(StringBuilder texto, HashMap<Character, Integer> map){
		
		HashMap<Character, ArrayList<Integer>> mapPosition = new HashMap<Character, ArrayList<Integer>>();
		
		/* Recorre cada entrada del mapa de caracteres */
		for(Entry<Character, Integer> a : map.entrySet()){
			
			/* Cadena que va a almacenar las posiciones */
			ArrayList<Integer> chain = new ArrayList<Integer>();
			
			/* Una vez que se tiene el caracter, recorre el StringBuilder en busca de las posiciones en las que se encuentra */
			for(int i = 0; i < texto.length(); i++){
				
				/* Si en la posicion "i" encuentra el caracter "a.getKey()", añade esa posicion al la cadena */
				if(texto.charAt(i) == a.getKey()){
					
					chain.add(i);
				}
			}
			mapPosition.put(a.getKey(), chain);
		}		
		return mapPosition;
	}
	
	public static void main(String[] args) {
		
		//StringBuilder texto = leerTexto("galdos.txt");
		
		//HashMap<Character, Integer> mapa = crearHash(texto);
		//HashMap<Character, ArrayList<Integer>> mapita = charPositions(texto, mapa);
		
		/*Matriz a = new Matriz(null, mapa, '\u0000');
		Matriz b = new Matriz(a, null, 'd'); 
		Matriz c = new Matriz(b, null, 't');*/			

		long startTime = System.currentTimeMillis();
		
		Nodo.dimension = 5;
		Nodo raiz = new Nodo(1, 79);

		long endTime = System.currentTimeMillis();
		System.out.println("Tiempo: "+(endTime - startTime) + " ms");

	}

}
