import java.awt.Graphics.*; 
import java.awt.geom.Ellipse2D;
import java.util.* ;
import java.awt.* ;

import javax.swing.JPanel;

public class Reloj extends JPanel implements Runnable { 
	//Detectar presionado de teclas
	//implementar keyListener
	
	//public void keyPressed(keyEvent e){
	      //generar evento presionado de teclas
	      //recorrer vector de listener de presionado de teclas
	
	//}
	
	Thread UnHilo;

	public Reloj(){
		//this.setSize(100, 100);
		this.setPreferredSize(new Dimension(100, 100));
	
		this.setBackground(new Color((int) (Math.random() * 255), 
				(int) (Math.random() * 255), 
				(int) (Math.random() * 255)) );
	}
	
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		Date ahora = new Date();
		
		int anchoTexto = g.getFontMetrics().stringWidth(ahora.getHours() + ":" 
				+ ahora.getMinutes() + ":"
				+ ahora.getSeconds());
		int altoTexto = g.getFontMetrics().getHeight();
		
		g.drawString(ahora.getHours() + ":" 
					+ ahora.getMinutes() + ":"
					+ ahora.getSeconds(), 
				(this.getWidth() - anchoTexto) / 2, 
				((this.getHeight() - altoTexto) / 2) + altoTexto/2 );
		
		Graphics2D g2d = (Graphics2D) g;
        /* imagen de fondo
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage.getImage(), 0, 0, null);
        }
        Reloj reloj = (Reloj) c;  */
		
		Calendar calendario = Calendar.getInstance();
		
        int hour = calendario.get(Calendar.HOUR);
        int minute = calendario.get(Calendar.MINUTE);
        double second = calendario.get(Calendar.SECOND + Calendar.MILLISECOND / 1000);
 
        int diametroOvalo;
     
        // acotamos la parte donde dibujaremos las manecillas del reloj
        if (this.getWidth() < this.getHeight())
        		diametroOvalo = this.getWidth() - 2;
        else diametroOvalo = this.getHeight() - 2;
        
        Shape oval = new Ellipse2D.Double(0, 0, diametroOvalo, diametroOvalo);
        int centerX = (int) (oval.getBounds().getWidth() / 2);
        int centerY = (int) (oval.getBounds().getHeight() / 2);
 
        // puntos donde acaban las manecillas del reloj
        int xh, yh, xm, ym, xs, ys;
 
        // calculamos la posicion de las manecillas del reloj
        xs = (int) (Math.cos(second * Math.PI / 30 - Math.PI / 2) * (diametroOvalo/2)*3/4 + centerX);
        ys = (int) (Math.sin(second * Math.PI / 30 - Math.PI / 2) * (diametroOvalo/2)*3/4 + centerY);
        xm = (int) (Math.cos(minute * Math.PI / 30 - Math.PI / 2) * (diametroOvalo/2) * 1/2 + centerX);
        ym = (int) (Math.sin(minute * Math.PI / 30 - Math.PI / 2) * (diametroOvalo/2) * 1/2 + centerY);
        xh = (int) (Math.cos((hour * 30 + minute / 2) * Math.PI / 180 - Math.PI / 2) * (diametroOvalo/2) * 1/3 + centerX);
        yh = (int) (Math.sin((hour * 30 + minute / 2) * Math.PI / 180 - Math.PI / 2) * (diametroOvalo/2) * 1/3 + centerY);
 
        //offset, movemos el origen de las coordenadas usadas para dibujar
        //al vertice superior izquierdo del rectángulo que contiene la esfera del reloj.
        g.translate((int) (this.getWidth() / 2 - oval.getBounds().getWidth()/2), 
        		(int) (this.getHeight()/2 - oval.getBounds().getHeight() / 2));
        
        g2d.draw(oval);
        //dibujamos los números y las manecillas
        g.drawString("9", 3, centerY+5);
        g.drawString("3", (int) (oval.getBounds().getWidth() - 10), centerY+5);
        g.drawString("12", centerX-6, 15);
        g.drawString("6", centerX-3, (int) (oval.getBounds().getHeight() - 5));
 
        // segundos
        g.drawLine(centerX, centerY, xs, ys);
        // minutos
        BasicStroke handsStroke = new BasicStroke(2);
        g2d.setStroke(handsStroke);
        g.drawLine(centerX, centerY, xm, ym);
        //horas
        g.drawLine(centerX, centerY, xh, yh);
 
        // - offset
        g.translate(0,0);
	 
    }
	
	public void start()
	{
		if (UnHilo == null)
		{
			UnHilo = new Thread(this, "Reloj");
			UnHilo.start();//Método start de la clase Thread 
		}
	}
		
	public void run() {
		while(true) { 
			this.repaint();
			//Dispara evento de alarma
			//Suena la alarma
			//Llamar a método que ejecuta métodos de listeners
			
			try {
				UnHilo.sleep(10);
			}catch (Exception e) {} 
		}
	}
	
	public void stop() { 
		UnHilo.stop(); //Para el hilo
		UnHilo = null; 
	}
}