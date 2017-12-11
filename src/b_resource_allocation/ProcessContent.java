package b_resource_allocation;

class ProcessContent {
    private static int numOfName = 0;
    private String name;            // 进程名
    private int[] maximum;          // 最大资源需求数
    private int[] allocation;       // 已分配资源数
    private int[] work;             // 当前剩余资源数
    private int[] need;             // 当前进程需要资源数
    private char status;            // 进程状态
    private int[] workAllocation;

    ProcessContent(int numOfResource) {
        this.name = "p" + String.valueOf(numOfName++);
        this.maximum = new int[numOfResource];
        this.allocation = new int[numOfResource];
        this.work = new int[numOfResource];
        this.need = new int[numOfResource];
        this.workAllocation = new int[numOfResource];
        this.status = 'W';
    }

    String getName() {
        return name;
    }

    int[] getMaximum() {
        return maximum;
    }

    int[] getAllocation() {
        return allocation;
    }

    int[] getWork() {
        return work;
    }

    int[] getNeed() {
        return need;
    }

    int[] getWorkAllocation() {
        return workAllocation;
    }

    char getStatus() {
        return status;
    }

    void setMaximum(int[] maximum) {
        this.maximum = maximum;
    }

    void setAllocation(int[] allocation) {
        this.allocation = allocation;
    }

    void setWork(int[] work) {
        this.work = work;
    }

    void setNeed(int[] need) {
        this.need = need;
    }

    void setWorkAllocation(int[] workAllocation) {
        this.workAllocation = workAllocation;
    }

    void setStatus(char status) {
        this.status = status;
    }
}
