/*
sealed abstract class Transport permits Car, Tractor, Plane, Helicopter, Motorcycle, JetSki, Ship, Snowmobile {
    private String mark;
    private String name;
    private Engine engine;
    private double maxSpeed;
    private int seats;

    public Transport(String mark, String name, double maxSpeed, Engine engine, int seats) {
        if (maxSpeed <= 0) throw new IllegalArgumentException("Скорость должна быть положительной");
        if (seats <= 0) throw new IllegalArgumentException("Количество мест должно быть положительным");

        this.mark = mark;
        this.name = name;
        this.engine = engine;
        this.maxSpeed = maxSpeed;
        this.seats = seats;
    }

    public abstract void start();
    public abstract void stop();

    // Геттеры и сеттеры
    public String getMark() { return mark; }
    public void setMark(String mark) { this.mark = mark; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Engine getEngine() { return engine; }
    public void setEngine(Engine engine) { this.engine = engine; }
    public double getMaxSpeed() { return maxSpeed; }
    public void setMaxSpeed(double maxSpeed) { this.maxSpeed = maxSpeed; }
    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }

    @Override
    public String toString() {
        return String.format("%s %s: %d мест, макс. скорость %.1f км/ч, топливо %s",
                mark, name, seats, maxSpeed, engine.getFuelType().getDescription());
    }
}

abstract class Vehicle extends Transport {
    protected Vehicle(String mark, String name, double maxSpeed, Engine engine, int seats) {
        super(mark, name, maxSpeed, engine, seats);
    }

    protected void printMessage(String type, String action) {
        System.out.println(type + " " + getMark() + " " + getName() + " " + action);
    }
}

class Car extends Vehicle {
    public Car(String mark, String name, double maxSpeed, Engine engine, int seats) {
        super(mark, name, maxSpeed, engine, seats);
    }

    @Override
    public void start() { printMessage("Автомобиль", "запущен"); }
    @Override
    public void stop() { printMessage("Автомобиль", "остановлен"); }
}

// Аналогично для остальных классов...

class Engine {
    private FuelType fuelType;

    public Engine(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public FuelType getFuelType() { return fuelType; }
}

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

public class Vehicle {
    public static void main(String[] args) {
        Engine engine = new Engine(FuelType.PETROL);
        Car car = new Car("Nissan", "X-Trail", 210.0, engine, 5);

        car.start();
        System.out.println(car);
        car.stop();
    }
}
*/