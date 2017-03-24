package Model.Image;

/**
 * Created by Cantekin on 24.3.2017.
 */

public class MediaDetails {
    private String file;
    private SizeOptions sizes;

    public SizeOptions getSizes() {
        return sizes;
    }

    public void setSizes(SizeOptions sizes) {
        this.sizes = sizes;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
