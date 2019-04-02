package randshapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Tim Barber & Elliot McCormick
 */
public class Shape {

    private double x;
    private double y;
    private double theta;
    private double r;
    private int t; // type
    private Color color;
    public static final int STAR = 0;
    public static final int RECT = 1;
    public static final int TRI = 2;
    public static final int NUMSHAPETYPES = 3;

    public Shape(double x, double y, double r, int type) {
        this.x = x;
        this.y = y;
        this.t = type;
        this.theta = 0;
        this.r = r;
    }

    public Shape(double x, double y, double r, int type, Color c) {
        this.x = x;
        this.y = y;
        this.t = type;
        this.theta = 0;
        this.r = r;
        this.color = c;
    }

    public double[] scaleList(double[] list, double scalar) {
        double[] copy = new double[list.length];
        for (int i = 0; i < list.length; i++) {
            copy[i] = list[i] * scalar;
        }
        return copy;
    }

    public double[] incrementList(double[] list, double increment) {
        double[] copy = new double[list.length];
        for (int i = 0; i < list.length; i++) {
            copy[i] = list[i] + increment;
        }
        return copy;
    }

    public void draw(Canvas c) {
        GraphicsContext gc = c.getGraphicsContext2D();

        if (color != null) {
            gc.setStroke(color);
        }
        switch (t) {
            case STAR:
                if (color == null) {
                    gc.setStroke(Color.YELLOW);
                }
                double[] starXPs = {};
                double[] starYPs = {};
                gc.strokePolygon(starXPs, starYPs, 4);
                break;
            case RECT:
                if (color == null) {
                    gc.setStroke(Color.DODGERBLUE);
                }
                double[] rectXPs = {};
                double[] rectYPs = {};
                gc.strokePolygon(rectXPs, rectYPs, 4);
                break;
            case TRI:
                if (color == null) {
                    gc.setStroke(Color.ORANGERED);
                }
                double[] triXPs = {x + r * Math.cos(theta), x + r * Math.cos(theta) + r / 2, x + r * Math.cos(theta) + r};
                double[] triYPs = {y + r * Math.sin(theta) + r, y + r * Math.sin(theta), y + r * Math.sin(theta) + r};
                gc.strokePolygon(triXPs, triYPs, 3);
                break;
            default:
                break;
        }
    }

    public void rotate(double degrees) {
        theta += degrees;
        if (theta % 360 == 0) {
            theta = 0;
        }
    }

    public void setColor(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }
}
