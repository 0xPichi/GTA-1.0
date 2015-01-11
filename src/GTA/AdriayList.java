package GTA;

import java.util.Arrays;

public class AdriayList<E> {
	
    private int size = 0;
    private static final int CAPACIDAD_DEFECTO = 10;
    private Object elementos[];

    public AdriayList(){
        elementos = new Object[CAPACIDAD_DEFECTO];
    }

    public void add (E elemento){
        if(size == elementos.length) {
            ensureCapa();
        }
        elementos[size++] = elemento;

    }

    public void ensureCapa(){
        int nuevoTamaño = elementos.length * 2;
        elementos = Arrays.copyOf(elementos, nuevoTamaño);
    }

    @SuppressWarnings("unchecked")
	public E get(int i){
        if(i>= size || i<0){
            throw new IndexOutOfBoundsException("Index" + i + ", Size" + i);
        }
        return (E) elementos[i];
    }

    public int getSize(){
        return size;
    }
}
