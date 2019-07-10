package com.xavier.fast.model.user.flower;

import com.xavier.fast.entity.user.UserFlower;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    鲜花
* @Author:         Wang
* @CreateDate:     2019/7/3 10:13
* @UpdateUser:
* @UpdateDate:     2019/7/3 10:13
* @UpdateRemark:
* @Version:        1.0
*/
public class RopToTalFlowerResponse implements Serializable {

    private static final long serialVersionUID = 3680146004266335861L;

    /**
     * 预估鲜花数
     */
    private Integer estimateFlowers;

    /**
     * 待结算鲜花数
     */
    private Integer waitSettleFlowers;

    /**
     * 可使用鲜花数
     */
    private Integer spendableFlowers;

    public Integer getEstimateFlowers() {
        return estimateFlowers;
    }

    public void setEstimateFlowers(Integer estimateFlowers) {
        this.estimateFlowers = estimateFlowers;
    }

    public Integer getWaitSettleFlowers() {
        return waitSettleFlowers;
    }

    public void setWaitSettleFlowers(Integer waitSettleFlowers) {
        this.waitSettleFlowers = waitSettleFlowers;
    }

    public Integer getSpendableFlowers() {
        return spendableFlowers;
    }

    public void setSpendableFlowers(Integer spendableFlowers) {
        this.spendableFlowers = spendableFlowers;
    }
}
