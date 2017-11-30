package c_manage_storage;

class StorageStructuralBody {
    int index;
    int address;
    int end;
    int size;

    StorageStructuralBody(int index, int address, int end, int size) {
        this.index = index;
        this.address = address;
        this.end = end;
        this.size = size;
    }
}