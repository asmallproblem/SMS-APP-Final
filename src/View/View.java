package View;

/*
 Created by Soeren Schou 31-05-2017.
 Created by Magnus Thygesen 31-05-2017.
 Created by Jonas Overgaard 31-05-2017.

 Our GUI for the SMS application, also containts method that
 says how long a contest last, what did they win.
*/

import Controller.Controller;
import DB_Layer.DB;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class View extends Application
{
    //Initializing our fields
    private Scene scene;
    private Button btnSend;
    private VBox vBox;
    private Button seeTabelview;
    private TextArea textArea;
    public TextField textField;
    private Label txtLabel;
    Controller controller_instance = new Controller();
    public int Jml = 40;

    public View()
    {
    }


    public static void main(String[] args)
    {
        //launching the javaFX application
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception
    {
        //Initializing the values
        sndButton();
        setSeeTabelview();
        setTextArea();
        setTextField();

        //creating layout
        vBox = new VBox(10);
        vBox.getChildren().addAll(btnSend, seeTabelview, textArea, txtLabel, textField);

        //A action when selection button is clicked, begins thread
        btnSend.setOnAction((event) -> {
            Jml = Integer.parseInt(textField.getText());
            treadBegin();
        });

        //creating Scene
        scene = new Scene(vBox, 500,420);

        //creating stage with selected scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Castus's Konkurrence System");
        primaryStage.show();
    }

    public void sendMsg()
    {
        //Sending message to user, try/catch for expection exceptions
        try {

            //initializing values
            String number = null;
            int number2;

            //calling on our DB for the data and vinder selection
            DB db;
            db = new DB();
            db.getContestNumber();
            db.readToArray();
            db.Random();

            //initializing important values, our number value is the number DB put out
            number2 = db.getNumber();
            System.out.println(number2);
            number = number.valueOf(number2);
            System.out.println(number);
            String msg = textArea.getText();
            String sender = "Castus";
            this.controller_instance.sendSMS(number, msg, sender);
        }
        catch (Exception var5)
        {
            //catching any uneksepted errors
        }//ending try/catch
    }

    public void sndButton()
    {
        //Building the start konkurrence button
        btnSend = new Button("Start konkurrence");
        btnSend.setMaxWidth(150);
        btnSend.setTranslateX(40);
        btnSend.setTranslateY(292);
    }


    public void setSeeTabelview()
    {
        //building the tabel view button
        seeTabelview = new Button("Se kundetabel");
        seeTabelview.setMaxWidth(150);
        seeTabelview.setTranslateX(300);
        seeTabelview.setTranslateY(250);
    }


    public void setTextArea()
    {
        //building the text area
        textArea = new TextArea();
        txtLabel = new Label("Besked her!");
        textArea.setTranslateX(0);
        textArea.setTranslateY(-50);
        txtLabel.setTranslateY(-310);
        txtLabel.setTranslateX(10);
    }

    public void setTextField()
    {
        //building the text field
        textField = new TextField();
        textField.setPromptText("konkurrence i sec.");
        textField.setTranslateX(40);
        textField.setTranslateY(10);
        textField.setMaxWidth(150);
    }

    public void treadBegin()
    {
        //start the thread with a given parameter from the class "RunningThread"
        RunningThread thread1 = new RunningThread("TestThread", this );
        thread1.start();
    }
}

//creating class that implemets a runnable thread
class RunningThread implements Runnable
{
    //Initializing fields including the thread
    private Thread thread;
    private String threadName;
    View view;

    RunningThread(String Name, View view)
    {
        //Pointing out this value to the respected parameter
        threadName = Name;
        this.view = view;
        System.out.println("Creating: " + threadName);
    }

    @Override
    public void run()
    {
        //Run our thread
        System.out.println("Running " + threadName);
        //creating try catch to find unexcepted errors in thread
        try
        {
            // for loop set for how long the thread last
            for(int i = 1; i <= view.Jml; i++)
            {
                System.out.println("Thread: " + threadName + ", " + i);
                java.lang.Thread.sleep((long)(1000));
            }
            System.out.println("Thread ending");

        }
        catch (InterruptedException e)
        {
            //prints out error
            System.out.println("Error catched " + e);
        }
        //calls method "sendMsg" from view
        view.sendMsg();
    }
    public void start()
    {
        //our start method to start the thread with giving name
        // if null, it tries again
        System.out.println("Starting: " + threadName);
        if(thread == null)
        {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}
