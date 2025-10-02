import java.util.Scanner;

// Базовый запечатанный абстрактный класс видов транспорта
sealed abstract class Transport permits Car,  Tractor, Plane, Helicopter, Motorcycle, JetSki, Ship, Snowmobile {
    String transportType;
    String mark;
    String name;
    Engine engine;
    double maxSpeed;
    int seats;

    public Transport(String transportType, String mark, String name, double maxSpeed, Engine engine, int seats) {
        this.transportType = transportType;
        this.mark = mark;
        this.name = name;
        this.engine = engine;
        this.maxSpeed = maxSpeed;
        this.seats = seats;
    }
// Методы запуска и остановки двигателя
    public abstract void start();
    public abstract void stop();

    // Геттеры и сеттеры по полям класса
    public String getMark() { return mark; }
    public void setMark(String mark) { this.mark = mark; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Engine getEngine() { return engine; }
    public void setEngine(Engine engine) { this.engine = engine; }
    public double getMaxSpeed() { return maxSpeed; }
    public void setMaxSpeed(double maxSpeed) { this.maxSpeed = maxSpeed; }

    // Преобразует строку вывода в читаемый формат со всеми полями транспорта
    @Override
    public String toString() {
        return String.format("%s: %s %s, %d мест, макс. скорость %.1f км/ч, топливо %s",
                transportType,    // String
                mark,             // String
                name,             // String
                seats,            // int
                maxSpeed,         // double
                engine.getFuelType().getDescription() // String
        );
    }
}

// Наследники базового класса, описывающие отдельные виды транспорта
// Автомобиль
non-sealed class Car extends Transport {
    public Car(String mark, String name, double maxSpeed, Engine engine, int seats) {
        super("Автомобиль",mark, name, maxSpeed, engine, seats);
    }
    @Override
    public void start () {
        System.out.println("Автомобиль " + mark + " " + name + " запущен");
    }

    @Override
    public void stop () {
        System.out.println("Автомобиль " + mark + " " + name + " остановлен");
    }
}
// Трактор
non-sealed class Tractor extends Transport {
    public Tractor(String mark, String name, double maxSpeed, Engine engine, int seats) {
        super("Трактор",mark, name, maxSpeed, engine, seats);
    }

    @Override
    public void start() {
        System.out.println("Трактор " + getMark() + " " + getName() + " запущен");
    }

    @Override
    public void stop() {
        System.out.println("Трактор " + getMark() + " " + getName() + " остановлен");
    }
}
// Самолёт
non-sealed class Plane extends Transport {
    public Plane(String mark, String name, double maxSpeed, Engine engine, int seats) {
        super("Самолёт",mark, name, maxSpeed, engine, seats);
    }

    @Override
    public void start() {
        System.out.println("Самолёт " + getMark() + " " + getName() + " запущен");
    }

    @Override
    public void stop() {
        System.out.println("Самолёт " + getMark() + " " + getName() + " остановлен");
    }
}
// Вертолёт
non-sealed class Helicopter extends Transport {
    public Helicopter(String mark, String name, double maxSpeed, Engine engine, int seats) {
        super("Вертолёт",mark, name, maxSpeed, engine, seats);
    }

    @Override
    public void start() {
        System.out.println("Вертолёт " + getMark() + " " + getName() + " запущен");
    }

    @Override
    public void stop() {
        System.out.println("Вертолёт " + getMark() + " " + getName() + " остановлен");
    }
}
// Мотоцикл
non-sealed class Motorcycle extends Transport {
    public Motorcycle(String mark, String name, double maxSpeed, Engine engine, int seats) {
        super("Мотоцикл",mark, name, maxSpeed, engine, seats);
    }

    @Override
    public void start() {
        System.out.println("Мотоцикл " + getMark() + " " + getName() + " запущен");
    }

    @Override
    public void stop() {
        System.out.println("Мотоцикл " + getMark() + " " + getName() + " остановлен");
    }
}
// Гидроцикл
non-sealed class JetSki extends Transport {
    public JetSki(String mark, String name, double maxSpeed, Engine engine, int seats) {
        super("Гидроцикл",mark, name, maxSpeed, engine, seats);
    }

    @Override
    public void start() {
        System.out.println("Гидроцикл " + getMark() + " " + getName() + " запущен");
    }

    @Override
    public void stop() {
        System.out.println("Гидроцикл " + getMark() + " " + getName() + " остановлен");
    }
}
// Корабль
non-sealed class Ship extends Transport {
    public Ship(String mark, String name, double maxSpeed, Engine engine, int seats) {
        super("Корабль",mark, name, maxSpeed, engine, seats);
    }

    @Override
    public void start() {
        System.out.println("Корабль " + getMark() + " " + getName() + " запущен");
    }

    @Override
    public void stop() {
        System.out.println("Корабль " + getMark() + " " + getName() + " остановлен");
    }
}
// Снегоход
non-sealed class Snowmobile extends Transport {
    public Snowmobile(String mark, String name, double maxSpeed, Engine engine, int seats) {
        super("Снегоход",mark, name, maxSpeed, engine, seats);
    }
    @Override
    public void start() {
        printMessage("Снегоход ", "запущен");
    }

    @Override
    public void stop() {
        printMessage("Снегоход ", "остановлен");
    }

    private void printMessage(String type, String action) {
        System.out.println(type + " " + getMark() + " " + getName() + " " + action);
    }
}
// Класс двигателя с определением вида топлива через Enum
class Engine {
    private FuelType fuelType;

