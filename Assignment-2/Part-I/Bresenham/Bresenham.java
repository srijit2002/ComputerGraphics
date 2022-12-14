// package Bresenham;

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
// import java.util.*;

public class Bresenham extends Applet implements ActionListener {
    Color zoomInButtonColor = Color.CYAN;
    Color zoomOutButtonColor = Color.ORANGE;
    Color drawLineButtonColor = Color.green;
    int[] gridColorRGB = { 0, 0, 0 };
    int[] axisColorRGB = { 255, 0, 0 };
    int scale = 1;
    int gridGap;
    int pointDiameter = 16;
    Button zoomInButton, zoomOutButton, drawLineButton;
    TextField input;
    int[] endPoint1 = new int[2];
    int[] endPoint2 = { 10, 10 };

    public void init() {
        zoomInButton = createButton("Zoom In", zoomInButtonColor);
        add(zoomInButton);
        zoomInButton.addActionListener(this);

        zoomOutButton = createButton("Zoom Out", zoomOutButtonColor);
        add(zoomOutButton);
        zoomOutButton.addActionListener(this);

        drawLineButton = createButton("Draw Line", drawLineButtonColor);
        add(drawLineButton);
        drawLineButton.addActionListener(this);

        input = new TextField("0 0 10 10");
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

    public void drawLineBresenham(Graphics g, int[] p1, int[] p2, int[] origin) {
        int x1 = p1[0], y1 = p1[1];
        int x2 = p2[0], y2 = p2[1];
        int dy=y2-y1;
        int dx=x2-x1;
        boolean flag=Math.abs(dy)<Math.abs(dx);
        int pk = flag ? (2 * dy - dx) : (2 * dx - dy);
        for (int i = 0; i <= dx; i++) {
            plotPoint(g, origin, x1, y1, Color.blue);
            if (x1 < x2) {
                x1++;
            } else {
                x1--;
            }
            if (pk < 0) {
                if (flag) {
                    pk = pk + 2 * dy;
                } else
                    pk = pk + 2 * dx;
            } else {
                if (y1 < y2)
                    y1++;
                else
                    y1--;
                if(flag) pk = pk + 2 * dy - 2 * dx;
                else pk = pk + 2 * dx - 2 * dy;
            }
        }
    }

    public void paint(Graphics g) {
        int originX = (getX() + getWidth()) / 2;
        int originY = (getY() + getHeight()) / 2;
        gridGap = 4 + scale;

        // background colors
        Color axisColor = new Color(axisColorRGB[0], axisColorRGB[1], axisColorRGB[2]);
        Color gridColor = new Color(gridColorRGB[0], gridColorRGB[1], gridColorRGB[2]);

        // sets axis
        g.setColor(axisColor);
        g.drawLine(0, originY, getWidth(), originY);
        g.drawLine(originX, 0, originX, getHeight());

        // create grid
        drawGrid(g, originX, originY, getWidth(), getHeight(), gridColor);

        // plot line
        drawLineBresenham(g, endPoint1, endPoint2, new int[] { originX, originY });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == zoomInButton) {
            scale = Math.min(100, scale + 2);
            repaint();
        } else if (e.getSource() == zoomOutButton) {
            scale = Math.max(-3, scale - 2);
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
                System.out.println(exception.getMessage());
                return;
            }
            endPoint1 = endpoint_1;
            endPoint2 = endpoint_2;
            repaint();

        }
    }
}
