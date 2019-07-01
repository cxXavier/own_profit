package com.xavier.fast.entity.img;

import java.io.Serializable;
import java.util.Date;

/**
* @Description:    图片
* @Author:         Wang
* @CreateDate:     2019/7/1 11:14
* @UpdateUser:
* @UpdateDate:     2019/7/1 11:14
* @UpdateRemark:
* @Version:        1.0
*/
public class Image implements Serializable{
    private static final long serialVersionUID = 7342648502365905508L;

    private Integer id;

    /**
     * 图片名称
     */
    private String imgName;

    /**
     * 主图
     */
    private String imgUrl;

    /**
     * 缩略图
     */
    private String thumbnailUrl;

    /**
     * 图片类型(用途)，例：BANNER
     */
    private String imgType;

    /**
     * 是否可见,1为可见
     */
    private Byte visible;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Image() {
    }

    public Image(Integer id, String imgName, String imgUrl, String thumbnailUrl,
                 String imgType, Byte visible, Integer order, Date createTime, Date updateTime) {
        this.id = id;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.imgType = imgType;
        this.visible = visible;
        this.order = order;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public Byte getVisible() {
        return visible;
    }

    public void setVisible(Byte visible) {
        this.visible = visible;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public static enum IMG_TYPE{
        BANNER;
    }
}
