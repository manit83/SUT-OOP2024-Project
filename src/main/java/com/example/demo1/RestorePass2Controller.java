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
import java.util.Objects;

public class RestorePass2Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Signinmenu signinmenu;
    private DataManage dataManage = new DataManage();
    private Player player;

    @FXML private TextField restorePassNewPass;
    @FXML private TextField restorePassNewPassConfirm;
    @FXML private TextField restorePassAnswer;
    @FXML private Label restorePassError2;
    @FXML private Label restorePassQuestion;

    public void setSigninmenu(Signinmenu signinmenu) {
        this.signinmenu = signinmenu;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setInformation() {
        int questionIndex = player.getRecoverNum();
        if (questionIndex == 1) {
            restorePassQuestion.setText("What is your father’s name?");
        } else if (questionIndex == 2) {
            restorePassQuestion.setText("What is your favourite color?");
        } else if (questionIndex == 3) {
            restorePassQuestion.setText("What was the name of your first pet?");
        }
    }
    @FXML
    public void confirmNewPasswordRestorePass(ActionEvent e) throws IOException {
        String newPassword = restorePassNewPass.getText();
        String newPasswordConfirm = restorePassNewPassConfirm.getText();
        String answer = restorePassAnswer.getText();
        int userIndex = signinmenu.userExists(player.getUsername());
        int recoverAnswer = signinmenu.recoverPass(userIndex, answer);
        int recoverSetNewPass = signinmenu.recoverSetPass(userIndex, newPassword);
        if (recoverAnswer == -1) {
            restorePassError2.setText("*Answer to the recovery question\nis incorrect!");
        } else if (recoverSetNewPass == -1) {
            restorePassError2.setText("*Password is invalid!");
        } else if (!Objects.equals(newPassword, newPasswordConfirm)) {
            restorePassError2.setText("*Password Confirmation is incorrect!");
        } else {
            dataManage.writeFromUsersFile();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            root = loader.load();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.setPlayer(player);
            mainMenuController.setShop(new Shop(player, new DataManage()));
            mainMenuController.setInformation(player.getNickname(), Integer.toString(player.level), Integer.toString(player.getXp()),
                    Integer.toString(player.coin), Integer.toString(player.getHp()));

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.show();
        }
    }
}
