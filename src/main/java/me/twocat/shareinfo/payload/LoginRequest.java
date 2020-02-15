package me.twocat.shareinfo.payload;

import javax.validation.constraints.NotBlank;

/***
 @author echo
 @create 2019年09月20日 10:55

 */
public class LoginRequest {
    @NotBlank
    private String phoneOrEmail;

    @NotBlank
    private String password;

    public String getPhoneOrEmail() {
        return phoneOrEmail;
    }

    public void setPhoneOrEmail(String phoneOrEmail) {
        this.phoneOrEmail = phoneOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
