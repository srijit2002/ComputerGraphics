
// package Candle;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
// import java.util.*;



public class Candle extends Applet implements ActionListener {
    Color zoomInButtonColor = Color.CYAN;
    Color zoomOutButtonColor = Color.ORANGE;
    Color drawLineButtonColor = Color.green;

    Color backgroundColor=Color.white;
    Color foregroundColor=Color.black;

    int[] gridColorRGB = { 0, 0, 0 };
    int[] axisColorRGB = { 255, 0, 0 };
    int scale = 1;
    int gridGap;
    int pointDiameter = 16;
    Button zoomInButton, zoomOutButton, drawLineButton,invertColorButton;
    TextField input;

    int []candleBottomLeft={5,6};
    int candleWidth=3,candleHeight=22;
    public void init() {
        this.setBackground(backgroundColor);
        zoomInButton = createButton("Zoom In", zoomInButtonColor);
        add(zoomInButton);
        zoomInButton.addActionListener(this);

        zoomOutButton = createButton("Zoom Out", zoomOutButtonColor);
        add(zoomOutButton);
        zoomOutButton.addActionListener(this);

        drawLineButton = createButton("Draw Candle", drawLineButtonColor);
        add(drawLineButton);
        drawLineButton.addActionListener(this);

        input = new TextField("Bottom-Left-X Bottom-Left-Y Width Height");
        add(input);
    }

    public void plotPoint(Graphics g, int[] origin, int x, int y, Color c) {
        int radius = pointDiameter / 2;
        int centerX = origin[0] + x * gridGap - radius / 2;
        int centerY = origin[1] - y * gridGap - radius / 2;
        g.setColor(c);
        g.fillOval(centerX, centerY, radius, radius);
    }

    private void drawGrid(Graphics g, int originX, int originY, int maxX, int maxY, Color gridColor) {
        g.setColor(gridColor);
        int curY = originY - gridGap;
        int markY = 1;
        while (curY >= 0) {
            g.drawLine(0, curY, maxX, curY);
            g.drawString(Integer.toString(markY++), originX, curY);
            curY -= gridGap;
        }
        markY = -1;
        curY = originY + gridGap;
        while (curY <= maxY) {
            g.drawLine(0, curY, maxX, curY);
            g.drawString(Integer.toString(markY--), originX, curY);
            curY += gridGap;
        }
        int markX = -1;
        int curX = originX - gridGap;
        while (curX >= 0) {
            g.drawLine(curX, 0, curX, maxY);
            g.drawString(Integer.toString(markX--), curX, originY);
            curX -= gridGap;
        }
        markX = 1;
        curX = originX + gridGap;
        while (curX <= maxX) {
            g.drawLine(curX, 0, curX, maxY);
            g.drawString(Integer.toString(markX++), curX, originY);
            curX += gridGap;
        }
    }

    private Button createButton(String label, Color color) {
        Button button = new Button(label);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(color);
        button.setFont(Font.decode(Font.SANS_SERIF));
        return button;
    }

    public void drawLineDDA(Graphics g, int[] p0, int[] p1, int[] origin) {
        int x0 = p0[0], y0 = p0[1];
        int x1 = p1[0], y1 = p1[1];
        int dx = x1 - x0;
        int dy = y1 - y0;
        int total_steps = Math.max(Math.abs(dx), Math.abs(dy));
        float x_incr = (float) dx / total_steps;
        float y_incr = (float) dy / total_steps;
        float x = x0;
        float y = y0;
        Color currentFlameColor = Color.yellow;
        int orangeStart = (int) Math.ceil(0.3 * total_steps);
        int redStart = (int) Math.ceil(0.7 * total_steps);
        for (int number_of_steps = 0; number_of_steps <= total_steps; number_of_steps++) {
            if (number_of_steps >= orangeStart)
                currentFlameColor = Color.orange;
            if (number_of_steps >= redStart)
                currentFlameColor = Color.red;
            plotPoint(g, origin, Math.round(x), Math.round(y), currentFlameColor);
            x += x_incr;
            y += y_incr;
        }
    }

    public void drawFlame(Graphics g, int[] start, int[] origin, int flameWidth) {
        if (flameWidth % 2 == 0)
            flameWidth++;
        int endY = start[1] + 4;
        int endX = start[0] - (flameWidth / 2) * 2;
        int sign = 1;

        for (int i = 0; i < flameWidth; i++) {
            drawLineDDA(g, start, new int[] { endX, endY }, origin);
            endX += 2;
            if (i == (flameWidth / 2)) {
                sign *= -1;
            }
            endY += sign * 4;
        }
    }
    public void drawCandleBase(Graphics g,int []origin,int []bottomLeft,int []topRight){
        for(int x=bottomLeft[0];x<=topRight[0];x++){
            for(int y=bottomLeft[1];y<=topRight[1];y++){
                plotPoint(g, origin, x, y,Color.white);
            }
        }
    }
    public void drawCandle(Graphics g,int []origin,int []bottomLeft,int []topRight,int flameWidth){
        drawCandleBase(g, origin, bottomLeft, topRight);
        drawFlame(g, new int[]{(bottomLeft[0]+topRight[0])/2,topRight[1]+1}, origin, flameWidth);
    }
    
    public void paint(Graphics g) {
        

        int originX = (getX() + getWidth()) / 2;
        int originY = (getY() + getHeight()) / 2;
        gridGap = 4 + scale;

        // background colors
        Color axisColor = new Color(axisColorRGB[0], axisColorRGB[1], axisColorRGB[2]);
        // Color gridColor = new Color(gridColorRGB[0], gridColorRGB[1], gridColorRGB[2]);

        // sets axis
        g.setColor(axisColor);
        g.drawLine(0, originY, getWidth(), originY);
        g.drawLine(originX, 0, originX, getHeight());

        // create grid
        drawGrid(g, originX, originY, getWidth(), getHeight(), foregroundColor);

        // plot line
        int []candleTopRight={candleBottomLeft[0]+candleWidth,candleBottomLeft[1]+candleHeight};
        drawCandle(g, new int[] {originX,originY}, candleBottomLeft, candleTopRight,7);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == zoomInButton) {
            scale = Math.min(100, scale + 2);
            repaint();
        } else if (e.getSource() == zoomOutButton) {
            scale = Math.max(1, scale - 2);
            repaint();
        } else if (e.getSource() == drawLineButton) {
            String userinput = input.getText();
            if (userinput.length() == 0) {
                input.setText("Please check your input format");
                return;
            }
            String[] points_str = userinput.split(" ");
            if (points_str.length != 4) {
                input.setText("Please check your input format");
                return;
            }
            int[] endpoint_1 = new int[2];
            int[] endpoint_2 = new int[2];
            try {
                for (int i = 0; i < 2; i++) {
                    endpoint_1[i] = Integer.valueOf(points_str[i]);
                }
                for (int i = 2; i < 4; i++) {
                    endpoint_2[i % 2] = Integer.valueOf(points_str[i]);
                }
            } catch (Exception exception) {
                input.setText("Please check your input format");
                return;
            }
            candleBottomLeft = endpoint_1;
            candleWidth=endpoint_2[0];
            candleHeight=endpoint_2[1];
            repaint();
        }
    }
}
