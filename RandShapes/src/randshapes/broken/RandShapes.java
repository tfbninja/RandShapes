package randshapes.broken;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Tim Barber & Elliot McCormick
 */
public class RandShapes extends Application {

    private ArrayList<Shape> shapes;
    public static final double SWIDTH = 800;
    public static final double SHEIGHT = 600;
    private Screen screen;
    private double maxSize = 100;
    private boolean rotating = false;
    private double rotateAmt = 0.5;

    @Override
    public void start(Stage primaryStage) {
        screen = new Screen(SWIDTH, SHEIGHT);
        shapes = new ArrayList<>();
        StackPane root = new StackPane();
        screen.draw(shapes);
        root.getChildren().add(screen.getCanvas());

        Scene scene = new Scene(root, SWIDTH, SHEIGHT);

        primaryStage.setTitle("Shapes");
        primaryStage.setScene(scene);
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (rotating) {
                    for (Shape s : shapes) {
                        s.rotate(rotateAmt);
                    }
                }
                screen.draw(shapes);
            }
        }.start();

        scene.setOnMousePressed(
                (MouseEvent event) -> {
                    double s = (int) (Math.random() * maxSize);
                    addShape(event.getX() - (s / 2), event.getY() - (s / 2), s, (int) (Math.random() * Shape.NUMSHAPETYPES));
                }
        );

        scene.setOnKeyPressed(
                (KeyEvent eventa) -> {
                    if (eventa.getCode() == KeyCode.ENTER) {
                        addRandomShape(primaryStage.getWidth(), primaryStage.getHeight());
                    } else if (eventa.getCode() == KeyCode.Z && eventa.isControlDown()) {
                        removeLastShape();
                    } else if (eventa.getCode() == KeyCode.R) {
                        rotating = !rotating;
                    }
                }
        );
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void addRandomShape(double xBound, double yBound) {
        double x = Math.random() * xBound;
        double y = Math.random() * yBound;
        double r = Math.random() * maxSize;
        int type = (int) (Math.random() * Shape.NUMSHAPETYPES);
        addShape(x, y, r, type);
    }

    public void addShape(double x, double y, double r, Color c, int type) {
        Shape temp = new Shape(x, y, r, type);
        temp.setColor(c);
        shapes.add(temp);
    }

    public void addShape(double x, double y, double r, int type) {
        Shape temp = new Shape(x, y, r, type);
        shapes.add(temp);
    }

    public void removeLastShape() {
        if (shapes.size() >= 1) {
            shapes.remove(shapes.size() - 1);
        }
    }
}
