package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;

public class GameHistoryController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Player player;
    @FXML private TableView<Info> gameHistoryTable = new TableView<>();
    @FXML private TableColumn<Info, String> opponentColumn = new TableColumn<>();
    @FXML private TableColumn<Info, Boolean> winColumn = new TableColumn<>();
    @FXML private TableColumn<Info, Integer> xpGainColumn = new TableColumn<>();
    @FXML private TableColumn<Info, String> dateColumn = new TableColumn<>();
    @FXML private TableColumn<Info, Integer> oppLevelColumn = new TableColumn<>();
    private ObservableList<Info> data;

    public void setPlayer(Player player) {
        this.player = player;
    }
    @FXML
    public void setInformation() {
        data = FXCollections.observableArrayList(player.getGameHistory());
        opponentColumn.setCellValueFactory(new PropertyValueFactory<>("op_name"));
        winColumn.setCellValueFactory(new PropertyValueFactory<>("win"));
        xpGainColumn.setCellValueFactory(new PropertyValueFactory<>("xp_gained"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("timeDate"));
        oppLevelColumn.setCellValueFactory(new PropertyValueFactory<>("op_l"));
        gameHistoryTable.getColumns().addAll(opponentColumn, winColumn, xpGainColumn, dateColumn, oppLevelColumn);
        gameHistoryTable.setItems(data);
    }
    public void sortTable() {
        FXCollections.sort(data, Comparator.comparing(Info::getOp_name));
    }
    public void goToMainMenu(ActionEvent e) throws IOException {
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
