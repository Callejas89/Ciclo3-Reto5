package G32_reto5.ciclo3Reto5.Model;

public class StatusReservas {
    
    private int completed;
    private int cancelled;
    
    public StatusReservas() {
    }

    public StatusReservas(int completed, int cancelled) {
        this.completed = completed;
        this.cancelled = cancelled;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

}
