package controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import empleado.Empleados;

public class ActualizaEmpleados {
	
public String updateEmpleado(int id, String nombre){
	//creamos un sessionfactory con el archivo de hibernate
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Empleados.class).buildSessionFactory();
		//iniciamos session
		Session session = miFactory.openSession();
		
		try {	
			//iniciamos el proceso
			session.beginTransaction();
			//Obtenemos el empleado que queremos actualizar
			Empleados empleado1 = session.get(Empleados.class, id);
			//establecemos un nuevo nombre para el
			empleado1.setNombre(nombre);
			//merge, sustitúe ao anterior update()
			session.merge(empleado1);
			//finalizamos con un commit
			session.getTransaction().commit();
			
			session.close();
			
			return "Empleado actualizado";
			
		}catch (Exception e) {
			e.printStackTrace();
			
			miFactory.close();
		}
		return "Error al actualizar empleado";
	}
}

