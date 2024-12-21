package com.example.demo1;

public class Block {

    public String getDamage;
    private boolean disabled = false;
    private boolean filled = false;
    private int damage= 0;
    private int heal = 0;
    private boolean hs = false;
    private String name = "";
    private int code = 0 ;
    //set
    public void setDisabled(boolean disabled){this.disabled = disabled;}
    public void setFilled(boolean filled){this.filled = filled;}
    public void setDamage(int damage){this.damage = damage;}
    public void setHeal(int heal){this.heal = heal;}
    public void setHs(boolean hs){
        this.hs = hs;
    }
    public void setName(String name){this.name = name;}
    public void setCode(int code) {
        this.code = code;
    }
//    public void setAcc(int acc){this.acc = acc;}
    //get
    public boolean getFilled(){return this.filled;}
    public int getDamage(){return this.damage;}
    public int getHeal(){return this.heal;}
    public boolean getHs(){return this.hs;}
    public String getName(){return this.name;}
    public boolean getDisabled(){return this.disabled;}

    public int getCode() {
        return code;
    }



//    public int getAcc(){return this.acc;}


}
