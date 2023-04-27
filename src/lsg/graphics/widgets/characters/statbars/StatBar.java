package lsg.graphics.widgets.characters.statbars;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lsg.graphics.ImageFactory;
import lsg.graphics.widgets.texts.GameLabel;

public class StatBar extends BorderPane {
    private ImageView avatar;
    private GameLabel name;
    private ProgressBar lifeBar;
    private  ProgressBar stamBar;

    public ImageView getAvatar() {
        return avatar;
    }

    public GameLabel getName() {
        return name;
    }

    public ProgressBar getLifeBar() {
        return lifeBar;
    }

    public ProgressBar getStamBar() {
        return stamBar;
    }



    public StatBar(){
//        super();
        this.setPrefSize(350, 100);

//        this.setStyle("-fx-border-color: red");
        VBox centre = new VBox();
        this.setCenter(centre);

        this.avatar = new ImageView();
        this.avatar.setImage(ImageFactory.getSprites(ImageFactory.SPRITES_ID.HERO_HEAD)[0]);
        this.avatar.setPreserveRatio(true);
        this.avatar.setFitHeight(100);
        this.setLeft(this.avatar);

        this.name = new GameLabel();
        this.name.setStyle("-fx-font-size: 33px");

        this.lifeBar = new ProgressBar();
        this.lifeBar.setMaxWidth(Double.MAX_VALUE);
        this.lifeBar.setStyle("-fx-accent: red");

        this.stamBar = new ProgressBar();
        this.stamBar.setMaxWidth(Double.MAX_VALUE);
        this.stamBar.setStyle("-fx-accent: greenyellow");
        centre.getChildren().addAll(this.name, this.lifeBar, this.stamBar);

        // pour peaufiner
//        stamBar.maxWidthProperty().bind(center.widthProperty().multiply(0.8));
//        stamBar.setMinHeight(0);
//        stamBar.setPrefHeight(15);
    }

    public void flip(){ // methode pour creer un effet miroir
        this.setScaleX(-this.getScaleX());
        this.name.setScaleX(-this.name.getScaleX());
    }
}
