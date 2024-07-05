package pruebamatriculabean;

import java.sql.SQLException;
import java.util.logging.*;

import Bean.MatriculaBean;
import Bean.ModoModificadoEvent;
import Bean.ModoModificadoListener;

/**
 *
 * @author Gonzalo
 */
public class PruebaMatricula implements ModoModificadoListener {
    
    MatriculaBean Matriculas;
    
    PruebaMatricula(){
        Matriculas = new MatriculaBean();
        Matriculas.addModoModificadoListener((ModoModificadoListener)this);
    }
    
    public void listado(){
        for (int i = 0; i < 4; i++) {
            Matriculas.seleccionarFila(i);
            System.out.println("\t" + Matriculas.getDatosMatricula());
            
        }
    }
    
    public void consultaListado(){
        
        try {
            Matriculas.recargarFilas();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    void engade(){
        
        try {
            Matriculas.addMatricula("46597815R", "DAM", "23-24", 7.9);
        } catch (Exception e) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    

    @Override
    public void capturarConsultaGlobal(ModoModificadoEvent ev) {
        System.out.println("Desde el programa de prueba se captura: capturarConsultaGlobal");
    }

    @Override
    public void capturarConsultaIndividual(ModoModificadoEvent ev) {
        System.out.println("Desde el programa de prueba se captura: capturarConsultaIndividual");
    }

    @Override
    public void capturarEngadeAlumno(ModoModificadoEvent ev) {
         System.out.println("Desde el programa de prueba se captura: capturarEngadeAlumno");
    }
    
    
}
