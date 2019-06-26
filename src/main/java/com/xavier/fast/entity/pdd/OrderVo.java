package com.xavier.fast.entity.pdd;

/**
 * 虚拟订单vo
 */
public class OrderVo extends CommonDto {

    private Long orderId;

    private String appId;

    private String weAppIconUrl;

    private String bannerUrl;

    private String desc;

    private String sourceDisplayName;

    private String pagePath;

    private String userName;

    private String title;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWeAppIconUrl() {
        return weAppIconUrl;
    }

    public void setWeAppIconUrl(String weAppIconUrl) {
        this.weAppIconUrl = weAppIconUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSourceDisplayName() {
        return sourceDisplayName;
    }

    public void setSourceDisplayName(String sourceDisplayName) {
        this.sourceDisplayName = sourceDisplayName;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
