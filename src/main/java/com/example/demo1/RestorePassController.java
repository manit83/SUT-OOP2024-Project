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

public class RestorePassController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DataManage dataManage;
    private Signinmenu signinmenu;
    @FXML private TextField restorePassUsername;
    @FXML private Label restorePassError;

    public void setSigninmenu(Signinmenu signinmenu) {
        this.signinmenu = signinmenu;
    }

    public void setDataManage(DataManage dataManage) {
        this.dataManage = dataManage;
    }

    @FXML
    public void confirmUsernameRestorePass(ActionEvent e) throws IOException {
        String username = restorePassUsername.getText();
        int errorIndex = signinmenu.forgotPassUserExist(username);
        if (errorIndex == -1) {
            restorePassError.setText("*Username does not exist!");
        } else {

            Player player = dataManage.players.get(errorIndex);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("restorePass2-menu.fxml"));
            root = loader.load();
            RestorePass2Controller restorePass2Controller = loader.getController();
            restorePass2Controller.setSigninmenu(signinmenu);
            restorePass2Controller.setPlayer(player);
            restorePass2Controller.setInformation();


            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.show();
        }
    }
}
