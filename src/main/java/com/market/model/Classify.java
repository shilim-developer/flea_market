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
public class Classify extends Model<Classify> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "c_id", type = IdType.AUTO)
    private Integer cId;
    @TableField("classify_name")
    private String classifyName;

    public Classify() {
		super();
	}

	public Integer getcId() {
        return cId;
    }

    public Classify setcId(Integer cId) {
        this.cId = cId;
        return this;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public Classify setClassifyName(String classifyName) {
        this.classifyName = classifyName;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.cId;
    }

    @Override
    public String toString() {
        return "Classify{" +
        ", cId=" + cId +
        ", classifyName=" + classifyName +
        "}";
    }
}
