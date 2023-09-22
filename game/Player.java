package game;


import charcters.*;
import stock.*;
import places.*;

public class Player {
    private int heroNumber;
    private Hero hero;
    private Stock stock;
    private String nick;
    private Places places;

    public Player(int heroNumber,String nick,Stock stock){
        this.heroNumber=heroNumber;
        if(nick.equals(""))
            nick="Player1";
        this.nick=nick;
        this.stock=stock;
    }

    public void selectChar(){
        switch (this.heroNumber){
            case 1:
            default:
                this.hero=new Ironman();
                break;
            case 2:
                this.hero=new Batman();
                break;
            case 3:
                this.hero=new Superman();
                break;
        }
    }

    public boolean selectLocation(int locId){
        switch (locId){
            case 1:
            default:
                this.places=new SafeHouse(this);
                break;
            case 2:
                this.places=new ToolStore(this);
                break;
            case 3:
                if(isWinLoc(new Park(this))){
                    this.places=new SafeHouse(this);
                    break;
                }else {
                    this.places = new Park(this);
                    break;
                }
            case 4:
                if(isWinLoc(new Gotham(this))){
                    this.places=new SafeHouse(this);
                    break;
                }else {
                    this.places = new Gotham(this);
                    break;
                }
            case 5:
                if(isWinLoc(new Hogwarts(this))){
                    this.places=new SafeHouse(this);
                    break;
                }else {
                    this.places=new Hogwarts(this);
                    break;
                }
            case 6:
                this.places=new Newyork(this);
                break;
            case 7:
                getItemList();
                return true;
        }
        return places.onPlaces();
    }


    private boolean isWinLoc(FightPlace fightPlace){
        if(fightPlace.getId()==3 && this.getStock().isWood()){
            System.out.println("Bu alanda savası kazandınız. Tekrar Giremezsiniz.");
            return true;
        }
        if(fightPlace.getId()==4 && this.getStock().isFood()){
            System.out.println("Bu alanda savası kazandınız. Tekrar Giremezsiniz.");
            return true;
        }
        if(fightPlace.getId()==5 && this.getStock().isWater()){
            System.out.println("Bu alanda savası kazandınız. Tekrar Giremezsiniz.");
            return true;
        }
        if(fightPlace.getId()==6 && this.getStock().isGold()){
            System.out.println("Bu alanda savası kazandınız. Tekrar Giremezsiniz.");
            return true;
        }
        return false;

    }

    private void getItemList() {
        System.out.println("Silah : "+this.getStock().getWeapon().getName());
        System.out.println("Zırh : "+this.getStock().getArmor().getName());
        System.out.println("Water : "+this.getStock().isWater());
        System.out.println("Food : "+this.getStock().isFood());
        System.out.println("Wood : "+this.getStock().isWood());
        System.out.println("Gold : "+this.getStock().isGold());
    }

    public boolean win(){
        return this.getStock().isWood() && this.getStock().isFood() && this.getStock().isWater()&&this.getStock().isGold();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getHeroNumber() {
        return heroNumber;
    }

    public void setHeroNumber(int heroNumber) {
        this.heroNumber = heroNumber;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Places getPlaces() {
        return places;
    }

    public void setPlaces(Places places) {
        this.places = places;
    }
    public int getPlayerHealth(){
        return this.getHero().getHealth();
    }
    public int getPlayerDamage(){
        return this.getHero().getDamage();
    }
    public int getPlayerMoney(){
        return this.getHero().getMoney();
    }
    public int getPlayerDefence(){
        return this.getStock().getArmor().getDefence();
    }

    public Stock getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return  "\u001B[35mOyuncunun Özellikleri :\n" +
                "Nick = " + this.nick +
                ", Seçilen karakter= " + this.hero.getName() +
                ", Karakter canı = " + this.hero.getHealth() +
                ", Karakterin başlangıçtaki hasarı = " + this.hero.getDefaultDamage() +
                ", Karakterin toplam hasarı = "+this.getHero().getDamage()+
                ", Paranız = " + this.getHero().getMoney() +
                ", Zırh savunması ="+this.getStock().getArmor().getDefence()+
                ", Silah hasarı ="+this.getStock().getWeapon().getDamage()+"\u001B[0m";
    }

}

