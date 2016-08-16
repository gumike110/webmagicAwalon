package org.awalon.model;

import java.io.Serializable;
import java.util.Date;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

//@TargetUrl("http://top.chinaz.com/all/")
//@ExtractBy("//li[@class='clearfix']")
public class UrlRank implements Serializable {
	
    private Long id;
    
    //@ExtractBy("//div[@class='CentTxt']/h3[@class='rightTxtHead']/a/@title")
    private String siteName;
    
    //@ExtractBy("//div[@class='CentTxt']/h3[@class='rightTxtHead']/span/text()")
    private String siteUrl;
    
    //@ExtractBy("//div[@class='RtCPart clearfix']/p/a/text()/")
    private Integer alexaRank;
    
    //@ExtractBy("//div[@class='RtCRateCent']/strong/text()")
    private Integer chinazRank;
    
    //@ExtractBy("//div[@class='RtCRateCent']/span/text()")
    private String chinazScope;
    
    //@ExtractBy("//div[@class='CentTxt']/h3[@class='rightTxtHead']/a/@title")
    private Date createTime = new Date();
    
    //@ExtractBy("//div[@class='CentTxt']/h3[@class='rightTxtHead']/a/@title")
    private Date updateTime = new Date();

    private Boolean status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
    }

    public Integer getAlexaRank() {
        return alexaRank;
    }

    public void setAlexaRank(Integer alexaRank) {
        this.alexaRank = alexaRank;
    }

    public Integer getChinazRank() {
        return chinazRank;
    }

    public void setChinazRank(Integer chinazRank) {
        this.chinazRank = chinazRank;
    }

    public String  getChinazScope() {
        return chinazScope;
    }

    public void setChinazScope(String chinazScope) {
        this.chinazScope = chinazScope;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("id=").append(id);
        sb.append(", siteName=").append(siteName);
        sb.append(", siteUrl=").append(siteUrl);
        sb.append(", alexaRank=").append(alexaRank);
        sb.append(", chinazRank=").append(chinazRank);
        sb.append(", chinazScope=").append(chinazScope);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}