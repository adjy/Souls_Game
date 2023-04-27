package lsg.graphics.panes;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lsg.graphics.widgets.texts.GameLabel;


public class CreationPane extends  VBox{
    private TextField nameField;


    public TextField getNameField() {
        return nameField;
    }
    public CreationPane(){
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(100,0,0,0));

        this.setSpacing(20);
        this.nameField = new TextField();
        this.getChildren().add(new GameLabel("Player Name"));
        this.getChildren().add(this.nameField );
        this.nameField.setAlignment(Pos.CENTER);
        this.nameField.setMaxHeight(10);
        this.nameField.setStyle("-fx-max-width: 250px");

    }

        public void fadeIn(EventHandler<ActionEvent> finishedHandler){
        FadeTransition ft = new FadeTransition() ;
        ft.setNode(this);
        ft.setDuration(Duration.millis(1000));
        ft.setToValue(1);
        ft.setOnFinished((event)->{
            try{
                finishedHandler.handle(event);
            }catch (NullPointerException e){}
        });
        ft.play();
    }

}