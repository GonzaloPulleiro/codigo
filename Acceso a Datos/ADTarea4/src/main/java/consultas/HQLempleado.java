package consultas;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import empleado.Empleados;

public class HQLempleado {

	public static void main(String[] args) {
		//creamos un sessionfactory con el archivo de hibernate
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Empleados.class).buildSessionFactory();
		//iniciamos session
		Session session = miFactory.openSession();
		
		try {
			//comenzar sesion
			session.beginTransaction();
			
			//consulta empleados
			List<Empleados> empleados = session.createQuery("from Empleados").getResultList();
			
			//mostrar empleados
			for(Empleados empleado : empleados) {
				System.out.println(empleado);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			miFactory.close();
		}
		
    }
}
