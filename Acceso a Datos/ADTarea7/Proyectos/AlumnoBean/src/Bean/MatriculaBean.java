package Bean;

import java.sql.*;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Gonzalo
 */
public final class MatriculaBean implements Serializable {

    private PropertyChangeSupport propertySupport;
    private Connection con;
    private Matricula matricula = new Matricula();
    private Vector<Matricula> Matriculas = new Vector<Matricula>();
    private ModoModificadoListener receptor;

    public MatriculaBean() {
        propertySupport = new PropertyChangeSupport(this);
        try {
            recargarFilas();
        } catch (Exception e) {
        }
    }

    public String getDatosMatricula() {
        return matricula.toString();
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        this.con = DriverManager.getConnection("jdbc:mysql://localhost/alumnos", "root", "root");

    }

    public void recargarFilas() {

        try {
            getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from matricula");

            while (rs.next()) {
                Matricula m = new Matricula(rs.getString("DNI"), rs.getString("NombreModulo"), rs.getString("Curso"), rs.getDouble("Nota"));

                Matriculas.add(m);
            }
            Matricula m = new Matricula();
            m = (Matricula) Matriculas.elementAt(1);

            matricula.setDNI(m.getDNI());
            matricula.setNombreModulo(m.getNombreModulo());
            matricula.setCurso(m.getCurso());
            matricula.setNota(m.getNota());

            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
            matricula.setDNI("");
            matricula.setNombreModulo("");
            matricula.setCurso("");
            matricula.setNota(0.0);
            
            if(this.receptor != null){
                receptor.capturarConsultaGlobal(new ModoModificadoEvent(this));
            }
        } 

    }

    public void seleccionarFila(int i) {
        if (i <= Matriculas.size()) {
            Matricula m = new Matricula();
            m = (Matricula) Matriculas.elementAt(i);
            matricula.setDNI(m.getDNI());
            matricula.setNombreModulo(m.getNombreModulo());
            matricula.setCurso(m.getCurso());
            matricula.setNota(m.getNota());
        } else {
            matricula.setDNI("");
            matricula.setNombreModulo("");
            matricula.setCurso("");
            matricula.setNota(0.0);
        }

        receptor.capturarConsultaIndividual(new ModoModificadoEvent(this));
    }

    public void recargarDNI(String DNI) {
        Matricula m = new Matricula();
        int i = 0;

        matricula.setDNI("");
        matricula.setNombreModulo("");
        matricula.setCurso("");
        matricula.setNota(0.0);

        while (matricula.getDNI().equals("") && i <= Matriculas.size()) {
            m = (Matricula) Matriculas.elementAt(i);
            if (m.getDNI().equals(DNI)) {
                matricula.setDNI(m.getDNI());
                matricula.setNombreModulo(m.getNombreModulo());
                matricula.setCurso(m.getCurso());
                matricula.setNota(m.getNota());
            }
        }

    }
    
    public void addMatricula(String DNI, String nome, String curso, Double nota){
        
        try {
            getConnection();
            PreparedStatement ps = con.prepareStatement("insert into matricula values (?,?,?,?)");

            ps.setString(1, DNI);
            ps.setString(2, nome);
            ps.setString(3, curso);
            ps.setDouble(4, nota);

            ps.executeUpdate();
            con.close();

            receptor.capturarEngadeAlumno(new ModoModificadoEvent(this));
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    // Definimos métodos para engadir e eliminar ointes ao compoñente.
    public void addModoModificadoListener(ModoModificadoListener receptor){
        this.receptor = receptor;
    }
    public void removeModoModificadoListener(ModoModificadoEvent receptor){
        this.receptor = null;
    }
}
