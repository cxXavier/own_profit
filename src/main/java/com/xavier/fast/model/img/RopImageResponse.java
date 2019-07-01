package com.xavier.fast.model.img;

import com.xavier.fast.entity.img.Image;

import java.io.Serializable;
import java.util.List;

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

    private List<Image> imageList;

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
