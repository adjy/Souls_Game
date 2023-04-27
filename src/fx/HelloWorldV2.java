package fx;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import  javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorldV2 extends Application {
    Button btn;
    public void start(Stage primaryStage) throws Exception{
        buildUI(primaryStage);
        addListeners();
        primaryStage.show();
    }

    private void buildUI(Stage stage){
        stage.setTitle("Hello world");

        btn = new Button();
        btn.setText("Say Hello");

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        stage.setScene(new Scene(root, 300, 250));

    }
    private void addListeners(){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello");
            }
        });
    }

    public static void main(String[] args){
        launch(args);
    }

}
