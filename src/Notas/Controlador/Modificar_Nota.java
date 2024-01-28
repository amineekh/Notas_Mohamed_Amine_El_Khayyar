package Notas.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modificar_Nota {
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

    public Modificar_Nota() {


    }

    public void mostrarVentana(final Notas notas, String nombre, String apellido, String nota) {
        // Mostrar la ventana
        JFrame frame = new JFrame("Modificar Nota");
        frame.setContentPane(Mainpanel_modificar);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(0, 0, 300, 300);

        // Mostrar los datos en los textfields
        field_modificar_nombre.setText(nombre);
        field_modificar_apellido.setText(apellido);
        field_modificar_nota.setText(nota);

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

        cancelar_btn_modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Mainpanel_modificar);
                frame.dispose();
            }
        });
    }
}
