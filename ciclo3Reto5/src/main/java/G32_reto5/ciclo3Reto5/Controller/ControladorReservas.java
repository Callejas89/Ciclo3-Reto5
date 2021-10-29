package G32_reto5.ciclo3Reto5.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import G32_reto5.ciclo3Reto5.Model.Contadorcliente;
import G32_reto5.ciclo3Reto5.Model.Reservas;
import G32_reto5.ciclo3Reto5.Model.StatusReservas;
import G32_reto5.ciclo3Reto5.Services.ServiciosReservas;



@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ControladorReservas {
    @Autowired
    private ServiciosReservas servicio;
    @GetMapping("/all")
    public List<Reservas> getReservations(){
        return servicio.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservas> getReservation(@PathVariable("id") int reservationId) {
        return servicio.getReservation(reservationId);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservas save(@RequestBody Reservas reservation) {
        return servicio.save(reservation);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservas update(@RequestBody Reservas reservacion) {
        return servicio.update(reservacion);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int reservationId) {
        return servicio.deleteReservation(reservationId);
    }

    @GetMapping("/report-status")
	    public StatusReservas getReservas(){
	        return servicio.reporteStatusServicio();
	}
	    
	@GetMapping("/report-dates/{dateOne}/{dateTwo}")
	    public List<Reservas> getReservasTiempo (@PathVariable("dateOne")String dateOne, @PathVariable("dateTwo")String dateTwo ){
	        return servicio.reporteTiempoServicio(dateOne, dateTwo);
	}
	     
	@GetMapping("/report-clients")
	    public List<Contadorcliente> getClientes(){
	        return servicio.reporteClientesServicio();
	}
}
