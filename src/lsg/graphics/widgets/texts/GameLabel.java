package lsg.graphics.widgets.texts;

import javafx.scene.Node;
import javafx.scene.control.Label;
import lsg.graphics.CSSFactory;

public class GameLabel extends Label {

    public GameLabel(){
        super();
        this.addCss();
    }

    public GameLabel(String s){
        super(s);
        this.addCss();
    }

    public GameLabel(String s, Node node){
        super(s,node);
        this.addCss();
    }

    private void addCss(){
        this.getStylesheets().add(CSSFactory.getStyleSheet("LSGFont.css")) ;
        this.getStyleClass().addAll("game-font", "game-font-fx") ;

    }

}
