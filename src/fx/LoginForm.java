package fx;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import  javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class LoginForm extends Application {

    Button btn;
    Text actionTarget;
    public void start(Stage primaryStage) throws Exception{
        buildUI(primaryStage);
        addListeners();
        primaryStage.show();
    }

    private void buildUI(Stage stage){
        stage.setTitle("JavaFX welcome");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text sceneTitle = new Text("Welcome");
//        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        sceneTitle.setId("welcome-text");
        grid.add(sceneTitle,0,0,2,1);

        Label username = new Label("Username");
        grid.add(username, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pwd = new Label("Password: ");
        grid.add(pwd, 0, 2);

        PasswordField pwdField = new PasswordField();
        grid.add(pwdField, 1, 2);

        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        btn = new Button("Sign in");
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        actionTarget = new Text();
        actionTarget.setId("action-target");
        grid.add(actionTarget, 1, 6);

        Scene scene = new Scene(grid, 300, 275);
        stage.setScene(scene);
//        scene.getStylesheets().add("css/LoginFormCSS.css");
        scene.getStylesheets().add(getClass().getResource("css/LoginFormCSS.css").toExternalForm());
//        scene.getStylesheets().add(LoginFormCSS.class.getResource("css/LoginFormCSS.css").toExternalForm());
    }
    private void addListeners(){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               actionTarget.setFill(Color.FIREBRICK);
               actionTarget.setText("Sign in button pressed");
            }
        });

//        btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (event)->{
//            System.out.println("!!! Mouse in button: " + event);
//        });
    }

    public static void main(String[] args){
        launch(args);
    }


}
