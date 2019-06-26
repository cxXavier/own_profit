package com.xavier.fast.mapper;

import com.xavier.fast.entity.userFormid.UserFormid;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFormidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFormid record);

    int insertSelective(UserFormid record);

    UserFormid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFormid record);

    int updateByPrimaryKey(UserFormid record);

    int save(@Param("openid") String openid, @Param("formId") String formId);

    List<UserFormid> getFormIdList(@Param("openid") String openid);

    boolean update2TimeLimit(@Param("formid") String formid);
    boolean udate2Used(@Param("formid") String formid);
}