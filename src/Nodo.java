public class Nodo {

	private char letra;
	//private int valor;
	private Nodo[] nodes;
	private int nivel;
	public static int dimension;
	
	public Nodo(int nivel, int caracteres){

		if(nivel == dimension){
			
			this.nodes = null;
		}else{
			
			this.nodes = new Nodo[caracteres];
			
			for (int i = 0; i < this.nodes.length; i++) {
				
				this.nodes[i] = new Nodo(nivel+1, caracteres);
			}
			
		}
		this.setNivel(nivel);
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

}
