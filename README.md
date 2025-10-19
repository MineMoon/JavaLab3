# Лабораторная работа Java №3:
Данный проект представляет собой решение лабораторной работы №3 Java, вариант 6.
# Содержание
1. [Содержание](#содержание)
2. [Структура проекта](#структура-проекта)
3. [Как работает основной код в Main](#как-работает-основной-код-в-main)
4. [Пакет datastructures](#пакет-datastructures)
   - [Класс Coordinate](#класс-сoordinate)
   - [Класс ImmutableListofValues](#класс-immutableListofValues)
5. [Пакет house](#пакет-house)
   -  [Класс House](#класс-house)
6. [Пакет math](#пакет-math)
   -  [Класс Calculator](#класс-Calculator)
7. [Пакет weapon + класс Gun](#пакет-weapon-+-класс-Gun)
   -  [Абстрактный класс Weapon](#абстрактный-класс-Weapon)
   -  [Интерфейс Copyable](#интерфейс-copyable)
   -  [Класс Gun](#класс-gun)
   -  [Класс Rifle](#класс-rifle)
8. [Пакет person](#пакет-person)
   - [Класс Shooter](#класс-Shooter)
8. [Пакет main](#пакет-main)
8. [Пакет userinput](#пакет-userinput)
   - [Класс UserInput](#класс-userinput)
   - [Класс Check](#класс-check)

# Структура проекта
Проект состоит из следующих классов:

Пакет ru солдержит пакет mastiko, который уже содержит пакеты с классами и main

 - datastructures 
   - Coordinate
   - ImmutableListofValues
 - house 
   - House
 - main 
   - Main 
   - Gun(перемещен в main по заданию)
 - math
   - Calculator
 - person
   - Shooter
 - userinput
   - Check
   - UserInput
 - weapon
   - Copyable
   - Rifle
   - Weapon

# Как работает основной код в Main
```java
do{
    if(choice == 2){
        choiceTask = input.inputChoiceInt(1,7, "тему:\n1. Дом\n2. Пистолет\n3. Автомат\n4. Стрелок\n...");
    }

    switch (choiceTask) {
        case 1: // Работа с House
        case 2: // Работа с Gun  
        case 3: // Работа с Rifle
        case 4: // Работа с Shooter
        case 5: // Работа с ImmutableListofValues
        case 6: // Работа с Coordinate
        case 7: // Работа с Calculator
    }
    
    System.out.println("Повторить?\n1. Да\n2. К выбору задания\n3. Выход");
    choice = input.inputDiaposonInt(1, 3, "вариант");
    if(choice == 3){
        exit = true;
    }
} while (!exit);
```
# Пакет datastructures

## Класс Coordinate
### Назначение: 
Представляет точку на плоскости с координатами (x, y).

### Конструкторы

 - public Coordinate(int x, int y) - создает точку(координату), назначая x и y.

 - public Coordinate(Coordinate coordinate) - создает точку, копируя значения из другого объекта

### Метод equals
```java
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
```

Сперва сравниват объект с самим собой -> проверяет является ли объект экземпляром класса Coordinate 
-> если да, то сравниваем по x и y, если нет, то объекты разные(нельзя их сравнить) => возвращаем false

![Coordinate](https://github.com/MineMoon/JavaLab3/blob/main/PNGFR/Coordinate1.png)

## Класс ImmutableListofValues
### Назначение:
Класс описывает обертку над массивом целых чисел 

### Конструкторы

 - public  ImmutableListofValues(int... numbers) - может создавать массив как из прямого перечня, 
так и с указанием в виде массива целых чисел.

 - public ImmutableListofValues(List<Integer> numbers) - создает массив из списка.

 - public ImmutableListofValues(ImmutableListofValues array) - создает массив из копии того же типа, 
все элементы из array копируются

### Оссобенности реализации
#### Метод get(int index)
```java
 public int get(int index) {
   if (index < 0 || index >= this.array.length) {
      throw new RuntimeException("Index out array's bounds");
   }
   return array[index];
}
```

Проверяет index на корректность, возвращает нужный элемент.

**Примечание**: можно было возвращать копию на объект, но эта реализация излишне, 
поскольку идет работа с базовым типом int, который возвращается копией.

#### Метод set(int index, int value)
```java
  public ImmutableListofValues set(int index, int value) {
    if (index < 0 || index >= array.length) {
        throw new RuntimeException("Index out array's bounds");
    }
    int[] newArray = array.clone();
    newArray[index] = value;
    return new ImmutableListofValues(newArray);
}
```
Описание задания не давало конкретный ответ на вопрос: изменяемый массив или нет.
Поэтому было принято решение - в set возвращать копию массива, в котором изменялся нужный эл-нт.

![ImmutableListofValues](https://github.com/MineMoon/JavaLab3/blob/main/PNGFR/ImmutableListofValues1.png)

#### Остальные методы

- isEmpty() - проверка на пустоту.
- size() - возвращает размер массива.
- toString() - реализован через метод toString, принадлежащим, классу Arrays. 

# Пакет house
## Класс House

### Назначение
Описывает сущность дом

### Конструкторы
- `House(int floor)` - создает дом с указанным количеством этажей
- `House(House house)` - конструктор копирования

### Особенности реализации:
- Поле `floor` объявлено как `final` - нельзя изменить после создания
- Валидация в конструкторе: этажи должны быть в диапазоне 0-164, если кол-во этажей отрицательное - выкидывает exeption, 
если > 164, то floor = 1
- Автоматическое определение правильного окончания для слова "этаж"

![House](https://github.com/MineMoon/JavaLab3/blob/main/PNGFR/House1.png)
![House](https://github.com/MineMoon/JavaLab3/blob/main/PNGFR/House2.png)

Подробнее с описанием этого класса можно ознакомиться в отчете предыдущей лабораторной работы:

https://github.com/MineMoon/JavaLab2

# Пакет math
## Класс Calculator
### Назначение
Пока содержит только статичный метод power - возведение в степень.
### Метод Power
Статический метод, вычисляющий возведение x в степень y. Принимает две строки
```java
  public static double power(String xStr, String yStr) {
   int x;
   int y;
   try {
       x = parseInt(xStr);
       y = parseInt(yStr);
   } catch (NumberFormatException e) {
       throw new NumberFormatException("Unreadable argument format");
   }
   return pow(x, y);
}
```
Введена проверка на корректность ввода строк xStr, yStr. В случае если строки xStr, yStr
не являются числам(в них записаны не числа), выкидывается соответсвующий exeption.

Также в класс были специально статично импортированы методы Integer.parseInt и Math.pow, 
что позволяет использовать их без объявление класса:
```java
  // Вместо
    int x = Integer.parseInt(xStr);
    return Math.pow(x, y);
    
  // Можно так
    int x = parseInt(xStr);
    return pow(x, y);
}
```

**Примечание**: метод power можно использовать **не создавая** объект класса Calculator.

![Pow](https://github.com/MineMoon/JavaLab3/blob/main/PNGFR/Pow1.png)

# Пакет weapon + класс Gun
## Абстрактный класс Weapon
Дан по заданию
### Назначение
Базовый класс для всех видов оружия.
### Конструкторы 
- Weapon(int ammo) — инициализирует оружие указанным количеством патронов. 
```java
public Weapon(int ammo) {
   if (ammo < 0) {
      throw new RuntimeException();
   }
   this.ammo = ammo;
}
```
Также осуществляет проверку на отрицательное кол-во патронов

### Основные методы
- abstract void shoot() — абстрактный метод для выстрела.
- ammo() — возвращает текущее количество патронов.
- getAmmo() — уменьшает количество патронов на 1.
- load(int ammo) — заряжает оружие определенным кол-во патронов, 
возвращает предыдущее кол-во патронов до зарядки.

## Интерфейс Copyable
### Назначение
Имплиментируется в классах Gun и Rifle, для работы метода getWeapon в классе Shooter (см. раздел с классом Shooter)

```java
public interface Copyable {
    Weapon copy();
}
```
## Класс Gun
Наследуется от абстрактоного класса Weapon, также имплиментируется от интерфейса Copyable
### Назначение
Описывает сущность пистолет
### Поля
Добавляется новое поле maxAmmo - максимальное кол-во патронов в оружии(магазине)
### Конструкторы 
- public Gun()
```java
public Gun() {
   super(5);
   this.maxAmmo = 12;
}
```
Вызывает конструктор класса родителя, создавая пистолет с 5 патронами, 
затем назначает максимальную вместимость = 12 патронов. (5, 12 - значения по умолчанию, при создании без аргументов)

- public Gun(int maxAmmo)
```java
public Gun(int maxAmmo) {
   super(0);
   if(maxAmmo < 1){
      throw new RuntimeException("Max Ammo cannot be negative and 0");
   }
   this.maxAmmo = maxAmmo;
}
``` 
Сперва создается с помощью конструктора класса родителя, создает пистолет с 0 патронами, 
затем проверяет на отрицательное и нулевое значение maxAmmo, и если exeption не был выкинут, 
значит все хорошо, можно назначить maxAmmo.

- public Gun(int maxAmmo, int ammo)
```java
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
   // если загружено больше чем maxAmmo, зарядится max ammo
}
```
- public Gun(Gun copyGun) - создает, копируя значения из другого объекта.

### Основные методы

- shoot() — выстрел, если есть патроны.
- reload(int ammo) — перезарядка, возвращает лишние патроны.
- unload() — разряжает пистолет, возвращает количество выгруженных патронов.
- toString - возвращает информацию о пистолете.


- copy() — создаёт копию пистолета.(обязан быть по контракту с Copyable)
```java
@Override
public Gun copy() {
   return new Gun(this);
}
```

![Gun](https://github.com/MineMoon/JavaLab3/blob/main/PNGFR/Gun1.png)

## Класс Rifle
Наследуется от абстрактоного Gun, также имплиментируется от интерфейса Copyable
### Назначение
Описывает сущность автомат
### Поля
По мимо полей, наследуемых от Gun(ammo, maxAmmo), 
хранит неизменяемое(final) поле fireSpeed - скорострельность.
### Конструкторы
- public Rifle() - создает автомат с значениями по умолчанию: 
0 патронов, 30 - max, 30 - скорострельность

- public Rifle(int maxAmmo)
```java
public Rifle(int maxAmmo) {
   super(maxAmmo);
   this.fireSpeed = Math.max(1, maxAmmo / 2); // минимум - 1 выстрел в секунду
}
```
Использует конструктор класса родителя, fireSpeed задается след. правилом:
половина от кол-во максимальной вместимости.

**Примечание:** Так как, в конструкторе класса родителя уже делается проверка 
на отрицательное значение maxAmmo, то в этом конструкторе эта проверка не требуется.

- public Rifle(int maxAmmo, int fireSpeed) - проверяет и задает значения maxAmmo.
- public Rifle(Rifle copyRifle) - создает автомат, копируя значения из другого объекта.

### Основные методы

- shoot() - стреляет, при чем кол-во выстрелов равно скорострельности.
```java
   rifle.shoot(); // (fireSpeed = 3)
output:
    БАХ!
    БАХ!
    БАХ!
```

- shoot(int sec) - стреляет несколько секунд, соответсвенно
кол-во выстрелов равно скорострельности, помноженное на кол-во секунд.
```java
   rifle.shoot(2); // (fireSpeed = 2)
output:
    БАХ!
    БАХ!
    БАХ!
    БАХ!
```

- toString() - возвращает иформацию об автомате.

![Rifle](https://github.com/MineMoon/JavaLab3/blob/main/PNGFR/Rifle1.png)

# Пакет person
## Класс Shooter
Описывает сущность стрелок
### Поля
- String name - имя
- Weapon weapon - ссылка на оружие

### Метод getWeapon и смысл интерфейса Copyable
```java
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
```

Во всех геттерах этого проекта возвращается не прямая ссылка на объект, а его копия.
Но к сожалению, в абстрактном классе Weapon(который нам был дан заданием) не было прописано 
абстрактного метода copy(). Поэтому было принято решение - создать интерфейс Copyable, от которого
будут имплиментироваться классы оружия.

Как это работает в геттере: если оружие данного стрелка было имплиментированно от интерфейса Copyable
(то есть может создавать свою копию), то мы вызываем метод copy у оружия, и возвращаем копию.
Если же такого метода класс не имеет, то возвращаем null.

### Методы
- Shoot() - стреляет из своего оружия(если оно есть)

- Shoot(int sec) - стреляет определенное кол-во секунд, если оружя нет,
то выводится - "name: не могу участвовать в перестрелке!"
```java
public void shoot(int sec){
   if (this.weapon == null) {
      System.out.println(String.format("%s: не могу участвовать в перестрелке!",this.getName()));
   } else if (this.weapon instanceof Rifle secShootableWeapon){
      System.out.print(name + ": ");
      secShootableWeapon.shoot(sec);
   }
}
```
С помощью instanceof проверяем, может ли оружие стрелять опред. кол-во секунд
(то есть было наследуемо от класса Rifle).

- reload(int bullets) - перезаряжает оружие, заряжая определенное кол-во патронов, если кол-во патронов
было больше чем может поместится, то возвращается остаток
```java
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
```
С помощью instanceof проверяем, может ли оружие перезяряжаться
(то есть было наследуемо от класса Gun).

- unload() - разряжает оружие, возвращая патроны.
```java
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
```
С помощью instanceof проверяем, может ли оружие разряжаться
(то есть было наследуемо от класса Gun).
-toString() - возращает информацию о стрелке

![Person](https://github.com/MineMoon/JavaLab3/blob/main/PNGFR/Shooter1.png)

# Пакет main
Содержит Main.java - точка входа в программу и класс Gun.
**Примечание:** класс Gun был перемещен из пакета weapon в пакет main по заданию.
# Пакет userinput
## Класс UserInput
Обеспечивает безопасный ввод данных с консоли:
- `inputInt()`, `inputPositiveInt()` - ввод чисел
- `inputDiaposonInt()` - ввод числа в диапазоне
- `inputChoiceInt()` - ввод выбора из меню
- `inputString()` - ввод строки

![UserInput](https://github.com/MineMoon/JavaLab3/blob/main/PNGFR/Input1.png)
![UserInput](https://github.com/MineMoon/JavaLab3/blob/main/PNGFR/Input2.png)
![UserInput](https://github.com/MineMoon/JavaLab3/blob/main/PNGFR/Input3.png)
  
## Класс Check
Содержит методы валидации:
- `isInteger(String str)` - проверка целого числа
- `Positive(int num)` - проверка неотрицательности



