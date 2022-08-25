package applet.userdefined;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class First extends Applet {
    public void init() {
        setBackground(Color.LIGHT_GRAY);
    }

    public void paint(Graphics g) {
        g.drawString("Hi I am applet", 800, 600);
    }
}