package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInGuestController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private MainMenu mainMenu;
    private Player player;
    private Player secondPlayer;
    @FXML private TextField loginOppUsername;
    @FXML private TextField loginOppPassword;
    @FXML private Label loginOppError;


    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    @FXML
    public void loginOpp() {
        String username = loginOppUsername.getText();
        String password = loginOppPassword.getText();
        int errorIndex = mainMenu.setOpponent(username, password);
        if (errorIndex == -1) {
            loginOppError.setText("*Username does not exist!");
        } else if (errorIndex == -2) {
            loginOppError.setText("*Username or password is incorrect!");
        } else {
            loginOppError.setText("*Second player logged in successfully!");
            secondPlayer = mainMenu.getOpponent();
        }
    }
    public void goToSelectCharacter(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("selectCharacter-menu.fxml"));
        root = loader.load();
        SelectCharacterController selectCharacterController = loader.getController();
        selectCharacterController.setPlayer(player);
        selectCharacterController.setSecondPlayer(secondPlayer);
        selectCharacterController.setMainMenu(mainMenu);
        selectCharacterController.setInformation();

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
    public void goToMainMenu(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
}
