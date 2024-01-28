import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserApplication extends JFrame {

    // Declaración de variables para los componentes de la interfaz
    private JTextField nombre_Field, apellido_Field, DNI_Field, email_Field, pass_Field, idField;
    private JButton nuevo_Button, borrar_Button, modificar_Button, añadir_Button;
    private JTable table;
    private DefaultTableModel model;
    private int idCounter = 1;

    // Constructor de la clase UserApplication
    public UserApplication() {

        // Añadir márgenes a la ventana
        int marginSize = 20; // Tamaño del margen en píxeles
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(marginSize, marginSize, marginSize, marginSize));

        // Creación del panel para introducir datos de usuario
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        // Inicialización de los campos de texto y del campo de ID
        idField = new JTextField();
        idField.setEditable(false); // Hacemos el campo de ID no editable al inicio
        idField.setText(String.valueOf(idCounter)); // Mostramos el ID actual

        nombre_Field = new JTextField();
        apellido_Field = new JTextField();
        DNI_Field = new JTextField();
        email_Field = new JTextField();
        pass_Field = new JPasswordField();

        // Agregar etiquetas y campos de texto al panel
        inputPanel.add(new JLabel("ID"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Nombre"));
        inputPanel.add(nombre_Field);
        inputPanel.add(new JLabel("Apellidos"));
        inputPanel.add(apellido_Field);
        inputPanel.add(new JLabel("DNI (opcional)"));
        inputPanel.add(DNI_Field);
        inputPanel.add(new JLabel("Email"));
        inputPanel.add(email_Field);
        inputPanel.add(new JLabel("Contraseña (opcional)"));
        inputPanel.add(pass_Field);

        // Agregar el panel de entrada en la parte superior de la ventana
        add(inputPanel, BorderLayout.NORTH);

        // Creación del modelo de tabla y definición de la no edición de la columna ID
        model = new DefaultTableModel() {
            // Hacemos que la columna ID sea no editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // Hacemos que la columna ID sea no editable
            }
        };

        // Agregar columnas al modelo de la tabla
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("DNI");
        model.addColumn("Email");
        model.addColumn("Contraseña");

        // Inicialización de la tabla con el modelo de datos
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER); // Agregar la tabla en el centro de la ventana

        // Definir un DefaultTableCellRenderer para ocultar la contraseña en la tabla
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof String) {
                    setText("\u2022\u2022\u2022\u2022\u2022\u2022"); // Mostrar "••••••" en lugar del texto real
                } else {
                    super.setValue(value);
                }
            }
        };

        // Aplicar el renderer a la columna de contraseñas (índice 5)
        table.getColumnModel().getColumn(5).setCellRenderer(renderer);

        // Creación del panel para los botones y adición de los botones al panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        nuevo_Button = new JButton("Nuevo");
        borrar_Button = new JButton("Eliminar");
        modificar_Button = new JButton("Modificar");
        añadir_Button = new JButton("Añadir");

        buttonPanel.add(nuevo_Button);
        buttonPanel.add(borrar_Button);
        buttonPanel.add(modificar_Button);
        buttonPanel.add(añadir_Button);

        // Agregar el panel de botones en la parte inferior de la ventana
        add(buttonPanel, BorderLayout.SOUTH);

        // Acción para el botón Nuevo
        nuevo_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields(); // Limpia los campos de texto
            }
        });

        // Acción para el botón Añadir
        añadir_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica si los campos obligatorios están rellenos antes de agregar una nueva fila a la tabla
                if (!nombre_Field.getText().isEmpty() && !apellido_Field.getText().isEmpty() && !email_Field.getText().isEmpty() && !pass_Field.getText().isEmpty()) {
                    String[] rowData = {
                            idField.getText(),
                            nombre_Field.getText(),
                            apellido_Field.getText(),
                            DNI_Field.getText(),
                            email_Field.getText(),
                            pass_Field.getText()
                    };
                    model.addRow(rowData); // Añade una nueva fila con los datos introducidos en los campos
                    idCounter++; // Incrementa el contador de ID
                    idField.setText(String.valueOf(idCounter)); // Actualiza el campo de ID
                } else {
                    // Muestra un mensaje de advertencia si no se han rellenado los campos obligatorios
                    JOptionPane.showMessageDialog(UserApplication.this, "Por favor, rellene los campos obligatorios.");
                }
            }
        });

        // Acción para el botón Modificar
        modificar_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                // Verifica si se ha seleccionado una fila y si los campos obligatorios están rellenos antes de realizar la modificación
                if (selectedRow != -1 && !nombre_Field.getText().isEmpty() && !apellido_Field.getText().isEmpty() && !email_Field.getText().isEmpty() && !pass_Field.getText().isEmpty()) {
                    // Actualiza los valores de la fila seleccionada con los valores de los campos de texto
                    table.setValueAt(nombre_Field.getText(), selectedRow, 1);
                    table.setValueAt(apellido_Field.getText(), selectedRow, 2);
                    table.setValueAt(DNI_Field.getText(), selectedRow, 3);
                    table.setValueAt(email_Field.getText(), selectedRow, 4);
                    table.setValueAt(pass_Field.getText(), selectedRow, 5);

                } else {
                    // Muestra un mensaje de advertencia si no se ha seleccionado una fila o si los campos obligatorios no están rellenos
                    JOptionPane.showMessageDialog(UserApplication.this, "Por favor, seleccione una fila y rellene los campos obligatorios.");
                }
            }
        });

        // Acción para el botón Eliminar
        borrar_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                // Verifica si se ha seleccionado una fila antes de eliminarla
                if (selectedRow != -1) {
                    // Muestra una confirmación antes de eliminar la fila seleccionada
                    int confirm = JOptionPane.showConfirmDialog(UserApplication.this, "¿Está seguro de que desea eliminar este usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        model.removeRow(selectedRow); // Elimina la fila seleccionada del modelo de la tabla
                    }
                } else {
                    // Muestra un mensaje de advertencia si no se ha seleccionado ninguna fila para eliminar
                    JOptionPane.showMessageDialog(UserApplication.this, "Por favor, seleccione una fila para eliminar.");
                }
            }
        });

        pack(); // Ajusta el tamaño de la ventana
        setVisible(true); // Hace visible la ventana
    }

    // Método para limpiar los campos de texto del formulario
    private void clearFields() {
        nombre_Field.setText("");
        apellido_Field.setText("");
        DNI_Field.setText("");
        email_Field.setText("");
        pass_Field.setText("");
    }


    public static void main(String[] args) {
        UserApplication UserApplication= new UserApplication();
        // Configuración inicial de la ventana
        UserApplication.setTitle("User Application"); // Establece el título de la ventana
        UserApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura la operación al cerrar la ventana

        // Hacer visible la ventana
        UserApplication.setVisible(true);

    }
}
