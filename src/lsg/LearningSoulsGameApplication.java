package lsg;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lsg.characters.Zombie;
import lsg.graphics.ImageFactory;
import lsg.graphics.panes.AnimationPane;
import lsg.graphics.panes.CreationPane;
import lsg.graphics.panes.HUDPane;
import lsg.graphics.panes.TitlePane;
import javafx.geometry.Pos;
import lsg.graphics.widgets.characters.renderers.HeroRenderer;
import lsg.graphics.widgets.characters.renderers.ZombieRenderer;
import lsg.characters.Hero;
import lsg.characters.Zombie;
import lsg.graphics.widgets.characters.statbars.StatBar;
import lsg.weapons.Sword;
import javafx.event.ActionEvent;


import static lsg.graphics.CSSFactory.getStyleSheet;

public class LearningSoulsGameApplication extends Application {
    private Scene scene;
    private AnchorPane root;
    private TitlePane gameTitle;
    private CreationPane creationPane;
    private String heroName;
    private AnimationPane animationPane;
    private Hero hero;
    private HeroRenderer heroRenderer;
    private Zombie zombie;
    private ZombieRenderer zombieRenderer;
    private HUDPane hudPane;

    public void start(Stage stage) throws Exception{
        stage.setTitle("Learning Souls Game");
        root = new AnchorPane();

        scene = new Scene(root, 1200, 800);

        stage.setScene(scene);
        stage.setResizable(false);

        buildUI(stage);
        addListeners();
        startGame();

        stage.show();
    }

    private void buildUI(Stage stage){
        scene.getStylesheets().add(getStyleSheet("LSG.css"));

        this.gameTitle = new TitlePane(this.scene, "Learning Souls Game");
        AnchorPane.setLeftAnchor(this.gameTitle,0.0);
        AnchorPane.setRightAnchor(this.gameTitle,0.0);
        AnchorPane.setTopAnchor(this.gameTitle,0.0);

        this.creationPane = new CreationPane();
        this.creationPane.setOpacity(0);

        AnchorPane.setLeftAnchor(this.creationPane,0.0);
        AnchorPane.setRightAnchor(this.creationPane,0.0);
        AnchorPane.setTopAnchor(this.creationPane,0.0);
        AnchorPane.setBottomAnchor(this.creationPane,0.0);

        this.hudPane = new HUDPane();
        AnchorPane.setLeftAnchor(hudPane,0.0);
        AnchorPane.setRightAnchor(hudPane,0.0);
        AnchorPane.setTopAnchor(hudPane,0.0);
        AnchorPane.setBottomAnchor(hudPane,0.0);


        root.getChildren().addAll(this.gameTitle, this.creationPane);
        animationPane = new AnimationPane(root);


    }
    private void startGame(){
        this.gameTitle.zoomIn((event -> {
                this.creationPane.fadeIn((event1 -> {
                    ImageFactory.preloadAll((()->{
                        System.out.println("Pre chargement termine");
                    }));
                }));
        }));
    }

    private void addListeners(){
        creationPane.getNameField().setOnAction((event -> {
            heroName = creationPane.getNameField().getText();
            System.out.println("Nom du hero: " + heroName);
            if(!heroName.isEmpty()) {
                root.getChildren().remove(creationPane);
                gameTitle.zoomOut((event1 -> play()));
            }
        }));
    }
    private void play(){
        root.getChildren().add(animationPane);
        root.getChildren().add(hudPane);

        createHero();

        createMonster((event -> {
            hudPane.getMessagePane().showMessage("FIGHT !!!");
            heroRenderer.attack((event1 -> {
                try {
                    zombie.getHitWith(hero.attack()) ;
                    hero.printStats();
                    zombie.printStats();
                } catch (Exception e) {
                    hudPane.getMessagePane().showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }));

        }));
}
    public void createHero(){
        hero = new Hero(heroName);
        hero.setWeapon(new Sword());


        heroRenderer = animationPane.createHeroRenderer();
        heroRenderer.goTo(animationPane.getPrefWidth()*0.5 - heroRenderer.getFitWidth()*0.65, null);

        // mettre le nom et la photo du hero en dessus du stat bar
        StatBar bar = hudPane.getHeroStatBar();
        bar.getAvatar().setImage(ImageFactory.getSprites(ImageFactory.SPRITES_ID.HERO_HEAD)[0]); // la photo de l'hero

        bar.getName().setText(hero.getName()); // le nom du hero
        hudPane.getHeroStatBar().getLifeBar().progressProperty().bind(hero.lifeRateProperty());
//
        bar.getLifeBar().progressProperty().bind(hero.lifeRateProperty());
        bar.getStamBar().progressProperty().bind(hero.staminaRateProperty());
    }
    public void createMonster(EventHandler<ActionEvent> finishedHandler){
        zombie = new Zombie();
        zombieRenderer = animationPane.createZombieRenderer();
        zombieRenderer.goTo(animationPane.getPrefWidth()*0.7 - heroRenderer.getFitWidth()*0.65, finishedHandler);

        // mettre le nom et la photo du zombie en dessus du stat bar
        StatBar bar = hudPane.getMonsterStatBar();
        bar.getAvatar().setImage(ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_HEAD)[0]);// recupere l'image
        bar.getAvatar().setRotate(30);// pour faire une rotation
        bar.getName().setText(zombie.getName()); // Pour mettre le nom du zombie

        bar.getLifeBar().progressProperty().bind(zombie.lifeRateProperty());
        bar.getStamBar().progressProperty().bind(zombie.staminaRateProperty());

    }
    public static void main(String[] args){
        launch(args);
    }

}
