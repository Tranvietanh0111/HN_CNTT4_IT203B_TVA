package Sesson8.Bai1;

import java.util.*;

// --- SINGLETON PATTERN ---
class HardwareConnection {
    private static HardwareConnection instance;
    private boolean isConnected = false;

    private HardwareConnection() {}

    public static HardwareConnection getInstance() {
        if (instance == null) {
            instance = new HardwareConnection();
        }
        return instance;
    }

    public void connect() {
        if (!isConnected) {
            System.out.println("HardwareConnection: Đã kết nối phần cứng.");
            isConnected = true;
        }
    }

    public void disconnect() {
        if (isConnected) {
            System.out.println("HardwareConnection: Đã ngắt kết nối.");
            isConnected = false;
        }
    }
}

// --- DEVICE INTERFACE ---
interface Device {
    void turnOn();
    void turnOff();
}

class Light implements Device {
    @Override
    public void turnOn() { System.out.println("Đèn: Bật sáng."); }
    @Override
    public void turnOff() { System.out.println("Đèn: Tắt."); }
}

class Fan implements Device {
    @Override
    public void turnOn() { System.out.println("Quạt: Quay."); }
    @Override
    public void turnOff() { System.out.println("Quạt: Dừng."); }
}

class AirConditioner implements Device {
    @Override
    public void turnOn() { System.out.println("Điều hòa: Đang làm mát."); }
    @Override
    public void turnOff() { System.out.println("Điều hòa: Tắt."); }
}

// --- FACTORY METHOD PATTERN ---
abstract class DeviceFactory {
    public abstract Device createDevice();
}

class LightFactory extends DeviceFactory {
    @Override
    public Device createDevice() {
        System.out.println("LightFactory: Đã tạo đèn mới.");
        return new Light();
    }
}

class FanFactory extends DeviceFactory {
    @Override
    public Device createDevice() {
        System.out.println("FanFactory: Đã tạo quạt mới.");
        return new Fan();
    }
}

class AirConditionerFactory extends DeviceFactory {
    @Override
    public Device createDevice() {
        System.out.println("AirConditionerFactory: Đã tạo điều hòa mới.");
        return new AirConditioner();
    }
}

// --- CLIENT ---
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HardwareConnection connection = null;
        List<Device> devices = new ArrayList<>();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Kết nối phần cứng");
            System.out.println("2. Tạo thiết bị mới");
            System.out.println("3. Bật/Tắt thiết bị hiện có");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                connection = HardwareConnection.getInstance();
                connection.connect();
            } else if (choice.equals("2")) {
                System.out.println("Chọn loại: 1. Đèn | 2. Quạt | 3. Điều hòa");
                String type = sc.nextLine();
                DeviceFactory factory = null;

                if (type.equals("1")) factory = new LightFactory();
                else if (type.equals("2")) factory = new FanFactory();
                else if (type.equals("3")) factory = new AirConditionerFactory();

                if (factory != null) {
                    devices.add(factory.createDevice());
                }
            } else if (choice.equals("3")) {
                if (devices.isEmpty()) {
                    System.out.println("Chưa có thiết bị nào.");
                    continue;
                }
                for (int i = 0; i < devices.size(); i++) {
                    System.out.println((i + 1) + ". " + devices.get(i).getClass().getSimpleName());
                }
                System.out.print("Chọn thiết bị: ");
                int idx = Integer.parseInt(sc.nextLine()) - 1;
                if (idx >= 0 && idx < devices.size()) {
                    devices.get(idx).turnOn();
                }
            } else if (choice.equals("0")) {
                break;
            }
        }
        sc.close();
    }
}