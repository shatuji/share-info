package me.twocat.shareinfo.entity.userprofile;



import com.fasterxml.jackson.annotation.JsonFormat;
import me.twocat.shareinfo.entity.BasicEntityCls;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User  extends BasicEntityCls {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String account;//登陆账号名称
	private String userName;//用户姓名
	private String passwd;//登陆密码

	private String userPic;//用户头像
	private Integer status;//状态 0：新建用户 1：被删除
	private Date lastLoginTime;//最后登陆时间
	private Integer loginTimes;//总登陆次数
	private Date createTime ;//创建时间

	private String profession;//职业
	private Integer ages;//年龄 0 ：20以下 , 1 ： 20~30 ,  2：30~40 , 3：40~50 , 4：50以上
	private String address;//地址
	private String tel;//电话号码
	private String userCode;//预留
	private Integer gender;//0 女 1 男
	private String  personalStyle;//个性签名

	public String getPersonalStyle() {
		return personalStyle;
	}

	public void setPersonalStyle(String personalStyle) {
		this.personalStyle = personalStyle;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Integer getAges() {
		return ages;
	}

	public void setAges(Integer ages) {
		this.ages = ages;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}



	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}



	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"id=" + id +
				", account='" + account + '\'' +
				", userName='" + userName + '\'' +
				", passwd='" + passwd + '\'' +

				", userPic='" + userPic + '\'' +

				", status=" + status +
				", lastLoginTime=" + lastLoginTime +
				", loginTimes=" + loginTimes +
				", createTime=" + createTime +
				'}';
	}
}
