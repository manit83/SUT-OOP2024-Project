package com.example.demo1;

import java.util.ArrayList;
import java.util.Objects;

public class Player extends User implements Cloneable{
    public ArrayList<Card> cardinDeck = new ArrayList<>();
    public ArrayList<Card> cards = new ArrayList<>();
    private boolean[] boolCards  = new boolean[20];
    private int recoverNum ;
    boolean hasUpdate = false;
    private String recoverAns ;
    private ArrayList<Info> gameHistory = new ArrayList<>();
    int level = 0;
    int hp;
    int xp;
    int coin = 200;



    Player(String userName, String password, String email, String nickname) {
        super(userName, password, email, nickname);
        Info info =new Info("af",true,5,"aifhf",34);
        gameHistory.add(info);
    }

    //get
    public String getUserName() {
        return this.userName;
    }
    public String getPassword(){
        return this.password;
    }
    public String getNickname(){
        return this.nickname;
    }
    public String getEmail(){
        return this.email;
    }
    public String getRecoverAns(){return this.recoverAns;}

    public void addCard(Card card){this.cards.add(card);}
    public int getLevel(){return this.level;}
    public int getHp(){return this.hp;}
    public int getXp(){return this.xp;}
    public int getCoin(){return this.coin;}
    public int getRecoverNum(){return this.recoverNum;}
    public ArrayList<Card> getCards(){return this.cards;}
    public ArrayList<Card> getCardinDeck(){return this.cardinDeck;}
    public ArrayList<Info> getGameHistory() {
        return gameHistory;
    }
    // set

    public void setLevel(int level){this.level = level;}
    public void setHp(int hp){this.hp = hp;}
    public void setXp(int xp){this.xp = xp;}
    public void setCoin(int coin){this.coin = coin;}
    public void setNickname(String nickname){this.nickname = nickname;}
    public void setEmail(String email){this.email = email;}
    void setRecoverQuestions(String ans ,int num){
        recoverAns = ans;
        recoverNum = num;
    }
    public void setHasUpdate(boolean hasUpdate){this.hasUpdate = hasUpdate;}
    public void setPassword(String pass){this.password = pass;}
    public void setGameHistory(ArrayList<Info> gameHistory) {
        this.gameHistory = gameHistory;
    }
    // functions
    boolean cardFound(Card card){
        for (Card temp : cards){
            if(Objects.equals(temp.getName(), card.getName())){return true;}
        }
        return false;
    }
    public int indexOfCardInDeck(Card card){
        for (int i = 0; i < cards.size(); i++) {
            if(Objects.equals(card.getName(), cards.get(i).getName())){return i;}
        }
        return -1;
    }
    private int xpToLevelUp = 50;
    public void levelUpdate(){
        while (xp > xpToLevelUp){
            xp -= xpToLevelUp;
            setXPForNextLevel();
        }
    }
    public void setXPForNextLevel(){xpToLevelUp = 50 * level * level;}
    public void add_gameInfo(Info info){this.gameHistory.add(info);}


    @Override
    public Player clone() {
        try {
            Player clone = (Player) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
