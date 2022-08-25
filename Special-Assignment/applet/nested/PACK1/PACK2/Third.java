package PACK1.PACK2;

import java.awt.*;

public class Third {
    Font F2 = new Font("Arial", Font.BOLD, 20);

    public void draw_third(Graphics G) {
        G.setFont(F2);
        G.setColor(Color.black);
        G.drawString("I hope this is visible too", 130, 190);
    }
}