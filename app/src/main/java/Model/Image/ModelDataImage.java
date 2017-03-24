package Model.Image;

import Model.Detail;
import Model.Links;

/**
 * Created by Cantekin on 24.3.2017.
 */

public class ModelDataImage {
    private long id;
    private String date;
    private Detail guid;
    private String link;
    private Detail title;
    private Detail content;
    private MediaDetails media_details;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Detail getGuid() {
        return guid;
    }

    public void setGuid(Detail guid) {
        this.guid = guid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Detail getTitle() {
        return title;
    }

    public void setTitle(Detail title) {
        this.title = title;
    }

    public Detail getContent() {
        return content;
    }

    public void setContent(Detail content) {
        this.content = content;
    }

    public MediaDetails getMedia_details() {
        return media_details;
    }

    public void setMedia_details(MediaDetails media_details) {
        this.media_details = media_details;
    }
}
