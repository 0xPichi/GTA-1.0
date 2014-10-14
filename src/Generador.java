import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Generador {

public static int[][] leerSimple(String nombre){
		
		int[][] matriz = new int[79][2];
		
		File archivo = new File(nombre);
		try {
			
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			
			int leo;
			
			while((leo = br.read()) != -1){
				
				if(leo > 32){
					for(int i = 0; i < matriz.length; i++){
					
						if (leo == matriz[i][0]){
							
							matriz[i][1] += 1;
							break;
						}
						else if((leo != matriz[i][0]) && (matriz[i][0] == 0)){
							
							matriz[i][0] = leo;
							matriz[i][1] = 1;
							break;
						}
					}
				}				
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return matriz;
	}
	
	public static void main(String[] args) {

		Matriz a = new Matriz(2, 10);
		
		a.guardarDato((int)'r', 4, 5);

		System.out.println((char)a.getDato(4, 6));
	}

}
