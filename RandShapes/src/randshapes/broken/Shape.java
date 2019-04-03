package randshapes.broken;

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
                double[] starXPs = {x, x + 10, x + 20};
                double[] starYPs = {y, y - 10, y + 20};
                gc.strokePolygon(rotateXList(starXPs, starYPs, theta), rotateYList(starXPs, starYPs, theta), 3);
                break;
            case RECT:
                if (color == null) {
                    gc.setStroke(Color.DODGERBLUE);
                }
                double[] rectXPs = {x, x + 10, x + 20};
                double[] rectYPs = {y, y - 10, y + 20};
                gc.strokePolygon(rotateXList(rectXPs, rectYPs, theta), rotateYList(rectXPs, rectYPs, theta), 3);
                break;
            case TRI:
                if (color == null) {
                    gc.setStroke(Color.ORANGERED);
                }
                double[] triXPs = {x, x + r / 2, x + r};
                double[] triYPs = {y + r, y, y + r};
                gc.strokePolygon(rotateXList(triXPs, triYPs, theta), rotateYList(triXPs, triYPs, theta), 3);
                break;
            default:
                break;
        }
    }
    
    public static double[] rotateXList(double[] xList, double[] yList, double theta) {
        double[] out = new double[xList.length];
        for (int i = 0; i < xList.length; i++) {
            out[i] = xList[i] + rotateX(RandShapes.SWIDTH / 2, RandShapes.SHEIGHT / 2, theta);
        }
        return out;
    }
    
    public static double[] rotateYList(double[] xList, double[] yList, double theta) {
        double[] out = new double[yList.length];
        for (int i = 0; i < yList.length; i++) {
            out[i] = yList[i] + rotateY(RandShapes.SWIDTH / 2, RandShapes.SHEIGHT / 2, theta);
        }
        return out;
    }

    public static double rotateX(double x, double y, double theta) {
        return x * Math.cos(Math.toRadians(theta)) - y * Math.sin(Math.toRadians(theta));
    }

    public static double rotateY(double x, double y, double theta) {
        return x * Math.sin(Math.toRadians(theta)) + y * Math.cos(Math.toRadians(theta));
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
