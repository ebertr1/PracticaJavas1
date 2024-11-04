package com.example.rest;

import controller.Dao.servicies.InversionistaServicies;
import models.Inversionista;

import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import com.google.gson.Gson;

@Path("inversionista")
public class InversionistaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInversionistas() {
        HashMap map = new HashMap<>();
        InversionistaServicies ps = new InversionistaServicies();
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
    public Response getInversionista(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
            InversionistaServicies ps = new InversionistaServicies();
        try {
            ps.setInversionista(ps.get(id));
        } catch (Exception e) {

        }

        map.put("msg", "Ok");
        map.put("data", ps.getInversionista());

        if (ps.getInversionista() == null || ps.getInversionista().getIdInversionista() == 0) {
            map.put("msg", "No se encontró Inversionista con ese identificador");
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
    public Response save(HashMap map) {
        HashMap res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("***** " + a);

        try {
            InversionistaServicies ps = new InversionistaServicies();
            ps.getInversionista().setNombre(map.get("nombre").toString());
            ps.getInversionista().setApellido(map.get("apellido").toString());
            ps.getInversionista().setDNI(map.get("dni").toString());

            ps.save();
            res.put("msg", "Ok");
            res.put("data", "Guardado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en save data" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }

        // todo
        // Validation
    }

    @Path("/listType")

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap map = new HashMap<>();
        InversionistaServicies ps = new InversionistaServicies();
        map.put("msg", "Ok");
        map.put("data", ps.getInversionista());
        return Response.ok(map).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map) {
        HashMap res = new HashMap<>();
        

        try {
            InversionistaServicies ps = new InversionistaServicies();
            ps.setInversionista(ps.get(Integer.parseInt(map.get("idInversionista").toString())));
            ps.getInversionista().setNombre(map.get("nombre").toString());
            ps.getInversionista().setApellido(map.get("apellido").toString());
            ps.getInversionista().setDNI(map.get("dni").toString());
           

            ps.update();
            res.put("msg", "Ok");
            res.put("data", "Guardado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en save data" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }

        // todo
        // Validation

    }
    @Path("/delete")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response delete(HashMap<String, Object> map) {
    HashMap<String, Object> res = new HashMap<>();

    try {
        InversionistaServicies ps = new InversionistaServicies();
        Integer id = Integer.parseInt(map.get("idInversionista").toString());
        
        Boolean success = ps.delete(id);
        if (success) {
            res.put("msg", "Ok");
            res.put("data", "Eliminado correctamente");
            return Response.ok(res).build();
        } else {
            res.put("msg", "Error");
            res.put("data", "Inversionista no encontrada");
            return Response.status(Status.NOT_FOUND).entity(res).build();
        }
    } catch (Exception e) {
        System.out.println("Error en delete data" + e.toString());
        res.put("msg", "Error");
        res.put("data", e.toString());
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}


}
