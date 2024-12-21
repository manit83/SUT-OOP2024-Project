package com.example.demo1;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Signinmenu {
    boolean menuLocked = false;
    private int tryCount = 0;
    ArrayList<Player> players1;
    private  ArrayList<Admin> admins = new ArrayList<>();
    DataManage dataManage ;
    Signinmenu(DataManage dataManage){
        this.dataManage = dataManage;
        this.players1 = dataManage.players;
        System.out.println("klkmlk");
    }

    void setRecoveryQuestion(String ans , int num){
        System.out.println(players1.size());
        Player player = players1.getLast();

//        Player player = players.getLast();
        player.setRecoverQuestions(ans,num);
        player.level = 1;
        player.hp = 100;
        player.xp = 0;
        player.coin = 80;
    }
    String generateRandomPass(){
        ArrayList<Character> chars = new ArrayList<Character>();
        Random random = new Random();
        int upperCount = random.nextInt(2) + 3;
        int lowerCount = random.nextInt(2) + 3;
        int charCount = random.nextInt(2) + 3;
        char[] chars1 = {'!','@','#','$','^','&','*','(',')','+','-','[',']','{','}','<','>','?'};
        for (int i = 1; i <= upperCount; i++) {
            int ascii = random.nextInt(26) + 65;
            chars.add((char)ascii);
        }
        for (int i = 1; i <= lowerCount; i++) {
            int ascii = random.nextInt(26) + 97;
            chars.add((char)ascii);
        }
        for (int i = 1; i <= charCount; i++) {
            int ind = random.nextInt(18) ;
            chars.add(chars1[ind]);
        }
        String pass  = "";
        int len = upperCount + lowerCount + charCount;
        for (int i =1 ; i<= len ; i++){
            int ind = random.nextInt(chars.size());
            String temp = String.valueOf(chars.get(ind));
            pass += temp;
            chars.remove(ind);
        }
        return  pass;

    }
    Captcha generateCaptcha(){
        Random random = new Random();
        int spc = random.nextInt(3);
        String func = "";
        if(spc == 0){func = " PLUS ";}
        if(spc == 1){func = " MINUS ";}
        if(spc == 2){func = " MULTIPLY ";}
        int n1 = random.nextInt(20);
        int func2 = random.nextInt(4);
        int n3 = random.nextInt(5) +1;
        int ans2 = 0;
        String part2 = "";
        if(func2 == 0){int n2 = random.nextInt(10);ans2 = n2+n3;
            part2 = n2 + "+" + n3;}
        if(func2 == 1){int n2 = random.nextInt(10);ans2 = n2-n3;
            part2 = n2 + "-" + n3;}
        if(func2 == 2){int n2 = random.nextInt(5);ans2 = n2*n3;
            part2 = n2 + "*" + n3;}
        if(func2 == 3){int n2 = (random.nextInt(5)+1) * n3;ans2 = n2/n3;
            part2 = n2 + "/" + n3;}
        if(spc == 0){
            int ans = n1 + ans2;
            String q = n1 + func + part2;
            return new Captcha(q,ans);
        }
        if(spc == 1){
            int ans = n1 - ans2;
            String q = n1 + func + part2;
            return new Captcha(q,ans);

        }
        int ans = n1 * ans2;
        String q = n1 + func + part2;
        return new Captcha(q,ans);
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
    int checkCaptcha(Captcha captcha, int ans){
        if(ans == captcha.getAns()){
            return 1;
        }
        return -1;
    }
    int signUp(String username,String password,String passC , String email,String nickname){
        if(Objects.equals(username, "") || Objects.equals(password, "") || Objects.equals(passC, "")
        || Objects.equals(email, "") || Objects.equals(nickname, "")){return -1;}
        boolean uservalidation = usernameValid(username);
        if(!uservalidation){return -2;}
        if(userExists(username)>0){return -3;}
        if(!Objects.equals(password, passC)){return -4;}
        int passvalidation = checkPassStrength(password);
        if(passvalidation == -1){return -5;}
        if(passvalidation == -2){return -6;}
        boolean emailValidation = checkEmail(email);
        if(!emailValidation){return -7;}
        Player newCommer = new Player(username,password,email,nickname);
        playerCardsInit(newCommer);
        players1.add(newCommer);
        System.out.println("llllll");
        System.out.println(players1.size());
        return 1;
    }
    int signUp(String username, String email,String nickname){
        if(Objects.equals(username, "")){return -1;}
        if(Objects.equals(email, "")){return -4;}
        if(Objects.equals(nickname, "")){return -5;}
        boolean uservalidation = usernameValid(username);
        if(!uservalidation){return -6;}
        if(userExists(username)>0){return -7;}
        boolean emailvalidation = checkEmail(email);
        if(!emailvalidation){return -11;}
        String pass = generateRandomPass();
        Player newCommer = new Player(username,pass,email,nickname);
        playerCardsInit(newCommer);
        players1.add(newCommer);
        return 1;
    }
    boolean checkEmail(String email){
        Pattern pattern = Pattern.compile("(\\S+)@(\\S+).com");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();

    }

    void playerCardsInit(Player player){
        Random random = new Random();
        int spell_rnd = random.nextInt(5);
        player.addCard(dataManage.cards.get(spell_rnd));
        Set<Integer> cardIndex = new HashSet<>();
        while (cardIndex.size()<4){cardIndex.add(random.nextInt(15)+5);}
        for (Integer n : cardIndex){
            int ind = n;
            player.addCard(dataManage.cards.get(ind));
        }
        player.hasUpdate = true;
    }
    int userExists(String username){
        for (int i = 0; i < players1.size(); i++) {
            Player player = players1.get(i);
            if(Objects.equals(player.getUserName(), username)){
                return i;
            }
        }
        return -1;
    }
    int login(String username,String password){
        int  sign = userExists(username);
        if(sign == -1){return -1;}
        Player player = players1.get(sign);
        if(!Objects.equals(player.getPassword(), password)){
            tryCount ++;
//            loginLockCheck();
            return -2;}
        return sign;

    }
    void loginLock(String user,String pass){
        return;
    }
    void loginLockCheck(){
        if (tryCount >= 5){menuLocked = true;}
    }

    /////////
    int forgotPassUserExist(String username){
        int sign = userExists(username);
        return sign;
    }
    int recoverPass(int userIndex,String ans){
        Player player = players1.get(userIndex);
        String recoverAns = player.getRecoverAns();
        if(!Objects.equals(ans, recoverAns)){return -1;}
        return 1;
    }
    int recoverSetPass(int userIndex,String pass){
        Player player = players1.get(userIndex);
        int isPassValid = checkPassStrength(pass);
        if(isPassValid < 0){return -1;}
        player.setPassword(pass);
        player.hasUpdate = true;
        return 1;
    }
    boolean usernameValid(String username){
        String regex = "^[a-zA-Z]+(_[0-9]+[a-zA-Z]*)*$";
        return username.matches(regex);
    }


//    int checkPasswordStrength(String pass){
//
//    }
//    boolean usernameValid(String username) {
//        boolean hasUpperCase = false;
//        boolean hasLowerCase = false;
//        boolean hasDigit = false;
//
//        for (int i = 0; i < username.length(); i++) {
//            char ch = username.charAt(i);
//
//            if (Character.isUpperCase(ch)) {
//                hasUpperCase = true;
//            } else if (Character.isLowerCase(ch)) {
//                hasLowerCase = true;
//            } else if (Character.isDigit(ch)) {
//                hasDigit = true;
//            }
//
//        }
//        if (!(hasDigit && hasLowerCase && hasUpperCase)) {
//            return false;
//        }
//        return true;
//    }
    //    int forgotPassword(String username,String ans,String newPass){
//
//    }
    //    void addAdmin(String username,String password, String email,String nickname){
//
//    }
//    ArrayList<User> getUsers(){
//
//    }
//    ArrayList<Admin> getAdmins(){
//
//    }









}
