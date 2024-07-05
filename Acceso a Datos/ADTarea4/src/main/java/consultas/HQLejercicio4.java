package consultas;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import empleado.Empleados;
import dept.Departamentos;

public class HQLejercicio4 {

	public static void main(String[] args) {
		//creamos un sessionfactory con el archivo de hibernate
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Empleados.class)
				.addAnnotatedClass(Departamentos.class).buildSessionFactory();
		//iniciamos session
		Session session = miFactory.openSession();

		try {
			// comenzar sesion
			session.beginTransaction();

			// consulta empleados
			List<Object[]> empleados = session
					.createQuery("select emp.id, emp.nombre, emp.sueldo,"
							+ " dept.nombre FROM Empleados emp JOIN Departamentos dept ON emp.departamento = dept.id").getResultList();

			// mostrar empleados
			//creamos una variable para cada dato, para poder obtenerlos
			for (Object[] arr : empleados) {
				Integer id = (Integer) arr[0];
			    String nombreEmpleado = (String) arr[1];
			    Double sueldo = (Double) arr[2];
			    String nombreDepartamento = (String) arr[3];
			    //los imprimimos
			    System.out.println("Empleado [id= " + id + ", nombre= " + nombreEmpleado + ", departamento= " + nombreDepartamento
			            + ", sueldo= " + sueldo + "]");
			}
			session.close();
		miFactory.close();

		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
