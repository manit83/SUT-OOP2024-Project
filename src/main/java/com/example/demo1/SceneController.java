package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.animation.PathTransition;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class SceneController {
    private DataManage dataManage;
    private Signinmenu signinmenu;
    private Profilemenu profilemenu;
    private Shop shop;
    private MainMenu mainMenu;
    private Game game;
    private ArrayList<Integer> shoppingCart = new ArrayList<Integer>();
    private ArrayList<Integer> shoppingCartUpgrade = new ArrayList<Integer>();
    private Player player;
    private Player secondPlayer;
    private int userIndex;
    private int hostCharIndex;
    private int oppCharIndex;
    private int hostTurn = 4;
    private int oppTurn = 4;
    private boolean selectCharacterPlayer;
    private double mouseAnchorY;
    private double mouseAnchorX;
    private double initialX;
    private double initialY;
    private int selectBlock;
    private boolean whoseTurn;
    private Captcha captcha;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private TextField logInUsername;
    @FXML private PasswordField logInPassword;
    @FXML private Label logInError;
    @FXML private TextField signInUsername;
    @FXML private TextField signInNickname;
    @FXML private TextField signInPassword;
    @FXML private TextField signInPasswordConfirm;
    @FXML private TextField signInEmail;
    @FXML private Label signInError;
    @FXML private CheckBox questionOne;
    @FXML private CheckBox questionTwo;
    @FXML private CheckBox questionThree;
    @FXML private TextField setAnswer;
    @FXML private Label setQuestionError;
    @FXML private Label captchaText;
    @FXML private Label captchaError;
    @FXML private TextField captchaAnswer;
    @FXML private TextField restorePassUsername;
    @FXML private TextField restorePassNewPass;
    @FXML private TextField restorePassNewPassConfirm;
    @FXML private TextField restorePassAnswer;
    @FXML private Label restorePassError;
    @FXML private Label restorePassError2;
    @FXML private Label restorePassQuestion;
    @FXML private TextField editProfileUsername;
    @FXML private TextField editProfileNickname;
    @FXML private TextField editProfileNewPassword;
    @FXML private TextField editProfileOldPassword;
    @FXML private TextField editProfileEmail;
    @FXML private Label editProfileError;
    @FXML private HBox shopPurchaseCards = new HBox();
    @FXML private ScrollPane shopPurchaseCardsScroll = new ScrollPane();
    @FXML private HBox shopUpgradeCards = new HBox();
    @FXML private ScrollPane shopUpgradeCardsScroll = new ScrollPane();
    @FXML private Label shopInfo;
    @FXML private Label shopInfo2;
    @FXML private TextField loginOppUsername;
    @FXML private TextField loginOppPassword;
    @FXML private Label loginOppError;
    @FXML private Pane character1 = new Pane();
    @FXML private Pane character2 = new Pane();
    @FXML private Pane character3 = new Pane();
    @FXML private Label character1Label;
    @FXML private Label character2Label;
    @FXML private Label character3Label;
    @FXML private Label player1Nickname;
    @FXML private Label player1Level;
    @FXML private Label player1Exp;
    @FXML private Label player1Coins;
    @FXML private Label player1Health;
    @FXML private Label player2Nickname;
    @FXML private Label player2Level;
    @FXML private Label player2Exp;
    @FXML private Label player2Coins;
    @FXML private Label player2Health;
    @FXML private ImageView hostCard1 = new ImageView();
    @FXML private ImageView hostCard2 = new ImageView();
    @FXML private ImageView hostCard3 = new ImageView();
    @FXML private ImageView hostCard4 = new ImageView();
    @FXML private ImageView hostCard5 = new ImageView();
    @FXML private ImageView oppCard1 = new ImageView();
    @FXML private ImageView oppCard2 = new ImageView();
    @FXML private ImageView oppCard3 = new ImageView();
    @FXML private ImageView oppCard4 = new ImageView();
    @FXML private ImageView oppCard5 = new ImageView();
    @FXML private HBox hostTimeline;
    @FXML private HBox oppTimeline;
    @FXML private ImageView block11 = new ImageView();
    @FXML private ImageView block12 = new ImageView();
    @FXML private ImageView block13 = new ImageView();
    @FXML private ImageView block14 = new ImageView();
    @FXML private ImageView block15 = new ImageView();
    @FXML private ImageView block16 = new ImageView();
    @FXML private ImageView block17 = new ImageView();
    @FXML private ImageView block18 = new ImageView();
    @FXML private ImageView block19 = new ImageView();
    @FXML private ImageView block110 = new ImageView();
    @FXML private ImageView block111 = new ImageView();
    @FXML private ImageView block112 = new ImageView();
    @FXML private ImageView block113 = new ImageView();
    @FXML private ImageView block114 = new ImageView();
    @FXML private ImageView block115 = new ImageView();
    @FXML private ImageView block116 = new ImageView();
    @FXML private ImageView block117 = new ImageView();
    @FXML private ImageView block118 = new ImageView();
    @FXML private ImageView block119 = new ImageView();
    @FXML private ImageView block120 = new ImageView();
    @FXML private ImageView block21 = new ImageView();
    @FXML private ImageView block22 = new ImageView();
    @FXML private ImageView block23 = new ImageView();
    @FXML private ImageView block24 = new ImageView();
    @FXML private ImageView block25 = new ImageView();
    @FXML private ImageView block26 = new ImageView();
    @FXML private ImageView block27 = new ImageView();
    @FXML private ImageView block28 = new ImageView();
    @FXML private ImageView block29 = new ImageView();
    @FXML private ImageView block210 = new ImageView();
    @FXML private ImageView block211 = new ImageView();
    @FXML private ImageView block212 = new ImageView();
    @FXML private ImageView block213 = new ImageView();
    @FXML private ImageView block214 = new ImageView();
    @FXML private ImageView block215 = new ImageView();
    @FXML private ImageView block216 = new ImageView();
    @FXML private ImageView block217 = new ImageView();
    @FXML private ImageView block218 = new ImageView();
    @FXML private ImageView block219 = new ImageView();
    @FXML private ImageView block220 = new ImageView();
    @FXML private Label label11;
    @FXML private Label label12;
    @FXML private Label label13;
    @FXML private Label label14;
    @FXML private Label label15;
    @FXML private Label label16;
    @FXML private Label label17;
    @FXML private Label label18;
    @FXML private Label label19;
    @FXML private Label label110;
    @FXML private Label label111;
    @FXML private Label label112;
    @FXML private Label label113;
    @FXML private Label label114;
    @FXML private Label label115;
    @FXML private Label label116;
    @FXML private Label label117;
    @FXML private Label label118;
    @FXML private Label label119;
    @FXML private Label label120;
    @FXML private Label label21;
    @FXML private Label label22;
    @FXML private Label label23;
    @FXML private Label label24;
    @FXML private Label label25;
    @FXML private Label label26;
    @FXML private Label label27;
    @FXML private Label label28;
    @FXML private Label label29;
    @FXML private Label label210;
    @FXML private Label label211;
    @FXML private Label label212;
    @FXML private Label label213;
    @FXML private Label label214;
    @FXML private Label label215;
    @FXML private Label label216;
    @FXML private Label label217;
    @FXML private Label label218;
    @FXML private Label label219;
    @FXML private Label label220;
    private ImageView[] track1Blocks = {block11, block12, block13, block14, block15,
            block16, block17, block18, block19, block110, block111, block112, block113,
            block114, block115, block116, block117, block118, block119, block120};
    private Label[] track1Label = {label11, label12, label13, label14, label15,
            label16, label17, label18, label19, label110, label111, label112, label113,
            label114, label115, label116, label117, label118, label119, label120};
    private ImageView[] track2Blocks = {block21, block22, block23, block24, block25,
            block26, block27, block28, block29, block210, block211, block212, block213,
            block214, block215, block216, block217, block218, block219, block220};
    private Label[] track2Label = {label21, label22, label23, label24, label25,
            label26, label27, label28, label29, label210, label211, label212, label213,
            label214, label215, label216, label217, label218, label219, label220};
    private ImageView[] hostDeck = {hostCard1, hostCard2, hostCard3, hostCard4, hostCard5};
    private ImageView[] oppDeck = {oppCard1, oppCard2, oppCard3, oppCard4, oppCard5};
    @FXML private Rectangle tracker;
    @FXML private Line timeline;
    private PathTransition timelineAnimation;
    @FXML private Label gameHostNickname;
    @FXML private Label gameOppNickname;
    @FXML private Label gameHostHealth;
    @FXML private Label gameOppHealth;
    @FXML private Label gameHostTurn;
    @FXML private Label gameOppTurn;
    @FXML private Label gameHostDamage;
    @FXML private Label gameOppDamage;
    @FXML private Label winOrLose;
    @FXML private Label winOrLoseXp;
    @FXML private Label winOrLoseLvl;
    @FXML private Button winOrLoseButton;
    @FXML private Label gameInfo;
    @FXML private ImageView winOrLoseWindow;
    @FXML private TableView<Info> gameHistoryTable = new TableView<>();
    @FXML private TableColumn<Info, String> opponentColumn = new TableColumn<>();
    @FXML private TableColumn<Info, Boolean> winColumn = new TableColumn<>();
    @FXML private TableColumn<Info, Integer> xpGainColumn = new TableColumn<>();
    @FXML private TableColumn<Info, String> dateColumn = new TableColumn<>();
    @FXML private TableColumn<Info, Integer> oppLevelColumn = new TableColumn<>();
    private ObservableList<Info> data;

//    private Image blockDisabled = new Image("");
//    private Image blockFilled = new Image("");
//    private Image blockDestroyed = new Image("");
//    private Image card1 = new Image("");
//    private Image card2 = new Image("");
//    private Image card3 = new Image("");
//    private Image card4 = new Image("");
//    private Image card5 = new Image("");
//    private Image card6 = new Image("");
//    private Image card7 = new Image("");
//    private Image card8 = new Image("");
//    private Image card9 = new Image("");
//    private Image card10 = new Image("");
//    private Image card11 = new Image("");
//    private Image card12 = new Image("");
//    private Image card13 = new Image("");
//    private Image card14 = new Image("");
//    private Image card15 = new Image("");
//    private Image card16 = new Image("");
//    private Image card17 = new Image("");
//    private Image card18 = new Image("");
//    private Image card19 = new Image("");
//    private Image card20 = new Image("");
//    private Image[] cards = {card1, card2, card3, card4, card5,
//            card6, card7, card8, card9, card10,
//            card11, card12, card13, card14, card15,
//            card16, card17, card18, card19, card20};





    @FXML
    public void initialize(){
//        dataManage = new DataManage();
//        signinmenu = new Signinmenu(dataManage);
        setupPane(character1, 1);
        setupPane(character2, 2);
        setupPane(character3, 3);
        makeDraggable(hostCard1, 1);
        makeDraggable(hostCard2, 2);
        makeDraggable(hostCard3, 3);
        makeDraggable(hostCard4, 4);
        makeDraggable(hostCard5, 5);
        makeDraggable(oppCard1, 1);
        makeDraggable(oppCard2, 2);
        makeDraggable(oppCard3, 3);
        makeDraggable(oppCard4, 4);
        makeDraggable(oppCard5, 5);
        for (int i = 0; i < 20; i++) {
            returnBlockIndex(track1Blocks[i], i+1);
            returnBlockIndex(track2Blocks[i], i+1);
        }
        timelineAnimation = new PathTransition();
    }
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
            userIndex = errorIndex;
            player = dataManage.players.get(userIndex);
            profilemenu = new Profilemenu(player , dataManage);
            mainMenu = new MainMenu(player, dataManage);
            shop = mainMenu.shop;
            root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
//          stage.setFullScreen(true);
            stage.show();
        }
    }
    public void createAcc(ActionEvent e) throws IOException {
        System.out.println("signin");
        dataManage = new DataManage();
        signinmenu = new Signinmenu(dataManage);
        root = FXMLLoader.load(getClass().getResource("signin-menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
    public void cancelCreateAcc(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login-menu.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("securityQuestion-menu.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.show();
        }
    }
    @FXML
    public void randomPass() {
        signInPassword.setText(signinmenu.generateRandomPass());
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
            captchaText.setText(captcha.getQuestion());
            root = FXMLLoader.load(getClass().getResource("captcha-menu.fxml"));
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
        else if (questionOne.isSelected()) { return 2; }
        else if (questionOne.isSelected()) { return 3; }
        else { return -1; }
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
            root = FXMLLoader.load(getClass().getResource("login-menu.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    //RESTORE PASS MENU
    public void restorePass(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("restorePass-menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void confirmUsernameRestorePass(ActionEvent e) throws IOException {
        String username = restorePassUsername.getText();
        int errorIndex = signinmenu.forgotPassUserExist(username);
        if (errorIndex == -1) {
            restorePassError.setText("*Username does not exist!");
        } else {
            userIndex = errorIndex;
            Player player = dataManage.players.get(userIndex);
            int questionIndex = player.getRecoverNum();
            if (questionIndex == 1) {
                restorePassQuestion.setText("What is your father’s name?");
            } else if (questionIndex == 2) {
                restorePassQuestion.setText("What is your favourite color?");
            } else if (questionIndex == 3) {
                restorePassQuestion.setText("What was the name of your first pet?");
            }
            root = FXMLLoader.load(getClass().getResource("restorePass2-menu.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    public void confirmNewPasswordRestorePass(ActionEvent e) throws IOException {
        String newPassword = restorePassNewPass.getText();
        String newPasswordConfirm = restorePassNewPassConfirm.getText();
        String answer = restorePassAnswer.getText();
        int recoverAnswer = signinmenu.recoverPass(userIndex, answer);
        int recoverSetNewPass = signinmenu.recoverSetPass(userIndex, newPassword);
        if (recoverAnswer == -1) {
            restorePassError2.setText("*Answer to the recovery question\nis incorrect!");
        } else if (recoverSetNewPass == -1) {
            restorePassError2.setText("*Password is invalid!");
        } else if (!Objects.equals(newPassword, newPasswordConfirm)) {
            restorePassError2.setText("*Password Confirmation is incorrect!");
        } else {
            root = FXMLLoader.load(getClass().getResource("login-menu.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void goToMainMenu(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    //EDIT PROFILE MENU
    @FXML
    public void editProfile(ActionEvent e) throws IOException {
        editProfileUsername.setText(player.getUserName());
        editProfileEmail.setText(player.getEmail());
        editProfileNickname.setText(player.getNickname());
        root = FXMLLoader.load(getClass().getResource("login-menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    //SHOP MENU
    @FXML
    public void shop(ActionEvent e) throws IOException {
        createAndAddPurchaseCards(shop.getInventory().get(0));
        createAndAddUpgradeCards(shop.getInventory().get(1));
        shopInfo2.setText("coins:" + player.getCoin());
        root = FXMLLoader.load(getClass().getResource("shop-menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    public void createAndAddPurchaseCards(ArrayList<Card> purchaseCards) {
        shopPurchaseCards.getChildren().clear();
        for (int i = 0; i < purchaseCards.size(); i++) {
            ImageView card = new ImageView();
            Image image = new Image(purchaseCards.get(i).getName()+"png");
            card.setImage(image);
            int index = i;
            card.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                selectPurchaseCards(purchaseCards, card, index);
            });
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
            shoppingCart.remove(Index);
            shopInfo.setText("");
            selectedCard.setSelected(false);
        }
    }
    public void createAndAddUpgradeCards(ArrayList<Card> purchaseCards) {
        shopUpgradeCards.getChildren().clear();
        for (int i = 0; i < purchaseCards.size(); i++) {
            ImageView card = new ImageView();
            Image image = new Image(purchaseCards.get(i).getName() + ".png");
            card.setImage(image);
            int index = i;
            card.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                selectUpgradeCards(purchaseCards, card, index);
            });
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
            shoppingCartUpgrade.remove(Index);
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
    public void backFromShop(ActionEvent e) throws IOException {
        for (Card card : shop.getInventory().get(0)) {
            card.setSelected(false);
        }
        for (Card card : shop.getInventory().get(1)) {
            card.setSelected(false);
        }
        root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void loginOpp(ActionEvent e) {
        String username = loginOppUsername.getText();
        String password = loginOppPassword.getText();
        int errorIndex = mainMenu.setOpponent(username, password);
        if (errorIndex == -1) {
            loginOppError.setText("*Username does not exist!");
        } else if (errorIndex == -2) {
            loginOppError.setText("*Username or password is incorrect!");
        } else {
            loginOppError.setText("*Second player logged in successfully!");
            secondPlayer = mainMenu.getOpponent();
        }
    }
    @FXML
    public void goToSelectCharacter(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("selectCharacter-menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private void setupPane(Pane pane, int index) {

        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            selectCharacter(pane, index);
        });
    }
    public void selectCharacter(Pane character, int charIndex) {
        if(!selectCharacterPlayer){
            character.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, null)));
            character.setBlendMode(BlendMode.MULTIPLY);
            Label label = new Label("player.getNickname()");
            character.getChildren().add(label);
            hostCharIndex = charIndex;
            selectCharacterPlayer = true;
        } else if (selectCharacterPlayer) {
            character.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, null)));
            character.setBlendMode(BlendMode.MULTIPLY);
            Label label = new Label("secondPlayer.getNickname()");
            character.getChildren().add(label);
            oppCharIndex = charIndex;
            selectCharacterPlayer = false;
        }
    }
    public void makeDraggable(Node node, int index) {
        node.setOnMousePressed(mouseEvent -> {
            initialX = node.getLayoutX();
            initialY = node.getLayoutY();
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
        });

        node.setOnMouseReleased(mouseEvent -> {
            node.setLayoutY(initialY);
            node.setLayoutX(initialX);
            try {
                int placeCardError = game.placeCard(selectBlock, index);
                if (placeCardError == -1) {
                    System.out.println("you cant put the card here");
                } else if (placeCardError == 5) {
                    System.out.println("spell 5");
                    //updateTrack();
                    //updateDeck();
                    updateStatus();
                } else if (placeCardError == 8) {
                    System.out.println("spell 8");
                    //updateTrack();
                    //updateDeck();
                    updateStatus();
                } else if (placeCardError == 1) {
                    if (game.getTurn() < 8) {
                        changeTurn();
                        //updateTrack();
                        //updateDeck();
                        updateStatus();
                    } else {
                        int endOfOperation = game.endOfRoundOperator();
                        int endBlock = (endOfOperation > 100 ? endOfOperation-100:endOfOperation);
                        boolean winner = (endOfOperation <= 100);
                        tracker.setWidth((endBlock)*50);
                        timelineAnimation.setPath(timeline);
                        timelineAnimation.setNode(tracker);
                        timelineAnimation.setDuration(Duration.seconds(endBlock));
                        timelineAnimation.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue.greaterThanOrEqualTo(timelineAnimation.getDuration())) {
                                if(winner) {
                                    winOrLose.setText("Victory!");
                                    winOrLoseLvl.setText("Level: " + player.getLevel());
                                    winOrLoseXp.setText("Xp: " + player.getXp());
                                    winOrLoseWindow.setOpacity(1);
                                    winOrLoseButton.setMouseTransparent(false);


                                } else {
                                    winOrLose.setText("Game Over!");
                                    winOrLoseLvl.setText("Level: " + player.getLevel());
                                    winOrLoseXp.setText("Xp: " + player.getXp());
                                    winOrLoseWindow.setOpacity(1);
                                    winOrLoseButton.setMouseTransparent(false);
                                }
                            }
                        });
                        timelineAnimation.play();
                    }

                } else if (placeCardError == -1) {
                    //updateTrack();
                    //updateDeck();
                    updateStatus();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void updateStatus() {
        gameHostTurn.setText("turn : " + hostTurn);
        gameOppTurn.setText("turn : " + oppTurn);
        gameHostDamage.setText("Damage : " + game.getHost_total_damage());
        gameOppDamage.setText("Damage : " + game.getGuest_total_damage());
    }
    public void returnBlockIndex(Node node, int index) {
        node.setOnMouseDragReleased(mouseDragEvent -> {
            selectBlock = index;
        });
    }
    public void startGame(ActionEvent e) throws IOException {
        mainMenu.setChars(hostCharIndex, oppCharIndex);
        mainMenu.initGame();
        game = mainMenu.game;
        gameHostTurn.setText("turn : " + hostTurn);
        gameOppTurn.setText("turn : " + oppTurn);
        gameHostHealth.setText(Integer.toString(player.hp));
        gameOppHealth.setText(Integer.toString(secondPlayer.hp));
        gameHostNickname.setText(player.getNickname());
        gameOppNickname.setText(secondPlayer.getNickname());
        gameHostDamage.setText("Damage : " + game.getHost_total_damage());
        gameOppDamage.setText("Damage : " + game.getGuest_total_damage());
        //updateTrack();
        //updateDeck():
        root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//    public void updateTrack() {
//        for (int i = 0; i < 20; i++) {
//            if (game.getTrackPad()[0][i].getDisabled()) {
//                track1Blocks[i].setImage(blockDisabled);
//            } else if (game.getTrackPad()[0][i].getFilled()) {
//                track1Blocks[i].setImage(blockFilled);
//                /track1Label[i].setText(game.getTrackPad()[0][i].getDamage)
//            }
//        }
//        for (int i = 0; i < 20; i++) {
//            if (game.getTrackPad()[1][i].getDisabled()) {
//                track1Blocks[i].setImage(blockDisabled);
//            } else if (game.getTrackPad()[1][i].getFilled()) {
//                track1Blocks[i].setImage(blockFilled);
//                track1Label[i].setText(game.getTrackPad()[0][i].getDamage)
//            }
//        }
//    }
//    public void updateDeck() {
//        for (int i = 0; i < 5; i++) {
//            hostDeck[i].setImage(cards[dataManage.cardIndex(player.getCardinDeck().get(i))]);
//        }
//        for (int i = 0; i < 5; i++) {
//            oppDeck[i].setImage(cards[dataManage.cardIndex(secondPlayer.getCardinDeck().get(i))]);
//        }
//    }
    public void changeTurn() {
        if (!whoseTurn) {
            whoseTurn = true;
            hostTurn--;
            hostTimeline.setMouseTransparent(true);
            for (ImageView card : hostDeck) {
                card.setMouseTransparent(true);
            }
            oppTimeline.setMouseTransparent(false);
            for (ImageView card : oppDeck) {
                card.setMouseTransparent(false);
            }
        } else {
            whoseTurn = false;
            oppTurn--;
            hostTimeline.setMouseTransparent(false);
            for (ImageView card : hostDeck) {
                card.setMouseTransparent(false);
            }
            oppTimeline.setMouseTransparent(true);
            for (ImageView card : oppDeck) {
                card.setMouseTransparent(true);
            }
        }
    }
    public void goToGameHistory(ActionEvent e) throws IOException {
        data = FXCollections.observableArrayList(player.getGameHistory());
        opponentColumn.setCellValueFactory(new PropertyValueFactory<>("op_name"));
        winColumn.setCellValueFactory(new PropertyValueFactory<>("win"));
        xpGainColumn.setCellValueFactory(new PropertyValueFactory<>("xp_gained"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("timeDate"));
        oppLevelColumn.setCellValueFactory(new PropertyValueFactory<>("op_l"));
        gameHistoryTable.getColumns().addAll(opponentColumn, winColumn, xpGainColumn, dateColumn, oppLevelColumn);
        gameHistoryTable.setItems(data);
        root = FXMLLoader.load(getClass().getResource("gameHistory-menu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void sortTable() {
        FXCollections.sort(data, Comparator.comparing(Info::getOp_name));
    }

}