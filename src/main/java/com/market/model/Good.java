package com.market.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

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
 * @since 2018-04-16
 */
public class Good extends Model<Good> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "g_id", type = IdType.AUTO)
	private Integer gId;
	/**
	 * 商品名称
	 */
	@TableField("good_name")
	@Length(min=1, max=30,message="商品名称为1-30个字符",groups={Addition.class,Edition.class}) 
	private String goodName;
	/**
	 * 商品价格
	 */
	@TableField("good_price")
	@Min(value = 1,message="商品价格必须大于0",groups={Addition.class,Edition.class})
	private Integer goodPrice;
	/**
	 * 商品数量
	 */
	@TableField("good_count")
	@Min(value = 1,message="商品数量必须大于0",groups={Addition.class,Edition.class})
	private Integer goodCount;
	/**
	 * 商品余量
	 */
	@TableField("good_surplus")
	@Min(value = 1,message="商品数量必须大于0",groups={Addition.class,Edition.class})
	private Integer goodSurplus;
	/**
	 * 商品描述
	 */
	@TableField("good_description")
	@Length(min=1, max=200,message="商品名称为1-200个字符",groups={Addition.class,Edition.class}) 
	private String goodDescription;
	/**
	 * 商品详情
	 */
	@TableField("good_content")
	private String goodContent;
	/**
	 * 商品图片
	 */
	@TableField("good_pics")
	@NotBlank(message="请上传一张商品图片",groups={Addition.class,Edition.class})
	private String goodPics;
	/**
	 * 0 待审核,1 审核通过,2审核不通过,3下架商品
	 */
	@TableField("status")
	private Integer status;
	/**
	 * 所属用户id
	 */
	@TableField("u_id")
	private Integer uId;
	/**
	 * 所属用户实体
	 */
	@TableField(exist = false)
	private User fUser;
	/**
	 * 所属分类id
	 */
	@TableField("c_id")
	@Min(value = 1,message="请选择一个分类",groups={Addition.class,Edition.class})
	private Integer cId;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	@TableField(value="create_time",update="%s")
	private Date createTime;

	public Integer getgId() {
		return gId;
	}

	public Good setgId(Integer gId) {
		this.gId = gId;
		return this;
	}

	public String getGoodName() {
		return goodName;
	}

	public Good setGoodName(String goodName) {
		this.goodName = goodName;
		return this;
	}

	public Integer getGoodPrice() {
		return goodPrice;
	}

	public Good setGoodPrice(Integer goodPrice) {
		this.goodPrice = goodPrice;
		return this;
	}

	public Integer getGoodCount() {
		return goodCount;
	}

	public Good setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
		return this;
	}

	public Integer getGoodSurplus() {
		return goodSurplus;
	}

	public Good setGoodSurplus(Integer goodSurplus) {
		this.goodSurplus = goodSurplus;
		return this;
	}

	public String getGoodDescription() {
		return goodDescription;
	}

	public Good setGoodDescription(String goodDescription) {
		this.goodDescription = goodDescription;
		return this;
	}

	public String getGoodContent() {
		return goodContent;
	}

	public Good setGoodContent(String goodContent) {
		this.goodContent = goodContent;
		return this;
	}

	public String getGoodPics() {
		return goodPics;
	}

	public Good setGoodPics(String goodPics) {
		this.goodPics = goodPics;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public Good setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public Integer getuId() {
		return uId;
	}

	public Good setuId(Integer uId) {
		this.uId = uId;
		return this;
	}

	public User getFUser() {
		return fUser;
	}

	public Good setFUser(User fUser) {
		this.fUser = fUser;
		return this;
	}

	public Integer getcId() {
		return cId;
	}

	public Good setcId(Integer cId) {
		this.cId = cId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Good setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.gId;
	}

	@Override
	public String toString() {
		return "Good{" +
				", gId=" + gId +
				", goodName=" + goodName +
				", goodPrice=" + goodPrice +
				", goodCount=" + goodCount +
				", goodSurplus=" + goodSurplus +
				", goodDescription=" + goodDescription +
				", goodPics=" + goodPics +
				", status=" + status +
				", uId=" + uId +
				", user=" + fUser +
				", cId=" + cId +
				", createTime=" + createTime +
				"}";
	}

	public interface Addition{};
	public interface Edition{};
}
