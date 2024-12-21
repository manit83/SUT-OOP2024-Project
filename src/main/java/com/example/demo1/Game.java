package com.example.demo1;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Game {

    private final Player host;
    private final Player guest;
    private int bank;
    private int turn;
    private int turnCounter;
    private Block[][] trackPad = new Block[2][20];
    private int host_total_damage  = 0;
    private int guest_total_damage  = 0;
    private  int winner;

    Random random = new Random();
    private int placementCode = 1;
    private int roundsPlayed;
    private int host_char;
    private int guest_char;
    ArrayList<Card> host_ordCards = new ArrayList<>();
    ArrayList<Card> guest_ordCards = new ArrayList<>();
    ArrayList<Card> host_spells = new ArrayList<>();
    ArrayList<Card> guest_spells = new ArrayList<>();




    Game(Player host,Player guest,int host_char,int guest_char){
        this.host = host;
        this.guest = guest;
        this.host_char = host_char;
        this.guest_char = guest_char;
        initPlayersDeck();
        initGame();
        initRound();
    }
    Game(Player host,Player guest,int host_char,int guest_char,int bank){
        this.host = host;
        this.guest = guest;
        this.host_char = host_char;
        this.guest_char = guest_char;
        this.bank = bank;
        boolean gamble = true;
        initPlayersDeck();
        initGame();
        initRound();
    }
    public int getHost_total_damage(){return this.host_total_damage;}
    public int getGuest_total_damage(){return this.guest_total_damage;}

    // initializing game
    void initGame(){
        // choosing starter
        int rnd = random.nextInt(100) + 1;
        if (rnd <= 65){turn = 0;
            System.out.println("host starts the game");}
        else{turn = 1;
            System.out.println("guest starts the game");
        }
    }
    void initRound(){
        for (int i = 0; i < 20; i++) {
            trackPad[0][i] = new Block();
            trackPad[1][i] = new Block();
        }
        // disabling a random block off every player
        int rand1 = random.nextInt(20);
        int rand2 = random.nextInt(20);
        trackPad[0][rand1].setDisabled(true);
        trackPad[1][rand2].setDisabled(true);

    }
    // checkers
    void endOfRoundChecker() throws InterruptedException {
        if( turnCounter >=8 ){int res = endOfRoundOperator();}
    }
    int endOfGameCheCker(){
        int h_hp = host.getHp();
        int g_hp = guest.getHp();
        if(h_hp <= 0){host.setHp(0);return 2;}
        if(g_hp <= 0){guest.setHp(0);return 1;}
        return 0;
    }
    // placing cards
    int placeCard(int n,int ind) throws InterruptedException {
        Player online = null  ;
        if(turn == 0){online = host;}
        if(turn == 1){online = guest;}
        Card card = online.getCardinDeck().get(n);
        // check if the corresponding blocks are filled
        int duration = card.getDuration();
        boolean all_ok = true;
        for (int i = ind; i < ind + duration; i++) {
            Block temp = trackPad[turn][i];
            if (temp.getFilled()||temp.getDisabled()) {
                all_ok = false;
                break;
            }
        }
        if(!all_ok){return -1;}
        // struct the blocks

        String name = card.getName();
        online.getCardinDeck().removeIf(card1 -> Objects.equals(card1.getName(), name));
        // just adding details for spell5
        int f = abilityClarifier(name);
        if(f==5){
            for (int i = 0; i < 24; i++)
                if(trackPad[turn][i].getDisabled()){trackPad[turn][i].setDisabled(false);}
            if(turn == 0){fillHostDeck();}
            if(turn == 1){fillGuestDeck();}
            turnCounter ++;
            turn = 1 - turn;
            placementCode ++;
            return 5;
        }
        if(f==8){
            if(turn == 0){
                for (Card card1 : guest.getCardinDeck()){
                    int type = abilityClarifier(card1.getName());
                    if(type >= 1 && type <= 5){card1.setDisabledInDeck(true);}
                }
            }
            if(turn == 1){
                for (Card card1 : host.getCardinDeck()){
                    int type = abilityClarifier(card1.getName());
                    if(type >= 1 && type <= 5){card1.setDisabledInDeck(true);}
                }
            }
            if(turn == 0){fillHostDeck();}
            if(turn == 1){fillGuestDeck();}
            turnCounter ++;
            turn = 1 - turn;
            placementCode ++;
            return 8;
        }
        int b_damage = card.getDamage()/duration;
        int b_heal = card.getHeal()/duration;
        int acc = card.getAccuracy();
        for (int i = ind; i < ind + duration; i++) {
                Block temp = trackPad[turn][i];
                // set filled
                temp.setFilled(true);
                // set damage & heal
                temp.setDamage(calculateFinalDamage(b_damage,acc));
                temp.setHeal(b_heal);
                temp.setCode(placementCode);
                temp.setName(name);

        }
        trackPad[turn][ind].setHs(card.getHs());
        // turning and data
        if(turn == 0){host_total_damage += card.getDamage();fillHostDeck();}
        if(turn == 1){guest_total_damage += card.getDamage();fillGuestDeck();}
        turnCounter ++;
        turn = 1 - turn;
        placementCode ++;
        endOfRoundChecker();
        return 1;
    }
    // timeline and reset of the track
    int flag = 0;
    int endOfRoundOperator() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            Block hostBlock = trackPad[0][i];
            Block guestBlock = trackPad[1][i];

            boolean h_d = (hostBlock.getDisabled() || !hostBlock.getFilled());
            boolean g_d = (guestBlock.getDisabled() || !guestBlock.getFilled());

            // handel special abilities
            if (!h_d && hostBlock.getHs()) {
                String name = hostBlock.getName();
                int sign = abilityClarifier(name);
                if (sign == 1) {ability1(i, 1);}
                if (sign == 3) {ability3(i + 1, 0);}
                if (sign == 6) {host.setHp(Math.max(100, host.getHp() + 60));}
//                    if(sign == 7){}
            }
            if (!g_d && guestBlock.getHs()) {
                String name = guestBlock.getName();
                int sign = abilityClarifier(name);
                if (sign == 1) {ability1(i, 0);}
                if (sign == 3) {ability3(i + 1, 1);}
                if (sign == 6) {host.setHp(Math.max(100, host.getHp() + 60));}
//                    if(sign == 7){}
            }
            // handel damages
            if(h_d && g_d){continue;}
            int g_hp = guest.getHp();
            int h_hp = host.getHp();
            if(!h_d && g_d){
                flag = 1;
                int damage = hostBlock.getDamage();
                int heal = hostBlock.getHeal();
                host.setHp(Math.min(100,h_hp  + heal));
                guest.setHp(Math.max(0,g_hp - damage));
            }
            if(h_d && !g_d){
                flag = 2;
                int damage = guestBlock.getDamage();
                int heal = guestBlock.getHeal();
                guest.setHp(Math.min(100,g_hp  + heal));
                host.setHp(Math.max(0,h_hp - damage));
            }
            if(!h_d && !g_d){
                int h_damage = hostBlock.getDamage();
                int g_damage = guestBlock.getDamage();
                if(h_damage > g_damage){
                    flag = 1;
                    int damage = hostBlock.getDamage();
                    int heal = hostBlock.getHeal();
                    host.setHp(Math.min(100,h_hp  + heal));
                    guest.setHp(Math.max(0,g_hp - damage));
                }
                if(h_damage == g_damage) {
                    flag = 3;
                    int damageToGuest = hostBlock.getDamage();
                    int healToHost = hostBlock.getHeal();
                    host.setHp(Math.min(100,h_hp  + healToHost));
                    guest.setHp(Math.max(0,g_hp - damageToGuest));
                    int damageToHost = guestBlock.getDamage();
                    int healToGuest = guestBlock.getHeal();
                    guest.setHp(Math.min(100,g_hp  + healToGuest));
                    host.setHp(Math.max(0,h_hp - damageToHost));
                }
                if(h_damage < g_damage){
                    flag = 2;
                    int damage = guestBlock.getDamage();
                    int heal = guestBlock.getHeal();
                    guest.setHp(Math.min(100,g_hp  + heal));
                    host.setHp(Math.max(0,h_hp - damage));
                }
            }
            int checkForEnd = endOfGameCheCker();
            if (checkForEnd != 0) {
                winner = checkForEnd - 1;
                updateAccountsAfterGame();
                return winner * 100 + i;
            }
            Thread.sleep(1000);
        }
        trackPad = new Block[2][25];
        turnCounter = 0;
        host_total_damage = 0 ;
        guest_total_damage = 0 ;
        roundsPlayed++;
        initRound();
        updateAccountsAfterRound();
        return 0;

    }
    // damage &  accuracy
    private static int calculateFinalDamage(int damage, int accuracy) {
        Random random = new Random();
        int hitChance = random.nextInt(100) + 1; // Generates a number between 1 and 100

        if (hitChance <= accuracy) {
            // Attack hits
            return damage;
        } else {
            // Attack misses
            return 0;
        }
    }
    private static int abilityClarifier(String name){
        Pattern pattern1 = Pattern.compile("Spell(\\d+)");
        Matcher matcher1 = pattern1.matcher(name);
        Pattern pattern2 = Pattern.compile("CardChar(\\d+)");
        Matcher matcher2 = pattern2.matcher(name);
        if(matcher1.find()){
            return Integer.parseInt(matcher1.group(1));
        }
        if(matcher2.find()){
            return Integer.parseInt(matcher2.group(1)) + 5;
        }
        return -1;
    }
    public  void ability1(int start_ind,int row){
        int dst = Math.max(24,start_ind + 2);
        int strt = Math.max(24,start_ind + 1);
        for (int i = strt ; i <= dst ; i++) {
            Block block = trackPad[row][i];
            String name = block.getName();
            int flag = abilityClarifier(name);
            if(flag >= 1 && flag <= 5){block.setDisabled(true) ;}
        }
    }
    public  void ability3(int start_ind,int row){
        String name = trackPad[row][start_ind].getName();
        int flag = abilityClarifier(name);
        if(flag >= 1 && flag <= 5){return;}
        int code = trackPad[row][start_ind].getCode();
        for (int i = start_ind  ; i < 25; i++) {
            Block block = trackPad[row][i];
            if(block.getCode() != code){break;}
            int damage = block.getDamage();
            int heal = block.getHeal();
            block.setDamage(damage + 10);
            block.setHeal(heal + 5);
        }
    }
    void updateAccountsAfterGame(){
        // hp & level & info & xp
        Player w ;
        Player l ;
        int xp_w,xp_l ;
        int l_l,w_l ;
        String w_name,l_name ;
        if(winner == 0){w = host;l=guest;}
        else{l = host;w=guest;}
        xp_w = w.getXp();xp_l = l.getXp();
        l_l = l.getLevel();w_l = w.getLevel();
        w_name = w.getUserName();
        l_name = l.getUserName();
        String now = dateAndTime();
//        LocalDateTime now = dateAndTime();
        int winnerXP = calculateXP(w,l, roundsPlayed, true);
        int loserXP = calculateXP(w, l, roundsPlayed, false);
        w.setXp(xp_w + winnerXP);
        l.setXp(xp_l + loserXP);
        w.levelUpdate();
        l.levelUpdate();
        Info l_info = new Info(now,false,w_l,w_name,loserXP);
        Info w_info = new Info(now,true,l_l,l_name,winnerXP);
        l.add_gameInfo(l_info);
        w.add_gameInfo(w_info);
        l.setHp(100);
        w.setHp(100);
    }
    void updateAccountsAfterRound(){

    }
    public static int calculateXP(Player winner, Player loser, int roundsPlayed, boolean isWinner) {
        int winnerLevel = winner.getLevel();
        int loserLevel = loser.getLevel();
        int hpDifference = winner.getHp() - loser.getHp();

        // Base XP calculation
        int baseXP = roundsPlayed * 10 + Math.abs(hpDifference) / 2;

        // Level factor
        double levelFactor = 1 + (winnerLevel - loserLevel) * 0.05;

        // Winner and loser multipliers
        double winnerMultiplier = isWinner ? 1.5 : 1.0;
        double loserMultiplier = isWinner ? 1.0 : 0.75;

        // Final XP calculation
        int finalXP = (int) (baseXP * levelFactor * (isWinner ? winnerMultiplier : loserMultiplier));
        return finalXP;
    }
    public String dateAndTime(){
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String res = current.format(dtf);
        return res;
    }
    private void initPlayersDeck(){
        for (Card card: host.getCards()){
            int type = abilityClarifier(card.getName());
            if(type >= 1 && type <= 5){host_spells.add(card);continue;}
            host_ordCards.add(card);
        }
        int spellNum = host_spells.size();
        int rnd = random.nextInt(spellNum);
        host.getCardinDeck().add(host_spells.get(rnd));
        ArrayList<Card> selectedCards = host.getCardinDeck();
        int totalSum = 0;
        for (Card obj : host_ordCards) {
            totalSum += obj.getProbability();
        }
        for (int i = 0; i < 3; i++) {
            int index = -1;
            int randomIndex = random.nextInt(totalSum) + 1;
            int sum = 0;
            for (int j = 0; j < host_ordCards.size(); j++) {
                sum += host_ordCards.get(j).getProbability();
                if (randomIndex <= sum) {
                    index = j;
                    break;
                }
            }
            if (index != -1) {
                selectedCards.add(host_ordCards.get(index));
                totalSum -= host_ordCards.get(index).getProbability();
                host_ordCards.remove(index);
            }
        }

        for (Card card: guest.getCards()){
            int type = abilityClarifier(card.getName());
            if(type >= 1 && type <= 5){guest_spells.add(card);continue;}
            guest_ordCards.add(card);
        }
        spellNum = guest_spells.size();
        rnd = random.nextInt(spellNum);
        guest.getCardinDeck().add(guest_spells.get(rnd));
        selectedCards = guest.getCardinDeck();
        totalSum = 0;
        for (Card obj : guest_ordCards) {
            totalSum += obj.getProbability();
        }
        for (int i = 0; i < 3; i++) {
            int index = -1;
            int randomIndex = random.nextInt(totalSum) + 1;
            int sum = 0;
            for (int j = 0; j < guest_ordCards.size(); j++) {
                sum += guest_ordCards.get(j).getProbability();
                if (randomIndex <= sum) {
                    index = j;
                    break;
                }
            }
            if (index != -1) {
                selectedCards.add(guest_ordCards.get(index));
                totalSum -= guest_ordCards.get(index).getProbability();
                guest_ordCards.remove(index);
            }
        }
    }
    void fillHostDeck(){
//        if(!isHostCharCardUsed()){
//
//            return;
//        }
        ArrayList<Card> all_cards_remained = new ArrayList<>() ;
        for (Card card:host_ordCards){all_cards_remained.add(card);}
        for (Card card:host_spells){all_cards_remained.add(card);}
        int totalSum = 0;
        for (Card obj : all_cards_remained) {
            totalSum += obj.getProbability();
        }
        int index = -1;
        int randomIndex = random.nextInt(totalSum) + 1;
        int sum = 0;
        for (int j = 0; j < all_cards_remained.size(); j++) {
            sum += all_cards_remained.get(j).getProbability();
            if (randomIndex <= sum) {
                index = j;
                break;
            }
        }
        if (index != -1) {
            host.getCardinDeck().add(all_cards_remained.get(index));
        }
        String temp = all_cards_remained.get(index).getName();
        int type = abilityClarifier(temp);
        if(type >= 1 && type <= 5)
            host_spells.removeIf(card -> Objects.equals(card.getName(), temp));
        else{
            host_ordCards.removeIf(card -> Objects.equals(card.getName(), temp));
        }
    }
    void fillGuestDeck(){
//        if(!isGuestCharCardUsed()){
//
//            return;
//        }
        ArrayList<Card> all_cards_remained = new ArrayList<>() ;
        for (Card card:guest_ordCards){all_cards_remained.add(card);}
        for (Card card:guest_spells){all_cards_remained.add(card);}
        int totalSum = 0;
        for (Card obj : all_cards_remained) {
            totalSum += obj.getProbability();
        }
        int index = -1;
        int randomIndex = random.nextInt(totalSum) + 1;
        int sum = 0;
        for (int j = 0; j < all_cards_remained.size(); j++) {
            sum += all_cards_remained.get(j).getProbability();
            if (randomIndex <= sum) {
                index = j;
                break;
            }
        }
        if (index != -1) {
            guest.getCardinDeck().add(all_cards_remained.get(index));
        }
        String temp = all_cards_remained.get(index).getName();
        int type = abilityClarifier(temp);
        if(type >= 1 && type <= 5)
            guest_spells.removeIf(card -> Objects.equals(card.getName(), temp));
        else{
            guest_ordCards.removeIf(card -> Objects.equals(card.getName(), temp));
        }
    }
//    boolean isHostCharCardUsed(){
//        for (Card card : host.getCardinDeck()){
//            if(card.getName().matches("CardChar\\d+"))return false;
//        }
//        return true;
//    }
//    boolean isGuestCharCardUsed(){
//        for (Card card : guest.getCardinDeck()){
//            if(card.getName().matches("CardChar\\d+"))return false;
//        }
//        return true;
//    }
    // choosing card
    int chooseCard(int ind){
        Player online = null  ;
        if(turn == 0){online = host;}
        if(turn == 1){online = guest;}
        if(online.getCardinDeck().get(ind).isDisabledInDeck()){return -1;}
        for (Card card : online.getCardinDeck())
            if(card.isDisabledInDeck()){card.setDisabledInDeck(false);}
        return ind;

    }

    public Block[][] getTrackPad() {
        return trackPad;
    }
    //    public void ability5(int row){
//
//    }


    public int getTurn() {
        return turn;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public int getFlag() {
        return flag;
    }
}
