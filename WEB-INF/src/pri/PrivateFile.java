package pri;


public class PrivateFile {
    private int id;
    private int fileToUser;
    private String fileName;
    private String owner;
    private double size;

    public PrivateFile(int id, int fileToUser, String fileName, String owner, double size) {
        this.id = id;
        this.fileToUser = fileToUser;
        this.fileName = fileName;
        this.owner = owner;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getFileToUser() {
        return fileToUser;
    }

    public String getFileName() {
        return fileName;
    }

    public String getOwner() {
        return owner;
    }

    public double getSize() {
        return size;
    }
}