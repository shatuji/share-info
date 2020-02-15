package me.twocat.shareinfo.controller;

import me.twocat.shareinfo.dao.UserInfoMapper;
import me.twocat.shareinfo.entity.userprofile.User;
import me.twocat.shareinfo.payload.ApiResponse;
import me.twocat.shareinfo.payload.JwtAuthenticationResponse;
import me.twocat.shareinfo.payload.LoginRequest;
import me.twocat.shareinfo.payload.SignUpRequest;
import me.twocat.shareinfo.util.JwtTokenProvider;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

/***
 @author echo
 @create 2019年09月20日 10:58

 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  public static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserInfoMapper userInfoMapper;


    @Autowired

    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
/**
 * {
 *
 * "email" : "124541@foxmail.com",
 * "password" : "password"
 * }
 * AuthenticationManagerBuilder and AuthenticationManager
 * AuthenticationManagerBuilder is used to create an AuthenticationManager instance which is the main Spring Security interface for authenticating a user.
 * You can use AuthenticationManagerBuilder to build in-memory authentication, LDAP authentication, JDBC authentication, or add your custom authentication provider.
 * In our example, we’ve provided our customUserDetailsService and a passwordEncoder to build the AuthenticationManager.
 * We’ll use the configured AuthenticationManager to authenticate a user in the login API.
 */
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getPhoneOrEmail(),
                        loginRequest.getPassword()
                )
        );
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        try {
            // Creating user's account
            User user = new User();
            user.setAccount(signUpRequest.getAccount());
            user.setUserName(signUpRequest.getUsername());

            user.setPasswd(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setStatus(0);
            user.setCreateTime(new Date());
            userInfoMapper.insertEntity(user);
        }catch (Exception ex){
            ex.printStackTrace();
            LOGGER.info("authcontroller sign up error message--->{}" , ex.getMessage());
        }
        return ResponseEntity.ok(ApiResponse.responseSuccess());
    }

    /***
     * check account whether exit in user table
     * 检测账户是否存在
     * @param account
     * @return
     */
    @RequestMapping("/checkaccount")
    public ResponseEntity checkAccountDupli(@Param("account") String account){
        Optional<User> user = userInfoMapper.findJdUserByAccount(account);
        if(user.isPresent())
            return ResponseEntity.ok(ApiResponse.responseFailure().setMessage("账户已经存在!!!"));
        else
            return ResponseEntity.ok(ApiResponse.responseSuccess().setMessage("账户不存在!!!"));
    }
}
