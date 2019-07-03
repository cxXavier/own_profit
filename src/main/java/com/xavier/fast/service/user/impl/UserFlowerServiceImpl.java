package com.xavier.fast.service.user.impl;

import com.xavier.fast.annotation.ApiMethod;
import com.xavier.fast.dao.OrderMapper;
import com.xavier.fast.dao.UserFlowerMapper;
import com.xavier.fast.dao.UserMapper;
import com.xavier.fast.entity.order.Order;
import com.xavier.fast.entity.user.Prentice;
import com.xavier.fast.entity.user.User;
import com.xavier.fast.entity.user.UserFlower;
import com.xavier.fast.model.base.RopRequestBody;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.model.user.flower.RopFlowerRequest;
import com.xavier.fast.model.user.flower.RopFlowerResponse;
import com.xavier.fast.model.user.flower.RopPrenticeResponse;
import com.xavier.fast.service.user.IUserFlowerService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description:    用户鲜花
* @Author:         Wang
* @CreateDate:     2019/7/2 18:21
* @UpdateUser:
* @UpdateDate:     2019/7/2 18:21
* @UpdateRemark:
* @Version:        1.0
*/
@Service
public class UserFlowerServiceImpl implements IUserFlowerService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserFlowerMapper userFlowerMapper;

    /**
    * 徒弟鲜花列表
    * @author      Wang
    * @param       flowerRequest
    * @return      
    * @exception   
    * @date        2019/7/2 19:41
    */
    @ApiMethod(method = "api.pinke.user.flower.getPrenticeListWithFlowers", version = "1.0.0")
    public RopResponse<RopPrenticeResponse> getPrenticeListWithFlowers(RopRequestBody<RopFlowerRequest> flowerRequest) {

        String openId = flowerRequest.getT().getOpenId();
        String unionId = flowerRequest.getT().getUnionId();

        //查询徒弟列表
        Map<String, Object> params = new HashMap<>();
        params.put("parentOpenid", openId);
        params.put("parentUnionid", unionId);
        List<User> userList = userMapper.getUserListByParams(params);
        if(CollectionUtils.isEmpty(userList)){
            return RopResponse.createFailedRep("-1", "暂无徒弟", "1.0.0");
        }

        //查询徒弟订单
        List<String> openIds = new ArrayList<>();
        for(User u : userList){
            openIds.add(u.getOpenid());
        }
        params.clear();
        params.put("openIds", openIds);
        List<Order> orderList = orderMapper.findOrderListByParams(params);
        if(CollectionUtils.isEmpty(orderList)){
            return RopResponse.createFailedRep("-1", "暂无徒弟订单", "1.0.0");
        }


//        //查询徒弟订单
//        Order order = new Order();
//        order.setParentOpenId(openId);
//        order.setParentUnionId(unionId);
//        List<Order> orderList = orderMapper.findOrderList(order);
//        if(CollectionUtils.isEmpty(orderList)){
//            return RopResponse.createFailedRep("-1", "暂无徒弟订单", "1.0.0");
//        }
//        Map<String, Integer> flowerMap = new HashMap<>();
//
//        for(Order o : orderList){
//            if("".equals(o.getOpenId()) && "".equals(o.getUnionId())){
//
//            }
//        }

        RopPrenticeResponse response = new RopPrenticeResponse();
        List<Prentice> prenticeList = new ArrayList<>();
        Prentice prentice = new Prentice();

        //添加用户信息、贡献鲜花数、待结算鲜花数
        for(User u : userList){
            prentice.setAvatar(u.getAvatar());
            prentice.setNickname(u.getNickname());
            prentice.setOpenid(u.getOpenid());
            prentice.setUnionid(u.getUnionid());
            prentice.setContributionFlower(getFlower(orderList, "5", u.getOpenid()));
            prentice.setWaitSettleFlower(getFlower(orderList, "2", u.getOpenid()));
            prenticeList.add(prentice);
        }

        response.setPrenticeList(prenticeList);
        return RopResponse.createSuccessRep("1", "查询徒弟列表成功", "1.0.0", response);
    }

    /**
    * 用户鲜花开支
    * @author      Wang
    * @param       flowerRequest
    * @return
    * @exception
    * @date        2019/7/3 10:17
    */
    @ApiMethod(method = "api.pinke.user.flower.getUserFlowers", version = "1.0.0")
    public RopResponse<RopFlowerResponse> getUserFlowers(RopRequestBody<RopFlowerRequest> flowerRequest) {
        RopFlowerResponse response = new RopFlowerResponse();
        UserFlower u = new UserFlower();
        u.setOpenId(flowerRequest.getT().getOpenId());
        List<UserFlower> flowerList = userFlowerMapper.findUserFlowerList(u);
        if(CollectionUtils.isEmpty(flowerList)){
            return RopResponse.createFailedRep("-1", "暂无鲜花明细", "1.0.0");
        }
        response.setFlowerList(flowerList);
        return RopResponse.createSuccessRep("1", "查询徒弟列表成功", "1.0.0", response);
    }

    /**
    * 归总鲜花
    * @author      Wang
    * @param       orderList
    * @param       orderStatus
    * @param       openId
    * @return
    * @exception
    * @date        2019/7/3 0:57
    */
    private int getFlower(List<Order> orderList, String orderStatus, String openId){
        int result = 0;
        for(Order o : orderList){
            if(orderStatus.equals(o.getOrderStatus()) && openId.equals(o.getOpenId())){
                result += o.getContributionFlower();
            }
        }
        return result;
    }
}