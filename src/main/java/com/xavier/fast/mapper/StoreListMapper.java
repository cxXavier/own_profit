package com.xavier.fast.mapper;

import com.xavier.fast.entity.StoreList;

import java.util.List;
import java.util.Map;

public interface StoreListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StoreList record);

    int insertSelective(StoreList record);

    StoreList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StoreList record);

    int updateByPrimaryKeyWithBLOBs(StoreList record);

    int updateByPrimaryKey(StoreList record);

    List<StoreList> selectUserList(Map<String, Object> map);

    void insertForeach(List<StoreList> list);
}