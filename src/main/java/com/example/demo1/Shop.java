package com.example.demo1;

import java.util.ArrayList;
import java.util.Objects;

public class Shop{
    private Player player;
    private ArrayList<ArrayList<Card>> inventory = new ArrayList<>();
    DataManage dataManage;
    Shop(Player player,DataManage dataManage){
        this.player = player;
        this.dataManage = dataManage;
        setInventoryCards();
    }
    void setInventoryCards(){
        //first I will return the cards that player doesn't have  then we
        ArrayList<ArrayList<Card>> cards = this.inventory;
        ArrayList<Card> temp1 = new ArrayList<>();
        ArrayList<Card> temp2 = new ArrayList<>();
        cards.add(temp1);cards.add(temp2);
        for (Card card : dataManage.cards){
            boolean found = false;
            String n = card.getName();
            for(Card card1 : player.cards){
                if(Objects.equals(card1.getName(), n)){
                    found = true;
                    break;
                }
            }
            if(!found){cards.getFirst().add(card);}
        }
        //will go for updatable cards;
        for (Card card : player.cards){
            if(card.getUpdatable()){
                cards.get(1).add(card);
            }
        }
        System.out.println(cards.get(0).size());
        System.out.println(cards.get(1).size());
    }

    ArrayList<ArrayList<Card>> getInventory(){return this.inventory;}
    int buyCard(int ind) throws CloneNotSupportedException {
        //bring the card from the inventory
        Card card = (Card) inventory.getFirst().get(ind).clone();
        //check if player already has this card
        boolean playerHasThis = player.cardFound(card);
        if(playerHasThis){return -1;}
        //check if player has enough money
        int balance = player.getCoin();
        int cost = card.getCost();
        if(balance < cost){return -2;}
        //add card to the cards
        player.cards.add(card);
        //exchange for coin
        player.setCoin(balance - cost);
        //set the 'has updated'
        player.setHasUpdate(true);
        return 1;
    }
    int levelUPCard(int ind) throws CloneNotSupportedException {
        //bring the card from inventory
        Card card = (Card) inventory.get(1).get(ind).clone();
        //check if player has enough money
        int cost = card.getUpgradeCost();
        int balance = player.getCoin();
        if(balance < cost){return -1;}
        //update the card
        int level = card.getLevel();
        card.syncCardAndLevel(level+1);
        //exchange for coin
        player.setCoin(balance - cost);
        //set 'has updated'
        player.setHasUpdate(true);
        return 1;
    }

}
