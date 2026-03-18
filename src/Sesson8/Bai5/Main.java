package Sesson8.Bai5;

import java.util.ArrayList;
import java.util.List;

// --- INTERFACES ---
interface Command {
    void execute();
}

interface Observer {
    void update(int temperature);
}

interface Subject {
    void attach(Observer o);
    void notifyObservers();
}

// --- DEVICES (RECEIVERS & OBSERVERS) ---
class Light {
    public void off() { System.out.println("Đèn: Tắt"); }
}

class Fan implements Observer {
    private String speed = "Tắt";

    public void setSpeed(String speed) {
        this.speed = speed;
        System.out.println("Quạt: Chạy tốc độ " + speed);
    }

    @Override
    public void update(int temperature) {
        if (temperature > 30) {
            setSpeed("Mạnh");
        }
    }
}

class AirConditioner implements Observer {
    private int temperature = 25;

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    @Override
    public void update(int temperature) {
        if (temperature > 30) {
            System.out.println("Điều hòa: Nhiệt độ phòng quá cao (" + temperature + "°C), duy trì làm mát tối đa.");
        }
    }
}

// --- SUBJECT ---
class TemperatureSensor implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int currentTemp;

    public void setTemperature(int temp) {
        System.out.println("\nCảm biến: Nhiệt độ = " + temp);
        this.currentTemp = temp;
        notifyObservers();
    }

    @Override
    public void attach(Observer o) { observers.add(o); }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) o.update(currentTemp);
    }
}

// --- COMMAND IMPLEMENTATION ---
class SleepModeCommand implements Command {
    private Light light;
    private AirConditioner ac;
    private Fan fan;

    public SleepModeCommand(Light light, AirConditioner ac, Fan fan) {
        this.light = light;
        this.ac = ac;
        this.fan = fan;
    }

    @Override
    public void execute() {
        System.out.println("SleepMode: Tắt đèn");
        light.off();
        System.out.println("SleepMode: Điều hòa set 28°C");
        ac.setTemperature(28);
        System.out.println("SleepMode: Quạt tốc độ thấp");
        fan.setSpeed("Thấp");
    }
}

// --- CLIENT ---
public class Main {
    public static void main(String[] args) {
        Light bedRoomLight = new Light();
        AirConditioner ac = new AirConditioner();
        Fan fan = new Fan();
        TemperatureSensor sensor = new TemperatureSensor();

        sensor.attach(ac);
        sensor.attach(fan);

        Command sleepMode = new SleepModeCommand(bedRoomLight, ac, fan);

        System.out.println("1. Kích hoạt chế độ ngủ");
        sleepMode.execute();

        System.out.println("\n2. Nhiệt độ tăng lên 32°C (giả lập)");
        sensor.setTemperature(32);

        System.out.println("\n3. Nhiệt độ giảm về 25°C (giả lập)");
        sensor.setTemperature(25);
    }
}
