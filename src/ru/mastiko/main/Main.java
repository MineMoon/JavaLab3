package ru.mastiko.main;

import java.util.Scanner;
import ru.mastiko.house.*;
import ru.mastiko.userinput.*;
import ru.mastiko.datastructures.*;
import ru.mastiko.weapon.*;
import ru.mastiko.person.*;
import ru.mastiko.math.Calculator;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserInput input = new UserInput();

        int choice = 2;
        int choiceTask = 0;
        int argument = 0;
        boolean exit = false;

        do{

            if(choice == 2){
                choiceTask = input.inputChoiceInt(1,7, "тему:\n1. Дом\n2. Пистолет\n3. Автомат\n4. Стрелок\n5. Неизменяемый список\n6. Сравнение точек\n7. Возведение в степень\n");
            }

            switch (choiceTask) {

                case 1:
                    System.out.println("Введите кол-во этажей: ");
                    argument = sc.nextInt();
                    House house = new House(argument);
                    System.out.println(house.toString());
                    break;

                case 2:

                    Gun pistol = new Gun(7);
                    System.out.println(pistol.toString());
                    pistol.reload(3); // 3 ammo
                    pistol.shoot();
                    pistol.shoot();
                    pistol.shoot();
                    pistol.shoot(); // 0 ammo
                    pistol.shoot();
                    pistol.reload(8); // 7 ammo
                    System.out.println(pistol.toString());
                    pistol.shoot();
                    pistol.shoot();
                    pistol.unload(); // 0 ammo
                    pistol.shoot();// 0 ammo

                    break;

                case 3:

                    Rifle testRifle = new Rifle(10, 3);
                    testRifle.reload(10);
                    System.out.println(testRifle.toString());
                    testRifle.shoot(); // 3 выстрела
                    System.out.println("Осталось патронов: " + testRifle.ammo());

                    testRifle.shoot(2); // 6 выстрелов (3 × 2 секунды)
                    System.out.println("Осталось патронов: " + testRifle.ammo());

                    testRifle.shoot(); // 1 выстрел + 2 "клац" (т.к. остался 1 патрон)

                    break;
                case 4:

                    System.out.println("=== Тесты Shooter ===\n");

                    // Создаем трех стрелков:
                    // 1. Без оружия
                    Shooter shooter1 = new Shooter("Вася");

                    // 2. С пистолетом
                    Shooter shooter2 = new Shooter("Петя");
                    shooter2.setWeapon(new Gun(12, 5));
                    // пистолет на 12 патронов, заряжено 5

                    // 3. С автоматом
                    Shooter shooter3 = new Shooter("Коля");
                    shooter3.setWeapon(new Rifle(20, 3));
                    // автомат на 20 патронов, скорострельность 3
                    shooter3.reload(7);

                    // Каждый стреляет
                    System.out.println("=== Перестрелка ===");
                    shooter1.shoot(); // без оружия
                    shooter2.shoot(); // с пистолетом
                    shooter3.shoot(3); // с автоматом;

                    // Дополнительная информация
                    System.out.println("\n=== Информация о стрелках ===");
                    System.out.println(shooter1);
                    System.out.println(shooter2);
                    System.out.println(shooter3);
                    break;

                case 5:

                    System.out.println("=== Тесты ImmutableListofValues ===\n");

                    // Создание разными конструкторами
                    ImmutableListofValues list1 = new ImmutableListofValues(1, 2, 3);
                    int[] arr = {4, 5, 6};
                    ImmutableListofValues list2 = new ImmutableListofValues(arr);
                    ImmutableListofValues list3 = new ImmutableListofValues(list1);

                    System.out.println("list1: " + list1);
                    System.out.println("list2: " + list2);
                    System.out.println("list3 (копия list1): " + list3);
                    System.out.println("(как ссылка) list1 == list3: " + (list1 == list3));
                    System.out.println();

                    // Тест методов
                    System.out.println("list1.get(0) = " + list1.get(0));
                    System.out.println("list1.size() = " + list1.size());
                    System.out.println("list1.isEmpty() = " + list1.isEmpty());
                    System.out.println();

                    // Тест set()
                    ImmutableListofValues modified = list1.set(1, 99);
                    System.out.println("После list1.set(1, 99): " + modified + " (копия)");
                    System.out.println("Оригинал list1 не изменился: " + list1);
                    System.out.println();

                    // Тест исключений
                    try {
                        list1.get(10);
                    } catch (RuntimeException e) {
                        System.out.println("Исключение при get(10): " + e.getMessage());
                    }

                    break;

                case 6:

                    System.out.println("=== Тесты сравнение точек ===\n");

                    Coordinate coor1 = new Coordinate(4, 5);
                    Coordinate coor2 = new Coordinate(4, 5);
                    Coordinate coor3 = new Coordinate(3, 8);

                    System.out.println("coor1: " + coor1.toString());
                    System.out.println("coor2: " + coor2.toString());
                    System.out.println("coor3: " + coor3.toString());
                    System.out.println();

                    System.out.println(String.format("coord1 == coord2: %b (как ссылки)", coor1 == coor2));
                    System.out.println(String.format("coord1 == coord2: %b (как точки)", coor1.equals(coor2)));

                    System.out.println(String.format("coord1 == coord3: %b (как точки)", coor1.equals(coor3)));

                    break;

                case 7:

                    System.out.println("=== Тест возведения в степень ===\n");
                    if(args.length > 1) {
                        int x = 0;
                        int y = 0;

                        try {
                            x = Integer.parseInt(args[0]);
                            y = Integer.parseInt(args[1]);
                        } catch (NumberFormatException e) {
                            throw new NumberFormatException("Incorrect number format");
                        }
                        System.out.println(String.format("x = %d, y = %d", x, y));

                        double result = Calculator.power(args[0], args[1]);
                        if (result == (int) result) {
                            System.out.print(String.format("(%d) ^ (%d) = %d", x, y, (int) result));
                        } else {
                            System.out.print(String.format("(%d) ^ (%d) = %.3f", x, y, result));
                        }
                        System.out.println();
                    } else {
                        System.out.println("Передано недостаточно аргументов.");
                    }
                    break;
            }
            System.out.println();

            System.out.println("Повторить?\n1. Да\n2. К выбору задания\n3. Выход");
            choice = input.inputDiaposonInt(1, 3, "вариант");
            if(choice == 3){
                exit = true;
            }
            System.out.println();

        }while (!exit);
    }

}
