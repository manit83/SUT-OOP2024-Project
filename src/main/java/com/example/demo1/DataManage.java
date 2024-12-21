package com.example.demo1;

import java.io.*;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataManage {
    public  ArrayList<Player> players = new ArrayList<>();
    public  ArrayList<Card> cards = new ArrayList<>();
    DataManage(){
        readFromCardsManager();
        readUsersFile();
    }
    void writeFromUsersFile() throws IOException {
        System.out.println("sdgsgsdgs");
        String directoryPath = "C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\java\\com\\example\\demo1\\users\\";
        for (Player player : players){
            if(!player.hasUpdate){continue;}
            String username = player.getUsername();
            File file = new File(directoryPath + username + ".txt");
            file.createNewFile();
//            System.out.println(file.exists());
//            System.out.println("klnkn");
//
//            if(!file.exists()){
//                file.createNewFile();
//                file = new File(directoryPath + username + ".txt");
//            }
            try (FileWriter fileWriter = new FileWriter(file)) {
                String user = player.getUserName();
                String pass = player.getPassword();
                String nick = player.getNickname();
                String email = player.getEmail();
                int lvl = player.getLevel();
                int hp = player.getHp();
                int xp = player.getXp();
                int coin = player.getCoin();
                int n = player.getRecoverNum();
                String ans = player.getRecoverAns();
//                fileWriter.write(STR."\{player.getUserName()}\n");
//                fileWriter.write(STR."\{player.getPassword()}\n");
//                fileWriter.write(STR."\{player.getEmail()}\n");
//                fileWriter.write(STR."\{player.getNickname()}\n");
//                fileWriter.write(STR."\{player.getLevel()}\n");
//                fileWriter.write(STR."\{player.getHp()}\n");
//                fileWriter.write(STR."\{player.getXp()}\n");
//                fileWriter.write(STR."\{player.getCoin()}\n");
//                fileWriter.write(STR."\{player.getRecoverNum()}\n");
//                fileWriter.write(STR."\{player.getRecoverAns()}\n");
                fileWriter.write(user + "\n");
                fileWriter.write(pass + "\n");
                fileWriter.write(email + "\n");
                fileWriter.write(nick + "\n");
                fileWriter.write(lvl + "\n");
                fileWriter.write(hp + "\n");
                fileWriter.write(xp + "\n");
                fileWriter.write(coin + "\n");
                fileWriter.write(n + "\n");
                fileWriter.write(ans + "\n");
                for (Card card : player.getCards()) {
                    int ind = cardIndex(card);
                    int lev = card.getLevel();
                    fileWriter.write(ind + "--" + lev  + "\n");
//                    fileWriter.write(STR."\{ind}--\{card.getLevel()}\n");
                }
//            fileWriter.write("***");
//            for (Card card : player.getCardinDeck()){
//                int indOfCard = player.indexOfCardInDeck(card);
//                fileWriter.write(indOfCard + "\n");
//            }
                fileWriter.write("***\n");
                ArrayList<Info> infos = player.getGameHistory();
                for (Info info : infos) {
                    String temp = "";
                    temp += info.getTimeDate();//time
                    temp += "--";
                    String win;//win
                    if (info.isWin()) {
                        win = "1";
                    } else {
                        win = "0";
                    }
                    temp += win;
                    temp += "--";
                    temp += Integer.toString(info.getOp_l());//opl
                    temp += "--";
                    temp += info.getOp_name();//opn
                    temp += "--";
                    temp += Integer.toString(info.getXp_gained());//xp
                    temp += "\n";
                    fileWriter.write(temp);
                }
                fileWriter.write("***");
            }
        }
    }

    void readFromCardsManager(){
//        List<Card> objects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ASUS\\IdeaProjects\\game\\demo\\src\\main\\java\\com\\example\\demo\\cards.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                String name = parts[0];
                int prob = Integer.parseInt(parts[1]);
                int duration = Integer.parseInt(parts[2]);
                int damage = Integer.parseInt(parts[3]);
                int heal = Integer.parseInt(parts[4]);
                int accuracy = Integer.parseInt(parts[5]);
                boolean hs = Boolean.parseBoolean(parts[6]);
                int cost = Integer.parseInt(parts[7]);
                int lvl = Integer.parseInt(parts[8]);
                Card obj = new Card(name,prob,duration,damage,heal
                ,accuracy,hs,cost,lvl);
                cards.add(obj);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void readUsersFile(){
        File folder = new File("C:\\Users\\ASUS\\IdeaProjects\\game\\demo1\\src\\main\\java\\com\\example\\demo1\\users\\");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles){
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String name = br.readLine();
//                System.out.println(name);
                String password = br.readLine();
//                System.out.println(password);
                String email = br.readLine();
//                System.out.println(email);
                String nickname = br.readLine();
//                System.out.println(nickname);
                int level = Integer.parseInt(br.readLine());
//                System.out.println(level);
                int hp = Integer.parseInt(br.readLine());
//                System.out.println(hp);
                int xp = Integer.parseInt(br.readLine());
//                System.out.println(xp);
                int coin = Integer.parseInt(br.readLine());
//                System.out.println(coin);
                int recoverNum = Integer.parseInt(br.readLine());
//                System.out.println(recoverNum);
                String recoverAns = br.readLine();
//                System.out.println(recoverAns);
//                String card_inf = br.readLine();
                Player player = new Player(name,password,email
                        ,nickname);
                player.setLevel(level);
                player.setHp(hp);
                player.setXp(xp);
                player.setCoin(coin);
//                player.level = level;
//                player.hp = hp;
//                player.xp = xp;
//                player.coin = coin;
                String card_inf = br.readLine();
                player.setRecoverQuestions(recoverAns,recoverNum);
                while (!Objects.equals(card_inf, "***")){
//                    card_inf = br.readLine();
                    String[] parts = card_inf.split("--");
                    int card_ind = Integer.parseInt(parts[0]);
                    int lvl = Integer.parseInt(parts[1]);
                    Card temp = (Card) cards.get(card_ind).clone();
                    temp.syncCardAndLevel(lvl);
                    player.getCards().add(temp);
                    card_inf = br.readLine();
                }
//                card_inf = br.readLine();
//                while (!Objects.equals(card_inf, "***")){
//                    int indOfDeck = Integer.parseInt(card_inf);
//                    player.getCardinDeck().add(player.getCards().get(indOfDeck));
//                    card_inf = br.readLine();
//                }
                card_inf = br.readLine();
                while (!Objects.equals(card_inf, "***")){
                    card_inf = br.readLine();
                    String[] parts = card_inf.split("--");
                    String dt = parts[0];
                    String res = parts[1];
                    int op_level = Integer.parseInt(parts[2]);
                    String op_name = parts[3];
                    int xp_gained = Integer.parseInt(parts[4]);
                    boolean win = (Objects.equals(res, "1"));
                    Info info = new Info(dt,win,op_level,op_name,xp_gained);
                    player.getGameHistory().add(info);
                }
                players.add(player);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //    void writeToCardsFile(){
//
//    }
    int cardIndex(Card card){
        for (int i = 0; i < cards.size(); i++) {
            if(Objects.equals(cards.get(i).getName(), card.getName())) return i;
        }
        return -1;
    }
    int userFinder(String username){
        for (int i = 0; i< players.size();i++){
            Player player = players.get(i);
            if(Objects.equals(player.getUserName(), username))
                return i;
        }
        return -1;
    }

}

