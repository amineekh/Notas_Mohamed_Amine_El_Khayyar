// Importar paquetes y clases necesarias
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

// Definición de la clase, extiende JFrame para funcionalidad de GUI
public class Notas extends JFrame {

    // Variables de instancia para componentes Swing
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

    // Campos de texto para entrada de usuario
    private JTextField nombre_Field;
    private JTextField apellido_Field;
    private JTextField notas_Field;

    // Modelo estático compartido entre instancias de la clase Notas
    private static DefaultTableModel model;

    // Nota seleccionada, accesible desde otras clases
    public static Nota notaseleccionada = null;

    // Lista para almacenar objetos Nota
    private ArrayList<Nota> notas = new ArrayList<Nota>();

    // Constructor
    public Notas() {
        // Inicializar el modelo de la tabla
        model = new DefaultTableModel();

        // Crear la tabla y configurar la interfaz de usuario (UI)
        crear_tabla();
        Configuracion_UI_botones();
        configurarMouseListener();  // Configurar el MouseListener para seleccionar toda la fila
    }

    // Método para configurar botones y acciones de la UI
    private void Configuracion_UI_botones() {
        // Agregar un ListSelectionListener para habilitar/deshabilitar botones según la selección de fila
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int seleccionar_fila = table.getSelectedRow();

                // Habilitar/deshabilitar botones según la selección de fila
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

        // ActionListener para el botón de ayuda (ayudaButton)
        ayudaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar un mensaje de ayuda usando JOptionPane
                JOptionPane.showMessageDialog(Notas.this,
                        "Para añadir nueva nota debes rellenar los campos obligatorios y para activar los botones de Modificar, Eliminar y Ver nota debes seleccionar una fila de la tabla",
                        "Ayuda", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // ActionListener para el botón Añadir (Añadir_boton)
        Añadir_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir una nueva ventana para agregar notas
                Añadir_notas ventana = new Añadir_notas();
                ventana.mostrarventana(Notas.this);
            }
        });

        // ActionListener para el botón Ver nota (vernota_boton)
        vernota_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener datos de la fila seleccionada y crear un objeto Nota
                int selectedRow = table.getSelectedRow();
                String nombre = (String) table.getValueAt(selectedRow, 0);
                String apellidos = (String) table.getValueAt(selectedRow, 1);
                String nota = (String) table.getValueAt(selectedRow, 2);

                notaseleccionada = new Nota(nombre, apellidos, nota);

                // Crear y mostrar un nuevo JFrame para ver la nota
                JFrame frame = new JFrame("Ver Nota");
                frame.setContentPane(new Ver_nota().Panel_ver);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setResizable(false);
                frame.pack();
                frame.setBounds(0, 0, 245, 300);
                frame.setVisible(true);
            }
        });

        // ActionListener para el botón Modificar (Modificar_boton)
        Modificar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener datos de la fila seleccionada
                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {
                    // Obtener datos de la fila seleccionada
                    String nombre = (String) table.getValueAt(selectedRow, 0);
                    String apellido = (String) table.getValueAt(selectedRow, 1);
                    String nota = (String) table.getValueAt(selectedRow, 2);

                    // Crear una instancia de Modificar_Nota y pasarle los datos
                    Modificar_Nota modificarNotaVentana = new Modificar_Nota();
                    modificarNotaVentana.mostrarVentana(Notas.this, nombre, apellido, nota);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila antes de modificar.");
                }
            }
        });

        // ActionListener para el botón Eliminar (Eliminar_boton)
        Eliminar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();

                // Verificar si se ha seleccionado una fila antes de intentar eliminar
                if (selectedRow != -1) {
                    // Mostrar una confirmación antes de eliminar la fila seleccionada
                    int confirm = JOptionPane.showConfirmDialog(Notas.this,
                            "¿Está seguro de que desea eliminar este usuario?",
                            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        model.removeRow(selectedRow); // Eliminar la fila seleccionada del modelo de la tabla
                    }
                } else {
                    // Mostrar un mensaje de advertencia si no se ha seleccionado ninguna fila para eliminar
                    JOptionPane.showMessageDialog(Notas.this, "Por favor, seleccione una fila para eliminar.");
                }
            }
        });
    }


    // Método privado para configurar un MouseListener en la tabla
    private void configurarMouseListener() {
        // Agregar un MouseListener a la tabla
        table.addMouseListener(new MouseAdapter() {
            @Override
            // Sobrescribir el método mouseClicked del MouseAdapter
            public void mouseClicked(MouseEvent e) {
                // Verificar si se hizo clic una vez
                if (e.getClickCount() == 1) {
                    // Obtener la fila seleccionada en función de la posición del clic
                    int selectedRow = table.rowAtPoint(e.getPoint());
                    // Establecer la selección de fila en la tabla
                    table.setRowSelectionInterval(selectedRow, selectedRow);

                    // También puedes agregar esta línea para asegurarte de que toda la fila esté seleccionada
                    table.setColumnSelectionInterval(0, table.getColumnCount() - 1);
                }
            }
        });
    }

    // Método para obtener acceso a la tabla
    public JTable getTable() {
        return table;
    }

    // Método para agregar una nueva fila a la tabla
    public static void agregar_fila(String nombre, String apellido, String nota) {
        // Añadir una nueva fila a la tabla con los datos proporcionados
        String[] fila = {nombre, apellido, nota};
        model.addRow(fila);
    }

    // Método para crear la tabla
    private void crear_tabla() {
        // Agregar columnas al modelo de la tabla si es necesario
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Nota");
        // Asignar el modelo a la tabla
        table.setModel(model);
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        // Crear un JFrame y configurar su panel de contenido con una instancia de la clase Notas
        JFrame frame = new JFrame("Notas");
        frame.setContentPane(new Notas().Mainpanael);
        // Evitar que la ventana sea maximizable
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
