package empleado;

import javax.persistence.*;

import dept.Departamentos;

@Entity
@Table(name = "empleado")
public class Empleados {

	//identificamos campos con @ y mediante su nombre
	@Id
	@Column(name = "id")
	public int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "departamento")
	private int departamento;
	
	@Column(name = "sueldo")
	private double sueldo;

	//creamos constructores, vacío y con datos.
	public Empleados() {

	}

	public Empleados(String nombre, int departamento, double sueldo) {
		this.nombre = nombre;
		this.departamento = departamento;
		this.sueldo = sueldo;
	}

	//añadimos getters & setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}
	
	public double getSueldo() {
		return sueldo;
	}
	
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	//sobrescribimos método toString()
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + ", sueldo=" + sueldo
				+ "]";
	}

	
}
