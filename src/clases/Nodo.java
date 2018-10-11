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
public class Nodo {
ArrayList <Arco> nextArco;
    String state;
    boolean isFinalState,isInitState;

    /**
     * Constructor que permite crear un Nodo y definir su contenido
     * @return El Nodo
     */
    public Nodo(String state,boolean isFinalState,boolean isInitState){
        this.state=state;
        this.isInitState=isInitState;
        this.isFinalState=isFinalState;
        nextArco=new ArrayList<Arco>();
    }

    /**
     * Método que permite crear un puntero de tipo arco para un nodo dado
     * @return El puntero del nodo
     */
    public void createNextArco(Arco arc){
        nextArco.add(arc);
    }    
}
