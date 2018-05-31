package Lesson7;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Java level 1.
 * Homework for lesson 7.
 *
 * @author Bogdanov Anton.
 * @version dated May 31, 2018.
 * @link https://github.com/BaklaYner/Homeworks-Java-level-1
 */

public class Lesson7 extends Application {
    private Canvas[] canvases;
    private GraphicsContext gc;
    private Cat[] cats;
    private Label lb;
    private Bowl bowl;
    private Random random = new Random();
    private String[] names = {"Barsik", "Murzik", "Matroskin", "Gutalin", "Belyash"};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        bowl = new Bowl(300, 101 + random.nextInt(200));
        cats = new Cat[names.length];
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat(names[i], (21 + random.nextInt(30)));
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Feed the cats");
        primaryStage.setResizable(false);

        BorderPane rootNode = new BorderPane();
        Scene myScene = new Scene(rootNode, 610, 400);

        Button addFood = new Button("Add Food");
        addFood.setPrefSize(100, 25);
        Button feedCats = new Button("Feed");
        feedCats.setPrefSize(100, 25);
        Button reset = new Button("Reset");
        reset.setPrefSize(100, 25);

        addFood.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                bowl.increaseFood();
                lb.setText(showStatistic());
                gc = canvases[0].getGraphicsContext2D();
                gc.fillRect(10, 10, bowl.getFoodQuantity() * 2, 25);
            }
        });

        feedCats.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < cats.length; i++) {
                    cats[i].eat(bowl);
                    if (cats[i].isSatisfied()) {
                        gc = canvases[i + 1].getGraphicsContext2D();
                        gc.fillRect(10, 10, cats[i].getAppetite() * 2, 25);
                    }
                }
                gc = canvases[0].getGraphicsContext2D();
                gc.clearRect(10, 10, bowl.getVOLUME() * 2, 25);
                gc.strokeRect(10, 10, bowl.getVOLUME() * 2, 25);
                gc.fillRect(10, 10, bowl.getFoodQuantity() * 2, 25);

                lb.setText(showStatistic());
            }
        });

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                init();
                gc = canvases[0].getGraphicsContext2D();
                gc.clearRect(10, 10, bowl.getVOLUME() * 2, 27);
                gc.strokeRect(10, 10, bowl.getVOLUME() * 2, 25);
                gc.fillRect(10, 10, bowl.getFoodQuantity() * 2, 25);
                for (int i = 0; i < cats.length; i++) {
                    gc = canvases[i + 1].getGraphicsContext2D();
                    gc.clearRect(10, 10, 110, 27);
                    gc.strokeRect(10, 10, cats[i].getAppetite() * 2, 25);
                }

                lb.setText(bowl.toString());
            }
        });

        FlowPane header = new FlowPane(20, 20, addFood, feedCats, reset);
        header.setAlignment(Pos.CENTER);
        rootNode.setTop(header);

        canvases = new Canvas[cats.length + 1];
        canvases[0] = new Canvas(620, 40);
        gc = canvases[0].getGraphicsContext2D();
        gc.strokeRect(10, 10, bowl.getVOLUME() * 2, 25);
        gc.setFill(Color.RED);
        gc.fillRect(10, 10, bowl.getFoodQuantity() * 2, 25);
        for (int i = 0; i < cats.length; i++) {
            canvases[i + 1] = new Canvas(620, 40);
            gc = canvases[i + 1].getGraphicsContext2D();
            gc.setFill(Color.GREEN);
            gc.strokeRect(10, 10, cats[i].getAppetite() * 2, 25);
        }
        FlowPane content = new FlowPane(20, 20, canvases);
        content.setOrientation(Orientation.VERTICAL);
        rootNode.setCenter(content);

        lb = new Label(bowl.toString());
        lb.setFont(new Font(18));
        rootNode.setBottom(lb);
        BorderPane.setAlignment(lb, Pos.CENTER);

        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    private String showStatistic() {
        int numOfSatisfiedCats = 0;
        for (Cat cat : cats) {
            if (cat.isSatisfied()) {
                numOfSatisfiedCats++;
            }
        }
        return "There are " + numOfSatisfiedCats + " from " + cats.length + " cats are satisfied. " + bowl;
    }
}