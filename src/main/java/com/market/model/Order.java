package com.market.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("m_order")
public class Order extends Model<Order> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "o_id", type = IdType.AUTO)
	private Integer oId;
	/**
	 * 订单号
	 */
	@TableField("order_number")
	private String orderNumber;
	/**
	 * 商品id
	 */
	@TableField("good_id")
	private Integer goodId;
	/**
	 * 商品名称
	 */
	@TableField("good_name")
	private String goodName;
	/**
	 * 商品数量
	 */
	@TableField("good_count")
	private Integer goodCount;
	/**
	 * 商品价格
	 */
	@TableField("good_price")
	private Integer goodPrice;
	/**
	 * 总价
	 */
	@TableField("total_money")
	private Integer totalMoney;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	@TableField("create_time")
	private Date createTime;
	/**
	 * 买家
	 */
	@TableField("buy_user")
	private String buyUser;
	/**
	 * 买家id
	 */
	@TableField("buy_user_id")
	private Integer buyUserId;
	/**
	 * 卖家
	 */
	@TableField("sold_user")
	private String soldUser;
	/**
	 * 买家id
	 */
	@TableField("sold_user_id")
	private Integer soldUserId;
	/**
	 * 地址
	 */
	@TableField("address")
	private String address;
	/**
	 * 联系方式
	 */
	@TableField("phone")
	private String phone;
	/**
	 * 支付序列号
	 */
	@TableField("pay_serials_number")
	private String paySerialsNumber;
	/**
	 * 状态
	 */
	@TableField("status")
	private Integer status;

	public Order() {
		super();
	}

	public Integer getoId() {
		return oId;
	}

	public Order setoId(Integer oId) {
		this.oId = oId;
		return this;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public Order setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
		return this;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public Order setGoodId(Integer goodId) {
		this.goodId = goodId;
		return this;
	}

	public String getGoodName() {
		return goodName;
	}

	public Order setGoodName(String goodName) {
		this.goodName = goodName;
		return this;
	}

	public Integer getGoodCount() {
		return goodCount;
	}

	public Order setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
		return this;
	}

	public Integer getGoodPrice() {
		return goodPrice;
	}

	public Order setGoodPrice(Integer goodPrice) {
		this.goodPrice = goodPrice;
		return this;
	}

	public Integer getTotalMoney() {
		return totalMoney;
	}

	public Order setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Order setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getBuyUser() {
		return buyUser;
	}

	public Order setBuyUser(String buyUser) {
		this.buyUser = buyUser;
		return this;
	}

	public Integer getBuyUserId() {
		return buyUserId;
	}

	public Order setBuyUserId(Integer buyUserId) {
		this.buyUserId = buyUserId;
		return this;
	}

	public String getSoldUser() {
		return soldUser;
	}

	public Order setSoldUser(String soldUser) {
		this.soldUser = soldUser;
		return this;
	}

	public Integer getSoldUserId() {
		return soldUserId;
	}

	public Order setSoldUserId(Integer soldUserId) {
		this.soldUserId = soldUserId;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Order setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public Order setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getPaySerialsNumber() {
		return paySerialsNumber;
	}

	public void setPaySerialsNumber(String paySerialsNumber) {
		this.paySerialsNumber = paySerialsNumber;
	}
	
	public Integer getStatus() {
		return status;
	}

	public Order setStatus(Integer status) {
		this.status = status;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.oId;
	}

	@Override
	public String toString() {
		return "Order{" +
				", oId=" + oId +
				", orderNumber=" + orderNumber +
				", goodId=" + goodId +
				", goodName=" + goodName +
				", goodCount=" + goodCount +
				", goodPrice=" + goodPrice +
				", totalMoney=" + totalMoney +
				", createTime=" + createTime +
				", buyUser=" + buyUser +
				", buyUserId=" + buyUserId +
				", soldUser=" + soldUser +
				", soldUserId=" + soldUserId +
				", address=" + address +
				", phone=" + phone +
				", paySerialsNumber=" + paySerialsNumber +
				", status=" + status +
				"}";
	}
}
