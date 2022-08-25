import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridConstructor extends Applet implements ActionListener {
    Color zoomInButtonColor=Color.CYAN;
    Color zoomOutButtonColor=Color.ORANGE;
    int gridRed=0;
    int gridGreen=0;
    int gridBlue=0;
    int axisRed=255;
    int axisGreen=0;
    int axisBlue=0;
    int scale=1;
    Button zoomInButton,zoomOutButton;
    public void init() {
        zoomInButton = createButton("Zoom In",zoomInButtonColor);
        add(zoomInButton);
        zoomInButton.addActionListener(this);
        zoomOutButton = createButton("Zoom Out",zoomOutButtonColor);
        add(zoomOutButton);
        zoomOutButton.addActionListener(this);
    }
    private void drawGrid(Graphics g,int gridGap,int originX,int originY,int maxX,int maxY,Color gridColor){
        g.setColor(gridColor);
        int curY=originY-gridGap;
        while(curY>=0){
            g.drawLine(0,curY,maxX,curY);// x1 y1 x2 y2
            curY-=gridGap;
        }
        curY=originY+gridGap;
        while(curY<=maxY){
            g.drawLine(0,curY,maxX,curY);
            curY+=gridGap;
        }

        int curX=originX-gridGap;
        while(curX>=0){
            g.drawLine(curX,0,curX,maxY);
            curX-=gridGap;
        }
        curX=originX+gridGap;
        while(curX<=maxX){
            g.drawLine(curX,0,curX,maxY);
            curX+=gridGap;
        }
    }
    private Button createButton(String label,Color color){
        Button button=new Button(label);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(color);
        button.setFont(Font.decode(Font.SANS_SERIF));
        return button;
    }
    public boolean plotPoint(Graphics g,int originX,int originY,int gridGap,int x,int y,Color c,int diameter,String pointType){
        int radius=diameter/2;
        int centerX=originX+x*gridGap-radius/2;
        int centerY=originY-y*gridGap-radius/2;
        if(centerX<0||centerX>getWidth())return false;
        if(centerY<0||centerY>getHeight())return false;
        g.setColor(c);
        if(pointType.equals("circle"))g.fillOval(centerX,centerY,radius,radius);
        if(pointType.equals("square"))g.fillRect(centerX+radius/2-radius,centerY+radius/2-radius,diameter,diameter);
        return true;
    }

    public void paint(Graphics g) {
        int originX = (getX() + getWidth()) / 2;
        int originY = (getY() + getHeight()) / 2;
        int gridGap=20+scale;

        //background colors
        Color axisColor=new Color(axisRed,axisGreen,axisBlue);
        Color gridColor=new Color(gridRed,gridGreen,gridBlue);
        //grid colors

        //applying background color

        //sets axis
        g.setColor(axisColor);
        g.drawLine(0,originY,getWidth(),originY);
        g.drawLine(originX,0,originX,getHeight());

        //draws grid
        drawGrid(g,gridGap,originX,originY,getWidth(),getHeight(),gridColor);

        //plots point
        plotPoint(g,originX,originY,gridGap,-5,5,Color.RED,40,"circle");
        plotPoint(g,originX,originY,gridGap,5,5,Color.BLUE,40,"circle");
        plotPoint(g,originX,originY,gridGap,-5,-5,Color.YELLOW,40,"circle");
        plotPoint(g,originX,originY,gridGap,5,-5,Color.GREEN,40,"circle");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == zoomInButton) {
            scale=Math.min(100,scale+2);
            repaint();
        }
        if (e.getSource() == zoomOutButton) {
            scale=Math.max(1,scale-2);
            repaint();
        }
    }
}
