package Sesson8.Bai3;

import java.util.*;

interface Command {
    void execute();
    void undo();
}

class Light {
    public void on() { System.out.println("Đèn: Bật"); }
    public void off() { System.out.println("Đèn: Tắt"); }
}

class AirConditioner {
    private int temperature = 25;

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    public int getTemperature() {
        return temperature;
    }
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

class ACSetTemperatureCommand implements Command {
    private AirConditioner ac;
    private int newTemp;
    private int prevTemp;

    public ACSetTemperatureCommand(AirConditioner ac, int newTemp) {
        this.ac = ac;
        this.newTemp = newTemp;
    }

    @Override
    public void execute() {
        prevTemp = ac.getTemperature();
        ac.setTemperature(newTemp);
    }

    @Override
    public void undo() {
        System.out.print("Undo: ");
        ac.setTemperature(prevTemp);
    }
}

class RemoteControl {
    private Map<Integer, Command> commands = new HashMap<>();
    private Stack<Command> undoStack = new Stack<>();

    public void setCommand(int slot, Command command) {
        commands.put(slot, command);
    }

    public void pressButton(int slot) {
        if (commands.containsKey(slot)) {
            Command cmd = commands.get(slot);
            cmd.execute();
            undoStack.push(cmd);
        } else {
            System.out.println("Nút này chưa được gán lệnh.");
        }
    }

    public void pressUndo() {
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
        } else {
            System.out.println("Không có lệnh nào để Undo.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();
        Light livingRoomLight = new Light();
        AirConditioner ac = new AirConditioner();

        System.out.println("Gán nút 1: Bật đèn");
        remote.setCommand(1, new LightOnCommand(livingRoomLight));
        System.out.println("Đã gán LightOnCommand cho nút 1");

        System.out.println("Nhấn nút 1");
        remote.pressButton(1);

        System.out.println("\nGán nút 2: Tắt đèn");
        remote.setCommand(2, new LightOffCommand(livingRoomLight));
        System.out.println("Đã gán LightOffCommand cho nút 2");

        System.out.println("Nhấn nút 2");
        remote.pressButton(2);

        System.out.println("\nNhấn Undo");
        remote.pressUndo();

        System.out.println("\nGán nút 3: Set điều hòa 26°C");
        remote.setCommand(3, new ACSetTemperatureCommand(ac, 26));
        System.out.println("Đã gán ACSetTempCommand(26) cho nút 3");

        System.out.println("Nhấn nút 3");
        remote.pressButton(3);

        System.out.println("\nNhấn Undo");
        remote.pressUndo();
    }
}