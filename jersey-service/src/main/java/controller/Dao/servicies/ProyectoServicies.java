package controller.Dao.servicies;
import controller.tda.list.LinkedList;
import models.Proyecto;
import controller.Dao.ProyectoDao;

public class ProyectoServicies {
    private ProyectoDao obj;
    public ProyectoServicies(){
        obj = new ProyectoDao();
    }
    public Boolean save() throws Exception{
        return obj.save();
    }
    public Boolean update() throws Exception{
        return obj.update();
    }
    public Boolean delete(Integer id) throws Exception {
        return obj.delete(id); 
    }
    public LinkedList listAll(){
        return obj.getlistAll();

    }

    public Proyecto getProyecto(){
        return obj.getProyecto();
    }

    public void setProyecto( Proyecto proyecto){
        obj.setProyecto(proyecto);
    }
    public Proyecto get(Integer id) throws Exception {
        return obj.get(id);
    }  
}
