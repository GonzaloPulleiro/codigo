/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package adtarea2.pkg1;

/**
 *
 * @author Gonzalo
 */
public class Empleado {

    int codigo;
    String nombre;
    String direccion;
    float salario;
    float comision;

    public Empleado(int codigo, String nombre, String direccion, float salario, float comision) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.salario = salario;
        this.comision = comision;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Float getSalario() {
        return salario;
    }

    public Float getComision() {
        return comision;
    }
}
