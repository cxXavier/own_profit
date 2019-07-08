package com.xavier.fast.utils;

/**
* @Description:    鲜花计算工具类
* @Author:         Wang
* @CreateDate:     2019/7/8 16:32
* @UpdateUser:
* @UpdateDate:     2019/7/8 16:32
* @UpdateRemark:
* @Version:        1.0
*/
public class CalFlowerUtils {

    //贡献鲜花系数
    private static final int CONTRI_COEFFICIENT = 1;

    //提现鲜花系数
    private static final int CASH_COEFFICIENT = 4;

    /**
    * 贡献鲜花数
    * @author      Wang
    * @param       price 价格（分）
    * @return      
    * @exception   
    * @date        2019/7/8 16:34
    */
    public static Integer calContributionFlower(Long price){
        return calFlower(price, CONTRI_COEFFICIENT);
    }

    /**
     * 提现鲜花数
     * @author      Wang
     * @param       price 价格（分）
     * @return
     * @exception
     * @date        2019/7/8 16:34
     */
    public static Integer calCashFlower(Long price){
        return calFlower(price, CASH_COEFFICIENT);
    }

    /**
    * 计算鲜花数
    * 计算规则：鲜花 = 单价 * 响应系数
    * @author
    * @param       price 价格
    * @param       coefficient 系数
    * @return
    * @exception
    * @date        2019/7/8 17:21
    */
    private static Integer calFlower(Long price, Integer coefficient){
        if(price == null || price <= 0){
            return 0;
        }
        double flower = price.doubleValue() * coefficient / 100;
        int result = new Double(Math.floor(flower)).intValue();
        return result;
    }
}
