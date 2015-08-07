/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertsaleatorios;

import java.util.ArrayList;
/**
 * Esta clase es para guardar clave valor.
 * @author pedruben
 * @param <C>
 * @param <V> 
 */
public class DobleArray <C,V>{

    private  ArrayList<C> clave;
    private final ArrayList<V> valor;
    private int length;

    public ArrayList<C> getClave() {
        return clave;
    }

    public ArrayList<V> getValor() {
        return valor;
    }
    
    public void asignarClaves(ArrayList<C> claves){
        this.clave=claves;
    }
    /**
     * Añade las claves que se le pasan como array 
     * y los valores los pone a null.
     * @param claves 
     */
    public void pasarClavesConValorNull(ArrayList<C> claves){
        claves.stream().forEach((clave1) -> {
            add(clave1, null);
        });
    }
    
    public DobleArray(){
        this.clave=new ArrayList<>();
        this.valor=new ArrayList<>();
        this.length=0;
    }
    public int size(){
        return this.length;
    }
    private int tm(){
        int v=clave.size()-valor.size();
        return v;
    }
    public void add(C t,V v){
        this.clave.add(t);
        this.valor.add(v);
        comprobarLongitud(this);
        this.length++;
    }
    
    public void remove(int index){
        this.clave.remove(index);
        this.valor.remove(index);
       comprobarLongitud(this);
        this.length--;
           
    }
    
    public C getKey(int index){
        return this.clave.get(index);
    }
    
    public V getValue(int index){
        return this.valor.get(index);
    }
       
    private static void comprobarLongitud(DobleArray arr){
        if(arr.tm()!=0){
            throw new Error("El tamaño de los arrays de la clase DobleArray no coincide");
        }
    }
}
