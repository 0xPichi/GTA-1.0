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

public class Generador {

	private StringBuilder textito_final = new StringBuilder();
	
	public Generador(String texto, int refinamiento, int numcaracteres) {

		/* StringBuilder con el texto leido desde el fichero */
		StringBuilder texto_Completo = leerTexto(texto);

		// Array con todos los caracteres
		char[] caracteres = distintosCaracteres(texto_Completo);

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
			
			long tiempoInicio = System.currentTimeMillis();
			rellenamatriz(texto_Completo, raiz);
			long tiempoFin = System.currentTimeMillis();
			System.out.println("Tiempo: "+ ( tiempoFin - tiempoInicio ) + " ms");
			
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

	private void rellenamatriz(StringBuilder texto, Nodo matriz) {

		String cadena = "";
		for(int j = 0; j < texto.length(); j++){

				cadena += texto.charAt(j);
				for (int i = 1; i < Nodo.dimension; i++) {

					try {
						cadena += texto.charAt(j+i);

					} catch (StringIndexOutOfBoundsException e) {
						// Llega a la ultima posicion

					}
				}
				matriz.set(cadena);
				cadena = "";
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

		for (int j = 0; j < matriz.nodes.getSize(); j++) {

			valores += matriz.nodes.get(j).getValor();
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
				for (int j = 0; j < matriz.nodes.getSize(); j++) {

					// Si la letra al recorrer los nodos = ultima letra del
					// StringBuilder
					if (matriz.nodes.get(j).getLetra() == textito_final.charAt(textito_final
							.length() - 1)) {

						// System.out.print(matriz.nodes[i].getLetra());
						elegido = matriz.nodes.get(j);
						break;
					}
				}
				aleatorios(elegido);

				// Demas niveles
			} else {
				Nodo elegido = null;

				// Busca, teniendo en cuenta las probabilidades, la siguiente
				// letra
				
				for (int j = 0; j < matriz.nodes.getSize(); j++) {

					acumulado += matriz.nodes.get(j).getValor();

					if (acumulado >= aleatorio) {

						// System.out.println("Acum.: "+acumulado + "; Alea.: "
						// + aleatorio + "; Valor: "+matriz.getValor());
						textito_final.append(matriz.nodes.get(j).getLetra());
						elegido = matriz.nodes.get(j);
						break;
					}
				}
				aleatorios(elegido);
			}

			// Penultimo nivel
		} else {

			for (int j = 0; j < matriz.nodes.getSize(); j++) {

				acumulado += matriz.nodes.get(j).getValor();

				if (acumulado >= aleatorio) {

					textito_final.append(matriz.nodes.get(j).getLetra());
					break;
				}
			}
		}
	}

	public StringBuilder getTextoGenerado() {

		return this.textito_final;
	}

}
