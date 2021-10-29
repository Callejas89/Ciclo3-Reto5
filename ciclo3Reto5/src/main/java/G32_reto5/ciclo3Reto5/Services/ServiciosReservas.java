package G32_reto5.ciclo3Reto5.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import G32_reto5.ciclo3Reto5.Model.Contadorcliente;
import G32_reto5.ciclo3Reto5.Model.Reservas;
import G32_reto5.ciclo3Reto5.Model.StatusReservas;
import G32_reto5.ciclo3Reto5.Repository.repositorioReservas;


@Service
public class ServiciosReservas {
    @Autowired
    private repositorioReservas metodosCrud;
    
    public List<Reservas> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Reservas> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }

    public Reservas save(Reservas reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservas> reserva_1= metodosCrud.getReservation(reservation.getIdReservation());
            if(reserva_1.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }
    
    public Reservas update(Reservas reservacion){
        if(reservacion.getIdReservation()!=null){
            Optional<Reservas> reserva_1= metodosCrud.getReservation(reservacion.getIdReservation());
            if(!reserva_1.isEmpty()){

                if(reservacion.getStartDate()!=null){
                    reserva_1.get().setStartDate(reservacion.getStartDate());
                }
                if(reservacion.getDevolutionDate()!=null){
                    reserva_1.get().setDevolutionDate(reservacion.getDevolutionDate());
                }
                if(reservacion.getStatus()!=null){
                    reserva_1.get().setStatus(reservacion.getStatus());
                }
                metodosCrud.save(reserva_1.get());
                return reserva_1.get();
            }else{
                return reservacion;
            }
        }else{
            return reservacion;
        }
    }

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public StatusReservas reporteStatusServicio (){
        List<Reservas>completed= metodosCrud.ReservacionStatusRepositorio("completed");
        List<Reservas>cancelled= metodosCrud.ReservacionStatusRepositorio("cancelled");
        
        return new StatusReservas(completed.size(), cancelled.size() );
    }

    public List<Reservas> reporteTiempoServicio (String datoA, String datoB){
        SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");
        
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
             datoUno = parser.parse(datoA);
             datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return metodosCrud.ReservacionTiempoRepositorio(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        
        } 
    }

    public List<Contadorcliente> reporteClientesServicio(){
        return metodosCrud.getClientesRepositorio();
    }

}
