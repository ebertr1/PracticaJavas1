package models;

public class Inversionista {
    private int idInversionista;
    private String nombre;
    private String apellido;
    private String DNI;

    public Inversionista(String nombre) {
        this.nombre = nombre;
    }
    
    public int getIdInversionista() {
        return idInversionista;
    }

    public void setIdInversionista(int idInversionista) {
        this.idInversionista = idInversionista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }

    public Inversionista() {
    }

    public Inversionista(int idInversionista, String nombre, String apellido, String DNI) {
        this.idInversionista = idInversionista;
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
    }
    
    // Getters and Setters
    
}


