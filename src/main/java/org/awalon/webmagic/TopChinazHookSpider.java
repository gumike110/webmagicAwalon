package org.awalon.webmagic;

import java.util.Date;

import org.awalon.model.SpiderRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author 410775541@qq.com <br>
 * @since 0.5.1
 */
@Service
public class TopChinazHookSpider implements PageProcessor {
	
    @Autowired
    private MongoTemplate mongoTemplate;

    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setCharset("UTF-8");

    //private static final int voteNum = 1000;
    //@Autowired
    //private UrlRankMapper urlMappper;

    @Override
	public void process(Page page) {
		String updateTimeStr = page.getHtml().xpath("//div[@class='lsitHead']/p/span[@class='col-gray']/text()").toString().trim();
		String updateTimeSub= updateTimeStr.subSequence(1, updateTimeStr.length()-1).toString();
		String updateTime = "";
		if(updateTimeSub.contains("：")){
			updateTime = updateTimeSub.split("：")[1];
		}
		//注意断言网页更新时间爬虫判断异常
		Assert.hasText(updateTime, "网页updateTime获取失败");
		//SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");	
		System.out.println(updateTime);
		Query query = new Query();
		//获取最后一次的爬虫记录
		SpiderRecord vo= mongoTemplate.findOne(query.with(new Sort(Direction.DESC,"createTime")), SpiderRecord.class, "spider_record");
		//如果第一次或者更新时间和最后一次时间不等就启动爬虫
		if(null == vo || !vo.getCrawlerTime().equals(updateTime)){
			SpiderRecord record = new SpiderRecord();
			Date date = new Date();
			record.setCrawlerTime(updateTime);
			record.setCreateTime(date);
			record.setUpdateTime(date);
			mongoTemplate.insert(record, "spider_record");
			page.putField("updateTime", updateTime);
		}else{
			return ;
		}
			
	}

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	 ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/applicationContext.xml");
         final TopChinazHookSpider hookSpider = applicationContext.getBean(TopChinazHookSpider.class);
         //jobCrawler.crawl();
  /*      Spider.create(process).addUrl("http://top.chinaz.com/all/").
                //addPipeline(new JsonFilePipeline("D:\\ProgramData\\webmagic\\")).
                thread(1).run();*/
         ResultItems result = Spider.create(hookSpider).thread(1).get("http://top.chinaz.com/all/");
         //System.out.println(result.get("updateTime"));
    }
}
