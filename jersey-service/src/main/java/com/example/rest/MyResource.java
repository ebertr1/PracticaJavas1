package com.example.rest;

import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import controller.tda.list.LinkedList;  // Asegúrate de que la LinkedList esté importada correctamente
import controller.Dao.servicies.ProyectoServicies;
import models.Proyecto;  // Ajusta el paquete de Proyecto si es necesario

@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {

        HashMap<String, Object> mapa = new HashMap<>();
        ProyectoServicies pd = new ProyectoServicies();
        String aux = "";

        try {
            // Verificamos si la lista de proyectos está vacía
            aux = "La lista de proyectos está vacía: " + pd.listAll().isEmpty();
            System.out.println("Verificación de lista vacía: " + aux);  // Depuración
            
            // Obtener lista de proyectos
            LinkedList<Proyecto> proyectos = pd.listAll(); // Suponemos que listAll devuelve LinkedList<Proyecto>
            System.out.println("Lista de proyectos: " + proyectos.toString()); // Depuración

            // Ordenar usando Merge Sort
            proyectos.mergesort("nombre", 1);
            System.out.println("Lista de proyectos ordenados por Merge Sort: " + proyectos.toString());

            // Ordenar usando Quick Sort
            proyectos.quicksort("nombre", 1);
            System.out.println("Lista de proyectos ordenados por Quick Sort: " + proyectos.toString());

            // Ordenar usando Shell Sort
            proyectos.shellSort("nombre", 1);
            System.out.println("Lista de proyectos ordenados por Shell Sort: " + proyectos.toString());

            // Crear y guardar proyectos como ejemplo
            pd.getProyecto().setNombre("Ariana Sophia");
            pd.getProyecto().setInversion(222);
            pd.getProyecto().setDuracion(44);
            pd.getProyecto().setFechaInicio("23-08-2005");
            pd.getProyecto().setFechaFin("23-08-2028");
            pd.getProyecto().setInversionistas("Maria");
            pd.getProyecto().setCostoOperativo(22);
            pd.getProyecto().setTipoEnergia("Solar");
            pd.getProyecto().setUbicacion("Perú");
            pd.save();

            // Crear un segundo proyecto y guardarlo
            pd.getProyecto().setNombre("Priana Sophia");
            pd.save();

            aux = "La lista contiene " + proyectos.getSize() + " proyectos.";

        } catch (Exception e) {
            e.printStackTrace();  // Muestra el stack trace completo
            mapa.put("msg", "Error");
            mapa.put("error", e.getMessage() != null ? e.getMessage() : "Error desconocido");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mapa).build();
        }

        mapa.put("msg", "Ok");
        mapa.put("data", aux);

        return Response.ok(mapa).build();
    }
}
