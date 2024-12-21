package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectCharacterController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private MainMenu mainMenu;
    private Player player;
    private Player secondPlayer;
    private int hostCharIndex;
    private int oppCharIndex;
    private boolean selectCharacterPlayer;
    @FXML
    private Pane character1 = new Pane();
    @FXML private Pane character2 = new Pane();
    @FXML private Pane character3 = new Pane();
    @FXML private Label player1Nickname;
    @FXML private Label player1Level;
    @FXML private Label player1Exp;
    @FXML private Label player1Coins;
    @FXML private Label player1Health;
    @FXML private Label player2Nickname;
    @FXML private Label player2Level;
    @FXML private Label player2Exp;
    @FXML private Label player2Coins;
    @FXML private Label player2Health;
    public void initialize(){
        setupPane(character1, 1);
        setupPane(character2, 2);
        setupPane(character3, 3);
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setInformation() {
        player1Nickname.setText(player.getNickname());
        player1Level.setText("lvl : " + player.getLevel());
        player1Exp.setText("Xp : " + player.getXp());
        player1Coins.setText("coins : " + player.getCoin());
        player1Health.setText("Hlth : " + player.getHp());
        player2Nickname.setText(secondPlayer.getNickname());
        player2Level.setText("lvl : " + secondPlayer.getLevel());
        player2Exp.setText("Xp : " + secondPlayer.getXp());
        player2Coins.setText("coins : " + secondPlayer.getCoin());
        player2Health.setText("Hlth : " + secondPlayer.getHp());
    }

    private void setupPane(Pane pane, int index) {

        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            selectCharacter(pane, index);
        });
    }
    public void selectCharacter(Pane character, int charIndex) {
        if(!selectCharacterPlayer){
            character.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, null)));
            character.setBlendMode(BlendMode.MULTIPLY);
            Label label = new Label("player.getNickname()");
            character.getChildren().add(label);
            hostCharIndex = charIndex;
            selectCharacterPlayer = true;
        } else if (selectCharacterPlayer) {
            character.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, null)));
            character.setBlendMode(BlendMode.MULTIPLY);
            Label label = new Label("secondPlayer.getNickname()");
            character.getChildren().add(label);
            oppCharIndex = charIndex;
            selectCharacterPlayer = false;
        }
    }
    public void startGame(ActionEvent e) throws IOException {
        mainMenu.setChars(hostCharIndex, oppCharIndex);
        mainMenu.initGame();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        root = loader.load();
        GameController gameController = loader.getController();
        gameController.setGame(mainMenu.game);
        gameController.setPlayer(player);
        gameController.setSecondPlayer(secondPlayer);
        gameController.setStatus();

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
}
