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
import com.xavier.fast.model.user.flower.RopToTalFlowerResponse;
import com.xavier.fast.service.user.IUserFlowerService;
import com.xavier.fast.utils.CalFlowerUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(UserFlowerServiceImpl.class);

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
        RopPrenticeResponse response = new RopPrenticeResponse();

        String openId = flowerRequest.getT().getOpenId();
        String unionId = flowerRequest.getT().getUnionId();

        log.info("openId=" + openId + ",unionId=" + unionId);

        //查询我的鲜花数
        UserFlower uf = new UserFlower();
        uf.setOpenId(flowerRequest.getT().getOpenId());
        uf.setParentOpenId(flowerRequest.getT().getOpenId());
        List<UserFlower> flowerList = userFlowerMapper.findListByOpendIdOrParentId(uf);
        if(CollectionUtils.isNotEmpty(flowerList)){
            response.setTotalFlowers(CalFlowerUtils.calTotalFlowers(flowerList));
        }

        //查询徒弟列表
        Map<String, Object> params = new HashMap<>();
        params.put("parentOpenid", openId);
        params.put("parentUnionid", unionId);
        List<User> userList = userMapper.getUserListByParams(params);
        if(CollectionUtils.isEmpty(userList)){
            return RopResponse.createFailedRep("", "暂无徒弟", "1.0.0");
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
            return RopResponse.createFailedRep("", "暂无徒弟订单", "1.0.0");
        }


        List<Prentice> prenticeList = new ArrayList<>();
        Prentice prentice;

        //添加用户信息、贡献鲜花数、待结算鲜花数
        for(User u : userList){
            prentice = new Prentice();
            prentice.setAvatar(u.getAvatar());
            prentice.setNickname(u.getNickname());
            prentice.setOpenid(u.getOpenid());
            prentice.setUnionid(u.getUnionid());
            prentice.setContributionFlower(getFlower(orderList, "5", u.getOpenid()));
            prentice.setWaitSettleFlower(getFlower(orderList, "2", u.getOpenid()));
            prenticeList.add(prentice);
        }

        response.setDataList(prenticeList);
        return RopResponse.createSuccessRep("", "查询徒弟列表成功", "1.0.0", response);
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
        u.setParentOpenId(flowerRequest.getT().getOpenId());
        List<UserFlower> flowerList = userFlowerMapper.findListByOpendIdOrParentId(u);
        if(CollectionUtils.isEmpty(flowerList)){
            return RopResponse.createFailedRep("", "暂无鲜花明细", "1.0.0");
        }
        response.setDataList(flowerList);
        return RopResponse.createSuccessRep("", "查询鲜花明细成功", "1.0.0", response);
    }

    /**
     * 鲜花数量汇总
     * @author      Wang
     * @param       flowerRequest
     * @return
     * @exception
     * @date        2019/7/3 10:17
     */
    @ApiMethod(method = "api.pinke.user.flower.getTotalFlowers", version = "1.0.0")
    public RopResponse<RopToTalFlowerResponse> getTotalFlowers(RopRequestBody<RopFlowerRequest> flowerRequest) {
        RopToTalFlowerResponse response = new RopToTalFlowerResponse();
        String openId = flowerRequest.getT().getOpenId();

        //获取可使用鲜花
        UserFlower u = new UserFlower();
        u.setOpenId(flowerRequest.getT().getOpenId());
        u.setParentOpenId(flowerRequest.getT().getOpenId());
        List<UserFlower> flowerList = userFlowerMapper.findListByOpendIdOrParentId(u);
        if(CollectionUtils.isEmpty(flowerList)){
            log.warn("暂无可使用鲜花,openId=" + openId);
        }
        int sf = CalFlowerUtils.calTotalFlowers(flowerList);
        response.setSpendableFlowers(sf);

        //获取待结算鲜花
        Order order = new Order();
        order.setParentOpenId(openId);
        order.setOrderStatus("2");
        List<Order> orderList = orderMapper.findOrderList(order);
        if(CollectionUtils.isEmpty(orderList)){
            log.warn("暂无待结算鲜花,openId=" + openId);
        }
        int wf = getWaitSettleFlower(orderList);
        response.setWaitSettleFlowers(wf);

        //设置预估鲜花 = 可使用鲜花 + 待结算鲜花
        int ef = sf + wf;
        response.setEstimateFlowers(ef);

        log.info("可使用鲜花:" + sf + ",待结算鲜花:" + wf + ",预估鲜花:" + ef + ",openId=" + openId);

        return RopResponse.createSuccessRep("", "归总鲜花数量成功", "1.0.0", response);
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

    /**
     * 归总鲜花
     * @author      Wang
     * @param       orderList
     * @return
     * @exception
     * @date        2019/7/3 0:57
     */
    private int getWaitSettleFlower(List<Order> orderList){
        int result = 0;
        for(Order o : orderList){
            result += o.getContributionFlower();
        }
        return result;
    }
}
