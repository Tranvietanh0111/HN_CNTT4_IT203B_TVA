package Sesson1.Bai6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BestWoman myMom = new BestWoman();
        myMom.inputData(sc);
        myMom.displayData();
    }
}
