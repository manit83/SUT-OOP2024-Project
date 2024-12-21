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

public class EditProfileController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Profilemenu profilemenu;
    private Player player;
    @FXML private TextField editProfileUsername;
    @FXML private TextField editProfileNickname;
    @FXML private TextField editProfileNewPassword;
    @FXML private TextField editProfileOldPassword;
    @FXML private TextField editProfileEmail;
    @FXML private Label editProfileError;

    public void setProfilemenu(Profilemenu profilemenu) {
        this.profilemenu = profilemenu;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setInformation() {
        editProfileUsername.setText(player.getUserName());
        editProfileEmail.setText(player.getEmail());
        editProfileNickname.setText(player.getNickname());
    }

    @FXML
    public void editProfileApplyChanges(ActionEvent e) throws IOException {
        String email = editProfileEmail.getText();
        String username = editProfileUsername.getText();
        String nickname = editProfileNickname.getText();
        String oldPassword = editProfileOldPassword.getText();
        String newPassword = editProfileNewPassword.getText();
        int editProfileErr = profilemenu.editProfile(username, oldPassword, newPassword, nickname, email);
        if (editProfileErr == -1) {
            editProfileError.setText("*Username is invalid!");
        } else if (editProfileErr == -2) {
            editProfileError.setText("*This username already exists!");
        } else if (editProfileErr == -3) {
            editProfileError.setText("*The password is incorrect!");
        } else if (editProfileErr == -4) {
            editProfileError.setText("*Please choose a new password!");
        } else if (editProfileErr == -5) {
            editProfileError.setText("*password is invalid!");
        } else if (editProfileErr == -6) {
            editProfileError.setText("*Email is invalid!");
        } else {
            root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.show();
        }
    }
}
