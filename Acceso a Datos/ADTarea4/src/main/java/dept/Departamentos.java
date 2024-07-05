package dept;

import javax.persistence.*;

@Entity
@Table(name = "departamento")
public class Departamentos {

	//identificamos campos con @ y mediante su nombre
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	//creamos constructores, vacío y con datos.
	public Departamentos() {
		
	}

	public Departamentos(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
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

	//sobrescribimos método toString()
	@Override
	public String toString() {
		return "Departamentos [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
	
}
