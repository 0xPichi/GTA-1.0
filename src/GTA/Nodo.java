package GTA;

import java.util.ArrayList;
/* Hecho por:
 *  Adrian Calvo Rojo 
 *  Sergio Delgado Alvarez
 */
 
public class Nodo {

	private char letra;
	private int valor = 0;
	public ArrayList<Nodo> nodes = null;
	private int nivel;
	
	public static int dimension;
	
	/**
	 * Constructor que crea el Nodo raiz, y va creando objetos Nodo(nivel, letra) recursivamente.
	 * 
	 * @param caracteres Array con los distintos caracteres que van a existir
	 */
	public Nodo(char[] caracteres, int dimension){
		
		Nodo.dimension = dimension;
		this.letra = '\u0000';
		this.nivel = 0;
		
		nodes = new ArrayList<Nodo>();
	}
	
	/**
	 * Constructor de subNodos que van a formar parte del Nodo raiz y a su vez van a crear mas objetos
	 * Nodo recursivamente hasta completar todos los niveles del arbol.
	 * 
	 * @param nivel dimension en la que va a estar dicho Nodo
	 * @param letra letra que va a representar al Nodo
	 */
	public Nodo(int nivel, char letra){
		
		this.nivel = nivel;
		this.letra = letra;		
					
		this.nodes = new ArrayList<Nodo>();			
	
	}
	
	public void set(String cadena){

		if(nivel == cadena.length()){
			
			valor++;
		}else{
			
			nodes.add(new Nodo(nivel + 1, cadena.charAt(nivel)));

			for (Nodo a: nodes) {
				
				if(a.getLetra() == cadena.charAt(nivel)){
					
					a.set(cadena);
					valor++;
					break;
				}
			}
			
		}
	}
	/**
	 * Hace lo mismo que set, pero en este caso en vez de llegar y establecer el atributo "valor"
	 * del objeto, lo que hace es recuperarlo.
	 * 
	 * @param cadena
	 * @return
	 */
	public int get(String cadena){

		if(nivel == cadena.length()){
			
			System.out.println("C: " + this.letra + "; "+this.valor);
			return getValor();
		}else{
			
			for (Nodo a: nodes) {
				
				//Si la letra del nodo es igual que la que hay para ese nivel
				if(a.getLetra() == cadena.charAt(nivel)){
					
					System.out.println("C: " + this.letra + "; "+this.valor);
					return a.get(cadena);
				}
			}
		}
		return 0;
	}
	
	
	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}
	public int getValor(){
		return this.valor;
	}
	
	public ArrayList<Nodo> getnodes(){
		return this.nodes;
	}
	
	public int getNivel(){
		return this.nivel;
	}

}
