package c_manage_storage;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageStorage {
    private static Scanner scanner = new Scanner(System.in);
    private static String way;
    private static ArrayList<StorageStructuralBody> storage = new ArrayList<>();

    public static void main(String[] args) {
        // choose best or first
        boolean flag = false;
        while (true) {
            // initialize the list
            if (storage.isEmpty()) {
                StorageStructuralBody ssb = new StorageStructuralBody(1, 0, 32766, 32767);
                storage.add(ssb);
            }
            showStorage();
            // choose way
            if (!flag) {
                System.out.print("input the way(best or first): ");
                way = scanner.next();
                if (way.equals("best") || way.equals("first")) {
                    flag = true;
                } else {
                    System.out.println("input incorrect message.");
                    continue;
                }
            }
            chooseAssignOrAccept();
        }
    }

    private static void showStorage() {
        System.out.println("Index   address   end   size");
        for (StorageStructuralBody aStorage : storage) {
            System.out.print(aStorage.index + "         ");
            System.out.print(aStorage.address + "      ");
            System.out.print(aStorage.end + "  ");
            System.out.print(aStorage.size);
        }
        System.out.println();
    }

    private static void chooseAssignOrAccept() {
        System.out.print("assign or accept: ");
        String asOrAc = scanner.next();
        switch (asOrAc) {
            case "as":
                System.out.println("input APPLICATION: ");
                int applicationSize = scanner.nextInt();
                assign(applicationSize, way);
                break;
            case "ac":
                int address = scanner.nextInt();
                int size = scanner.nextInt();
                accept(address, size);
                break;
            default:
                System.out.println("input incorrect message.");
        }
    }

    private static void assign(int applicationSize, String way) {

    }

    private static void accept(int address, int size) {

    }

}
