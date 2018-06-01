import java.util.*;

public class RelojEvent extends EventObject {

    private Date horaAlarma;
    protected Object objFuente;

    public RelojEvent(Object fuente, Date horaAlarma) {
        super(fuente);
        this.horaAlarma = horaAlarma;
        this.objFuente = fuente;
    }

    public Date getHoraAlarma() {
        return horaAlarma;
    }

    public Object getSource(){
        return objFuente;
    }
}
