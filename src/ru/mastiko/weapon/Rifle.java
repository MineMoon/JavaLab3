package ru.mastiko.weapon;

import ru.mastiko.main.Gun;

public class Rifle extends Gun implements Copyable{
    private final int fireSpeed;

    // properties
    public int getFireSpeed() {
        return this.fireSpeed;
    }

    // constructors
    public Rifle() {
        super(30);
        this.fireSpeed = 30;
//        this.reload(30); // в задании не прописано, поэтому в конструкторах не предусмотренно
    }

    public Rifle(int maxAmmo) {
        super(maxAmmo);
        this.fireSpeed = Math.max(1, maxAmmo / 2); // минимум - 1 выстрел в секунду
    }

    public Rifle(int maxAmmo, int fireSpeed) {
        super(maxAmmo);
        if(fireSpeed < 1){
            throw new RuntimeException("Fire Speed cannot be negative and 0");
        }
        this.fireSpeed = fireSpeed;
    }

    // copy constructor
    public Rifle(Rifle copyRifle) {
        super(copyRifle.getMaxAmmo(),copyRifle.ammo());
        this.fireSpeed = copyRifle.getFireSpeed();
    }

    // methods
    @Override
    public Rifle copy(){
        return new Rifle(this);
    }

    @Override
    public void shoot(){
        for(int i = 0; i < this.fireSpeed; i++){
            super.shoot();
        }
    }

    public void shoot(int sec){
        if(sec < 1){
            throw new RuntimeException("Fire Time cannot be negative and 0");
        }
        for(int i = 0; i < sec; i++){
            this.shoot();
        }
    }

    @Override
    public String toString() {
        return String.format("Автомат с %d патронами", this.ammo());
    }



}
