package me.twocat.shareinfo.dao;


import me.twocat.shareinfo.entity.userprofile.User;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface UserInfoMapper {

	/**
	 * find user entity information hit database from mysql
	 * @param account
	 * @return
	 */
	Optional<User> findJdUserByAccount(@Param("account") String account);

	/**
	 * 根据id查询
	 * */
	User findEntityById(Integer id);

	/**
	 * 增加用户
	 * */
	void insertEntity(User param);

	/***
	 * update 用户信息
	 */
	void updateUser(User param);
}
