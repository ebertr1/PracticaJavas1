package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Proyecto {
    private int idProyecto;
    private String nombre;
    private double inversion;
    private int duracion;  // en años
    private String fechaInicio;
    private String fechaFin;
    private String inversionistas;
    private double generacionDiaria;  // electricidad generada diariamente en kWh
    private double costoOperativo;
    private String tipoEnergia;
    private String ubicacion;

    public Proyecto() {
    }

    // Constructor actualizado
    public Proyecto(int idProyecto, String nombre, double inversion, int duracion, String fechaInicio, String fechaFin,
                    String inversionistas, double generacionDiaria, double costoOperativo,
                    String tipoEnergia, String ubicacion) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.inversion = inversion;
        this.duracion = duracion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.inversionistas = inversionistas;
        this.generacionDiaria = generacionDiaria;
        this.costoOperativo = costoOperativo;
        this.tipoEnergia = tipoEnergia;
        this.ubicacion = ubicacion;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getInversionistas() {
        return inversionistas;
    }

    public void setInversionistas(String inversionistas) {
        this.inversionistas = inversionistas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getInversion() {
        return inversion;
    }

    public void setInversion(double inversion) {
        this.inversion = inversion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

  
    public double getGeneracionDiaria() {
        return generacionDiaria;
    }

    public void setGeneracionDiaria(double generacionDiaria) {
        this.generacionDiaria = generacionDiaria;
    }

    public double getCostoOperativo() {
        return costoOperativo;
    }

    public void setCostoOperativo(double costoOperativo) {
        this.costoOperativo = costoOperativo;
    }

    public String getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(String tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // Métodos para obtener información derivada
    public double calcularGeneracionTotal() {
        return generacionDiaria * duracion * 365;  // aprox. días en un año
    }

    public double calcularInversionTotal() {
        return inversion + (costoOperativo * duracion);
    }

    // Getters para valores calculados
    public double getGeneracionTotal() {
        return calcularGeneracionTotal();
    }

    public double getInversionTotal() {
        return calcularInversionTotal();
    }

    @Override
    public String toString() {
        return  ", nombre='" + nombre + '\'' +
                ", inversionistas='" + inversionistas + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                '}';

    
}
}
