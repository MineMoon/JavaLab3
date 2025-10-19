package ru.mastiko.datastructures;


public class Coordinate {

    private int x;
    private int y;

    // properties
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // constructors
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // copy constructor
    public Coordinate(Coordinate coordinate) {
        this.x = coordinate.getX();
        this.y = coordinate.getY();
    }

    // methods
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj instanceof Coordinate coord){
            return this.x == coord.getX() && this.y == coord.getY();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("{%d;%d}", this.x, this.y);
    }
}
