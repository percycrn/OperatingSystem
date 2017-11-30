package c_manage_storage;

import java.util.Comparator;

public class AddressComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        StorageStructuralBody ssb1 = (StorageStructuralBody) o1;
        StorageStructuralBody ssb2 = (StorageStructuralBody) o2;
        return (ssb1.address > ssb2.address) ? 1 : -1;
    }
}
