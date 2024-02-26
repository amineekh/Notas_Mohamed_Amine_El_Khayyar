package Notas.Modelo;
// Definición de la clase Nota en el paquete Notas.Modelo
public class Nota {

    // Atributos de la clase
    private String Nombre;
    private String Apellido;
    private String Nota;

    // Constructor con parámetros para inicializar la nota con valores específicos
    public Nota(String nombre, String apellido, String nota) {
        Nombre = nombre;
        Apellido = apellido;
        Nota = nota;
    }

    // Constructor vacío
    public Nota() {

    }

    // Método getter para obtener el nombre
    public String getNombre() {
        return Nombre;
    }

    // Método setter para establecer el nombre
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    // Método getter para obtener el apellido
    public String getApellido() {
        return Apellido;
    }

    // Método setter para establecer el apellido
    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    // Método getter para obtener la nota
    public String getNota() {
        return Nota;
    }

    // Método setter para establecer la nota
    public void setNota(String nota) {
        Nota = nota;
    }
}
