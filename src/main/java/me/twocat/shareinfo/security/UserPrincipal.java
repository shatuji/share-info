package me.twocat.shareinfo.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import me.twocat.shareinfo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

/***
 @author echo
 @create 2019年09月20日 9:51

 */
public class UserPrincipal implements UserDetails {
    private Long id;

    private String name;

    private String username;

    //login account
    private String phoneOrEmail;

    @JsonIgnore
    private String password;


  public UserPrincipal(Long id, String username, String phoneOrEmail, String password) {
    this.id = id;
    this.username = username;
    this.phoneOrEmail = phoneOrEmail;
    this.password = password;
  }

    public static UserPrincipal create(User user) {
        return  new UserPrincipal(user.getId() ,
                                  user.getUserName() ,
                                  user.getAccount() ,
                                  user.getPasswd());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

  public String getPhoneOrEmail() {
    return phoneOrEmail;
  }



  @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
