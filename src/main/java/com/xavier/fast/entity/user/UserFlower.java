package com.xavier.fast.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:    用户鲜花开支
 * @Author:         Wang
 * @CreateDate:     2019/7/2 17:47
 * @UpdateUser:
 * @UpdateDate:     2019/7/2 17:47
 * @UpdateRemark:
 * @Version:        1.0
 */
public class UserFlower implements Serializable {
    private static final long serialVersionUID = -4924238883850737328L;

    private Integer id;

    private String openId;

    private String unioinId;

    /**
     * 鲜花数量
     */
    private Integer flowers;

    /**
     * 开支类型（增加/减少）
     */
    private String costType;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnioinId() {
        return unioinId;
    }

    public void setUnioinId(String unioinId) {
        this.unioinId = unioinId;
    }

    public Integer getFlowers() {
        return flowers;
    }

    public void setFlowers(Integer flowers) {
        this.flowers = flowers;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static enum COST_TYPE{
        INCREASE,DECREASE
    }

}
