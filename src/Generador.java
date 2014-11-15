import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Generador {

	/**
	 * Lee un texto y lo almacena en un objeto de la clase StringBuilder
	 * 
	 * @param nombre
	 * @return StringBuilder
	 */
	private StringBuilder textito_final = new StringBuilder();

	public Generador() {

		textito_final.append("a");
	}

	public StringBuilder leerTexto(String nombre) {

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
	private static HashMap<Character, Integer> distintosCaracteres(
			StringBuilder texto) {

		HashMap<Character, Integer> mapa = new HashMap<Character, Integer>();

		for (int i = 0; i < texto.length(); i++) {

			try {
				// Suma 1 al total de veces que se repite ese caracter
				// (mapa.put('caracter', veces + 1))
				if (((int) texto.charAt(i) > 31)
						|| ((int) texto.charAt(i) == 10)) {
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
	private static HashMap<Character, ArrayList<Integer>> charPositions(StringBuilder texto, HashMap<Character, Integer> map) {

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
				matriz.set(cadena);
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

	public void aleatorios(Nodo matriz) {
		
		double aleatorio = Math.random() * matriz.getValor();
		double acumulado = 0;
		
		// Antes de llegar al penúltimo nivel
		if (matriz.getNivel() + 1 < Nodo.dimension) {

			// Primer nivel: Solo coge la ultima letra que hay escrita
			if (matriz.getNivel() == 0) {

				Nodo elegido = null;

				//Recorre los subNodos de ese Nodo
				for (int i = 0; i < matriz.nodes.length; i++) {

					// Si la letra al recorrer los nodos = ultima letra del StringBuilder
					if (matriz.nodes[i].getLetra() == 
							textito_final.charAt(textito_final.length() - 1)) {

						// System.out.print(matriz.nodes[i].getLetra());
						elegido = matriz.nodes[i];
						break;
					}
				}
				aleatorios(elegido);

				// Demas niveles
			} else {
				Nodo elegido = null;
				
				// Busca, teniendo en cuenta las probabilidades, la siguiente
				// letra
				for (int i = 0; i < matriz.nodes.length; i++) {

					acumulado += matriz.nodes[i].getValor();

					if (acumulado >= aleatorio) {

						textito_final.append(matriz.nodes[i].getLetra());
						elegido = matriz.nodes[i];
						break;
					}
					
					// En caso de que llege al final, al usar Math.Random()
					// Los decimales tambien les tiene en cuenta al comparar
					// y no lo detecta con el "if" anterior. De esta forma
					// evitamos que mande la matriz vacia.
					if(i == matriz.nodes.length - 1){
						
						textito_final.append(matriz.nodes[i].getLetra());
						elegido = matriz.nodes[i];
					}
				}
				aleatorios(elegido);
			}

			// Penultimo nivel
		} else {

			for (int i = 0; i < matriz.nodes.length; i++) {

				acumulado += matriz.nodes[i].getValor();

				if (acumulado >= aleatorio) {

					textito_final.append(matriz.nodes[i].getLetra());
					break;
				}
				if(i == matriz.nodes.length - 1){
					textito_final.append(matriz.nodes[i].getLetra());
				}
			}
		}
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("BIENVENIDO AL GENERADOR DE TEXTOS ALEATORIOS" + "\n");
		System.out.print("Seleccione el texto: ");
		String texto = in.nextLine();
		System.out.print("Introduzca el nivel de refinamiento: ");
		int refinamiento = in.nextInt();
		System.out.print("Introduzca el numero de caracteres que desea generar: ");
		int numcaracteres = in.nextInt();
		in.close();

		Generador gta = new Generador();
		StringBuilder texto_Completo = gta.leerTexto(texto);
		HashMap<Character, Integer> mapa = distintosCaracteres(texto_Completo);
		HashMap<Character, ArrayList<Integer>> mapita = charPositions(texto_Completo, mapa);
		char[] caracteres = pasoAChar(mapa);

		if (refinamiento == 0) {
			
			String random_cero = nivelCero(caracteres, numcaracteres);
			System.out.println(random_cero);
		} else {
			
			//long startTime = System.currentTimeMillis();
			//long endTime = System.currentTimeMillis();
			
			Nodo raiz = new Nodo(caracteres, refinamiento);
			rellenamatriz(texto_Completo, mapita, raiz);
			
			while (gta.textito_final.length() < numcaracteres) {

				gta.aleatorios(raiz);
				
			}
			//gta.textito_final.deleteCharAt(numcaracteres-1);
			System.out.print(gta.textito_final);
			
			//Coger el número justo de caracteres
			gta.textito_final.delete(numcaracteres, gta.textito_final.length());
			
			System.out.println("Tamaño: " + gta.textito_final.length());
		}

	}

}
