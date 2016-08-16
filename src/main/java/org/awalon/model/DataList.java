package org.awalon.model;

/**
 * Created by guming on 2016-08-15.
 */
public class DataList {
    private Integer GroupID;

    private String GroupName;

    private String appName;

    private String appVersion;

    private String appid;

    private String devName;
    private String href;
    private String picurl;

    private String r_href;

    private String rank;

    private String sAddTimes;
    private String sDesc;

    private String sDetail;

    private String sIconI6868;

    private String sImgUrl;

    private String sInnerName;

    private String sRawUrl;

    private String sShortName;

    private String sSize;

    private String sVersionCode;

    private String to_href;

    /**
     * 0 失败 1成功
     */
    private Integer code;

    /**
     * -1 未知
     * 0 非钓鱼
     * 1 钓鱼
     * 2 高风险，有钓鱼嫌疑
     */
    private Integer phish;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getPhish() {
        return phish;
    }

    public void setPhish(Integer phish) {
        this.phish = phish;
    }

    public Integer getGroupID() {
        return GroupID;
    }

    public void setGroupID(Integer groupID) {
        GroupID = groupID;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getR_href() {
        return r_href;
    }

    public void setR_href(String r_href) {
        this.r_href = r_href;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getsAddTimes() {
        return sAddTimes;
    }

    public void setsAddTimes(String sAddTimes) {
        this.sAddTimes = sAddTimes;
    }

    public String getsDesc() {
        return sDesc;
    }

    public void setsDesc(String sDesc) {
        this.sDesc = sDesc;
    }

    public String getsDetail() {
        return sDetail;
    }

    public void setsDetail(String sDetail) {
        this.sDetail = sDetail;
    }

    public String getsIconI6868() {
        return sIconI6868;
    }

    public void setsIconI6868(String sIconI6868) {
        this.sIconI6868 = sIconI6868;
    }

    public String getsImgUrl() {
        return sImgUrl;
    }

    public void setsImgUrl(String sImgUrl) {
        this.sImgUrl = sImgUrl;
    }

    public String getsInnerName() {
        return sInnerName;
    }

    public void setsInnerName(String sInnerName) {
        this.sInnerName = sInnerName;
    }

    public String getsRawUrl() {
        return sRawUrl;
    }

    public void setsRawUrl(String sRawUrl) {
        this.sRawUrl = sRawUrl;
    }

    public String getsShortName() {
        return sShortName;
    }

    public void setsShortName(String sShortName) {
        this.sShortName = sShortName;
    }

    public String getsSize() {
        return sSize;
    }

    public void setsSize(String sSize) {
        this.sSize = sSize;
    }

    public String getsVersionCode() {
        return sVersionCode;
    }

    public void setsVersionCode(String sVersionCode) {
        this.sVersionCode = sVersionCode;
    }

    public String getTo_href() {
        return to_href;
    }

    public void setTo_href(String to_href) {
        this.to_href = to_href;
    }
}
