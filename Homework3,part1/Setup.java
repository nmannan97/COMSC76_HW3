import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.lang.Math;
/**
 * Write a description of JavaFX class Setup here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Setup extends Application
{
    // We keep track of the count, and label displaying the count:
    private int count = 0;
    private Label myLabel = new Label("0");
    private int row = 0;
    private double years = 0;
    private double Rate = 0;
    private double amount = 0;
    private double Value = 0;
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
        final TextField year = new TextField();
        final TextField investment = new TextField();
        final TextField rate = new TextField();
        final TextField value = new TextField();
        // Create a Button or any control item
        Button myButton = new Button("Calculate");
        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);
        // making text fields
        investment.setPromptText("Investment amounts");
        investment.setPrefColumnCount(10);
        investment.getText();
        GridPane.setConstraints(investment, 0, row);
        pane.getChildren().add(investment);
        row = row + 1;
            //Years investing 
        year.setPromptText("year amount");
        year.setPrefColumnCount(10);
        year.getText();
        GridPane.setConstraints(year, 0, row);
        pane.getChildren().add(year);
        row = row + 1;
        //

            //Rate
        rate.setPromptText("rate amount");
        rate.setPrefColumnCount(10);
        rate.getText();
        GridPane.setConstraints(rate, 0, row);
        pane.getChildren().add(rate);
        //
        row = row + 1;
            //Value        
        //set an action on the button using method reference
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e){
                amount = Double.valueOf(investment.getText());
                System.out.println(amount);
                years = Double.valueOf(year.getText());
                System.out.println(years);
                Rate = Double.valueOf(rate.getText());
                System.out.println(years);
            }
        };
        myButton.setOnAction(this::buttonClick);
        myButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        // Add the button and label into the pane
        pane.add(myLabel, 1, 0);
        pane.add(myButton, 0, row);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 300,100);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
    }
    /**
     * This will be executed when the button is clicked
     * It increments the count by 1
     */
    private void buttonClick(ActionEvent event)
    {
        // Counts number of button clicks and shows the result on a label
        Value = Math.pow((1+Rate),12*years);
        Value = Value*amount;
        System.out.println(Value);
        myLabel.setText("Final amount: " + Value);
    }
}
