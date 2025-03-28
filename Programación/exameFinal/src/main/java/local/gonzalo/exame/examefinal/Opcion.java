package local.gonzalo.exame.examefinal;

import java.io.Serializable;

/**
 *
 * @author dammdprog1
 */
public class Opcion implements Serializable {

    private final String enunciado;
    private final boolean correcta;

    public Opcion(String enunciado, boolean correcta) {
        this.enunciado = enunciado;
        this.correcta = correcta;
    }

    public Opcion(String enunciado) {
        this.enunciado = enunciado;
        this.correcta = false;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    @Override
    public String toString() {
        if (enunciado != null && enunciado.length() > 20) {
            if (correcta == true) {
                return enunciado.substring(0, 20) + "[*]";
            } else {
                return enunciado;
            }
        } else if (enunciado != null) {
            if (correcta == true) {
                return enunciado + "[*]";
            }
        }
        return enunciado;
    }
}
