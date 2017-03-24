package Model.Image;

/**
 * Created by Cantekin on 24.3.2017.
 */

public class SizeOptions {
    private SizeImage thumbnail;
    private SizeImage medium;

    public SizeImage getMedium() {
        return medium;
    }

    public void setMedium(SizeImage medium) {
        this.medium = medium;
    }

    public SizeImage getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(SizeImage thumbnail) {
        this.thumbnail = thumbnail;
    }
}
