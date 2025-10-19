package ru.mastiko.person;
import ru.mastiko.main.Gun;
import ru.mastiko.weapon.*;

public class Shooter {
    private String name;
    private Weapon weapon;

    // properties
    public String getName() {
        return new String(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon(){
        if(this.weapon ==null){
            return null;
        }

        // если будет какой-либо класс наследуемый от Weapon,
        // но не имеющий метод copy, то просто пропустит логику в if и вернет null
        if(this.weapon instanceof Copyable copyableWeapon){ // приведедение типа
            return copyableWeapon.copy();
        }

        return null;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    // constructors
    public Shooter(String name){
        this.setName(name);
        this.setWeapon(null);
    }

    public Shooter(String name, Weapon weapon){
        this.setName(name);
        this.setWeapon(weapon);
    }

    // copy constructor
    public Shooter(Shooter shooter){
        this.setName(shooter.getName());
        this.setWeapon(shooter.getWeapon());
    }

    // methods
    public void shoot() {
        if (this.weapon == null) {
            System.out.println(String.format("%s: не могу участвовать в перестрелке!",this.getName()));
        } else {
            System.out.print(this.getName() + ": ");
            this.weapon.shoot();
        }
    }

    public void shoot(int sec){
        if (this.weapon == null) {
            System.out.println(String.format("%s: не могу участвовать в перестрелке!",this.getName()));
        } else if (this.weapon instanceof Rifle secShootableWeapon){
            System.out.print(name + ": ");
            secShootableWeapon.shoot(sec);
        }
    }

    public int reload(int bullets) {
        int over = 0;
        if (this.weapon == null) {
            System.out.println(String.format("%s: нет оружия для перезарядки!",this.getName()));
        } else if (this.weapon instanceof Gun reloadableWeapon) {
            over = reloadableWeapon.reload(bullets);
            if (over > 0) {
                System.out.println(String.format("%s: вернул %d лишних патронов",this.getName(), over));
            }
        } else {
            System.out.println(String.format("%s: это оружие нельзя перезарядить",this.getName()));
        }
        return over;
    }

    public int unload() {
        int unloadedAmmo = 0;
        if (this.weapon == null) {
            System.out.println(String.format("%s: нет оружия для разрядки!",this.getName()));;
        } else if (this.weapon instanceof Gun unloadableWeapon) {
            unloadedAmmo = unloadableWeapon.unload();
            System.out.println(String.format("%s: разрядил %d патронов",this.getName(), unloadedAmmo));;
        } else {
            System.out.println(String.format("%s: это оружие нельзя разрядить!",this.getName()));
        }
        return unloadedAmmo;
    }

    @Override
    public String toString() {
        if(this.weapon!=null){
            return String.format("%s вооружен (%s)",this.getName(),this.getWeapon().toString());
        } else{
            return String.format("%s не вооружен.",this.getName());
        }

    }



}



