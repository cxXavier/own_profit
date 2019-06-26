package com.xavier.fast.mapper;

import com.xavier.fast.entity.user.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserByInviteCode(String value);

    User getUserByOpenid(String value);
}