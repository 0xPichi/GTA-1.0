public class Nodo {

	private char letra;
	private int valor = 0;
	public Nodo[] nodes = null;
	private int nivel;
	
	public static int dimension;
	public static char[] caracteres;
	
	/**
	 * Constructor que crea el Nodo raiz, y va creando objetos Nodo(nivel, letra) recursivamente.
	 * 
	 * @param caracteres Array con los distintos caracteres que van a existir
	 */
	public Nodo(char[] caracteres, int dimension){
		
		Nodo.dimension = dimension;
		Nodo.caracteres = caracteres;
		this.letra = '\u0000';
		this.nivel = 0;
		this.nodes = new Nodo[caracteres.length];
		
		for (int i = 0; i < caracteres.length; i++) {

			nodes[i] = new Nodo(nivel + 1, caracteres[i]);
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
		
		if(nivel != dimension){
			
			this.nodes = new Nodo[caracteres.length];
			
			for (int i = 0; i < caracteres.length; i++) {
				
				nodes[i] = new Nodo(nivel + 1, caracteres[i]);		
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
	 * Raiz              Indicamos que la cadena es "ab" ->             Raiz
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
	public void set(String cadena){

		if(nivel == cadena.length()){
			
			this.valor += 1;
		}else{
			
			for (Nodo a: nodes) {
				
				if(a.getLetra() == cadena.charAt(nivel)){
					
					a.set(cadena);
					this.valor += 1;
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
	
	public Nodo getEsto(String cadena){

		if(nivel == cadena.length()){
			
			//System.out.println("C: " + this.letra + "; "+this.valor);
			return this;
		}else{
			
			if(nodes != null){
				
				for (Nodo a: nodes) {
					
					//Si la letra del nodo es igual que la que hay para ese nivel
					if(a.getLetra() == cadena.charAt(nivel)){
						
						//System.out.println("C: " + this.letra + "; "+this.valor);
						return a.getEsto(cadena);
					}
				}
			}
		}
		return null;
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
	
	public Nodo[] getnodes(){
		
		return this.nodes;
	}
	
	public int getNivel(){
		return this.nivel;
	}

}
