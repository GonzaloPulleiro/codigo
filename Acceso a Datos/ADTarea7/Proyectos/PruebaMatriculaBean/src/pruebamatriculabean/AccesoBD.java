package pruebamatriculabean;

import alumnobean.*;
import java.sql.Date;
import java.util.logging.*;
/**
 *
 * @author Gonzalo
 */
public class AccesoBD implements AlumnoBean.BDModificadaListener{
    
    AlumnoBean alumnos;
    
    AccesoBD(){
        alumnos = new AlumnoBean();
        alumnos.addBDModificadaListener((AlumnoBean.BDModificadaListener)this);
    }
    
    public void listado(){
        
        for (int i = 0; i < 4; i++) {
            
            alumnos.seleccionarFila(i);
            System.out.println("Alumno " + i + "\n\tDNI: " + alumnos.getDNI());
            System.out.println("\tNombre: " +  alumnos.getNombre());
            System.out.println("\tApellidos: " +  alumnos.getApellidos());
            System.out.println("\tDireccion: " +  alumnos.getDireccion());
            System.out.println("\tFecha de nacimiento: " +  alumnos.getFechaNac());
            
        }
    }
    
    void engade (){
        
        alumnos.setDNI("46597815R");
        alumnos.setNombre("Gonzalo");
        alumnos.setApellidos("Pulleiro Juncal");
        alumnos.setDireccion("Rúa de San Clemente, sn");
        alumnos.setFechaNac(Date.valueOf("1994-06-20"));
        
        try {
            alumnos.addAlumno();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void capturarBDModificada(AlumnoBean.BDModificadaEvent ev) {
        System.out.println("Añadido un nuevo elemento a la base de datos");
    }
    
}
