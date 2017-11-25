package b_resource_allocation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("input the type of resource and number of customer:");
        Scanner scanner = new Scanner(System.in);

        // input quantity of the resources and processes
        int numOfResources = scanner.nextInt();
        int numOfProcesses = scanner.nextInt();
        int[] temp;

        // create an object of ResourceAllocation
        ResourceAllocation resourceAllocation = new ResourceAllocation(numOfProcesses, numOfResources);

        System.out.println("input the amount of resource (maximum, allocated) of each customer:");
        for (int i = 0; i < numOfProcesses; i++) {
            temp = new int[numOfResources];
            for (int j = 0; j < numOfResources; j++) {
                temp[j] = scanner.nextInt();
            }
            resourceAllocation.addProcessMax(temp, i);
            temp = new int[numOfResources];
            for (int j = 0; j < numOfResources; j++) {
                temp[j] = scanner.nextInt();
            }
            resourceAllocation.addProcessAllocation(temp, i);
        }

        System.out.println("input the amount of resources(available)");
        int[] available = new int[numOfResources];
        for (int i = 0; i < numOfResources; i++) {
            available[i] = scanner.nextInt();
        }
        resourceAllocation.setAvailableResources(available);

        // choice interface
        while (true) {
            System.out.println("1 judge the system security\n2 judge the request security\n3 quit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (resourceAllocation.bankAlgorithm(available)) {
                        resourceAllocation.print();
                        System.out.println("SYSTEM SECURITY");
                    } else {
                        System.out.println("SYSTEM INSECURITY");
                    }
                    resourceAllocation.reassignmentBank();
                    break;
                case 2:
                    System.out.println("Please input the customer's name and request");
                    String name = scanner.next();
                    int[] request = new int[numOfResources];
                    for (int i = 0; i < numOfResources; i++) {
                        request[i] = scanner.nextInt();
                    }
                    if (resourceAllocation.securityAlgorithm(name, request)) {
                        resourceAllocation.print();
                        System.out.println("SYSTEM SECURITY\nCUSTOMER " + name + " CAN GET RESOURCES IMMEDIATELY");
                    } else {
                        resourceAllocation.print();
                        System.out.println("SYSTEM INSECURITY\nCUSTOMER " + name + " CAN NOT OBTAIN RESOURCES IMMEDIATELY");
                    }
                    resourceAllocation.reassignmentSecurity(name, request);
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }
}
/* example of resource of the processes
7 5 3 0 1 0
3 2 2 2 0 0
9 0 2 3 0 2
2 2 2 2 1 1
4 3 3 0 0 2
*/