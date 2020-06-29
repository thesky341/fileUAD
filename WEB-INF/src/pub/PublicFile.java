package pub;


public class PublicFile {
    private int id;
    private int pubToPri;
    private String fileName;
    private String owner;
    private double size;

    public PublicFile(int id, int pubToPri, String fileName, String owner, double size) {
        this.id = id;
        this.pubToPri = pubToPri;
        this.fileName = fileName;
        this.owner = owner;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getPubToPri() {
        return pubToPri;
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