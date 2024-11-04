package com.example.rest;

import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;  // Import correcto para Response

import controller.Dao.servicies.ProyectoServicies;
// Importar tu LinkedList personalizada


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
    
    HashMap mapa = new HashMap<>();
    ProyectoServicies pd = new ProyectoServicies(); 
    String aux = "";
    try{
        pd.getProyecto().setNombre("Ariana Sophia");
        pd.getProyecto().setInversion(222);
        pd.getProyecto().setDuracion(44);
        pd.getProyecto().setFechaInicio("23-08-2005");
        pd.getProyecto().setFechaFin("23-08-2028");
        pd.getProyecto().setInversionistas("maria");
        pd.getProyecto().setCostoOperativo(22);
        pd.getProyecto().setTipoEnergia("solar");
        pd.getProyecto().setUbicacion("peru");
        pd.save();
        
    
        aux = " La lista esta vacia"+pd.listAll().isEmpty();
    } catch (Exception e){
        System.out.println("Error al guardar: "+e);
        // Todo 

    }
        mapa.put("msg", "Ok");
        mapa.put("data", "test "+aux);
        
        // Construir la respuesta correctamente
        return Response.ok(mapa).build();
    }
}
