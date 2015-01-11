package GTA;

/* Hecho por:
 *  Adrian Calvo Rojo 
 *  Sergio Delgado Alvarez
 */
 
public class Nodo {

	private char letra;
	private int valor = 0;
	public AdriayList<Nodo> nodes = null;
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
		
		nodes = new AdriayList<Nodo>();
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
					
		this.nodes = new AdriayList<Nodo>();			
	
	}
	
	public void set(String cadena){

		if(nivel == cadena.length()){
			//System.out.println(cadena);
			valor++;
		}else{
			
			nodes.add(new Nodo(nivel + 1, cadena.charAt(nivel)));
			for (int i = 0; i < nodes.getSize(); i++) {
				
				if(nodes.get(i).getLetra() == cadena.charAt(nivel)){
					
					nodes.get(i).set(cadena);
					valor++;
					break;
				}
			}
			
		}
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
	
	public AdriayList<Nodo> getnodes(){
		return this.nodes;
	}
	
	public int getNivel(){
		return this.nivel;
	}

}
