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
    private String imageUrl;

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
    private Integer visible;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 跳转地址
     */
    private String linkUrl;

    /**
     * 跳转类型
     */
    private String linkType;

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

    public Image(Integer id, String imgName, String imageUrl, String thumbnailUrl,
                 String imgType, Integer visible, Integer order, Date createTime, Date updateTime) {
        this.id = id;
        this.imgName = imgName;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
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
