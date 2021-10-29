package G32_reto5.ciclo3Reto5.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import G32_reto5.ciclo3Reto5.Model.Cliente;
import G32_reto5.ciclo3Reto5.Repository.RepositorioCliente;


@Service
public class ServiciosCliente {
    @Autowired
    private RepositorioCliente metodosCrud;
    
    public List<Cliente> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Cliente> getCliente(int clientId){
        return metodosCrud.getCliente(clientId);
    }
    
    public Cliente save(Cliente client){
        if(client.getIdClient()==null){
            return metodosCrud.save(client);
        }else{
            Optional<Cliente> evt=metodosCrud.getCliente(client.getIdClient());
            if(evt.isEmpty()){
                return metodosCrud.save(client);
            }else{
                return client;
            }
        }
    }
     public Cliente update(Cliente client){
        if(client.getIdClient()!=null){
            Optional<Cliente> cliente_1= metodosCrud.getCliente(client.getIdClient());
            if(!cliente_1.isEmpty()){
                if(client.getName()!=null){
                    cliente_1.get().setName(client.getName());
                }
                if(client.getAge()!=null){
                    cliente_1.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null){
                    cliente_1.get().setPassword(client.getPassword());
                }
                metodosCrud.save(cliente_1.get());
                return cliente_1.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }

    public boolean deleteClient(int clientId) {
        Boolean aBoolean = getCliente(clientId).map(client -> {
            metodosCrud.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
