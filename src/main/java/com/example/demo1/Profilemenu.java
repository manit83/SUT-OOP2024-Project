package com.example.demo1;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profilemenu {
    private ArrayList<Player> players ;
    private Player player ;
    private DataManage dataManage;
    Profilemenu(Player player,DataManage dataManage){
        this.player = player;
        this.dataManage = dataManage;
    }

    void showProfile(){
        String username = player.getUsername();
//        String password = player.getPassword();
        String Email = player.getEmail();
        int level = player.getLevel();
        int xp = player.getXp();
        int coin = player.getCoin();
        System.out.println("Players information");
        System.out.println("username : " + username);
        System.out.println("Email : " + Email);
        System.out.println("Level : " + level);
        System.out.println("Xp : " + xp);
        System.out.println("Coins : " + coin);
    }
    int editProfile(String username,String oldPass,String newPass,String nickname,String email){
        ///
        player.hasUpdate = true;
        if(!usernameValid(username)){return -1;}
        int flag  = userExists(username);
        if(flag > 0){return -2;}
        player.setUserName(username);
        ///
        player.setNickname(nickname);
        ///
        if(!Objects.equals(player.getPassword(), oldPass)){return -3;}
        if(Objects.equals(oldPass, newPass)){return -4;}
        int confirmation = checkPassStrength(newPass);
        if(confirmation == -1 || confirmation==-2){return -5;}
        player.setPassword(newPass);
        ///
        if(!checkEmail(email)){return -6;}
        player.setEmail(email);
        ///
        return 1;
    }
//    int changeUsername(String newNickname){
//
//    }
//    void changeNickname(String nickname){}
    int changePassword(String oldPass,String newPass){
        return 1;
    }
    boolean changeEmail(String newEmail){

        return true;
    }

    boolean usernameValid(String username){
        String regex = "^[a-zA-Z]+(_[0-9]+[a-zA-Z]*)*$";
        return username.matches(regex);
    }
    int checkPassStrength(String pass){
        if (pass.length() < 8) {
            return -1;
        }
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;

        for (char c : pass.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
        if(!(hasSpecialChar&&hasLowerCase&&hasUpperCase)){
            return -2;
        }
        return 1;
    }
    boolean checkEmail(String email){
        Pattern pattern = Pattern.compile("(\\S+)@(\\S+).com");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
    int userExists(String username){
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if(Objects.equals(player.getUserName(), username)){
                return i;
            }
        }
        return -1;
    }
    //    public static boolean isAlphanumeric(String str) {
//        return str.matches("^[a-zA-Z0-9]+$");
//    }
}
