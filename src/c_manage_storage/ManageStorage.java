package c_manage_storage;

import java.util.*;

public class ManageStorage {
    private static Scanner scanner = new Scanner(System.in);
    private static String way;
    private static ArrayList<StorageStructuralBody> storage = new ArrayList<>();
    private static Comparator comparator;
    private static int maxSize = 32767;

    @SuppressWarnings("all")
    public static void main(String[] args) {
        comparator = (o1, o2) -> {
            StorageStructuralBody ssb1 = (StorageStructuralBody) o1;
            StorageStructuralBody ssb2 = (StorageStructuralBody) o2;
            return (ssb1.address > ssb2.address) ? 1 : -1;
        };
        // choose best or first
        boolean flag = false;
        while (true) {
            // initialize the list
            if (storage.isEmpty()) {
                StorageStructuralBody ssb = new StorageStructuralBody(1, 0, maxSize - 1, maxSize);
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
                    System.out.println();
                    continue;
                }
            }
            chooseAssignOrAccept();
        }
    }

    private static void showStorage() {
        System.out.println("Index   address   end   size");
        for (StorageStructuralBody aStorage : storage) {
            printWithBlank(aStorage.index);
            printWithBlank(aStorage.address);
            printWithBlank(aStorage.end);
            printWithBlank(aStorage.size);
            System.out.println();
        }
    }

    private static void printWithBlank(int content) {
        System.out.print(content);
        for (int i = 1; i <= 8 - String.valueOf(content).length(); i++) {
            System.out.print(" ");
        }
    }

    private static void chooseAssignOrAccept() {
        System.out.print("assign or accept: ");
        String asOrAc = scanner.next();
        switch (asOrAc) {
            case "as":
                System.out.print("input APPLICATION: ");
                int applicationSize = scanner.nextInt();
                assign(applicationSize, way);
                System.out.println();
                break;
            case "ac":
                System.out.print("input address and size: ");
                int address = scanner.nextInt();
                int size = scanner.nextInt();
                accept(address, size);
                System.out.println();
                break;
            default:
                System.out.println("input incorrect message.");
                System.out.println();
        }
    }

    private static void assign(int applicationSize, String way) {
        switch (way) {
            case "best":
                int tempSize = maxSize;
                int bestPointer = 0;
                for (int i = 0; i < storage.size(); i++) {
                    if (tempSize > (storage.get(i).size - applicationSize)
                            && (storage.get(i).size - applicationSize) >= 0) {
                        tempSize = storage.get(i).size - applicationSize;
                        bestPointer = i;
                    }
                }
                if (tempSize == maxSize) {
                    System.out.println("too large application!");
                } else {
                    System.out.println("SUCCESS!!  ADDRESS = " + (storage.get(bestPointer).end - applicationSize + 1));
                    storage.get(bestPointer).size -= applicationSize;
                    storage.get(bestPointer).end -= applicationSize;
                    if (storage.get(bestPointer).size == 0) {
                        storage.remove(bestPointer);
                    }
                }
                break;
            case "first":
                for (int i = storage.size() - 1; i >= 0; i--) {
                    if (storage.get(i).size >= applicationSize) {
                        System.out.println("SUCCESS!!  ADDRESS = " + (storage.get(i).end - applicationSize + 1));
                        storage.get(i).size -= applicationSize;
                        storage.get(i).end -= applicationSize;
                        if (storage.get(i).size == 0) {
                            storage.remove(i);
                        }
                        return;
                    }
                }
                System.out.println("too large application!");
                break;
            default:
                break;
        }

    }

    // http://blog.csdn.net/kingzone_2008/article/details/41368989 iterator
    // http://blog.csdn.net/u011299745/article/details/52654023 sort
    @SuppressWarnings("unchecked")
    private static void accept(int address, int size) {
        StorageStructuralBody ssb = new StorageStructuralBody(storage.size() + 1, address, address + size - 1, size);
        for (int i = 0; i < storage.size(); i++) {
            // intersection
            if (!(ssb.end < storage.get(i).address - 1 || ssb.address > storage.get(i).end + 1)) {
                ssb.address = (storage.get(i).address > ssb.address) ? ssb.address : storage.get(i).address;
                ssb.end = (storage.get(i).end > ssb.end) ? storage.get(i).end : ssb.end;
                ssb.size = storage.get(i).end - storage.get(i).address + 1;
                storage.remove(i);
                i--;
            }
        }
        storage.add(ssb);
        storage.sort(comparator);
        for (int i = 1; i <= storage.size(); i++) {
            storage.get(i - 1).index = i;
        }
    }
}