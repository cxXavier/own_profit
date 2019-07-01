package com.xavier.fast.service.complex.banner.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.dao.ImgMapper;
import com.xavier.fast.entity.img.Image;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.img.RopImageRequest;
import com.xavier.fast.model.img.RopImageResponse;
import com.xavier.fast.service.complex.banner.IBannerService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @Description:    banner
* @Author:         Wang
* @CreateDate:     2019/6/26 15:32
* @UpdateUser:
* @UpdateDate:     2019/6/26 15:32
* @UpdateRemark:
* @Version:        1.0
*/
@Service
public class BannerServiceImpl implements IBannerService {

    @Autowired
    private ImgMapper imgMapper;

    @ApiMethod(method = "api.pinke.complex.banner.getImgList", version = "1.0.0")
    public RopResponse<RopImageResponse> getImgList(RopRequestBody<RopImageRequest> goodsRequest) {
        RopImageResponse response = new RopImageResponse();
        Image queryVo = new Image();
        queryVo.setImgType(goodsRequest.getT().getImgType());
        List<Image> imageList = imgMapper.findImgList(queryVo);
        if(CollectionUtils.isEmpty(imageList)){
            return RopResponse.createFailedRep("-1", "获取banner数据失败", "1.0.0");
        }
        response.setImageList(imageList);
        return RopResponse.createSuccessRep("1", "获取banner数据成功", "1.0.0", response);
    }
}
