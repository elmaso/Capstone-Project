package inc.maso.mangiare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.firebase.client.ServerValue;

/**
 * Created by mariosoberanis on 3/22/16.
 * This Class is going to have all the restaurants categories, at least 5 different from each category
 */
public class PhotosCategories {
    String category;
    String subCategory;
    String photo_url;
    Integer likes;
    @JsonProperty
    private Object created;

// To Add The constructor Just hit Ctrl + Return on a mac and select from the menu

    public PhotosCategories() {
    }

    public PhotosCategories(String category, String subCategory, String photo_url, Integer likes) {
        this.category = category;
        this.subCategory = subCategory;
        this.photo_url = photo_url;
        this.likes = likes;
        this.created = ServerValue.TIMESTAMP;
    }
// To Add the Getter select Getter from the list and add al the elements in the class POJO
    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public Integer getLikes() {
        return likes;
    }

    @JsonIgnore
    public Long getCreatedTimestamp() {
        if (created instanceof Long) {
            return (Long) created;
        }
        else {
            return null;
        }
    }
}
