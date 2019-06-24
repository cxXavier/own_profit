package com.xavier.fast.utils;


/**
 * api系统错误码
 * Created by yirenjie
 *
 */
public enum ResponseCodeType {

//    SUCCESS("00000000", "成功"),
    HANDLING("00000001", "正在处理中，请您耐心等待"),
    SYSTEM_ERROR("10009999", "系统异常，请稍后再试"),
    OPERATION_NOT_EXECUTED("10009998", "操作未执行"),
    OPERATION_NOT_SUPPORTED("10009997", "操作不支持"),
    DATA_ANOMALY("10009996", "数据异常"),
    PARAM_ILLEGAL("10009995", "参数非法%s"),
    CHECK_SIGN_FAIL("10009994", "验签失败"),
    USER_NOT_LOGIN("10009993", "用户未登录"),
    USER_CASHBACKING("10100010","提现申请提交成功"),
    AMOUNT_NOT_ENOUGH("10100011", "提现金额不能大于可提现金额"),
    AMOUNT_LESS_THAN_THRESHOLD("10100012", "提现金额小于最小可提现金额"),
    BOOST_NOT_EXIST("10100013", "助力不存在"),
    BOOST_HAS_FINISHED("10100014", "助力已完成"),
    BOOST_HAS_NO_CHANCE("10100015", "助力机会不足"),
    BOOST_SELF_ILLEGAL("10100016", "不能给自己助力"),
    HAS_NO_FREE_CHANCE("10100017", "没有0元购机会"),
    BOOST_UNFINISHED("10100018", "助力未完成"),
    USER_NOT_EXIST("10100019", "用户不存在"),
    NOT_NEW_USER("10100020", "该用户不是新用户"),
    ;


    /**
     * 返回码
     */
    private String errorCode;

    /**
     * 返回描述
     */
    private String errorMsg;

    ResponseCodeType(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

//    public static boolean isSuccess(String resultCode) {
//        return SUCCESS.getErrorCode().equals(resultCode);
//    }

    public static boolean isHandling(String resultCode) {
        return HANDLING.getErrorCode().equals(resultCode);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
