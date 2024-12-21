package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Profilemenu profilemenu;
    private Shop shop;
    private MainMenu mainMenu;
    private Player player;
    private Signinmenu signinmenu;
    private DataManage dataManage;
    @FXML private Label mainMenuNickname;
    @FXML private Label mainMenuLevel;
    @FXML private Label mainMenuXp;
    @FXML private Label mainMenuCoins;
    @FXML private Label mainMenuHealth;

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setProfilemenu(Profilemenu profilemenu) {
        this.profilemenu = profilemenu;
    }

    public void setSigninmenu(Signinmenu signinmenu) {
        this.signinmenu = signinmenu;
    }

    public void setInformation(String nickname, String level, String xp, String coins, String health) {
        mainMenuNickname.setText(nickname);
        mainMenuLevel.setText("lvl : " + level);
        mainMenuXp.setText("Xp : " + xp);
        mainMenuCoins.setText("Coins : " + coins);
        mainMenuHealth.setText("Health : " + health);
    }
    public void editProfile(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editProfile-menu.fxml"));
        root = loader.load();
        EditProfileController editProfileController = loader.getController();
        editProfileController.setPlayer(player);
        editProfileController.setProfilemenu(profilemenu);
        editProfileController.setInformation();

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
    public void shop(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shop-menu.fxml"));
        root = loader.load();
        ShopController shopController = loader.getController();
        shopController.setShop(shop);
        shopController.setPlayer(player);
        shopController.setInformation();

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
    public void goToLogInOpp(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginSecondPlayer-menu.fxml"));
        root = loader.load();
        LogInGuestController logInGuestController = loader.getController();
        logInGuestController.setMainMenu(mainMenu);
        logInGuestController.setPlayer(player);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
    public void goToGameHistory(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameHistory-menu.fxml"));
        root = loader.load();
        GameHistoryController gameHistoryController = loader.getController();
        gameHistoryController.setPlayer(player);
        gameHistoryController.setInformation();

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
    public void quitAndLogout(ActionEvent e) throws IOException {
        dataManage.writeFromUsersFile();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }


}
