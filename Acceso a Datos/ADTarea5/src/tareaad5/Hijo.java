/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaad5;

/**
 *
 * @author Gonzalo
 */
public class Hijo {
    
    private String nombre;
    private int idade;
    
    public Hijo(){
        
    }

    public Hijo(String nombre, int idade) {
        this.nombre = nombre;
        this.idade = idade;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "{" + "nome:" + nombre + ", idade:" + idade + '}';
    }
    
    
}
