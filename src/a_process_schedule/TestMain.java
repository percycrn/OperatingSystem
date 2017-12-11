package a_process_schedule;

import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        ProcessControlBlock p = new ProcessControlBlock();
        Scanner scanner = new Scanner(System.in);
        System.out.print("input the quantity of the processes ");
        int n = scanner.nextInt();
        String name;
        int needTime;
        System.out.println("input data ");
        for (int i = 1; i <= n; i++) {
            name = scanner.next();
            needTime = scanner.nextInt();
            p.add(name, needTime);
        }
        //p.roundMethod();
        p.priorityMethod();
    }
}
/*
a1 3
a2 2
a3 4
a4 2
a5 1
*/