package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogInController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DataManage dataManage = new DataManage();
    private Signinmenu signinmenu = new Signinmenu(dataManage);
    private Player player;
    private Profilemenu profilemenu;
    private MainMenu mainMenu;
    private Shop shop;

    @FXML private TextField logInUsername;
    @FXML private PasswordField logInPassword;
    @FXML private Label logInError;
    @FXML
    public void login(ActionEvent e) throws IOException {
        System.out.println("login");
        String username = logInUsername.getText();
        String password = logInPassword.getText();
        int errorIndex = signinmenu.login(username, password);
        if (Objects.equals(username, "") || Objects.equals(password, "")) {
            logInError.setText("*Please don't leave the fields \n empty!");
        } else if (errorIndex == -1) {
            logInError.setText("*Username does not exist!");
        } else if (errorIndex == -2) {
            logInError.setText("*Username or password is incorrect!");
        } else {
            player = signinmenu.players1.get(errorIndex);
            profilemenu = new Profilemenu(player , dataManage);
            mainMenu = new MainMenu(player, dataManage);
            shop = mainMenu.shop;
            dataManage.writeFromUsersFile();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            root = loader.load();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.setSigninmenu(signinmenu);
            mainMenuController.setPlayer(player);
            mainMenuController.setProfilemenu(profilemenu);
            mainMenuController.setShop(shop);
            mainMenuController.setMainMenu(mainMenu);
            mainMenuController.setProfilemenu(profilemenu);
            mainMenuController.setInformation(player.getNickname(), Integer.toString(player.getLevel()), Integer.toString(player.getXp())
            , Integer.toString(player.getCoin()), Integer.toString(player.getHp()));

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
    }
    public void createAcc(ActionEvent e) throws IOException {
        System.out.println("signin");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("signin-menu.fxml"));
        root = loader.load();
        SignInController signInController = loader.getController();
        signInController.setSigninmenu(signinmenu);
        signInController.setDataManage(dataManage);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
    public void restorePass(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("restorePass-menu.fxml"));
        root = loader.load();
        RestorePassController restorePassController = loader.getController();
        restorePassController.setDataManage(dataManage);
        restorePassController.setSigninmenu(signinmenu);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
}
