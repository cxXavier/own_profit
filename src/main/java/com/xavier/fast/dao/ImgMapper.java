package com.xavier.fast.dao;

import com.xavier.fast.common.mybatis.MyBatisDao;
import com.xavier.fast.entity.img.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImgMapper extends MyBatisDao {

    public ImgMapper() {
        super("TBL_IMG");
    }

    public int insert(Image record){
        return super.insert("insert", record);
    }

    public int insertSelective(Image record){
        return super.insert("insertSelective", record);
    }

    public Image selectByPrimaryKey(Integer id) {
        return super.get("selectByPrimaryKey", id);
    }

    public int queryTotalCount(Image record) {
        return super.get("queryTotalCount", record);
    }

    public List<Image> findImgList(Image record) {
        return super.queryForList("findImgList", record);
    }

    public int update(Image record){
        return super.update("update", record);
    }
}