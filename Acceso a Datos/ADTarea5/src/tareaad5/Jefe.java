/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaad5;

/**
 *
 * @author Gonzalo
 */
public class Jefe {
    
    private String nombre;
    private int antig;
    private int edad;
    private Hijo hijo;

    public Jefe(String nombre, int antig, int edad, Hijo hijo) {
        this.nombre = nombre;
        this.antig = antig;
        this.edad = edad;
        this.hijo = hijo;
    }

    public Jefe(String nombre, int edad, Hijo hijo) {
        this.nombre = nombre;
        this.edad = edad;
        this.hijo = hijo;
    }

    public Jefe(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    public Jefe(String nombre){
        this.nombre = nombre;
    }
    public Jefe(){
        this.nombre = null;
        this.antig = 0;
        this.edad = 0;
        this.hijo = null;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAntig() {
        return antig;
    }

    public void setAntig(int antig) {
        this.antig = antig;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Hijo getHijo() {
        return hijo;
    }

    public void setHijo(Hijo hijo) {
        this.hijo = hijo;
    }

    @Override
    public String toString() {
        return "Jefe {" + "Nome: " + nombre + ", Antigüedade: " + antig + ", Idade: " + edad + ", Fill@:" + hijo + '}';
    }
    
    
}
