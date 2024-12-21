package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShopController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Shop shop;
    private Player player;
    private ArrayList<Integer> shoppingCart = new ArrayList<Integer>();
    private ArrayList<Integer> shoppingCartUpgrade = new ArrayList<Integer>();
    @FXML private HBox shopPurchaseCards = new HBox();
    @FXML private ScrollPane shopPurchaseCardsScroll = new ScrollPane();
    @FXML private HBox shopUpgradeCards = new HBox();
    @FXML private ScrollPane shopUpgradeCardsScroll = new ScrollPane();
    @FXML private Label shopInfo;
    @FXML private Label shopInfo2;
    private File card1 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Spell1.png");
    private File card2 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Spell2.png");
    private File card3 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Spell3.png");
    private File card4 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Spell4.png");
    private File card5 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Spell5.png");
    private File card6 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card1.png");
    private File card7 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card2.png");
    private File card8 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card3.png");
    private File card9 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card4.png");
    private File card10 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card5.png");
    private File card11 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card6.png");
    private File card12 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card7.png");
    private File card13 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card8.png");
    private File card14 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card9.png");
    private File card15 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card10.png");
    private File card16 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card11.png");
    private File card17 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card12.png");
    private File card18 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\Card13.png");
    private File card19 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\CardChar1.png");
    private File card20 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\CardChar2.png");
    private File card21 = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\CardChar3.png");
    private String card1Pth = card1.toURI().toString();
    private String card2Pth = card2.toURI().toString();
    private String card3Pth = card3.toURI().toString();
    private String card4Pth = card4.toURI().toString();
    private String card5Pth = card5.toURI().toString();
    private String card6Pth = card6.toURI().toString();
    private String card7Pth = card7.toURI().toString();
    private String card8Pth = card8.toURI().toString();
    private String card9Pth = card9.toURI().toString();
    private String card10Pth = card10.toURI().toString();
    private String card11Pth = card11.toURI().toString();
    private String card12Pth = card12.toURI().toString();
    private String card13Pth = card13.toURI().toString();
    private String card14Pth = card14.toURI().toString();
    private String card15Pth = card15.toURI().toString();
    private String card16Pth = card16.toURI().toString();
    private String card17Pth = card17.toURI().toString();
    private String card18Pth = card18.toURI().toString();
    private String card19Pth = card19.toURI().toString();
    private String card20Pth = card20.toURI().toString();
    private Image card1Img = new Image(card1Pth);
    private Image card2Img = new Image(card2Pth);
    private Image card3Img = new Image(card3Pth);
    private Image card4Img = new Image(card4Pth);
    private Image card5Img = new Image(card5Pth);
    private Image card6Img = new Image(card6Pth);
    private Image card7Img = new Image(card7Pth);
    private Image card8Img = new Image(card8Pth);
    private Image card9Img = new Image(card9Pth);
    private Image card10Img = new Image(card10Pth);
    private Image card11Img = new Image(card11Pth);
    private Image card12Img = new Image(card12Pth);
    private Image card13Img = new Image(card13Pth);
    private Image card14Img = new Image(card14Pth);
    private Image card15Img = new Image(card15Pth);
    private Image card16Img = new Image(card16Pth);
    private Image card17Img = new Image(card17Pth);
    private Image card18Img = new Image(card18Pth);
    private Image card19Img = new Image(card19Pth);
    private Image card20Img = new Image(card20Pth);
    private Image[] cards = {card1Img, card2Img, card3Img, card4Img, card5Img,
            card6Img, card7Img, card8Img, card9Img, card10Img,
            card11Img, card12Img, card13Img, card14Img, card15Img,
            card16Img, card17Img, card18Img, card19Img, card20Img};
    public void setInformation() {
        createAndAddPurchaseCards(shop.getInventory().get(0));
        createAndAddUpgradeCards(shop.getInventory().get(1));
        shopInfo2.setText("coins:" + player.getCoin());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void createAndAddPurchaseCards(ArrayList<Card> purchaseCards) {
        shopPurchaseCards.getChildren().clear();
        for (int i = 0; i < purchaseCards.size(); i++) {
            ImageView card = new ImageView();
            card.setImage(card1Img);
            int index = i;
            card.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                selectPurchaseCards(purchaseCards, card, index);
            });
            shopPurchaseCards.getChildren().add(card);
        }
    }
    @FXML
    public void selectPurchaseCards(ArrayList<Card> purchaseCards, ImageView card, int Index) {
        Card selectedCard = purchaseCards.get(Index);
        if (!selectedCard.isSelected()) {
            card.setOpacity(0.5);
            shoppingCart.add(Index);
            shopInfo.setText("price:" + selectedCard.getCost());
            selectedCard.setSelected(true);
        } else if (selectedCard.isSelected()) {
            card.setOpacity(1.0);
            shoppingCart.remove((Integer) Index);
            shopInfo.setText("");
            selectedCard.setSelected(false);
        }
    }
    public void createAndAddUpgradeCards(ArrayList<Card> purchaseCards) {
        shopUpgradeCards.getChildren().clear();
        for (int i = 0; i < purchaseCards.size(); i++) {
            ImageView card = new ImageView();
            card.setImage(card1Img);
            int index = i;
            card.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                selectUpgradeCards(purchaseCards, card, index);
            });
            shopUpgradeCards.getChildren().add(card);
        }

    }
    @FXML
    public void selectUpgradeCards(ArrayList<Card> purchaseCards, ImageView card, int Index) {
        Card selectedCard = purchaseCards.get(Index);
        if (!selectedCard.isSelected()) {
            card.setOpacity(0.5);
            shoppingCartUpgrade.add(Index);
            shopInfo.setText("upgrade cost:" + selectedCard.getUpgradeCost());
            selectedCard.setSelected(true);
        } else if (selectedCard.isSelected()) {
            card.setOpacity(1.0);
            shoppingCartUpgrade.remove((Integer) Index);
            shopInfo.setText("");
            selectedCard.setSelected(false);
        }
    }
    @FXML
    public void buyCards() throws CloneNotSupportedException {
        for (int i = 0; i < shoppingCart.size(); i++) {
            int errorIndex = shop.buyCard((int)shoppingCart.get(i));
            if (errorIndex == -1) {
                shopInfo.setText("You already have this card!");
                break;
            } else if (errorIndex == -2) {
                shopInfo.setText("You don't have enough coins!");
                break;
            }
        }
    }
    @FXML
    public void upgradeCards() throws CloneNotSupportedException {
        for (int i = 0; i < shoppingCartUpgrade.size(); i++) {
            int errorIndex = shop.buyCard(shoppingCartUpgrade.get(i));
            if (errorIndex == -1) {
                shopInfo.setText("You don't have enough coins!");
                break;
            }
        }
    }
    public void goToMainMenu(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setInformation(player.getNickname(), Integer.toString(player.getLevel()), Integer.toString(player.getXp()),
                Integer.toString(player.getCoin()), Integer.toString(player.getHp()));

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
}
