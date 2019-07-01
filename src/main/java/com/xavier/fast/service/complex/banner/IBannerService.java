package com.xavier.fast.service.complex.banner;

import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.img.RopImageRequest;
import com.xavier.fast.model.img.RopImageResponse;

import java.util.List;

/**
* @Description:    banner
* @Author:         Wang
* @CreateDate:     2019/6/26 15:21
* @UpdateUser:
* @UpdateDate:     2019/6/26 15:21
* @UpdateRemark:
* @Version:        1.0
*/
public interface IBannerService {

    public RopResponse<RopImageResponse> getImgList(RopRequestBody<RopImageRequest> goodsRequest);
}
