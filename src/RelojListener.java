import java.util.*;

public interface RelojListener extends EventListener {
        public void cambioHoraAlarma(RelojEvent e);
        public void inicioAlarma(RelojEvent e);

}