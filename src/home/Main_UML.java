/*
@startuml
skinparam packageStyle rectangle
skinparam classStyle rectangle

abstract class Transport {
    + String transportType
    + String mark
    + String name
    + Engine engine
    + double maxSpeed
    + int seats
    
    + Transport(String, String, String, double, Engine, int)
    + start(): void
    + stop(): void
    + getMark(): String
    + setMark(String): void
    + getName(): String
    + setName(String): void
    + getEngine(): Engine
    + setEngine(Engine): void
    + getMaxSpeed(): double
    + setMaxSpeed(double): void
    + toString(): String
}

class Engine {
    - FuelType fuelType
    
    + Engine(FuelType)
    + getFuelType(): FuelType
}

enum FuelType {
    PETROL
    DIESEL
            ELECTRIC
    GAS
            KEROSENE
    
    + getDescription(): String
    }

class Car
class Tractor
class Plane
class Helicopter
class Motorcycle
class JetSki
class Ship
class Snowmobile

Car --|> Transport
Tractor --|> Transport
Plane --|> Transport
Helicopter --|> Transport
Motorcycle --|> Transport
JetSki --|> Transport
Ship --|> Transport
Snowmobile --|> Transport

Transport "1" -- "1" Engine : имеет

@enduml
*/