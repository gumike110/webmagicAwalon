package org.awalon.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.awalon.utils.JsonUtil;
import org.awalon.model.UrlRank;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * @author 410775541@qq.com <br>
 * @since 0.5.1
 */
public class TopChinazPageProcessor implements PageProcessor {

    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setCharset("UTF-8");

    //private static final int voteNum = 1000;
    /*@Autowired
    private UrlRankMapper urlMappper;*/

    @Override
	public void process(Page page) {
    	List<String> relativeUrl =  new ArrayList<String>(10);
    	for(int i=2;i<=50;i++){
    		StringBuffer url = new StringBuffer();
    		url.append("http://top.chinaz.com/all/index_");
    		url.append(i);
    		url.append(".html");
    		relativeUrl.add(url.toString());
    	}
    	page.addTargetRequests(relativeUrl);
		List<String> rankList = page.getHtml().xpath("//li[@class='clearfix']").all();
		
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		//boolean exist = false;
		for (String rank : rankList) {
			Map<String,String> data = new HashMap<String,String>();
			String siteName = new Html(rank).xpath("//div[@class='CentTxt']/h3[@class='rightTxtHead']/a/@title").toString();
			String siteUrl = new Html(rank).xpath("//div[@class='CentTxt']/h3[@class='rightTxtHead']/span/text()").toString();
			List<String> rankTypes = new Html(rank).xpath("//div[@class='RtCPart clearfix']/p").all();
			String chinaz_rank = new Html(rank).xpath("//div[@class='RtCRateCent']/strong/text()").toString();
			String chinaz_scope = new Html(rank).xpath("//div[@class='RtCRateCent']/span/text()").toString();
			String alexa_rank = "";
			
			for(String rankType:rankTypes){
				String rankName = new Html(rankType).xpath("//span/text()").toString();			
				if(rankName.contains("Alexa周排名")){
					alexa_rank=new Html(rankType).xpath("//a/text()").toString();
					break;
				}				
			}

			data.put("siteName", siteName);
			data.put("siteUrl", siteUrl);
			data.put("alexaRank", alexa_rank);
			data.put("chinazRank", chinaz_rank);
			data.put("chinazScope", chinaz_scope.split(":")[1]);
			Date date = new Date();
			System.out.println(data);
			UrlRank rankVo = JsonUtil.toBean(data, UrlRank.class);
			System.out.println("rankVo===="+rankVo);
			rankVo.setCreateTime(date);
			rankVo.setUpdateTime(date);
			//urlMappper.insert(rankVo);
			result.add(data);
		}
		
		page.putField("rank", result);
	}

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	 ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/applicationContext.xml");
         final TopChinazPageProcessor process = applicationContext.getBean(TopChinazPageProcessor.class);
         //jobCrawler.crawl();
        Spider.create(process).
                addUrl("http://top.chinaz.com/all/").
                addPipeline(new JsonFilePipeline("D:\\webmagic\\")).
                thread(10).
                run();
    }
}
