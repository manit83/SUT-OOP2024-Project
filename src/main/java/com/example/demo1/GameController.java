package com.example.demo1;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class GameController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Player player;
    private Player secondPlayer;
    private Game game;
    private DataManage dataManage = new DataManage();
    private int hostTurn = 4;
    private int oppTurn = 4;
    private double mouseAnchorY;
    private double mouseAnchorX;
    private double initialX;
    private double initialY;
    private int selectBlock;
    private boolean whoseTurn;
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
    @FXML private Label label11 = new Label();
    @FXML private Label label12 = new Label();
    @FXML private Label label13 = new Label();
    @FXML private Label label14 = new Label();
    @FXML private Label label15 = new Label();
    @FXML private Label label16 = new Label();
    @FXML private Label label17 = new Label();
    @FXML private Label label18 = new Label();
    @FXML private Label label19 = new Label();
    @FXML private Label label110 = new Label();
    @FXML private Label label111 = new Label();
    @FXML private Label label112 = new Label();
    @FXML private Label label113 = new Label();
    @FXML private Label label114 = new Label();
    @FXML private Label label115 = new Label();
    @FXML private Label label116 = new Label();
    @FXML private Label label117 = new Label();
    @FXML private Label label118 = new Label();
    @FXML private Label label119 = new Label();
    @FXML private Label label120 = new Label();
    @FXML private Label label21 = new Label();
    @FXML private Label label22 = new Label();
    @FXML private Label label23 = new Label();
    @FXML private Label label24 = new Label();
    @FXML private Label label25 = new Label();
    @FXML private Label label26 = new Label();
    @FXML private Label label27 = new Label();
    @FXML private Label label28 = new Label();
    @FXML private Label label29 = new Label();
    @FXML private Label label210 = new Label();
    @FXML private Label label211 = new Label();
    @FXML private Label label212 = new Label();
    @FXML private Label label213 = new Label();
    @FXML private Label label214 = new Label();
    @FXML private Label label215 = new Label();
    @FXML private Label label216 = new Label();
    @FXML private Label label217 = new Label();
    @FXML private Label label218 = new Label();
    @FXML private Label label219 = new Label();
    @FXML private Label label220 = new Label();
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

    private File blockFilled = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\block.png");
    private File blockDisabled = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\resources\\blockDisabled.png");
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
    private String blockFilledPth = blockFilled.toURI().toString();
    private String blockDisabledPth = blockDisabled.toURI().toString();

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
    private String card21Pth = card21.toURI().toString();
    private Image blockFilledImg = new Image(blockFilledPth);
    private Image blockDisabledImg = new Image(blockDisabledPth);
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
    private Image card21Img = new Image(card21Pth);
    private Image[] cards = {card1Img, card2Img, card3Img, card4Img, card5Img,
            card6Img, card7Img, card8Img, card9Img, card10Img,
            card11Img, card12Img, card13Img, card14Img, card15Img,
            card16Img, card17Img, card18Img, card19Img, card20Img, card21Img};
    public void initialize() {
        track1Blocks = new ImageView[]{block11, block12, block13, block14, block15,
                block16, block17, block18, block19, block110, block111, block112, block113,
                block114, block115, block116, block117, block118, block119, block120};
        track1Label = new Label[] {label11, label12, label13, label14, label15,
                label16, label17, label18, label19, label110, label111, label112, label113,
                label114, label115, label116, label117, label118, label119, label120};
        track2Blocks = new ImageView[]{block21, block22, block23, block24, block25,
                block26, block27, block28, block29, block210, block211, block212, block213,
                block214, block215, block216, block217, block218, block219, block220};
        track2Label = new Label[] {label21, label22, label23, label24, label25,
                label26, label27, label28, label29, label210, label211, label212, label213,
                label214, label215, label216, label217, label218, label219, label220};
        hostDeck = new ImageView[]{hostCard1, hostCard2, hostCard3, hostCard4, hostCard5};
        oppDeck = new ImageView[]{oppCard1, oppCard2, oppCard3, oppCard4, oppCard5};
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

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public void setStatus() {
        gameHostTurn.setText("turn : " + hostTurn);
        gameOppTurn.setText("turn : " + oppTurn);
        gameHostHealth.setText(Integer.toString(player.hp));
        gameOppHealth.setText(Integer.toString(secondPlayer.hp));
        gameHostNickname.setText(player.getNickname());
        gameOppNickname.setText(secondPlayer.getNickname());
        gameHostDamage.setText("Damage : " + game.getHost_total_damage());
        gameOppDamage.setText("Damage : " + game.getGuest_total_damage());
        updateTrack();
        updateDeck();
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
                int placeCardError = game.placeCard(index, selectBlock);
                if (placeCardError == -1) {
                    System.out.println("you cant put the card here");
                } else if (placeCardError == 5) {
                    System.out.println("spell 5");
                    updateTrack();
                    updateDeck();
                    updateStatus();
                } else if (placeCardError == 8) {
                    System.out.println("spell 8");
                    updateTrack();
                    updateDeck();
                    updateStatus();
                } else if (placeCardError == 1) {
                    if (game.getTurnCounter() < 8) {
                        changeTurn();
                        updateTrack();
                        updateDeck();
                        node.setOpacity(0);
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
                    updateTrack();
                    updateDeck();
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
        node.setOnMouseEntered(mouseDragEvent -> {
            selectBlock = index;
        });
    }
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
        public void updateTrack() {
        for (int i = 19; i >= 0; i--) {
            if (game.getTrackPad()[1][i].getDisabled()) {
                track1Blocks[i].setImage(blockDisabledImg);
            } else if (game.getTrackPad()[1][i].getFilled()) {
                track1Blocks[i].setImage(blockFilledImg);
                track1Label[i].setText(game.getTrackPad()[1][i].getDamage);
            }
        }
        for (int i = 19; i >= 0; i--) {
            if (game.getTrackPad()[0][i].getDisabled()) {
                track2Blocks[i].setImage(blockDisabledImg);
                System.out.println("m");
            } else if (game.getTrackPad()[0][i].getFilled()) {
                track2Blocks[i].setImage(blockFilledImg);
                track2Label[i].setText(game.getTrackPad()[0][i].getDamage);
                System.out.println("n");
            }
        }
    }
    public void updateDeck() {
        for (int i = 0; i < 5; i++) {
            hostDeck[i].setImage(cards[dataManage.cardIndex(player.getCardinDeck().get(i))]);
        }
        for (int i = 0; i < 5; i++) {
            oppDeck[i].setImage(cards[dataManage.cardIndex(secondPlayer.getCardinDeck().get(i))]);
        }
    }
    public void goToMainMenu(ActionEvent e) {

    }
}
