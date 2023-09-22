package places;

import game.Player;
import stock.*;

public class ToolStore extends NormalPlace {
    public ToolStore(Player player) {
        super(2,"Mağaza", player);
    }
    @Override
    public boolean onPlaces() {
        System.out.println("Mağazaya hoşgeldiniz.");
        menu();
        return true;
    }
    public void menu(){
        System.out.println("Mağazadan mevcut olan ekipmanlar aşağıdaki gibidir.");
        System.out.println("1-SİLAH\n2-ZIRH\n3-ÇIKIŞ YAP");
        System.out.print("Seçiminiz :");
        int equipment= Places.scanner.nextInt();
        switch (equipment){
            case 1:
                getWeaponList();
                break;
            case 2:
                getArmorList();
                break;
            case 3:
            default:
                System.out.println("Çıkış yapılıyor.");
                break;
        }

    }

    private void getWeaponList() {
        Weapon[] weapons = {new Gun(), new Knife(), new Bomb()};

        for (Weapon weapon : weapons) {
            System.out.println(weapon.getId() + "-" + weapon.getName() + " -->  " +
                    "Hasarı : " + weapon.getDamage() + " Ücreti : " + weapon.getMoney());
        }
        System.out.println("Mevcut Bakiyeniz : " + getPlayer().getHero().getMoney());
        System.out.print("Almak istediğiniz silahın numarasını yazınız (Çıkış yapmak için 9 yazın) : ");
        int number = scanner.nextInt();
        while (number < 1 || number > weapons.length) {
            if (number == 9) {
                System.out.println("Çıkış yapılıyor.");
                break;
            }
            System.out.print("Geçersiz değer girdiniz, tekrar girin :");
            number = scanner.nextInt();
        }
        if (number != 9) buyWeapon(number);


    }

    private void buyWeapon(int number){
        Weapon[] weapons={new Gun(),new Knife(),new Bomb()};
        if(getPlayer().getStock().getWeapon().getName()==weapons[number-1].getName()){
            System.out.println("Bu silah envanterinizde mevcut.");
        }
        else if(getPlayer().getHero().getMoney()>=weapons[number-1].getMoney()){
            int balance=getPlayer().getHero().getMoney()-weapons[number-1].getMoney();
            getPlayer().getHero().setMoney(balance);
            getPlayer().getStock().setWeapon(weapons[number-1]);
            System.out.println("Silah alındı.");
            int newDamage=getPlayer().getHero().getDefaultDamage()+weapons[number-1].getDamage();
            getPlayer().getHero().setDamage(newDamage);
            System.out.println("Mevcut Bakiyeniz :"+getPlayer().getHero().getMoney());
            System.out.println("Silahınız :"+getPlayer().getStock().getWeapon().getName()+
                    "\nHasarı : "+getPlayer().getStock().getWeapon().getDamage()+
                    "\nKarakterinizin hasarı : "+getPlayer().getHero().getDamage());
        }
        else {
            System.out.println("Almak istediğiniz silaha paranız yetmemektedir!");
            System.out.println("Mevcut Bakiyeniz : "+getPlayer().getHero().getMoney()+ " Silahın Parası : "+weapons[number-1].getMoney());
        }
    }

    private void getArmorList() {

        for (Armor armor: Armor.armorList()){
            System.out.println(armor.getId()+"-"+armor.getName()+" -->  " +
                    "Savunması : "+armor.getDefence()+" Ücreti : "+armor.getMoney());
        }
        System.out.println("Bakiyeniz :"+this.getPlayer().getPlayerMoney());
        System.out.print("Almak istediğiniz zırhın numarasını yazınız(Çıkış için 9'a basın) : ");
        int number=scanner.nextInt();
        while (number<1 || number> Armor.armorList().length){
            if(number==9){
                System.out.println("Çıkış yapılıyor.");
                break;
            }
            System.out.print("Geçersiz değer girdiniz, tekrar girin :");
            number=scanner.nextInt();
        }
        if(number!=9) getPlayer().getStock().setArmor(buyArmor(number));
    }

    private Armor buyArmor(int number){
        Armor selectedArmor=Armor.getArmorById(number);
        if(this.getPlayer().getStock().getArmor().getId()==selectedArmor.getId()){
            System.out.println("Bu zırh envanterinizde mevcuttur.");
            selectedArmor=getPlayer().getStock().getArmor();
        }
        else if(getPlayer().getHero().getMoney()< selectedArmor.getMoney()){
            System.out.println("Bakiyeniz yetersiz. Zırhı alamazsınız.");
            selectedArmor=getPlayer().getStock().getArmor();
        }
        else {
            getPlayer().getStock().setArmor(selectedArmor);
            int balance=getPlayer().getHero().getMoney()- selectedArmor.getMoney();
            getPlayer().getHero().setMoney(balance);
            System.out.println("Zırhı aldınız.");
            System.out.println("Mevcut bakiyeniz : "+getPlayer().getHero().getMoney());
            System.out.println("Zırh savunması :"+getPlayer().getStock().getArmor().getDefence());

        }
        return selectedArmor;
    }
}

