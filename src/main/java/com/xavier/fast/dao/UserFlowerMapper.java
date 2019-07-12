package com.xavier.fast.dao;

import com.xavier.fast.common.mybatis.MyBatisDao;
import com.xavier.fast.entity.user.UserFlower;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserFlowerMapper extends MyBatisDao {

    public UserFlowerMapper() {
        super("TBL_USER_FLOWER");
    }

    public int insert(UserFlower record){
        return super.insert("insert", record);
    }

    public int insertSelective(UserFlower record){
        return super.insert("insertSelective", record);
    }

    public UserFlower selectByPrimaryKey(Integer id) {
        return super.get("selectByPrimaryKey", id);
    }

    public int queryTotalCount(UserFlower record) {
        return super.get("queryTotalCount", record);
    }

    public List<UserFlower> findUserFlowerList(UserFlower record) {
        return super.queryForList("findUserFlowerList", record);
    }

    public List<UserFlower> findListByOpendIdOrParentId(UserFlower record) {
        return super.queryForList("findListByOpendIdOrParentId", record);
    }

    public int update(UserFlower record){
        return super.update("update", record);
    }
}