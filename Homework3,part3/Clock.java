import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import javafx.stage.Window;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.scene.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.input.*;

import java.util.*;
import java.time.*;
import java.math.*;
import javafx.util.Duration;
/**
 * Write a description of JavaFX class Clock here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Clock extends Application
{
    private Circle clock = new Circle(50, 50, 100);
    private Circle circle = new Circle(50, 50, 90);
    private Text[] time = new Text[12];
    private Line[] ticks = new Line[60];
    private Line[] tickCover = new Line[60];
    private Calendar now = new GregorianCalendar();
    private int timeHour = now.get(Calendar.HOUR);
    private int timeMin = now.get(Calendar.MINUTE);
    private int timeSec = now.get(Calendar.SECOND);
    private Line hour = new Line(50, 50, 
                                (50 + 50*(Math.cos(hour()))), 
                                (50 + 50*(Math.sin(hour()))));
    private Line min = new Line(50, 50, 
                                50 + 70*(Math.cos(min())), 
                                50 + 70*(Math.sin(min())));
    private Line sec = new Line(50, 50, 
                                50 + 85*Math.cos(sec()), 
                                50 + 85*Math.sin(sec()));
    private Button start = new Button("Start");
    private Button stop = new Button("Stop");
    private boolean active = true;
    @Override
    public void start(Stage stage)
    {
        // Create a new grid pane        
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);
        Group root = new Group(); 
        Text currentTime = new Text(-5, 200, 
                                    "Current time: " 
                                    + now.get(Calendar.HOUR) + ":" +
                                    now.get(Calendar.MINUTE) + ":" +
                                    + now.get(Calendar.SECOND) ); 
        start.setLayoutX(0);
        start.setLayoutY(250);
        //setting up the clock
        clock.setFill(Color.TRANSPARENT);
        clock.setStroke(Color.BLACK);
        root.getChildren().add(clock);
        //setting up the numbers 3,6,9, & 12
        for(int i = 0;i<60;i++)
        {
            ticks[i] = new Line(50, 50,
                                50 +100*(Math.cos(((double)i/60)*2*Math.PI - Math.PI/2)),
                                50 +100*(Math.sin(((double)i/60)*2*Math.PI - Math.PI/2)));
            tickCover[i] = new Line(50, 50,
                                50 +90*(Math.cos(((double)i/60)*2*Math.PI - Math.PI/2)),
                                50 +90*(Math.sin(((double)i/60)*2*Math.PI - Math.PI/2)));
            tickCover[i].setStroke(Color.WHITE);
            root.getChildren().add(ticks[i]);
            root.getChildren().add(tickCover[i]);
        }
        circle.setFill(Color.WHITE);
        root.getChildren().add(circle);
        root.getChildren().add(currentTime);
        for(int i = 0;i<12;i++)
        {
            time[i] = new Text(45 + 70*(Math.cos(((double)i/12)*2*Math.PI - Math.PI/3)),
                               50 + 70*(Math.sin(((double)i/12)*2*Math.PI - Math.PI/3)),
                               Integer.toString(i+1));
            root.getChildren().add(time[i]);
        }
        //Making the hands of the clock
        
        sec.setStroke(Color.RED);                     
        min.setStroke(Color.GREEN);
        
        root.getChildren().add(sec);
        root.getChildren().add(hour);
        root.getChildren().add(min);
        // JavaFX must have a Scene (window content) inside a Stage (window)
        
        pane.getChildren().add(root);
        pane.add(start,0,1);
        pane.add(stop ,1,1);
        /**scene.addEventHandler(WindowEvent.WINDOW_SHOWING, 
                new EventHandler<WindowEvent>(){
                    public void handle(WindowEvent w){
                        Line sec = new Line(50, 50, 
                             50 + 85*Math.cos(sec()), 
                             50 + 85*Math.sin(sec()));
                    }
                });
        **/
        // Show the Stage (window)
        EventHandler<MouseEvent> startHandler = new EventHandler<MouseEvent>()
        {
            @Override 
            public void handle(MouseEvent e) {
                active = true;
            }
        };
        EventHandler<MouseEvent> stopHandler = new EventHandler<MouseEvent>()
        {
            @Override 
            public void handle(MouseEvent e) {
                active = false;
            }
        };
        start.addEventHandler(MouseEvent.MOUSE_CLICKED, startHandler);
        stop.addEventHandler(MouseEvent.MOUSE_CLICKED, stopHandler);
        EventHandler<ActionEvent> eventHandler = e->
        {
                if(active){
                    now = new GregorianCalendar(); 
                
                    timeSec = now.get(Calendar.SECOND);
                    timeHour = now.get(Calendar.HOUR);
                    timeMin = now.get(Calendar.MINUTE);
                
                    hour.setEndX(50 + 50*Math.cos(hour()));
                    hour.setEndY(50 + 50*Math.sin(hour()));
                
                    min.setEndX(50 + 70*Math.cos(min()));
                    min.setEndY(50 + 70*Math.sin(min()));
                
                    sec.setEndX(50 + 85*Math.cos(sec()));
                    sec.setEndY(50 + 85*Math.sin(sec()));
                    currentTime.setText("Current time: " 
                                    + now.get(Calendar.HOUR) + ":" +
                                    now.get(Calendar.MINUTE) + ":" +
                                    + now.get(Calendar.SECOND) );
                }
        };
        Scene scene = new Scene(pane, 300,300);
        //scene.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);  
        Timeline animation = new Timeline(
            new KeyFrame(Duration.millis(1000),
            eventHandler));
            
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        
        stage.setTitle("Clock");
        stage.setScene(scene);

        stage.setTitle("ClockAnimation"); // Set the stage title 29
        stage.show();

        stage.toFront();
        
    }
    private double hour()
    {        
        double angle = ((double)this.timeHour/12)*2*Math.PI - Math.PI/2;
        return angle;
    }
    private double min()
    {
        double angle = ((double)this.timeMin/60)*2*Math.PI - Math.PI/2;
        return angle;
    }
    private double sec()
    {
        double angle = ((double)this.timeSec/60)*2*Math.PI - Math.PI/2;
        return angle;
    }
}