import java.util.ArrayList;

public class Nodo {

	private char letra;
	private int valor;
	public ArrayList<Nodo> nodes;
	private int nivel;
	
	public static int dimension;
	private static char[] caracteres;
	
	public Nodo(char[] caracteres){
		
		Nodo.caracteres = caracteres;
		this.letra = '\u0000';
		this.nivel = 0;
		this.nodes = new ArrayList<Nodo>();
		
		for (int i = 0; i < caracteres.length; i++) {

			this.nodes.add(new Nodo(1, caracteres[i]));
		}

	}
	public Nodo(int nivel, char letra){
		
		this.nivel = nivel;
		this.letra = letra;
		String espacio = "  ";
		
		for (int i = 0; i < nivel; i++) {
			
			espacio += espacio;
		}
		
		//System.out.println(espacio +"C: " + letra + "; "+valor);
		
		if(nivel != dimension){
			
			this.nodes = new ArrayList<Nodo>();
			
			for (int i = 0; i < caracteres.length; i++) {
				
				this.nodes.add(new Nodo(nivel + 1, caracteres[i]));		
			}
			
		}else{
			
			this.nodes = null;
		}
	}
	
	public void set(String cadena, int valor){

		if(this.nivel == cadena.length()){
			
			this.valor = valor;
		}else{
			
			for (Nodo a: nodes) {
				
				if(a.getLetra() == cadena.charAt(nivel)){
					
					a.set(cadena, valor);
				}
			}
		}
	}
	
	public int get(String cadena){

		if(this.nivel == cadena.length()){
			
			return this.valor;
		}else{
			
			for (Nodo a: nodes) {
				
				if(a.getLetra() == cadena.charAt(nivel)){
					
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
