/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package working;

/**
 *
 * @author elliot and tim barber
 */
/*
 * This is another exercise in ArrayLists and an example of inheritance and polymorphism.
 * Please add an ArrayList instance variable that will hold all of the shapes you will display on the screen.
 * In the handle method at the bottom of this class, please go through all of the shapes in the Arraylist and draw them and if rotating is on, rotate them on the screen.
 * (you can call the rotate method in the shape class and pass in the following value as a parameter - Math.PI / 120)
 */
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Matt
 */
public class Main extends Application {

    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private Canvas canvas;
    private RotationTimer timer;
    private ArrayList<Shape> shapes;
    
    private boolean rotating;

    @Override
    public void start(Stage primaryStage) {
        shapes = new ArrayList();
        canvas = new Canvas(WIDTH, HEIGHT);
        canvas.getGraphicsContext2D().setLineWidth(5);
        timer = new RotationTimer();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);

        scene.setOnKeyPressed((KeyEvent) -> {
            switch (KeyEvent.getCode()) {
                case ENTER:
                    addNewShape();
                    break;
                case SPACE:
                    toggleRotates();
                    break;
                case Z:
                    if (KeyEvent.isControlDown()) {
                        removeLastShape();
                        break;
                    }
                case T:
                    if (KeyEvent.isControlDown()) {
                        removeTriangles();
                        break;
                    }
                case R:
                    if (KeyEvent.isControlDown()) {
                        removeRectangles();
                        break;
                    }
                case S:
                    if (KeyEvent.isControlDown()) {
                        removeStars();
                        break;
                    }

            }
        });

        primaryStage.setTitle("Shapes!");
        primaryStage.setScene(scene);
        primaryStage.show();

        timer.start();
    }

    private void addNewShape() {
        shapes.add(getRandomShape());
    }

    private Shape getRandomShape() {
        int random = (int)(Math.random() * 3);
        int maxSize = 80;
        if (random == 0){
            return new Triangle(Math.random() * WIDTH, Math.random() * HEIGHT, Math.random() * maxSize);
        } else if (random == 1){
            return new Rectangle(Math.random() * WIDTH, Math.random() * HEIGHT, Math.random() * maxSize, Math.random() * maxSize);
        } else {
            return new Star(Math.random() * WIDTH, Math.random() * HEIGHT, Math.random() * maxSize/2);
        }
    }

    private void toggleRotates() {
        rotating = !rotating;
    }

    private void removeLastShape() {
        shapes.remove(shapes.size() - 1);
    }

    public Shape getLastShape() {
        return shapes.get(shapes.size() - 1);
    }

    public void removeTriangles() {
        for (int i = shapes.size() - 1; i >= 0; i--){
            if (shapes.get(i) instanceof Triangle){
                shapes.remove(i);
            }
        }
    }
    public void removeRectangles() {
        for (int i = shapes.size() - 1; i >= 0; i--){
            if (shapes.get(i) instanceof Rectangle){
                shapes.remove(i);
            }
        }
    }
    public void removeStars() {
        for (int i = shapes.size() - 1; i >= 0; i--){
            if (shapes.get(i) instanceof Star){
                shapes.remove(i);
            }
        }
    }
//        Add remove Rectangles and Stars as well
//		  Add remove a random shape - see above - as well

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public class RotationTimer extends AnimationTimer {

        @Override
        public void handle(long now) {
            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            // draw and rotate all shape objects
            for (Shape shape : shapes){
                shape.draw(canvas);
            }
            if (rotating){
                for (Shape shape : shapes){
                    shape.rotate(Math.PI / 120);
                }
            }
        }

    }

}
