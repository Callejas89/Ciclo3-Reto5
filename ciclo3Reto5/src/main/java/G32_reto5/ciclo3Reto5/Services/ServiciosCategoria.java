package G32_reto5.ciclo3Reto5.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import G32_reto5.ciclo3Reto5.Model.Categoria;
import G32_reto5.ciclo3Reto5.Repository.RepostirorioCategoria;


@Service
public class ServiciosCategoria {
    @Autowired
    private RepostirorioCategoria metodosCrud;
    
    public List<Categoria> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Categoria> getCategoria(int idCategoria){
        return metodosCrud.getCategoria(idCategoria);
    }
    
    public Categoria save(Categoria categoria){
        if(categoria.getId()==null){
            return metodosCrud.save(categoria);
        }else{
            Optional<Categoria> evt=metodosCrud.getCategoria(categoria.getId());
            if(evt.isEmpty()){
                return metodosCrud.save(categoria);
            }else{
                return categoria;
            }
        }
    }
    public Categoria update(Categoria categoria){
        if(categoria.getId()!=null){
            Optional<Categoria>servicio=metodosCrud.getCategoria(categoria.getId());
            if(!servicio.isEmpty()){
                if(categoria.getDescription()!=null){
                    servicio.get().setDescription(categoria.getDescription());
                }
                if(categoria.getName()!=null){
                    servicio.get().setName(categoria.getName());
                }
                return metodosCrud.save(servicio.get());
            }
        }
        return categoria;
    }
    public boolean deleteCategoria(int categoriaId){
        Boolean servicio_2 =getCategoria(categoriaId).map(categoria -> {
            metodosCrud.delete(categoria);
            return true;
        }).orElse(false);
        return servicio_2;
    }
}
