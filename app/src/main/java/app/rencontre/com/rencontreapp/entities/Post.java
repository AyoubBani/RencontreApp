package app.rencontre.com.rencontreapp.entities;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by famille on 5/30/2018.
 */

public class Post implements Serializable {
    String post;
    String username;
    String date;

    public Post() {
    }

    public Post(String post, String username, String date) {
        this.post = post;
        this.username = username;
        this.date = date;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("post", post);
        return result;
    }
}
