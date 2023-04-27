package fx;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import  javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class HelloWorld  extends Application{

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello world");

        Button btn = new Button();
        btn.setText("Say Hello");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello");
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

    }
    public static void main(String[] args){
        launch(args);
    }
}
