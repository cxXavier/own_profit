package com.xavier.fast.dao;

import com.xavier.fast.common.mybatis.MyBatisDao;
import com.xavier.fast.entity.userFormid.UserFormid;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserFormidMapper extends MyBatisDao {

    public UserFormidMapper () {
        super("TBL_USER_FORMID_INFO");
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

    public UserFormid selectByPrimaryKey(Integer id){
        return super.get("selectByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(UserFormid record){
        return super.update("updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(UserFormid record){
        return super.update("updateByPrimaryKey", record);
    }

    //TODO
    public int save(@Param("openid") String openid, @Param("formId") String formId){
        return super.insert("save", openid);
    }

    public List<UserFormid> getFormIdList(@Param("openid") String openid){
        return super.queryForList("getFormIdList", openid);
    }

    public boolean update2TimeLimit(@Param("formid") String formid){
        return super.update("update2TimeLimit", formid) > 0;
    }

    public boolean udate2Used(@Param("formid") String formid){
        return super.update("udate2Used", formid) > 0;
    }
}