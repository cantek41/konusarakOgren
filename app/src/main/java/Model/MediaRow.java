package Model;

import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Cantekin on 24.3.2017.
 */

public class MediaRow implements Serializable {
    private Date date;
    private String title;
    private String imgUrl;
    private String content;

    public void setDate(String date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:dd:ss", Locale.ENGLISH);
        try {
            this.date = dateFormatter.parse(date);
        } catch (ParseException e) {
            this.date=new Date();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        if (date != null) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            return dateFormatter.format(date);
        } else
            return "";

    }
}
