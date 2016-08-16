package org.awalon.webmagic;

import org.awalon.model.UrlRankModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

/**
 * 信息抓取，注解方式爬取
 */
@Component
public class TopChianzSpider {

	@Autowired
	private TopChinazPipeline topChinazPipeline;

	private static final String START_URL = "http://top.chinaz.com/all/";

	private Site site = Site
			.me()
			.setCycleRetryTimes(5)
			.setRetryTimes(5)
			.setSleepTime(1000)
			.setTimeOut(3 * 60 * 1000)
			.setUserAgent(
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
			.setCharset("UTF-8");

	public void crawl(String updateTime) {
		//初始化mongo中的集合名称
		topChinazPipeline.initialize(updateTime);
		//启动爬虫
		OOSpider.create(site, topChinazPipeline, UrlRankModel.class)
				.addUrl(START_URL)
				.thread(10).run();
	}

	public static void main(String[] args) {
		 ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/applicationContext.xml");
		 //applicationContext.getBean(TopChianzSpider.class).crawl();
	}
}
