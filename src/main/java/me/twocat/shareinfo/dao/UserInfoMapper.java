package me.twocat.shareinfo.dao;


import me.twocat.shareinfo.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {

	/**
	 * find user entity information hit database from mysql
	 * @param account
	 * @return
	 */
	User findJdUserByAccount(@Param("account") String account);

	/**
	 * 根据id查询
	 * */
	User findEntityById(Integer id);

	/**
	 * 增加用户
	 * */
	void insertEntity(User param);
}
