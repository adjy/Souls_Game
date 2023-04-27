package lsg.graphics.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import lsg.graphics.widgets.characters.statbars.StatBar;

public class HUDPane extends BorderPane {
    private MessagePane messagePane;
    private StatBar heroStatBar, monsterStatBar;

    public StatBar getHeroStatBar() {
        return heroStatBar;
    }

    public StatBar getMonsterStatBar() {
        return monsterStatBar;
    }

    public MessagePane getMessagePane() {return messagePane;}

    public HUDPane(){
       this.setPadding(new Insets(80,10,10,10));

        buildCenter();
        buildTop();
    }

    public void buildCenter(){

        messagePane = new MessagePane();
        this.setCenter(messagePane);

    }
    public void buildTop(){
        BorderPane bord = new BorderPane();
        this.setTop(bord);

        heroStatBar = new StatBar();
//        heroStatBar.getChildren().add(heroStatBar);
        bord.setLeft(heroStatBar);

        monsterStatBar = new StatBar();
        bord.setRight(monsterStatBar);
        monsterStatBar.flip();

        bord.setStyle("-fx-padding: 40px");

    }
}
