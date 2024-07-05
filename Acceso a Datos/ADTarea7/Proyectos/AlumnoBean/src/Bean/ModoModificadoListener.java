package Bean;

import java.util.EventListener;

/**
 *
 * @author Gonzalo
 */
public interface ModoModificadoListener extends EventListener {

    public void capturarConsultaGlobal (ModoModificadoEvent ev);

    public void capturarConsultaIndividual(ModoModificadoEvent ev);

    public void capturarEngadeAlumno(ModoModificadoEvent ev);
}
