package Notas.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Definición de la clase Modificar_Nota
public class Modificar_Nota {
    // Componentes de la interfaz de usuario (UI)
    private JPanel Mainpanel_modificar;
    private JPanel Panel_titulo;
    private JPanel Panel_modificar;
    private JPanel Panel_boton;
    private JTextField field_modificar_nombre;
    private JTextField field_modificar_apellido;
    private JTextField field_modificar_nota;
    private JLabel label_modificar_nombre;
    private JLabel label_modificar_apellido;
    private JLabel label_modificar_nota;
    private JButton aceptar_btn_modificar;
    private JButton cancelar_btn_modificar;
    private JLabel Titulo_Modificar_label;

    // Constructor vacío
    public Modificar_Nota() {

    }

    // Método para mostrar la ventana de modificación de nota
    public void mostrarVentana(final Notas notas, String nombre, String apellido, String nota) {
        // Mostrar la ventana
        JFrame frame = new JFrame("Modificar Nota");
        frame.setContentPane(Mainpanel_modificar);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        // Evitar que la ventana sea maximizable
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setBounds(0, 0, 300, 300);

        // Mostrar los datos en los textfields
        field_modificar_nombre.setText(nombre);
        field_modificar_apellido.setText(apellido);
        field_modificar_nota.setText(nota);

        // ActionListener para el botón de aceptar
        aceptar_btn_modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los nuevos datos
                String nuevoNombre = field_modificar_nombre.getText();
                String nuevoApellido = field_modificar_apellido.getText();
                String nuevaNota = field_modificar_nota.getText();

                // Verificar campos obligatorios
                if (nuevoNombre.isEmpty() || nuevaNota.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Los campos 'Nombre' y 'Nota' son obligatorios.",
                            "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;  // No continuar con la modificación si faltan campos obligatorios
                }

                // Modificar los datos en la tabla
                int selectedRow = notas.getTable().getSelectedRow();
                notas.getTable().setValueAt(nuevoNombre, selectedRow, 0);
                notas.getTable().setValueAt(nuevoApellido, selectedRow, 1);
                notas.getTable().setValueAt(nuevaNota, selectedRow, 2);

                // Cerrar la ventana
                frame.dispose();
            }
        });

        // ActionListener para el botón de cancelar
        cancelar_btn_modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Mainpanel_modificar);
                frame.dispose();
            }
        });
    }
}
