public class MemoryPartition {
    private  int size;
    private boolean Free;

    public MemoryPartition() {}

    public MemoryPartition(int size, boolean isFree) {
        this.size = size;
        this.Free = isFree;
    }

    public int getSize() {
        return size;
    }

    public boolean isFree() {
        return Free;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setFree(boolean isFree) {
        this.Free = isFree;
    }

}
