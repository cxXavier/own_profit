package com.xavier.fast.redis;


import com.xavier.fast.utils.KeyBuilder;
import com.xavier.fast.utils.KeyGenerator;

/**
 * @author yirenjie
 * createDate:  2018/11/8
 */
public final class RedisConstants {

    private RedisConstants(){}


    public static final String GOODS_POOL_KEY = "MINIPROGRAM:GOODSPOOL:LIST"; // 商品池redisKey
    public static final String TAG_CONFIG_KEY = "MINIPROGRAM:TAG:CONFIG"; // 标签配置
    public static final String BANNER_CONFIG_KEY = "MINIPROGRAM:BANNER:LIST"; // banner配置
    public static final String CHANNEL_KEY = "MINIPROGRAM:CHANNEL:LIST"; // 首页栏目配置key
    public static final String REBATE_CONFIG_KEY = "MINIPROGRAM:REBATE:LIST"; // 返利配置,根据商品id; 占位符0为默认配置
    public static final String POP_LIST_KEY = "MINIPROGRAM:POP:LIST"; // pop模拟数据key
    public static final String PDD_GOODS_LIST_CACHE_KEY = "MINIPROGRAM:PDD_GOODS:LIST:%s"; // 拼多多查询商品列表
    public static final String PDD_GOODS_DETAIL_CACHE_KEY = "MINIPROGRAM:PDD_GOODS:DETAIL:%s"; // 拼多多商品详情
    public static final String PDD_THEME_GOODS_CACHE_KEY = "MINIPROGRAM:PDD:THEME:GOODS:%s"; // 拼多多主题商品池

    public static final String FREE_TIMES = "MINIPROGRAM:FREE:TIMES"; // 配置用户0元购次数
    public static final String SHARE_FREE_TIMES = "MINIPROGRAM:SHARE:FREE:TIMES"; // 用户通过分享可获取次数
    public static final String INIT_FREE_TIMES = "MINIPROGRAM:INIT:FREE:TIMES"; // 用户初始赠予0元购次数
    public static final String FREE_MAX_AMOUNT = "MINIPROGRAM:FREE:MAX:AMOUNT"; // 用户0元购上限优惠金额,单位分
    public static final String HANDING_FREE_ORDER = "MINIPROGRAM:HANDING:FREE:ORDER:%s"; // 用户0元购下单后设置key,下单成功后扣除0元购机会并删除key


    public static class User{

        /**
         * SESSION时长 1小时
         */
        public static final Integer SESSION_TIME = 60 * 60 ;

        /**
         * 用户信息 HASH
         * OPENID : USERDAO
         * @return
         */
        public static KeyGenerator getUserInfo(){
            return () -> KeyBuilder.build("MINIPROGRAM", "USER", "CACHE", "INFO");
        }

        /**
         * SESSION信息 HASH
         * sessionId :  openid
         * @return
         */
        public static KeyGenerator getSessionId(String openid,String sessionId){
            return () -> KeyBuilder.build("MINIPROGRAM", "USER", "SESSIONID",openid,sessionId);
        }

    }


    public static final class Order{
        /**
         * 订单状态缓存
         * orderSn : lastUpdatetime|orderStatus;
         * @return
         */
        public static KeyGenerator getOrderLastUpdatetimeAndStatus(){
            return () -> KeyBuilder.build("MINIPROGRAM", "ORDER", "UPDATETIMEANDSTATUS");
        }

        /**
         * 根据商品ID获取返利配置，
         * goodsId 为0时为默认配置
         * @return
         */
        public static KeyGenerator getDefaultRebateConfig(){
            return () -> KeyBuilder.build("MINIPROGRAM", "DEFAULT", "REBATE");
        }


        /**
         * 订单增量同步接口上次接口时间（防止接口调用太频繁）
         * @return
         */
        public static KeyGenerator getOrderIncrementSynTime(){
            return () -> KeyBuilder.build("MINIPROGRAM", "ORDER", "INCREAMENSYNTIME");
        }

        /**
         * 同步订单加锁
         * @param customParameters
         * @return
         */
        public static KeyGenerator getOrderSyncLock(String customParameters){
            return () -> KeyBuilder.build("MINIPROGRAM", "ORDER", "LOCK", customParameters);
        }
    }


    /**
     * 社交关系;redids缓存
     */
    public static final class Friend{

        /**
         * 直接好友的信息
         * 好友openId : 好友信息
         * @return
         */
        public static KeyGenerator getDirectFriend(String openid){
            return () -> KeyBuilder.build("MINIPROGRAM", "FRIEND", "DIRECT",openid);
        }

        /**
         * 间接好友的信息
         * 间接好友openId : 好友信息
         * @return
         */
        public static KeyGenerator getIndirectFriend(String openid){
            return () -> KeyBuilder.build("MINIPROGRAM", "FRIEND", "INDIRECT",openid);
        }

    }

    /**
     * 钱包模块
     */
    public static final class Wallet{

        /**
         * 钱包相关信息暂未使用
         * @return
         */
        public static KeyGenerator getWalletInfo(){
            return () -> KeyBuilder.build("MINIPROGRAM", "WALLET", "INFO");
        }

        /**
         * 已提现金额或冻结金额
         * @return
         */
        public static KeyGenerator getFreezeOrUsed(){
            return () -> KeyBuilder.build("MINIPROGRAM", "WALLET", "FREEZEORUSED");
        }

        public static KeyGenerator getWalletToken(String openid){
            return () -> KeyBuilder.build("MINIPROGRAM", "WALLET", "TOKEN",openid);
        }


        /**
         * 是否重试开关
         * @return
         */
        public static KeyGenerator getRetrySwitch(){
            return () -> KeyBuilder.build("MINIPROGRAM", "WALLET", "RETRY","SWITCH");
        }

        /**
         * 提现锁
         * @param openid
         * @return
         */
        public static KeyGenerator getWithdrawLongck(String openid){
            return () -> KeyBuilder.build("MINIPROGRAM", "WALLET", "LOCK", openid);
        }

    }

    public static final class Share{

        /**
         * 提现锁
         * @param openid
         * @return
         */
        public static KeyGenerator getShareInfo(){
            return () -> KeyBuilder.build("MINIPROGRAM", "SHARE");
        }
    }


    public static final class Favorite{


        /**
         * 我的收藏hash
         * @param openid
         * @return
         */
        public static KeyGenerator getMyFavorite(String openid){
            return () -> KeyBuilder.build("MINIPROGRAM", "MYFAVORITE",openid);
        }
    }

    public static final class Wechat{

        /**
         * ACCESS_CODE有效时间2小时
         */
        public static final Integer VALID_TIME = 60 * 60 * 2 - 10 ;

        /**
         * 获取accessToken
         * @return
         */
        public static KeyGenerator getAccessToken(){
            return () -> KeyBuilder.build("MINIPROGRAM", "WECHAT","ACCESSTOKEN");
        }



    }


    public static final class LandPage{

        public static KeyGenerator getLandPageConfig(){
            return () -> KeyBuilder.build("MINIPROGRAM", "LANDPAGE","CONFIG");
        }


    }

}
