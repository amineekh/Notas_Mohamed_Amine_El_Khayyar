package Notas.Controlador;


import Notas.Modelo.Nota;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Notas extends JFrame{

    private JPanel Mainpanael;
    private JPanel Panel_titulo;
    private JPanel Panel_Tabla;
    private JPanel Panel_Botones;
    private JTable table;
    private JButton Añadir_boton;
    private JButton Modificar_boton;
    private JButton Eliminar_boton;
    private JLabel Titulo;
    private JButton vernota_boton;
    private JPanel panel_ayuda;
    private JButton ayudaButton;

    private  JTextField nombre_Field;
    private JTextField apellido_Field;
    private JTextField notas_Field;

    private static DefaultTableModel model;

    public static Nota notaseleccionada = null; // Usuario seleccionado, accesible desde otras clases

    private ArrayList<Nota> notas = new ArrayList<Nota>(); // Lista para almacenar objetos de Usuario

    public Notas() {
        model = new DefaultTableModel();


        crear_tabla();
        Configuracion_UI_botones();
        configurarMouseListener();  // Configurar el MouseListener para seleccionar toda la fila


    }


    private void Configuracion_UI_botones() {

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int seleccionar_fila = table.getSelectedRow();

                if (seleccionar_fila >= 0) {
                    Modificar_boton.setEnabled(true);
                    Eliminar_boton.setEnabled(true);
                    vernota_boton.setEnabled(true);
                } else {
                    Modificar_boton.setEnabled(false);
                    Eliminar_boton.setEnabled(false);
                    vernota_boton.setEnabled(false);

                }

            }
        });

        ayudaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar un mensaje de ayuda utilizando JOptionPane
                JOptionPane.showMessageDialog(Notas.this, "Para añadir nueva nota debes rellenar los campos obligatorios y para acitvar los botones de Modificar, Eliminar y Ver nota debes selecionar una fila de la tabla", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        // Configuracion de los botones
        Añadir_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Añadir_notas ventana = new Añadir_notas();
                ventana.mostrarventana(Notas.this);

            }
        });

        vernota_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow=  table.getSelectedRow();
                String nombre= (String) table.getValueAt(selectedRow, 0);
                String apellidos = (String) table.getValueAt(selectedRow, 1);
                String nota = (String) table.getValueAt(selectedRow, 2);

                notaseleccionada = new Nota (nombre, apellidos, nota);

                JFrame frame = new JFrame( "Ver Nota"); frame.setContentPane(new Ver_nota().Panel_ver);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                // Evitar que la ventana sea maximizable
                frame.setResizable(false);
                frame.pack();
                frame.setBounds(0,  0,  245, 300);
                frame.setVisible(true);

            }
        });

        Modificar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1 ) {
                    // Obtener los datos de la fila seleccionada
                    String nombre = (String) table.getValueAt(selectedRow, 0);
                    String apellido = (String) table.getValueAt(selectedRow, 1);
                    String nota = (String) table.getValueAt(selectedRow, 2);

                    // Crear instancia de Modificar_Nota y pasarle los datos
                    Modificar_Nota modificarNotaVentana = new Modificar_Nota();
                    modificarNotaVentana.mostrarVentana(Notas.this, nombre, apellido, nota);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila antes de modificar.");
                }
            }
        });

        Eliminar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                // Verifica si se ha seleccionado una fila antes de eliminarla
                if (selectedRow != -1) {
                    // Muestra una confirmación antes de eliminar la fila seleccionada
                    int confirm = JOptionPane.showConfirmDialog(Notas.this, "¿Está seguro de que desea eliminar este usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        model.removeRow(selectedRow); // Elimina la fila seleccionada del modelo de la tabla
                    }
                } else {
                    // Muestra un mensaje de advertencia si no se ha seleccionado ninguna fila para eliminar
                    JOptionPane.showMessageDialog(Notas.this, "Por favor, seleccione una fila para eliminar.");
                }
            }
        });

    }

    private void configurarMouseListener() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedRow = table.rowAtPoint(e.getPoint());
                    table.setRowSelectionInterval(selectedRow, selectedRow);

                    // También puedes agregar esta línea para asegurarte de que toda la fila esté seleccionada
                    table.setColumnSelectionInterval(0, table.getColumnCount() - 1);
                }
            }
        });
    }
    public JTable getTable() {
        return table;
    }

    public static void agregar_fila(String nombre, String apellido, String nota) {
        // Añade una nueva fila a la tabla con los datos proporcionados
        String[] fila = {nombre, apellido, nota};
        model.addRow(fila);
    }
    private void crear_tabla() {
        // Agregar columnas al modelo de la tabla si es necesario
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Nota");
        // Asignar el modelo a la tabla
        table.setModel(model);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Notas");
        frame.setContentPane(new Notas().Mainpanael);
        // Evitar que la ventana sea maximizable
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}


