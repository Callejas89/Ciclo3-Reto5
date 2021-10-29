package G32_reto5.ciclo3Reto5.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import G32_reto5.ciclo3Reto5.Model.Bike;
import G32_reto5.ciclo3Reto5.Repository.RepositorioBike;


@Service
public class ServiciosBike {
    @Autowired
    private RepositorioBike metodosCrud;

    public List<Bike> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Bike> getBike(int bikeId) {
        return metodosCrud.getBike(bikeId);
    }

    public Bike save(Bike bike){
        if(bike.getId()==null){
            return metodosCrud.save(bike);
        }else{
            Optional<Bike> bici=metodosCrud.getBike(bike.getId());
            if(bici.isEmpty()){
                return metodosCrud.save(bike);
            }else{
                return bike;
            }
        }
    }

    public Bike update(Bike bike){
        if(bike.getId()!=null){
            Optional<Bike> bici=metodosCrud.getBike(bike.getId());
            if(!bici.isEmpty()){
                if(bike.getName()!=null){
                    bici.get().setName(bike.getName());
                }
                if(bike.getBrand()!=null){
                    bici.get().setBrand(bike.getBrand());
                }
                if(bike.getYear()!=null){
                    bici.get().setYear(bike.getYear());
                }
                if(bike.getDescription()!=null){
                    bici.get().setDescription(bike.getDescription());
                }
                if(bike.getCategory()!=null){
                    bici.get().setCategory(bike.getCategory());
                }
                metodosCrud.save(bici.get());
                return bici.get();
            }else{
                return bike;
            }
        }else{
            return bike;
        }
    }


    public boolean deleteBike(int bikeId) {
        Boolean aBoolean = getBike(bikeId).map(bike -> {
            metodosCrud.delete(bike);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
