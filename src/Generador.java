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
	 * Lee un texto y lo almacena en un objeto de la clase StringBuilder
	 * 
	 * @param nombre
	 * @return StringBuilder
	 */
	public static StringBuilder leerTexto(String nombre) {

		StringBuilder txt = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					nombre)));

			String leo;

			while ((leo = br.readLine()) != null) {

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
	 * 
	 * @param texto
	 * @return HashMap<Character, Integer>
	 */
	private static HashMap<Character, Integer> crearHash(StringBuilder texto) {

		HashMap<Character, Integer> mapa = new HashMap<Character, Integer>();

		for (int i = 0; i < texto.length(); i++) {

			try {
				// Suma 1 al total de veces que se repite ese caracter
				// (mapa.put('caracter', veces + 1))
				if ((int) texto.charAt(i) > 31) {
					mapa.put(texto.charAt(i), mapa.get(texto.charAt(i)) + 1);
				}

			} catch (NullPointerException e) {

				// El caracter aparece por primera vez, veces = 1

				mapa.put(texto.charAt(i), 1);
			}
		}
		return mapa;
	}

	/**
	 * Metodo que recibe un StringBuilder y un HashMap, en el cual estan las
	 * referencias de cada caracter y las veces que se repite, para despues
	 * buscar en el StringBuilder las posiciones en las que esta cada caracter
	 * 
	 * @param texto
	 * @param map
	 * @return HashMap<Character, ArrayList<Integer>> Por ejemplo: caracter 'a',
	 *         posiciones {23, 45, 103}
	 */
	private static HashMap<Character, ArrayList<Integer>> charPositions(
			StringBuilder texto, HashMap<Character, Integer> map) {

		HashMap<Character, ArrayList<Integer>> mapPosition = new HashMap<Character, ArrayList<Integer>>();

		/* Recorre cada entrada del mapa de caracteres */
		for (Entry<Character, Integer> a : map.entrySet()) {

			/* Cadena que va a almacenar las posiciones */
			ArrayList<Integer> chain = new ArrayList<Integer>();

			/*
			 * Una vez que se tiene el caracter, recorre el StringBuilder en
			 * busca de las posiciones en las que se encuentra
			 */
			for (int i = 0; i < texto.length(); i++) {

				/*
				 * Si en la posicion "i" encuentra el caracter "a.getKey()",
				 * añade esa posicion al la cadena
				 */
				if (texto.charAt(i) == a.getKey()) {

					chain.add(i);
				}
			}
			mapPosition.put(a.getKey(), chain);
		}
		return mapPosition;
	}

	public static char[] pasoAChar(HashMap<Character, Integer> mapa) {

		char[] caracteres = new char[mapa.size()];
		int posicion = 0;

		for (Entry<Character, Integer> a : mapa.entrySet()) {

			caracteres[posicion] = a.getKey();
			posicion++;
		}
		return caracteres;
	}

	public static Nodo rellenamatriz(StringBuilder texto,
			HashMap<Character, ArrayList<Integer>> mapa, Nodo matriz) {

		char caracter;
		ArrayList<Integer> lista;
		String cadena = "";

		for (Entry<Character, ArrayList<Integer>> a : mapa.entrySet()) {

			caracter = a.getKey();
			lista = a.getValue();

			for (int b : lista) {

				cadena += caracter;

				for (int i = 1; i < Nodo.dimension; i++) {

					try {
						cadena += texto.charAt(b + i);

					} catch (StringIndexOutOfBoundsException e) {
						// Llega a la ultima posicion

					}
				}
				// System.out.println(cadena);
				matriz.set(cadena, matriz.get(cadena) + 1);
				cadena = "";
			}
		}
		return matriz;
	}

	public static String nivelCero(char[] caracteres, double tamano) {

		String fin = "";
		double aleatorio;

		for (int i = 0; i < tamano; i++) {

			aleatorio = Math.random() * caracteres.length;
			fin += caracteres[(int) aleatorio];
		}

		return fin;
	}

	public static void aleatorios( Nodo matriz,int numcaracteres) {
		int aux = 0;
		int suma_valores = 0;
		int j;
		double aleatorio = 0;
		int i ;
		ArrayList<Nodo> lista = matriz.getnodes();
		while(Nodo.dimension != matriz.getNivel()){
			for (i = 0; i < lista.size(); i++) {
				suma_valores += lista.get(i).getValor();
				// System.out.println(lista.size());
				// System.out.println(lista.get(i).getValor());
			}
			aleatorio = Math.random() * suma_valores;
			for (j = 0; j < lista.size(); j++) {
				aux += lista.get(j).getValor();
				if (aux >= aleatorio) {
					System.out.print(lista.get(j).getLetra());
					
				}
				else{continue;}
			}
			aleatorios(lista.get(j), numcaracteres-1);
		}
			
	}

	public static void main(String[] args) {

		/*
		 * Scanner in = new Scanner(System.in);
		 * System.out.print("Refinamiento: "); int refinamiento = in.nextInt();
		 */

		StringBuilder texto = leerTexto("galdos.txt");
		HashMap<Character, Integer> mapa = crearHash(texto);
		HashMap<Character, ArrayList<Integer>> mapita = charPositions(texto,
				mapa);

		char[] caracteres = pasoAChar(mapa);
		// char[] caracteres = {'a', 'b', 'c'};

		long startTime = System.currentTimeMillis();
		for (int i = 0; i<caracteres.length;i++){
			System.out.print(caracteres[i] + " ");
		}
		Nodo raiz = new Nodo(caracteres, 3);
		long endTime = System.currentTimeMillis();

		rellenamatriz(texto, mapita, raiz);
		String random_text = "";
		//aleatorios(raiz, 200);

		// System.out.println(raiz.get("rea"));
		// System.out.println(nivelCero(caracteres, 100));

		System.out.println("Tiempo: " + (endTime - startTime) + " ms");

	}

}
