
public class Matriz {
	
	private int dimension, lado, capacidad;
	private int[] memoria;
	
	public Matriz(int dimension, int lado){
		
		this.dimension = dimension;
		this.lado = lado;
		this.capacidad = (int) Math.pow(lado, dimension);
		memoria = new int[capacidad];
	}
	
	public int getDimension(){
		
		return this.dimension;
	}
	
	public int getDato(int...indice){
	
		int posicion = 0;
		try{

			int vuelta = 0;
			
			for (int a : indice){
				
				posicion += (int) (a * Math.pow(lado, vuelta));
				vuelta++;
			}			
			
		}catch(IndexOutOfBoundsException e){
			
			e.printStackTrace();
		}
		return memoria[posicion];
	}
	
	public void guardarDato(int dato, int...indice){
		
		try{

			int vuelta = 0, posicion = 0;
			
			for (int a : indice){
				
				posicion += (int) (a * Math.pow(lado, vuelta));
				vuelta++;

				//System.out.println(a);
			}
			//System.out.println("Dato" + dato);
			//System.out.println("Posi " + posicion);
			memoria[posicion] = dato;
	
		}catch(Error e){
			
			e.printStackTrace();
		}
	}

}
