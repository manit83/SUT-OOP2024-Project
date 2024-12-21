package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SecurityQuestionController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Signinmenu signinmenu;
    private DataManage dataManage;
    private Captcha captcha;

    @FXML private CheckBox questionOne;
    @FXML private CheckBox questionTwo;
    @FXML private CheckBox questionThree;
    @FXML private TextField setAnswer;
    @FXML private Label setQuestionError;


    public void setSigninmenu(Signinmenu signinmenu) {
        this.signinmenu = signinmenu;
    }

    public void setDataManage(DataManage dataManage) {
        this.dataManage = dataManage;
    }

    @FXML
    public void goToCaptcha(ActionEvent e) throws IOException {
        int questionNumber = checkQuestion();
        System.out.println(questionNumber);
        String answer = setAnswer.getText();
        if (questionNumber == -1) {
            setQuestionError.setText("*Please select a question first!");
        } else if (Objects.equals(answer, "")) {
            setQuestionError.setText("*Please answer the selected question!");
        } else {
            signinmenu.setRecoveryQuestion(answer, questionNumber);
            captcha = signinmenu.generateCaptcha();
            System.out.println(captcha.getQuestion());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("captcha-menu.fxml"));
            root = loader.load();
            CaptchaController captchaController = loader.getController();
            captchaController.setSigninmenu(signinmenu);
            captchaController.setCaptcha(captcha);
            captchaController.setCaptchaText(captcha.getQuestion());
            captchaController.setDataManage(dataManage);

            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.show();
        }
    }
    @FXML
    public void questionOneCheck() {
        questionTwo.setSelected(false);
        questionThree.setSelected(false);
        System.out.println("1");
    }
    @FXML
    public void questionTwoCheck() {
        questionOne.setSelected(false);
        questionThree.setSelected(false);
        System.out.println("2");
    }
    @FXML
    public void questionThreeCheck() {
        questionTwo.setSelected(false);
        questionOne.setSelected(false);
        System.out.println("3");
    }
    @FXML
    public int checkQuestion() {
        if (questionOne.isSelected()) { return 1; }
        else if (questionTwo.isSelected()) { return 2; }
        else if (questionThree.isSelected()) { return 3; }
        else { return -1; }
    }
}
