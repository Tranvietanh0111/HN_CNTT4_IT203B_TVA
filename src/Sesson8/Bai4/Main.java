package Sesson8.Bai4;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(int temperature);
}

interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}

class TemperatureSensor implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void setTemperature(int temperature) {
        System.out.println("Cảm biến: Nhiệt độ = " + temperature);
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

class Fan implements Observer {
    @Override
    public void update(int temperature) {
        if (temperature < 20) {
            System.out.println("Quạt: Nhiệt độ thấp, tự động TẮT");
        } else if (temperature <= 25) {
            System.out.println("Quạt: Nhiệt độ vừa phải, chạy tốc độ trung bình");
        } else {
            System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
        }
    }
}

class Humidifier implements Observer {
    @Override
    public void update(int temperature) {
        System.out.println("Máy tạo ẩm: Điều chỉnh độ ẩm cho nhiệt độ " + temperature);
    }
}

public class Main {
    public static void main(String[] args) {
        TemperatureSensor sensor = new TemperatureSensor();

        Fan fan = new Fan();
        Humidifier humidifier = new Humidifier();

        System.out.println("Đăng ký quạt và máy tạo ẩm");
        sensor.attach(fan);
        System.out.println("Quạt: Đã đăng ký nhận thông báo");
        sensor.attach(humidifier);
        System.out.println("Máy tạo ẩm: Đã đăng ký");

        System.out.println("\nSet nhiệt độ = 18");
        sensor.setTemperature(18);

        System.out.println("\nSet nhiệt độ = 26");
        sensor.setTemperature(26);

        System.out.println("\nSet nhiệt độ = 22");
        sensor.setTemperature(22);
    }
}