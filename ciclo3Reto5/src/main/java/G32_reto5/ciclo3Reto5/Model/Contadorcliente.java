package G32_reto5.ciclo3Reto5.Model;

public class Contadorcliente {
    
    private long total;
    private Cliente client;
    
   public Contadorcliente(long total, Cliente client) {
        this.total = total;
        this.client = client;
    }

    public Contadorcliente() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

}
