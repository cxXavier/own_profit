package com.xavier.fast.dao;

import com.xavier.fast.common.mybatis.MyBatisDao;
import com.xavier.fast.entity.userFormid.UserFormid;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserFormidMapper extends MyBatisDao {

    public UserFormidMapper () {
        super("TBL_USER_FORMID_INFO");
    }

    public UserFormid selectByPrimaryKey(Integer id){
        return super.get("selectByPrimaryKey", id);
    }

    public int deleteByPrimaryKey(Integer id){
        return super.delete("deleteByPrimaryKey", id);
    }

    public int insert(UserFormid record){
        return super.insert("insert", record);
    }

    public int insertSelective(UserFormid record){
        return super.insert("insertSelective", record);
    }

    public int updateByPrimaryKeySelective(UserFormid record){
        return super.update("updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(UserFormid record){
        return super.update("updateByPrimaryKey", record);
    }

    public List<UserFormid> getFormIdList(String openid){
        return super.queryForList("getFormIdList", openid);
    }

    public boolean udate2Used(String formid){
        return super.update("udate2Used", formid) > 0;
    }

    public UserFormid getLatestUsefulInfo(String openId){
        return super.get("getLatestUsefulInfo", openId);
    }
}