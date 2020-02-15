package me.twocat.shareinfo.service;

import me.twocat.shareinfo.dao.UserInfoMapper;
import me.twocat.shareinfo.entity.userprofile.User;
import me.twocat.shareinfo.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/***
 @author echo
 @create 2019年09月20日 10:04

 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        try {
            Optional<User> user = userInfoMapper.findJdUserByAccount(s);
            if(user.isPresent())
            return UserPrincipal.create(user.get());
            return  null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    // This method is used by JWTAuthenticationFilter
}
