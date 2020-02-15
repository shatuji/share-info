package me.twocat.shareinfo.payload;


/***
 @author echo
 @create 2019年09月20日 10:56

 */
public class SignUpRequest {


    private String username;

    private String account;

    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
