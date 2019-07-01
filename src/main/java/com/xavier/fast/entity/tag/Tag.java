package com.xavier.fast.entity.tag;

import java.io.Serializable;
import java.util.Date;

/**
* @Description:    标签（类目）
* @Author:         Wang
* @CreateDate:     2019/6/30 11:58
* @UpdateUser:
* @UpdateDate:     2019/6/30 11:58
* @UpdateRemark:
* @Version:        1.0
*/
public class Tag implements Serializable {

    private static final long serialVersionUID = -1556321104514449951L;

    private Integer id;

    /**
     * 父id
     */
    private Integer parentid;

    /**
     * 标签名
     */
    private String name;

    /**
     * 分类图片地址
     */
    private String imageurl;

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
    private Date created;

    /**
     * 修改时间
     */
    private Date updated;

    private Long startRow;

    private Long endRow;

    public Tag(Integer id, Integer parentid, String name, String imageurl,
               Byte visible, Integer order, Date created, Date updated) {
        this.id = id;
        this.parentid = parentid;
        this.name = name;
        this.imageurl = imageurl;
        this.visible = visible;
        this.order = order;
        this.created = created;
        this.updated = updated;
    }

    public Tag() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getStartRow() {
        return startRow;
    }

    public void setStartRow(Long startRow) {
        this.startRow = startRow;
    }

    public Long getEndRow() {
        return endRow;
    }

    public void setEndRow(Long endRow) {
        this.endRow = endRow;
    }
}