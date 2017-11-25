package a_process_schedule;

import java.util.ArrayList;

class ProcessControlBlock {
    private static class Node {
        private String name; // 进程名字
        private int priority; // 进程优先数
        private int CPUTime; // 已占用CPU的时间
        private int needTime; // 需要CPU的时间
        private char status; // 进程状态
        private int round; // 时间片
        private int count; // 已经运行的轮数
        private Node next; // 下一个进程

        Node(String name, int needTime, int round) {
            this.name = name;
            this.priority = 50 - needTime;
            this.CPUTime = 0;
            this.needTime = needTime;
            this.status = 'W';
            this.round = round;
            this.count = 0;
            this.next = null;
        }
    }

    private ArrayList finishedQueue;
    private Node first;
    private Node last;
    private int size = 0;

    // 添加数据
    void add(String name, int needTime, int round) {
        Node node = new Node(name, needTime, round);
        Node l = last;
        last = node;
        if (first == null) {
            first = node;
        } else {
            l.next = node;
        }
        size++;
    }

    // 将第一个进程移到最后一个
    private void moveFtoE() {
        if (first == null) {
            return;
        }
        Node node = first;
        first = first.next;
        last.next = node;
        node.next = null;
        last = node;
    }

    private void printRound() {
        System.out.println("Name CPUTime needTime count round state");
        Node node = first;
        while (node != null) {
            System.out.println(node.name + "\t " + node.CPUTime + "\t\t " +
                    node.needTime + "\t\t " + node.count + "\t   " +
                    node.round + "\t\t " + node.status);
            node = node.next;
        }
        node = first;
        System.out.print("就绪队列：");
        while (node != null) {
            if (node.status == 'W') {
                System.out.print(node.name + " ");
            }
            node = node.next;
        }
        System.out.println();
        System.out.print("完成队列：");
        for (Object aFinishedQueue : finishedQueue) {
            System.out.print(aFinishedQueue + " ");
        }
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    void roundMethod() {
        finishedQueue = new ArrayList();
        int finished = 0;
        while (finished < size) {
            if (first.status == 'F') {
                moveFtoE();
                continue;
            }
            first.status = 'R';
            printRound();
            if (first.needTime <= first.round) {
                first.CPUTime += first.needTime;
                first.needTime = 0;
                first.count++;
                first.status = 'F';
                finishedQueue.add(first.name);
                finished++;
            } else {
                first.CPUTime += first.round;
                first.needTime -= first.round;
                first.count++;
                first.status = 'W';
            }
            moveFtoE();
        }
        printRound();
    }

    void printName() {
        Node node = first;
        while (node != null) {
            System.out.print(node.name + " ");
            node = node.next;
        }
        System.out.println();
    }

    void sortPriority() {
        Node nodeI = first;
        Node nodeJ;
        while (nodeI != null) {
            nodeJ = nodeI.next;
            while (nodeJ != null) {
                if (nodeI.priority < nodeJ.priority) {
                    String str = nodeI.name;
                    nodeI.name = nodeJ.name;
                    nodeJ.name = str;
                    int needTime = nodeI.needTime;
                    nodeI.needTime = nodeJ.needTime;
                    nodeJ.needTime = needTime;
                    int pri = nodeI.priority;
                    nodeI.priority = nodeJ.priority;
                    nodeJ.priority = pri;
                }else if (nodeI.priority==nodeJ.priority&&(nodeI.name.compareTo(nodeJ.name) > 0)){
                    String str = nodeI.name;
                    nodeI.name = nodeJ.name;
                    nodeJ.name = str;
                    int needTime = nodeI.needTime;
                    nodeI.needTime = nodeJ.needTime;
                    nodeJ.needTime = needTime;
                }
                nodeJ = nodeJ.next;
            }
            nodeI = nodeI.next;
        }
    }

    private void printPriority() {
        System.out.println("Name CPUTime needTime count pri state");
        Node node = first;
        while (node != null) {
            System.out.println(node.name + "\t " + node.CPUTime + "\t\t " +
                    node.needTime + "\t\t " + node.count + "\t   " +
                    node.priority + "\t\t " + node.status);
            node = node.next;
        }
        node = first;
        System.out.print("就绪队列：");
        while (node != null) {
            if (node.status == 'W') {
                System.out.print(node.name + " ");
            }
            node = node.next;
        }
        System.out.println();
        System.out.print("完成队列：");
        for (Object aFinishedQueue : finishedQueue) {
            System.out.print(aFinishedQueue + " ");
        }
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    void priorityMethod() {
        sortPriority();
        finishedQueue = new ArrayList();
        int finished = 0;
        while (finished < size) {
            first.status = 'R';
            printPriority();
            first.CPUTime += first.needTime;
            first.needTime = 0;
            first.count++;
            first.status = 'F';
            finishedQueue.add(first.name);
            finished++;
            moveFtoE();
        }
        printPriority();
    }
}
