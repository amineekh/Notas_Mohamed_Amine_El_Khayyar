package Notas.Controlador;

// Importar paquetes y clases necesarias
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Definición de la clase Añadir_notas
class Añadir_notas extends JFrame {

    // Componentes de la interfaz de usuario (UI)
    private JPanel Panel_1;
    private JPanel panel_ingresar;
    private JPanel panel_botones;
    JTextField field_nombre;
    JTextField field_apellido;
    JTextField field_nota;
    private JPanel panel_nombre;
    private JPanel panel_apellido;
    private JPanel panel_nota;
    private JLabel label_nombre;
    private JLabel label_apellido;
    private JLabel label_nota;
    private JButton aceptar_boton;
    private JButton cancelar_boton;
    private JPanel Panel_titulo;
    private JLabel Ingresar_Nota;

    // Constructor vacío
    public Añadir_notas(){

    }

    // Método para mostrar la ventana de añadir notas
    public void mostrarventana(final Notas notas){

        // Crea un nuevo JFrame para mostrar la ventana
        JFrame frame = new JFrame("Añadir Nota");
        frame.setContentPane(Panel_1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana al cerrarla
        frame.pack();
        // Evitar que la ventana sea maximizable
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setBounds(0,0,300,300);

        // ActionListener para el botón de aceptar
        aceptar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener valores de los campos de texto
                String nombre = field_nombre.getText();
                String apellido = field_apellido.getText();
                String notas = field_nota.getText();

                // Verificar que los campos obligatorios no estén vacíos
                if(!field_nombre.getText().isEmpty() && !field_nota.getText().isEmpty()){
                    // Llamar al método agregar_fila de la clase Notas para agregar una nueva fila a la tabla
                    Notas.agregar_fila(nombre, apellido, notas);
                    // Cerrar la ventana después de agregar la nota
                    frame.dispose();
                } else {
                    // Mostrar mensaje de advertencia si los campos obligatorios están vacíos
                    JOptionPane.showMessageDialog(Añadir_notas.this, "Por favor, rellene los campos obligatorios.");
                }
            }
        });

        // ActionListener para el botón de cancelar
        cancelar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana sin realizar ninguna acción
                frame.dispose();
            }
        });
    }
}
