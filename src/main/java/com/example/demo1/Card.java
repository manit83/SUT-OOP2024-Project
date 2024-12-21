package com.example.demo1;

public class Card implements Cloneable{
    private String name;
//    private int defense;
    private int duration;
    private int damage;
    private int level ;
    private int upgradeCost;
    private int probability;
    private int cost;
    private int heal;
    private int accuracy ;
    private boolean hs;
    private boolean updatable = true;
    private boolean disabledInDeck = false;
    private boolean usedInDeck;
    private boolean selected;

    Card(String name,int probability,int duration
            ,int damage,int heal,int accuracy
            ,boolean hs,int cost,int level){
        this.name =name;
        this.probability = probability;
        this.duration = duration;
        this.damage = damage;
        this.heal = heal;
        this.accuracy = accuracy;
        this.hs = hs;
        this.cost = cost;
        this.level = level;
//        setUpgradeCost();
    }
    //get
    public String getName(){return this.name;}
    public int getDuration(){return this.duration;}
    public int getDamage(){return this.damage;}
    public int getLevel(){return this.level;}
    public int getUpgradeCost(){return this.upgradeCost;}
    public int getProbability(){return this.probability;}
    public int getCost(){return this.cost;}
    public int getHeal(){return this.heal;}
    public int getAccuracy(){return this.accuracy;}
    public boolean getHs(){return this.hs;}
    public boolean getUpdatable(){return this.updatable;}
    public boolean isDisabledInDeck() {return disabledInDeck;}
    public boolean isSelected() {return selected;}

    // set
    void setName(String name){this.name = name;}
    void setDuration(int duration){this.duration = duration;}
    void setDamage(int damage){this.damage = damage;}
    void setLevel(int level){this.level = level;}
    void setProbability(int probability){this.probability = probability;}

    public void setSelected(boolean selected) {this.selected = selected;}

    public void setDisabledInDeck(boolean diabledInDeck) {this.disabledInDeck = diabledInDeck;}
    void setUpgradeCost(){upgradeCost = (cost/8)*level;}
    // function
    void syncCardAndLevel(int level){
        this.level = level;
        //damage
        damage += (damage/5)*(level-1) ;
        //heal
        heal += (heal/5)*(level-1) ;
        //upgradeCost
        setUpgradeCost();
        //accuracy
        accuracy += 2*(level-1);
    }
//    void setName(String name){
//        this.name =name;
//    }
//    void setDefense(int defense){
//        this.defense =defense;
//    }
//    void setDuration(int duration){
//        this.duration = duration;
//    }
//    void setDamage(int damage){
//        this.damage = damage;
//    }
//    void setLevel(int level){
//        this.level = level;
//    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }




}
