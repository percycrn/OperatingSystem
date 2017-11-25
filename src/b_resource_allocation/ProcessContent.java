package b_resource_allocation;

class ProcessContent {
    private static int numOfName = 0;
    private String name;
    private int[] maximum;
    private int[] allocation;
    private int[] work;
    private int[] need;
    private int[] workAllocation;
    private char status;

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
