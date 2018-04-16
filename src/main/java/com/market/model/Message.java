package com.market.model;

import java.io.Serializable;
import java.util.Date;

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
 * @since 2018-04-16
 */
public class Message extends Model<Message> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "m_id", type = IdType.AUTO)
    private Integer mId;
    /**
     * 回复内容
     */
    @TableField("content")
    private String content;
    /**
     * 创建时间
     */
    @TableField("create_tiem")
    private Date createTiem;
    /**
     * 来自用户id
     */
    @TableField("from_user")
    private Integer fromUser;
    /**
     * 目的用户id
     */
    @TableField("to_user")
    private Integer toUser;
    /**
     * 状态 0未读 1已读
     */
    @TableField("status")
    private Integer status;


    public Integer getmId() {
        return mId;
    }

    public Message setmId(Integer mId) {
        this.mId = mId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreateTiem() {
        return createTiem;
    }

    public Message setCreateTiem(Date createTiem) {
        this.createTiem = createTiem;
        return this;
    }

    public Integer getFromUser() {
        return fromUser;
    }

    public Message setFromUser(Integer fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public Integer getToUser() {
        return toUser;
    }

    public Message setToUser(Integer toUser) {
        this.toUser = toUser;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Message setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.mId;
    }

    @Override
    public String toString() {
        return "Message{" +
        ", mId=" + mId +
        ", content=" + content +
        ", createTiem=" + createTiem +
        ", fromUser=" + fromUser +
        ", toUser=" + toUser +
        ", status=" + status +
        "}";
    }
}
