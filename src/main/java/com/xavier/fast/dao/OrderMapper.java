package com.xavier.fast.dao;

import com.xavier.fast.common.mybatis.MyBatisDao;
import com.xavier.fast.entity.order.Order;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    public int insertSelective(Order record){
        return super.insert("insertSelective", record);
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

    /**
    * 更新订单提现状态
    * @author      Wang
    * @param       params
    * @return
    * @exception
    * @date        2019/7/4 17:45
    */
    public int updateOrderCashBackStatus(Map<String, Object> params){
        return super.update("updateOrderCashBackStatus", params);
    }

    /**
     * 获取最新时间，用于判断是否更新订单
     * @param params
     * @return
     */
    public Date getMinDate(Map<String, Object> params){
        return super.get("getMinDate", params);
    }
}