package com.xavier.fast.dao;

import com.xavier.fast.common.mybatis.MyBatisDao;
import com.xavier.fast.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserMapper extends MyBatisDao {

    public UserMapper () {
        super("TBL_USER_INFO");
    }

    public int deleteByPrimaryKey(Long id){
        return super.delete("deleteByPrimaryKey", id);
    }

    public int insert(User record){
        return super.insert("insert", record);
    }

    public int insertSelective(User record){
        return super.insert("insertSelective", record);
    }

    public User selectByPrimaryKey(Long id){
        return super.get("selectByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(User record){
        return super.update("updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(User record){
        return super.update("updateByPrimaryKey", record);
    }

    public User getUserByInviteCode(String value){
        return super.get("getUserByInviteCode", value);
    }

    public User getUserByOpenid(String value){
        return super.get("getUserByOpenid", value);
    }

    public List<User> getUserListByParams(Map<String, Object> params){
        return super.queryForList("getUserListByParams", params);
    }
}