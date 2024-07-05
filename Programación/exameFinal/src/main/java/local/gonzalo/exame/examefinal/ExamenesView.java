package local.gonzalo.exame.examefinal;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.*;

/**
 *
 * @author dammdprog1
 */
public class ExamenesView extends JFrame {

    private ExamenDAO examenDAO;
    private Connection conexion;
    private Examen examen;
    private JTextField[] tfOpciones;
    private JCheckBox[] cbCorrectas;
    private JTextField tfEnunciado;

    public void sair() {
        int value = JOptionPane.showConfirmDialog(this, "¿Quieres salir?", "Salir del gestor de exámenes", JOptionPane.OK_CANCEL_OPTION);
        if (value == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    public ExamenesView() {
        super("Gestión de exámenes");
        createGUI();
    }

    public void createGUI() {
        examenDAO = new ExamenDAO(conexion);

        JMenuBar mnuBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        JMenuItem mnuSelect = new JMenuItem("Seleccionar examen");
        JMenuItem mnuSalir = new JMenuItem("Salir");

        mnuSalir.addActionListener((e) -> {
            sair();
        });

        menu.add(mnuSelect);
        menu.addSeparator();
        menu.add(mnuSalir);

        mnuBar.add(menu);

        setJMenuBar(mnuBar);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sair();
            }

        });

        add(new JPanel(), BorderLayout.LINE_END);
        add(new JPanel(), BorderLayout.LINE_START);
        
        
        JPanel panel = new JPanel();
        JLabel enun = new JLabel("Enunciado: ");
        panel.add(enun);
        
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main (String[] args) {
        new ExamenesView();
    }

}
