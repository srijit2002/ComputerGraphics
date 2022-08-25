import java.applet.*;
import java.awt.*;
import PACK1.*;
import PACK1.PACK2.*;

public class First extends Applet {
    Font F1 = new Font("Courier", Font.BOLD, 20);

    public void init() {
        setBackground(Color.DARK_GRAY);
    }

    public void paint(Graphics G) {
        Second s = new Second();
        s.draw_second(G);
        G.setColor(Color.DARK_GRAY);
        G.setFont(F1);
        G.drawString("Hello I hope this is visible.", 130, 150);
        Third t = new Third();
        t.draw_third(G);
    }
}