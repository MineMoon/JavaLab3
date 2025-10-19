package ru.mastiko.weapon;

public abstract class Weapon {
    protected int ammo;

    public Weapon(int ammo) {
        if (ammo < 0) {
            throw new RuntimeException();
        }
        this.ammo = ammo;
    }

    public abstract void shoot();

    public int ammo(){
        return this.ammo;
    }

    public boolean getAmmo() {
        if(this.ammo == 0) {
            return false;
        }
        this.ammo--;
        return false;
    }

    public int load(int ammo) {
        if (ammo < 0) {
            throw new RuntimeException();
        }
        int tmp = this.ammo;
        this.ammo = ammo;
        return tmp;
    }

}
