package me.twocat.shareinfo.controller;

import me.twocat.shareinfo.entity.userprofile.User;
import me.twocat.shareinfo.payload.ApiResponse;
import me.twocat.shareinfo.service.redis.RedisServiceApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/***
 @author echo
 @create 2019年09月20日 11:55

 */
@RestController
@RequestMapping("/2catme/test")
public class TestCntroller extends SuperController{
  @Autowired
  RedisServiceApiImpl redisServiceApi;
    @RequestMapping("/mokey")
    public ResponseEntity mokey()
    {
      Optional<User> user = getUserInfo();
      System.out.println("print this user info--->" + (user.isPresent() ? null : user.get().toString()));
      Map<String , Object> datas = new HashMap<>();
      datas.put("userid" , 232);
      datas.put("obj" , "jdjj");
      datas.put("kkoj" , new ArrayList<>());
        return ResponseEntity.ok(ApiResponse.responseSuccess().setData(datas));
    }

   // @PostAuthorize("hasAnyRole('ROLE_ADMIN')")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/cat")
    public ResponseEntity cat()
    {
        System.out.println("test auth about this project and show all message ");
        return ResponseEntity.badRequest().body(ApiResponse.responseFailure().setData(new HashMap<>()));
    }

   // @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping("/redistest")
    public User snake()
    {
        System.out.println("<---start redis test----> ");
        User user = new User();
        user.setAccount("fkaccount");
        user.setUserName("cats");
        user.setId(234l);
        user.setStatus(2);
        user.setLastLoginTime(new Date());
        user.setUserPic("string pic");
        //save test
        redisServiceApi.redisSaveObjectByKey("user222" , user , 50);
        //extract test
       User users = (User) redisServiceApi.redisGetValueByKey("user222");
      System.out.println("output user value is --->" + users.toString() );
      System.out.println("<-----------end redis test-------------->");
        return users;
    }

    // @PostAuthorize("hasAnyRole('ROLE_ADMIN')")
   // @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/elephant")
    public String elephant()
    {
      //extract test
      User users = (User) redisServiceApi.redisGetValueByKey("user222");
      System.out.println("output user value is --->" + users.toString() );
      System.out.println("<-----------end redis test-------------->");
        return "11111";
    }

}
