package local.gonzalo.exame.examefinal;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author dammdprog1
 */
public abstract class Pregunta implements Comparable<Pregunta>, Serializable {

    public static final int DEFAULT_VALUE = 1;

    private Integer idPregunta;
    private TipoPregunta tipoPregunta;
    private final String enunciado;
    private double puntos;

    public Pregunta(String enunciado, double puntos) {
        if (puntos < 0) {
            throw new IllegalArgumentException("Los puntos deben ser mayores de 0");
        } else {
            this.enunciado = enunciado;
            this.puntos = puntos;
        }
    }

    public Pregunta(String enunciado) {
        this.enunciado = enunciado;
        this.puntos = DEFAULT_VALUE;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public TipoPregunta getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(TipoPregunta tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public double getPuntos() {
        return puntos;
    }

    public void setPuntos(double puntos) {
        if (puntos < 0) {
            throw new IllegalArgumentException("Los puntos deben ser mayores de 0");
        } else {
            this.puntos = puntos;
        }
    }

    public String getEnunciado() {
        return enunciado;
    }
    

    @Override
    public int hashCode() {
        return 17 * 3 + Objects.hashCode(this.idPregunta);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pregunta other = (Pregunta) obj;
        return Objects.equals(this.idPregunta, other.idPregunta);
    }

    @Override
    public String toString() {
        if (enunciado != null) {
            Character c = enunciado.charAt(0);
            String e = c.toString();
            String salida = e.toUpperCase() + enunciado.substring(1);
            return salida;
        }
        return "-";
    }

    @Override
    public int compareTo(Pregunta o) {
        return (o.idPregunta != null) ? idPregunta.compareTo(o.idPregunta) : -1;
    }

}
