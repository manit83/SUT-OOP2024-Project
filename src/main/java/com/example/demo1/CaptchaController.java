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

public class CaptchaController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Signinmenu signinmenu;
    private DataManage dataManage;
    private Captcha captcha;
    @FXML private Label captchaText;
    @FXML private Label captchaError;
    @FXML private TextField captchaAnswer;

    public void setCaptchaText(String captcha) {
        captchaText.setText(captcha);
    }

    public void setSigninmenu(Signinmenu signinmenu) {
        this.signinmenu = signinmenu;
    }

    public void setDataManage(DataManage dataManage) {
        this.dataManage = dataManage;
    }

    public void setCaptcha(Captcha captcha) {
        this.captcha = captcha;
    }

    public void confirmCaptcha(ActionEvent e) throws IOException {
    String answer = captchaAnswer.getText();
    int errorIndex = signinmenu.checkCaptcha(captcha, Integer.parseInt(answer));
    if (Objects.equals(answer, "")) {
        captchaError.setText("*Please answer the captcha!");
    } else if (errorIndex == -1) {
        captchaError.setText("*Captcha is incorrect!\nPlease answer the new captcha");
        captcha = signinmenu.generateCaptcha();
        captchaAnswer.setText("");
    } else {
        dataManage.writeFromUsersFile();
        root = FXMLLoader.load(getClass().getResource("login-menu.fxml"));



        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
}}
