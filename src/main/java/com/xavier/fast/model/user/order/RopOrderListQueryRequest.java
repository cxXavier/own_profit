package com.xavier.fast.model.user.order;

import com.xavier.fast.annotation.validate.NotEmpty;
import com.xavier.fast.annotation.validate.NotNull;
import com.xavier.fast.annotation.validate.NotZero;
import com.xavier.fast.model.base.RopRequest;

import java.io.Serializable;

/**
* @Description:    订单
* @Author:         Wang
* @CreateDate:     2019/7/3 10:27
* @UpdateUser:
* @UpdateDate:     2019/7/3 10:27
* @UpdateRemark:
* @Version:        1.0
*/
public class RopOrderListQueryRequest extends RopRequest implements Serializable {


    private static final long serialVersionUID = 4013201414862522347L;

    @NotEmpty(message = "opendId不能为空")
    private String openId;

    /**
     * ALL - 查询全部
     * WAITRECEIVE - 待收货
     * WAITSETTLE - 待结算
     * VALIDATED - 已生效
     * INVALIDATE - 已失效
     */
    @NotEmpty(message = "queryType不能为空")
    private String queryType;

    private String unionId;

    @NotNull(message = "pageNum不能为空")
    @NotZero(message = "pageNum必须大于0")
    private Integer pageNum;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
