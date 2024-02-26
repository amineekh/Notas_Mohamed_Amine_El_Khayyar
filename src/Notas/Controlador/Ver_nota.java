package Notas.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Notas.Controlador.Notas.notaseleccionada;

// Definición de la clase Ver_nota, que extiende JFrame
public class Ver_nota extends JFrame {

    // Componentes de la interfaz de usuario (UI)
    JPanel Panel_ver;
    private JLabel Info_nota;
    private JButton Boton_ok;
    private JPanel Panel_ttulo;
    private JPanel Panel_boton;
    private JPanel Panel_datosnota;
    private JLabel nombre;
    private JLabel apellido;
    private JLabel nota;
    private JLabel label_nombre;
    private JLabel label_apellido;
    private JLabel label_nota;

    // Constructor
    public Ver_nota() {
        // Configurar los datos en las etiquetas de la interfaz de usuario
        nombre.setText(notaseleccionada.getNombre());
        apellido.setText(notaseleccionada.getApellido());
        nota.setText(notaseleccionada.getNota());

        // ActionListener para el botón OK
        Boton_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar un mensaje de advertencia al hacer clic en OK
                JOptionPane.showMessageDialog(null, "Esos son todos los datos de la nota seleccionada",
                        "Nota", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}



