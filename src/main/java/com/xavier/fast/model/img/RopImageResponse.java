package com.xavier.fast.model.img;

import java.io.Serializable;

/**
 * @Description:    图片
 * @Author:         Wang
 * @CreateDate:     2019/6/26 15:25
 * @UpdateUser:
 * @UpdateDate:     2019/6/26 15:25
 * @UpdateRemark:
 * @Version:        1.0
 */
public class RopImageResponse implements Serializable {

    private static final long serialVersionUID = -6798662839902365266L;
    /**
     * 图片名称
     */
    private String imgName;

    /**
     * 图片链接
     */
    private String imgUrl;

    public RopImageResponse() {
    }

    public RopImageResponse(String imgName, String imgUrl) {
        this.imgName = imgName;
        this.imgUrl = imgUrl;
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

    @Override
    public String toString() {
        return "RopImageResponse{" +
                "imgName='" + imgName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
