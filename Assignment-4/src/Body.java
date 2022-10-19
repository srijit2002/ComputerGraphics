package animal.bodyParts;

import java.util.concurrent.ThreadLocalRandom;
import animal.features.*;
import com.computer_graphics.*;

public class Body {
    int centerX, centerY, radiusX, radiusY;
    int number_of_spots = 20;
    int number_of_hairs = 80;

    public Body(PointPlotter p, int centerX, int centerY, int radiusX, int radiusY, SpotType st, HairType ht) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        EllipsePainter.paintEllipse(p, radiusX, radiusY, centerX, centerY);
        if (st == SpotType.SPOTTED)
            createSpot(p);
        if (ht == HairType.HAIRY)
            createHair(p);
    }

    private void createSpot(PointPlotter p) {
        for (int i = 0; i < number_of_spots; i++) {
            int []point=getRandomPoint();
            CirclePainter.paintCircle(2, point[0],point[1], p);
        }
    }

    private void createHair(PointPlotter p) {
        int dirx = 1, dx = 2, dy = 3, diry = 1;
        for (int i = 0; i < number_of_hairs; i++) {
            int []point=getRandomPoint();
            int rx=point[0],ry=point[1];
            LinePainter.paintLine(new int[] { rx, ry }, new int[] { rx + dirx * dx, ry + diry * dy }, p);
            dirx *= -1;
            diry *= -1;
        }
    }

    private int[] getRandomPoint() {
        double rho= ThreadLocalRandom.current().nextDouble(0,1);
        double phi= ThreadLocalRandom.current().nextDouble(0,2*Math.PI);
        double x = Math.sqrt(rho) * Math.cos(phi);
        double y = Math.sqrt(rho) * Math.sin(phi);
        x = x * (radiusX-2);
        y = y * (radiusY-2);
        return new int[]{centerX+(int)x,centerY+(int)y};
    }
}
