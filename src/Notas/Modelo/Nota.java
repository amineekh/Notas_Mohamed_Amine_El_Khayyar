package Notas.Modelo;

public class Nota {

    private String Nombre;
    private String Apellido;
    private  String Nota;

    public Nota(String nombre, String apellido, String nota) {
        Nombre = nombre;
        Apellido = apellido;
        Nota = nota;
    }

    public Nota() {

    }



    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String nota) {
        Nota = nota;
    }


}
