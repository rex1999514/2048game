package com.company;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<KeyEvent> {

    int size=4;
    Text score;
    Group root;
    Group text;
    numbers data;
    gui grid;
    //chose StackPane as layout so every block will placed evenly
    StackPane p =new StackPane();
    Group n=new Group();
    Pane s = new Pane();
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game of 2048");
        grid=new gui();
        grid.setMenuButton();
        n.getChildren().addAll(grid.getMenubackGround(),grid.getEasy(),grid.getMedian(),grid.getHard());
        //level scene
        Scene first = new Scene(n,500,600);
        //main game scene
        Scene scene = new Scene(p, 1000, 800);
        //switch scenes based on level
        grid.getEasy().setOnAction(event ->
        {
            size=4;
            setUp(size,1);
            primaryStage.setScene(scene);
        });
        grid.getMedian().setOnAction(event ->
        {
            size =5;
            setUp(size,2);
            primaryStage.setScene(scene);
        });
        grid.getHard().setOnAction(event ->
        {
            size=6;
            setUp(size,4);
            primaryStage.setScene(scene);
        });
        //click to play
        scene.setOnMouseClicked(mouseEvent ->
                {
                    score.setText("");
                    scene.setOnKeyPressed(this);
                }
        );
        //show the level scene first
        primaryStage.setScene(first);
        primaryStage.show();

    }
    @Override
    public void handle(KeyEvent keyEvent) {
        //When button is clicked, handle() gets called
        //Button click is an ActionEvent (also MouseEvents, TouchEvents, etc...)
        switch (keyEvent.getCode()) {
            case DOWN:
                down();
                break;
            case UP:
                up();
                break;
            case LEFT:
                left();
                break;
            case RIGHT:
                right();
                break;
        }
        if(data.lose())
        {
            end("you lose! ");
        }
        if(data.win())
        {
            end("you win !! ");
        }
    }
    //set up the panel,
    public void setUp(int s,int multiple)
    {
        grid.setSize(s);
        data=new numbers(s);
        data.setMax(multiple);
        initial();
        root=new Group(grid.getBoard());
        text=new Group(grid.getText_number());
        score=new Text(data.max +"  click to play ");
        score.setFill(Color.rgb(237,119,35));
        score.setFont(Font.font("SansSerif",  100));
        p.getChildren().addAll(grid.getBackGround(),root,text,score);
        Text score = new Text(800, 700, "SCORE:                        ");
        p.getChildren().add(score);
        p.setAlignment(score, Pos.TOP_RIGHT);
    }
    //create an end button to display exit words and exit the window
    public void end(String answer)
    {
        Rectangle exit=new Rectangle(300,300);
        exit.setArcHeight(30);
        exit.setArcWidth(30);
        Button exitBtn = new Button( answer);
        exitBtn.setShape(exit);
        exitBtn.setStyle("-fx-background-color: #fff89b; -fx-font-size: 3em;");
        exitBtn.setMinSize(300,300);
        p.getChildren().add(exitBtn);
        exitBtn.setOnAction(e -> Platform.exit());
    }
    //set up the numbers,and fill blocks with colors
    public void initial()
    {
        data.setNumbers();
        grid.setBackGround();
        grid.fillBoard(data);
    }

    //move up
    public void up()
    {
        data.up();
        grid.modifyText(data);
    }

    //move down
    public void down()
    {
        data.down();
        grid.modifyText(data);
    }
    //move right
    public void right()
    {
        data.right();
        grid.modifyText(data);
    }

    //move left
    public void left()
    {
        data.left();
        grid.modifyText(data);
    }

}