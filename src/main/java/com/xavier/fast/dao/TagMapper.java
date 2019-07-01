package com.xavier.fast.dao;

import com.xavier.fast.common.mybatis.MyBatisDao;
import com.xavier.fast.entity.tag.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagMapper extends MyBatisDao {

    public TagMapper() {
        super("TBL_TAG");
    }

    public int insert(Tag record){
        return super.insert("insert", record);
    }

    public int insertSelective(Tag record){
        return super.insert("insertSelective", record);
    }

    public Tag selectByPrimaryKey(Integer id) {
        return super.get("selectByPrimaryKey", id);
    }

    public int queryTotalCount(Tag record) {
        return super.get("queryTotalCount", record);
    }

    public List<Tag> findTagList(Tag record) {
        return super.queryForList("findTagList", record);
    }
}