package local.gonzalo.exame.examefinal;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 *
 * @author dammdprog1
 */
public class PreguntaTest extends Pregunta implements Predicate<Integer>, Serializable {

    public static final int NUMERO_OPCIONES = 4;

    private final Opcion[] opciones;

    public PreguntaTest(String enunciado) {
        super(enunciado);
        this.opciones = new Opcion[NUMERO_OPCIONES];
    }

    public PreguntaTest(String enunciado, int opciones) {
        super(enunciado);
        this.opciones = new Opcion[opciones];
    }

    public Opcion[] getOpciones() {
        return opciones;
    }

    public boolean addOpcion(String enunciado, boolean correcta) {
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i] == null) {
                opciones[i] = new Opcion(enunciado, correcta);
            }
            return true;
        }
        return false;
    }

    public int getNumCorrectas() {

        return 0;
    }

    public int getPuntos(int[] marcadas) {
        for (int i = 0; i < marcadas.length; i++) {

        }
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();

    }

    @Override
    public boolean test(Integer seleccion) {
        return false;
    }

}
