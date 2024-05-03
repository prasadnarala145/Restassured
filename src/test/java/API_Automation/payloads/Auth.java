package API_Automation.payloads;

public class Auth {

 String username;
 String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username =username;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password;
        this.password = password;
    }

    public String toString() {
        return "Auth{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
