public class MyFile {
    private String name;
    private int size;

    public MyFile(String property) {}

    public MyFile(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

}
