package controller.Dao.servicies;

import controller.tda.list.LinkedList;
import models.Proyecto;
import controller.Dao.ProyectoDao;

public class ProyectoServicies {
    private ProyectoDao obj;

    // Constructor de ProyectoServicies, donde inicializamos el ProyectoDao
    public ProyectoServicies() {
        obj = new ProyectoDao();
    }

    // Método para guardar un proyecto
    public Boolean save() throws Exception {
        return obj.save();
    }

    // Método para actualizar un proyecto
    public Boolean update() throws Exception {
        return obj.update();
    }

    // Método para eliminar un proyecto por ID
    public Boolean delete(Integer id) throws Exception {
        return obj.delete(id);
    }

    // Método para obtener todos los proyectos
    public LinkedList<Proyecto> listAll() {
        return obj.getlistAll();
    }

    // Método para obtener un único proyecto
    public Proyecto getProyecto() {
        return obj.getProyecto();
    }

    // Método para establecer un proyecto
    public void setProyecto(Proyecto proyecto) {
        obj.setProyecto(proyecto);
    }

    // Obtener un proyecto por su ID
    public Proyecto get(Integer id) throws Exception {
        return (Proyecto) obj.get(id);
    }

    // Buscar proyectos por nombre
    public LinkedList<Proyecto> buscar_nombre(String texto) {
        return obj.buscar_nombre(texto);
    }

    // Buscar proyectos por inversionistas
    public LinkedList<Proyecto> buscar_inversionistas(String texto) {
        return obj.buscar_inversionistas(texto);
    }


    // Buscar proyectos por ubicación
    public LinkedList<Proyecto> buscar_ubicacion(String texto) {
        return obj.buscar_ubicacion(texto);
    }

    public LinkedList orderQuick(Integer type_order, String atributo) {
        return obj.orderQuick(type_order, atributo);
    }

    public LinkedList orderMerge(Integer type_order, String atributo) {
        return obj.orderMerge(type_order, atributo);
    }

    public LinkedList orderShell(Integer type_order, String atributo) {
        return obj.orderShell(type_order, atributo);
    }


    public LinkedList<Proyecto>buscarNombreLineal (String texto) {
        return obj.buscarNombreLineal(texto);
    }

    public LinkedList<Proyecto>buscarNombreBinario (String texto) {
        return obj.buscarNombreBinario(texto);
    }
    // Buscar proyectos por inversionistas
    public LinkedList<Proyecto> buscarInversionistasLineal(String texto) {
        return obj.buscarInversionistasLineal(texto);
    }


    // Buscar proyectos por ubicación
    public LinkedList<Proyecto> buscarInversionistaBinario(String texto) {
        return obj.buscarInversionistaBinario(texto);
    }

    public LinkedList<Proyecto> buscarUbicacionLineal(String texto) {
        return obj.buscarUbicacionLineal(texto);
    }


    // Buscar proyectos por ubicación
    public LinkedList<Proyecto> buscarUbicacionBinario(String texto) {
        return obj.buscarUbicacionBinario(texto);
    }
    
}
