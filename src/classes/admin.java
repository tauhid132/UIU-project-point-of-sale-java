package classes;

public class admin{
    public int id;
    public String username;
    public String password;
    public String fullName;
    public String emailAddress;
    public String userType;

    public admin(int id, String username, String password, String fullName, String emailAddress, String userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUserType() {
        return userType;
    }
}