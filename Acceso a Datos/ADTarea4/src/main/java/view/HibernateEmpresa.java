package view;

import controllers.*;

public class HibernateEmpresa {

	public static void main(String[] args) {

	String empleado = new ObtenerEmpleados().getEmpleado(2);
	String empleado1 = new ObtenerEmpleados().getEmpleado(3);
	String empleado2 = new ObtenerEmpleados().getEmpleado(4);
	
	System.out.println(empleado);
	System.out.println(empleado1);
	System.out.println(empleado2);

	}

}
