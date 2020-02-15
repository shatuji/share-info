package me.twocat.shareinfo.controller.userprofile;

import me.twocat.shareinfo.controller.SuperController;
import me.twocat.shareinfo.entity.userprofile.User;
import me.twocat.shareinfo.payload.ApiResponse;
import me.twocat.shareinfo.service.userprofile.UserProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/***
 * 用户的个人信息编辑
 */
@RestController
@RequestMapping("/userprofile")
public class UserProfileController extends SuperController {

    @Autowired
    UserProfileServiceImpl userProfileService;
    /**
     * edit user profile
     * 编辑增加用户的信息
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public ResponseEntity addProfile(User user){
        Optional<User> loginUser = getUserInfo();
        if(loginUser.isPresent())
        {
            user.setId(loginUser.get().getId());
            userProfileService.updateUser(user);
            return ResponseEntity.ok(ApiResponse.responseSuccess());
        }
        return ResponseEntity.ok(ApiResponse.responseFailure());
    }
}
