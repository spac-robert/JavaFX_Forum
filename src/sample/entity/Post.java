package sample.entity;

public class Post {
    private String title;
    private String posts;
    private String id;
    private String id_user;

    public Post() {
        this.title = "";
        this.posts = "";
        this.id = "";
        this.id_user = "";
    }

    public Post(String id, String id_user, String title, String posts) {
        this.title = title;
        this.posts = posts;
        this.id = id;
        this.id_user = id_user;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPosts() {
        return this.posts;
    }

    public String getId_user() {
        return this.id_user;
    }
}
