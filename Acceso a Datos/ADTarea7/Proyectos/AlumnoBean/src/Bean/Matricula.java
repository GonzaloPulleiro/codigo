package Bean;

import java.io.Serializable;

/**
 *
 * @author Gonzalo
 */
public class Matricula implements Serializable {

    private String DNI;
    private String NombreModulo;
    private String Curso;
    private Double Nota;

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombreModulo() {
        return NombreModulo;
    }

    public void setNombreModulo(String NombreModulo) {
        this.NombreModulo = NombreModulo;
    }

    public String getCurso() {
        return Curso;
    }

    public void setCurso(String Curso) {
        this.Curso = Curso;
    }

    public Double getNota() {
        return Nota;
    }

    public void setNota(Double Nota) {
        this.Nota = Nota;
    }

    public Matricula() {
    }

    public Matricula(String DNI, String NombreModulo, String Curso, Double Nota) {
        this.DNI = DNI;
        this.NombreModulo = NombreModulo;
        this.Curso = Curso;
        this.Nota = Nota;
    }

    @Override
    public String toString() {
        return "Matricula{" + "[ DNI=" + DNI + ", NombreModulo=" + NombreModulo + ", Curso=" + Curso + ", Nota=" + Nota + " ]";
    }

}
