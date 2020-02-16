package me.twocat.shareinfo.service.userprofile;

import me.twocat.shareinfo.dao.UserInfoMapper;
import me.twocat.shareinfo.entity.userprofile.User;
import me.twocat.shareinfo.service.GenricSelectServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements GenricSelectServer<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileServiceImpl.class);
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public Map selectData(User user) {
        return null;
    }

    /***
     * update user
     * @param user
     * @return
     */
    public boolean updateUser(User user)
    {
        boolean flags = false;
        try {
            userInfoMapper.updateUser(user);
            flags = true ;
        }catch (Exception ex){
            LOGGER.info("用户更新页面报错(user update website has error )---->{}" , ex.getMessage());
        }
        return flags;
    }

    /**
     * extract user table information by id
     * 提取user表的信息根据id
     * @param id
     * @return
     */
    public Optional<User> selectUserById(Long id){
        if(id != null){
           return Optional.ofNullable(userInfoMapper.findEntityById(id));
        }
        return Optional.empty();
    }
}
