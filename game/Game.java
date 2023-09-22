package game;

import java.util.Scanner;

import charcters.Ironman;
import charcters.Hero;
import charcters.Superman;
import charcters.Batman;
import stock.Armor;
import stock.Stock;
import stock.Weapon;
import places.*;
//import places.Hospital;
//import places.Hogwarts;
//import places.Places;
//import places.Gotham;
//import places.Newyork;
//import places.SafeHouse;
//import places.ToolStore;

public class Game {
	Scanner scanner=new Scanner(System.in);

    private Player player;
    private Places places;
    private Weapon weapon=new Weapon(0,"Yok",0,0) ;
    private Armor armor=new Armor(0,"Yok",0,0);
    private Stock stock=new Stock(false,false,false,false,weapon,armor);

    public void start(){
        System.out.println("MACERA OYUNUNA HOŞGELDİNİZ.");
        System.out.println("---------------------------");
        System.out.print("Nickinizi Giriniz : ");
        String nick=scanner.next();
        int heroNumber;
        Hero[] charList={new Ironman(),new Batman(),new Superman()};
        while (true) {
            System.out.println("Karakter Listesi ");
            for (Hero chr : charList){
                System.out.print(chr.getId()+"-"+ chr.getName() + " : Hasar="+chr.getDamage() +" Sağlık="+chr.getHealth()+" Para="+chr.getMoney()+"\n");
            }
            System.out.print("Seçmek istediğiniz karakter numarasını giriniz : ");

            heroNumber = scanner.nextInt();
            if(heroNumber>3 || heroNumber<1){
                System.out.println("Yanlış numara girdiniz lütfen tekrar deneyin.");
            }else{
                break;
            }
        }
        player = new Player(heroNumber,nick,stock);
        player.selectChar();
        while (true){
            System.out.println(player.toString());
            System.out.println("Lokasyon Listesi :");
            System.out.println("-------------------------------------------------------");
            Places[] locations={new SafeHouse(player),new ToolStore(player),
                    new Park(player),new Hogwarts(player),new Newyork(player),new Gotham(player)};
            for(Places loc: locations){
                System.out.println(loc.getId()+"-"+loc.getName());
            }
            System.out.println("7-Envanter");
            System.out.println("9-Oyunu Sonlandır.");
            System.out.print("Gitmek istediğiniz lokasyonun numarasını yazınız : ");
            int locationId=scanner.nextInt();

            if(locationId==9){
                System.out.println("Oyundan çıkış yapılıyor. Tekrar bekleriz!");
                System.exit(0);
            }
            if(!player.selectLocation(locationId)){
                System.out.println("Oyun bitti! Kaybettiniz!");
                break;
            }
        }
    }
}
