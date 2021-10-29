package G32_reto5.ciclo3Reto5.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import G32_reto5.ciclo3Reto5.Model.Cliente;
import G32_reto5.ciclo3Reto5.Model.Contadorcliente;
import G32_reto5.ciclo3Reto5.Model.Reservas;
import G32_reto5.ciclo3Reto5.Repository.Crud.InterfaceReservas;


@Repository
public class repositorioReservas {
    @Autowired
    private InterfaceReservas crud4;
    
    public List<Reservas> getAll(){
        return (List<Reservas>) crud4.findAll();
    }
    public Optional<Reservas> getReservation(int id){
        return crud4.findById(id);
    }
    public Reservas save(Reservas reservacion){
        return crud4.save(reservacion);
    }
     public void delete(Reservas reservacion){
        crud4.delete(reservacion);
    }

    public List<Reservas> ReservacionStatusRepositorio (String status){
        return crud4.findAllByStatus(status);
    }
    
    public List<Reservas> ReservacionTiempoRepositorio (Date a, Date b){
        return crud4.findAllByStartDateAfterAndStartDateBefore(a, b);
    
    }
    
    public List<Contadorcliente> getClientesRepositorio(){
        List<Contadorcliente> res = new ArrayList<>();
        List<Object[]> report = crud4.countTotalReservasByCliente();
        for(int i=0; i<report.size(); i++){
            res.add(new Contadorcliente((Long)report.get(i)[1],(Cliente) report.get(i)[0]));
        }
        return res;
    }
}
