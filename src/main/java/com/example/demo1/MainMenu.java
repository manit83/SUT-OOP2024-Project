package com.example.demo1;

import java.util.Objects;

public class MainMenu {
    Player onUser ;
    DataManage dataManage;
    Shop shop ;
    Profilemenu profilemenu;
    Game game ;
    private int char_Host = 0;
    private int char_Guest = 0;
    private Player opponent;
    private int opp_ind ;

    MainMenu(Player player,DataManage dataManage){
        this.onUser = player;
        this.dataManage = dataManage;
        shop = new Shop(player,dataManage);
        profilemenu = new Profilemenu(player, dataManage);
    }
    public int setOpponent(String op_user,String op_pass){
        opp_ind = dataManage.userFinder(op_user);
        if(opp_ind == -1){return -1;}
        if(!Objects.equals(dataManage.players.get(opp_ind).getPassword(), op_pass)){return -2;}
        opponent = dataManage.players.get(opp_ind);
        return 1;
    }


    public void setChars(int char_Host,int char_Guest) {
        this.char_Host = char_Host;
        this.char_Guest = char_Guest;
        onUser.getCardinDeck().add(dataManage.cards.get(17 + char_Host));
        opponent.getCardinDeck().add(dataManage.cards.get(17 + char_Guest));
    }
    public void initGame(){
        game = new Game(onUser,opponent,char_Host,char_Guest);
        opponent.setHasUpdate(true);
        opponent.setHasUpdate(true);
    }

    public Player getOpponent() {
        return opponent;
    }
    //    public int setOpponent(String op_user,String op_pass){
//
//    }
}
