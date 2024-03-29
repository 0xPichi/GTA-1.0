package GTA;
/* Hecho por:
 *  Adrian Calvo Rojo 
 *  Sergio Delgado Alvarez
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		Scanner in = new Scanner(System.in);
		System.out.println("BIENVENIDO AL GENERADOR DE TEXTOS ALEATORIOS"
				+ "\n");
		System.out.print("Seleccione el texto: ");
		String texto = in.nextLine();
		System.out.print("Introduzca el nivel de refinamiento: ");
		int refinamiento = in.nextInt();
		System.out.print("Introduzca el numero de caracteres que desea generar: ");
		int numcaracteres = in.nextInt();
		in.close();

		long tiempoInicio = System.currentTimeMillis();
		Generador gta = new Generador(texto, refinamiento, numcaracteres);
		long tiempoFin = System.currentTimeMillis();
		
		System.out.print("\n" + gta.getTextoGenerado());

		System.out.println("\nTiempo: "+ ( tiempoFin - tiempoInicio ) + " ms");

		/*
		AdriayList<Integer> hola = new AdriayList<Integer>();
		hola.add(23);
		hola.add(10);
		System.out.println(hola.getSize());System.out.println(hola.get(1));
		*/
		
	}

}
