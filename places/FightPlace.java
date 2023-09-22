package places;

import java.util.Locale;
import java.util.Random;

import enemies.Difficulty;
import game.Player;

public abstract class FightPlace extends Places {
	
	 Random random=new Random();
	    private Difficulty difficulty;
	    private String award;
	    private int numDif;
	    public FightPlace(int id, String name, Player player, Difficulty difficulty, String award) {
	        super(id, name, player);
	        this.difficulty=difficulty;
	        this.award=award;
	    }

	    @Override
	    public boolean onPlaces() {
	        System.out.println("Şuan bulunduğunuz yer : \u001B[31m"+this.getName()+"\u001B[0m");
	        setNumDif(this.getId());
	        System.out.println("Dikkatli olun burada "+this.getNumDif()+" adet " +this.getDifficulty().getName().toUpperCase()+ " yaşıyor!");
	        System.out.print("Bu düşmanlarla savaşmak için 'S' kaçmak için 'K' basınız : ");
	        String sel=scanner.nextLine().toUpperCase();
	        if(sel.equals("S")){
	            if(!combat(getNumDif()) && this.getPlayer().getPlayerHealth()==0){
	                return false;
	            }
	        }
	        else if(sel.equals("K")){
	            System.out.println("\u001B[31mCanavardan kaçtınız.\u001B[0m");

	        }
	        else {
	            System.out.println("\u001B[33mYanlış girdiniz! Ana Ekrana dönülüyor\u001B[0m");
	        }
	        return true;
	    }

	    public boolean combat(int value){
	        boolean isFirst=true;
	        for (int i = 1; i <= value; i++) {

	            System.out.println(this.getPlayer().toString());
	            System.out.println(this.difficulty.toString());
	            boolean finish=true;
	            while (this.difficulty.getHealth()>0 && this.getPlayer().getPlayerHealth()>0){
	                if(firstShot()==2 && isFirst) { //ilk olarak oyuncu vuruyor
	                    System.out.println("Vuruş Hakkı Sizle Başlıyor.");
	                    System.out.println("Vurmak için 'V' , kaçmak için 'K' basınız .");
	                    String match = scanner.nextLine().toUpperCase(Locale.ROOT);
	                    if (match.equals("V")) {
	                        System.out.println(this.getPlayer().getNick() + " " + this.difficulty.getName() + " düşmanına "
	                                + this.getPlayer().getPlayerDamage() + " puan hasar vurdunuz!");
	                        int obsHealth = this.getDifficulty().getHealth() - this.getPlayer().getPlayerDamage();
	                        this.getDifficulty().setHealth(obsHealth);
	                        System.out.println(this.difficulty.getName() + " canavarının canı \u001B[31m" + this.getDifficulty().getHealth() + " kaldı.\u001B[0m");
	                        if (this.getDifficulty().getHealth() > 0) { //canavar ölmezse bize vuruyor
	                            System.out.println("\u001B[32m"+i + ".  Canavar Size Vurdu !\u001B[0m");
	                            int damage = this.getDifficulty().getDamage() - getPlayer().getPlayerDefence();
	                            if (damage < 0) damage = 0;
	                            this.getPlayer().getHero().setHealth(this.getPlayer().getPlayerHealth() - damage);
	                            System.out.println("Canınız : \u001B[31m" + this.getPlayer().getPlayerHealth()+"\u001B[0m");
	                            if (this.getPlayer().getPlayerHealth() == 0) {
	                                return false;
	                            }
	                        }
	                    }
	                    else {
	                        System.out.println("\u001B[31mSavaş alanından kaçtınız.\u001B[0m");
	                        return true;
	                    }
	                }
	                else {
	                    finish = true;
	                    isFirst = false;
	                    System.out.println("\u001B[34mVuruş Hakkı Canavarla Başlıyor.\u001B[0m");
	                    System.out.println("\u001B[32m"+i + ". Canavar Size Vurdu !\u001B[0m");
	                    int damage = this.getDifficulty().getDamage() - getPlayer().getPlayerDefence();
	                    if (damage < 0) damage = 0;
	                    this.getPlayer().getHero().setHealth(this.getPlayer().getPlayerHealth() - damage);
	                    System.out.println("Canınız : \u001B[31m" + this.getPlayer().getPlayerHealth()+"\u001B[0m");
	                    if (this.getPlayer().getPlayerHealth() < 1) {
	                        return false;
	                    }
	                    System.out.println("Vurmak için 'V' , kaçmak için 'K' basınız .");
	                    String match = scanner.nextLine().toUpperCase(Locale.ROOT);
	                    if (match.equals("V")) {
	                        System.out.println(this.getPlayer().getNick() + " " + this.difficulty.getName() + " canavarına  "
	                                + this.getPlayer().getPlayerDamage() + " puan hasar vurdunuz!");
	                        int obsHealth = this.getDifficulty().getHealth() - this.getPlayer().getPlayerDamage();
	                        this.getDifficulty().setHealth(obsHealth);
	                        System.out.println(this.difficulty.getName() + " canavarının canı \u001B[31m" + this.getDifficulty().getHealth() + " kaldı.\u001B[0m");
	                    } else {
	                        System.out.println("\u001B[31mSavaş alanından kaçtınız.\u001B[0m");

	                        return true;
	                    }
	                }
	            }
	            if(this.getDifficulty().getHealth()<=0){
	                isFirst=true;
	                int x=value-i;
	                System.out.println("\u001B[32mKazandınız. Canavar Öldü.Kalan Canavar Sayısı :"+x+"\u001B[0m");

	                    giveMoney(getDifficulty().getAwardMoney());
	                    System.out.println("\u001B[36mÖdülünüz verildi . Bakiyeniz : " + this.getPlayer().getPlayerMoney()+"\u001B[0m");
	                    if(x==0) {
	                        giveItem(this.getAward());
	                        System.out.println("\u001B[35m"+this.getAward() + " ödülünü kazandınız!"+"\u001B[0m");
	                    }

	                if(x!=0){
	                    this.getDifficulty().setHealth(this.getDifficulty().getDefaultHealth());
	                }
	            }
	        }
	        return true;
	    }



	    private int firstShot(){
	        Random r=new Random();
	        //1 çıkarsa canavar, 2 çıkarsa player ilk vursun.
	        int x= r.nextInt(1,3);
	        return x;
	    }

	    private void giveMoney(int awardMoney) {
	        this.getPlayer().getHero().setMoney(this.getPlayer().getPlayerMoney()+awardMoney);
	    }
	    private void giveItem(String award){
	        if(award.equals("food")){
	            getPlayer().getStock().setFood(true);
	        }
	        if(award.equals("water")){
	            getPlayer().getStock().setWater(true);
	        }
	        if(award.equals("wood")){
	            getPlayer().getStock().setWood(true);
	        }
	        if(award.equals("gold")){
	            getPlayer().getStock().setGold(true);
	        }
	    }

	    public int getNumDif() {
	        return numDif;
	    }

	    public void setNumDif(int id) {

	            this.numDif = random.nextInt(1,4);
	    }

	    public String getAward() {
	        return award;
	    }

	    public void setAward(String award) {
	        this.award = award;
	    }

	    public Difficulty getDifficulty() {
	        return difficulty;
	    }

	    public void setDifficulty(Difficulty difficulty) {
	        this.difficulty = difficulty;
	    }
	

}
