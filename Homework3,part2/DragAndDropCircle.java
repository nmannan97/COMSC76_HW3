import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;

/**
 * Write a description of JavaFX class DragAndDropCircle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DragAndDropCircle extends Application
{
    // We keep track of the count, and label displaying the count:
    private int count = 0;
    private Label myLabel = new Label("0");
    private Line distance = new Line();
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage)
    {
        // Create a Button or any control item
        Circle circle1 = new Circle( 40,  40, 10);
        Circle circle2 = new Circle(120, 120, 10);
        Group root = new Group();
        distance.setStartX(circle1.getCenterX());
        distance.setStartY(circle1.getCenterY());
        
        distance.setEndX(circle2.getCenterX());
        distance.setEndY(circle2.getCenterY());
        
        circle1.setFill(Color.TRANSPARENT);
        circle1.setStroke(Color.RED);
        
        circle2.setFill(Color.TRANSPARENT);
        circle2.setStroke(Color.BLACK);
        
        circle1.setOnMouseDragged(e-> {
                /* drag was detected, start drag-and-drop gesture*/
                
                /* allow any transfer mode */
                circle1.setCenterX(e.getX());
                circle1.setCenterY(e.getY());
                distance.setStartX(circle1.getCenterX());
                distance.setStartY(circle1.getCenterY());
                
                /* put a string on dragboard */
            });
        circle2.setOnMouseDragged(e-> {
                /* drag was detected, start drag-and-drop gesture*/
                
                /* allow any transfer mode */
                circle2.setCenterX(e.getX());
                circle2.setCenterY(e.getY());
                distance.setEndX(circle2.getCenterX());
                distance.setEndY(circle2.getCenterY());
                /* put a string on dragboard */
            });
        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);

        root.getChildren().add(circle2);
        root.getChildren().add(circle1);
        root.getChildren().add(distance);
        // JavaFX must have a Scene (window content) inside a Stage (window)
        pane.getChildren().add(root);
        Scene scene = new Scene(pane, 300,100);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
    }
}
