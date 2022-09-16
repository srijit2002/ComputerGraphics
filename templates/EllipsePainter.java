package com.computer_graphics;
import java.awt.*;

public class EllipsePainter {
        public static void paintEllipse(PointPlotter p, float rx, float ry, float xc, float yc) {
                float dx, dy, d1, d2, x, y;
                x = 0;
                y = ry;
                d1 = (ry * ry) - (rx * rx * ry) +
                                (0.25f * rx * rx);
                dx = 2 * ry * ry * x;
                dy = 2 * rx * rx * y;
                while (dx < dy) {
                        p.plotPoint((int) (x + xc), (int) (y + yc));
                        p.plotPoint((int) (-x + xc), (int) (y + yc));
                        p.plotPoint((int) (x + xc), (int) (-y + yc));
                        p.plotPoint((int) (-x + xc), (int) (-y + yc));
                        if (d1 < 0) {
                                x++;
                                dx = dx + (2 * ry * ry);
                                d1 = d1 + dx + (ry * ry);
                        } else {
                                x++;
                                y--;
                                dx = dx + (2 * ry * ry);
                                dy = dy - (2 * rx * rx);
                                d1 = d1 + dx - dy + (ry * ry);
                        }
                }
                d2 = ((ry * ry) * ((x + 0.5f) * (x + 0.5f)))
                                + ((rx * rx) * ((y - 1) * (y - 1)))
                                - (rx * rx * ry * ry);

                while (y >= 0) {
                        p.plotPoint((int) (x + xc), (int) (y + yc));
                        p.plotPoint((int) (-x + xc), (int) (y + yc));
                        p.plotPoint((int) (x + xc), (int) (-y + yc));
                        p.plotPoint((int) (-x + xc), (int) (-y + yc));

                        if (d2 > 0) {
                                y--;
                                dy = dy - (2 * rx * rx);
                                d2 = d2 + (rx * rx) - dy;
                        } else {
                                y--;
                                x++;
                                dx = dx + (2 * ry * ry);
                                dy = dy - (2 * rx * rx);
                                d2 = d2 + dx - dy + (rx * rx);
                        }
                }
        }
}
