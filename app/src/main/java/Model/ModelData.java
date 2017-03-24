package Model;

import java.util.Date;

/**
 * Created by Cantekin on 24.3.2017.
 */

public class ModelData {
    private long id;
    private String date;
    private Detail guid;
    private String link;
    private Detail title;
    private Detail content;
    private Links _links;

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

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }
}