    public Engine(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public FuelType getFuelType() { return fuelType; }
}
// Виды топлива
enum FuelType {
    PETROL("Бензин"),
    DIESEL("Дизель"),
    ELECTRIC("Электричество"),
    GAS("Газ"),
    KEROSENE("Керосин");

    private String description;

    FuelType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

// Основной рабочий класс
public class DZTransport {
// создаём сканер консоли
    private static Scanner scanner = new Scanner(System.in);
// задаём дефолтный тип топлива
    private static Engine defaultEngine = new Engine(FuelType.PETROL);
// инициируем массив транспорта на 100 штук записей
    private static Transport[] transportList = new Transport[100];
    private static int transportCount = 0;

    public static void main(String[] args) {
 // При инициализации сразу записываем в качестве примера по 1 транспортному средству каждого вида
        transportList[transportCount++] =  new Car("Nissan", "X-Trail", 190.0, defaultEngine, 5);
        transportList[transportCount++] =  new Tractor("МТЗ", "Беларус", 100.0, new Engine(FuelType.DIESEL), 2);
        transportList[transportCount++] =  new Plane("Boeing", "737 MAX", 852.0, new Engine(FuelType.KEROSENE), 162);
        transportList[transportCount++] =  new Helicopter("Robinson", "R44", 240.0, new Engine(FuelType.KEROSENE), 4);
        transportList[transportCount++] =  new Motorcycle("YAMAHA", "R7", 255.0, defaultEngine, 2);
        transportList[transportCount++] =  new JetSki("YAMAHA", "VX HO Cruiser 2024", 105.0, defaultEngine, 2);
        transportList[transportCount++] =  new Ship("Ferretti Yachts", "1000", 51.0, new Engine(FuelType.DIESEL), 14);
        transportList[transportCount++] =  new JetSki("Snowmobile", "RS Venture", 155.0, defaultEngine, 2);

 // флаг для остановки, если пользователь выберет выход
        boolean exit = false;

        // Основной цикл программы
        while (!exit) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить транспортное средство");
            System.out.println("2. Просмотреть все транспортные средства");
            System.out.println("3. Запустить транспортное средство");
            System.out.println("4. Остановить транспортное средство");
            System.out.println("5. Выход");

            // переменная для записи выбранного пользователем пункта меню
            int choice = getIntInput("Выберите пункт меню: ", 1, 5);

// Запуск методов в зависимости от выбранного пользователем пункта меню
            switch (choice) {
                case 1:
                    addTransport();
                    break;
                case 2:
                    showAllTransport();
                    break;
                case 3:
                    startTransport();
                    break;
                case 4:
                    stopTransport();
                    break;
                case 5:
                    exit = true;
                    break;
            }
        }
    }
// Добавляет новый транспорт, пользователь указывая пункты меню задаёт поля транспорта:
// тип и вид топлива - по пунктам меню, марку, название, максимальную скорость, количество посадочных мест - вводит вручную
    private static void addTransport() {
        System.out.println("\nВыберите тип транспортного средства:");
        System.out.println("1. Автомобиль");
        System.out.println("2. Трактор");
        System.out.println("3. Самолёт");
        System.out.println("4. Вертолёт");
        System.out.println("5. Мотоцикл");
        System.out.println("6. Гидроцикл");
        System.out.println("7. Корабль");
        System.out.println("8. Снегоход");

        int type = getIntInput("Введите номер: ", 1, 8);
        String mark = getStringInput("Введите марку: ");
        String name = getStringInput("Введите название модели: ");

        System.out.println("\nВыберите тип топлива:");
        System.out.println("1. Бензин");
        System.out.println("2. Дизель");
        System.out.println("3. Электричество");
        System.out.println("4. Газ");
        System.out.println("5. Керосин");

        int fuelChoice = getIntInput("Введите номер топлива: ", 1, 5);
        FuelType fuelType;

        switch (fuelChoice) {
            case 1: fuelType = FuelType.PETROL; break;
            case 2: fuelType = FuelType.DIESEL; break;
            case 3: fuelType = FuelType.ELECTRIC; break;
            case 4: fuelType = FuelType.GAS; break;
            case 5: fuelType = FuelType.KEROSENE; break;
            default: fuelType = FuelType.PETROL; break;
        }

        Engine engine = new Engine(fuelType);

        double maxSpeed = getDoubleInput("Введите максимальную скорость: ");
        int seats = getIntInput("Введите количество мест: ", 1, 100);

// добавляет в массив новый транспорт с заданными полями
        switch (type) {
            case 1:
                transportList[transportCount++] = new Car(mark, name, maxSpeed, engine, seats);
                break;
            case 2:
                transportList[transportCount++] = new Tractor(mark, name, maxSpeed, engine, seats);
                break;
            case 3:
                transportList[transportCount++] = new Plane(mark, name, maxSpeed, engine, seats);
                break;
            case 4:
                transportList[transportCount++] = new Helicopter(mark, name, maxSpeed, engine, seats);
                break;
            case 5:
                transportList[transportCount++] = new Motorcycle(mark, name, maxSpeed, engine, seats);
                break;
            case 6:
                transportList[transportCount++] = new JetSki(mark, name, maxSpeed, engine, seats);
                break;
            case 7:
                transportList[transportCount++] = new Ship(mark, name, maxSpeed, engine, seats);
                break;
            case 8:
                transportList[transportCount++] = new Snowmobile(mark, name, maxSpeed, engine, seats);
                break;
        }

        System.out.println("Транспортное средство добавлено!");
    }

