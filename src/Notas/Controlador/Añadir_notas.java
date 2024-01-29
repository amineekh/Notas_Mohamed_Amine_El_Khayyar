package Notas.Controlador;

// Clase Añadir_notas
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Añadir_notas extends JFrame {

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

    public Añadir_notas(){

    }
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


        aceptar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre= field_nombre.getText();
                String apellido= field_apellido.getText();
                String notas= field_nota.getText();

                if(!field_nombre.getText().isEmpty() && !field_nota.getText().isEmpty()){
                    Notas.agregar_fila(nombre, apellido, notas);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(Añadir_notas.this, "Por favor, rellene los campos obligatorios.");
                }

            }
        });

        cancelar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana sin realizar ninguna acción
                frame.dispose();
            }
        });


    }



}