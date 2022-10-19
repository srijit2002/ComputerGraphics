import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import animal.bodyParts.*;
import animal.features.*;
import com.computer_graphics.*;

public class Test extends Applet{

    public void init() {
        
    }
    public void paint(Graphics g) {
        int originX = (getX() + getWidth()) / 2;
        int originY = (getY() + getHeight()) / 2;
        PointPlotter p = new PointPlotter(g, 5, new int[] { originX, originY }, 16);
        Body body=new Body(p,10,10,15,35,SpotType.SPOTTED,HairType.HAIRY);
    }
}
