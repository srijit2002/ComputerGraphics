package com.computer_graphics;
import java.awt.*;

public class PointPlotter {
    Graphics g;
    int gridGap;
    int []origin;
    int pointDiameter;
    public PointPlotter(Graphics g,int gridGap,int []origin,int pointDiameter){
        this.g=g;
        this.gridGap=gridGap;
        this.origin=origin;
        this.pointDiameter=pointDiameter;
    }
    public void plotPoint(int x, int y) {
        int radius = pointDiameter / 2;
        int centerX = origin[0] + x * gridGap - radius / 2;
        int centerY = origin[1] - y * gridGap - radius / 2;
        
        g.fillOval(centerX, centerY, radius, radius);
    }
    public void setColor(Color c){
        g.setColor(c);
    }
    public void setPointDiameter(int pointDiameter){
        this.pointDiameter=pointDiameter;
    }
}
