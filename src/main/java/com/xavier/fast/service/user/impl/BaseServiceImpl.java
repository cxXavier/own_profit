package com.xavier.fast.service.user.impl;

public class BaseServiceImpl {
    public static enum QUERY_TYPE{
        /**
         * ALL - 查询全部
         * WAITRECEIVE - 待收货
         * WAITSETTLE - 待结算
         * VALIDATED - 已生效
         * INVALIDATE - 已失效
         */
        ALL,WAITRECEIVE,WAITSETTLE,VALIDATED,INVALIDATE;
    }
}
