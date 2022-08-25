package applet.userdefined;
import applet.userdefined.PACK1.*;
import java.applet.*;
import java.awt.*;

public class First extends Applet {
    Font F1 = new Font("Courier", Font.BOLD, 20);

    public void init() {
        setBackground(Color.DARK_GRAY);
    }

    public void paint(Graphics G) {
        Second s = new Second();
        s.draw(G);
        G.setColor(Color.DARK_GRAY);
        G.setFont(F1);
        G.drawString("Hello I hope this is visible.", 130, 150);
    }
}