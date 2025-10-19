package ru.mastiko.main;

import ru.mastiko.weapon.Copyable;
import ru.mastiko.weapon.Weapon;

public class Gun extends Weapon implements Copyable {

    private final int maxAmmo;

    // properties
    private void setAmmo(int ammo) { // нужен ли он вообще (заменяется методами load, reload, unload).
        if (ammo < 0) {
            throw new RuntimeException("Ammo cannot be negative");
        }
        this.load(Math.min(ammo, this.getMaxAmmo()));
    }

    public int getMaxAmmo() {
        return this.maxAmmo;
    }

    // getAmmo - выбрасывет 1 патрон, прописан в Weapon.
    // ammo() - уже возвращает кол-во патронов, прописан в Weapon

    // constructors
    public Gun() {
        super(5);
        this.maxAmmo = 12;
    }

    public Gun(int maxAmmo) {
        super(0);
        if(maxAmmo < 1){
            throw new RuntimeException("Max Ammo cannot be negative and 0");
        }
        this.maxAmmo = maxAmmo;
    }

    public Gun(int maxAmmo, int ammo) {
        super(0);
        if(maxAmmo < 1){
            throw new RuntimeException("Max Ammo cannot be negative and 0");
        }
        if(ammo < 0){
            throw new RuntimeException("Ammo cannot be negative");
        }
        this.maxAmmo = maxAmmo;
        this.load(Math.min(ammo, this.getMaxAmmo()));
    }

    // copy constructor
    public Gun(Gun copyGun) {
        super(copyGun.ammo());
        this.maxAmmo = copyGun.getMaxAmmo();
    }

    // methods
    @Override
    public Gun copy() {
        return new Gun(this);
    }

    @Override
    public void shoot() {
        if (ammo() > 0) {
            this.getAmmo();
            System.out.println("БАХ!");
        } else {
            System.out.println("Клац...");
        }
    }

    @Override
    public int load(int ammo){
        if (ammo < 0) {
            throw new RuntimeException("Ammo cannot be negative");
        }
        int tmp = this.ammo();
        this.ammo = Math.min(ammo, this.getMaxAmmo());
        return tmp;
    }

    public int reload(int ammo) {
        System.out.println("ПЕРЕЗАРЯДКА!!!");

        if(ammo < 0){
            throw new RuntimeException("Ammo cannot be negative");
        }

        int over = 0;
        if((this.ammo() + ammo) > this.getMaxAmmo()){
            over = (this.ammo() + ammo) - this.getMaxAmmo();
            this.load(this.getMaxAmmo());
        } else {
            this.load(this.ammo() + ammo);
        }

        return over;
    }

    public int unload() {
        int unloadedAmmo = this.ammo;
        this.ammo = 0;
        return unloadedAmmo;
    }

    public boolean isCharged() {
        return this.ammo() > 0;
    }

    @Override
    public String toString() {
        return String.format("Пистолет с %d патронами", this.ammo());
    }
}