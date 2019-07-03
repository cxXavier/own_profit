package com.xavier.fast.dao;

import com.xavier.fast.common.mybatis.MyBatisDao;
import com.xavier.fast.entity.order.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrderMapper extends MyBatisDao {

    public OrderMapper() {
        super("TBL_ORDER");
    }

    public int insert(Order record){
        return super.insert("insert", record);
    }

    public Order selectByPrimaryKey(Integer id) {
        return super.get("selectByPrimaryKey", id);
    }

    public int queryTotalCount(Order record) {
        return super.get("queryTotalCount", record);
    }

    public List<Order> findOrderList(Order record) {
        return super.queryForList("findOrderList", record);
    }

    public int update(Order record){
        return super.update("update", record);
    }

    public List<Order> findOrderListByParams(Map<String, Object> params) {
        return super.queryForList("findOrderListByParams", params);
    }
}