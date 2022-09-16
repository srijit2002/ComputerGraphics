import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import com.computer_graphics.*;

public class Circle extends Applet implements ActionListener {
    Color zoomInButtonColor = Color.CYAN;
    Color zoomOutButtonColor = Color.ORANGE;
    Color drawLineButtonColor = Color.green;

    Color backgroundColor = Color.white;
    Color foregroundColor = Color.black;

    int[] gridColorRGB = { 0, 0, 0 };
    int[] axisColorRGB = { 255, 0, 0 };
    int scale = 1;
    int gridGap;
    int pointDiameter = 16;
    Button zoomInButton, zoomOutButton, drawLineButton, invertColorButton;
    TextField input;

    public void init() {
        this.setBackground(backgroundColor);
        zoomInButton = createButton("Zoom In", zoomInButtonColor);
        add(zoomInButton);
        zoomInButton.addActionListener(this);

        zoomOutButton = createButton("Zoom Out", zoomOutButtonColor);
        add(zoomOutButton);
        zoomOutButton.addActionListener(this);
    }

    private Button createButton(String label, Color color) {
        Button button = new Button(label);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(color);
        button.setFont(Font.decode(Font.SANS_SERIF));
        return button;
    }

    public void paint(Graphics g) {
        int originX = (getX() + getWidth()) / 2;
        int originY = (getY() + getHeight()) / 2;
        gridGap = 4 + scale;

        // background colors
        Color axisColor = new Color(axisColorRGB[0], axisColorRGB[1], axisColorRGB[2]);
        // Color gridColor = new Color(gridColorRGB[0], gridColorRGB[1],

        // sets axis
        g.setColor(axisColor);
        g.drawLine(0, originY, getWidth(), originY);
        g.drawLine(originX, 0, originX, getHeight());

        // create grid
        new GridDrawer(g, originX, originY).drawGrid(gridGap, getWidth(), getHeight(), foregroundColor);
        PointPlotter p = new PointPlotter(g, gridGap, new int[] { originX, originY }, pointDiameter);
        p.setColor(Color.red);
        CirclePainter.paintCircle(100, 0, 0,p);
        CirclePainter.paintCircle(46, 46, 27,p);
        CirclePainter.paintCircle(46, -46, 27,p);
        CirclePainter.paintCircle(46, 0, -54,p);
        CirclePainter.paintCircle(100, 0, 0,p);
        CirclePainter.paintCircle(8, 0, 0,p);
        CirclePainter.paintCircle(22, 0, 78,p);
        CirclePainter.paintCircle(22, 65, -38,p);
        CirclePainter.paintCircle(22, -65, -38,p);
        CirclePainter.paintCircle(10, 33, 82,p);
        CirclePainter.paintCircle(10, -33, 82,p);
        CirclePainter.paintCircle(10, 88, -14,p);
        CirclePainter.paintCircle(10, -88, -14,p);
        CirclePainter.paintCircle(10, -54, -70,p);
        CirclePainter.paintCircle(10, 54, -70,p);
        CirclePainter.paintCircle(5, 50, 80,p);
        CirclePainter.paintCircle(5, -50, 80,p);
        CirclePainter.paintCircle(6, 94, 3,p);
        CirclePainter.paintCircle(6, -94, 3,p);
        CirclePainter.paintCircle(6, 45, -83,p);
        CirclePainter.paintCircle(6, -45, -83,p);
        CirclePainter.paintCircle(4, 58, 76,p);
        CirclePainter.paintCircle(4, -58, 76,p);
        CirclePainter.paintCircle(3, 95, 14,p);
        CirclePainter.paintCircle(3, -95, 14,p);
        CirclePainter.paintCircle(3, 36, -88,p);
        CirclePainter.paintCircle(3, -36, -88,p);
        CirclePainter.paintCircle(4, 0, 50,p);
        CirclePainter.paintCircle(4, 42, -24,p);
        CirclePainter.paintCircle(4, -42, -24,p);
        CirclePainter.paintCircle(2, 65, 72,p);
        CirclePainter.paintCircle(2, -65, 72,p);
        CirclePainter.paintCircle(3, 95, 22,p);
        CirclePainter.paintCircle(3, -95, 22,p);
        CirclePainter.paintCircle(2, 31, -92,p);
        CirclePainter.paintCircle(2, -31, -92,p);
        CirclePainter.paintCircle(4, 23, 92,p);
        CirclePainter.paintCircle(4, -23, 92,p);
        CirclePainter.paintCircle(4, 91, -30,p);
        CirclePainter.paintCircle(4, -91, -30,p);
        CirclePainter.paintCircle(4, 69, -64,p);
        CirclePainter.paintCircle(4, -69, -64,p);
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
            if (points_str.length != 3) {
                input.setText("Please check your input format");
                return;
            }
            int[] endpoint_1 = new int[2];

            try {
                for (int i = 0; i < 2; i++) {
                    endpoint_1[i] = Integer.valueOf(points_str[i]);
                }
            } catch (Exception exception) {
                input.setText("Please check your input format");
                return;
            }
            repaint();
        }
    }
}
