package com.xavier.fast.entity.order;

import java.io.Serializable;

/**
* @Description:    订单基类
* @Author:         Wang
* @CreateDate:     2019/7/17 10:23
* @UpdateUser:
* @UpdateDate:     2019/7/17 10:23
* @UpdateRemark:
* @Version:        1.0
*/
public class OrderBase implements Serializable {

    private static final long serialVersionUID = -3098547509871287899L;

    /**
     * 仅限前台展示用
     */
    public static enum SHOW_ORDER_STATUS{
        wait_receive("1", "WAITRECEIVE", "待收货"),
        wait_settle("2", "WAITSETTLE", "待结算"),
        validate("3", "VALIDATE", "已生效"),//对应订单的提现状态0-待提现
        invalidate("4", "INVALIDATE", "已失效"),
        wait_cash_back("5", "WAITCASHBACK", "待提现"),//对应订单的提现状态0 - 待提现
        cash_back_success("6", "CASHBACKSUCCESS", "提现成功"),//对应订单的提现状态1 - 提现成功
        cash_back_fail("7", "CASHBACKFAIL", "提现中");//对应订单的提现失败-1 - 提现失败

        private String code;
        private String name;
        private String cnName;

        private SHOW_ORDER_STATUS(String code, String name, String cnName){
            this.code = code;
            this.name = name;
            this.cnName = cnName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCnName() {
            return cnName;
        }

        public void setCnName(String cnName) {
            this.cnName = cnName;
        }
    }

    /**
     * 订单状态
     * 拼多多{
     *     -1 未支付; 0-已支付；1-已成团；2-确认收货；
     *     3-审核成功；4-审核失败（不可提现）；
     *     5-已经结算；8-非多多进宝商品（无佣金订单）
     * }
     */
    public static enum ORDER_STATUS{
        wait_receive("1", "WAITRECEIVE", "待收货"),
        //待结算=拼多多已结算+暂不可提现
        wait_settle("11", "WAITSETTLE", "待结算"),
        invalidate("4", "INVALIDATE", "已失效"),
        settled("5", "settled", "已结算");
        //已生效 == 待提现

        private String code;
        private String name;
        private String cnName;

        private ORDER_STATUS(String code, String name, String cnName){
            this.code = code;
            this.name = name;
            this.cnName = cnName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCnName() {
            return cnName;
        }

        public void setCnName(String cnName) {
            this.cnName = cnName;
        }
    }

    /**
     * 订单提现状态
     */
    public static enum ORDER_CASH_STATUS{
        //已生效 == 待提现
        wait_cash_back(0, "WAITCASHBACK", "待提现"),
        cash_back_success(1, "CASHBACKSUCCESS", "提现成功"),
        cash_back_fail(-1, "CASHBACKFAIL", "提现中");//提现失败

        private int code;
        private String name;
        private String cnName;

        private ORDER_CASH_STATUS(int code, String name, String cnName){
            this.code = code;
            this.name = name;
            this.cnName = cnName;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCnName() {
            return cnName;
        }

        public void setCnName(String cnName) {
            this.cnName = cnName;
        }
    }

    /**
     * 订单支付状态
     */
    public static enum ORDER_PAYMENT_STATUS{
        wait_pay("0", "WAITCASHBACK", "待支付"),
        pay_success("1", "CASHBACKSUCCESS", "支付成功");

        private String code;
        private String name;
        private String cnName;

        private ORDER_PAYMENT_STATUS(String code, String name, String cnName){
            this.code = code;
            this.name = name;
            this.cnName = cnName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCnName() {
            return cnName;
        }

        public void setCnName(String cnName) {
            this.cnName = cnName;
        }
    }
}
