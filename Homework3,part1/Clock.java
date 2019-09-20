import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

import java.util.*;
import java.time.*;
import java.math.*;
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
    private int timeHour = 0;
    private int timeMin = 0;
    private int timeSec = 0;
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
        Line hour = new Line(50, 50, 
                             (50 + 50*(Math.cos(hour()))), 
                             (50 + 50*(Math.sin(hour()))));
        Line min = new Line(50, 50, 
                             50 + 70*(Math.cos(min())), 
                             50 + 70*(Math.sin(min())));
        min.setStroke(Color.GREEN);
        
        Line sec = new Line(50, 50, 
                             50 + 85*Math.cos(sec()), 
                             50 + 85*Math.sin(sec()));
        sec.setStroke(Color.RED);                     
             
        root.getChildren().add(sec);
        root.getChildren().add(hour);
        root.getChildren().add(min);
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 300,300);
        stage.setTitle("Clock");
        stage.setScene(scene);
        pane.getChildren().add(root);
        
        // Show the Stage (window)
        stage.show();
        stage.toFront();
        while(stage.isAlwaysOnTop()){
           sec = new Line(50, 50, 
                       50 + 85*Math.cos(sec()), 
                       50 + 85*Math.sin(sec()));
            //stage.show();
           root.getChildren().add(sec);
        }
    }
    private double hour()
    {
        timeHour = now.get(Calendar.HOUR);
        double angle = ((double)timeHour/12)*2*Math.PI - Math.PI/2;
        return angle;
    }
    private double min()
    {
        timeMin = now.get(Calendar.MINUTE);
        double angle = ((double)timeMin/60)*2*Math.PI - Math.PI/2;
        return angle;
    }
    private double sec()
    {
        this.timeSec = now.get(Calendar.SECOND);
        double angle = ((double)timeSec/60)*2*Math.PI - Math.PI/2;
        return angle;
    }
}
