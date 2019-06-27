package com.xavier.fast.service.search;

import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.search.RopSearchRequest;
import com.xavier.fast.model.search.RopSearchResponse;

/**
* @Description:    搜索
* @Author:         Wang
* @CreateDate:     2019/6/26 15:55
* @UpdateUser:
* @UpdateDate:     2019/6/26 15:55
* @UpdateRemark:
* @Version:        1.0
*/
public interface ISearchService {

    public RopResponse<RopSearchResponse> searchGoods(RopRequestBody<RopSearchRequest> searchRequest);
}
