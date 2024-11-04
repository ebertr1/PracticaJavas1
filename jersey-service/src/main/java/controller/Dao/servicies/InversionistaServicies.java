package controller.Dao.servicies;

import controller.Dao.InversionistaDao;
import controller.tda.list.LinkedList;
import controller.tda.list.ListEmptyException;
import models.Inversionista;
import java.util.ArrayList;
import java.util.List;


public class InversionistaServicies {

    private InversionistaDao obj;

    public InversionistaServicies() {
        obj = new InversionistaDao();
    }

    
    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean delete(Integer id) throws Exception {
        return obj.delete(id); // Llamar al método delete de InversionistaDao
    }

    // Modifica el método existente
    
    public List<Inversionista> listAll() {
    LinkedList<Inversionista> inversionistasLinkedList = obj.getlistAll(); // Obtén el LinkedList
    List<Inversionista> inversionistas = new ArrayList<>(); // Crea una nueva lista de tipo List<Inversionista>

    // Iterar sobre el LinkedList y agregar los elementos a la lista estándar
    try {
        for (int i = 0; i < inversionistasLinkedList.getSize(); i++) { // Cambiar size() por getSize()
            inversionistas.add(inversionistasLinkedList.get(i)); // Agregar cada Inversionista a la nueva lista
        }
    } catch (ListEmptyException e) {
        // Manejar la excepción de lista vacía
        System.err.println("La lista de inversionistas está vacía: " + e.getMessage());
    } catch (IndexOutOfBoundsException e) {
        // Manejar la excepción de índice fuera de límites
        System.err.println("Índice fuera de límites: " + e.getMessage());
    }

    return inversionistas; // Retornar la nueva lista
}

    public Inversionista getInversionista() {
        return obj.getInversionista();
    }

    public void setInversionista(Inversionista inversionista) {
        obj.setInversionista(inversionista);
    }

    public Inversionista get(Integer id) throws Exception {
        return obj.get(id);
    }
}