    // Показывает список транспорта уже занесённого в массив: циклом перебирает все элементы массива и печатает
    private static void showAllTransport() {
        System.out.println("\nСписок транспортных средств:");
        for (int i = 0; i < transportCount; i++) {
            System.out.println((i + 1) + ". " + transportList[i]);
        }
    }
// Запускает выбранный пользователем транспорт,
// если не занесено ещё ни одного транспортного средства, массив транспорта пустой, запускать нечего
    private static void startTransport() {
        if (transportCount == 0) {
            System.out.println("Нет транспортных средств!");
            return;
        }
// пользователь выбирает транспорт для запуска из списка
        showAllTransport();
        int index = getIntInput("Выберите номер транспортного средства для запуска: ", 1, transportCount) - 1;
        transportList[index].start();
    }

   // Останавливает выбранный пользователем из списка транспорт
    private static void stopTransport() {
        if (transportCount == 0) {
            System.out.println("Нет транспортных средств!");
            return;
        }
        showAllTransport();
        int index = getIntInput("Выберите номер транспортного средства для остановки: ", 1, transportCount) - 1;
        transportList[index].stop();
    }

    // Методы для валидации ввода
    // Вввод пользователем пункта меню и количество мест должны быть int
    private static int getIntInput(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine(); // Очищаем буфер
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Ошибка: значение должно быть от " + min + " до " + max);
                }
            } else {
                System.out.println("Ошибка: введите целое число");
                scanner.next(); // Очищаем неверный ввод
            }
        }
    }

    private static int getIntInput(String prompt) {
        return getIntInput(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
// Скорость должна быть числом с плавающей точкой
    private static double getDoubleInput(String prompt) {
        double value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                scanner.nextLine(); // Очищаем буфер
                return value;
            } else {
                System.out.println("Ошибка: введите число");
                scanner.next(); // Очищаем неверный ввод
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}