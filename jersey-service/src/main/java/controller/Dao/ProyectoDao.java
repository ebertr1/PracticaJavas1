package controller.Dao;

import models.Proyecto;
import controller.Dao.implement.AdapterDao;
import controller.tda.list.LinkedList;

public class ProyectoDao extends AdapterDao<Proyecto> {
    private Proyecto proyecto; 
    private LinkedList<Proyecto> listAll;

    public ProyectoDao() {
        super(Proyecto.class);
        this.listAll = new LinkedList<>();
    }

    public Proyecto getProyecto() {
        if (proyecto == null) {
            proyecto = new Proyecto();
        }
        return this.proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public LinkedList<Proyecto> getlistAll() {
        // Asegúrate de que `listAll()` devuelva una lista completa y correcta
        if (listAll == null || listAll.isEmpty()) {
            this.listAll = listAll(); // Verifica que listAll() funcione como se espera
        }
        return listAll;
    }

    
    public Boolean save() throws Exception {
        // Asegúrate de que `proyecto` esté inicializado
        if (proyecto == null) {
            throw new NullPointerException("El objeto 'proyecto' no ha sido inicializado.");
        }

        // Asignar un ID único
        Integer id = getlistAll().getSize() + 1;
        proyecto.setIdProyecto(id);

        // Guarda el proyecto
        this.persist(proyecto);

        // Actualiza la lista con los nuevos datos
        this.listAll = getlistAll();
        return true;
    }




    public Boolean update() throws Exception {
        try {
            if (proyecto == null) {
                throw new NullPointerException("El objeto 'proyecto' no ha sido inicializado.");
            }
            // Actualiza el proyecto existente
            this.merge(getProyecto(), getProyecto().getIdProyecto() - 1);
            this.listAll = getlistAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(Integer id) throws Exception {
        LinkedList<Proyecto> list = getlistAll();
        Proyecto proyecto = get(id);
        if (proyecto != null) {
            list.remove(proyecto);
            String info = g.toJson(list.toArray());
            saveFile(info);
            this.listAll = list;
            return true;
        } else {
            System.out.println("Proyecto con id " + id + " no encontrado.");
            return false;
        }
    }
}
