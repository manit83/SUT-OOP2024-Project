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

public class SignInController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Signinmenu signinmenu;
    private DataManage dataManage;
    @FXML private TextField signInUsername;
    @FXML private TextField signInNickname;
    @FXML private TextField signInPassword;
    @FXML private TextField signInPasswordConfirm;
    @FXML private TextField signInEmail;
    @FXML private Label signInError;
    public void cancelCreateAcc(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
    @FXML
    public void goToSecurityQ(ActionEvent e) throws IOException {
        String username = signInUsername.getText();
        String nickname = signInNickname.getText();
        String password = signInPassword.getText();
        String passwordConfirm = signInPasswordConfirm.getText();
        String email = signInEmail.getText();
        int errorIndex = signinmenu.signUp(username, password, passwordConfirm, email, nickname);
        if (errorIndex == -1) {
            signInError.setText("*Please don't leave the fields \n empty!");
        } else if (errorIndex == -2) {
            signInError.setText("*Username is invalid!");
        } else if (errorIndex == -3) {
            signInError.setText("*This username already exists!");
        } else if (errorIndex == -4) {
            signInError.setText("*Password Confirmation is incorrect!");
        } else if (errorIndex == -5) {
            signInError.setText("*Password should at least contain \n 8 characters");
        } else if (errorIndex == -6) {
            signInError.setText("*Password should at least contain both \nletters and digits");
        } else if (errorIndex == -7) {
            signInError.setText("*Email is invalid!");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("securityQuestion-menu.fxml"));
            root = loader.load();
            SecurityQuestionController securityQuestionController = loader.getController();
            securityQuestionController.setSigninmenu(signinmenu);
            securityQuestionController.setDataManage(dataManage);
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.show();
        }
    }
    public void randomPass() {
        signInPassword.setText(signinmenu.generateRandomPass());
    }


    public void setSigninmenu(Signinmenu signinmenu) {
        this.signinmenu = signinmenu;
    }

    public void setDataManage(DataManage dataManage) {
        this.dataManage = dataManage;
    }
}
