import java.util.ArrayList;

public class Nodo {

	private char letra;
	private int valor;
	public ArrayList<Nodo> nodes;
	private int nivel;
	
	public static int dimension;
	private static char[] caracteres;
	
	/**
	 * Constructor que crea el Nodo raiz, y va creando objetos Nodo(nivel, letra) recursivamente.
	 * 
	 * @param caracteres Array con los distintos caracteres que van a existir
	 */
	public Nodo(char[] caracteres){
		
		Nodo.caracteres = caracteres;
		this.letra = '\u0000';
		this.nivel = 0;
		this.nodes = new ArrayList<Nodo>();
		
		for (int i = 0; i < caracteres.length; i++) {

			this.nodes.add(new Nodo(1, caracteres[i]));
		}

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
		
		//System.out.printl super chulo para que parezca un arbol
		/*
		String espacio = "  ";
		for (int i = 0; i < nivel; i++) {
			
			espacio += espacio;
		}		
		System.out.println(espacio +"C: " + letra + "; "+valor);
		*/		
		if(nivel != dimension){
			
			this.nodes = new ArrayList<Nodo>();
			
			for (int i = 0; i < caracteres.length; i++) {
				
				this.nodes.add(new Nodo(nivel + 1, caracteres[i]));		
			}
			
		}else{
			
			this.nodes = null;
		}
	}
	
	/**
	 * Metodo que a partir de una cadena recorre el arbol y coloca un valor dado al final. La cadena
	 * indica las direcciones que debemos tomar. Por ejemplo:
	 * 
	 * Tenemos esta estructura de datos:
	 * 
	 *         a                                                                a
	 *        /                                                                
	 *       a                                                                a  
	 *     /  \                                                             /  \
	 *    /    b                                                           /    b
	 *   /                                                                /
	 * Raiz              Indicamos que la cadena es "ab" ->            Raiz
	 *   \
	 *    \    a                                                                 a
	 *     \  /                                                                  
	 *       b                                                                b
	 *        \
	 *         b                                                                 b
	 *         
	 * Busca la ruta recursivamente hasta llegar al punto deseado y establecer el valor del objeto.
	 *         
	 * @param cadena 
	 * @param valor
	 */
	public void set(String cadena, int valor){

		if(nivel == cadena.length()){
			
			this.valor = valor;
		}else{
			
			for (Nodo a: nodes) {
				
				if(a.getLetra() == cadena.charAt(nivel)){
					
					a.set(cadena, valor);
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
			return this.valor;
		}else{
			
			for (Nodo a: nodes) {
				
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

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}

}
