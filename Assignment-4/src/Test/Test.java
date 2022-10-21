import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import com.computer_graphics.*;


public class Test extends Applet implements ActionListener {
    Color zoomInButtonColor = Color.CYAN;
    Color zoomOutButtonColor = Color.ORANGE;
    Color drawLineButtonColor = Color.green;

    Color backgroundColor = Color.white;
    Color foregroundColor = Color.black;

    int[] gridColorRGB = { 0, 0, 0 };
    int[] axisColorRGB = { 255, 0, 0 };
    int scale = 1;
    int gridGap;
    int pointDiameter = 13;
    Button zoomInButton, zoomOutButton, drawLineButton;
    TextField input;
    int rx=10,ry=10,xc=20,yc=20;
    public void init() {
        this.setBackground(backgroundColor);
        zoomInButton = createButton("Zoom In", zoomInButtonColor);
        add(zoomInButton);
        zoomInButton.addActionListener(this);

        zoomOutButton = createButton("Zoom Out", zoomOutButtonColor);
        add(zoomOutButton);
        zoomOutButton.addActionListener(this);

        drawLineButton = createButton("Draw Ellipse", drawLineButtonColor);
        add(drawLineButton);
        drawLineButton.addActionListener(this);

        input = new TextField("10 20 20 20");
        add(input);
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
        p.setColor(Color.blue);
         
        
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
            String[] points_str = userinput.split(" ");
            rx=Integer.parseInt(points_str[0]);
            rx=Integer.parseInt(points_str[1]);
            rx=Integer.parseInt(points_str[2]);
            rx=Integer.parseInt(points_str[3]);
            repaint();
        }
    }
}
