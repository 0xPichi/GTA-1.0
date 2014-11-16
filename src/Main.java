import java.util.Scanner;


	public class Main {

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
		
		Generador gta = new Generador(texto, refinamiento, numcaracteres);
		
		System.out.print(gta.getTextoGenerado());		

	}

}
