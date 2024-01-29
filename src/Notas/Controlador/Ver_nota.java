package Notas.Controlador;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Notas.Controlador.Notas.notaseleccionada;

public class Ver_nota extends  JFrame{

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

    public Ver_nota(){
        nombre.setText(notaseleccionada.getNombre());
        apellido.setText(notaseleccionada.getApellido());
        nota.setText(notaseleccionada.getNota());



        Boton_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana
                JOptionPane.showMessageDialog(null, "Esos son todos los datos de la nota selecionada",
                        "Nota", JOptionPane.WARNING_MESSAGE);


            }
        });
    }




}
