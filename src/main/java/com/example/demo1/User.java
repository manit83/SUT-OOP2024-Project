package com.example.demo1;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class User {
    protected String userName ;
    protected String password;
    protected String nickname ;
    protected String email ;

//    protected int recoverNum ;
//    protected String recoverAns ;
//    private ArrayList<Card> cards ;
//    private boolean[] boolCards  = new boolean[20];

    User(String userName,String password,String email,String nickname){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }
    User(String userName,String password,String nickname){
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
    }
    //get

    String getUsername(){return this.userName;}

    String getPassword(){
        return this.password;
    }
    String getNickname(){
        return this.nickname;
    }
    String getEmail(){
        return this.email;
    }
    void setUserName(String userName){this.userName = userName;}



}
