package com.market.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liwei
 * @since 2018-05-14
 */
public class Admin extends Model<Admin> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 管理员账号
     */
    @TableField("username")
    private String username;
    /**
     * 管理员密码
     */
    @TableField("password")
    private String password;


    public Integer getId() {
        return id;
    }

    public Admin setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Admin setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Admin setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Admin{" +
        ", id=" + id +
        ", username=" + username +
        ", password=" + password +
        "}";
    }
}
