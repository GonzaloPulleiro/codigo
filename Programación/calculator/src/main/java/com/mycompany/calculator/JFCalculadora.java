package com.mycompany.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JFCalculadora extends JFrame implements KeyListener {

    public static ImageIcon icono;

    /**
     * Campo de texto para mostrar los números.
     */
    private final JTextField display;

    /**
     * Operando 1 y 2. Llegaría con un solo operando, pero lo hago así para que
     * se vea más claro.
     */
    private double operando1;
    private double operando2;

    /**
     * Operación a realizar.
     */
    private char operacion;

    /**
     * Indica si tengo que limpiar el display.
     */
    private boolean tenhoQueLimpar = true;

    private StringBuilder inputBuffer;

    public JFCalculadora(String title) throws HeadlessException {

        super(title);

        try {
            if (icono == null) {
                try {
                    icono = new ImageIcon(getClass().getResource("/images/calculator24.png"));
                } catch (NullPointerException | ExceptionInInitializerError ex) {
                    System.out.println("Error al cargar icono");
                }
            }
            setIconImage(icono.getImage());
        } catch (NullPointerException ne) {
            System.err.println("Error al cargar icono... " + ne.getMessage());
        }

        // Guardo los controles en una cadena (podría ser un array)
        String controles = "123+456-789=";

        // La ventana es borderLayout de espacio horizontal 1 y vertical 5.
        setLayout(
                new BorderLayout(1, 5));

        // No hago nada al pulsar la X.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        // Se hace con un WindowAdapter porque no se puede hacer con una expresión lambda. Con una clase anónima que here de WindowAdapter
        // solo preciso el método windowClosing o los que necesite.

        addWindowListener(
                new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e
            ) {
                sair();
            }
        }
        );

        /*
        Aquí debéis completar la base de menú y añadirla a la ventana:
        - Crear un objeto JMenuBar.
        - Crear un objeto JMenu con el texto "Archivo".
        Además, debe tener dos JMenuItem: uno con "Acerca de..." y otro con "Salir".
        Al pulsar sobre "Acerca de..." se debe mostrar un JOptionPane con un mensaje.
        Al pulsar sobre "Salir" se debe mostrar un JOptionPane con un mensaje y, si se pulsa sobre "Sí",
        se sale de la aplicación.
         */
        JMenuBar jmb = new JMenuBar();
        JMenu menu = new JMenu("Arquivo");

        JMenuItem jmi = new JMenuItem("Acerca de...");
        JMenuItem jmi2 = new JMenuItem("Salir.");

        jmi.addActionListener(e
                -> {
            JOptionPane.showMessageDialog(this, "Versión 0.0.2 de calculator. By Gonzalo.");
        }
        );

        jmi2.addActionListener(e
                -> sair());

        menu.add(jmi);

        menu.add(jmi2);

        jmb.add(menu);

        setJMenuBar(jmb);

        // Campo de texto para mostrar los números.
        display = new JTextField("0");

        display.setHorizontalAlignment(SwingConstants.RIGHT);

        display.setBackground(
                new Color(255, 255, 100));
        display.setEditable(
                false);
        // A modo de curiosidad, se puede cambiar el color de fondo del campo de texto cuando tiene el foco.
        display.addFocusListener(
                new FocusListener() {
            @Override
            public void focusGained(FocusEvent e
            ) {
                display.setBackground(new Color(220, 220, 150));

            }

            @Override
            public void focusLost(FocusEvent e
            ) {
                display.setBackground(new Color(255, 255, 150));
            }
        }
        );

        display.addKeyListener(this);

        // Añado el campo de texto al panel superior.
        add(display, BorderLayout.PAGE_START);

        // Panel central (CENTER) con los botones.
        JPanel pCentro = new JPanel();
        // El panel central es un gridLayout con tantas filas como caracteres tenga la cadena de controles dividido por 4 y 4 columnas.

        pCentro.setLayout(
                new GridLayout(controles.length() / 4, 4, 2, 1));
        for (int i = 0;
                i < controles.length();
                i++) {
            JButton bt = getBoton(controles, i); // creo un botón con el carácter de la cadena.
            pCentro.add(bt); // Añado el botón al panel central.
        }
        // Añado el panel central al centro del borderLayout.

        add(pCentro);

        // Panel sur (PAGE_END) con un mensaje. Es equivalente a SOUTH.
        JPanel pSur = new JPanel();
        JLabel msg = new JLabel("<html>"
                + "<i style=\"color: rgb(150,150,0);\">"
                + "Calculadora multiplataforma"
                + " <strong style=\"color: green;\">"
                + "ver 0.02</strong></i></html>");

        pSur.add(msg);

        add(pSur, BorderLayout.PAGE_END);

        add(
                new JPanel(), BorderLayout.LINE_END);
        add(
                new JPanel(), BorderLayout.LINE_START);

        // Configuración de la ventana: tamaño, posición, visibilidad, etc.
        pack();

        setResizable(
                false);
        setLocationRelativeTo(
                null);
        setVisible(
                true);

    }

    /**
     * Método que crea un botón con el carácter de la cadena de controles. Al
     * extraer el código de creación de botones a un método se simplifica el
     * código y se evita repetir código.
     *
     * @param controles cadena con los caracteres de los botones.
     * @param i posición del carácter en la cadena.
     * @return un botón con el carácter de la cadena.
     */
    private JButton getBoton(String controles, int i) {
        JButton bt = new JButton(String.valueOf(controles.charAt(i))); // Creo el botón con el carácter.
        /* Añado un actionListener al botón. En este caso, una expresión lambda, pues ActionListener es una
            interfaz funcional que sólo tiene un método abstracto, actionPerformed, el que se implementa en la
            expresión lambda. */
        bt.addActionListener(e -> {
            JButton b = (JButton) e.getSource(); // Obtengo el botón pulsado.
            char caracter = b.getText().charAt(0); // Obtengo el carácter del botón.
            // Aquí iría el código que hace la operación, dependiendo del carácter pulsado.
            // Completa el código con una estructura switch que haga las operaciones básicas de una calculadora.
            // Si el carácter es un número, lo añado al campo de texto.
            if (Character.isDigit(caracter) || caracter == '.') {
                if (tenhoQueLimpar) {
                    display.setText("");
                    tenhoQueLimpar = false;
                }
                display.setText(display.getText() + caracter); // Añado el carácter al campo de texto.
            } else {
                switch (caracter) {
                    case '+':
                    case '-':
                        if (!tenhoQueLimpar) {
                            operando1 = Double.parseDouble(display.getText());
                            operacion = caracter;
                            tenhoQueLimpar = true;
                        }
                        break;
                    case '=':
                        if (!tenhoQueLimpar) {
                            operando2 = Double.parseDouble(display.getText());
                            double res = 0;
                            switch (operacion) {
                                case '+':
                                    res = operando1 + operando2;
                                    break;
                                case '-':
                                    res = operando1 - operando2;
                                    break;
                            }
                            display.setText(Double.toString(res));
                            tenhoQueLimpar = true;
                        }
                }

            }
        });
        return bt;
    }

    public void sair() {
        int valor = JOptionPane.showConfirmDialog(this, "Queres saír?", "Saír",
                JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        /*
        A modo de curiosidad, se puede cambiar la apariencia de la aplicación. Para ello, se puede hacer con el método
        setLookAndFeel de la clase UIManager. Nimbus es un tema que se puede usar en
        cualquier sistema operativo. Se puede cambiar el color de fondo de los botones, el color de fondo de la ventana, etc.

         */
        try {
            UIManager.put("nimbusBase", new Color(100, 100, 100));
            UIManager.put("nimbusBlueGrey", Color.LIGHT_GRAY);
            UIManager.put("control", new Color(255, 255, 200));
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("Error al cargar el tema Nimbus");
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            new JFCalculadora("Calculadora");

        });

    }

    /**
     * Al pulsar sobre la caja de texto comprueba que sea un número y lo añade
     * al campo de texto. Si no es un número, no hace nada. Si es un número o
     * una coma, lo añade al campo de texto, sólo una coma.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char caracter = e.getKeyChar();

        // COMPLETA EL CÓDIGO PARA QUE SOLO SE AÑADAN NÚMEROS Y UNA COMA.
        if (Character.isDigit(caracter) || caracter == ',') {
            if (tenhoQueLimpar) {
                display.setText("");
                tenhoQueLimpar = false;
            }
            display.setText(display.getText() + caracter);

        } else if ((caracter == '+' || caracter == '-') && !tenhoQueLimpar) {

            operando1 = Double.parseDouble(display.getText());
            operacion = caracter;
            tenhoQueLimpar = true;

        } else if (caracter == '=') {
            if (!tenhoQueLimpar) {
                operando2 = Double.parseDouble(display.getText());
                double resultado = 0;
                switch (operacion) {
                    case '+':
                        resultado = operando1 + operando2;
                        break;
                    case '-':
                        resultado = operando1 - operando2;
                        break;
                }
                display.setText(Double.toString(resultado));
                tenhoQueLimpar = true;

            }

        }
    }

    /**
     * Al pulsar ENTER, se realiza la operación.
     *
     * @param e evento de teclado
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // COMPLETA EL CÓDIGO PARA QUE SE REALICEN LAS OPERACIONES al pulsar ENTER.

            if (!tenhoQueLimpar) {
                operando2 = Double.parseDouble(display.getText());
                double resultado = 0;

                switch (operacion) {
                    case '+':
                        resultado = operando1 + operando2;
                        break;
                    case '-':
                        resultado = operando1 - operando2;
                        break;
                }
                display.setText(Double.toString(resultado));
                tenhoQueLimpar = true;
            }

        }

    }

    /**
     * No se hace nada al soltar la tecla.
     *
     * @param e evento de teclado
     */
    @Override
    public void keyReleased(KeyEvent e) {
        /*
        No se hace nada al soltar la tecla.
         */
    }

}
