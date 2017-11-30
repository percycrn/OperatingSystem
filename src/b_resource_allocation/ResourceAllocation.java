package b_resource_allocation;

import java.util.Arrays;

class ResourceAllocation {
    private ProcessContent[] processArr;
    private int[] order;
    private int[] availableResources;
    private int numOfProcess;
    private int numOfResource;

    // constructor: to initialize two parameters and one array
    ResourceAllocation(int numOfProcess, int numOfResource) {
        this.numOfProcess = numOfProcess;
        this.numOfResource = numOfResource;
        processArr = new ProcessContent[numOfProcess];
        for (int i = 0; i < numOfProcess; i++) {
            processArr[i] = new ProcessContent(numOfResource);
        }
    }

    void addProcessMax(int[] max, int i) {
        processArr[i].setMaximum(max);
    }

    void addProcessAllocation(int[] allocation, int i) {
        processArr[i].setAllocation(allocation);
        int[] max = processArr[i].getMaximum();
        int[] need = new int[numOfResource];
        for (int j = 0; j < numOfResource; j++) {
            need[j] = max[j] - allocation[j];
        }
        processArr[i].setNeed(need);
    }

    // 安全性算法
    boolean securityAlgorithm(String name, int[] request) {
        int i;
        for (i = 0; i < numOfProcess; i++) {
            if (processArr[i].getName().equals(name)) {
                break;
            }
        }
        if (i == numOfProcess) {
            System.out.println("cannot find this process");
            return false;
        }
        int[] allocation = processArr[i].getAllocation();
        int[] need = processArr[i].getNeed();
        for (int j = 0; j < numOfResource; j++) {
            availableResources[j] -= request[j];
            allocation[j] += request[j];
            need[j] -= request[j];
        }
        processArr[i].setAllocation(allocation);
        processArr[i].setNeed(need);
        return bankAlgorithm(availableResources);
    }

    // 安全性算法后，将值复原
    void reassignmentSecurity(String name, int[] request) {
        int i;
        for (i = 0; i < numOfProcess; i++) {
            if (processArr[i].getName().equals(name)) {
                break;
            }
        }
        int[] allocation = processArr[i].getAllocation();
        int[] need = processArr[i].getNeed();
        for (int j = 0; j < numOfResource; j++) {
            availableResources[j] += request[j];
            allocation[j] -= request[j];
            need[j] += request[j];
        }
        processArr[i].setAllocation(allocation);
        processArr[i].setNeed(need);
        reassignmentBank();
    }

    // 银行家算法
    boolean bankAlgorithm(int[] work) {
        // order 数组用来存储调度的次序
        order = new int[numOfProcess];
        for (int i = 0; i < numOfProcess; i++) {
            order[i] = -1;
        }
        int finished = 0;
        int loopI = 0, orderI = 0, judgeI = 0, borderI = numOfProcess;
        // 循环调度进程
        // 结束条件 1. 全部进程调度完毕  2. 进程未调度完毕且剩余资源无法满足任一进程
        while (finished < numOfProcess) {
            if (loopI >= numOfProcess) {
                loopI = 0;
            }
            if (processArr[loopI].getStatus() != 'T') {
                judgeI++;
                if (checkIsSecure(work, processArr[loopI].getNeed())) {
                    setWork(work, loopI);
                    setWorkAllocation(work, processArr[loopI].getAllocation(), loopI);
                    work = processArr[loopI].getWorkAllocation();
                    processArr[loopI].setStatus('T');
                    finished++;
                    order[orderI++] = loopI;
                    borderI--;
                    judgeI = 0;
                }
                // 判断是否满足第二条结束条件
                if (judgeI == borderI && finished != numOfProcess) {
                    return false;
                }
            }
            loopI++;
        }
        return true;
    }

    // 银行家算法后复原
    void reassignmentBank() {
        int[] reassignment = new int[numOfResource];
        for (int i = 0; i < numOfProcess; i++) {
            processArr[i].setWork(reassignment);
            processArr[i].setWorkAllocation(reassignment);
            processArr[i].setStatus('W');
        }
    }

    // check whether each resource in array work is more than that in array need or not
    private boolean checkIsSecure(int[] work, int[] need) {
        for (int i = 0; i < work.length; i++) {
            if (work[i] < need[i]) {
                return false;
            }
        }
        return true;
    }

    // 给 workAllocation 数组赋值
    private void setWorkAllocation(int[] work, int[] allocation, int i) {
        int[] workAllocation = new int[work.length];
        for (int j = 0; j < work.length; j++) {
            workAllocation[j] = work[j] + allocation[j];
        }
        processArr[i].setWorkAllocation(workAllocation);
    }

    // 给 work 数组赋值
    private void setWork(int[] work, int i) {
        int[] workOfProcess = new int[work.length];
        System.arraycopy(work, 0, workOfProcess, 0, work.length);
        processArr[i].setWork(workOfProcess);
    }

    void setAvailableResources(int[] availableResources) {
        this.availableResources = availableResources;
    }

    void print() {
        if (order[0] == -1) {
            return;
        }
        System.out.println("Name  Work       Need       Allocation  Work+Allocation  Finish");
        for (int i = 0; i < numOfProcess; i++) {
            if (order[i] == -1) {
                break;
            }
            System.out.print(processArr[order[i]].getName() + "    ");
            System.out.print(Arrays.toString(processArr[order[i]].getWork()) + "  ");
            System.out.print(Arrays.toString(processArr[order[i]].getNeed()) + "  ");
            System.out.print(Arrays.toString(processArr[order[i]].getAllocation()) + "   ");
            System.out.print(Arrays.toString(processArr[order[i]].getWorkAllocation()) + "         ");
            System.out.print(processArr[order[i]].getStatus());
            System.out.println();
        }
    }
}