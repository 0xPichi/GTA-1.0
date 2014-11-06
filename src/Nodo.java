import java.util.ArrayList;

public class Nodo {

	private char letra;
	private int valor;
	public ArrayList<Nodo> nodes;
	private int nivel;
	public static int dimension;
	public static char[] caracteres;
	public static int contador = 0;
	
	public Nodo(){
		
		this.letra = '\u0000';
		this.nivel = 0;
		this.nodes = new ArrayList<Nodo>();

	}
	public Nodo(int nivel, char letra){
		
		this.nivel = nivel;
		this.letra = letra;
		
		//System.out.println("L: " + nivel + ", C: " + letra);
		
		if(nivel != dimension){
			
			this.nodes = new ArrayList<Nodo>();
			
			for (int i = 0; i < caracteres.length; i++) {
				
				this.nodes.add(new Nodo(nivel + 1, caracteres[i]));		
			}
			
		}else{
			this.nodes = null;
		}
	
		contador++;
	}
	
	public ArrayList<Nodo> getHijo(){
		
		if(this.nodes != null){
			
			return this.nodes;
		}else{
			
			return null;
		}
	}
	/*
	public void rellena(char[] caracteres, int nivel){
		
		for (int i = 0; i < caracteres.length; i++) {
			
			nodes[i] = new Nodo(caracteres[i], this.nivel + 1);
		}
	}*/

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
