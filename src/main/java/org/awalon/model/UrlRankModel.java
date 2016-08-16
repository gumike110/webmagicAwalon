package org.awalon.model;

import java.io.Serializable;

import org.awalon.webmagic.ChianzScopeFormat;
import org.awalon.webmagic.RankConvertFormat;
import org.springframework.data.annotation.Id;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.Formatter;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("http://top.chinaz.com/all/(index_([0-9]|[1-9][0-9]|1[0-9][0-9]).html)?")
@ExtractBy(value = "//li[@class='clearfix']",multi = true)
public class UrlRankModel implements Serializable {
	@Id
    private String id;
    
    //站点名称
    @ExtractBy("//div[@class='CentTxt']/h3[@class='rightTxtHead']/a/@title")
    private String siteName;
    
    //站点地址
    @ExtractBy("//div[@class='CentTxt']/h3[@class='rightTxtHead']/span/text()")
    private String siteUrl;
    
    //Alexa周排名
    //@ExtractBy("//div[@class='RtCPart clearfix']/p/a/regex(@href,'http://alexa.chinaz.com/*')/text()")
    @ExtractBy("//div[@class='RtCPart clearfix']/p[1]/a/text()")
    private Integer alexaRank;
    
    //百度权重值
    //@ExtractBy("//div[@class='RtCPart clearfix']/p/a/regex(@href,'http://mytool.chinaz.com/*')/img/@src")
    @ExtractBy("//div[@class='RtCPart clearfix']/p[2]/a/img/@src")
    @Formatter(formatter = RankConvertFormat.class)
    private Integer baiduWeight;
    
    //Google的PR值
    @ExtractBy("//div[@class='RtCPart clearfix']/p[3]/a/img/@src")
    @Formatter(formatter = RankConvertFormat.class)
    private Integer googlePR;
    
    //站长之家综合排名
    @ExtractBy("//div[@class='RtCRateCent']/strong/text()")
    private Integer chinazRank;
    
    //站长之家综合得分
    //@Formatter(value = "author is %s",formatter  = StringTemplateFormatter.class)
    @ExtractBy("//div[@class='RtCRateCent']/span/text()")
    @Formatter(formatter = ChianzScopeFormat.class)
    private Integer chinazScore;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Integer getBaiduWeight() {
		return baiduWeight;
	}

	public void setBaiduWeight(Integer baiduWeight) {
		this.baiduWeight = baiduWeight;
	}

	public Integer getGooglePR() {
		return googlePR;
	}

	public void setGooglePR(Integer googlePR) {
		this.googlePR = googlePR;
	}

	public Integer getChinazRank() {
        return chinazRank;
    }

    public void setChinazRank(Integer chinazRank) {
        this.chinazRank = chinazRank;
    }

    public Integer getChinazScore() {
        return chinazScore;
    }

    public void setChinazScope(Integer chinazScore) {
        this.chinazScore = chinazScore;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        //sb.append("id=").append(id);
        sb.append(", siteName=").append(siteName);
        sb.append(", siteUrl=").append(siteUrl);
        sb.append(", alexaRank=").append(alexaRank);
        sb.append(", baiduWeight=").append(baiduWeight);
        sb.append(", googlePR=").append(googlePR);
        sb.append(", chinazRank=").append(chinazRank);
        sb.append(", chinazScore=").append(chinazScore);
        sb.append("]");
        return sb.toString();
    }
}