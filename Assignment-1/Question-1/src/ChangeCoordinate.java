import java.applet.*;
import java.awt.*;

public class ChangeCoordinate extends Applet{
    public void init() {

    }
    public void paint(Graphics g) {
        int originX = (getX() + getWidth()) / 2;
        int originY = (getY() + getHeight()) / 2;
        System.out.println("origin x"+"->"+originX);
        System.out.println("origin y"+"->"+originY);
        g.setColor(Color.blue);
        g.fillOval(originX,originY,40,40);
    }
}
