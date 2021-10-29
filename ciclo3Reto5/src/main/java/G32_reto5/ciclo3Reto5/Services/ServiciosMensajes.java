package G32_reto5.ciclo3Reto5.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import G32_reto5.ciclo3Reto5.Model.Mensajes;
import G32_reto5.ciclo3Reto5.Repository.RepositorioMensajes;


@Service
public class ServiciosMensajes {
    @Autowired
    private RepositorioMensajes metodosCrud;
    
    public List<Mensajes> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Mensajes> getMessage(int messageId) {
        return metodosCrud.getMessage(messageId);
    }
    
    public Mensajes save(Mensajes message){
        if(message.getIdMessage()==null){
            return metodosCrud.save(message);
        }else{
            Optional<Mensajes> evt= metodosCrud.getMessage(message.getIdMessage());
            if(evt.isEmpty()){
                return metodosCrud.save(message);
            }else{
                return message;
            }
        }
    }
    public Mensajes update(Mensajes message){
        if(message.getIdMessage()!=null){
            Optional<Mensajes> mensaje_1= metodosCrud.getMessage(message.getIdMessage());
            if(!mensaje_1.isEmpty()){
                if(message.getMessageText()!=null){
                    mensaje_1.get().setMessageText(message.getMessageText());
                }
                metodosCrud.save(mensaje_1.get());
                return mensaje_1.get();
            }else{
                return message;
            }
        }else{
            return message;
        }
    }

    public boolean deleteMessage(int messageId) {
        Boolean aBoolean = getMessage(messageId).map(message -> {
            metodosCrud.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
