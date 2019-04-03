package randshapes.broken;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Tim Barber & Elliot McCormick
 */
public class Screen {

    private Canvas c;
    private double width;
    private double height;

    public Screen(double width, double height) {
        this.width = width;
        this.height = height;
        c = new Canvas(width, height);
    }

    public Canvas getCanvas() {
        return c;
    }

    public void setC(Canvas c) {
        this.c = c;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void draw(ArrayList<Shape> shapes) {
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        for (Shape s : shapes) {
            s.draw(c);
        }
    }
}
