
package Main;

import List.doubleLinkedList;
import Nodes.Node;
import java.util.Scanner;

public class Main {

    static doubleLinkedList<Double> dobles =  new doubleLinkedList<>();
   // public static doubleLinkedList<Integer> list1;
    
    /* public static void fill(int n,int m){
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <m ; j++) {
                dobles.add( (Double)Math.abs( Math.floor(Math.random()*(n-m+1)+m)));
            }
        }
    }*/

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
       
        int menor, mayor;
        
        System.out.println("Ingrese el rango menor de su lista");
        menor=sc.nextInt();
        System.out.println("Ingrese el rango mayor de su lista");
        mayor=sc.nextInt();
        System.out.println("");
        
        
       for (double i = menor; i < mayor; i++) {
            dobles.add(i);
        }

       
   
        dobles.add(11d);  //Agrega un elemento al final de la lista
        
        dobles.addAt(10d,3); //Agrega un numero en la posicion asignada de la lista
        
        dobles.addAfter(7d, 45d); //Agrega un numero asignado Y despues del numero X en la lista
        
        dobles.addBefore(-3d, 54d); //Agrega un numero asignado Y antes del numero X en la lista
        
        dobles.addStart(30d); //Agrega un numero al inicio de la lista
        
        dobles.remove(3d); //Elimina la primer repeticion del numero asignado que encuentre
        
        dobles.removeAfter(8d); //Elimina el numero que se encuentre despues del valor asignado
        
        dobles.removeBefore(30d); //Elimina el numero que se encuentre antes del valor asignado
        
        dobles.removeAll(12d); //Busca el numero asignado y lo elimina todas las veces que se repita
       
        System.out.println(dobles.getElementAt(7).getValue()); //Obtiene el numero en la posicion asignada
        
         for (Double object : dobles) {
            System.out.println(object);
        }
    }
}
