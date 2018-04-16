package com.market.model;

import java.io.Serializable;

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
public class Good extends Model<Good> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "g_id", type = IdType.AUTO)
    private Integer gId;
    /**
     * 商品名称
     */
    @TableField("good_name")
    private String goodName;
    /**
     * 商品价格
     */
    @TableField("good_price")
    private Integer goodPrice;
    /**
     * 商品数量
     */
    @TableField("good_count")
    private Integer goodCount;
    /**
     * 商品描述
     */
    @TableField("good_description")
    private String goodDescription;
    /**
     * 商品图片
     */
    @TableField("good_pics")
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
     * 所属分类id
     */
    @TableField("c_id")
    private Integer cId;


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

    public String getGoodDescription() {
        return goodDescription;
    }

    public Good setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
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

    public Integer getcId() {
        return cId;
    }

    public Good setcId(Integer cId) {
        this.cId = cId;
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
        ", goodDescription=" + goodDescription +
        ", goodPics=" + goodPics +
        ", status=" + status +
        ", uId=" + uId +
        ", cId=" + cId +
        "}";
    }
}
