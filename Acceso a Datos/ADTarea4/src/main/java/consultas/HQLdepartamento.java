package consultas;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dept.Departamentos;

public class HQLdepartamento {

	public static void main(String[] args) {
		//creamos un sessionfactory con el archivo de hibernate
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Departamentos.class).buildSessionFactory();
		//iniciamos session
		Session session = miFactory.openSession();
		
		try {
			//comenzar sesion
			session.beginTransaction();
			
			//consulta empleados
			List<Departamentos> departamento = session.createQuery("from Departamentos").getResultList();
			
			//mostrar empleados
			for(Departamentos dept : departamento) {
				System.out.println(dept);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			miFactory.close();
		}
		
    }
}