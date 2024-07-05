package controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import empleado.Empleados;

public class ObtenerEmpleados {
	
	public String getEmpleado(int id) {
		//creamos un sessionfactory con el archivo de hibernate
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Empleados.class).buildSessionFactory();
		//iniciamos session
		Session session = miFactory.openSession();
		
		try {			
			//iniciamos el proceso
			session.beginTransaction();
			//Obtenemos el empleado que queremos mostrar
			Empleados empleado1 = session.get(Empleados.class, id);
			//finalizamos con un commit
			session.getTransaction().commit();
			
			session.close();
			
			return empleado1.toString();
			
		}catch (Exception e) {
			e.printStackTrace();
			miFactory.close();
		}
		return "Error";
	}
}