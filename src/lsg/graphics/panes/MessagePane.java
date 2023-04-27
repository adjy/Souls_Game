package lsg.graphics.panes;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lsg.graphics.widgets.texts.GameLabel;

public class MessagePane extends VBox {
    public MessagePane(){
        this.setAlignment(Pos.CENTER);
    }

    public void showMessage(String msg){
        GameLabel ms = new GameLabel(msg);
        this.getChildren().add(ms);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(3));
        tt.setToY(-200);

        FadeTransition st = new FadeTransition(Duration.seconds(3));
        st.setToValue(0);

        ParallelTransition pt = new ParallelTransition(tt, st);
        // pt.setNode(this);
        pt.setNode(ms);


        pt.setCycleCount(1);
        pt.setOnFinished(event-> {
            this.getChildren().remove(ms);

        });
        pt.play();

    }

}
