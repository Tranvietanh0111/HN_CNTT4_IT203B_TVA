package Sesson8.Bai2;
import java.util.ArrayList;
import java.util.List;

interface TemperatureSensor {
    double getTemperatureCelsius();
}

class OldThermometer {
    public int getTemperatureFahrenheit() {
        return 78;
    }
}

class ThermometerAdapter implements TemperatureSensor {
    private OldThermometer oldThermometer;

    public ThermometerAdapter(OldThermometer oldThermometer) {
        this.oldThermometer = oldThermometer;
    }

    @Override
    public double getTemperatureCelsius() {
        int f = oldThermometer.getTemperatureFahrenheit();
        return (f - 32) * 5.0 / 9.0;
    }
}

class Light {
    public void off() { System.out.println("FACADE: Tắt đèn"); }
}

class Fan {
    public void off() { System.out.println("FACADE: Tắt quạt"); }
    public void setLow() { System.out.println("FACADE: Quạt chạy tốc độ thấp"); }
}

class AirConditioner {
    public void off() { System.out.println("FACADE: Tắt điều hòa"); }
    public void setTemperature(int temp) { System.out.println("FACADE: Điều hòa set " + temp + "°C"); }
}

class SmartHomeFacade {
    private Light light;
    private Fan fan;
    private AirConditioner ac;
    private TemperatureSensor sensor;

    public SmartHomeFacade(Light light, Fan fan, AirConditioner ac, TemperatureSensor sensor) {
        this.light = light;
        this.fan = fan;
        this.ac = ac;
        this.sensor = sensor;
    }

    public void leaveHome() {
        light.off();
        fan.off();
        ac.off();
    }

    public void sleepMode() {
        light.off();
        ac.setTemperature(28);
        fan.setLow();
    }

    public double getCurrentTemperature() {
        return sensor.getTemperatureCelsius();
    }
}

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        OldThermometer oldSensor = new OldThermometer();
        TemperatureSensor adapter = new ThermometerAdapter(oldSensor);

        SmartHomeFacade smartHome = new SmartHomeFacade(light, fan, ac, adapter);

        System.out.println("1. Xem nhiệt độ");
        System.out.printf("Nhiệt độ hiện tại: %.1f°C (chuyển đổi từ %d°F)\n",
                smartHome.getCurrentTemperature(), oldSensor.getTemperatureFahrenheit());

        System.out.println("\n2. Chế độ rời nhà");
        smartHome.leaveHome();

        System.out.println("\n3. Chế độ ngủ");
        smartHome.sleepMode();
    }
}