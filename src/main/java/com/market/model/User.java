package com.market.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author liwei
 * @since 2018-04-12
 */
public class User extends Model<User> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "u_id", type = IdType.AUTO)
	private Integer uId;
	/**
	 * 账号
	 */
	@Pattern(regexp="[\u4e00-\u9fa5_a-zA-Z0-9_]{3,16}",message="用户名为3-16位中文，英文字母和数字及_",groups={Register.class})
	@NotBlank(message="用户名不能为空",groups={Login.class})
	private String username;
	/**
	 * 昵称
	 */
	@Pattern(regexp="[\u4e00-\u9fa5_a-zA-Z0-9_]{3,16}",message="昵称为3-16位中文，英文字母和数字及_",groups={Register.class,UpdateUser.class}) 
	private String nickname;
	/**
	 * 密码
	 */
	@Pattern(regexp="^([a-fA-F0-9]{32})$",message="密码为6-16位英文字母和数字及_",groups={Register.class,UpdatePassword.class})
	@NotBlank(message="密码不能为空",groups={Login.class})
	private String password;
	/**
	 * 旧密码
	 */
	@Pattern(regexp="^([a-fA-F0-9]{32})$",message="旧密码为6-16位英文字母和数字及_",groups={Register.class,UpdatePassword.class}) 
	@TableField(exist=false)
	private String oldPassword;
	/**
	 * 性别
	 */
	@NotNull(message="请选择一个性别",groups={Register.class,UpdateUser.class}) 
	private int sex;
	/**
	 * 邮箱
	 */
	@Pattern(regexp="^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(.[a-zA-Z0-9-]+)*.[a-zA-Z0-9]{2,6}$",message="请输入正确的邮箱",groups={Register.class,UpdateUser.class}) 
	private String email;
	/**
	 * 联系方式
	 */
	@Pattern(regexp="^[1][0-9]{10}$",message="请输入正确的手机号",groups={Register.class,UpdateUser.class}) 
	private String phone;
	/**
	 * 0待审核,1审核通过,2审核不通过,3冻结
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	@TableField(value="create_time",update="%s")
	private Date createTime;

	public User() {
		super();
	}

	public Integer getuId() {
		return uId;
	}

	public User setuId(Integer uId) {
		this.uId = uId;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getNickname() {
		return nickname;
	}

	public User setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public User setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public User setPhone(String phone) {
		this.phone = phone;
		return this;
	}


	public int getSex() {
		return sex;
	}

	public User setSex(int sex) {
		this.sex = sex;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public User setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public User setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.uId;
	}

	@Override
	public String toString() {
		return "User{" +
				", uId=" + uId +
				", username=" + username +
				", password=" + password +
				", phone=" + phone +
				", status=" + status +
				"}";
	}

	/**
	 * 检验分组
	 */
	public interface Login{};
	public interface Register{};
	public interface UpdateUser{};
	public interface UpdatePassword{};
}
