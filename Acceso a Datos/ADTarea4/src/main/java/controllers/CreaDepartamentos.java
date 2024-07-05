package controllers;

import org.hibernate.*;


import org.hibernate.cfg.Configuration;

import dept.Departamentos;

public class CreaDepartamentos {

	public String creaDept(int id, String nombre) {
		//creamos un sessionfactory con el archivo de hibernate
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Departamentos.class).buildSessionFactory();
		//iniciamos session
		Session session = miFactory.openSession();
	
		try {
			//creamos un nuevo departamento
			Departamentos departamento1 = new Departamentos(id, nombre);
			//iniciamos el proceso
			session.beginTransaction();
			//persist, sustitúe ao anterior save()
			session.persist(departamento1);
			//finalizamos con un commit
			session.getTransaction().commit();
			
			session.close();
			
			return "Departamento creado";
			
		}catch (Exception e) {
			e.printStackTrace();
			miFactory.close();
					}
		return "Error al registrar departamento";
	
	}
}
