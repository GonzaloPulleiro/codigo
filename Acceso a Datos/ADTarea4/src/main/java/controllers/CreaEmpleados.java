package controllers;

import org.hibernate.*;

import org.hibernate.cfg.Configuration;

import empleado.Empleados;

public class CreaEmpleados {
	
	public String creaEmpleado(String nombre, int departamento, double sueldo){
		//creamos un sessionfactory con el archivo de hibernate
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Empleados.class).buildSessionFactory();
		//iniciamos session
		Session session = miFactory.openSession();
		
		try {
			//creamos un nuevo empleado
			Empleados empleado = new Empleados(nombre, departamento, sueldo);
			//iniciamos el proceso
			session.beginTransaction();
			//persist, sustitúe ao anterior save()
			session.persist(empleado);
			//finalizamos con un commit
			session.getTransaction().commit();
			
			session.close();
			
			return "Empleado creado";
			
		}catch (Exception e) {
			e.printStackTrace();
			miFactory.close();
		
		}
		return "Error al registrar empleado";
	}
}
