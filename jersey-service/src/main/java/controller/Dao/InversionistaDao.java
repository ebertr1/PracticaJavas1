package controller.Dao;

import models.Inversionista;
import controller.Dao.implement.AdapterDao;
import controller.tda.list.LinkedList;

public class InversionistaDao extends AdapterDao<Inversionista> {
    private Inversionista inversionista;
    private LinkedList<Inversionista> listAll;

    public InversionistaDao(){
        super(Inversionista.class);
        this.listAll = new LinkedList<>();
    }

    public Inversionista getInversionista() {
        if (inversionista == null) {
            inversionista = new Inversionista();
        }
        return this.inversionista;
    }

    public void setInversionista(Inversionista inversionista){
        this.inversionista = inversionista;
    }

    public LinkedList<Inversionista> getlistAll(){
        if (listAll.isEmpty()) { 
            this.listAll = listAll(); 
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getlistAll().getSize() + 1;
        inversionista.setIdInversionista(id);
        this.persist(this.inversionista);
        this.listAll = getlistAll(); 
        return true;
    }

    public Boolean update() throws Exception {
        try {
            this.merge(getInversionista(), getInversionista().getIdInversionista() - 1);
            this.listAll = getlistAll(); 
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(Integer id) throws Exception {
        LinkedList<Inversionista> list = getlistAll(); 
        Inversionista inversionista= get(id); 
        if (inversionista != null) {
            list.remove(inversionista); 
            String info = g.toJson(list.toArray());
            saveFile(info); 
            this.listAll = list;
            return true;
        } else {
            System.out.println("Inversionista con id " + id + " no encontrada.");
            return false;
        }
    }
    
}
