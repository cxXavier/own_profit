package com.xavier.fast.dao;

import com.xavier.fast.common.mybatis.MyBatisDao;
import com.xavier.fast.entity.user.UserReturnCashRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @Description:    用户提现记录
* @Author:         Wang
* @CreateDate:     2019/7/4 15:11
* @UpdateUser:
* @UpdateDate:     2019/7/4 15:11
* @UpdateRemark:
* @Version:        1.0
*/
@Repository
public class UserReturnCashRecordMapper extends MyBatisDao {

    public UserReturnCashRecordMapper() {
        super("TBL_USER_RETURN_CASH_RECORD");
    }

    public int insert(UserReturnCashRecord record){
        return super.insert("insert", record);
    }

    public UserReturnCashRecord selectByPrimaryKey(Integer id) {
        return super.get("selectByPrimaryKey", id);
    }

    public int queryTotalCount(UserReturnCashRecord record) {
        return super.get("queryTotalCount", record);
    }

    public List<UserReturnCashRecord> findRecordList(UserReturnCashRecord record) {
        return super.queryForList("findRecordList", record);
    }

    public UserReturnCashRecord selectByOpenIdAndOrderId(Map<String, Object> params) {
        return super.get("selectByOpenIdAndOrderId", params);
    }
}