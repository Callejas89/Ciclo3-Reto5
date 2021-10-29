package G32_reto5.ciclo3Reto5.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import G32_reto5.ciclo3Reto5.Model.Mensajes;
import G32_reto5.ciclo3Reto5.Repository.Crud.InterfaceMensajes;


@Repository
public class RepositorioMensajes {
    @Autowired
    private InterfaceMensajes crud3;
    public List<Mensajes> getAll(){
        return (List<Mensajes>) crud3.findAll();
    }
    public Optional<Mensajes> getMessage(int id){
        return crud3.findById(id);
    }

    public Mensajes save(Mensajes message){
        return crud3.save(message);
    }
     public void delete(Mensajes mensaje){
        crud3.delete(mensaje);
    }
}
