package com.market.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

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
    @NotNull(message="用户名不能为空",groups={Login.class,Register.class})
    private String username;
    /**
     * 密码
     */
    @NotNull(message="密码不能为空",groups={Login.class,Register.class})
    private String password;
    /**
     * 旧密码
     */
    @TableField(exist=false)
    private String oldPassword;
	/**
     * 联系方式
     */
    @NotNull(message="手机号不能为空",groups={Register.class})
    private String phone;
    /**
     * 0待审核,1审核通过,2审核不通过,3冻结
     */
    private Integer status;
    
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

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public User setStatus(Integer status) {
        this.status = status;
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
}
