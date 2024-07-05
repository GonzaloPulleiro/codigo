package com.mycompany.examengpj;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import javax.swing.*;

/**
 *
 * @author Gonzalo
 */
public class DictionaryView extends JFrame {

    private JTextField tfPalabra;
    private JButton btConsultar;
    private JTextArea taResultados;
    private WordDAO wordDAO;

    public void openConnection(String rutaArchivo) {
        Connection con = DictionaryConnection.getInstance().getConnection();
        wordDAO = new WordDAO(con, rutaArchivo);
    }

    public Word getWord(String palabra) {
        return wordDAO.get(palabra);
    }

    public void sair() {
        int value = JOptionPane.showConfirmDialog(this, "Queres sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (value == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    
    

    public DictionaryView() {
        super("Diccionario RAG");
        createGUI();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sair();
            }

        });

    }

    private void createGUI() {

        taResultados = new JTextArea();
        taResultados.setEditable(false);
        JScrollPane scrollP = new JScrollPane(taResultados);
        add(scrollP, BorderLayout.CENTER);

        JPanel panelTOP = new JPanel(new FlowLayout());
        tfPalabra = new JTextField(30);
        btConsultar = new JButton("Buscar");
        panelTOP.add(new JLabel("Palabra: "));
        panelTOP.add(tfPalabra);
        panelTOP.add(btConsultar);
        add(panelTOP, BorderLayout.NORTH);

        btConsultar.addActionListener(e -> {
            if (wordDAO != null) {
                buscarPalabra();
            } else {
                taResultados.setText("Por favor, seleccione una base de datos.");
            }
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        JMenuItem mnuSelectDB = new JMenuItem("Seleccionar BD");
        JMenuItem mnuSalir = new JMenuItem("Salir");

        menu.add(mnuSelectDB);
        menu.addSeparator();
        menu.add(mnuSalir);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        mnuSelectDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int value = chooser.showOpenDialog(DictionaryView.this);
                if (value == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    openConnection(file.getAbsolutePath());
                    tfPalabra.setEnabled(true);
                    btConsultar.setEnabled(true);
                    taResultados.setEnabled(true);
                }
            }
        });

        mnuSalir.addActionListener(e -> sair());

        tfPalabra.setEnabled(false);
        btConsultar.setEnabled(false);
        taResultados.setEnabled(false);

        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(new JPanel(), BorderLayout.SOUTH);
    }

    private void buscarPalabra() {
        String palabra = tfPalabra.getText().trim();
        if (palabra.isEmpty()) {
            taResultados.setText("Introduce una palabra.");
            return;

        }
        Word word = getWord(palabra);
        if (word != null) {
            taResultados.setText(word.toString() + "\nÚltimaconsulta: " + word.getUltimaConsultaAsString());
        } else {
            taResultados.setText("La palabra no está en la base de datos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DictionaryView().setVisible(true);
        });
    }

}
