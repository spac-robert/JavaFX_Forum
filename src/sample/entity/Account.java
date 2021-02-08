package sample.entity;

public class Account {
    private String id;
    private String userName;
    private String password;
    private String type;

    public Account() {
        this.id = "";
        this.password = "";
        this.type = "user";
        this.userName = "";
    }

    public Account(String id, String userName, String password, String type) {
        this.id = id;
        this.password = password;
        this.type = type;
        this.userName = userName;
    }

    //Getters
    public String getId() {
        return this.id;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getType() {
        return this.type;
    }

    //Setters
    public void setId(String newId) {
        this.id = newId;
    }

    public void setUserName(String newUserName) {
        this.userName = newUserName;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setType(String newType) {
        this.type = newType;
    }
}
