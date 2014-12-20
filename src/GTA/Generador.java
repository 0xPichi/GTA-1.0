package GTA;

/* Hecho por:
 *  Adrian Calvo Rojo 
 *  Sergio Delgado Alvarez
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Generador {

	private StringBuilder textito_final = new StringBuilder();
	
	public Generador(String texto, int refinamiento, int numcaracteres) {

		/* StringBuilder con el texto leido desde el fichero */
		StringBuilder texto_Completo = leerTexto(texto);

		// Array con todos los caracteres
		char[] caracteres = distintosCaracteres(texto_Completo);

		// Mapa que asigna a cada caracter la pisicion en la que se encuentra
		HashMap<Character, ArrayList<Integer>> mapita = charPositions(
				texto_Completo, caracteres);

		/*
		 * Si se ha escogido como refinamiento = 0, se cogen solo caracteres
		 * aleatorios
		 */
		if (refinamiento == 0) {

			textito_final = nivelCero(caracteres, numcaracteres);
		} else {

			/* Introducimos como primer caracter una letra aleatoria */
			textito_final.append(nivelCero(caracteres, 1));

			/*
			 * Crea un objeto Nodo "raiz" al que le vamos a dar la dimension
			 * dependiendo del refinamiento deseado
			 */
			Nodo raiz = new Nodo(caracteres, refinamiento);
			rellenamatriz(texto_Completo, mapita, raiz);
			
			while (textito_final.length() < numcaracteres) {

				aleatorios(raiz);

			}

			// Coger el número justo de caracteres
			textito_final.delete(numcaracteres, textito_final.length());
		}
	}

	/**
	 * Lee un fichero y lo almacena en un objeto de la clase StringBuilder
	 * 
	 * @param nombre
	 * @return StringBuilder
	 */
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

	public char[] distintosCaracteres(StringBuilder texto) {

		char[] chars = null;
		boolean flag = false;

		for (int i = 0; i < texto.length(); i++) {

			if (i == 0) {

				chars = new char[1];
				chars[0] = texto.charAt(0);

			} else {

				if (((int) texto.charAt(i) > 31)
						|| ((int) texto.charAt(i) == 10)) {
					flag = false;

					for (int j = 0; j < chars.length; j++) {

						if (texto.charAt(i) == chars[j]) {
							flag = true;
						}
					}
					if (!flag) {

						char[] aux = chars.clone();
						chars = new char[aux.length + 1];

						int capacidad = aux.length;

						for (int g = 0; g < capacidad; g++) {

							chars[g] = aux[g];
						}
						chars[aux.length] = texto.charAt(i);
					}
				}
			}
		}
		return chars;
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
	private HashMap<Character, ArrayList<Integer>> charPositions(
			StringBuilder texto, char[] chars) {

		HashMap<Character, ArrayList<Integer>> mapPosition = new HashMap<Character, ArrayList<Integer>>();
		char letra;
		ArrayList<Integer> positions;

		for (int position = 0; position < texto.length(); position++) {

			letra = texto.charAt(position);
			positions = mapPosition.get(letra);

			if (positions == null) {
				positions = new ArrayList<Integer>();
			}

			positions.add(position);
			mapPosition.put(letra, positions);

		}

		return mapPosition;
	}

	private void rellenamatriz(StringBuilder texto,
			HashMap<Character, ArrayList<Integer>> mapa, Nodo matriz) {

		String cadena = "";
		char ac = 'b';
		
		for (Entry<Character, ArrayList<Integer>> a : mapa.entrySet()) {

			long tiempoInicio = System.currentTimeMillis();

			for (int b : a.getValue()) {

				cadena += a.getKey();

				for (int i = 1; i < Nodo.dimension; i++) {

					try {
						cadena += texto.charAt(b + i);

					} catch (StringIndexOutOfBoundsException e) {
						// Llega a la ultima posicion

					}
				}

				matriz.set(cadena);
				cadena = "";
			}

			long tiempoFin = System.currentTimeMillis();
			System.out.println("\nChar:" + ac + " Tiempo: "+ ( tiempoFin - tiempoInicio ) + " ms");
		}

	}

	private StringBuilder nivelCero(char[] caracteres, double tamano) {

		StringBuilder fin = new StringBuilder();
		double aleatorio;

		for (int i = 0; i < tamano; i++) {

			aleatorio = Math.random() * caracteres.length;
			fin.append(caracteres[(int) aleatorio]);
		}

		return fin;
	}

	private void aleatorios(Nodo matriz) {

		// Calculamos la suma de sus hijos
		double valores = 0;

		for (Nodo i : matriz.nodes) {

			valores += i.getValor();
		}
		// Aleatorio entre 0 y la suma total
		double aleatorio = Math.random() * valores;
		double acumulado = 0;

		// Antes de llegar al penúltimo nivel
		if (matriz.getNivel() + 1 < Nodo.dimension) {

			// Primer nivel: Solo coge la ultima letra que hay escrita
			if (matriz.getNivel() == 0) {

				Nodo elegido = null;

				// Recorre los subNodos de ese Nodo
				for (Nodo i : matriz.nodes) {

					// Si la letra al recorrer los nodos = ultima letra del
					// StringBuilder
					if (i.getLetra() == textito_final.charAt(textito_final
							.length() - 1)) {

						// System.out.print(matriz.nodes[i].getLetra());
						elegido = i;
						break;
					}
				}
				aleatorios(elegido);

				// Demas niveles
			} else {
				Nodo elegido = null;

				// Busca, teniendo en cuenta las probabilidades, la siguiente
				// letra
				for (Nodo i : matriz.nodes) {

					acumulado += i.getValor();

					if (acumulado >= aleatorio) {

						// System.out.println("Acum.: "+acumulado + "; Alea.: "
						// + aleatorio + "; Valor: "+matriz.getValor());
						textito_final.append(i.getLetra());
						elegido = i;
						break;
					}
				}
				aleatorios(elegido);
			}

			// Penultimo nivel
		} else {

			for (Nodo i : matriz.nodes) {

				acumulado += i.getValor();

				if (acumulado >= aleatorio) {

					textito_final.append(i.getLetra());
					break;
				}
			}
		}
	}

	public StringBuilder getTextoGenerado() {

		return this.textito_final;
	}

}
