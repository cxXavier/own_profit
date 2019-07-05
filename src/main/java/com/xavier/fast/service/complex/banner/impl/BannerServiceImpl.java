package com.xavier.fast.service.complex.banner.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.dao.ImgMapper;
import com.xavier.fast.entity.img.Image;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.base.RopResponseBody;
import com.xavier.fast.model.img.RopImageRequest;
import com.xavier.fast.model.img.RopImageResponse;
import com.xavier.fast.service.complex.banner.IBannerService;
import com.xavier.fast.utils.RopServiceHandler;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(BannerServiceImpl.class);

    @Autowired
    private ImgMapper imgMapper;

    /**
    * 方法实现说明
    * @author      
    * @param       
    * @return      
    * @exception   
    * @date        2019/7/4 19:53
    */
    @ApiMethod(method = "api.pinke.complex.banner.getImgList", version = "1.0.0")
    public RopResponse<RopResponseBody> getImgList(RopRequestBody<RopImageRequest> imgRequest) {
        RopResponseBody response = new RopResponseBody();
        Image queryVo = new Image();
        queryVo.setImgType(imgRequest.getT().getImgType());
        List<Image> imageList = imgMapper.findImgList(queryVo);
        if(CollectionUtils.isEmpty(imageList)){
            logger.warn("暂无banner数据");
            return RopResponse.createFailedRep("", "获取banner数据失败", "1.0.0");
        }
        response.setDataList(imageList);
        return RopResponse.createSuccessRep("", "获取banner数据成功", "1.0.0", response);
    }
}
