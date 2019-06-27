package com.xavier.fast.service.complex.banner.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.img.RopImageRequest;
import com.xavier.fast.model.img.RopImageResponse;
import com.xavier.fast.service.complex.banner.IBannerService;
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

    @ApiMethod(method = "api.pinke.complex.banner.getImgList", version = "1.0.0")
    public RopResponse<List<RopImageResponse>> getImgList(RopRequestBody<RopImageRequest> goodsRequest) {

        List<RopImageResponse> imgList = new ArrayList<>();
        RopImageResponse img = new RopImageResponse();
        img.setImgName("test");
        img.setImgUrl("https://www.baidu.com");
        imgList.add(img);

        return RopResponse.createSuccessRep("1", "获取banner信息成功", "1.0.0", imgList);
    }
}
