/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import java.util.ArrayList;
/**
 *
 * @author pablo
 */
public class Arco {
    String letter;
    ArrayList<Nodo> nextNodo;

    /**
     * Constructor que permite crear un arco y definir su contenido
     * @return El arco
     */
    public Arco(String letter){
        this.letter=letter;
        nextNodo=new ArrayList<Nodo>();
    }

    /**
     * Método que permite crear un puntero de tipo nodo para un arco dado
     * @return El puntero del arco
     */
    public void createNextNodo(Nodo node){
        nextNodo.add(node);
    }
}
