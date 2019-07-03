package com.xavier.fast.model.user.flower;

import com.xavier.fast.entity.user.Prentice;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    徒弟
* @Author:         Wang
* @CreateDate:     2019/7/2 19:37
* @UpdateUser:
* @UpdateDate:     2019/7/2 19:37
* @UpdateRemark:
* @Version:        1.0
*/
public class RopPrenticeResponse implements Serializable {
    private static final long serialVersionUID = -152484453209932985L;

    /**
     * 徒弟列表
     */
    private List<Prentice> prenticeList;

    public List<Prentice> getPrenticeList() {
        return prenticeList;
    }

    public void setPrenticeList(List<Prentice> prenticeList) {
        this.prenticeList = prenticeList;
    }
}
