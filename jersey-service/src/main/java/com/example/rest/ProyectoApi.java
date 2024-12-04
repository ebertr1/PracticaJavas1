package com.example.rest;

import controller.Dao.ProyectoDao;
import controller.Dao.Exception.ListEmptyException;
import controller.Dao.servicies.InversionistaServicies;
import controller.Dao.servicies.ProyectoServicies;
import controller.tda.list.LinkedList;
import models.Inversionista;
import models.Proyecto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import com.google.gson.Gson;

@Path("proyecto")
public class ProyectoApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProyectos() {
        HashMap map = new HashMap<>();
        ProyectoServicies ps = new ProyectoServicies();
        map.put("msg", "Ok");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyecto(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
            ProyectoServicies ps = new ProyectoServicies();
        try {
            ps.setProyecto(ps.get(id));
        } catch (Exception e) {

        }

        map.put("msg", "Ok");
        map.put("data", ps.getProyecto());

        if (ps.getProyecto() == null || ps.getProyecto().getIdProyecto() == 0) {
            map.put("msg", "No se encontró Proyecto con ese identificador");
            return Response.status(Status.NOT_FOUND).entity(map).build();
        }

        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }

    
    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, Object> map) {
        HashMap<String, String> res = new HashMap<>();
        Gson gson = new Gson();
        String jsonString = gson.toJson(map);
        System.out.println("***** " + jsonString);
    
        try {
            ProyectoServicies ps = new ProyectoServicies();
            Proyecto proyecto = ps.getProyecto();
            proyecto.setNombre(map.get("nombre").toString());
            proyecto.setInversionistas(map.get("inversionistas").toString()); // Corregido: "inversionistas"
            proyecto.setInversion(Double.parseDouble(map.get("inversion").toString()));
            proyecto.setDuracion(Integer.parseInt(map.get("duracion").toString()));
            proyecto.setFechaInicio(map.get("fechaInicio").toString());
            proyecto.setFechaFin(map.get("fechaFin").toString()); // Corregido: "fechaFin"
            proyecto.setGeneracionDiaria(Double.parseDouble(map.get("generacionDiaria").toString()));
            proyecto.setCostoOperativo(Double.parseDouble(map.get("costoOperativo").toString()));
            proyecto.setTipoEnergia(map.get("tipoEnergia").toString());
            proyecto.setUbicacion(map.get("ubicacion").toString());
            ps.save();
    
            res.put("msg", "Ok");
            res.put("data", "Guardado correctamente");
            return Response.ok(res).build();
    
        } catch (NumberFormatException e) {
            // Manejo específico para errores de conversión de número
            res.put("msg", "Error");
            res.put("data", "Formato de número inválido: " + e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        } catch (Exception e) {
            // Manejo general de excepciones
            e.printStackTrace();
            res.put("msg", "Error");
            res.put("data", "Error en save data: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
    
    @Path("/listType")

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap map = new HashMap<>();
        ProyectoServicies ps = new ProyectoServicies();
        map.put("msg", "Ok");
        map.put("data", ps.getProyecto());
        return Response.ok(map).build();
    }

    @Path("/update")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response update(HashMap<String, Object> map) {
    HashMap<String, String> res = new HashMap<>();

    try {
        ProyectoServicies ps = new ProyectoServicies();
        int idProyecto = Integer.parseInt(map.get("idProyecto").toString());
        Proyecto proyecto = ps.get(idProyecto);

        if (proyecto != null) {
            ps.setProyecto(proyecto);
            ps.getProyecto().setNombre(map.get("nombre").toString());
            ps.getProyecto().setInversion(Double.parseDouble(map.get("inversion").toString()));
            ps.getProyecto().setDuracion(Integer.parseInt(map.get("duracion").toString()));
            ps.getProyecto().setFechaInicio(map.get("fechaInicio").toString());
            ps.getProyecto().setFechaFin(map.get("fechaFin").toString());
            ps.getProyecto().setInversionistas(map.get("inversionistas").toString()); // Corregido: "inversionistas"
            ps.getProyecto().setGeneracionDiaria(Double.parseDouble(map.get("generacionDiaria").toString()));
            ps.getProyecto().setCostoOperativo(Double.parseDouble(map.get("costoOperativo").toString()));
            ps.getProyecto().setTipoEnergia(map.get("tipoEnergia").toString());
            ps.getProyecto().setUbicacion(map.get("ubicacion").toString());

            if (ps.update()) {
                res.put("msg", "Ok");
                res.put("data", "Guardado correctamente");
                return Response.ok(res).build();
            } else {
                res.put("msg", "Error");
                res.put("data", "No se pudo actualizar el proyecto.");
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
            }
        } else {
            res.put("msg", "Error");
            res.put("data", "Proyecto no encontrado.");
            return Response.status(Status.NOT_FOUND).entity(res).build();
        }
    } catch (NumberFormatException e) {
        res.put("msg", "Error");
        res.put("data", "Formato de número inválido: " + e.getMessage());
        return Response.status(Status.BAD_REQUEST).entity(res).build();
    } catch (Exception e) {
        e.printStackTrace();
        res.put("msg", "Error");
        res.put("data", "Error en save data: " + e.getMessage());
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}

@DELETE 
@Path("/delete/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response deleteProyecto(@PathParam("id") int id) {
    HashMap<String, Object> res = new HashMap<>();
    
    try {
        ProyectoServicies fs = new ProyectoServicies();
        
        // Comprueba si el ID es válido
        if (id <= 0) {
            res.put("message", "ID no puede ser menor o igual a cero");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }

        boolean proyectoDelete = fs.delete(id - 1);  // Intentamos eliminar el proyecto
        
        if (proyectoDelete) {
            res.put("message", "Proyecto eliminado exitosamente");
            return Response.ok(res).build();
        } else {
            res.put("message", "Proyecto no encontrada o no eliminada");
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
    } catch (ListEmptyException e) {
        res.put("message", e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(res).build(); // Para lista vacía
    } catch (IndexOutOfBoundsException e) {
        res.put("message", "Error: Índice fuera de rango");
        return Response.status(Response.Status.BAD_REQUEST).entity(res).build(); // Índice fuera de rango
    } catch (Exception e) {
        res.put("message", "Error al intentar eliminar el proyecto");
        res.put("error", e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}
@SuppressWarnings("unchecked")
@Path("/list/search/nombre/{texto}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getPersonsLastName(@PathParam("texto") String texto) {
    HashMap map = new HashMap<>();
    ProyectoServicies ps = new ProyectoServicies();
    map.put("msg", "Ok");
    LinkedList lista = ps.buscar_nombre(texto);
    map.put("data", lista.toArray());
    if (lista.isEmpty()) {
        map.put("data", new Object[] {});
    }
    return Response.ok(map).build();
}

@SuppressWarnings("unchecked")
@Path("/list/search/inversionistas/{texto}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getPersonsInversionistas(@PathParam("texto") String texto) {
    HashMap map = new HashMap<>();
    ProyectoServicies ps = new ProyectoServicies();
    map.put("msg", "Ok");
    LinkedList lista = ps.buscar_inversionistas(texto);
    map.put("data", lista.toArray());
    if (lista.isEmpty()) {
        map.put("data", new Object[] {});
    }
    return Response.ok(map).build();
}

@SuppressWarnings("unchecked")
@Path("/list/search/ubicacion/{texto}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getPersonsUbicacion(@PathParam("texto") String texto) {
    HashMap map = new HashMap<>();
    ProyectoServicies ps = new ProyectoServicies();
    map.put("msg", "Ok");
    LinkedList lista = ps.buscar_ubicacion(texto);
    map.put("data", lista.toArray());
    if (lista.isEmpty()) {
        map.put("data", new Object[] {});
    }
    return Response.ok(map).build();
}

@SuppressWarnings("unchecked")
    @Path("/list/order/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsLastName(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        ProyectoServicies ps = new ProyectoServicies();
        map.put("msg", "Ok");
        try {
            //revisar el order
            LinkedList lista = ps.orderQuick(type, attribute);
            map.put("data", lista.toArray());
            if (lista.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            // TODO handle exception
        }

        return Response.ok(map).build();
    }

    @SuppressWarnings("unchecked")
    @Path("/list/merge/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyectoLastName(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        ProyectoServicies ps = new ProyectoServicies();
        map.put("msg", "Ok");
        try {
            //revisar el order
            LinkedList lista = ps.orderMerge(type, attribute);
            map.put("data", lista.toArray());
            if (lista.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            // TODO handle exception
        }

        return Response.ok(map).build();
    }

    @SuppressWarnings("unchecked")
    @Path("/list/shell/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyectoL(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        ProyectoServicies ps = new ProyectoServicies();
        map.put("msg", "Ok");
        try {
            //revisar el order
            LinkedList lista = ps.orderMerge(type, attribute);
            map.put("data", lista.toArray());
            if (lista.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            // TODO handle exception
        }

        return Response.ok(map).build();
    }

    @SuppressWarnings("unchecked")
@Path("/list/search/lineal/nombre/{texto}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getPersonsLa(@PathParam("texto") String texto) {
    HashMap map = new HashMap<>();
    ProyectoServicies ps = new ProyectoServicies();
    map.put("msg", "Ok");
    LinkedList lista = ps.buscarNombreLineal(texto);
    map.put("data", lista.toArray());
    if (lista.isEmpty()) {
        map.put("data", new Object[] {});
    }
    return Response.ok(map).build();
}
@SuppressWarnings("unchecked")
@Path("/list/search/binario/nombre/{texto}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getPersdssonsLa(@PathParam("texto") String texto) {
    HashMap map = new HashMap<>();
    ProyectoServicies ps = new ProyectoServicies();
    map.put("msg", "Ok");
    LinkedList lista = ps.buscarNombreBinario(texto);
    map.put("data", lista.toArray());
    if (lista.isEmpty()) {
        map.put("data", new Object[] {});
    }
    return Response.ok(map).build();
}
@SuppressWarnings("unchecked")
@Path("/list/search/lineal/inversionistas/{texto}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getPersonLa(@PathParam("texto") String texto) {
    HashMap map = new HashMap<>();
    ProyectoServicies ps = new ProyectoServicies();
    map.put("msg", "Ok");
    LinkedList lista = ps.buscarInversionistasLineal(texto);
    map.put("data", lista.toArray());
    if (lista.isEmpty()) {
        map.put("data", new Object[] {});
    }
    return Response.ok(map).build();
}
@SuppressWarnings("unchecked")
@Path("/list/search/binario/inversionistas/{texto}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getPersodnLa(@PathParam("texto") String texto) {
    HashMap map = new HashMap<>();
    ProyectoServicies ps = new ProyectoServicies();
    map.put("msg", "Ok");
    LinkedList lista = ps.buscarInversionistaBinario(texto);
    map.put("data", lista.toArray());
    if (lista.isEmpty()) {
        map.put("data", new Object[] {});
    }
    return Response.ok(map).build();
}


@SuppressWarnings("unchecked")
@Path("/list/search/lineal/ubicacion/{texto}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getPedrsonsUbicacion(@PathParam("texto") String texto) {
    HashMap map = new HashMap<>();
    ProyectoServicies ps = new ProyectoServicies();
    map.put("msg", "Ok");
    LinkedList lista = ps.buscarUbicacionLineal(texto);
    map.put("data", lista.toArray());
    if (lista.isEmpty()) {
        map.put("data", new Object[] {});
    }
    return Response.ok(map).build();
}

@SuppressWarnings("unchecked")
@Path("/list/search/binario/ubicacion/{texto}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getPedrdsonsUbicacion(@PathParam("texto") String texto) {
    HashMap map = new HashMap<>();
    ProyectoServicies ps = new ProyectoServicies();
    map.put("msg", "Ok");
    LinkedList lista = ps.buscarUbicacionBinario(texto);
    map.put("data", lista.toArray());
    if (lista.isEmpty()) {
        map.put("data", new Object[] {});
    }
    return Response.ok(map).build();
}
}
