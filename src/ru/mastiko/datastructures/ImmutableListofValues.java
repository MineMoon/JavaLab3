package ru.mastiko.datastructures;

import java.util.Arrays;
import java.util.List;

public class ImmutableListofValues {
    private final int[] array;

    // properties
    public int[] getArray() {
        return array.clone();
    }

    // constructors
    public  ImmutableListofValues(int... numbers) {
        this.array = numbers.clone(); // тут надо копию
    }

    public ImmutableListofValues(List<Integer> numbers) {
        this.array = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            // создаем копию каждого эл-та numbers(int - базовый тип + автораспаковка Integer -> int )
            this.array[i] = numbers.get(i);
        }
    }

    // copy constructor
    public ImmutableListofValues(ImmutableListofValues array) {
        this.array = new int[array.getArray().length];
        for (int i = 0; i < array.getArray().length; i++) {
            this.array[i] = array.getArray()[i];
        }
    }

    // methods
    public int get(int index) {
        if (index < 0 || index >= this.array.length) {
            throw new RuntimeException("Index out array's bounds");
        }
        return array[index];
    }

    public ImmutableListofValues set(int index, int value) {
        if (index < 0 || index >= array.length) {
            throw new RuntimeException("Index out array's bounds");
        }
        int[] newArray = array.clone();
        newArray[index] = value;
        return new ImmutableListofValues(newArray);
    }

    public boolean isEmpty() {
        return array.length == 0;
    }

    public int size() {
        return array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
