package com.xavier.fast.model.img;

import com.xavier.fast.model.base.RopRequest;

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
public class RopImageRequest extends RopRequest implements Serializable {


    private static final long serialVersionUID = -4986166352042555263L;

    private String imgType;

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }
}
